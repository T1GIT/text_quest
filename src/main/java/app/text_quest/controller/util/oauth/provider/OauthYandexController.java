package app.text_quest.controller.util.oauth.provider;


import app.text_quest.controller.util.oauth.enums.OauthPropName;
import app.text_quest.controller.util.oauth.enums.OauthProvider;
import app.text_quest.controller.util.oauth.enums.OauthReqParam;
import app.text_quest.controller.util.oauth.exception.OauthApiError;
import app.text_quest.controller.util.oauth.util.OauthController;
import app.text_quest.controller.util.oauth.util.OauthProps;
import app.text_quest.controller.util.oauth.util.http_request.types.GetOauthRequest;
import app.text_quest.util.LoggerFactory;
import app.text_quest.util.enums.LogType;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


@Controller
@RequestMapping("oauth/yandex")
public class OauthYandexController implements OauthController {

    public static final String a = "iefj";
    private final Logger logger = LoggerFactory.getLogger(LogType.ERROR);
    private final OauthProps properties = new OauthProps(OauthProvider.VK);

    @GetMapping("code")
    @Override
    public String receiveCode(HttpServletRequest request) {
        if (request.getParameter(OauthReqParam.ERROR.getName()) == null) {
            String code = request.getParameter(OauthReqParam.TOKEN.getName());
            System.out.println(code);
            GetOauthRequest oauthReq = new GetOauthRequest("oauth.vk.com/access_token"); // TODO: props: to .pr-s file
            oauthReq.addParam(OauthReqParam.CLIENT_ID, properties.get(OauthPropName.CLIENT_ID));
            oauthReq.addParam(OauthReqParam.CLIENT_SECRET, properties.get(OauthPropName.CLIENT_SECRET));
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
