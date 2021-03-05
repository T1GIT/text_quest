package app.text_quest.controller.oauth.util.request;

import app.text_quest.controller.oauth.util.exception.types.ApiException;
import app.text_quest.util.LoggerFactory;
import app.text_quest.util.constant.LogType;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.HashMap;


public abstract class Request {

    protected final Logger logger = LoggerFactory.getLogger(LogType.ERROR);
    protected final HashMap<String, String> headers = new HashMap<>();
    protected final HashMap<String, String> params = new HashMap<>();
    protected final UrlBuilder urlBuilder;

    public Request(UrlBuilder urlBuilder) {
        this.urlBuilder = urlBuilder;
    }

    public Request addHeader(String name, String value) {
        headers.put(name, value);
        return this;
    }

    public Request addParam(String name, String value) {
        params.put(name, value);
        return this;
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

    public final String send() throws ApiException {
        try {
            HttpURLConnection con = parseConnection();
            int code = con.getResponseCode();
            String message = con.getResponseMessage();
            String response = readInputStream(con.getInputStream());
            con.disconnect();
            if (con.getResponseCode() != 200)
                throw new ApiException(message, code);
            return response;
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    protected abstract HttpURLConnection parseConnection() throws IOException;
}
