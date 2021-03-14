package app.text_quest.controller.oauth.util.request.types;

import app.text_quest.controller.oauth.util.request.Request;
import app.text_quest.controller.oauth.util.request.UrlBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;


public class PostRequest extends Request {

    public PostRequest(UrlBuilder urlBuilder) {
        super(urlBuilder);
    }

    private byte[] parseData() {
        List<String> paramList = new ArrayList<>(params.size());
        params.forEach((k, v) -> paramList.add(k + "=" + v));
        return String.join("&", paramList).getBytes(StandardCharsets.UTF_8);
    }

    @Override
    protected HttpURLConnection parseConnection() throws IOException {
        HttpURLConnection con = (HttpURLConnection) new URL(this.urlBuilder.build()).openConnection();
        con.setRequestMethod(HttpMethod.POST.name());
        headers.forEach(con::setRequestProperty);
        byte[] data = parseData();
        con.setDoOutput(true);
        con.setUseCaches(false);
        con.setInstanceFollowRedirects(false);
        con.setRequestProperty(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded");
        con.setRequestProperty(HttpHeaders.CONTENT_LENGTH, Integer.toString(data.length));
        try (DataOutputStream out = new DataOutputStream(con.getOutputStream())) {
            out.write(data);
            out.flush();
        }
        return con;
    }
}
