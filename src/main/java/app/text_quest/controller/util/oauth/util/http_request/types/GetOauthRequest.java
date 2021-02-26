package app.text_quest.controller.util.oauth.util.http_request.types;

import app.text_quest.controller.util.oauth.util.exception.OauthApiError;
import app.text_quest.controller.util.oauth.util.http_request.OauthHttpRequest;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;


public class GetOauthRequest extends OauthHttpRequest {

    public GetOauthRequest(String url) {
        super(url);
    }

    @Override
    public String flush() throws OauthApiError {
        try {
            HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            if (con.getResponseCode() != 200) {
                con.disconnect();
                throw new OauthApiError(readInputStream(con.getInputStream()));
            }
            con.disconnect();
            return readInputStream(con.getInputStream());
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }
}
