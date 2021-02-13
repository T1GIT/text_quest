package app.text_quest.util.modelCreator.Msg.types;

import app.text_quest.model.Msg.types.Image;

public class ImageCreator {
    public static Image create() {
        Image image = new Image();
        image.setImage(new byte[0]);
        return image;
    }
}
