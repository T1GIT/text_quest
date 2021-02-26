package app.text_quest.controller.oauth.util;

import app.text_quest.controller.util.oauth.enums.OauthPropName;
import app.text_quest.controller.util.oauth.enums.OauthProvider;
import app.text_quest.controller.util.oauth.util.props.OauthProps;
import app.text_quest.controller.util.oauth.util.props.OauthPropsFactory;
import org.junit.jupiter.api.Test;


class OauthPropsTestName {

    @Test
    void get() {
        OauthPropsFactory propsFactory = new OauthPropsFactory();
        for (OauthProvider provider : OauthProvider.values()) {
            OauthProps props = propsFactory.getFor(provider);
            for (OauthPropName propName : OauthPropName.values()) {
                assert props.get(propName) != null;
            }
        }
    }
}