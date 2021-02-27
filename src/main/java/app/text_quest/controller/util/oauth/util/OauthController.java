package app.text_quest.controller.util.oauth.util;

import app.text_quest.controller.util.oauth.enums.Provider;
import app.text_quest.controller.util.oauth.enums.ReqParam;
import app.text_quest.controller.util.oauth.util.props.OauthProps;
import app.text_quest.controller.util.oauth.util.props.OauthPropsFactory;
import app.text_quest.util.LoggerFactory;
import app.text_quest.util.enums.LogType;
import org.apache.log4j.Logger;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;


public abstract class OauthController {

    private static final OauthPropsFactory propsFactory = new OauthPropsFactory();
    protected static final Logger logger = LoggerFactory.getLogger(LogType.ERROR);
    protected final OauthProps props;

    public OauthController(Provider provider) {
        this.props = propsFactory.getFor(provider);
    }

    // TODO: 25.02.2021 Random links for users
    public String receiveCode(Cookie cookieState, HttpServletRequest request) {
        if (request.getParameter(ReqParam.ERROR.name().toLowerCase()) == null) {
            String code = request.getParameter(ReqParam.CODE.name().toLowerCase());
            String state = request.getParameter(ReqParam.STATE.name().toLowerCase());
            if (state.equals(cookieState.getValue())) {
                String token = receiveToken(code);
                System.out.println(receiveId(token));
            }
        }
        return "redirect:/";
    }

    public abstract String receiveToken(String code);

    public abstract String receiveId(String token);

    // TODO: 26.02.2021 State param in oauth requests CSRF
}
