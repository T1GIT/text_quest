package app.text_quest.database.service;


import app.text_quest.database.model.msg.Msg;

import java.util.List;


public interface MsgService {
    Msg addMsg(Msg msg);
    void delete(Msg msg);
    Msg editMsg(Msg msg);
    List<Msg> getAll();
}
