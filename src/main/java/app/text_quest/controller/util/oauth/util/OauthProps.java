package app.text_quest.controller.util.oauth.util;

import app.text_quest.controller.util.oauth.enums.OauthPropName;
import app.text_quest.controller.util.oauth.enums.OauthProvider;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;


public class OauthProps {

    private final String fileName = "oauth.properties";
    private final OauthProvider provider;
    private final Properties properties;

    public OauthProps(OauthProvider provider) {
        this.properties = new Properties();
        this.provider = provider;
        try {
            String path = Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource(fileName)).getPath();
            properties.load(new FileInputStream(path));
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
    }

    public String get(OauthPropName name) {
        return this.properties.getProperty(provider.getName() + "." + name.getName());
    }
}
