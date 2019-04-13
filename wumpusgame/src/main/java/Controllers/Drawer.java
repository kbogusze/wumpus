package Controllers;

import javafx.scene.control.TextArea;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Drawer {
    static final String STATIC_HEADER_PATH = "background/header";
    static final String STATIC_CONTROL_PATH = "background/control";
    static final String STATIC_INFO_PATH = "background/info";

    private TextArea textArea;

    public Drawer(TextArea textArea) {
        this.textArea = textArea;
    }

    public void drawFile(String path){
        InputStream is = getClass().getClassLoader().getResourceAsStream(path);
        if (is != null) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            drawFromStream(reader);
        }
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

    public void drawLevel(int level, int points, int arrowsNumber) {
        textArea.appendText("LEVEL: " + level + "  POINTS: " + points + " ARROWS: " + arrowsNumber + "\n");
    }
}
