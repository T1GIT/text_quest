package app.text_quest.controller.oauth.util;

import app.text_quest.controller.util.oauth.enums.OauthPropName;
import app.text_quest.controller.util.oauth.enums.OauthProvider;
import app.text_quest.controller.util.oauth.util.OauthProps;
import org.junit.jupiter.api.Test;


class OauthPropsTestName {

    @Test
    void get() {
        for (OauthProvider provider : OauthProvider.values()) {
            OauthProps props = new OauthProps(provider);
            for (OauthPropName propName : OauthPropName.values()) {
                assert props.get(propName) != null;
            }
        }
    }
}