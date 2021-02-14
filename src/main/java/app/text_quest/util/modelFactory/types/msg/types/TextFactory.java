package app.text_quest.util.modelFactory.types.msg.types;

import app.text_quest.model.msg.types.Text;
import app.text_quest.util.modelFactory.AbstractModelFactory;

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
