package Chess.Representation;

import Interfaces.iHandler;
import Interfaces.iState;
import Chess.interfaces.iChessGame;
import TileGUI.Discrete2DPlus.TileGUI2dDiscretePlus;
import TileGUI.enums.TileSize;

import java.util.ArrayList;
import java.util.HashMap;

public class ChessHandler implements iHandler {
    private ArrayList<Object> highscores;
    private ChessPlayer p;
    private HashMap<Integer, String> spriteMapping;
    private TileGUI2dDiscretePlus gui;

    public ChessHandler() {
        init();
    }

    @Override
    public void init() {
        highscores = new ArrayList<>();
        p = new ChessPlayer();
        //km = new ChessKeyManager();
        //mm = new ChessMouseManager();
        //km.setPlayer(p);

        //---------------------------------------------
        this.spriteMapping = new HashMap<>();
        //---white back rank---------------------
        spriteMapping.put(iChessGame.W_ROOK_BF, "src/main/resources/Chess/white/w_rook_bf.png");
        spriteMapping.put(iChessGame.W_KNIGHT_WF, "src/main/resources/Chess/white/w_knight_wf.png");
        spriteMapping.put(iChessGame.W_BISHOP_BF, "src/main/resources/Chess/white/w_bishop_bf.png");
        spriteMapping.put(iChessGame.W_KING, "src/main/resources/Chess/white/w_king.png");
        spriteMapping.put(iChessGame.W_QUEEN, "src/main/resources/Chess/white/w_queen.png");
        spriteMapping.put(iChessGame.W_BISHOP_WF, "src/main/resources/Chess/white/w_bishop_wf.png");
        spriteMapping.put(iChessGame.W_KNIGHT_BF, "src/main/resources/Chess/white/w_knight_bf.png");
        spriteMapping.put(iChessGame.W_ROOK_WF, "src/main/resources/Chess/white/w_rook_bf.png");
        //---white front rank---------------------
        spriteMapping.put(iChessGame.W_PAWN_A, "src/main/resources/Chess/white/w_pawn.png");
        spriteMapping.put(iChessGame.W_PAWN_B, "src/main/resources/Chess/white/w_pawn.png");
        spriteMapping.put(iChessGame.W_PAWN_C, "src/main/resources/Chess/white/w_pawn.png");
        spriteMapping.put(iChessGame.W_PAWN_D, "src/main/resources/Chess/white/w_pawn.png");
        spriteMapping.put(iChessGame.W_PAWN_E, "src/main/resources/Chess/white/w_pawn.png");
        spriteMapping.put(iChessGame.W_PAWN_F, "src/main/resources/Chess/white/w_pawn.png");
        spriteMapping.put(iChessGame.W_PAWN_G, "src/main/resources/Chess/white/w_pawn.png");
        spriteMapping.put(iChessGame.W_PAWN_H, "src/main/resources/Chess/white/w_pawn.png");
        //---black back rank---------------------
        spriteMapping.put(iChessGame.B_ROOK_BF, "src/main/resources/Chess/black/b_rook_bf.png");
        spriteMapping.put(iChessGame.B_KNIGHT_WF, "src/main/resources/Chess/black/b_knight_wf.png");
        spriteMapping.put(iChessGame.B_BISHOP_BF, "src/main/resources/Chess/black/b_bishop_bf.png");
        spriteMapping.put(iChessGame.B_KING, "src/main/resources/Chess/black/b_king.png");
        spriteMapping.put(iChessGame.B_QUEEN, "src/main/resources/Chess/black/b_queen.png");
        spriteMapping.put(iChessGame.B_BISHOP_WF, "src/main/resources/Chess/black/b_bishop_wf.png");
        spriteMapping.put(iChessGame.B_KNIGHT_BF, "src/main/resources/Chess/black/b_knight_bf.png");
        spriteMapping.put(iChessGame.B_ROOK_WF, "src/main/resources/Chess/black/b_rook_bf.png");
        //---black front rank---------------------
        spriteMapping.put(iChessGame.B_PAWN_A, "src/main/resources/Chess/black/b_pawn.png");
        spriteMapping.put(iChessGame.B_PAWN_B, "src/main/resources/Chess/black/b_pawn.png");
        spriteMapping.put(iChessGame.B_PAWN_C, "src/main/resources/Chess/black/b_pawn.png");
        spriteMapping.put(iChessGame.B_PAWN_D, "src/main/resources/Chess/black/b_pawn.png");
        spriteMapping.put(iChessGame.B_PAWN_E, "src/main/resources/Chess/black/b_pawn.png");
        spriteMapping.put(iChessGame.B_PAWN_F, "src/main/resources/Chess/black/b_pawn.png");
        spriteMapping.put(iChessGame.B_PAWN_G, "src/main/resources/Chess/black/b_pawn.png");
        spriteMapping.put(iChessGame.B_PAWN_H, "src/main/resources/Chess/black/b_pawn.png");

        //---board--------------------------------
        spriteMapping.put(iChessGame.BLACK, "src/main/resources/Chess/board/b_field.png");
        spriteMapping.put(iChessGame.BLACK_RISK, "src/main/resources/Chess/board/b_field_risk.png");
        spriteMapping.put(iChessGame.WHITE, "src/main/resources/Chess/board/w_field.png");
        spriteMapping.put(iChessGame.WHITE_RISK, "src/main/resources/Chess/board/w_field_risk.png");
        //---rim----------------------------------
        spriteMapping.put(iChessGame.RIM_TOP_LEFT, "src/main/resources/Chess/board/rim_top_left.png");
        spriteMapping.put(iChessGame.RIM_TOP, "src/main/resources/Chess/board/rim_top.png");
        spriteMapping.put(iChessGame.RIM_TOP_RIGHT, "src/main/resources/Chess/board/rim_top_right.png");
        spriteMapping.put(iChessGame.RIM_LEFT, "src/main/resources/Chess/board/rim_left.png");
        spriteMapping.put(iChessGame.RIM_RIGHT, "src/main/resources/Chess/board/rim_right.png");
        spriteMapping.put(iChessGame.RIM_BOTTOM_LEFT, "src/main/resources/Chess/board/rim_bottom_left.png");
        spriteMapping.put(iChessGame.RIM_BOTTOM, "src/main/resources/Chess/board/rim_bottom.png");
        spriteMapping.put(iChessGame.RIM_BOTTOM_RIGHT, "src/main/resources/Chess/board/rim_bottom_right.png");
        spriteMapping.put(iChessGame.EMPTY, "src/main/resources/Chess/board/empty.png");

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
        //leave empty
    }

    @Override
    public void game() {
        ChessGame game = new ChessGame();
        //with colors
        gui = new TileGUI2dDiscretePlus(null,null,this,game, TileSize.HUGE, spriteMapping, "Chess");
        double cur = System.currentTimeMillis();
        double delta = 50;
        while(!game.getState().isGameOver()) {
            while(System.currentTimeMillis() - cur < delta) {
                //System.out.println(System.currentTimeMillis() - cur);
            }
            cur = System.currentTimeMillis();
            gui.render(game.getState());
        }
    }
}
