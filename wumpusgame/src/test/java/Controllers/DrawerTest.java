package Controllers;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class DrawerTest {
    @Test
    void checkControlGuideInResources() {
        File f = new File(getClass().getClassLoader().getResource(Drawer.STATIC_CONTROL_PATH).getFile());
        assertTrue(f.exists());
    }

    @Test
    void checkHeaderInResources() {
        File f = new File(getClass().getClassLoader().getResource(Drawer.STATIC_HEADER_PATH).getFile());
        assertTrue(f.exists());
    }

    @Test
    void checkInfoResources() {
        File f = new File(getClass().getClassLoader().getResource(Drawer.STATIC_INFO_PATH).getFile());
        assertTrue(f.exists());
    }

    @Test
    void checkVictoryModelInResources() {
        File f = new File(getClass().getClassLoader().getResource(Drawer.STATIC_VICTORY_PATH).getFile());
        assertTrue(f.exists());
    }

    @Test
    void checkWallModelInResources() {
        File f = new File(getClass().getClassLoader().getResource(Drawer.STATIC_WALL_PATH).getFile());
        assertTrue(f.exists());
    }

    @Test
    void checkNextLevelModelInResources() {
        File f = new File(getClass().getClassLoader().getResource(Drawer.STATIC_NEXT_LEVEL_PATH).getFile());
        assertTrue(f.exists());
    }


}