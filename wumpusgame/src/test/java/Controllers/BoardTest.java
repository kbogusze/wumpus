package Controllers;

import enums.Direction;
import models.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    @Test
    void outOfBoard() {
        Board b = new Board(1);
        assertTrue(b.outOfBoard(15,-10));
        assertTrue(b.outOfBoard(-5,-10));
        assertTrue(b.outOfBoard(100,1));
    }

    @Test
    void hasPit() {
        Board b = new Board(1);
        b.getBoard()[0][0].setObject(new Pit());
        assertTrue(b.hasPit(0,0));
    }

    @Test
    void hasWumpus() {
        Board b = new Board(1);
        b.getBoard()[0][0].setObject(new Wumpus());
        assertTrue(b.hasWumpus(0,0));
    }

    @Test
    void getTipsInCurrentCell() {
        Board b = new Board(1);
        List<GameObject> list = new ArrayList<>();
        list.add(new Smell());
        Player p = new Player();
        b.setUpPlayer(p);
        b.findPlayerCell().setItems(list);
        assertEquals(b.getTipsInCurrentCell(), list);
    }

    @Test
    void findPlayerCell() {
        Board b = new Board(1);
        Player p = new Player();
        b.setUpPlayer(p);
        assertEquals(b.findPlayerCell().getObject(), p);
    }

    @Test
    void findPlayerCellCordinates() {
        Board b = new Board(1);
        b.getBoard()[0][0].setObject(new Player());
        assertEquals(b.findPlayerCellCordinates(),new Point(0,0));
    }

    @Test
    void getNextPoint() {
        Board b = new Board(1);
        Player p = new Player();
        b.getBoard()[0][0].setObject(p);
        assertEquals(b.getNextPoint(p),new Point(-1,0));
    }

    @Test
    void movePlayerForward() {
        Board b = new Board(1);
        Player p = new Player();
        b.getBoard()[0][0].setObject(p);
        b.movePlayer(p,b.getNextPoint(p));
        assertEquals(b.findPlayerCellCordinates(),new Point(0,0));
    }

    @Test
    void movePlayerBackward() {
        Board b = new Board(1);
        Player p = new Player();
        b.getBoard()[0][0].setObject(p);
        p.setDirection(Direction.BACKWARD);
        b.movePlayer(p,b.getNextPoint(p));
        assertEquals(b.findPlayerCellCordinates(),new Point(1,0));
    }

    @Test
    void hasTreasure() {
        Board b = new Board(1);
        b.getBoard()[0][0].setObject(new Treasure());
        assertTrue(b.hasTreasure(0,0));
    }

    @Test
    void onTarget() {
        Board b = new Board(1);
        Player p = new Player();
        b.getBoard()[1][0].setObject(p);
        b.getBoard()[0][0].setObject(new Wumpus());
        assertTrue(b.onTarget(p));
    }

    @Test
    void hasPitNext() {
        Board b = new Board(1);
        b.getBoard()[0][0].setObject(new Pit());
        assertTrue(b.hasPitNext(0,1));
    }

    @Test
    void hasWumpusNext() {
        Board b = new Board(1);
        b.getBoard()[0][0].setObject(new Wumpus());
        assertTrue(b.hasWumpusNext(1,0));
        assertFalse(b.hasWumpusNext(1,1));
    }

}