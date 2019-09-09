package ticTacToe;

import javax.swing.*;
import java.awt.*;

public class Board extends JPanel {
    
    Square[] board;
    
    Game game;
    
    int mode;
    
    public Board(Game game, int mode){
        this.game = game;
        this.mode = mode;
        setLayout(new GridLayout(3, 3));
        board = new Square[9];
        for(int i = 0; i < 9; i++){
            board[i] = new Square(game, i);
            add(board[i]);
        }
        update();
    }
    
    public void update(){
        for(Square s : board){
            s.update();
        }
    }
    
    public boolean setPiece(int i, String turn){
        if(!board[i].hasPiece()){
            board[i].setPiece(turn);
            return true;
        }
        return false;
    }
    
    public void computerMove(String turn){
        int move = Computer.getComputerMove(convertBoard(this), 1, true, turn);
        System.out.println(move);
        setPiece(move, turn);
    }
    
    public static String[] convertBoard(Board Board){
        Square[] board = Board.board;
        String[] returnArr = new String[9];
        for(int i = 0; i < 9; i++){
            returnArr[i] = board[i].character;
        }
        return returnArr;
    }
    
}
