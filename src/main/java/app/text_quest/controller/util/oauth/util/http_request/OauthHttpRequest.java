package app.text_quest.controller.util.oauth.util.http_request;

import app.text_quest.controller.util.oauth.util.exception.OauthApiError;
import app.text_quest.util.LoggerFactory;
import app.text_quest.util.enums.LogType;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public abstract class OauthHttpRequest {

    protected final Logger logger = LoggerFactory.getLogger(LogType.ERROR);
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

    public abstract String flush() throws OauthApiError;
}
