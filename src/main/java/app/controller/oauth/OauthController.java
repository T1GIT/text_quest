package app.controller.oauth;

import app.TextQuestApplication;
import app.controller.AuthController;
import app.controller.oauth.util.constant.PropName;
import app.controller.oauth.util.constant.ReqParam;
import app.controller.oauth.util.constant.SecureParam;
import app.controller.oauth.util.exception.OauthException;
import app.controller.oauth.util.exception.types.ApiException;
import app.controller.oauth.util.exception.types.InvalidStateException;
import app.controller.oauth.util.exception.types.MissedStateCookieException;
import app.controller.oauth.util.request.types.GetRequest;
import app.controller.oauth.util.request.types.PostRequest;
import app.controller.util.CookieUtil;
import app.controller.util.json.oauth.JsonId;
import app.controller.util.json.oauth.JsonToken;
import app.util.LoggerFactory;
import app.util.constant.LogType;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import org.apache.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


/**
 * Endpoint controller for oauth authorisation.
 * When user firstly download the web page, its oauth buttons get a link for redirecting user to
 * oauth server for authorising. When authorisation process ends
 * <ol>
 * <li> Oauth server redirects user to this endpoint adding code to
 * the request for identifying server in the future request
 * <li> The endpoint requests access token, sending received code in the POST (GET for VK) request
 * <li> The endpoint requests user id, sending received access token in the GET request
 * </ol>
 */
@RequestMapping("/oauth")  // TODO: 05.03.2021 Add cookie filter with crypting
public abstract class OauthController { // TODO: 06.03.2021 Add Steam and Telegram oauth

    /**
     * Logger for recording endpoint's actions
     */
    protected final static Logger oauthLogger = LoggerFactory.getLogger(LogType.OAUTH);

    /**
     * Logger for recording exceptions
     */
    protected final static Logger errLogger = LoggerFactory.getLogger(LogType.ERROR);

    /**
     * Specified name of the oauth server
     */
    protected final String provider;

    /**
     * Properties containing:
     * <ul>
     * <li> Client id
     * <li> Client secret
     * <li> Authorisation domain
     * <li> Access token domain
     * <li> Id domain
     * <li> Scope (optional)
     * </ul>
     */
    protected final Properties props;

    /**
     * Class constructor, specifying provider name.
     *
     * @param provider name of the provider
     */
    public OauthController(String provider) {
        this.provider = provider;
        this.props = new Properties();
        try {
            props.load(new FileInputStream("src/main/resources/props/oauth/" + provider + ".properties"));
        } catch (IOException e) {
            errLogger.error(e.getMessage(), e);
        }
    }

    /**
     * Mapping, that receives oauth server request to authorising an user.
     * <p>
     * Every children class must call this method in the overridden method with annotation
     * {@code @GetMapping("/[provider]")}
     * <p>
     * Steps of authorising:
     * <ol>
     * <li> Checks if state in request matches with state cookie in the client
     * <li> Gets code from the request via {@link OauthController#receiveCode(HttpServletRequest)}
     * <li> Sends request for receiving access token, using {@link OauthController#receiveToken(String)} with the
     * code received in the previous step.
     * <li> Sends request for receiving unique user id, using {@link OauthController#receiveId(String)} with the
     * access token received in the previous step.
     * <li> Attaches oauth id to the request and forwards to
     * the {@link AuthController#oauth(HttpServletRequest, HttpServletResponse)}
     * </ol>
     *
     * @param request  contains code
     * @param response for deleting state cookie
     * @return forward to {@link AuthController#oauth(HttpServletRequest, HttpServletResponse)} or
     * to the home page, if error occurs while authorising
     */
    public String oauthEndpoint(HttpServletRequest request, HttpServletResponse response) {
        try {
            validateState(request, response);
            String code = receiveCode(request);
            oauthLogger.info("code: " + code);
            String accessToken = receiveToken(code);
            oauthLogger.info("token: " + accessToken);
            String oauthId = receiveId(accessToken);
            oauthLogger.info("id: " + oauthId);
            request.setAttribute(SecureParam.OAUTH_ID, provider + ":" + oauthId);
            return "forward:/auth/oauth";
        } catch (OauthException e) {
            errLogger.error(e.getMessage(), e);
            return "redirect:/";
        }
    }

    /**
     * Checks if state in request matches with state cookie in the client.
     *
     * @param request  contains state cookie
     * @param response target for removing cookie
     * @throws MissedStateCookieException if state cookie not found in the request
     * @throws InvalidStateException      if state cookie doesn't match cookie from the request
     */
    protected void validateState(HttpServletRequest request, HttpServletResponse response) throws MissedStateCookieException, InvalidStateException {
        String state = request.getParameter(ReqParam.STATE);
        Cookie stateCookie = CookieUtil.find(request, ReqParam.STATE);
        CookieUtil.remove(response, ReqParam.STATE);
        if (stateCookie == null)
            throw new MissedStateCookieException();
        if (!state.equals(stateCookie.getValue()))
            throw new InvalidStateException(state, stateCookie.getValue());
    }

    /**
     * Gets code from the request or throw {@link ApiException}.
     *
     * @param request for extracting parameters
     * @return code or throw {@link ApiException} if request contains error
     * @throws ApiException if request contains error
     */
    protected String receiveCode(HttpServletRequest request) throws ApiException {
        String code = request.getParameter(ReqParam.CODE);
        String error = request.getParameter(ReqParam.ERROR);
        if (error != null)
            throw new ApiException(request.getParameter(ReqParam.ERROR_DESCRIPTION), Integer.parseInt(error));
        return code;
    }

    /**
     * Sends request to the oauth server for receiving access token.
     *
     * @param code received from the server in the endpoint request
     * @return access token
     * @throws ApiException        if request to the oauth server wasn't success
     * @throws JsonSyntaxException if answer format is incorrect
     */
    protected String receiveToken(String code) throws ApiException, JsonSyntaxException {
        PostRequest request = new PostRequest(props.getProperty(PropName.DOMAIN_TOKEN));
        request
                .addParam(ReqParam.CLIENT_ID, props.getProperty(PropName.CLIENT_ID))
                .addParam(ReqParam.CLIENT_SECRET, props.getProperty(PropName.CLIENT_SECRET))
                .addParam(ReqParam.CODE, code)
                .addParam(ReqParam.GRANT_TYPE, "authorization_code")
                .addParam(ReqParam.REDIRECT_URI, String.format("%s/oauth/%s", TextQuestApplication.getRootUrl(), provider));
        String response = request.send();
        JsonToken jsonToken = new Gson().fromJson(response, JsonToken.class);
        return jsonToken.getAccessToken();
    }

    /**
     * Sends request to the oauth server for receiving unique user id.
     *
     * @param token access token, received from the server in the endpoint request
     * @return user id
     * @throws ApiException        if request to the oauth server wasn't success
     * @throws JsonSyntaxException if answer format is incorrect
     */
    protected String receiveId(String token) throws ApiException, JsonSyntaxException {
        GetRequest request = new GetRequest(props.getProperty(PropName.DOMAIN_ID));
        request
                .addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token);
        String response = request.send();
        JsonId jsonId = new Gson().fromJson(response, JsonId.class);
        return jsonId.getId();
    }
}
