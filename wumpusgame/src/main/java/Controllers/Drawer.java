package Controllers;

import javafx.scene.control.TextArea;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Drawer {
    public static final String STATIC_HEADER_PATH = "background/header";
    public static final String STATIC_CONTROL_PATH = "background/control";
    public static final String STATIC_INFO_PATH = "background/info";
    public static final String STATIC_WALL_PATH = "models/wall";
    public static final String STATIC_VICTORY_PATH = "models/victory";
    public static final String STATIC_NEXT_LEVEL_PATH = "models/next_level";

    private TextArea textArea;

    public Drawer(TextArea textArea) {
        this.textArea = textArea;
    }

    public void drawHeader() {
        drawFile(STATIC_HEADER_PATH);
    }

    public void drawInstruction() {
        drawFile(STATIC_CONTROL_PATH);
    }

    public void drawFromStream(BufferedReader reader){
        reader.lines().forEach(s -> textArea.appendText(s + "\n"));
    }

    public void drawInfo() {
        drawFile(STATIC_INFO_PATH);
    }

    public void drawWall() {
        drawFile(STATIC_WALL_PATH);
    }

    public void drawLevel(int level, int points, int arrowsNumber) {
        textArea.appendText("LEVEL: " + level + "  POINTS: " + points + " ARROWS: " + arrowsNumber + "\n");
        textArea.appendText("MAZE: " + (level+3) + "x" +  (level+3) + "\n");
    }

    public void drawFinalScore(int level, int points) {
        textArea.appendText("YOUR SCORE LEVEL: " + level + "  POINTS: " + points + "\n");
    }

    public void drawFile(String path){
        InputStream is = getClass().getClassLoader().getResourceAsStream(path);
        if (is != null) {
            drawFromStream(new BufferedReader(new InputStreamReader(is)));
        }
    }

    public void drawVictory() {
        drawFile(STATIC_VICTORY_PATH);
    }

    public void drawNextLevel() {
        drawFile(STATIC_NEXT_LEVEL_PATH);
    }
}
