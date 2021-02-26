package app.text_quest.controller.util.oauth.util.http_request.types;

import app.text_quest.controller.util.oauth.util.exception.OauthApiError;
import app.text_quest.controller.util.oauth.util.http_request.HttpRequest;
import app.text_quest.controller.util.oauth.util.http_request.UrlBuilder;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;


public class PostRequest extends HttpRequest {

    private byte[] data = new byte[0];

    public PostRequest(String domain) {
        super(domain);
    }

    @Override
    public String send() throws OauthApiError {
        try {
            this.data = UrlBuilder.parseParams(params).getBytes();
            URL url = new URL(urlBuilder.build());
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setDoOutput(true);
            con.setInstanceFollowRedirects(false);
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            con.setRequestProperty("charset", "utf-8");
            con.setRequestProperty("Content-Length", Integer.toString(data.length));
            con.setUseCaches(false);
            try (DataOutputStream out = new DataOutputStream(con.getOutputStream())) {
                out.write(this.data);
                out.flush();
            }
            if (con.getResponseCode() == 200) {
                return readInputStream(con.getInputStream());
            } else {
                throw new OauthApiError(readInputStream(con.getErrorStream()), con.getResponseCode());
            }
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }
}
