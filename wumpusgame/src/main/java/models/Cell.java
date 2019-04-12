package models;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Cell {
    private GameObject object;
    private List<GameObject> items;
    private boolean escapeCell = false;

    public Cell() {
        this.items = new ArrayList<>();
    }
}
