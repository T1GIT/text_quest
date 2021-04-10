package app.database.util.modelFactory.types;

import app.database.model.Setting;
import app.database.util.enums.Color;
import app.database.util.enums.Font;
import app.database.util.enums.MsgType;
import app.database.util.modelFactory.AbstractModelFactory;


/**
 * @see app.database.util.modelFactory.AbstractModelFactory
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
