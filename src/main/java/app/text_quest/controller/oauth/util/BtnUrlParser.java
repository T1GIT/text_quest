package app.text_quest.controller.oauth.util;

import app.text_quest.TextQuestApplication;
import app.text_quest.controller.oauth.util.constant.PropName;
import app.text_quest.controller.oauth.util.constant.ReqParam;
import app.text_quest.controller.oauth.util.props.OauthProps;
import app.text_quest.controller.oauth.util.props.OauthPropsFactory;
import app.text_quest.controller.oauth.util.request.UrlBuilder;


public class BtnUrlParser { // TODO: 26.02.2021 May be transferred as a static method

    private static final OauthPropsFactory propsFactory = new OauthPropsFactory();

    public static String generateUrl(String state, String provider) {
        OauthProps props = propsFactory.getFor(provider);
        UrlBuilder urlBuilder = new UrlBuilder(props.get(PropName.DOMAIN_AUTH));
        urlBuilder
                .addParam(ReqParam.CLIENT_ID, props.get(PropName.CLIENT_ID))
                .addParam(ReqParam.REDIRECT_URI, String.format("%s/oauth/%s",
                        TextQuestApplication.getRootUrl(), provider))
                .addParam(ReqParam.RESPONSE_TYPE, ReqParam.CODE)
                .addParam(ReqParam.DISPLAY, "popup")
                .addParam(ReqParam.STATE, state)
                .addParam(ReqParam.SCOPE, props.get(PropName.SCOPE));
        return urlBuilder.build();
    }
}
