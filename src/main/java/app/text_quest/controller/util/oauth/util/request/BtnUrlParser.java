package app.text_quest.controller.util.oauth.util.request;

import app.text_quest.TextQuestApplication;
import app.text_quest.controller.util.oauth.enums.PropName;
import app.text_quest.controller.util.oauth.enums.Provider;
import app.text_quest.controller.util.oauth.enums.ReqParam;
import app.text_quest.controller.util.oauth.util.props.OauthProps;
import app.text_quest.controller.util.oauth.util.props.OauthPropsFactory;


public class BtnUrlParser { // TODO: 26.02.2021 May be transferred as a static method

    private static final OauthPropsFactory propsFactory = new OauthPropsFactory();
    private final String state;

    public BtnUrlParser(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public String generateUrl(Provider provider) {
        OauthProps props = propsFactory.getFor(provider);
        UrlBuilder urlBuilder = new UrlBuilder(props.get(PropName.DOMAIN_AUTH))
                .addParam(ReqParam.CLIENT_ID, props.get(PropName.CLIENT_ID))
                .addParam(ReqParam.REDIRECT_URI, String.format("%s/oauth/%s",
                        TextQuestApplication.getRootUrl(), provider.name().toLowerCase()))
                .addParam(ReqParam.RESPONSE_TYPE, ReqParam.CODE.name().toLowerCase())
                .addParam(ReqParam.DISPLAY, "popup")
                .addParam(ReqParam.STATE, state)
                .addParam(ReqParam.SCOPE, props.get(PropName.SCOPE));
        return urlBuilder.build();
    }
}
