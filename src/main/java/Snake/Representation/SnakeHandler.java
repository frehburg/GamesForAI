package Snake.Representation;

import Interfaces.iHandler;
import Interfaces.iState;
import Interfaces.iTileGUI;
import Snake.enums.SnakeBoardSize;
import Snake.enums.SnakeField;
import Snake.enums.SnakeSpeed;
import TileGUI.*;
import TileGUI.Discrete2D.TileGUI2dDiscrete;
import Utils.Tuple;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

public class SnakeHandler implements iHandler {
    private ArrayList<Tuple<String, Integer>> highscores;
    private iTileGUI gui;
    private SnakePlayer p;
    private SnakeKeyManager km;
    private SnakeBoardSize snakeBoardSize;
    private SnakeSpeed snakeSpeed;
    private HashMap<Integer, Color> colorMapping;

    public SnakeHandler() {
        init();
    }
    @Override
    public void init() {
        highscores = new ArrayList<>();
        p = new SnakePlayer();
        km = new SnakeKeyManager();
        km.setPlayer(p);
        snakeBoardSize = SnakeBoardSize.BIG;
        snakeSpeed = SnakeSpeed.INSANE;
        this.colorMapping = new HashMap<>();
        colorMapping.put(SnakeField.EMPTY.getID() - 1, Color.decode("#005c34"));
        colorMapping.put(SnakeField.EMPTY.getID(), Color.decode("#1c9725"));
        colorMapping.put(SnakeField.UP.getID(), Color.decode("#6a329f"));
        colorMapping.put(SnakeField.DOWN.getID(), Color.decode("#6a329f"));
        colorMapping.put(SnakeField.LEFT.getID(), Color.decode("#6a329f"));
        colorMapping.put(SnakeField.RIGHT.getID(), Color.decode("#6a329f"));
        colorMapping.put(SnakeField.PELLET.getID(), Color.decode("#e51313"));

        newGame();
    }

    @Override
    public void newGame() {
        if(gui != null)
            gui.exit();
        game();//plays one game
        newGame();//starts new game
    }

    @Override
    public void addToHighScores(iState state) {
        highscores.add(new Tuple<>("Bob",state.getScore()));
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
        System.out.println("--------------------------------");
    }

    @Override
    public void game() {
        SnakeGame game = new SnakeGame(snakeBoardSize, snakeSpeed, p);
        gui = new TileGUI2dDiscrete(km,null,this,game, TileSize.SMALL, "Snake", colorMapping);//new SnakeGUI(km, this,game);//

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
        addToHighScores(game.getState());
    }

}
