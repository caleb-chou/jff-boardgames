package chess;

import java.util.*;

public class Player {
    ArrayList<Piece> allPieces;
    ArrayList<Piece> pieces;
    ArrayList<Piece> taken;
    
    int piecesValue;
    
    String color;
    
    Game game;
    
    public Player(String color, Game game){
        allPieces = new ArrayList<>();
        pieces = new ArrayList<>();
        taken = new ArrayList<>();
        this.color = color;
        this.game = game;
    }
    
    public void addPiece(Piece piece){
        allPieces.add(piece);
    }
    
    public ArrayList<Piece> getPieces(){
        return pieces;
    }
    
    public King getKing(){
        for(Piece piece : pieces){
            if(piece.name.equals("King")) return (King) piece;
        }
        return new King(this, -1, -1);
    }
    
    public void takePieces(){
        sortPieces();
        pieces = new ArrayList<>();
        taken = new ArrayList<>();
        for(Piece p : allPieces){
            if(!p.isTaken) pieces.add(p);
            else taken.add(p);
        }
    }
    
    public void removePieces(){
        ArrayList<Piece> piecesRemaining = new ArrayList<>();
        for(Piece p : allPieces){
            if(!p.remove) piecesRemaining.add(p);
        }
        allPieces = piecesRemaining;
        takePieces();
    }
    
    public void sortPieces(){
        ArrayList<Piece> sorted = new ArrayList<>();
        for(Piece p : allPieces){
            if(p.name.equals("Pawn")) sorted.add(p);
        }
        for(Piece p : allPieces){
            if(p.name.equals("Knight")) sorted.add(p);
        }
        for(Piece p : allPieces){
            if(p.name.equals("Bishop")) sorted.add(p);
        }
        for(Piece p : allPieces){
            if(p.name.equals("Rook")) sorted.add(p);
        }
        for(Piece p : allPieces){
            if(p.name.equals("Queen")) sorted.add(p);
        }
        for(Piece p : allPieces){
            if(p.name.equals("King")) sorted.add(p);
        }
        allPieces = sorted;
    }
    
    public int calculateValue(){
        piecesValue = 0;
        for(Piece p : pieces){
            piecesValue += p.value;
        }
        return piecesValue;
    }
}
