package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import models.GameObject;
import models.Player;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class GameController implements Initializable {

    private int level = 1;
    private int points = 0;
    private int arrowsNumber = 0;
    private Board board;
    private Drawer drawer;
    private Player player;
    private AudioPlayer audioPlayer;

    @FXML
    private TextArea textArea;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.drawer = new Drawer(textArea);
        this.player = new Player();
        this.audioPlayer = new AudioPlayer();
        createBoard();
        audioPlayer.playIntro();
        drawBackground();
        drawGame();
    }

    @FXML
    void keyboardEvent(KeyEvent event) {
        audioPlayer.playPip();
        if (event.getCode() == KeyCode.W) {
            moveForward();
        } else if (event.getCode() == KeyCode.A) {
            turnLeft();
        } else if (event.getCode() == KeyCode.D) {
            turnRight();
        }else if (event.getCode() == KeyCode.SPACE) {
            shootArrow();
        } else if (event.getCode() == KeyCode.I) {
            drawInfo();
        }
    }

    private void drawInfo() {
        drawer.drawInfo();
    }

    private void shootArrow() {

    }

    private void turnRight() {
        player.turnRigh();
        drawGame();
    }

    private void turnLeft() {
        player.turnLeft();
        drawGame();
    }

    private void moveForward() {

    }

    public void drawGame(){
        drawLevel();
        drawTips();
        drawPlayer();
        drawInstruction();
    }

    private void drawLevel() {
        drawer.drawLevel(level, points, arrowsNumber);
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

}
