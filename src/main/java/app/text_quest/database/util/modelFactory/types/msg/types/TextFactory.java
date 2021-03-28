package app.text_quest.database.util.modelFactory.types.msg.types;

import app.text_quest.database.model.msg.types.Text;
import app.text_quest.database.util.modelFactory.AbstractModelFactory;


/**
 @see app.text_quest.database.util.modelFactory.AbstractModelFactory */
public class TextFactory extends AbstractModelFactory<Text> {
    private final String val = "textVal";

    public Text create() {
        Text text = new Text();
        text.setVal(this.val);
        return text;
    }

    public String getVal() {
        return val;
    }
}
