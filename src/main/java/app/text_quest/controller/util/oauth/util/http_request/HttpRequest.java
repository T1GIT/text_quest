package app.text_quest.controller.util.oauth.util.http_request;

import app.text_quest.controller.util.oauth.enums.ReqParam;
import app.text_quest.controller.util.oauth.util.exception.OauthApiError;
import app.text_quest.util.LoggerFactory;
import app.text_quest.util.enums.LogType;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;


public abstract class HttpRequest implements ReqParamInclude<HttpRequest> {

    protected final Logger logger = LoggerFactory.getLogger(LogType.ERROR);
    protected final HashMap<String, String> params;
    protected final UrlBuilder urlBuilder;
    protected final String domain;

    public HttpRequest(String domain) {
        this.domain = domain;
        this.urlBuilder = new UrlBuilder(domain);
        this.params = new HashMap<>();
    }

    @Override
    public HttpRequest addParam(ReqParam reqParam, String value) {
        return addParam(reqParam.name().toLowerCase(), value);
    }

    @Override
    public HttpRequest addParam(String param, String value) {
        params.put(param, value);
        return this;
    }

    public abstract String send() throws OauthApiError;

    protected static String readInputStream(InputStream inputStream) throws IOException {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new InputStreamReader(inputStream))) {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                builder.append(inputLine);
            }
        }
        return builder.toString();
    }
}
