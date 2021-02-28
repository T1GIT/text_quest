package app.text_quest.controller.util.oauth.provider;


import app.text_quest.TextQuestApplication;
import app.text_quest.controller.util.oauth.OauthController;
import app.text_quest.controller.util.oauth.enums.PropName;
import app.text_quest.controller.util.oauth.enums.Provider;
import app.text_quest.controller.util.oauth.enums.ReqParam;
import app.text_quest.controller.util.oauth.util.exception.OauthApiException;
import app.text_quest.controller.util.oauth.util.json.JsonToken;
import app.text_quest.controller.util.oauth.util.json.vk.JsonVk;
import app.text_quest.controller.util.oauth.util.request.UrlBuilder;
import app.text_quest.controller.util.oauth.util.request.types.GetRequest;
import app.text_quest.database.service.OauthUserService;
import com.google.gson.Gson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;


@Controller
public class OauthVkController extends OauthController {

    public OauthVkController(OauthUserService oauthUserService) {
        super(oauthUserService, Provider.VK);
    }

    @GetMapping("oauth/vk")
    @Override
    public String oauthEndpoint(@CookieValue(value = "state") Cookie cookieState, HttpServletRequest request) {
        return super.oauthEndpoint(cookieState, request);
    }

    @Override
    public String receiveToken(String code) {
        UrlBuilder urlBuilder = new UrlBuilder(props.get(PropName.DOMAIN_TOKEN))
                .addParam(ReqParam.CLIENT_ID, props.get(PropName.CLIENT_ID))
                .addParam(ReqParam.CLIENT_SECRET, props.get(PropName.CLIENT_SECRET))
                .addParam(ReqParam.CODE, code)
                .addParam(ReqParam.REDIRECT_URI, String.format("%s/oauth/%s",
                        TextQuestApplication.getRootUrl(), provider.name().toLowerCase()));
        GetRequest request = new GetRequest(urlBuilder.build());
        try {
            String response = request.send();
            JsonToken jsonToken = new Gson().fromJson(response, JsonToken.class);
            return jsonToken.getAccessToken();
        } catch (OauthApiException oauthApiException) {
            logger.error(oauthApiException.getMessage(), oauthApiException);
            return null;
        }
    }

    @Override
    public String receiveId(String token) {
        UrlBuilder urlBuilder = new UrlBuilder(props.get(PropName.DOMAIN_ID))
                .addParam(ReqParam.ACCESS_TOKEN, token)
                .addParam(ReqParam.FIELDS, "uid")
                .addParam(ReqParam.V, "5.130");
        GetRequest request = new GetRequest(urlBuilder.build());
        try {
            String response = request.send();
            JsonVk jsonVk = new Gson().fromJson(response, JsonVk.class);
            return jsonVk.getResponse()[0].getId();
        } catch (OauthApiException e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }
}
