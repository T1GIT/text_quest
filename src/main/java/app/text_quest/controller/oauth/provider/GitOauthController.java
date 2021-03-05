package app.text_quest.controller.oauth.provider;


import app.text_quest.controller.oauth.OauthController;
import app.text_quest.controller.oauth.util.constant.PropName;
import app.text_quest.controller.oauth.util.constant.Provider;
import app.text_quest.controller.oauth.util.constant.ReqParam;
import app.text_quest.controller.oauth.util.exception.OauthException;
import app.text_quest.controller.oauth.util.json.JsonToken;
import app.text_quest.controller.oauth.util.request.UrlBuilder;
import app.text_quest.controller.oauth.util.request.types.PostRequest;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
public class GitOauthController extends OauthController {

    public GitOauthController() {
        super(Provider.GIT);
    }

    @GetMapping("/git")
    @Override
    protected String oauthEndpoint(HttpServletRequest request, HttpServletResponse response) {
        return super.oauthEndpoint(request, response);
    }

    @Override
    protected String receiveToken(String code) throws OauthException, JsonSyntaxException {
        UrlBuilder urlBuilder = new UrlBuilder(props.get(PropName.DOMAIN_TOKEN));
        PostRequest request = new PostRequest(urlBuilder);
        request
                .addHeader(HttpHeaders.ACCEPT, "application/json")
                .addParam(ReqParam.CLIENT_ID, props.get(PropName.CLIENT_ID))
                .addParam(ReqParam.CLIENT_SECRET, props.get(PropName.CLIENT_SECRET))
                .addParam(ReqParam.CODE, code);
        String response = request.send();
        JsonToken jsonToken = new Gson().fromJson(response, JsonToken.class);
        return jsonToken.getAccessToken();
    }
}
