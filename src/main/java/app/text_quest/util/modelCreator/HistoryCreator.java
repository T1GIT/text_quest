package app.text_quest.util.modelCreator;

import app.text_quest.model.History;
import app.text_quest.util.modelCreator.Msg.MsgCreator;

public class HistoryCreator {
    public static History create() {
        History history = new History();
        history.setMsg(MsgCreator.create());
        history.setUser(UserCreator.create());
        return history;
    }
}
