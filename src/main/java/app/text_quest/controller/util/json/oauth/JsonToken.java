package app.text_quest.controller.util.json.oauth;

import com.google.gson.annotations.SerializedName;


public class JsonToken {

    @SerializedName("access_token")
    private String accessToken;

    public String getAccessToken() {
        return accessToken;
    }
}
