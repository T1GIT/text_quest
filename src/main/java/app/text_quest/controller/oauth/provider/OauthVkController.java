package app.text_quest.controller.oauth.provider;


import app.text_quest.TextQuestApplication;
import app.text_quest.controller.oauth.OauthController;
import app.text_quest.controller.oauth.util.enums.PropName;
import app.text_quest.controller.oauth.util.enums.Provider;
import app.text_quest.controller.oauth.util.enums.ReqParam;
import app.text_quest.controller.oauth.util.json.JsonToken;
import app.text_quest.controller.oauth.util.json.vk.JsonVk;
import app.text_quest.controller.oauth.util.request.UrlBuilder;
import app.text_quest.controller.oauth.util.request.types.GetRequest;
import app.text_quest.security.auth.OauthAuthManager;
import app.text_quest.util.exceptions.OauthApiException;
import com.google.gson.Gson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
public class OauthVkController extends OauthController {

    public OauthVkController(OauthAuthManager oauthAuthManager) {
        super(oauthAuthManager, Provider.VK);
    }

    @GetMapping("/vk")
    @Override
    public String oauthEndpoint(HttpServletRequest request, HttpServletResponse response) {
        return super.oauthEndpoint(request, response);
    }

    @Override
    public String receiveToken(String code) throws OauthApiException {
        UrlBuilder urlBuilder = new UrlBuilder(props.get(PropName.DOMAIN_TOKEN))
                .addParam(ReqParam.CLIENT_ID, props.get(PropName.CLIENT_ID))
                .addParam(ReqParam.CLIENT_SECRET, props.get(PropName.CLIENT_SECRET))
                .addParam(ReqParam.CODE, code)
                .addParam(ReqParam.REDIRECT_URI, String.format("%s/oauth/%s",
                        TextQuestApplication.getRootUrl(), provider.lowName()));
        GetRequest request = new GetRequest(urlBuilder.build());
        String response = request.send();
        JsonToken jsonToken = new Gson().fromJson(response, JsonToken.class);
        return jsonToken.getAccessToken();
    }

    @Override
    public String receiveId(String token) throws OauthApiException {
        UrlBuilder urlBuilder = new UrlBuilder(props.get(PropName.DOMAIN_ID))
                .addParam(ReqParam.ACCESS_TOKEN, token)
                .addParam(ReqParam.FIELDS, "uid")
                .addParam(ReqParam.V, "5.130");
        GetRequest request = new GetRequest(urlBuilder.build());
        String response = request.send();
        JsonVk jsonVk = new Gson().fromJson(response, JsonVk.class);
        return jsonVk.getResponse()[0].getId();
    }
}
