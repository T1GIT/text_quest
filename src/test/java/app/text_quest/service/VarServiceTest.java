package app.text_quest.service;

import app.text_quest.model.Limit;
import app.text_quest.model.Var;
import app.text_quest.util.modelFactory.types.VarFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


@SpringBootTest
@EnableAutoConfiguration
@Configuration
class VarServiceTest {

    @Resource
    @Autowired
    private VarService varService;
    private final static VarFactory varFactory = new VarFactory();

    @Test
    void addVar() {
        Var var = varFactory.create();
        Var findVar = varService.getByName(var.getName());
        if (findVar != null) {
            varService.delete(findVar);
        }
        varService.addVar(var);
    }

    @Test
    @Transactional
    void delete() {
        Var var = varFactory.create();
        if (varService.getByName(var.getName()) == null) {
            varService.addVar(var);
        }
        varService.delete(var);
    }

    @Test
    @Transactional
    void getByName() {
        System.out.println(varService.getByName(varFactory.getName()));
    }

    @Test
    void editVar() {
        Var var = varFactory.create();
        varService.addVar(var);
        var.setName("name");
        varService.editVar(var);

    }

    @Test
    void getAll() {
        System.out.println(varService.getAll());
    }
}