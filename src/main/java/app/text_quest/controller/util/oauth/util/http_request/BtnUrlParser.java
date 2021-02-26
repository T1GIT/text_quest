package app.text_quest.controller.util.oauth.util.http_request;

import app.text_quest.TextQuestApplication;
import app.text_quest.controller.util.oauth.enums.OauthPropName;
import app.text_quest.controller.util.oauth.enums.OauthProvider;
import app.text_quest.controller.util.oauth.enums.OauthReqParam;
import app.text_quest.controller.util.oauth.util.props.OauthProps;
import app.text_quest.controller.util.oauth.util.props.OauthPropsFactory;


public abstract class BtnUrlParser { // TODO: 26.02.2021 May be transferred as a static method

    private static final OauthPropsFactory propsFactory = new OauthPropsFactory();

    public static String getUrl(OauthProvider provider) {
        OauthProps props = propsFactory.getFor(provider);
        UrlBuilder urlBuilder = new UrlBuilder(props.get(OauthPropName.DOMAIN_AUTH));
        urlBuilder.addParam(OauthReqParam.CLIENT_ID, props.get(OauthPropName.CLIENT_ID));
        urlBuilder.addParam(OauthReqParam.REDIRECT_URI, String.format("%s/oauth/%s/code",
                TextQuestApplication.getRootUrl(), provider.name().toLowerCase()));
        urlBuilder.addParam(OauthReqParam.RESPONSE_TYPE, OauthReqParam.CODE.name().toLowerCase());
        urlBuilder.addParam(OauthReqParam.DISPLAY, "popup");
        return urlBuilder.build();
    }

}
