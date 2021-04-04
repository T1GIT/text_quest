package app.controller.util.json.oauth;

import com.google.gson.annotations.SerializedName;


/**
 * A GSON template for extracting access token from the oauth server answer
 */
public class JsonToken {

    /**
     * Token received from the oauth server
     */
    @SerializedName("access_token")
    private String accessToken;

    public String getAccessToken() {
        return accessToken;
    }
}
