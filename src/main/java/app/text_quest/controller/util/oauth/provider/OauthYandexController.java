package app.text_quest.controller.util.oauth.provider;


import app.text_quest.controller.util.oauth.enums.PropName;
import app.text_quest.controller.util.oauth.enums.Provider;
import app.text_quest.controller.util.oauth.enums.ReqParam;
import app.text_quest.controller.util.oauth.util.OauthController;
import app.text_quest.controller.util.oauth.util.exception.OauthApiException;
import app.text_quest.controller.util.oauth.util.http_request.UrlBuilder;
import app.text_quest.controller.util.oauth.util.http_request.types.GetRequest;
import app.text_quest.controller.util.oauth.util.http_request.types.PostRequest;
import app.text_quest.controller.util.oauth.util.json.JsonToken;
import app.text_quest.controller.util.oauth.util.json.yandex.YandexPassport;
import com.google.gson.Gson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;


@Controller
public class OauthYandexController extends OauthController {

    public OauthYandexController() {
        super(Provider.YANDEX);
    }

    @GetMapping("oauth/yandex")
    @Override
    public String receiveCode(HttpServletRequest request) {
        if (request.getParameter(ReqParam.ERROR.name().toLowerCase()) == null) {
            String code = request.getParameter(ReqParam.CODE.name().toLowerCase());
            String token = receiveToken(code);
            System.out.println(receiveId(token));
        }
        return "redirect:/";
    }

    @Override
    public String receiveToken(String code) {
        UrlBuilder urlBuilder = new UrlBuilder(props.get(PropName.DOMAIN_TOKEN));
        PostRequest request = new PostRequest(urlBuilder.build());
        HashMap<ReqParam, String> paramMap = new HashMap<>();
        paramMap.put(ReqParam.CLIENT_ID, props.get(PropName.CLIENT_ID));
        paramMap.put(ReqParam.CLIENT_SECRET, props.get(PropName.CLIENT_SECRET));
        paramMap.put(ReqParam.CODE, code);
        paramMap.put(ReqParam.GRANT_TYPE, "authorization_code");
        request.setData(paramMap);
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
                .addParam(ReqParam.FORMAT, "json")
                .addParam(ReqParam.OAUTH_TOKEN, token);
        GetRequest request = new GetRequest(urlBuilder.build());
        try {
            String response = request.send();
            YandexPassport passport = new Gson().fromJson(response, YandexPassport.class);
            return passport.getId();
        } catch (OauthApiException e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }
}
