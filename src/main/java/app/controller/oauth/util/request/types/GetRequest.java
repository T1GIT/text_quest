package app.controller.oauth.util.request.types;

import app.controller.oauth.util.request.Request;
import org.springframework.http.HttpMethod;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * Creates GET requests.
 * <p>
 * Data transferred as url parameters.
 */
public class GetRequest extends Request {

    /**
     * Class constructor, specifying protocol and host
     *
     * @param protocol to set in the url
     * @param domain   to set in the url
     */
    public GetRequest(String protocol, String domain) {
        super(protocol, domain);
    }

    /**
     * Class constructor, specifying only host.
     *
     * @param domain host to set in the url
     */
    public GetRequest(String domain) {
        super(domain);
    }

    /**
     * Prepares connection.
     *
     * @return url connection
     * @throws IOException if if input output error while reading occurs
     */
    @Override
    protected HttpURLConnection parseConnection() throws IOException {
        params.forEach(urlBuilder::addParam);
        HttpURLConnection con = (HttpURLConnection) new URL(urlBuilder.build()).openConnection();
        con.setRequestMethod(HttpMethod.GET.name());
        headers.forEach(con::setRequestProperty);
        return con;
    }
}
