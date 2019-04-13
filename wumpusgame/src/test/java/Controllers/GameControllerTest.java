package Controllers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameControllerTest {

    @Test
    void createBoard() {
        int level = 1;
        Board board = new Board(level);
        assertNotNull(board.getBoard()[level][level]);
    }

}