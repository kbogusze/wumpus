package models;

import lombok.Data;

import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

@Data
public class GameObject {
    protected String MODEL_PATH;

    public BufferedReader draw(){
        InputStream is = getClass().getClassLoader().getResourceAsStream(MODEL_PATH);
        if (is != null) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            return reader;
        }
        return null;
    }

}
