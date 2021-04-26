package app.controller.oauth.provider;


import app.controller.oauth.OauthController;
import app.controller.oauth.util.constant.PropName;
import app.controller.oauth.util.constant.Provider;
import app.controller.oauth.util.constant.ReqParam;
import app.controller.oauth.util.exception.types.ApiException;
import app.controller.oauth.util.request.types.PostRequest;
import app.controller.util.json.oauth.JsonToken;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @see app.controller.oauth.OauthController
 */
@Controller
public class YandexOauthController extends OauthController {

    public YandexOauthController() {
        super(Provider.YANDEX);
    }

    @GetMapping("/yandex")
    @Override
    public String oauthEndpoint(HttpServletRequest request, HttpServletResponse response) {
        return super.oauthEndpoint(request, response);
    }

    @Override
    protected String receiveToken(String code) throws ApiException, JsonSyntaxException {
        PostRequest request = new PostRequest(props.getProperty(PropName.DOMAIN_TOKEN));
        request
                .addParam(ReqParam.CLIENT_ID, props.getProperty(PropName.CLIENT_ID))
                .addParam(ReqParam.CLIENT_SECRET, props.getProperty(PropName.CLIENT_SECRET))
                .addParam(ReqParam.CODE, code)
                .addParam(ReqParam.GRANT_TYPE, "authorization_code");
        String response = request.send();
        JsonToken jsonToken = new Gson().fromJson(response, JsonToken.class);
        return jsonToken.getAccessToken();
    }
}
