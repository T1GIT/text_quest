package app.text_quest.controller.oauth.util.props;

import java.util.Properties;


public class OauthProps {

    private final String fileName = "oauth.properties";
    private final String provider;
    private final Properties properties;

    public OauthProps(Properties properties, String provider) {
        this.properties = properties;
        this.provider = provider;
    }

    public String get(String name) {
        return this.properties.getProperty(provider + "." + name);
    }
}
