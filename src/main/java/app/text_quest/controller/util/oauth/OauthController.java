package app.text_quest.controller.util.oauth;

import app.text_quest.TextQuestApplication;
import app.text_quest.controller.util.oauth.enums.PropName;
import app.text_quest.controller.util.oauth.enums.Provider;
import app.text_quest.controller.util.oauth.enums.ReqParam;
import app.text_quest.controller.util.oauth.util.exception.OauthApiException;
import app.text_quest.controller.util.oauth.util.json.JsonId;
import app.text_quest.controller.util.oauth.util.json.JsonToken;
import app.text_quest.controller.util.oauth.util.props.OauthProps;
import app.text_quest.controller.util.oauth.util.props.OauthPropsFactory;
import app.text_quest.controller.util.oauth.util.request.UrlBuilder;
import app.text_quest.controller.util.oauth.util.request.types.GetRequest;
import app.text_quest.controller.util.oauth.util.request.types.PostRequest;
import app.text_quest.database.model.user.User;
import app.text_quest.database.model.user.types.OauthUser;
import app.text_quest.database.service.OauthUserService;
import app.text_quest.util.LoggerFactory;
import app.text_quest.util.enums.LogType;
import com.google.gson.Gson;
import org.apache.log4j.Logger;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;


public abstract class OauthController {

    private static final OauthPropsFactory propsFactory = new OauthPropsFactory();
    protected static final Logger logger = LoggerFactory.getLogger(LogType.ERROR);
    protected final OauthUserService oauthUserService;
    protected final Provider provider;
    protected final OauthProps props;

    public OauthController(OauthUserService oauthUserService, Provider provider) {
        this.oauthUserService = oauthUserService;
        this.provider = provider;
        this.props = propsFactory.getFor(provider);
    }

    public String oauthEndpoint(Cookie cookieState, HttpServletRequest request) {
        if (request.getParameter(ReqParam.ERROR.name().toLowerCase()) == null) {
            String code = request.getParameter(ReqParam.CODE.name().toLowerCase());
            String state = request.getParameter(ReqParam.STATE.name().toLowerCase());
            if (state.equals(cookieState.getValue())) {
                String token = receiveToken(code);
                String oauthId = receiveId(token);
                User user = authorise(oauthId);
            }
        }
        return "redirect:/";
    }

    protected String receiveToken(String code) {
        UrlBuilder urlBuilder = new UrlBuilder(props.get(PropName.DOMAIN_TOKEN));
        PostRequest request = new PostRequest(urlBuilder.build());
        HashMap<ReqParam, String> paramMap = new HashMap<>();
        paramMap.put(ReqParam.CLIENT_ID, props.get(PropName.CLIENT_ID));
        paramMap.put(ReqParam.CLIENT_SECRET, props.get(PropName.CLIENT_SECRET));
        paramMap.put(ReqParam.CODE, code);
        paramMap.put(ReqParam.GRANT_TYPE, "authorization_code");
        paramMap.put(ReqParam.REDIRECT_URI, String.format("%s/oauth/%s",
                TextQuestApplication.getRootUrl(), provider.name().toLowerCase()));
        request.setData(paramMap);
        try {
            String response = request.send();
            JsonToken jsonToken = new Gson().fromJson(response, JsonToken.class);
            return jsonToken.getAccessToken();
        } catch (OauthApiException oauthApiException) {
            logger.error(oauthApiException.getMessage(), oauthApiException);
            return null;
        }
    }

    protected String receiveId(String token) {
        UrlBuilder urlBuilder = new UrlBuilder(props.get(PropName.DOMAIN_ID))
                .addParam(ReqParam.ACCESS_TOKEN, token)
                .addParam(ReqParam.OAUTH_TOKEN, token);
        GetRequest request = new GetRequest(urlBuilder.build());
        try {
            String response = request.send();
            JsonId jsonId = new Gson().fromJson(response, JsonId.class);
            return jsonId.getId();
        } catch (OauthApiException e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    protected OauthUser authorise(String oauthId) {
        OauthUser user = oauthUserService.getByOauthId(oauthId);
        if (user == null) {
            user = new OauthUser();
            user.setOauthId(oauthId);
            oauthUserService.addOauthUser(user);
        }
        return user;
    }
}
