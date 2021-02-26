package app.text_quest.controller.util.oauth.provider;


import app.text_quest.controller.util.oauth.enums.PropName;
import app.text_quest.controller.util.oauth.enums.Provider;
import app.text_quest.controller.util.oauth.enums.ReqParam;
import app.text_quest.controller.util.oauth.util.OauthController;
import app.text_quest.controller.util.oauth.util.exception.OauthApiError;
import app.text_quest.controller.util.oauth.util.http_request.types.GetRequest;
import app.text_quest.controller.util.oauth.util.http_request.types.PostRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;


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
        PostRequest request = new PostRequest(props.get(PropName.DOMAIN_TOKEN));
        request
                .addParam(ReqParam.CLIENT_ID, props.get(PropName.CLIENT_ID))
                .addParam(ReqParam.CLIENT_SECRET, props.get(PropName.CLIENT_SECRET))
                .addParam(ReqParam.CODE, code)
                .addParam(ReqParam.GRANT_TYPE, "authorization_code");
        try {
            String response = request.send();
            return getFromJson(ReqParam.ACCESS_TOKEN, response);
        } catch (OauthApiError oauthApiError) {
            logger.error(oauthApiError.getMessage(), oauthApiError);
            return null;
        }
    }

    @Override
    public String receiveId(String token) {
        GetRequest request = new GetRequest(props.get(PropName.DOMAIN_ID));
        request
                .addParam(ReqParam.FORMAT, "json")
                .addParam(ReqParam.OAUTH_TOKEN, token);
        try {
            String response = request.send();
            return getFromJson(ReqParam.ID, response);
        } catch (OauthApiError oauthApiError) {
            oauthApiError.printStackTrace();
            return null;
        }
    }
}
