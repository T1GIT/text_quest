package app.text_quest.util.modelCreator.Msg.types;

import app.text_quest.model.Msg.types.Text;

public class TextCreator {
    public static Text create() {
        Text text = new Text();
        text.setText("");
        return text;
    }
}
