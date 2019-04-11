package models;

import enums.Direction;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    @Test
    void checkLeftModelInResources() {
        File f = new File(getClass().getClassLoader().getResource(Player.LEFT_MODEL).getFile());
        assertTrue(f.exists());
    }

    @Test
    void checkLeftRightInResources() {
        File f = new File(getClass().getClassLoader().getResource(Player.RIGHT_MODEL).getFile());
        assertTrue(f.exists());
    }

    @Test
    void checkForwardModelInResources() {
        File f = new File(getClass().getClassLoader().getResource(Player.FORWARD_MODEL).getFile());
        assertTrue(f.exists());
    }

    @Test
    void checkBackwardModelInResources() {
        File f = new File(getClass().getClassLoader().getResource(Player.BACKWARD_MODEL).getFile());
        assertTrue(f.exists());
    }

    @Test
    void draw() {
        GameObject obj = new Player();
        ((Player) obj).setDirection(Direction.LEFT);
        obj.draw();
        assertTrue(obj.getMODEL_PATH().equals(Player.LEFT_MODEL));
    }
}