package app.text_quest.controller.oauth.util.request;

import app.text_quest.controller.oauth.util.exception.types.ApiException;
import app.text_quest.util.LoggerFactory;
import app.text_quest.util.constant.LogType;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.HashMap;


/**
 * Base class for an output request.
 * <p>
 * Uses {@code HttpUrlConnection} for sending request.
 */
public abstract class Request {

    /**
     * It will be protocol in the case if you won't specify protocol in the constructor
     */
    private final static String DEFAULT_PROTOCOL = "https";
    /**
     * Logger for recording exceptions
     */
    protected final Logger logger = LoggerFactory.getLogger(LogType.ERROR);
    /**
     * Contains raw headers map
     */
    protected final HashMap<String, String> headers;
    /**
     * Contains raw params map
     */
    protected final HashMap<String, String> params;
    /**
     * Parses url address
     */
    protected final UrlBuilder urlBuilder;

    /**
     * Class constructor, specifying protocol and host
     *
     * @param protocol to set in the url
     * @param domain   to set in the url
     */
    public Request(String protocol, String domain) {
        this.urlBuilder = new UrlBuilder(protocol, domain);
        this.headers = new HashMap<>();
        this.params = new HashMap<>();
    }

    /**
     * Class constructor, specifying only host.
     *
     * @param domain host to set in the url
     */
    public Request(String domain) {
        this(DEFAULT_PROTOCOL, domain);
    }

    /**
     * Reads all data from the input stream and returns as a string
     *
     * @param inputStream for reading data
     * @return read data
     * @throws IOException if input output error while reading occurs
     */
    protected static String readInputStream(InputStream inputStream) throws IOException {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new InputStreamReader(inputStream))) {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                builder.append(inputLine);
            }
        }
        return builder.toString();
    }

    /**
     * Adds header to the request.
     *
     * @param name  of the header
     * @param value of the header
     * @return request
     */
    public Request addHeader(String name, String value) {
        headers.put(name, value);
        return this;
    }

    /**
     * Adds parameter to the request.
     *
     * @param name  of the parameter
     * @param value of the parameter
     * @return request
     */
    public Request addParam(String name, String value) {
        params.put(name, value);
        return this;
    }

    /**
     * Executes the request.
     * <p>
     * Parses url, body. Sends the request. Returns answer.
     *
     * @return answer to the request
     * @throws ApiException if request wasn't success
     */
    public final String send() throws ApiException {
        try {
            HttpURLConnection con = parseConnection();
            int code = con.getResponseCode();
            String message = con.getResponseMessage();
            String response = readInputStream(con.getInputStream());
            con.disconnect();
            if (con.getResponseCode() != 200)
                throw new ApiException(message, code);
            return response;
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    /**
     * Must be overridden. Prepares connection.
     *
     * @return url connection
     * @throws IOException if if input output error while reading occurs
     */
    protected abstract HttpURLConnection parseConnection() throws IOException;
}
