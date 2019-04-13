package models;

import lombok.Data;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

@Data
public class GameObject {
    protected String MODEL_PATH;

    public BufferedReader draw(){
        InputStream is = getClass().getClassLoader().getResourceAsStream(MODEL_PATH);
        if (is != null) {
            return new BufferedReader(new InputStreamReader(is));
        }
        return null;
    }

}
