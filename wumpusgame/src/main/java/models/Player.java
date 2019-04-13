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

    private int points = 0;
    private int arrowsNumber = 5;

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

    public void turnRight() {
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

    public Point getNextPoint(Point currentCell) {
        int x = currentCell.getX();
        int y = currentCell.getY();
        switch (direction) {
            case FORWARD :
                x += -1;
                break;
            case BACKWARD :
                x += 1;
                break;
            case LEFT :
                y += -1;
                break;
            case RIGHT :
                y += 1;
                break;
        }

        return new Point(x,y);
    }

    public void restartSkills() {
        this.points = 0;
        this.arrowsNumber = 5;
    }

    public void shootArrow() {
        this.arrowsNumber--;
    }

    public void levelUp() {
        this.arrowsNumber += 3;
        this.points += 1000;
    }

    public void grabTreasure(int level) {
        this.points += 250 * level;
    }
}
