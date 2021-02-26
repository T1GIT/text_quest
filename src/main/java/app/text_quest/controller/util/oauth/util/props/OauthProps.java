package app.text_quest.controller.util.oauth.util.props;

import app.text_quest.controller.util.oauth.enums.PropName;
import app.text_quest.controller.util.oauth.enums.Provider;

import java.util.Properties;


public class OauthProps {

    private final String fileName = "oauth.properties";
    private final Provider provider;
    private final Properties properties;

    public OauthProps(Properties properties, Provider provider) {
        this.properties = properties;
        this.provider = provider;
    }

    public String get(PropName name) {
        return this.properties.getProperty(provider.name().toLowerCase() + "." + name.name().toLowerCase());
    }
}
