package app.text_quest.controller.util.oauth.provider;


import app.text_quest.TextQuestApplication;
import app.text_quest.controller.util.oauth.enums.PropName;
import app.text_quest.controller.util.oauth.enums.Provider;
import app.text_quest.controller.util.oauth.enums.ReqParam;
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
        super(Provider.VK);
    }

    @GetMapping("code")
    @Override
    public String receiveCode(HttpServletRequest request) {
        if (request.getParameter(ReqParam.ERROR.name().toLowerCase()) == null) {
            String code = request.getParameter(ReqParam.CODE.name().toLowerCase()); // TODO: 26.02.2021 Override name() .toLowerCase()
            UrlBuilder urlBuilder = new UrlBuilder("oauth.vk.com/access_token"); // TODO: props: to .pr-s file
            urlBuilder.addParam(ReqParam.CLIENT_ID, props.get(PropName.CLIENT_ID));
            urlBuilder.addParam(ReqParam.CLIENT_SECRET, props.get(PropName.CLIENT_SECRET));
            urlBuilder.addParam(ReqParam.TOKEN, code);
            urlBuilder.addParam(ReqParam.REDIRECT_URI, String.format("%s/auth/%s/code",
                    TextQuestApplication.getRootUrl(), Provider.VK.name().toLowerCase()));
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
