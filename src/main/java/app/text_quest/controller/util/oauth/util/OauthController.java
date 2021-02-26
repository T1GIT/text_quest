package app.text_quest.controller.util.oauth.util;

import app.text_quest.controller.util.oauth.enums.Provider;
import app.text_quest.controller.util.oauth.enums.ReqParam;
import app.text_quest.controller.util.oauth.util.props.OauthProps;
import app.text_quest.controller.util.oauth.util.props.OauthPropsFactory;
import app.text_quest.util.LoggerFactory;
import app.text_quest.util.enums.LogType;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.servlet.http.HttpServletRequest;


public abstract class OauthController {

    private static final OauthPropsFactory propsFactory = new OauthPropsFactory();
    protected static final Logger logger = LoggerFactory.getLogger(LogType.ERROR);
    protected final OauthProps props;

    public OauthController(Provider provider) {
        this.props = propsFactory.getFor(provider);
    }

    // TODO: 25.02.2021 Random links for users
    public abstract String receiveCode(HttpServletRequest request);

    public abstract String receiveToken(String code);

    public abstract String receiveId(String token);

    protected String getFromJson(String key, String jsonString) {
        try {
            JSONObject obj = (JSONObject) new JSONParser().parse(jsonString);
            return (String) obj.get(key);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    protected String getFromJson(ReqParam reqParam, String jsonString) {
        return getFromJson(reqParam.name().toLowerCase(), jsonString);
    }

    // TODO: 26.02.2021 State param in oauth requests CSRF
}
