package app.text_quest.controller.util.oauth.provider;


import app.text_quest.TextQuestApplication;
import app.text_quest.controller.util.oauth.enums.PropName;
import app.text_quest.controller.util.oauth.enums.Provider;
import app.text_quest.controller.util.oauth.enums.ReqParam;
import app.text_quest.controller.util.oauth.util.OauthController;
import app.text_quest.controller.util.oauth.util.exception.OauthApiException;
import app.text_quest.controller.util.oauth.util.json.JsonToken;
import app.text_quest.controller.util.oauth.util.json.vk.VkInfo;
import app.text_quest.controller.util.oauth.util.request.UrlBuilder;
import app.text_quest.controller.util.oauth.util.request.types.GetRequest;
import com.google.gson.Gson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;


@Controller
public class OauthVkController extends OauthController {

    public OauthVkController() {
        super(Provider.VK);
    }

    @GetMapping("oauth/vk")
    @Override
    public String receiveCode(HttpServletRequest request) {
        if (request.getParameter(ReqParam.ERROR.name().toLowerCase()) == null) {
            String code = request.getParameter(ReqParam.CODE.name().toLowerCase());
            String token = receiveToken(code);
            System.out.println(receiveId(token));
        }
        return "redirect:/start";
    }

    @Override
    public String receiveToken(String code) {
        UrlBuilder urlBuilder = new UrlBuilder(props.get(PropName.DOMAIN_TOKEN))
                .addParam(ReqParam.CLIENT_ID, props.get(PropName.CLIENT_ID))
                .addParam(ReqParam.CLIENT_SECRET, props.get(PropName.CLIENT_SECRET))
                .addParam(ReqParam.CODE, code)
                .addParam(ReqParam.REDIRECT_URI, String.format("%s/oauth/%s",
                        TextQuestApplication.getRootUrl(), Provider.VK.name().toLowerCase()));
        GetRequest request = new GetRequest(urlBuilder.build());
        try {
            String response = request.send();
            JsonToken jsonJsonToken = new Gson().fromJson(response, JsonToken.class);
            return jsonJsonToken.getAccessToken();
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
            VkInfo vkInfo = new Gson().fromJson(response, VkInfo.class);
            return vkInfo.getResponse()[0].getId();
        } catch (OauthApiException oauthApiException) {
            logger.error(oauthApiException.getMessage(), oauthApiException);
            return null;
        }
    }
}
