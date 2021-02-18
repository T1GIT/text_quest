package app.text_quest.service;

import app.text_quest.model.Limit;
import app.text_quest.util.modelFactory.types.LimitFactory;
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
class LimitServiceTest {
    @Resource
    @Autowired
    private LimitService limitService;
    private final static LimitFactory limitFactory = new LimitFactory();

    @Test
    void addLimit() {
        Limit limit = limitFactory.create();
        Limit findLimit = limitService.getByBranchAndVar(limit.getBranch(), limit.getVar());
        if (findLimit != null) {
            limitService.delete(findLimit);
        }
        limitService.addLimit(limit);
    }

    @Test
    @Transactional
    void delete() {
        Limit limit = limitFactory.create();
        if (limitService.getByBranchAndVar(limit.getBranch(), limit.getVar()) == null) {
            limitService.addLimit(limit);
        }
        limitService.delete(limit);
    }

    @Test
    @Transactional
    void getByBranch() {
        System.out.println(limitService.getByBranch(limitFactory.getBranch()));
    }

    @Test
    @Transactional
    void getByBranchAndVar() {
        System.out.println(limitService.getByBranchAndVar(limitFactory.getBranch(), limitFactory.getVar()));
    }

    @Transactional
    @Test
    void editLimit() {
        Limit limit = limitFactory.create();
        limitService.addLimit(limit);
        limit.setMin(-20);
        limitService.editLimit(limit);

    }

    @Transactional
    @Test
    void getAll() {
        System.out.println(limitService.getAll());
    }
}