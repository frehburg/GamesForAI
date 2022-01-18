package Snake;

import Snake.Representation.SnakeHandler;

public class Main {
    public static void main(String[] args) {
        SnakeHandler h = new SnakeHandler();
        /*SnakeGame game = new SnakeGame(SnakeBoardSize.STANDARD, SnakeSpeed.MEDIUM);
        SnakePlayer p = new SnakePlayer(game);
        SnakeGUI gui = new SnakeGUI(game.getState(), p);

        double cur = System.currentTimeMillis();
        double delta = 500;
        while(!game.getState().isGameOver()) {
            while(System.currentTimeMillis() - cur < delta) {
                //System.out.println(System.currentTimeMillis() - cur);
            }
            cur = System.currentTimeMillis();
            game.updateBoard();
            gui.render(game.getState());
        }*/
    }
}
