package chess;

import java.awt.image.*;
import java.util.*;
import finalproject.*;

public class Knight extends Piece {
    public static final int[][] NORMAL_MOVES = {{1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, -2}, {-2, -1}, {-2, 1}, {-1, 2}};
    
    public Knight(Player player, int x, int y){
        super("Knight", player, x, y, 3);
    }
    
    public BufferedImage getIcon(){
        switch(color){
            case "Black": return Resources.getImage("Black_Knight.png");
            default: return Resources.getImage("White_Knight.png");
        }
    }
    
    public ArrayList<Move> getMoves(Game game){
        Board board = game.board;
        ArrayList<Move> moves = new ArrayList<>();
        for(int i = 0; i < NORMAL_MOVES.length; i++){
            int cx = x + NORMAL_MOVES[i][0];
            int cy = y + NORMAL_MOVES[i][1];
            if(Tile.inBounds(cx, cy) && !board.getTile(cx, cy).hasObstruction(color)){
                if(board.getTile(cx, cy).hasPiece) moves.add(new Move(board.getTile(x, y), board.getTile(cx, cy), Move.TAKE));
                else moves.add(new Move(board.getTile(x, y), board.getTile(cx, cy), Move.MOVE));
            }
        }
        return moves;
    }
    
    public ArrayList<Move> getCompMoves(Game game){
        return getMoves(game);
    }
    
    public ArrayList<Move> getCheckingMoves(Game game){
        return getMoves(game);
    }
    
    
    public String toString(){
        switch(color){
            case "Black": return "♞";
            default: return "♘";
        }
    }
    
    public static String toString(String color){
        switch(color){
            case "Black": return "♞";
            default: return "♘";
        }
    }
}
