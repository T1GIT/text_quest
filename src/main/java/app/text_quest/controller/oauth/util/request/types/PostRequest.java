package app.text_quest.controller.oauth.util.request.types;

import app.text_quest.controller.oauth.util.enums.ReqParam;
import app.text_quest.controller.oauth.util.request.Request;
import app.text_quest.util.exceptions.OauthApiException;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class PostRequest extends Request {

    private byte[] data = new byte[0];

    public PostRequest(String url) {
        super(url);
    }

    public void setData(HashMap<ReqParam, String> data) {
        List<String> paramList = new ArrayList<>(data.size());
        data.forEach((k, v) -> paramList.add(k.name().toLowerCase() + "=" + v));
        this.data = String.join("&", paramList).getBytes(StandardCharsets.UTF_8);
    }

    @Override
    public String send() throws OauthApiException {
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
