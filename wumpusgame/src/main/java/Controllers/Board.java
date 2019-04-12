package Controllers;

import lombok.Data;
import models.*;

import java.util.List;
import java.util.Random;

@Data
public class Board {
    Cell[][] board;

    public Board(int level) {
        this.board = new Cell[level*3][level*3];
        createCells();
    }

    public void initializeObjects() {
        setUpWumpus();
        setUpTreasure();
        setUpPits();
        setUpTips();
    }

    private void setUpTips() {
        for (int x = 0; x < board.length; x++)
        {
            for (int y = 0; y < board[x].length; y++)
            {
                if (hasWumpusNext(x ,y))
                    board[x][y].getItems().add(new Smell());
                if (hasPitNext(x ,y))
                    board[x][y].getItems().add(new Breeze());
            }
        }
    }

    private boolean hasPitNext(int x, int y) {
        return hasPit(x + 1,y) || hasPit(x - 1,y) || hasPit(x,y + 1) || hasPit(x,y - 1);
    }

    public boolean hasPit(int x, int y) {
        boolean result = false;
        if (outOfBoard(x,y))
            result = false;
        else if (board[x][y].getObject() != null && (board[x][y].getObject() instanceof Pit))
            result = true;
        return result;
    }

    private boolean outOfBoard(int x, int y) {
        return x < 0 || y < 0 || x > (board.length - 1) || y > (board[x].length - 1);
    }

    private boolean hasWumpusNext(int x, int y) {
        return hasWumpus(x + 1,y) || hasWumpus(x - 1,y) || hasWumpus(x, y + 1) || hasWumpus(x,y - 1);
    }

    public boolean hasWumpus(int x, int y) {
        boolean result = false;
        if (outOfBoard(x,y))
            result = false;
        else if (board[x][y].getObject() != null && (board[x][y].getObject() instanceof Wumpus))
            result = true;
        return result;
    }

    private void createCells(){
        for (int x = 0; x < board.length; x++)
        {
            for (int y = 0; y < board[x].length; y++)
            {
                board[x][y] = new Cell();
            }
        }
    }

    public void setUpPlayer(Player player){
        Cell emptyCell = findEmptyCell();
        emptyCell.setObject(player);
        emptyCell.setEscapeCell(true);
    }

    private void setUpWumpus(){
        findEmptyCell().setObject(new Wumpus());
    }

    private void setUpPits(){
        for (int i = 0 ; i < board.length ; i++) {
            findEmptyCell().setObject(new Pit());
        }
    }

    private void setUpTreasure() {
        findEmptyCell().setObject(new Treasure());
    }

    private Cell findEmptyCell(){
        Random rand = new Random();
        int valueX = rand.nextInt(board.length);
        int valueY = rand.nextInt(board[valueX].length);
        if (board[valueX][valueY].getObject() == null)
            return board[valueX][valueY];
        else
            return findEmptyCell();
    }

    public List<GameObject> getTipsInCurrentCell() {
        return  findPlayerCell().getItems();
    }

    public Cell findPlayerCell() {
        Cell result = null;
        for (int x = 0; x < board.length; x++)
        {
            for (int y = 0; y < board[x].length; y++)
            {
                if(board[x][y].getObject() != null && board[x][y].getObject() instanceof Player) {
                    result = board[x][y];
                    break;
                }
            }
        }
        return result;
    }
}
