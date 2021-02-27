package app.text_quest.controller.util.oauth.util.json;

import com.google.gson.annotations.SerializedName;


public class JsonToken {

    @SerializedName("access_token")
    private String accessToken;

    public String getAccessToken() {
        return accessToken;
    }
}
