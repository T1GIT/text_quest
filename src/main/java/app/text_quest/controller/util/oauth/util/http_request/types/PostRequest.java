package app.text_quest.controller.util.oauth.util.http_request.types;

import app.text_quest.controller.util.oauth.enums.OauthReqParam;
import app.text_quest.controller.util.oauth.util.exception.OauthApiError;
import app.text_quest.controller.util.oauth.util.http_request.HttpRequest;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;


public class PostRequest extends HttpRequest {

    private byte[] data = new byte[0];

    public PostRequest(String url) {
        super(url);
    }

    public void setData(HashMap<OauthReqParam, String> data) {
        List<String> paramList = new LinkedList<>();
        data.forEach((k, v) -> paramList.add(k.name().toLowerCase() + "=" + v));
        this.data = String.join("&", paramList).getBytes(StandardCharsets.UTF_8);
    }

    @Override
    public String send() throws OauthApiError {
        try {
            URL url = new URL(this.url);
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
            return readInputStream(con.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
