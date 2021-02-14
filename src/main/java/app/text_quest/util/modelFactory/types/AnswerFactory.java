package app.text_quest.util.modelFactory.types;

import app.text_quest.model.Answer;
import app.text_quest.model.msg.types.Text;
import app.text_quest.model.node.types.LinkedNode.types.OutMsg;
import app.text_quest.util.modelFactory.AbstractModelFactory;
import app.text_quest.util.modelFactory.types.msg.types.TextFactory;
import app.text_quest.util.modelFactory.types.node.types.LinkedNode.types.OutMsgFactory;

public class AnswerFactory extends AbstractModelFactory<Answer> {
    private final static OutMsgFactory outMsgFactory = new OutMsgFactory();
    private final static TextFactory textFactory = new TextFactory();
    private final OutMsg outMsg = outMsgFactory.create();
    private final Text text = textFactory.create();

    public Answer create() {
        Answer answer = new Answer();
        answer.setOutMsg(outMsgFactory.create());
        answer.setText(textFactory.create());
        return answer;
    }

    public OutMsg getOutMsg() {
        return outMsg;
    }

    public Text getText() {
        return text;
    }
}
