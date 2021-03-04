package app.text_quest.controller.oauth.util.request.types;

import app.text_quest.controller.oauth.util.exceptions.types.ApiException;
import app.text_quest.controller.oauth.util.request.Request;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;


public class GetRequest extends Request {

    public GetRequest(String url) {
        super(url);
    }

    @Override
    public String send() throws ApiException {
        try {
            HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
            con.setRequestMethod("GET");
            con.disconnect();
            if (con.getResponseCode() == 200) {
                return readInputStream(con.getInputStream());
            } else {
                throw new ApiException(con.getResponseMessage(), con.getResponseCode());
            }
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }
}
