package app.text_quest.util.modelCreator;

import app.text_quest.model.Answer;
import app.text_quest.model.Psw;
import app.text_quest.util.modelCreator.Msg.types.TextCreator;
import app.text_quest.util.modelCreator.Node.types.LinkedNode.types.OutMsgCreator;

public class AnswerCreator {
    public static Answer create() {
        Answer answer = new Answer();
        answer.setOutMsg(OutMsgCreator.create());
        answer.setText(TextCreator.create());
        return answer;
    }
}
