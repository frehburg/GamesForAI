package Game2048;

import Game2048.enums.BoardSize2048;
import Game2048.enums.Direction2048;
import Snake.Representation.SnakeHandler;

public class Main {
    public static void main(String[] args) {
        Game2048 game = new Game2048(BoardSize2048.FOUR);
        System.out.println(game.toString());
        game.swipe(Direction2048.UP);
        System.out.println("UP");
        System.out.println(game.toString());
        System.out.println("LEFT");
        game.swipe(Direction2048.LEFT);
        System.out.println(game.toString());
        System.out.println("DOWN");
        game.swipe(Direction2048.DOWN);
        System.out.println(game.toString());
        System.out.println("RIGHT");
        game.swipe(Direction2048.RIGHT);
        System.out.println(game.toString());
    }
}
