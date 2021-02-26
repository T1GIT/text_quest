package app.text_quest.controller.util.oauth.provider;


import app.text_quest.TextQuestApplication;
import app.text_quest.controller.util.oauth.enums.OauthPropName;
import app.text_quest.controller.util.oauth.enums.OauthProvider;
import app.text_quest.controller.util.oauth.enums.OauthReqParam;
import app.text_quest.controller.util.oauth.util.OauthController;
import app.text_quest.controller.util.oauth.util.exception.OauthApiError;
import app.text_quest.controller.util.oauth.util.http_request.UrlBuilder;
import app.text_quest.controller.util.oauth.util.http_request.types.GetRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("oauth/vk")
public class OauthVkController extends OauthController {

    public OauthVkController() {
        super(OauthProvider.VK);
    }

    @GetMapping("code")
    @Override
    public String receiveCode(HttpServletRequest request) {
        if (request.getParameter(OauthReqParam.ERROR.name().toLowerCase()) == null) {
            String code = request.getParameter(OauthReqParam.CODE.name().toLowerCase()); // TODO: 26.02.2021 Override name() .toLowerCase()
            UrlBuilder urlBuilder = new UrlBuilder("oauth.vk.com/access_token"); // TODO: props: to .pr-s file
            urlBuilder.addParam(OauthReqParam.CLIENT_ID, props.get(OauthPropName.CLIENT_ID));
            urlBuilder.addParam(OauthReqParam.CLIENT_SECRET, props.get(OauthPropName.CLIENT_SECRET));
            urlBuilder.addParam(OauthReqParam.TOKEN, code);
            urlBuilder.addParam(OauthReqParam.REDIRECT_URI, String.format("%s/auth/%s/code",
                    TextQuestApplication.getRootUrl(), OauthProvider.VK.name().toLowerCase()));
            GetRequest oauthReq = new GetRequest(urlBuilder.build());
            try {
                System.out.println(oauthReq.send()); // TODO: 26.02.2021
            } catch (OauthApiError oauthApiError) {
                logger.error(oauthApiError.getMessage(), oauthApiError);
                return "redirect:/";
            }
        }
        return "redirect:/";
    }

    @Override
    public String receiveToken(String code) {
        return null;
    }

    @GetMapping("id")
    @Override
    public String receiveId(String token) {
        return null;
    }
}
