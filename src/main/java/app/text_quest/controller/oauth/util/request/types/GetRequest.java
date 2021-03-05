package app.text_quest.controller.oauth.util.request.types;

import app.text_quest.controller.oauth.util.request.Request;
import app.text_quest.controller.oauth.util.request.UrlBuilder;
import org.springframework.http.HttpMethod;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;


public class GetRequest extends Request {

    public GetRequest(UrlBuilder urlBuilder) {
        super(urlBuilder);
    }

    @Override
    protected HttpURLConnection parseConnection() throws IOException {
        params.forEach(urlBuilder::addParam);
        HttpURLConnection con = (HttpURLConnection) new URL(urlBuilder.build()).openConnection();
        con.setRequestMethod(HttpMethod.GET.name());
        headers.forEach(con::setRequestProperty);
        return con;
    }
}
