package app.text_quest.controller.util.oauth.util.props;

import app.text_quest.controller.util.oauth.enums.Provider;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;


public class OauthPropsFactory {

    private final String fileName = "oauth.properties";
    private final Properties properties;

    public OauthPropsFactory() {
        this.properties = new Properties();
        try {
            String path = Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource(fileName)).getPath();
            properties.load(new FileInputStream(path));
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
    }

    public OauthProps getFor(Provider provider) {
        return new OauthProps(properties, provider);
    }
}
