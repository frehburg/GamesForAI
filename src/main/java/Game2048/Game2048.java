package Game2048;

import Game2048.enums.BoardSize2048;
import Game2048.enums.Direction2048;
import Game2048.enums.Field2048;
import Utils.Tuple;
import jdk.jshell.execution.Util;

import java.util.ArrayList;

public class Game2048 extends iGame2048{

    private Field2048[][] board;

    public Game2048(BoardSize2048 size) {
        this.size = size;
        board = new Field2048[size.getSize()][size.getSize()];
        for(int x = 0; x < size.getSize(); x++) {
            for(int y = 0; y < size.getSize(); y++) {
                board[x][y] = new Field2048(1);
            }
        }
        //addRandom();
        //Add artificial numbers
        /*board[0][0] = new Field2048(1);
        board[0][1] = new Field2048(1);
        board[0][2] = new Field2048(1);
        board[0][3] = new Field2048(1);
        board[1][1] = new Field2048(1);
        board[1][2] = new Field2048(1);
        board[1][3] = new Field2048(1);*/
        /*
        //for rotation
        board[0][0] = new Field2048(1);
        board[1][0] = new Field2048(2);
        board[2][0] = new Field2048(3);
        board[3][0] = new Field2048(4);
        board[0][1] = new Field2048(5);
        board[1][1] = new Field2048(6);
        board[2][1] = new Field2048(7);
        board[3][1] = new Field2048(8);
        board[0][2] = new Field2048(9);
        board[1][2] = new Field2048(10);
        board[2][2] = new Field2048(11);
        board[3][2] = new Field2048(12);
        board[0][3] = new Field2048(13);
        board[1][3] = new Field2048(14);
        board[2][3] = new Field2048(15);
        board[3][3] = new Field2048(16);*/
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

        //add a two
        int r = Utils.RandomUtils.getRandom(0,empty.size() - 1);
        Tuple<Integer,Integer> f = empty.get(r);
        board[f.getX()][f.getY()] = new Field2048(1);

        //add a 4 with 10% chance
        r = Utils.RandomUtils.getRandom(0,9);
        if(r == 7) {// 1 / 10 chance
            r = Utils.RandomUtils.getRandom(0,empty.size() - 1);
            f = empty.get(r);
            board[f.getX()][f.getY()] = new Field2048(2);
        }
    }

    @Override
    public void swipe(Direction2048 direction) {
        if(direction == Direction2048.DOWN) {
            board = swipeDown(board);
        }
        if(direction == Direction2048.RIGHT) {
            board = rotate(board,1);
            board = swipeDown(board);
            board = rotate(board, -1);
        }
        if(direction == Direction2048.UP) {
            board = rotate(board,2);
            board = swipeDown(board);
            board = rotate(board, -2);
        }
        if(direction == Direction2048.LEFT) {
            board = rotate(board,3);
            board = swipeDown(board);
            board = rotate(board, -3);
        }
    }

    private Field2048[][] swipeDown(Field2048[][] a) {
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
            inner:for(int y = size.getSize() - 1; y >= 0; y--) {
                if(b[x][y].equals(Field2048.EMPTY)) {
                    continue inner;
                }
                if(y == 0) {
                    newRow[y] = b[x][y];
                    continue inner;
                }
                if(b[x][y].equals(b[x][y - 1])) {
                    newRow[y] = new Field2048(b[x][y].getPower() + 1);
                    b[x][y] = Field2048.EMPTY;
                    b[x][y - 1] = Field2048.EMPTY;
                } else {
                    newRow[y] = b[x][y];
                }
            }
            b[x] = newRow;
        }
        // 2. gravity
        for(int i = 0; i < size.getSize() - 1; i++) {
            for(int x = 0; x < size.getSize(); x++) {
                inner:for(int y = 0; y < size.getSize() - 1; y++) {
                    if(b[x][y].equals(Field2048.EMPTY)) {
                        continue inner;
                    }
                    if(b[x][y + 1].equals(Field2048.EMPTY)) {
                        b[x][y + 1] = b[x][y];
                        b[x][y] = Field2048.EMPTY;
                    }
                }
            }
        }
        return b;
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
    public boolean isSwipable(Direction2048 direction) {
        return false;
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
        String s = "--------------------------------------------\n";
        for(int y = 0; y < size.getSize(); y++) {
            s += "|";
            for(int x = 0; x < size.getSize(); x++) {
                s += a[x][y] + "|";
            }
            s+="\n";
        }
        return s;
    }
}
