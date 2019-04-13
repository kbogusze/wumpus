package Controllers;

import enums.GameState;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import models.*;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class GameController implements Initializable {

    private int level = 1;
    private Board board;
    private Drawer drawer;
    private Player player;
    private AudioPlayer audioPlayer;
    private GameState state;

    @FXML
    private TextArea textArea;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.drawer = new Drawer(textArea);
        this.player = new Player();
        this.audioPlayer = new AudioPlayer();
        this.state = GameState.STOP;
        drawBackground();
        audioPlayer.playIntro();
        createBoard();
    }

    @FXML
    void keyboardEvent(KeyEvent event) {
        audioPlayer.playPip();
        if (state.equals(GameState.PLAY)) {
            handleGameEvent(event);
        } else if (state.equals(GameState.GAME_OVER)) {
            handleRestartEvent();
        }  else if (state.equals(GameState.STOP)) {
            handleRefreshEvent();
        }
    }

    private void handleGameEvent(KeyEvent event) {
        if (event.getCode() == KeyCode.W) {
            moveForward();
        } else if (event.getCode() == KeyCode.A) {
            turnLeft();
        } else if (event.getCode() == KeyCode.D) {
            turnRight();
        } else if (event.getCode() == KeyCode.SPACE) {
            shootArrow();
        } else if (event.getCode() == KeyCode.I) {
            drawInfo();
        } else if (event.getCode() == KeyCode.R) {
            handleRestartEvent();
        }
    }

    private void handleRestartEvent() {
        level = 1;
        player.restartSkills();
        createBoard();
        drawGame();
        state = GameState.PLAY;
    }

    private void handleRefreshEvent() {
        drawGame();
        state = GameState.PLAY;
    }

    private void drawInfo() {
        drawer.drawInfo();
    }

    private void shootArrow() {
        player.shootArrow();
        if(board.onTarget(player))
            playShootOnTargetScenario();
        else
            playWallScenario();
    }

    private void playShootOnTargetScenario() {
        nextLevel();
        audioPlayer.playDeadWumpus();
        drawer.drawVictory();
        state = GameState.STOP;
    }

    private void nextLevel() {
        level++;
        player.levelUp();
        createBoard();
    }

    private void turnRight() {
        player.turnRight();
        drawGame();
    }

    private void turnLeft() {
        player.turnLeft();
        drawGame();
    }

    private void moveForward() {
        Point nextPoint = board.getNextPoint(player);
        if (board.outOfBoard(nextPoint.getX(), nextPoint.getY())) {
            playWallScenario();
        } else if (board.hasWumpus(nextPoint.getX(), nextPoint.getY())) {
            playWumpusScenario();
        } else if (board.hasPit(nextPoint.getX(), nextPoint.getY())) {
            playPitScenario();
        } else {
            playGoForwardScenario(nextPoint);
        }
    }

    private void playGoForwardScenario(Point nextPoint) {
        if (board.hasTreasure(nextPoint.getX(), nextPoint.getY())) {
            playTreasureFoundScenario();
        }
        board.movePlayer(player, nextPoint);
    }

    private void playTreasureFoundScenario() {
        player.grabTreasure(level);
        drawer.drawFile(Treasure.STATIC_MODEL_PATH);
        audioPlayer.playStart();
        state = GameState.STOP;
    }

    private void playPitScenario() {
        audioPlayer.playPit();
        drawer.drawFile(Pit.STATIC_MODEL_PATH);
        drawer.drawFinalScore(level, player.getPoints());
        state = GameState.GAME_OVER;
    }

    private void playWumpusScenario() {
        audioPlayer.playWumpus();
        drawer.drawFile(Wumpus.STATIC_MODEL_PATH);
        drawer.drawFinalScore(level, player.getPoints());
        state = GameState.GAME_OVER;
    }

    public void drawGame(){
        textArea.setText("");
        drawLevel();
        drawTips();
        drawPlayer();
        drawInstruction();
    }

    private void drawLevel() {
        drawer.drawLevel(level, player.getPoints(), player.getArrowsNumber());
    }

    private void drawTips() {
        List<GameObject> objects = board.getTipsInCurrentCell();
        objects.forEach(d -> drawer.drawFromStream(d.draw()));
    }

    private void drawPlayer() {
        drawer.drawFromStream(player.draw());
    }

    private void createBoard(){
        board = new Board(level);
        board.initializeObjects();
        board.setUpPlayer(player);
    }

    private void drawBackground() {
        drawer.drawHeader();
    }

    private void drawInstruction() {
        drawer.drawInstruction();
    }

    private void playWallScenario(){
        audioPlayer.playWall();
        drawer.drawWall();
        state = GameState.STOP;
    }

}
