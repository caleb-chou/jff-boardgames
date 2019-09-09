package chess;

import java.awt.image.*;
import java.util.*;
import finalproject.*;

public class Bishop extends Piece {
    public Bishop(Player player, int x, int y){
        super("Bishop", player, x, y, 3);
    }
    
    public BufferedImage getIcon(){
        switch(color){
            case "Black": return Resources.getImage("Black_Bishop.png");
            default: return Resources.getImage("White_Bishop.png");
        }
    }
    
    public ArrayList<Move> getMoves(Game game){
        Board board = game.board;
        ArrayList<Move> moves = new ArrayList<>();
        int cx = x + 1;
        int cy = y + 1;
        while(Tile.inBounds(cx, cy) && !board.getTile(cx, cy).hasObstruction(color) && !board.getTile(cx, cy).hasPiece){
            moves.add(new Move(board.getTile(x, y), board.getTile(cx, cy), Move.MOVE));
            cx++;
            cy++;
        }
        if(Tile.inBounds(cx, cy) && !board.getTile(cx, cy).hasObstruction(color) && board.getTile(cx, cy).hasPiece) moves.add(new Move(board.getTile(x, y), board.getTile(cx, cy), Move.TAKE));
        
        cx = x + 1;
        cy = y - 1;
        while(Tile.inBounds(cx, cy) && !board.getTile(cx, cy).hasObstruction(color) && !board.getTile(cx, cy).hasPiece){
            moves.add(new Move(board.getTile(x, y), board.getTile(cx, cy), Move.MOVE));
            cx++;
            cy--;
        }
        if(Tile.inBounds(cx, cy) && !board.getTile(cx, cy).hasObstruction(color) && board.getTile(cx, cy).hasPiece) moves.add(new Move(board.getTile(x, y), board.getTile(cx, cy), Move.TAKE));
        
        cx = x - 1;
        cy = y - 1;
        while(Tile.inBounds(cx, cy) && !board.getTile(cx, cy).hasObstruction(color) && !board.getTile(cx, cy).hasPiece){
            moves.add(new Move(board.getTile(x, y), board.getTile(cx, cy), Move.MOVE));
            cx--;
            cy--;
        }
        if(Tile.inBounds(cx, cy) && !board.getTile(cx, cy).hasObstruction(color) && board.getTile(cx, cy).hasPiece) moves.add(new Move(board.getTile(x, y), board.getTile(cx, cy), Move.TAKE));
        
        cx = x - 1;
        cy = y + 1;
        while(Tile.inBounds(cx, cy) && !board.getTile(cx, cy).hasObstruction(color) && !board.getTile(cx, cy).hasPiece){
            moves.add(new Move(board.getTile(x, y), board.getTile(cx, cy), Move.MOVE));
            cx--;
            cy++;
        }
        if(Tile.inBounds(cx, cy) && !board.getTile(cx, cy).hasObstruction(color) && board.getTile(cx, cy).hasPiece) moves.add(new Move(board.getTile(x, y), board.getTile(cx, cy), Move.TAKE));
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
            case "Black": return "♝";
            default: return "♗";
        }
    }
    
    public static String toString(String color){
        switch(color){
            case "Black": return "♝";
            default: return "♗";
        }
    }
}
