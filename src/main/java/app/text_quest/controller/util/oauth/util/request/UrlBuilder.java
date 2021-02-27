package app.text_quest.controller.util.oauth.util.request;

import app.text_quest.controller.util.oauth.enums.ReqParam;
import app.text_quest.util.LoggerFactory;
import app.text_quest.util.enums.LogType;
import org.apache.log4j.Logger;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class UrlBuilder {

    private final static Logger logger = LoggerFactory.getLogger(LogType.ERROR);
    private final static String defautlProtocol = "https";
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

    private static String getParsedParams(HashMap<String, String> params) {
        if (params.size() == 0) return "";
        List<String> paramsList = new ArrayList<>(params.size());
        params.forEach((name, value) -> {
            try {
                paramsList.add(String.format("%s=%s&",
                        URLEncoder.encode(name, "UTF-8"),
                        URLEncoder.encode(value, "UTF-8")));
            } catch (UnsupportedEncodingException e) {
                logger.error(e.getMessage(), e);
            }
        });
        return String.join("&", paramsList);
    }

    public UrlBuilder addParam(String param, String value) {
        this.params.put(param, value);
        return this;
    }

    public UrlBuilder addParam(ReqParam oauthParam, String value) {
        return addParam(oauthParam.name().toLowerCase(), value);
    }

    public String build() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("%s://%s", protocol, domain));
        if (params.size() > 0) {
            res.append("?").append(getParsedParams(params));
        }
        return res.toString();
    }
}
