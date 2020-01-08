package chess;

import java.awt.image.*;
import java.util.*;
import finalproject.*;

public class Pawn extends Piece{
    boolean canBeEnpassant;
    boolean canMoveTwo;
    
    public Pawn(Player player, int x, int y){
        super("Pawn", player, x, y, 1);
        canBeEnpassant = false;
        canMoveTwo = true;
    }
    
    public BufferedImage getIcon(){
        switch(color){
            case "Black": return Resources.getImage("Black_Pawn.png");
            default: return Resources.getImage("White_Pawn.png");
        }
        
    }
    
    public ArrayList<Move> getMoves(Game game){
        Board board = game.board;
        ArrayList<Move> moves = new ArrayList<>();
        if(color.equals("White")){
            int cx = x;
            int cy = y + 1;
            if(Tile.inBounds(cx, cy) && !board.getTile(cx, cy).hasPiece){
                if(cy == 7) moves.add(new Move(board.getTile(x, y), board.getTile(cx, cy), Move.PROMOTE, player.game));
                else moves.add(new Move(board.getTile(x, y), board.getTile(cx, cy), Move.MOVE));
                
                cx = x;
                cy = y + 2;
                if(canMoveTwo && Tile.inBounds(cx, cy) && !board.getTile(cx, cy).hasPiece) moves.add(new Move(board.getTile(x, y), board.getTile(cx, cy), Move.PAWNTWO));
            }
            cx = x - 1;
            cy = y + 1;
            if(Tile.inBounds(cx, cy) && board.getTile(cx, cy).hasPiece && !board.getTile(cx, cy).hasObstruction(color)){
                if(cy == 7) moves.add(new Move(board.getTile(x, y), board.getTile(cx, cy), Move.PROMOTETAKE, player.game));
                else moves.add(new Move(board.getTile(x, y), board.getTile(cx, cy), Move.TAKE));
            }
            cx = x + 1;
            cy = y + 1;
            if(Tile.inBounds(cx, cy) && board.getTile(cx, cy).hasPiece && !board.getTile(cx, cy).hasObstruction(color)){
                if(cy == 7) moves.add(new Move(board.getTile(x, y), board.getTile(cx, cy), Move.PROMOTETAKE, player.game));
                else moves.add(new Move(board.getTile(x, y), board.getTile(cx, cy), Move.TAKE));
            }
            
            cx = x + 1;
            cy = y;
            if(Tile.inBounds(cx, cy) && board.getTile(cx, cy).hasPiece && board.getTile(cx, cy).piece.name.equals("Pawn") && ((Pawn) board.getTile(cx, cy).piece).canBeEnpassant) moves.add(new Move(board.getTile(x, y), board.getTile(cx, cy + 1), board.getTile(cx, cy), Move.ENPASSANT));
            
            cx = x - 1;
            cy = y;
            if(Tile.inBounds(cx, cy) && board.getTile(cx, cy).hasPiece && board.getTile(cx, cy).piece.name.equals("Pawn") && ((Pawn) board.getTile(cx, cy).piece).canBeEnpassant) moves.add(new Move(board.getTile(x, y), board.getTile(cx, cy + 1), board.getTile(cx, cy), Move.ENPASSANT));
        } else {
            int cx = x;
            int cy = y - 1;
            if(Tile.inBounds(cx, cy) && !board.getTile(cx, cy).hasPiece){
                if(cy == 0) moves.add(new Move(board.getTile(x, y), board.getTile(cx, cy), Move.PROMOTE, player.game));
                else moves.add(new Move(board.getTile(x, y), board.getTile(cx, cy), Move.MOVE));
                
                cx = x;
                cy = y - 2;
                if(canMoveTwo && Tile.inBounds(cx, cy) && !board.getTile(cx, cy).hasPiece) moves.add(new Move(board.getTile(x, y), board.getTile(cx, cy), Move.PAWNTWO));
            }
            cx = x - 1;
            cy = y - 1;
            if(Tile.inBounds(cx, cy) && board.getTile(cx, cy).hasPiece() && !board.getTile(cx, cy).hasObstruction(color)){
                if(cy == 0) moves.add(new Move(board.getTile(x, y), board.getTile(cx, cy), Move.PROMOTETAKE, player.game));
                else moves.add(new Move(board.getTile(x, y), board.getTile(cx, cy), Move.TAKE));
            }
            cx = x + 1;
            cy = y - 1;
            if(Tile.inBounds(cx, cy) && board.getTile(cx, cy).hasPiece() && !board.getTile(cx, cy).hasObstruction(color)){
                if(cy == 0) moves.add(new Move(board.getTile(x, y), board.getTile(cx, cy), Move.PROMOTETAKE, player.game));
                else moves.add(new Move(board.getTile(x, y), board.getTile(cx, cy), Move.TAKE));
            }
            
            cx = x + 1;
            cy = y;
            if(Tile.inBounds(cx, cy) && board.getTile(cx, cy).hasPiece && board.getTile(cx, cy).piece.name.equals("Pawn") && ((Pawn) board.getTile(cx, cy).piece).canBeEnpassant) moves.add(new Move(board.getTile(x, y), board.getTile(cx, cy - 1), board.getTile(cx, cy), Move.ENPASSANT));
            
            cx = x - 1;
            cy = y;
            if(Tile.inBounds(cx, cy) && board.getTile(cx, cy).hasPiece && board.getTile(cx, cy).piece.name.equals("Pawn") && ((Pawn) board.getTile(cx, cy).piece).canBeEnpassant) moves.add(new Move(board.getTile(x, y), board.getTile(cx, cy - 1), board.getTile(cx, cy), Move.ENPASSANT));
        }
        
        return moves;
    }
    
    public ArrayList<Move> getCompMoves(Game game){
        Board board = game.board;
        ArrayList<Move> moves = new ArrayList<>();
        if(color.equals("White")){
            int cx = x;
            int cy = y + 1;
            if(Tile.inBounds(cx, cy) && !board.getTile(cx, cy).hasPiece){
                if(cy == 7) moves.add(new Move(board.getTile(x, y), board.getTile(cx, cy), Move.PROMOTE, player.game));
                else moves.add(new Move(board.getTile(x, y), board.getTile(cx, cy), Move.MOVE));
                
                cx = x;
                cy = y + 2;
                if(canMoveTwo && Tile.inBounds(cx, cy) && !board.getTile(cx, cy).hasPiece) moves.add(new Move(board.getTile(x, y), board.getTile(cx, cy), Move.PAWNTWO));
            }
            cx = x - 1;
            cy = y + 1;
            if(Tile.inBounds(cx, cy) && board.getTile(cx, cy).hasPiece && !board.getTile(cx, cy).hasObstruction(color)){
                if(cy == 7) moves.add(new Move(board.getTile(x, y), board.getTile(cx, cy), Move.PROMOTETAKE, player.game));
                else moves.add(new Move(board.getTile(x, y), board.getTile(cx, cy), Move.TAKE));
            }
            cx = x + 1;
            cy = y + 1;
            if(Tile.inBounds(cx, cy) && board.getTile(cx, cy).hasPiece && !board.getTile(cx, cy).hasObstruction(color)){
                if(cy == 7) moves.add(new Move(board.getTile(x, y), board.getTile(cx, cy), Move.PROMOTETAKE, player.game));
                else moves.add(new Move(board.getTile(x, y), board.getTile(cx, cy), Move.TAKE));
            }
        } else {
            int cx = x;
            int cy = y - 1;
            if(Tile.inBounds(cx, cy) && !board.getTile(cx, cy).hasPiece){
                if(cy == 0) moves.add(new Move(board.getTile(x, y), board.getTile(cx, cy), Move.PROMOTE, player.game));
                else moves.add(new Move(board.getTile(x, y), board.getTile(cx, cy), Move.MOVE));
                
                cx = x;
                cy = y - 2;
                if(canMoveTwo && Tile.inBounds(cx, cy) && !board.getTile(cx, cy).hasPiece) moves.add(new Move(board.getTile(x, y), board.getTile(cx, cy), Move.PAWNTWO));
            }
            cx = x - 1;
            cy = y - 1;
            if(Tile.inBounds(cx, cy) && board.getTile(cx, cy).hasPiece() && !board.getTile(cx, cy).hasObstruction(color)){
                if(cy == 0) moves.add(new Move(board.getTile(x, y), board.getTile(cx, cy), Move.PROMOTETAKE, player.game));
                else moves.add(new Move(board.getTile(x, y), board.getTile(cx, cy), Move.TAKE));
            }
            cx = x + 1;
            cy = y - 1;
            if(Tile.inBounds(cx, cy) && board.getTile(cx, cy).hasPiece() && !board.getTile(cx, cy).hasObstruction(color)){
                if(cy == 0) moves.add(new Move(board.getTile(x, y), board.getTile(cx, cy), Move.PROMOTETAKE, player.game));
                else moves.add(new Move(board.getTile(x, y), board.getTile(cx, cy), Move.TAKE));
            }
        }
        
        return moves;
    }
    
    public ArrayList<Move> getCheckingMoves(Game game){
        return getMoves(game);
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public String toString(){
        switch(color){
            case "Black": return "♟";
            default: return "♙";
        }
    }
    
    public static String toString(String color){
        switch(color){
            case "Black": return "♟";
            default: return "♙";
        }
    }
}
