package games.Chess.Model;

import misc.Interfaces.iHandler;
import misc.Interfaces.iState;
import games.Chess.interfaces.iChessGame;
import misc.TileGUI.Discrete2DPlus.TileGUI2dDiscretePlus;
import misc.TileGUI.enums.TileSize;

import java.util.HashMap;

public class ChessHandler implements iHandler {
    private ChessPlayer p;
    private HashMap<Integer, String> spriteMapping;
    private TileGUI2dDiscretePlus gui;

    public ChessHandler() {
        init();
    }

    @Override
    public void init() {
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
        spriteMapping.put(iChessGame.W_ROOK_WF, "src/main/resources/Chess/white/w_rook_wf.png");
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
        spriteMapping.put(iChessGame.B_ROOK_WF, "src/main/resources/Chess/black/b_rook_wf.png");
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
        int i = 0;
        int step = 1, offset = 10;
        while(!game.getState().isGameOver()) {
            if(i == offset + 1 * step) {
                game.move(iChessGame.B2,iChessGame.B4);
            }
            if(i == offset + 2 * step) {
                game.move(iChessGame.B7,iChessGame.B6);
            }
            if(i == offset + 3 * step) {
                game.move(iChessGame.E2,iChessGame.E3);
            }
            if(i == offset + 4 * step) {
                game.move(iChessGame.H7,iChessGame.H5);
            }
            if(i == offset + 5 * step) {
                game.move(iChessGame.A2,iChessGame.A4);
            }
            if(i == offset + 6 * step) {
                game.move(iChessGame.C7,iChessGame.C5);
            }
            if(i == offset + 7 * step) {
                game.move(iChessGame.B4,iChessGame.C5);
            }
            if(i == offset + 8 * step) {
                game.move(iChessGame.B6,iChessGame.C5);
            }
            if(i == offset + 9 * step) {
                game.move(iChessGame.E3,iChessGame.E4);
            }
            if(i == offset + 10 * step) {
                game.move(iChessGame.C5,iChessGame.C4);
            }
            if(i == offset + 11 * step) {
                game.move(iChessGame.F2,iChessGame.F3);
            }
            if(i == offset + 12 * step) {
                game.move(iChessGame.C4,iChessGame.C3);
            }
            if(i == offset + 13 * step) {
                game.move(iChessGame.E4,iChessGame.E5);
            }
            if(i == offset + 14 * step) {
                game.move(iChessGame.D7,iChessGame.D5);
            }
            if(i == offset + 15 * step) {
                game.move(iChessGame.E5,iChessGame.D5);
            }
            if(i == offset + 16 * step) {
                game.move(iChessGame.H5,iChessGame.H4);
            }
            if(i == offset + 17 * step) {
                game.move(iChessGame.G2,iChessGame.G4);
            }
            if(i == offset + 18 * step) {
                game.move(iChessGame.H4,iChessGame.G4);
            }
            if(i == offset + 19 * step) {
                game.move(iChessGame.H2,iChessGame.H4);
            }
            if(i == offset + 20 * step) {
                game.move(iChessGame.G4,iChessGame.H4);
            }
            if(i == offset + 21 * step) {
                game.move(iChessGame.D5,iChessGame.D6);
            }
            if(i == offset + 22 * step) {
                game.move(iChessGame.H4,iChessGame.H3);
            }
            if(i == offset + 23 * step) {
                game.move(iChessGame.D6,iChessGame.D7);
            }
            if(i == offset + 24 * step) {
                game.move(iChessGame.H3,iChessGame.H2);
            }
            if(i == offset + 25 * step) {
                game.move(iChessGame.D7,iChessGame.D8);
            }
            if(i == offset + 26 * step) {
                game.move(iChessGame.H2,iChessGame.H1);
            }
            if(i == offset + 27 * step) {
                game.move(iChessGame.A4,iChessGame.A5);
            }
            if(i == offset + 28 * step) {
                game.move(iChessGame.H8,iChessGame.H4);
            }
            if(i == offset + 29 * step) {
                game.move(iChessGame.D2,iChessGame.D3);
            }
            if(i == offset + 30 * step) {
                game.move(iChessGame.H4,iChessGame.F4);
            }
            if(i == offset + 31 * step) {
                game.move(iChessGame.D3,iChessGame.D4);
            }
            if(i == offset + 32 * step) {
                game.move(iChessGame.F4,iChessGame.F6);
            }
            if(i == offset + 33 * step) {
                game.move(iChessGame.D4,iChessGame.D5);
            }
            if(i == offset + 34 * step) {
                game.move(iChessGame.F6,iChessGame.H6);
            }
            if(i == offset + 35 * step) {
                game.move(iChessGame.G1,iChessGame.H3);
            }
            while(System.currentTimeMillis() - cur < delta) {
                //System.out.println(System.currentTimeMillis() - cur);
            }
            cur = System.currentTimeMillis();
            gui.render(game.getState());
            i++;
        }
    }
}
