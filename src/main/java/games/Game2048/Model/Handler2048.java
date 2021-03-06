package games.Game2048.Model;

import games.Game2048.enums.BoardSize2048;
import games.Game2048.enums.Field2048;
import misc.Interfaces.iHandler;
import misc.Interfaces.iState;
import misc.Interfaces.iTileGUI;
import misc.TileGUI.Discrete2D.TileGUI2dDiscrete;
import misc.TileGUI.enums.TileSize;
import misc.Utils.Tuple;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Handler2048 implements iHandler {
    private HashMap<Integer, Color> colorMapping;
    private ArrayList<Tuple<String, Integer>> highscores;
    private iTileGUI gui;
    private Player2048 p;
    private KeyManager2048 km;
    private BoardSize2048 boardSize2048;

    public Handler2048() {
        init();
    }

    @Override
    public void init() {
        highscores = new ArrayList<>();
        p = new Player2048();
        km = new KeyManager2048();
        km.setPlayer(p);
        boardSize2048 = BoardSize2048.FOUR;
        this.colorMapping = new HashMap<>();
        colorMapping.put(-1,Color.decode("#bbada6"));//lines inbetween
        colorMapping.put(Field2048.EMPTY.getPower(), Color.decode("#bbada6"));
        colorMapping.put(1, Color.decode("#eee4db"));//2
        colorMapping.put(2, Color.decode("#eedfc8"));//4
        colorMapping.put(3, Color.decode("#f3b079"));//8
        colorMapping.put(4, Color.decode("#e89356"));//16
        colorMapping.put(5, Color.decode("#f97b60"));//32
        colorMapping.put(6, Color.decode("#eb5b37"));//64
        colorMapping.put(7, Color.decode("#f3d96a"));//128
        colorMapping.put(8, Color.decode("#f2d04b"));//256
        colorMapping.put(9, Color.decode("#e3bf29"));//512
        colorMapping.put(10, Color.decode("#e3ba14"));//1024
        colorMapping.put(11, Color.decode("#ebc301"));//2048
        int eleven = Integer.valueOf("44413f", 16);
        for(int i = 12; i < 100; i++) {
            eleven += 3000;
            String hex = "#"+Integer.toHexString(eleven);
            colorMapping.put(i,Color.decode(hex));
        }

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
        Game2048 game = new Game2048(boardSize2048);
        p.setGame(game);
        gui = new TileGUI2dDiscrete(km,null,this,game, TileSize.HUGE, "2048", colorMapping);//new SnakeGUI(km, this,game);//

        double cur = System.currentTimeMillis();
        double delta = 50;
        while(!game.getState().isGameOver()) {
            while(System.currentTimeMillis() - cur < delta) {

            }
            cur = System.currentTimeMillis();
            gui.render(game.getState());
        }
        addToHighScores(game.getState(), p.getName());
    }

}
