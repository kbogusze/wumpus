package models;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class WumpusTest {
    @Test
    void checkModelInResources() {
        File f = new File(getClass().getClassLoader().getResource(Wumpus.STATIC_MODEL_PATH).getFile());
        assertTrue(f.exists());
    }

}