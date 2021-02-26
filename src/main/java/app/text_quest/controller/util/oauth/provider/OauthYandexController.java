package app.text_quest.controller.util.oauth.provider;


import app.text_quest.controller.util.oauth.enums.OauthPropName;
import app.text_quest.controller.util.oauth.enums.OauthProvider;
import app.text_quest.controller.util.oauth.enums.OauthReqParam;
import app.text_quest.controller.util.oauth.util.OauthController;
import app.text_quest.controller.util.oauth.util.exception.OauthApiError;
import app.text_quest.controller.util.oauth.util.http_request.UrlBuilder;
import app.text_quest.controller.util.oauth.util.http_request.types.GetRequest;
import app.text_quest.controller.util.oauth.util.http_request.types.PostRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;


@Controller
@RequestMapping("oauth/yandex")
public class OauthYandexController extends OauthController {

    public OauthYandexController() {
        super(OauthProvider.YANDEX);
    }

    @GetMapping("code")
    @Override
    public String receiveCode(HttpServletRequest request) {
        if (request.getParameter(OauthReqParam.ERROR.name().toLowerCase()) == null) {
            String code = request.getParameter(OauthReqParam.CODE.name().toLowerCase());
            String token = receiveToken(code);
            System.out.println(receiveId(token));
        }
        return "redirect:/";
    }

    @Override
    public String receiveToken(String code) {
        UrlBuilder urlBuilder = new UrlBuilder(props.get(OauthPropName.DOMAIN_TOKEN));
        PostRequest request = new PostRequest(urlBuilder.build());
        HashMap<OauthReqParam, String> paramMap = new HashMap<>();
        paramMap.put(OauthReqParam.CLIENT_ID, props.get(OauthPropName.CLIENT_ID));
        paramMap.put(OauthReqParam.CLIENT_SECRET, props.get(OauthPropName.CLIENT_SECRET));
        paramMap.put(OauthReqParam.CODE, code);
        paramMap.put(OauthReqParam.GRANT_TYPE, "authorization_code");
        request.setData(paramMap);
        try {
            String response = request.send();
            return getFromJson(OauthReqParam.ACCESS_TOKEN, response);
        } catch (OauthApiError oauthApiError) {
            logger.error(oauthApiError.getMessage(), oauthApiError);
            return null;
        }
    }

    @GetMapping("id")
    @Override
    public String receiveId(String token) {
        UrlBuilder urlBuilder = new UrlBuilder(props.get(OauthPropName.DOMAIN_ID))
                .addParam(OauthReqParam.FORMAT, "json")
                .addParam(OauthReqParam.OAUTH_TOKEN, token);
        GetRequest request = new GetRequest(urlBuilder.build());
        try {
            String response = request.send();
            return getFromJson(OauthReqParam.ID, response);
        } catch (OauthApiError oauthApiError) {
            oauthApiError.printStackTrace();
            return null;
        }
    }
}
