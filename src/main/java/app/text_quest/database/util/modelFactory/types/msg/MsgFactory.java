package app.text_quest.database.util.modelFactory.types.msg;

import app.text_quest.database.model.msg.Msg;
import app.text_quest.database.util.modelFactory.AbstractModelFactory;

public class MsgFactory extends AbstractModelFactory<Msg> {
    public Msg create() {
        return new Msg();
    }
}
