package app.text_quest.controller.util.oauth.util.http_request;

import app.text_quest.controller.util.oauth.enums.OauthReqParam;
import app.text_quest.controller.util.oauth.exception.OauthApiError;

import java.io.*;
import java.net.URLEncoder;
import java.util.HashMap;


public abstract class OauthHttpRequest {

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

    public String parseUrl(boolean injectParams) {
        StringBuilder res = new StringBuilder(protocol + "://" + url + "?");
        if (injectParams) {
            params.forEach((name, value) -> {
                try {
                    res.append(URLEncoder.encode(name.getName(), "UTF-8"));
                    res.append('=');
                    res.append(URLEncoder.encode(value, "UTF-8"));
                    res.append('&');
                } catch (UnsupportedEncodingException ignored) {
                }
            });
        }
        return res.toString();
    }

    public abstract String flush() throws OauthApiError;
}
