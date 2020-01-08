package chess;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import finalproject.*;

public class Game extends JFrame implements ActionListener {
    public static final Color color = new Color(80, 80, 80);
    
    Board board;
    MoveList moveList;
    End endFrame;
    
    Player white;
    Player black;
    
    String turn;
    
    HashMap<Piece, ArrayList<Move>> moves;
    HashMap<Piece, ArrayList<Move>> compMoves;
    
    ArrayList<Move> gameMoves;
    
    int currentMove;
    
    boolean isEnded;
    
    public Game(){
        isEnded = false;
        currentMove = 0;
        stopInput = false;
        initializeGame();
        initializeUI();
        startGame();
    }
    
    private void initializeUI(){
        setBounds(0, 0, 8 * Tile.SIZE + 200, 8 * Tile.SIZE);
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Chess");
        setIconImage(Resources.getImage("Chess_Icon.png"));
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.LINE_AXIS));
        setBackground(color);
        
            board = new Board(8, 8, this);
            
            moveList = new MoveList(gameMoves, this);
            
        add(board);
        add(moveList);
        pack();
        setVisible(true);
    }
    
    private void initializeGame(){
        white = new Player("White", this);
        black = new Player("Black", this);
        givePieces();
        gameMoves = new ArrayList<>();
    }
    
    private void givePieces(){
        
        for(int i = 0; i < 8; i++){
            white.addPiece(new Pawn(white, i, 1));
        }
        white.addPiece(new Rook(white, 0, 0));
        white.addPiece(new Knight(white, 1, 0));
        white.addPiece(new Bishop(white, 2, 0));
        white.addPiece(new Queen(white, 3, 0));
        white.addPiece(new King(white, 4, 0));
        white.addPiece(new Bishop(white, 5, 0));
        white.addPiece(new Knight(white, 6, 0));
        white.addPiece(new Rook(white, 7, 0));
        for(int i = 0; i < 8; i++){
            black.addPiece(new Pawn(black, i, 6));
        }
        black.addPiece(new Rook(black, 0, 7));
        black.addPiece(new Knight(black, 1, 7));
        black.addPiece(new Bishop(black, 2, 7));
        black.addPiece(new Queen(black, 3, 7));
        black.addPiece(new King(black, 4, 7));
        black.addPiece(new Bishop(black, 5, 7));
        black.addPiece(new Knight(black, 6, 7));
        black.addPiece(new Rook(black, 7, 7));
        /**
        white.addPiece(new Pawn(white, 0, 3));
        white.addPiece(new Rook(white, 1, 0));
        white.addPiece(new Pawn(white, 7, 1));
        black.addPiece(new Pawn(black, 1, 4));
        black.addPiece(new Rook(black, 1, 7));
        black.addPiece(new Pawn(black, 7, 6));
        * **/
    }
    
    public void startGame(){
        Resources.playAudio("Start_End.wav");
        turn = "Black";
        startNextTurn();
    }
    
    public void startNextTurn(){
        switchTurn();
        setUpTurn();
    }
    
    boolean willCancelEnPassant;
    Pawn enP;
    
    public void setUpTurn(){
        board.resetTiles();
        if(currentMove > 0) gameMoves.get(currentMove - 1).makeLastMove();
        willCancelEnPassant = false;
        if(turn.equals("White")){
            for(Piece piece : white.pieces) {
                if(piece.name.equals("Pawn")){
                    Pawn pawn = (Pawn) piece;
                    if(pawn.canBeEnpassant){
                        pawn.canBeEnpassant = false;
                        willCancelEnPassant = true;
                        enP = pawn;
                    }
                }
                if(piece.name.equals("King")){
                    King king = (King) piece;
                    king.inCheck = false;
                }
            }
        } else {
            for(Piece piece : black.pieces) {
                if(piece.name.equals("Pawn")){
                    Pawn pawn = (Pawn) piece;
                    if(pawn.canBeEnpassant){
                        pawn.canBeEnpassant = false;
                        willCancelEnPassant = true;
                        enP = pawn;
                    }
                }
                if(piece.name.equals("King")){
                    King king = (King) piece;
                    king.inCheck = false;
                }
            }
        }
        if(willCancelEnPassant){
            gameMoves.get(currentMove - 1).cancelledEnPassant(enP);
        }
        board.updatePieces(white, black);
        if(inCheck("White")){
            for(Piece piece : white.pieces){
                if(piece.name.equals("King")){
                    ((King) piece).inCheck = true;
                    board.makeCheck(piece.x, piece.y);
                }
            }
        }
        if(inCheck("Black")){
            for(Piece piece : black.pieces){
                if(piece.name.equals("King")){
                    ((King) piece).inCheck = true;
                    board.makeCheck(piece.x, piece.y);
                }
            }
        }
        updateMoves();
        board.update();
        int end = checkEnd();
        if(end != NOT_ENDED){
            endFrame = new End(this, end);
            isEnded = true;
            return;
        }
        moveList.update();
    }
    
    public void updateMoves(){
        moves = validateMoves(turn);
    }
    
    public void updateCompMoves(){
        compMoves = validateCompMoves(turn);
    }
    
    public Player getCurrentPlayer(){
        if(turn.equals("White")) return white;
        else return black;
    }
    
    public Player getCurrentOpponent(){
        if(turn.equals("Black")) return white;
        else return black;
    }
    
    public HashMap<Piece, ArrayList<Move>> validateMoves(String color){
        HashMap<Piece, ArrayList<Move>> moves = new HashMap<>();
        if(color.equals("White")){
            for(Piece piece : white.getPieces()){
                ArrayList<Move> moveList = piece.getMoves(this);
                ArrayList<Move> validMoves = new ArrayList<>();
                for(Move move : moveList){
                    if(Move.validMove(move, this)) validMoves.add(move);
                }
                moves.put(piece, validMoves);
            }
        } else {
            for(Piece piece : black.getPieces()){
                ArrayList<Move> moveList = piece.getMoves(this);
                ArrayList<Move> validMoves = new ArrayList<>();
                for(Move move : moveList){
                    if(Move.validMove(move, this)) validMoves.add(move);
                }
                moves.put(piece, validMoves);
            }
        }
        return moves;
    }
    
    public HashMap<Piece, ArrayList<Move>> validateCompMoves(String color){
        HashMap<Piece, ArrayList<Move>> moves = new HashMap<>();
        if(color.equals("White")){
            for(Piece piece : white.getPieces()){
                ArrayList<Move> moveList = piece.getCompMoves(this);
                ArrayList<Move> validMoves = new ArrayList<>();
                for(Move move : moveList){
                    if(Move.validCompMove(move, this)) validMoves.add(move);
                }
                moves.put(piece, validMoves);
            }
        } else {
            for(Piece piece : black.getPieces()){
                ArrayList<Move> moveList = piece.getCompMoves(this);
                ArrayList<Move> validMoves = new ArrayList<>();
                for(Move move : moveList){
                    if(Move.validCompMove(move, this)) validMoves.add(move);
                }
                moves.put(piece, validMoves);
            }
        }
        return moves;
    }
    
    public HashMap<Piece, ArrayList<Move>> getMoves(String color){
        HashMap<Piece, ArrayList<Move>> moves = new HashMap<>();
        if(color.equals("White")){
            for(Piece piece : white.getPieces()){
                moves.put(piece, piece.getMoves(this));
            }
        } else {
            for(Piece piece : black.getPieces()){
                moves.put(piece, piece.getMoves(this));
            }
        }
        return moves;
    }
    
    public HashMap<Piece, ArrayList<Move>> getCheckingMoves(String color){
        HashMap<Piece, ArrayList<Move>> moves = new HashMap<>();
        if(color.equals("White")){
            for(Piece piece : white.getPieces()){
                moves.put(piece, piece.getCheckingMoves(this));
            }
        } else {
            for(Piece piece : black.getPieces()){
                moves.put(piece, piece.getCheckingMoves(this));
            }
        }
        return moves;
    }
    
    public String otherTurn(String color){
        if(color == "White") return "Black";
        return "White";
    }
    
    public void switchTurn(){
        turn = otherTurn(turn);
    }
    
    public Player getOpponent(Player player){
        if(player.color.equals("White")) return black;
        else return white;
    }
    
    boolean stopInput;
    
    public void actionPerformed(ActionEvent ae){
        String command = ae.getActionCommand();
        switch(command){
            case "RestartE": restart();
                             endFrame.dispose();
                             return;
            case "UndoE": undoMove();
                          endFrame.dispose();
                          isEnded = false;
                          return;
            case "AbortE": endFrame.dispose();
                           dispose(); return;
        }
        if(stopInput || isEnded) return;
        switch(command){
            case "COMPUTER": Move move = Computer.getMove(this);
                             move.move1();
                             gameMoves.add(move);
                             currentMove++;
                             board.updatePieces(white, black);
                             startNextTurn(); return;
            case "Undo": undoMove(); return;
            case "Draw": endFrame = new End(this, DRAW);
                         isEnded = true; return;
            case "Resign": if(turn.equals("White")) endFrame = new End(this, WHITE_RESIGN);
                           else endFrame = new End(this, BLACK_RESIGN);
                           isEnded = true; return;
        }
        int x = command.charAt(0) - 48;
        int y = command.charAt(1) - 48;
        Tile tile = board.getTile(x, y);
        if(tile.hasPiece && tile.getPiece().color.equals(turn)){
            board.selectTile(tile);
            board.updateMoves(moves);
            board.update();
        }
        if(tile.isMove || tile.isTake){
            for(Move move : moves.get(board.selectedTile.getPiece())){
                if(move.moveTo.equals(tile)){
                    move.move();
                    gameMoves.add(move);
                    currentMove++;
                    board.updatePieces(white, black);
                }
            }
            startNextTurn();
        }
    }
    
    public void undoMove(){
        if(currentMove == 0) return;
        gameMoves.get(currentMove - 1).undo();
        gameMoves.remove(currentMove - 1);
        currentMove--;
        board.updatePieces(white, black);
        moveList.update();
        startNextTurn();
    }
    
    public boolean inCheck(String color){
        if(color.equals("White")){
            King whiteKing = white.getKing();
            HashMap<Piece, ArrayList<Move>> blackMoves = getCheckingMoves("Black");
            for(Piece piece : blackMoves.keySet()){
                for(Move move : blackMoves.get(piece)){
                    if((move.move == Move.TAKE || move.move == Move.ROOKTAKE || move.move == Move.KINGTAKE || move.move == Move.PROMOTETAKE) && move.moveP.equals(whiteKing)) return true;
                }
            }
        } else {
            King blackKing = black.getKing();
            HashMap<Piece, ArrayList<Move>> whiteMoves = getCheckingMoves("White");
            for(Piece piece : whiteMoves.keySet()){
                for(Move move : whiteMoves.get(piece)){
                    if((move.move == Move.TAKE || move.move == Move.ROOKTAKE || move.move == Move.KINGTAKE || move.move == Move.PROMOTETAKE) && move.moveP.equals(blackKing)) return true;
                }
            }
        }
        return false;
    }
    
    public static void printBoard(Game game){
        Board b = game.board;
        Tile[][] board = b.board;
        for(Tile[] row : board){
            for(Tile t : row){
                if(t.hasPiece) System.out.print(t.piece);
                else System.out.print("-");
            }
            System.out.println();
        }
    }
    
    public static final int NOT_ENDED = 0;
    public static final int WHITE_CHECKMATE = 1;
    public static final int BLACK_CHECKMATE = 2;
    public static final int STALEMATE = 3;
    public static final int DRAW = 4;
    public static final int WHITE_RESIGN = 5;
    public static final int BLACK_RESIGN = 6;
    
    public int checkEnd(){
        boolean noMoves = false;
        for(Piece p : moves.keySet()){
            if(!moves.get(p).isEmpty()) noMoves = true;
        }
        if(noMoves) return NOT_ENDED;
        if(inCheck(turn)){
            if(turn.equals("White")) return WHITE_CHECKMATE;
            else return BLACK_CHECKMATE;
        }
        return STALEMATE;
    }
    
    public void restart(){
        setVisible(false);
        getContentPane().removeAll();
        revalidate();
        isEnded = false;
        currentMove = 0;
        stopInput = false;
        initializeGame();
        initializeUI();
        startGame();
    }
}
