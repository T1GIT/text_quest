package app.text_quest.controller.oauth.provider;


import app.text_quest.TextQuestApplication;
import app.text_quest.controller.oauth.OauthController;
import app.text_quest.controller.oauth.util.constant.PropName;
import app.text_quest.controller.oauth.util.constant.Provider;
import app.text_quest.controller.oauth.util.constant.ReqParam;
import app.text_quest.controller.oauth.util.exception.OauthException;
import app.text_quest.controller.oauth.util.json.JsonToken;
import app.text_quest.controller.oauth.util.json.vk.JsonVk;
import app.text_quest.controller.oauth.util.request.UrlBuilder;
import app.text_quest.controller.oauth.util.request.types.GetRequest;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
public class VkOauthController extends OauthController {

    public VkOauthController() {
        super(Provider.VK);
    }

    @GetMapping("/vk")
    @Override
    protected String oauthEndpoint(HttpServletRequest request, HttpServletResponse response) {
        return super.oauthEndpoint(request, response);
    }

    @Override
    protected String receiveToken(String code) throws OauthException, JsonSyntaxException {
        UrlBuilder urlBuilder = new UrlBuilder(props.get(PropName.DOMAIN_TOKEN));
        GetRequest request = new GetRequest(urlBuilder);
        request
                .addParam(ReqParam.CLIENT_ID, props.get(PropName.CLIENT_ID))
                .addParam(ReqParam.CLIENT_SECRET, props.get(PropName.CLIENT_SECRET))
                .addParam(ReqParam.CODE, code)
                .addParam(ReqParam.REDIRECT_URI, String.format("%s/oauth/%s",
                        TextQuestApplication.getRootUrl(), provider));
        String response = request.send();
        JsonToken jsonToken = new Gson().fromJson(response, JsonToken.class);
        return jsonToken.getAccessToken();
    }

    @Override
    protected String receiveId(String token) throws OauthException, JsonSyntaxException {
        UrlBuilder urlBuilder = new UrlBuilder(props.get(PropName.DOMAIN_ID));
        GetRequest request = new GetRequest(urlBuilder);
        request
                .addParam(ReqParam.ACCESS_TOKEN, token)
                .addParam(ReqParam.FIELDS, "uid")
                .addParam(ReqParam.V, "5.130");
        String response = request.send();
        JsonVk jsonVk = new Gson().fromJson(response, JsonVk.class);
        return jsonVk.getResponse()[0].getId();
    }
}
