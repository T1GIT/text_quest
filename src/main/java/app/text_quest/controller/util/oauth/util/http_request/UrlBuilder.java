package app.text_quest.controller.util.oauth.util.http_request;

import app.text_quest.controller.util.oauth.enums.ReqParam;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class UrlBuilder implements ReqParamInclude<UrlBuilder> {

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

    public static String parseParams(HashMap<String, String> params) {
        if (params.size() == 0) return "";
        List<String> paramsList = new ArrayList<>(params.size());
        params.forEach((name, value) -> {
            try {
                paramsList.add(String.format("%s=%s&",
                        URLEncoder.encode(name, "UTF-8"),
                        URLEncoder.encode(value, "UTF-8")));
            } catch (UnsupportedEncodingException ignored) {
            }
        });
        return String.join("&", paramsList);
    }

    @Override
    public UrlBuilder addParam(String param, String value) {
        this.params.put(param, value);
        return this;
    }

    @Override
    public UrlBuilder addParam(ReqParam oauthParam, String value) {
        return addParam(oauthParam.name().toLowerCase(), value);
    }

    public String build() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("%s://%s", protocol, domain));
        if (params.size() > 0) {
            res.append("?").append(parseParams(params));
        }
        return res.toString();
    }
}
