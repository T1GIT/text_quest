package app.text_quest.controller.util.oauth.util;

import app.text_quest.TextQuestApplication;
import app.text_quest.controller.util.oauth.enums.OauthPropName;
import app.text_quest.controller.util.oauth.enums.OauthProvider;
import app.text_quest.controller.util.oauth.enums.OauthReqParam;
import app.text_quest.controller.util.oauth.util.http_request.types.GetOauthRequest;
import app.text_quest.util.LoggerFactory;
import app.text_quest.util.enums.LogType;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;


public abstract class OauthController {

    private static final OauthPropsFactory propsFactory = new OauthPropsFactory();
    protected final Logger logger = LoggerFactory.getLogger(LogType.ERROR);
    protected final OauthProps props;

    public OauthController(OauthProvider provider) {
        this.props = propsFactory.getFor(provider);
    }

    public static String getBtnUrl(OauthProvider provider) {
        OauthProps props = propsFactory.getFor(provider);
        GetOauthRequest request = new GetOauthRequest(props.get(OauthPropName.BTN_ROOT_URL));
        request
                .addParam(OauthReqParam.CLIENT_ID, props.get(OauthPropName.CLIENT_ID))
                .addParam(OauthReqParam.REDIRECT_URI, String.format("%s/oauth/%s/code",
                        TextQuestApplication.getRootUrl(),
                        provider.name().toLowerCase()))
                .addParam(OauthReqParam.RESPONSE_TYPE, "code");
//                .addParam(OauthReqParam.DISPLAY, "popup");
        return request.parseUrl();
    }

    // TODO: 25.02.2021 Random links for users
    public abstract String receiveCode(HttpServletRequest request);

    public abstract void receiveToken(HttpServletRequest request);

    public abstract void receiveId(HttpServletRequest request);

    // TODO: 26.02.2021 State param in oauth requests CSRF
}
