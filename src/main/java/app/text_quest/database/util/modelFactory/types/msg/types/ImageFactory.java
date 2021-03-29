package app.text_quest.database.util.modelFactory.types.msg.types;

import app.text_quest.database.model.msg.types.Image;
import app.text_quest.database.util.modelFactory.AbstractModelFactory;


/**
 * @see app.text_quest.database.util.modelFactory.AbstractModelFactory
 */
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
