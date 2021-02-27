package app.text_quest.controller.util.oauth.util.request;

import app.text_quest.controller.util.oauth.util.exception.OauthApiException;
import app.text_quest.util.LoggerFactory;
import app.text_quest.util.enums.LogType;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public abstract class Request {

    protected final Logger logger = LoggerFactory.getLogger(LogType.ERROR);
    protected final String url;

    public Request(String url) {
        this.url = url;
    }

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

    public abstract String send() throws OauthApiException;
}
