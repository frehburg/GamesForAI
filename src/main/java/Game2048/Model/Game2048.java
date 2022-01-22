package Game2048.Model;

import Game2048.Interfaces.iGame2048;
import Game2048.enums.BoardSize2048;
import Game2048.enums.Direction2048;
import Game2048.enums.Field2048;
import Interfaces.iGame;
import Utils.Tuple;

import java.util.ArrayList;

public class Game2048 extends iGame2048 implements iGame {

    private Field2048[][] board;
    private boolean gameOver;
    private boolean won;
    private int score;

    public Game2048(BoardSize2048 size) {
        this.size = size;
        this.gameOver = false;
        this.won = false;
        this.score = 0;
        board = new Field2048[size.getSize()][size.getSize()];
        for(int x = 0; x < size.getSize(); x++) {
            for(int y = 0; y < size.getSize(); y++) {
                board[x][y] = Field2048.EMPTY;
            }
        }
        addRandom();
        addRandom();
    }
    @Override
    public void addRandom() {
        ArrayList<Tuple<Integer,Integer>> empty = new ArrayList<>();

        for(int x = 0; x < size.getSize(); x++) {
            for(int y = 0; y < size.getSize(); y++) {
                if(board[x][y] == Field2048.EMPTY) {
                    empty.add(new Tuple<>(x,y));
                }
            }
        }

        if(empty.size() == 0) {
            gameOver = true;
            return;
        }

        //add a 4 with 10% chance
        int r = Utils.RandomUtils.getRandom(0,9);
        Tuple<Integer,Integer> f;
        if(r == 7) {// 1 / 10 chance
            r = Utils.RandomUtils.getRandom(0,empty.size() - 1);
            f = empty.get(r);
            board[f.getX()][f.getY()] = new Field2048(2);
            score += 4;
        } else {
            //add a two
            if(empty.size() == 1) {
                r = 0;
            } else {
                r = Utils.RandomUtils.getRandom(0,empty.size() - 1);
            }
            f = empty.get(r);
            board[f.getX()][f.getY()] = new Field2048(1);
            score += 2;
        }
    }

    @Override
    public void swipe(Direction2048 direction) {
        if(gameOver)
            return;
        boolean somethingmoved = false;
        if(direction == Direction2048.DOWN) {
            Tuple<Field2048[][],Boolean> t = swipeDown(board);
            board = t.getX();
            somethingmoved = t.getY();
        }
        if(direction == Direction2048.RIGHT) {
            board = rotate(board,1);
            Tuple<Field2048[][],Boolean> t = swipeDown(board);
            board = t.getX();
            somethingmoved = t.getY();
            board = rotate(board, -1);
        }
        if(direction == Direction2048.UP) {
            board = rotate(board,2);
            Tuple<Field2048[][],Boolean> t = swipeDown(board);
            board = t.getX();
            somethingmoved = t.getY();
            board = rotate(board, -2);
        }
        if(direction == Direction2048.LEFT) {
            board = rotate(board,3);
            Tuple<Field2048[][],Boolean> t = swipeDown(board);
            board = t.getX();
            somethingmoved = t.getY();
            board = rotate(board, -3);
        }
        if(somethingmoved) {
            addRandom();
        } else if(!somethingmoved && allFieldsFull()) {
            //cannot move anymore
            gameOver = true;
            //lost
            won = false;
            return;
        }
    }

    private boolean allFieldsFull() {
        for(int x = 0; x < size.getSize(); x++) {
            for(int y = 0; y < size.getSize(); y++) {
                if(board[x][y] == Field2048.EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    private Tuple<Field2048[][],Boolean> swipeDown(Field2048[][] a) {
        boolean somethingMoved = false;
        //get copy of a
        Field2048[][] b = new Field2048[size.getSize()][size.getSize()];
        for(int x = 0; x < size.getSize(); x++) {
            for(int y = 0; y < size.getSize(); y++) {
                b[x][y] = a[x][y];
            }
        }
        for(int x = 0; x < size.getSize(); x++) {
            Field2048[] newRow = new Field2048[size.getSize()];
            for(int i = 0; i < size.getSize(); i++) {
                newRow[i] = Field2048.EMPTY;
            }
            for (int y = size.getSize() - 1; y >= 0; y--) {
                if (b[x][y].getPower() == Field2048.EMPTY.getPower()) {//nothing happens if the field is empty
                    continue;
                }
                if (y == 0) {//add last bit if it hasnt been added
                    newRow[y] = b[x][y];
                    continue;
                }
                boolean added = false;
                //System.out.println("x: "+x+" y: "+y+" "+b[x][y]);
                for (int y2 = y - 1; y2 >= 0; y2--) {//go through under it
                    //System.out.println("x: "+x+" y: "+y2+" "+b[x][y2]);
                    if (b[x][y].getPower() == b[x][y2].getPower()) {//Add two together
                        added = true;
                        int newPower = b[x][y].getPower() + 1;
                        newRow[y] = new Field2048(newPower);
                        int deltaScore = (int) Math.pow(2, newPower);
                        score += deltaScore;
                        b[x][y] = Field2048.EMPTY;
                        b[x][y2] = Field2048.EMPTY;

                        if (deltaScore == 2048) {//means a 2048 block was created
                            won = true;
                            //gameOver = true;
                        }

                        somethingMoved = true;
                        break;
                    }
                    if (b[x][y].getPower() != b[x][y2].getPower() && b[x][y2].getPower() != Field2048.EMPTY.getPower()) {
                        break;
                    }
                }
                if (!added) {
                    newRow[y] = b[x][y];
                }
            }
            b[x] = newRow;
        }
        // 2. gravity
        for(int i = 0; i < size.getSize() - 1; i++) {
            for(int x = 0; x < size.getSize(); x++) {
                for (int y = 0; y < size.getSize() - 1; y++) {
                    if (b[x][y].equals(Field2048.EMPTY)) {
                        continue;
                    }
                    if (b[x][y + 1].equals(Field2048.EMPTY)) {
                        b[x][y + 1] = b[x][y];
                        b[x][y] = Field2048.EMPTY;
                        somethingMoved = true;
                    }
                }
            }
        }
        return new Tuple<>(b, somethingMoved);
    }

    /**
     * -3: -270° counterclockwise
     * -2: -180° counterclockwise
     * -1:  -90° counterclockwise
     *  0:    no rotation
     *  1:   90° clockwise
     *  2:  180° clockwise
     *  3:  270° clockwise
     * @param i
     * @return
     */
    private Field2048[][] rotate(Field2048[][] a, int i) {
        if(i < - 3 || i > 3)
            return a;
        if(i == - 3 || i == 1) {
            return rotate90(a);
        }
        if(i == -2 || i == 2) {
            return rotate90(rotate90(a));
        }
        if(i == -1 || i == 3) {
            return rotate90(rotate90(rotate90(a)));
        }
        return a;
    }

    private Field2048[][] rotate90(Field2048[][] a) {
        Field2048[][] b = new Field2048[size.getSize()][size.getSize()];
        for(int x = 0; x < size.getSize(); x++) {
            for(int y = size.getSize() - 1; y >= 0; y--) {
                Field2048 cur = a[x][y];
                b[size.getSize() - 1 - y][x] = cur;
            }
        }
        return b;
    }

    @Override
    public BoardSize2048 getSize() {
        return size;
    }

    @Override
    public Field2048[][] getBoard() {
        return board;
    }

    @Override
    public String toString() {
        return  getString(board);
    }

    public String getString(Field2048[][] a) {
        StringBuilder s = new StringBuilder("--------------------------------------------\n");
        for(int y = 0; y < size.getSize(); y++) {
            s.append("|");
            for(int x = 0; x < size.getSize(); x++) {
                s.append(a[x][y]).append("|");
            }
            s.append("\n");
        }
        return s.toString();
    }

    public State2048 getState() {
        return new State2048(gameOver,won,score,board);
    }
}
