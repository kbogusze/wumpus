package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyEvent;
import models.GameObject;
import models.Player;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class GameController implements Initializable {

    private int level = 1;
    private int points = 0;
    private Board board;
    private Drawer drawer;
    private Player player;
    private AudioPlayer audioPlayer;

    @FXML
    private TextArea textArea;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.drawer = new Drawer();
        this.player = new Player();
        this.audioPlayer = new AudioPlayer();
        createBoard();
        audioPlayer.playIntro();
        drawGame();
    }

    @FXML
    void keyboardEvent(KeyEvent event) {

    }

    public void drawGame(){
        drawBackground();
        drawTips();
        drawPlayer();
        drawInstruction();
    }

    private void drawTips() {
        List<GameObject> objects = board.getTipsInCurrentCell();
        objects.forEach(d -> drawer.drawFromStream(d.draw(),textArea));
    }

    private void drawPlayer() {
        drawer.drawFromStream(player.draw(),textArea);
    }

    private void createBoard(){
        board = new Board(level);
        board.initializeObjects();
        board.setUpPlayer(player);
    }

    private void drawBackground() {
        drawer.drawHeader(textArea);
    }

    private void drawInstruction() {
        drawer.drawInstruction(textArea);
    }



}
