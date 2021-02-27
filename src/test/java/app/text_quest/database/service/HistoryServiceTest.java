package app.text_quest.database.service;

import app.text_quest.database.model.History;
import app.text_quest.database.util.modelFactory.types.HistoryFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


@Deprecated
@SpringBootTest
@EnableAutoConfiguration
@Configuration
class HistoryServiceTest {

    private final static HistoryFactory historyFactory = new HistoryFactory();
    @Resource
    @Autowired
    private HistoryService historyService;

    @Test
    void addHistory() {
        History history = historyFactory.create();
        try {
            historyService.addHistory(history);
        } finally {
            historyService.delete(history);
        }
    }

    @Test
    @Transactional
    void delete() {
        History history = historyFactory.create();
        historyService.addHistory(history);
        historyService.delete(history);
    }

    @Test
    @Transactional
    void getByBranchAndVar() {
        System.out.println(historyService.getByUser(historyFactory.getUser()));
    }

    @Transactional
    @Test
    void editHistory() {
        History history = historyFactory.create();
        try {
            historyService.addHistory(history);
            history.setMsg(historyFactory.getMsg());
            historyService.editHistory(history);
        } finally {
            historyService.delete(history);
        }

    }

    @Transactional
    @Test
    void getAll() {
        System.out.println(historyService.getAll());
    }
}