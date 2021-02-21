package app.text_quest.service;

import app.text_quest.model.State;
import app.text_quest.util.modelFactory.types.StateFactory;
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
class StateServiceTest {

    private final static StateFactory stateFactory = new StateFactory();
    @Resource
    @Autowired
    private StateService stateService;

    @Transactional
    @Test
    void addState() {
        State state = stateFactory.create();
        try {
            State findState = stateService.getByUserAndVar(state.getUser(), state.getVar());
            if (findState != null) {
                stateService.delete(findState);
            }
            stateService.addState(state);
        } finally {
            stateService.delete(state);
        }
    }

    @Test
    void delete() {
        State state = stateFactory.create();
        if (stateService.getByUser(state.getUser()) == null) {
            stateService.addState(state);
        }
        stateService.delete(state);
    }

    @Test
    void getByUser() {
        System.out.println(stateService.getByUser(stateFactory.getUser()));
    }

    @Test
    void getByUserAndVar() {
        System.out.println(stateService.getByUserAndVar(stateFactory.getUser(), stateFactory.getVar()));
    }

    @Test
    void editState() {
        State state = stateFactory.create();
        try {
            State findState = stateService.getByUserAndVar(state.getUser(), state.getVar());
            if (findState != null) {
                stateService.delete(findState);
            }
            stateService.addState(state);
            state = stateService.getByUserAndVar(state.getUser(), state.getVar());
            state.setVal(1001);
            stateService.editState(state);
        } finally {
            stateService.delete(state);
        }
    }

    @Transactional
    @Test
    void getAll() {
        System.out.println(stateService.getAll());
    }
}