package chess;

import finalproject.Resources;
import java.awt.event.*;

public class Move implements ActionListener{
    public static final int MOVE = 0;
    public static final int TAKE = 1;
    public static final int KING_SIDE_CASTLE = 2;
    public static final int QUEEN_SIDE_CASTLE = 3;
    public static final int ENPASSANT = 4;
    public static final int PAWNTWO = 5;
    public static final int ROOKMOVE = 6;
    public static final int ROOKTAKE = 7;
    public static final int KINGMOVE = 8;
    public static final int KINGTAKE = 9;
    public static final int PROMOTE = 10;
    public static final int PROMOTETAKE = 11;
    
    int move;
    
    Tile orig;
    Tile moveTo;
    Tile special;
    
    Piece origP;
    Piece moveP;
    Piece specialP;
    
    PromoteWindow pw;
    
    Game game;
    
    Pawn enP;
    boolean cancelledEnPassant = false;
    
    public Move(Tile orig, Tile moveTo, int move){
        this.move = move;
        this.orig = orig;
        this.moveTo = moveTo;
        origP = orig.getPiece();
        if(moveTo.hasPiece) moveP = moveTo.getPiece();
        if(move == MOVE && origP.name.equals("Rook") && !((Rook) origP).hasMoved) this.move = ROOKMOVE;
        if(move == TAKE && origP.name.equals("Rook") && !((Rook) origP).hasMoved) this.move = ROOKTAKE;
        if(move == MOVE && origP.name.equals("King") && !((King) origP).hasMoved) this.move = KINGMOVE;
        if(move == TAKE && origP.name.equals("King") && !((King) origP).hasMoved) this.move = KINGTAKE;
    }
    
    public Move(Tile orig, Tile moveTo, int move, Game game){
        this.move = move;
        this.orig = orig;
        this.moveTo = moveTo;
        this.game = game;
        origP = orig.getPiece();
        if(moveTo.hasPiece) moveP = moveTo.getPiece();
    }
    
    public Move(Tile orig, Tile moveTo, Tile special, int move){
        this.move = move;
        this.orig = orig;
        this.special = special;
        this.moveTo = moveTo;
        origP = orig.getPiece();
        if(moveTo.hasPiece) moveP = moveTo.getPiece();
        specialP = special.getPiece();
    } 
    
    public void cancelledEnPassant(Pawn piece){
        this.cancelledEnPassant = true;
        this.enP = piece;
    }
    
    public void move(){
        String audio = "none";
        switch(move){
            case MOVE: audio = "Chess_Piece_Moved.wav"; break;
            case TAKE: audio = "Chess_Piece_Take.wav"; break;
            case KING_SIDE_CASTLE: audio = "Chess_Piece_Moved.wav"; break;
            case QUEEN_SIDE_CASTLE: audio = "Chess_Piece_Moved.wav"; break;
            case ENPASSANT: audio = "Chess_Piece_Take.wav"; break;
            case PAWNTWO: audio = "Chess_Piece_Moved.wav"; break;
            case ROOKMOVE: audio = "Chess_Piece_Moved.wav"; break;
            case ROOKTAKE: audio = "Chess_Piece_Take.wav"; break;
            case KINGMOVE: audio = "Chess_Piece_Moved.wav"; break;
            case KINGTAKE: audio = "Chess_Piece_Take.wav"; break;
            case PROMOTE: audio = "Chess_Piece_Moved.wav"; break;
            case PROMOTETAKE: audio = "Chess_Piece_Take.wav"; break;
        }
        Resources.playAudio(audio);
        origP.move(moveTo.x, moveTo.y);
        if(move == TAKE || move == ROOKTAKE || move == KINGTAKE || move == PROMOTETAKE) moveP.take();
        if(move == ROOKMOVE || move == ROOKTAKE) ((Rook) origP).hasMoved = true;
        if(move == KINGMOVE || move == KINGTAKE) ((King) origP).hasMoved = true;
        if(move == KING_SIDE_CASTLE){
            if(origP.color.equals("White")){
                ((King) origP).hasMoved = true;
                specialP.move(5, 0);
            } else {
                ((King) origP).hasMoved = true;
                specialP.move(5, 7);
            }
        }
        if(move == QUEEN_SIDE_CASTLE){
            if(origP.color.equals("White")){
                ((King) origP).hasMoved = true;
                specialP.move(3, 0);
            } else {
                ((King) origP).hasMoved = true;
                specialP.move(3, 7);
            }
        }
        if(move == ENPASSANT) specialP.take();
        if(origP.name.equals("Pawn")) ((Pawn) origP).canMoveTwo = false;
        if(move == PAWNTWO) ((Pawn) origP).canBeEnpassant = true;
        if(move == PROMOTE){
            game.stopInput = true;
            pw = new PromoteWindow(this);
        }
        if(move == PROMOTETAKE){
            moveP.take();
            game.stopInput = true;
            pw = new PromoteWindow(this);
        }
    }
    
    public void move1(){
        origP.move(moveTo.x, moveTo.y);
        if(move == TAKE || move == ROOKTAKE || move == KINGTAKE || move == PROMOTETAKE) moveP.take();
        if(move == ROOKMOVE || move == ROOKTAKE) ((Rook) origP).hasMoved = true;
        if(move == KINGMOVE || move == KINGTAKE) ((King) origP).hasMoved = true;
        if(move == KING_SIDE_CASTLE){
            if(origP.color.equals("White")){
                ((King) origP).hasMoved = true;
                specialP.move(5, 0);
            } else {
                ((King) origP).hasMoved = true;
                specialP.move(5, 7);
            }
        }
        if(move == QUEEN_SIDE_CASTLE){
            if(origP.color.equals("White")){
                ((King) origP).hasMoved = true;
                specialP.move(3, 0);
            } else {
                ((King) origP).hasMoved = true;
                specialP.move(3, 7);
            }
        }
        if(move == ENPASSANT) specialP.take();
        if(origP.name.equals("Pawn")) ((Pawn) origP).canMoveTwo = false;
        if(move == PAWNTWO) ((Pawn) origP).canBeEnpassant = true;
        if(move == PROMOTE){
            origP.take();
            promoted = new Queen(origP.player, origP.x, origP.y);
            origP.player.addPiece(promoted);
        }
        if(move == PROMOTETAKE){
            moveP.take();
            origP.take();
            promoted = new Queen(origP.player, origP.x, origP.y);
            origP.player.addPiece(promoted);
        }
    }
    
    public void move2(){
        origP.move(moveTo.x, moveTo.y);
        if(move == TAKE || move == ROOKTAKE || move == KINGTAKE || move == PROMOTETAKE) moveP.take();
        if(move == ROOKMOVE || move == ROOKTAKE) ((Rook) origP).hasMoved = true;
        if(move == KINGMOVE || move == KINGTAKE) ((King) origP).hasMoved = true;
        if(move == KING_SIDE_CASTLE){
            if(origP.color.equals("White")){
                ((King) origP).hasMoved = true;
                specialP.move(5, 0);
            } else {
                ((King) origP).hasMoved = true;
                specialP.move(5, 7);
            }
        }
        if(move == QUEEN_SIDE_CASTLE){
            if(origP.color.equals("White")){
                ((King) origP).hasMoved = true;
                specialP.move(3, 0);
            } else {
                ((King) origP).hasMoved = true;
                specialP.move(3, 7);
            }
        }
        if(origP.name.equals("Pawn")) ((Pawn) origP).canMoveTwo = false;
        if(move == PROMOTE){
            origP.take();
            promoted = new Queen(origP.player, origP.x, origP.y);
            origP.player.addPiece(promoted);
        }
        if(move == PROMOTETAKE){
            moveP.take();
            origP.take();
            promoted = new Queen(origP.player, origP.x, origP.y);
            origP.player.addPiece(promoted);
        }
    }
    
    public void undo(){
        String audio = "none";
        switch(move){
            case MOVE: audio = "Chess_Piece_Moved.wav"; break;
            case TAKE: audio = "Chess_Piece_Take.wav"; break;
            case KING_SIDE_CASTLE: audio = "Chess_Piece_Moved.wav"; break;
            case QUEEN_SIDE_CASTLE: audio = "Chess_Piece_Moved.wav"; break;
            case ENPASSANT: audio = "Chess_Piece_Take.wav"; break;
            case PAWNTWO: audio = "Chess_Piece_Moved.wav"; break;
            case ROOKMOVE: audio = "Chess_Piece_Moved.wav"; break;
            case ROOKTAKE: audio = "Chess_Piece_Take.wav"; break;
            case KINGMOVE: audio = "Chess_Piece_Moved.wav"; break;
            case KINGTAKE: audio = "Chess_Piece_Take.wav"; break;
            case PROMOTE: audio = "Chess_Piece_Moved.wav"; break;
            case PROMOTETAKE: audio = "Chess_Piece_Take.wav"; break;
        }
        Resources.playAudio(audio);
        origP.move(orig.x, orig.y);
        if(move == TAKE || move == ROOKTAKE || move == KINGTAKE) moveP.unTake();
        if(move == ROOKMOVE || move == ROOKTAKE) ((Rook) origP).hasMoved = false;
        if(move == KINGMOVE || move == KINGTAKE) ((King) origP).hasMoved = false;
        if(move == KING_SIDE_CASTLE){
            if(origP.color.equals("White")){
                ((King) origP).hasMoved = false;
                specialP.move(7, 0);
            } else {
                ((King) origP).hasMoved = false;
                specialP.move(7, 7);
            }
        }
        if(move == QUEEN_SIDE_CASTLE){
            if(origP.color.equals("White")){
                ((King) origP).hasMoved = false;
                specialP.move(0, 0);
            } else {
                ((King) origP).hasMoved = false;
                specialP.move(0, 7);
            }
        }
        if(move == ENPASSANT){
            specialP.unTake();
            ((Pawn) specialP).canBeEnpassant = true;
        }
        if(origP.name.equals("Pawn")){
            if(origP.color.equals("White")){
                if(orig.y == 1) ((Pawn) origP).canMoveTwo = true;
            } else {
                if(orig.y == 6) ((Pawn) origP).canMoveTwo = true;
            }
        }
        if(move == PAWNTWO) ((Pawn) origP).canBeEnpassant = false;
        if(move == PROMOTE){
            promoted.remove();
            origP.unTake();
        }
        if(move == PROMOTETAKE){
            promoted.remove();
            origP.unTake();
            moveP.unTake();
        }
        if(cancelledEnPassant){
            enP.canBeEnpassant = true;
        }
    }
    
    public void undo1(){
        origP.move(orig.x, orig.y);
        if(move == TAKE || move == ROOKTAKE || move == KINGTAKE) moveP.unTake();
        if(move == ROOKMOVE || move == ROOKTAKE) ((Rook) origP).hasMoved = false;
        if(move == KINGMOVE || move == KINGTAKE) ((King) origP).hasMoved = false;
        if(move == KING_SIDE_CASTLE){
            if(origP.color.equals("White")){
                ((King) origP).hasMoved = false;
                specialP.move(7, 0);
            } else {
                ((King) origP).hasMoved = false;
                specialP.move(7, 7);
            }
        }
        if(move == QUEEN_SIDE_CASTLE){
            if(origP.color.equals("White")){
                ((King) origP).hasMoved = false;
                specialP.move(0, 0);
            } else {
                ((King) origP).hasMoved = false;
                specialP.move(0, 7);
            }
        }
        if(move == ENPASSANT){
            specialP.unTake();
            ((Pawn) specialP).canBeEnpassant = true;
        }
        if(origP.name.equals("Pawn")){
            if(origP.color.equals("White")){
                if(orig.y == 1) ((Pawn) origP).canMoveTwo = true;
            } else {
                if(orig.y == 6) ((Pawn) origP).canMoveTwo = true;
            }
        }
        if(move == PAWNTWO) ((Pawn) origP).canBeEnpassant = false;
        if(move == PROMOTE){
            promoted.remove();
            origP.unTake();
        }
        if(move == PROMOTETAKE){
            promoted.remove();
            origP.unTake();
            moveP.unTake();
        }
        if(cancelledEnPassant){
            enP.canBeEnpassant = true;
        }
    }
    
    public void undo2(){
        origP.move(orig.x, orig.y);
        if(move == TAKE || move == ROOKTAKE || move == KINGTAKE) moveP.unTake();
        if(move == ROOKMOVE || move == ROOKTAKE) ((Rook) origP).hasMoved = false;
        if(move == KINGMOVE || move == KINGTAKE) ((King) origP).hasMoved = false;
        if(move == KING_SIDE_CASTLE){
            if(origP.color.equals("White")){
                ((King) origP).hasMoved = false;
                specialP.move(7, 0);
            } else {
                ((King) origP).hasMoved = false;
                specialP.move(7, 7);
            }
        }
        if(move == QUEEN_SIDE_CASTLE){
            if(origP.color.equals("White")){
                ((King) origP).hasMoved = false;
                specialP.move(0, 0);
            } else {
                ((King) origP).hasMoved = false;
                specialP.move(0, 7);
            }
        }
        if(origP.name.equals("Pawn")){
            if(origP.color.equals("White")){
                if(orig.y == 1) ((Pawn) origP).canMoveTwo = true;
            } else {
                if(orig.y == 6) ((Pawn) origP).canMoveTwo = true;
            }
        }
        if(move == PROMOTE){
            promoted.remove();
            origP.unTake();
        }
        if(move == PROMOTETAKE){
            promoted.remove();
            origP.unTake();
            moveP.unTake();
        }
    }
    
    public void makeLastMove(){
        orig.makeLastMove();
        moveTo.makeLastMove();
    }
    
    public String toString(){
        switch(move){
            case TAKE: return origP.toString() + orig.toString() + "x" + moveTo.toString();
            case ENPASSANT: return origP.toString() + orig.toString() + "x" + special.toString();
            case KING_SIDE_CASTLE: return origP.toString() + "O-O";
            case QUEEN_SIDE_CASTLE: return origP.toString() + "O-O-O";
            case PROMOTE: if(chose){
                            return origP.toString() + orig.toString() + "-" + moveTo.toString() + "=" + promoted.toString();
                          } else {
                            return origP.toString() + orig.toString() + "-" + moveTo.toString() + "=?";
                          }
            case PROMOTETAKE: if(chose){
                                return origP.toString() + orig.toString() + "x" + moveTo.toString() + "=" + promoted.toString();
                              } else {
                                return origP.toString() + orig.toString() + "x" + moveTo.toString() + "=?";
                              }
            case KINGTAKE: return origP.toString() + orig.toString() + "x" + moveTo.toString();
            case ROOKTAKE: return origP.toString() + orig.toString() + "x" + moveTo.toString();
            default: return origP.toString() + orig.toString() + "-" + moveTo.toString();
        }
    }
    
    public static boolean validMove(Move move, Game game){
        move.move1();
        game.board.updatePieces(game.white, game.black);
        if(game.inCheck(game.turn)){
            move.undo1();
            game.board.updatePieces(game.white, game.black);
            return false;
        }
        move.undo1();
        game.board.updatePieces(game.white, game.black);
        return true;
    }
    
    public static boolean validCompMove(Move move, Game game){
        move.move2();
        game.board.updatePieces(game.white, game.black);
        if(game.inCheck(game.turn)){
            move.undo2();
            game.board.updatePieces(game.white, game.black);
            return false;
        }
        move.undo2();
        game.board.updatePieces(game.white, game.black);
        return true;
    }
    
    boolean chose = false;
    Piece promoted;
    
    public void actionPerformed(ActionEvent ae){
        String command = ae.getActionCommand();
        switch(command){
            case "Queen": origP.take();
                          promoted = new Queen(origP.player, origP.x, origP.y);
                          origP.player.addPiece(promoted); break;
            case "Rook": origP.take();
                         promoted = new Rook(origP.player, origP.x, origP.y);
                         origP.player.addPiece(promoted); break;
            case "Bishop": origP.take();
                           promoted = new Bishop(origP.player, origP.x, origP.y);
                           origP.player.addPiece(promoted); break;
            case "Knight": origP.take();
                           promoted = new Knight(origP.player, origP.x, origP.y);
                           origP.player.addPiece(promoted); break;
                      
        }
        pw.dispose();
        chose = true;
        game.moveList.update();
        game.stopInput = false;
        game.setUpTurn();
    }
    
    public boolean isTaking(){
        return move == TAKE || move == KINGTAKE || move == ROOKTAKE || move == ENPASSANT;
    }
    
}
