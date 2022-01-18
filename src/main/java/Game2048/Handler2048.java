package Game2048;

import Game2048.enums.BoardSize2048;
import Snake.GUI.SnakeGUI;
import Snake.Representation.SnakeGame;
import Snake.Representation.SnakeState;
import Snake.enums.SnakeBoardSize;
import Snake.enums.SnakeSpeed;
import Utils.Tuple;

import java.util.ArrayList;
import java.util.Comparator;

public class Handler2048 {
    private ArrayList<Tuple<String, Integer>> highscores;
    private SnakeGUI gui;
    private Player2048 p;
    private KeyManager2048 km;
    private BoardSize2048 boardSize2048;

    public Handler2048() {
        highscores = new ArrayList<>();
        p = new Player2048();
        km = new KeyManager2048();
        boardSize2048 = BoardSize2048.FOUR;
        game();

        //newGame(boardSize2048);
    }

    public void newGame(BoardSize2048 boardSize2048) {
        /*SnakeGame game = new SnakeGame(snakeBoardSize, snakeSpeed);
        p.setGame(game);
        km.setPlayer(p);
        if(gui != null)
            gui.exit();
        game();
        newGame(SnakeBoardSize.STANDARD, SnakeSpeed.MEDIUM);*/
    }

    public void addToHighScores(SnakeState state) {
        /*highscores.add(new Tuple<>("Bob",state.getScore()));
        highscores.sort(new Comparator<Tuple<String,Integer>>() {
            @Override
            public int compare(Tuple<String,Integer> o1, Tuple<String,Integer> o2) {
                return -Integer.compare(o1.getY(),o2.getY());
            }
        });
        System.out.println("--------------------------------");
        System.out.println("           Highscores");
        System.out.println("--------------------------------");
        for(int i = 0; i < 5 && i < highscores.size(); i++) {
            System.out.println("     "+(i+1)+". "+ highscores.get(i).getX()+" "+highscores.get(i).getY());
        }
        System.out.println("--------------------------------");*/
    }

    public void game() {
        /*SnakeGame game = new SnakeGame(snakeBoardSize, snakeSpeed);
        p.setGame(game);
        gui = new SnakeGUI(km, this, game);

        double cur = System.currentTimeMillis();
        double delta = game.getSpeed();
        while(!game.getState().isGameOver()) {
            while(System.currentTimeMillis() - cur < delta) {
                //System.out.println(System.currentTimeMillis() - cur);
            }
            cur = System.currentTimeMillis();
            game.updateBoard();
            gui.render(game.getState());
        }
        addToHighScores(game.getState());*/
    }

}
