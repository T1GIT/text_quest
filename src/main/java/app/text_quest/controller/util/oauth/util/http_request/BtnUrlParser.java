package app.text_quest.controller.util.oauth.util.http_request;

import app.text_quest.TextQuestApplication;
import app.text_quest.controller.util.oauth.enums.PropName;
import app.text_quest.controller.util.oauth.enums.Provider;
import app.text_quest.controller.util.oauth.enums.ReqParam;
import app.text_quest.controller.util.oauth.util.props.OauthProps;
import app.text_quest.controller.util.oauth.util.props.OauthPropsFactory;


public abstract class BtnUrlParser { // TODO: 26.02.2021 May be transferred as a static method

    private static final OauthPropsFactory propsFactory = new OauthPropsFactory();

    public static String getUrl(Provider provider) {
        OauthProps props = propsFactory.getFor(provider);
        UrlBuilder urlBuilder = new UrlBuilder(props.get(PropName.DOMAIN_AUTH));
        urlBuilder.addParam(ReqParam.CLIENT_ID, props.get(PropName.CLIENT_ID));
        urlBuilder.addParam(ReqParam.REDIRECT_URI, String.format("%s/oauth/%s",
                TextQuestApplication.getRootUrl(), provider.name().toLowerCase()));
        urlBuilder.addParam(ReqParam.RESPONSE_TYPE, ReqParam.CODE.name().toLowerCase());
        urlBuilder.addParam(ReqParam.DISPLAY, "popup");
        return urlBuilder.build();
    }

}
