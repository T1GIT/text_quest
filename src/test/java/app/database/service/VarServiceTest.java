package app.database.service;

import app.database.model.Var;
import app.database.util.modelFactory.types.VarFactory;
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

    private final static VarFactory varFactory = new VarFactory();
    @Autowired
    private VarService varService;

    @Test
    void add() {
        Var var = varFactory.create();
        try {
            Var findVar = varService.getByName(var.getName());
            if (findVar != null) {
                varService.delete(findVar);
            }
            varService.add(var);
        } finally {
            varService.delete(var);
        }
    }

    @Test
    @Transactional
    void delete() {
        Var var = varFactory.create();
        if (varService.getByName(var.getName()) == null) {
            varService.add(var);
        }
        varService.delete(var);
    }

    @Test
    @Transactional
    void getByName() {
        System.out.println(varService.getByName(varFactory.getName()));
    }

    @Test
    void update() {
        Var var = varFactory.create();
        try {
            Var findVar = varService.getByName(var.getName());
            if (findVar != null) {
                varService.delete(findVar);
            }
            varService.add(var);
            var.setName("name");
            varService.update(var);
        } finally {
            varService.delete(var);
        }

    }

    @Test
    void getAll() {
        System.out.println(varService.getAll());
    }
}