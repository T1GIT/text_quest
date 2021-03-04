package app.text_quest.controller.oauth;

import app.text_quest.TextQuestApplication;
import app.text_quest.controller.oauth.util.enums.PropName;
import app.text_quest.controller.oauth.util.enums.Provider;
import app.text_quest.controller.oauth.util.enums.ReqParam;
import app.text_quest.controller.oauth.util.enums.SecureParam;
import app.text_quest.controller.oauth.util.json.JsonId;
import app.text_quest.controller.oauth.util.json.JsonToken;
import app.text_quest.controller.oauth.util.props.OauthProps;
import app.text_quest.controller.oauth.util.props.OauthPropsFactory;
import app.text_quest.controller.oauth.util.request.UrlBuilder;
import app.text_quest.controller.oauth.util.request.types.GetRequest;
import app.text_quest.controller.oauth.util.request.types.PostRequest;
import app.text_quest.database.model.user.types.OauthUser;
import app.text_quest.security.auth.OauthAuthManager;
import app.text_quest.security.util.secretFactory.types.TokenFactory;
import app.text_quest.util.LoggerFactory;
import app.text_quest.util.enums.LogType;
import app.text_quest.util.exceptions.OauthApiException;
import com.google.gson.Gson;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;


@RequestMapping("/oauth")
public abstract class OauthController {

    protected final static Logger logger = LoggerFactory.getLogger(LogType.ERROR);
    private final static OauthPropsFactory propsFactory = new OauthPropsFactory();
    private final static TokenFactory tokenFactory = new TokenFactory();
    protected final OauthAuthManager authManager;
    protected final Provider provider;
    protected final OauthProps props;

    public OauthController(OauthAuthManager authManager, Provider provider) {
        this.authManager = authManager;
        this.provider = provider;
        this.props = propsFactory.getFor(provider);
    }

    public String oauthEndpoint(HttpServletRequest request, HttpServletResponse response) {
        try {
            if (request.getParameter(ReqParam.ERROR.lowName()) == null) {
                String code = request.getParameter(ReqParam.CODE.lowName());
                String state = request.getParameter(ReqParam.STATE.lowName());
                if (state.equals(request.getSession().getAttribute(SecureParam.STATE.name()))) {
                    String accessToken = receiveToken(code);
                    String oauthId = receiveId(accessToken);
                    OauthUser user = authManager.findUser(oauthId);
                    if (user == null) {
                        user = authManager.register(oauthId);
                    }
                    request.getSession().setAttribute(SecureParam.NEEDS_TOKENS.name(), user);
                }
                request.getSession().removeAttribute(ReqParam.STATE.lowName());
            }
        } catch (OauthApiException e) {
            logger.error(e.getMessage(), e);
        }
        return "redirect:/";
    }

    protected String receiveToken(String code) throws OauthApiException {
        UrlBuilder urlBuilder = new UrlBuilder(props.get(PropName.DOMAIN_TOKEN));
        PostRequest request = new PostRequest(urlBuilder.build());
        HashMap<ReqParam, String> paramMap = new HashMap<>();
        paramMap.put(ReqParam.CLIENT_ID, props.get(PropName.CLIENT_ID));
        paramMap.put(ReqParam.CLIENT_SECRET, props.get(PropName.CLIENT_SECRET));
        paramMap.put(ReqParam.CODE, code);
        paramMap.put(ReqParam.GRANT_TYPE, "authorization_code");
        paramMap.put(ReqParam.REDIRECT_URI, String.format("%s/oauth/%s",
                TextQuestApplication.getRootUrl(), provider.lowName()));
        request.setData(paramMap);
        String response = request.send();
        JsonToken jsonToken = new Gson().fromJson(response, JsonToken.class);
        return jsonToken.getAccessToken();
    }

    protected String receiveId(String token) throws OauthApiException {
        UrlBuilder urlBuilder = new UrlBuilder(props.get(PropName.DOMAIN_ID))
                .addParam(ReqParam.ACCESS_TOKEN, token)
                .addParam(ReqParam.OAUTH_TOKEN, token);
        GetRequest request = new GetRequest(urlBuilder.build());
        String response = request.send();
        JsonId jsonId = new Gson().fromJson(response, JsonId.class);
        return jsonId.getId();
    }
}
