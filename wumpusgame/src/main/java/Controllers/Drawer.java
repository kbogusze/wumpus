package Controllers;

import javafx.scene.control.TextArea;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Drawer {
    static final String STATIC_HEADER_PATH = "background/header";
    static final String STATIC_CONTROL_PATH = "background/control";
    static final String STATIC_INFO_PATH = "background/info";

    public void drawFile(String path ,TextArea textArea){
        InputStream is = getClass().getClassLoader().getResourceAsStream(path);
        if (is != null) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            drawFromStream(reader, textArea);
        }
    }

    public void drawHeader(TextArea textArea) {
        drawFile(STATIC_HEADER_PATH, textArea);
    }

    public void drawInstruction(TextArea textArea) {
        drawFile(STATIC_CONTROL_PATH, textArea);
    }

    public void drawFromStream(BufferedReader reader, TextArea textArea){
        reader.lines().forEach(s -> textArea.appendText(s + "\n"));
    }

    public void drawInfo(TextArea textArea) {
        drawFile(STATIC_INFO_PATH, textArea);
    }

}
