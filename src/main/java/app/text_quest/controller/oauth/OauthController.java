package app.text_quest.controller.oauth;

import app.text_quest.TextQuestApplication;
import app.text_quest.controller.oauth.util.enums.PropName;
import app.text_quest.controller.oauth.util.enums.Provider;
import app.text_quest.controller.oauth.util.enums.ReqParam;
import app.text_quest.controller.oauth.util.enums.SecureParam;
import app.text_quest.controller.oauth.util.exceptions.OauthException;
import app.text_quest.controller.oauth.util.exceptions.types.ApiException;
import app.text_quest.controller.oauth.util.exceptions.types.InvalidStateException;
import app.text_quest.controller.oauth.util.exceptions.types.MissedStateCookieException;
import app.text_quest.controller.oauth.util.json.JsonId;
import app.text_quest.controller.oauth.util.json.JsonToken;
import app.text_quest.controller.oauth.util.props.OauthProps;
import app.text_quest.controller.oauth.util.props.OauthPropsFactory;
import app.text_quest.controller.oauth.util.request.UrlBuilder;
import app.text_quest.controller.oauth.util.request.types.GetRequest;
import app.text_quest.controller.oauth.util.request.types.PostRequest;
import app.text_quest.controller.util.CookieUtil;
import app.text_quest.security.util.secretFactory.types.RefreshFactory;
import app.text_quest.util.LoggerFactory;
import app.text_quest.util.enums.LogType;
import com.google.gson.Gson;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;


@RequestMapping("/oauth")
public abstract class OauthController {

    protected final static Logger logger = LoggerFactory.getLogger(LogType.ERROR);
    private final static OauthPropsFactory propsFactory = new OauthPropsFactory();
    private final static RefreshFactory REFRESH_FACTORY = new RefreshFactory();
    protected final Provider provider;
    protected final OauthProps props;

    public OauthController(Provider provider) {
        this.provider = provider;
        this.props = propsFactory.getFor(provider);
    }

    public String oauthEndpoint(HttpServletRequest request, HttpServletResponse response) {
        try {
            String code = request.getParameter(ReqParam.CODE.lowName());
            String state = request.getParameter(ReqParam.STATE.lowName());
            Cookie stateCookie = CookieUtil.find(request, ReqParam.STATE.name());
            CookieUtil.remove(response, ReqParam.STATE.name());
            if (request.getParameter(ReqParam.ERROR.lowName()) != null)
                throw new ApiException(
                        request.getParameter(ReqParam.ERROR_DESCRIPTION.lowName()),
                        Integer.parseInt(request.getParameter(ReqParam.ERROR.lowName())));
            if (stateCookie == null)
                throw new MissedStateCookieException("Missed state cookie");
            if (!state.equals(stateCookie.getValue()))
                throw new InvalidStateException(state, stateCookie.getValue());
            String accessToken = receiveToken(code);
            String oauthId = receiveId(accessToken);
            request.setAttribute(SecureParam.OAUTH_ID.name(), oauthId);
            return "forward:/auth/oauth";
        } catch (OauthException e) {
            logger.error(e.getMessage(), e);
            return "redirect:/";
        }
    }

    protected String receiveToken(String code) throws ApiException {
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

    protected String receiveId(String token) throws ApiException {
        UrlBuilder urlBuilder = new UrlBuilder(props.get(PropName.DOMAIN_ID))
                .addParam(ReqParam.ACCESS_TOKEN, token)
                .addParam(ReqParam.OAUTH_TOKEN, token);
        GetRequest request = new GetRequest(urlBuilder.build());
        String response = request.send();
        JsonId jsonId = new Gson().fromJson(response, JsonId.class);
        return jsonId.getId();
    }
}
