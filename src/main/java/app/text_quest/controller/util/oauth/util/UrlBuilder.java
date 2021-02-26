package app.text_quest.controller.util.oauth.util;

import app.text_quest.controller.util.oauth.enums.OauthReqParam;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;


public class UrlBuilder {

    private static final String defautlProtocol = "https";
    private final HashMap<String, String> params;
    private final String protocol;
    private final String domain;

    public UrlBuilder(String protocol, String domain) {
        this.params = new HashMap<>();
        this.protocol = protocol;
        this.domain = domain;
    }

    public UrlBuilder(String domain) {
        this(defautlProtocol, domain);
    }

    public UrlBuilder addParam(String param, String value) {
        this.params.put(param, value);
        return this;
    }

    public UrlBuilder addParam(OauthReqParam oauthParam, String value) {
        return addParam(oauthParam.name().toLowerCase(), value);
    }

    public String build() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("%s://%s?", protocol, domain));
        params.forEach((name, value) -> {
            try {
                res.append(String.format("%s=%s&",
                        URLEncoder.encode(name, "UTF-8"),
                        URLEncoder.encode(value, "UTF-8"))
                );
            } catch (UnsupportedEncodingException ignored) {
            }
        });
        return res.toString();
    }
}
