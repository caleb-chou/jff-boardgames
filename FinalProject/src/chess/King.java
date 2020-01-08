package chess;

import java.awt.image.*;
import java.util.*;
import finalproject.*;

public class King extends Piece {
    public static final int[][] NORMAL_MOVES = {{1, 0}, {1, 1}, {0, 1}, {-1, 1}, {-1, 0}, {-1, -1}, {0, -1}, {1, -1}};
    
    boolean hasMoved;
    
    boolean inCheck;
    
    public King(Player player, int x, int y){
        super("King", player, x, y, 0);
        hasMoved = false;
        inCheck = false;
    }
    
    public BufferedImage getIcon(){
        switch(color){
            case "Black": return Resources.getImage("Black_King.png");
            default: return Resources.getImage("White_King.png");
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
        if(inCheck) return moves;
        if(color.equals("White")){
            Tile kingSide = board.getTile(7,0);
            if(!hasMoved && kingSide.hasPiece && kingSide.piece.name.equals("Rook") && !board.getTile(5, 0).hasPiece && !board.getTile(6, 0).hasPiece && !((Rook) kingSide.piece).hasMoved){
                if(Move.validMove(new Move(board.getTile(4, 0), board.getTile(5, 0), Move.MOVE), game) && Move.validMove(new Move(board.getTile(4, 0), board.getTile(6, 0), Move.MOVE), game)) moves.add(new Move(board.getTile(x, y), board.getTile(6, 0), board.getTile(7, 0), Move.KING_SIDE_CASTLE));
            }
            Tile queenSide = board.getTile(0,0);
            if(!hasMoved && queenSide.hasPiece && queenSide.piece.name.equals("Rook") && !board.getTile(3, 0).hasPiece && !board.getTile(2, 0).hasPiece && !board.getTile(1, 0).hasPiece && !((Rook) queenSide.piece).hasMoved){
                if(Move.validMove(new Move(board.getTile(4, 0), board.getTile(3, 0), Move.MOVE), game) && Move.validMove(new Move(board.getTile(4, 0), board.getTile(2, 0), Move.MOVE), game) && Move.validMove(new Move(board.getTile(4, 0), board.getTile(1, 0), Move.MOVE), game)) moves.add(new Move(board.getTile(x, y), board.getTile(2, 0), board.getTile(0, 0), Move.QUEEN_SIDE_CASTLE));
            }
        } else {
            Tile kingSide = board.getTile(7, 7);
            if(!hasMoved && kingSide.hasPiece && kingSide.piece.name.equals("Rook") && !board.getTile(5, 7).hasPiece && !board.getTile(6, 7).hasPiece && !((Rook) kingSide.piece).hasMoved){
                if(Move.validMove(new Move(board.getTile(4, 7), board.getTile(5, 7), Move.MOVE), game) && Move.validMove(new Move(board.getTile(4, 7), board.getTile(6, 7), Move.MOVE), game)) moves.add(new Move(board.getTile(x, y), board.getTile(6, 7), board.getTile(7, 7), Move.KING_SIDE_CASTLE));
            }
            Tile queenSide = board.getTile(0, 7);
            if(!hasMoved && queenSide.hasPiece && queenSide.piece.name.equals("Rook") && !board.getTile(3, 7).hasPiece && !board.getTile(2, 7).hasPiece && !board.getTile(1, 7).hasPiece && !((Rook) queenSide.piece).hasMoved){
                if(Move.validMove(new Move(board.getTile(4, 7), board.getTile(3, 7), Move.MOVE), game) && Move.validMove(new Move(board.getTile(4, 7), board.getTile(2, 7), Move.MOVE), game) && Move.validMove(new Move(board.getTile(4, 7), board.getTile(1, 7), Move.MOVE), game)) moves.add(new Move(board.getTile(x, y), board.getTile(2, 7), board.getTile(0, 7), Move.QUEEN_SIDE_CASTLE));
            }
        }
        return moves;
    }
    
    public ArrayList<Move> getCompMoves(Game game){
        return getMoves(game);
    }
    
    public ArrayList<Move> getCheckingMoves(Game game){
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
    
    
    public String toString(){
        switch(color){
            case "Black": return "♚";
            default: return "♔";
        }
    }
    
    public static String toString(String color){
        switch(color){
            case "Black": return "♚";
            default: return "♔";
        }
    }
}
