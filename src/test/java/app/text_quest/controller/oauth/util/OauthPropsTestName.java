package app.text_quest.controller.oauth.util;

import app.text_quest.controller.oauth.util.enums.PropName;
import app.text_quest.controller.oauth.util.enums.Provider;
import app.text_quest.controller.oauth.util.props.OauthProps;
import app.text_quest.controller.oauth.util.props.OauthPropsFactory;
import org.junit.jupiter.api.Test;


class OauthPropsTestName {

    @Test
    void get() {
        OauthPropsFactory propsFactory = new OauthPropsFactory();
        for (Provider provider : Provider.values()) {
            OauthProps props = propsFactory.getFor(provider);
            for (PropName propName : PropName.values()) {
                assert props.get(propName) != null;
            }
        }
    }
}