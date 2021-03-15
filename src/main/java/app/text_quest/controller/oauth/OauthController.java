package app.text_quest.controller.oauth;

import app.text_quest.TextQuestApplication;
import app.text_quest.controller.oauth.util.constant.PropName;
import app.text_quest.controller.oauth.util.constant.ReqParam;
import app.text_quest.controller.oauth.util.constant.SecureParam;
import app.text_quest.controller.oauth.util.exception.OauthException;
import app.text_quest.controller.oauth.util.exception.types.ApiException;
import app.text_quest.controller.oauth.util.exception.types.InvalidStateException;
import app.text_quest.controller.oauth.util.exception.types.MissedStateCookieException;
import app.text_quest.controller.oauth.util.props.OauthProps;
import app.text_quest.controller.oauth.util.props.OauthPropsFactory;
import app.text_quest.controller.oauth.util.request.UrlBuilder;
import app.text_quest.controller.oauth.util.request.types.GetRequest;
import app.text_quest.controller.oauth.util.request.types.PostRequest;
import app.text_quest.controller.util.CookieUtil;
import app.text_quest.controller.util.json.oauth.JsonId;
import app.text_quest.controller.util.json.oauth.JsonToken;
import app.text_quest.util.LoggerFactory;
import app.text_quest.util.constant.LogType;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import org.apache.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@RequestMapping("/oauth")
public abstract class OauthController {

    protected final static Logger oauthLogger = LoggerFactory.getLogger(LogType.ERROR);
    protected final static Logger errLogger = LoggerFactory.getLogger(LogType.ERROR);
    private final static OauthPropsFactory propsFactory = new OauthPropsFactory();
    protected final String provider;
    protected final OauthProps props;

    public OauthController(String provider) {
        this.provider = provider;
        this.props = propsFactory.getFor(provider);
    }

    protected String oauthEndpoint(HttpServletRequest request, HttpServletResponse response) {
        try {
            String code = receiveCode(request, response);
            oauthLogger.info("code: " + code);
            String accessToken = receiveToken(code);
            oauthLogger.info("token: " + accessToken);
            String oauthId = receiveId(accessToken);
            oauthLogger.info("id: " + oauthId);
            request.setAttribute(SecureParam.OAUTH_ID, oauthId);
            return "forward:/auth/oauth";
        } catch (OauthException e) {
            errLogger.error(e.getMessage(), e);
            return "redirect:/";
        }
    }

    protected String receiveCode(HttpServletRequest request, HttpServletResponse response) throws OauthException {
        String code = request.getParameter(ReqParam.CODE);
        String state = request.getParameter(ReqParam.STATE);
        Cookie stateCookie = CookieUtil.find(request, ReqParam.STATE);
        CookieUtil.remove(response, ReqParam.STATE);
        String error = request.getParameter(ReqParam.ERROR);
        if (error != null)
            throw new ApiException(request.getParameter(ReqParam.ERROR_DESCRIPTION), Integer.parseInt(error));
        if (stateCookie == null)
            throw new MissedStateCookieException("Missed state cookie");
        if (!state.equals(stateCookie.getValue()))
            throw new InvalidStateException(state, stateCookie.getValue());
        return code;
    }

    protected String receiveToken(String code) throws OauthException, JsonSyntaxException {
        UrlBuilder urlBuilder = new UrlBuilder(props.get(PropName.DOMAIN_TOKEN));
        PostRequest request = new PostRequest(urlBuilder);
        request
                .addParam(ReqParam.CLIENT_ID, props.get(PropName.CLIENT_ID))
                .addParam(ReqParam.CLIENT_SECRET, props.get(PropName.CLIENT_SECRET))
                .addParam(ReqParam.CODE, code)
                .addParam(ReqParam.GRANT_TYPE, "authorization_code")
                .addParam(ReqParam.REDIRECT_URI, String.format("%s/oauth/%s",
                        TextQuestApplication.getRootUrl(), provider));
        String response = request.send();
        JsonToken jsonToken = new Gson().fromJson(response, JsonToken.class);
        return jsonToken.getAccessToken();
    }

    protected String receiveId(String token) throws OauthException, JsonSyntaxException {
        UrlBuilder urlBuilder = new UrlBuilder(props.get(PropName.DOMAIN_ID));
        GetRequest request = new GetRequest(urlBuilder);
        request
                .addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token);
        String response = request.send();
        JsonId jsonId = new Gson().fromJson(response, JsonId.class);
        return jsonId.getId();
    }
}
