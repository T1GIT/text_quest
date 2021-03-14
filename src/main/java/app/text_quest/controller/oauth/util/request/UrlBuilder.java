package app.text_quest.controller.oauth.util.request;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;


public class UrlBuilder {

    private final static String DEFAULT_PROTOCOL = "https";
    private final String protocol;
    private final String domain;
    private final HashMap<String, String> params;

    public UrlBuilder(String protocol, String domain) {
        this.params = new HashMap<>();
        this.protocol = protocol;
        this.domain = domain;
    }

    public UrlBuilder(String domain) {
        this(DEFAULT_PROTOCOL, domain);
    }

    public UrlBuilder addParam(String name, String value) {
        this.params.put(name, value);
        return this;
    }

    public String build() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("%s://%s", protocol, domain));
        if (params.size() > 0) {
            res.append("?");
            params.forEach((name, value) -> {
                try {
                    res.append(String.format("%s=%s&",
                            URLEncoder.encode(name, "UTF-8"),
                            URLEncoder.encode(value, "UTF-8")));
                } catch (UnsupportedEncodingException ignored) {
                }
            });
        }
        return res.toString();
    }
}
