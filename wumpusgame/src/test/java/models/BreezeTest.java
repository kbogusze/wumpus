package models;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class BreezeTest {

    @Test
    void checkModelInResources() {
        File f = new File(getClass().getClassLoader().getResource(Breeze.STATIC_MODEL_PATH).getFile());
        assertTrue(f.exists());
    }

}