package app.text_quest.util.modelFactory.types.msg.types;

import app.text_quest.model.msg.types.Image;
import app.text_quest.util.modelFactory.AbstractModelFactory;

public class ImageFactory extends AbstractModelFactory<Image> {
    private final byte[] file = new byte[0];

    public Image create() {
        Image image = new Image();
        image.setFile(this.file);
        return image;
    }

    public byte[] getFile() {
        return file;
    }
}
