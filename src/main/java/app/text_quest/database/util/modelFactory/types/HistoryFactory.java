package app.text_quest.database.util.modelFactory.types;

import app.text_quest.database.model.History;
import app.text_quest.database.model.User;
import app.text_quest.database.model.msg.Msg;
import app.text_quest.database.util.modelFactory.AbstractModelFactory;
import app.text_quest.database.util.modelFactory.types.msg.MsgFactory;


public class HistoryFactory extends AbstractModelFactory<History> {

    private final static MsgFactory msgFactory = new MsgFactory();
    private final static UserFactory userFactory = new UserFactory();
    private final Msg msg = msgFactory.create();
    private final User user = userFactory.create();


    public History create() {
        History history = new History();
        history.setMsg(msgFactory.create());
        history.setUser(userFactory.create());
        return history;
    }

    public Msg getMsg() {
        return msg;
    }

    public User getUser() {
        return user;
    }
}
