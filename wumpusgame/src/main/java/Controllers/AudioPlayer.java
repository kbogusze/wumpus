package Controllers;

import javafx.scene.media.AudioClip;

public class AudioPlayer {
    static final String STATIC_INTRO_PATH = "/audio/intro.mp3";
    static final String STATIC_START_PATH = "/audio/start.mp3";
    static final String STATIC_PIP_PATH = "/audio/pip.mp3";
    static final String STATIC_PIT_PATH = "/audio/pit.mp3";
    static final String STATIC_WUMPUS_PATH = "/audio/wumpus.mp3";
    static final String STATIC_WUMPUS_DEAD_PATH = "/audio/wumpus_dead.mp3";


    public void playIntro(){
        playAudio(STATIC_INTRO_PATH);
    }

    public void playStart(){
        playAudio(STATIC_START_PATH);
    }

    public void playPip(){
        playAudio(STATIC_PIP_PATH);
    }

    public void playPit(){
        playAudio(STATIC_PIT_PATH);
    }

    public void playWumpus(){
        playAudio(STATIC_WUMPUS_PATH);
    }

    public void playDeadWumpus(){
        playAudio(STATIC_WUMPUS_DEAD_PATH);
    }

    private void playAudio(String path){
        new AudioClip(getClass().getResource(path).toString()).play();
    }
}
