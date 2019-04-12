package models;

import enums.Direction;
import lombok.Data;

import java.io.BufferedReader;

import static enums.Direction.*;

@Data
public class Player extends GameObject {

    static final String FORWARD_MODEL = "models/player_F" ;
    static final String BACKWARD_MODEL = "models/player_D" ;
    static final String LEFT_MODEL = "models/player_L" ;
    static final String RIGHT_MODEL = "models/player_R" ;

    private Direction direction;

    public Player() {
        direction = Direction.FORWARD;
    }

    @Override
    public BufferedReader draw() {
        setUpModelPath();
        return super.draw();
    }

    private void setUpModelPath() {
        switch (direction) {
            case FORWARD :
                this.MODEL_PATH = FORWARD_MODEL;
                break;
            case BACKWARD :
                this.MODEL_PATH = BACKWARD_MODEL;
                break;
            case LEFT :
                this.MODEL_PATH = LEFT_MODEL;
                break;
            case RIGHT :
                this.MODEL_PATH = RIGHT_MODEL;
                break;
        }
    }

    public void turnRigh() {
        switch (direction) {
            case FORWARD :
                this.direction = RIGHT;
                break;
            case BACKWARD :
                this.direction = LEFT;
                break;
            case LEFT :
                this.direction = FORWARD;
                break;
            case RIGHT :
                this.direction = BACKWARD;
                break;
        }
    }

    public void turnLeft() {
        switch (direction) {
            case FORWARD :
                this.direction = LEFT;
                break;
            case BACKWARD :
                this.direction = RIGHT;
                break;
            case LEFT :
                this.direction = BACKWARD;
                break;
            case RIGHT :
                this.direction = FORWARD;
                break;
        }
    }
}
