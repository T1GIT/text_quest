package app.text_quest.controller.util.oauth.util;

import app.text_quest.controller.util.oauth.enums.OauthPropName;
import app.text_quest.controller.util.oauth.enums.OauthProvider;

import java.util.Properties;


public class OauthProps {

    private final String fileName = "oauth.properties";
    private final OauthProvider provider;
    private final Properties properties;

    public OauthProps(Properties properties, OauthProvider provider) {
        this.properties = properties;
        this.provider = provider;
    }

    public String get(OauthPropName name) {
        return this.properties.getProperty(provider.name().toLowerCase() + "." + name.name().toLowerCase());
    }
}
