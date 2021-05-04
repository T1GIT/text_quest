package app.security.secretFactory.types;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RefreshFactoryTest {

    RefreshFactory refreshFactory = new RefreshFactory();

    @Test
    void create() {
        for (int i = 0; i <100; i++) {
            System.out.println(refreshFactory.create());
        }
    }
}