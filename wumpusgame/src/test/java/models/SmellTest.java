package models;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class SmellTest {

    @Test
    void checkModelInResources() {
        File f = new File(getClass().getClassLoader().getResource(Smell.STATIC_MODEL_PATH).getFile());
        assertTrue(f.exists());
    }

}