package app.text_quest.controller.util.json.oauth.vk;

import app.text_quest.controller.util.json.oauth.JsonId;


/**
 * A GSON template for extracting id from the oauth server answer
 */
public class JsonVk {

    /**
     * List of the templates for extracting id
     */
    private JsonId[] response;

    public JsonId[] getResponse() {
        return response;
    }
}
