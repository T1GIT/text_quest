package app.text_quest.database.service.impl;

import app.text_quest.database.model.msg.Msg;
import app.text_quest.database.repo.MsgRepository;
import app.text_quest.database.service.MsgService;
import app.text_quest.database.util.AbstractServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class MsgServiceImpl
        extends AbstractServiceImpl<Msg, MsgRepository>
        implements MsgService {

    public MsgServiceImpl(MsgRepository repository) {
        super(repository);
    }
}
