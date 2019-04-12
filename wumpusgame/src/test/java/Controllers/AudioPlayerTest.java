package Controllers;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class AudioPlayerTest {
    @Test
    void checkIntroAudioInResources() {
        File f = new File(getClass().getClassLoader().getResource(AudioPlayer.STATIC_INTRO_PATH).getFile());
        assertTrue(f.exists());
    }

    @Test
    void checkPipAudioInResources() {
        File f = new File(getClass().getClassLoader().getResource(AudioPlayer.STATIC_PIP_PATH).getFile());
        assertTrue(f.exists());
    }

    @Test
    void checkPitAudioInResources() {
        File f = new File(getClass().getClassLoader().getResource(AudioPlayer.STATIC_PIT_PATH).getFile());
        assertTrue(f.exists());
    }

    @Test
    void checkStartAudioInResources() {
        File f = new File(getClass().getClassLoader().getResource(AudioPlayer.STATIC_START_PATH).getFile());
        assertTrue(f.exists());
    }

    @Test
    void checkWumpusDeadAudioInResources() {
        File f = new File(getClass().getClassLoader().getResource(AudioPlayer.STATIC_WUMPUS_DEAD_PATH).getFile());
        assertTrue(f.exists());
    }

    @Test
    void checkWumpusAudioInResources() {
        File f = new File(getClass().getClassLoader().getResource(AudioPlayer.STATIC_WUMPUS_PATH).getFile());
        assertTrue(f.exists());
    }
}