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

    public boolean outOfBoard(int x, int y) {
        return x < 0 || y < 0 || x >= board.length || y >= board[x].length;
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

    public boolean hasPitNext(int x, int y) {
        return hasPit(x + 1,y) || hasPit(x - 1,y) || hasPit(x,y + 1) || hasPit(x,y - 1);
    }

    public boolean hasPit(int x, int y) {
        return containsObject( x,  y, obj -> obj instanceof Pit);
    }

    public boolean hasWumpusNext(int x, int y) {
        return hasWumpus(x + 1,y) || hasWumpus(x - 1,y) || hasWumpus(x, y + 1) || hasWumpus(x,y - 1);
    }

    public boolean hasWumpus(int x, int y) {
        return containsObject( x,  y, obj -> obj instanceof Wumpus);
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
        return findByType(obj -> obj instanceof  Player);
    }

    public Cell findByType(Predicate predicate) {
        Cell result = null;
        for (int x = 0; x < board.length; x++)
        {
            for (int y = 0; y < board[x].length; y++)
            {
                if(board[x][y].getObject() != null && predicate.matches(board[x][y].getObject())) {
                    result = board[x][y];
                    break;
                }
            }
        }
        return result;
    }

    public Point findPlayerCellCordinates() {
        Point result = new Point();
        for (int x = 0; x < board.length; x++)
        {
            for (int y = 0; y < board[x].length; y++)
            {
                if(board[x][y].getObject() != null && board[x][y].getObject() instanceof Player) {
                    result.setX(x);
                    result.setY(y);
                    break;
                }
            }
        }
        return result;
    }

    public Point getNextPoint(Player player) {
        Point playerCell = findPlayerCellCordinates();
        return player.getNextPoint(playerCell);
    }

    public void movePlayer(Player player, Point nextPoint) {
        if (!outOfBoard(nextPoint.getX(),nextPoint.getY())) {
            findPlayerCell().setObject(null);
            board[nextPoint.getX()][nextPoint.getY()].setObject(player);
        }
    }

    public boolean hasTreasure(int x, int y) {
        return containsObject( x,  y, obj -> obj instanceof Treasure);
    }

    public boolean containsObject(int x, int y,Predicate predicate){
        boolean result = false;
        if (outOfBoard(x,y))
            result = false;
        else if (board[x][y].getObject() != null && predicate.matches(board[x][y].getObject()))
            result = true;
        return result;
    }

    public boolean onTarget(Player player) {
        boolean result = false;
        Point nextPoint = player.getNextPoint(findPlayerCellCordinates());
        while (!result && !outOfBoard(nextPoint.getX(), nextPoint.getY())) {
            if (hasWumpus(nextPoint.getX(), nextPoint.getY())) {
                result = true;
            }
            nextPoint = player.getNextPoint(nextPoint);
        }
        return result;
    }

    interface Predicate{
        boolean matches(GameObject object);
    }
}
