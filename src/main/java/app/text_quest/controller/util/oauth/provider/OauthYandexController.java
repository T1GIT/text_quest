package app.text_quest.controller.util.oauth.provider;


import app.text_quest.controller.util.oauth.enums.OauthPropName;
import app.text_quest.controller.util.oauth.enums.OauthProvider;
import app.text_quest.controller.util.oauth.enums.OauthReqParam;
import app.text_quest.controller.util.oauth.util.OauthController;
import app.text_quest.controller.util.oauth.util.exception.OauthApiError;
import app.text_quest.controller.util.oauth.util.http_request.types.GetOauthRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


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
            System.out.println(code);
            GetOauthRequest oauthReq = new GetOauthRequest("oauth.vk.com/access_token"); // TODO: props: to .pr-s file
            oauthReq.addParam(OauthReqParam.CLIENT_ID, props.get(OauthPropName.CLIENT_ID));
            oauthReq.addParam(OauthReqParam.CLIENT_SECRET, props.get(OauthPropName.CLIENT_SECRET));
            oauthReq.addParam(OauthReqParam.CODE, code);
            oauthReq.addParam(OauthReqParam.REDIRECT_URI, "localhost:8080/oauth/vk/code");
            try {
                System.out.println(oauthReq.flush());
            } catch (OauthApiError oauthApiError) {
                logger.error(oauthApiError.getMessage(), oauthApiError);
                return "redirect:/";
            }
        }
        return "redirect:/";
    }

    @GetMapping("token")
    @Override
    public void receiveToken(HttpServletRequest request) {
        Map<String, String[]> params = request.getParameterMap();

    }

    @GetMapping("id")
    @Override
    public void receiveId(HttpServletRequest request) {

    }
}
