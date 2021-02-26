package app.text_quest.controller.util.oauth.util.http_request;

import app.text_quest.controller.util.oauth.enums.OauthReqParam;
import app.text_quest.controller.util.oauth.util.exception.OauthApiError;
import app.text_quest.util.LoggerFactory;
import app.text_quest.util.enums.LogType;
import org.apache.log4j.Logger;

import java.io.*;
import java.net.URLEncoder;
import java.util.HashMap;


public abstract class OauthHttpRequest {

    protected final Logger logger = LoggerFactory.getLogger(LogType.ERROR);
    protected final String protocol = "https";
    protected final HashMap<OauthReqParam, String> params = new HashMap<>();
    protected final String url;

    public OauthHttpRequest(String url) {
        this.url = url;
    }

    protected static String readInputStream(InputStream inputStream) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
        String inputLine;
        StringBuilder builder = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            builder.append(inputLine);
        }
        in.close();
        return builder.toString();
    }

    public OauthHttpRequest addParam(OauthReqParam param, String value) {
        this.params.put(param, value);
        return this;
    }

    protected String parseUrl(boolean injectParams) {
        StringBuilder res = new StringBuilder(String.format("%s://%s", protocol, url));
        if (injectParams) {
            params.forEach((name, value) -> {
                try {
                    res.append(String.format("%s=%s&",
                            URLEncoder.encode(name.name().toLowerCase(), "UTF-8"),
                            URLEncoder.encode(value, "UTF-8"))
                    );
                } catch (UnsupportedEncodingException ignored) {
                }
            });
        }
        return res.toString();
    }

    public abstract String parseUrl();

    public abstract String flush() throws OauthApiError;
}
