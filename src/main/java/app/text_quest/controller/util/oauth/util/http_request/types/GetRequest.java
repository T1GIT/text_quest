package app.text_quest.controller.util.oauth.util.http_request.types;

import app.text_quest.controller.util.oauth.util.exception.OauthApiError;
import app.text_quest.controller.util.oauth.util.http_request.HttpRequest;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;


public class GetRequest extends HttpRequest {

    public GetRequest(String url) {
        super(url);
    }

    @Override
    public String send() throws OauthApiError {
        try {
            HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
            con.setRequestMethod("GET");
            con.disconnect();
            if (con.getResponseCode() == 200) {
                return readInputStream(con.getInputStream());
            } else {
                throw new OauthApiError(con.getResponseMessage(), con.getResponseCode());
            }
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }
}
