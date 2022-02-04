package games.Snake.Model;

import misc.Interfaces.iAgent;
import misc.Interfaces.iHandler;
import misc.Interfaces.iState;
import misc.Interfaces.iTileGUI;
import ai.Snake.agents.SnakeSimpleReflexAgent;
import games.Snake.enums.SnakeBoardSize;
import games.Snake.enums.SnakeField;
import games.Snake.enums.SnakeSpeed;
import misc.TileGUI.Discrete2D.TileGUI2dDiscrete;
import misc.TileGUI.enums.TileSize;
import misc.Utils.Tuple;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class SnakeHandler implements iHandler {
    private ArrayList<Tuple<String, Integer>> highscores;
    private iTileGUI gui;
    private SnakePlayer p;
    private SnakeKeyManager km;
    private SnakeBoardSize snakeBoardSize;
    private SnakeSpeed snakeSpeed;
    private HashMap<Integer, Color> colorMapping;
    private HashMap<Integer, String> spriteMapping;

    public SnakeHandler() {
        init();
    }
    @Override
    public void init() {
        highscores = new ArrayList<>();
        p = new SnakePlayer("Iatros v1");
        km = new SnakeKeyManager();
        km.setPlayer(p);
        snakeBoardSize = SnakeBoardSize.STANDARD;
        snakeSpeed = SnakeSpeed.EASY;
        this.colorMapping = new HashMap<>();
        colorMapping.put(SnakeField.EMPTY.getID() - 1, Color.decode("#005c34"));
        colorMapping.put(SnakeField.EMPTY.getID(), Color.decode("#1c9725"));
        colorMapping.put(SnakeField.UP.getID(), Color.decode("#6a329f"));
        colorMapping.put(SnakeField.DOWN.getID(), Color.decode("#6a329f"));
        colorMapping.put(SnakeField.LEFT.getID(), Color.decode("#6a329f"));
        colorMapping.put(SnakeField.RIGHT.getID(), Color.decode("#6a329f"));
        colorMapping.put(SnakeField.PELLET.getID(), Color.decode("#e51313"));

        this.spriteMapping = new HashMap<>();
        spriteMapping.put(SnakeField.EMPTY.getID() - 1, "src/main/resources/Snake/grass.png");
        spriteMapping.put(SnakeField.EMPTY.getID(), "src/main/resources/Snake/grass.png");
        spriteMapping.put(SnakeField.UP.getID(), "src/main/resources/Snake/snake.png");
        spriteMapping.put(SnakeField.DOWN.getID(), "src/main/resources/Snake/snake.png");
        spriteMapping.put(SnakeField.LEFT.getID(), "src/main/resources/Snake/snake.png");
        spriteMapping.put(SnakeField.RIGHT.getID(), "src/main/resources/Snake/snake.png");
        spriteMapping.put(SnakeField.PELLET.getID(), "src/main/resources/Snake/pellet.png");
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
    public void addToHighScores(iState state, String playerName) {
        highscores.add(new Tuple<>(playerName,state.getScore()));
        highscores.sort((o1, o2) -> -Integer.compare(o1.getY(),o2.getY()));
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
        iAgent agent = new SnakeSimpleReflexAgent(p);
        //with colors
        gui = new TileGUI2dDiscrete(km,null,this,game, TileSize.SMALL, "Snake", colorMapping);//new SnakeGUI(km, this,game);//
        //with images
        //gui = new TileGUI2dDiscrete(km,null,this,game, TileSize.SMALL, spriteMapping, "Snake");
        double cur = System.currentTimeMillis();
        double delta = game.getSpeed();
        while(!game.getState().isGameOver()) {
            while(System.currentTimeMillis() - cur < delta) {
                //System.out.println(System.currentTimeMillis() - cur);
            }
            cur = System.currentTimeMillis();
            agent.react(game.getState());
            game.updateBoard();
            gui.render(game.getState());
        }
        addToHighScores(game.getState(), p.getName());
    }

}
