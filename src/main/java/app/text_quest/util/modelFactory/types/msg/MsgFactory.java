package app.text_quest.util.modelFactory.types.msg;

import app.text_quest.model.msg.Msg;
import app.text_quest.util.modelFactory.AbstractModelFactory;

public class MsgFactory extends AbstractModelFactory<Msg> {
    public Msg create() {
        return new Msg();
    }
}
