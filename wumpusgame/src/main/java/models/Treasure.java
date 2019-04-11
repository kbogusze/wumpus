package models;

public class Treasure extends GameObject {
    static final String STATIC_MODEL_PATH = "models/treasure";

    public Treasure() {
        this.MODEL_PATH = STATIC_MODEL_PATH;
    }

    @Override
    protected String draw() {
        return super.draw();
    }

}
