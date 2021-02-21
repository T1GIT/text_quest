package app.text_quest.service;

import app.text_quest.model.msg.Msg;
import app.text_quest.model.msg.types.Text;
import app.text_quest.util.modelFactory.types.msg.MsgFactory;
import app.text_quest.util.modelFactory.types.msg.types.TextFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;


@SpringBootTest
@EnableAutoConfiguration
@Configuration
class MsgServiceTest {

    private final static MsgFactory msgFactory = new MsgFactory();
    private final static TextFactory textFactory = new TextFactory();
    @Resource
    @Autowired
    private MsgService msgService;

    @Test
    void addMsg() {
        Msg msg = msgFactory.create();
        try {
            msgService.addMsg(msg);
        } finally {
            msgService.delete(msg);
        }
    }

    @Test
    void delete() {
        Msg msg = msgFactory.create();
        msgService.addMsg(msg);
        msgService.delete(msg);
    }

    @Test
    void editMsg() {
        Text msg = textFactory.create();
        try {
            msgService.addMsg(msg);
            msg.setVal("newText");
            msgService.editMsg(msg);
        } finally {
            msgService.delete(msg);
        }
    }

    @Test
    void getAll() {
        System.out.println(msgService.getAll());
    }
}