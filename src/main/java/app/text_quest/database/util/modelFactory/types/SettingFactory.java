package app.text_quest.database.util.modelFactory.types;

import app.text_quest.database.model.Setting;
import app.text_quest.database.util.enums.Color;
import app.text_quest.database.util.enums.Font;
import app.text_quest.database.util.enums.MsgType;
import app.text_quest.database.util.modelFactory.AbstractModelFactory;


/**
 * @see app.text_quest.database.util.modelFactory.AbstractModelFactory
 */
public class SettingFactory extends AbstractModelFactory<Setting> {
    private final Color color = Color.BLUE;
    private final Font font = Font.ARIAL;
    private final MsgType msgType = MsgType.ROUNDED;
    private final int size = 20;


    public Setting create() {
        Setting setting = new Setting();
        setting.setColor(this.color);
        setting.setFont(this.font);
        setting.setMsgType(this.msgType);
        setting.setSize(this.size);
        return setting;
    }


    public Color getColor() {
        return color;
    }

    public Font getFont() {
        return font;
    }

    public MsgType getMsgType() {
        return msgType;
    }

    public int getSize() {
        return size;
    }
}
