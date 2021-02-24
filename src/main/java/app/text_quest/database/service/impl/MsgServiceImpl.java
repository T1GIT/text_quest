package app.text_quest.database.service.impl;

import app.text_quest.database.model.msg.Msg;
import app.text_quest.database.repo.MsgRepository;
import app.text_quest.database.service.MsgService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class MsgServiceImpl implements MsgService {
    private final MsgRepository msgRepository;

    public MsgServiceImpl(MsgRepository msgRepository) {
        this.msgRepository = msgRepository;
    }

    @Override
    public Msg addMsg(Msg msg) {
        return msgRepository.saveAndFlush(msg);
    }

    @Override
    public void delete(Msg msg) {
        msgRepository.delete(msg);
    }

    @Override
    public Msg editMsg(Msg msg) {
        return msgRepository.saveAndFlush(msg);
    }

    @Override
    public List<Msg> getAll() {
        return msgRepository.findAll();
    }
}
