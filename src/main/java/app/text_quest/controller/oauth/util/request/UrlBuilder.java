package app.text_quest.controller.oauth.util.request;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.LinkedList;


/**
 * Constructs url.
 * Gets protocol and domain when initialising.
 * Later variables can be added to a urlBuilder.
 * Finally, you can get final url using {@link UrlBuilder#build()}.
 */
public class UrlBuilder {

    /**
     * It will be protocol in the case if you won't specify protocol in the constructor
     */
    private final static String DEFAULT_PROTOCOL = "https";

    /**
     * Contains protocol (http/https)
     */
    private final String protocol;

    /**
     * Host of the url
     */
    private final String domain;

    /**
     * Map containing parameters
     */
    private final HashMap<String, String> params;

    /**
     * Class constructor, specifying protocol and host
     *
     * @param protocol to set in the url
     * @param domain   to set in the url
     */
    public UrlBuilder(String protocol, String domain) {
        this.params = new HashMap<>();
        this.protocol = protocol;
        this.domain = domain;
    }

    /**
     * Class constructor, specifying only host.
     *
     * @param domain host to set in the url
     */
    public UrlBuilder(String domain) {
        this(DEFAULT_PROTOCOL, domain);
    }

    /**
     * Adds parameter to the url.
     *
     * @param name  of the parameter
     * @param value of the parameter
     * @return url builder
     */
    public UrlBuilder addParam(String name, String value) {
        this.params.put(name, value);
        return this;
    }

    /**
     * Constructs url from the protocol, host and parameters.
     *
     * @return url string
     */
    public String build() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("%s://%s", protocol, domain));
        if (params.size() > 0) {
            LinkedList<String> items = new LinkedList<>();
            params.forEach((name, value) -> {
                items.add(String.format("%s=%s",
                        URLEncoder.encode(name, StandardCharsets.UTF_8),
                        URLEncoder.encode(value, StandardCharsets.UTF_8)));
            });
            res.append("?").append(String.join("&", items));
        }
        return res.toString();
    }
}
