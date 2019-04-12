package Controllers;

import javafx.scene.media.AudioClip;

public class AudioPlayer {
    static final String STATIC_INTRO_PATH = "/audio/intro.mp3";

    public void playIntro(){
        AudioClip sound = new AudioClip(getClass().getResource(STATIC_INTRO_PATH).toString());
        sound.play();
    }
}
