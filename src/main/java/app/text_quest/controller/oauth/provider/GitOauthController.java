package app.text_quest.controller.oauth.provider;


import app.text_quest.controller.oauth.OauthController;
import app.text_quest.controller.oauth.util.constant.PropName;
import app.text_quest.controller.oauth.util.constant.Provider;
import app.text_quest.controller.oauth.util.constant.ReqParam;
import app.text_quest.controller.oauth.util.exception.types.ApiException;
import app.text_quest.controller.oauth.util.request.types.PostRequest;
import app.text_quest.controller.util.json.oauth.JsonToken;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @see app.text_quest.controller.oauth.OauthController
 */
@Controller
public class GitOauthController extends OauthController {

    public GitOauthController() {
        super(Provider.GIT);
    }

    @GetMapping("/git")
    @Override
    public String oauthEndpoint(HttpServletRequest request, HttpServletResponse response) {
        return super.oauthEndpoint(request, response);
    }

    @Override
    protected String receiveToken(String code) throws ApiException, JsonSyntaxException {
        PostRequest request = new PostRequest(props.getProperty(PropName.DOMAIN_TOKEN));
        request
                .addHeader(HttpHeaders.ACCEPT, "application/json")
                .addParam(ReqParam.CLIENT_ID, props.getProperty(PropName.CLIENT_ID))
                .addParam(ReqParam.CLIENT_SECRET, props.getProperty(PropName.CLIENT_SECRET))
                .addParam(ReqParam.CODE, code);
        String response = request.send();
        JsonToken jsonToken = new Gson().fromJson(response, JsonToken.class);
        return jsonToken.getAccessToken();
    }
}
