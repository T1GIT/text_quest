package app.text_quest.controller.oauth.util.request.types;

import app.text_quest.controller.oauth.util.request.Request;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;


/**
 * Creates POST requests.
 * <p>
 * Data transferred in the x-www-form-urlencoded form.
 */
public class PostRequest extends Request {

    /**
     * Class constructor, specifying protocol and host
     *
     * @param protocol to set in the url
     * @param domain   to set in the url
     */
    public PostRequest(String protocol, String domain) {
        super(protocol, domain);
    }

    /**
     * Class constructor, specifying only host.
     *
     * @param domain host to set in the url
     */
    public PostRequest(String domain) {
        super(domain);
    }

    /**
     * Converts parameters map into the bytes array, form of the x-www-form-urlencoded
     *
     * @return parsed data
     */
    private byte[] parseData() {
        List<String> paramList = new ArrayList<>(params.size());
        params.forEach((k, v) -> paramList.add(k + "=" + v));
        return String.join("&", paramList).getBytes(StandardCharsets.UTF_8);
    }

    /**
     * Prepares connection.
     *
     * @return url connection
     * @throws IOException if if input output error while reading occurs
     */
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
