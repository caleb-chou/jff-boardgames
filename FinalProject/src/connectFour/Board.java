package connectFour;

import java.awt.*;
import javax.swing.*;

public class Board extends JPanel {
    
    Square[][] board;
    
    int mode;
    
    Game game;
    
    public Board(Game game){
        this.game = game;
        mode = Main.PVP;
        initializeUI();
        board = new Square[6][7];
        for(int r = 0; r < board.length; r++){
            for(int c = 0; c < board[r].length; c++){
                board[r][c] = new Square(c);
                board[r][c].addActionListener(game);
                add(board[r][c]);
            }
        }
        update();
    }
    public Board(Game game, int mode){
        this.game = game;
        this.mode = mode;
        initializeUI();
        board = new Square[6][7];
        for(int r = 0; r < board.length; r++){
            for(int c = 0; c < board[r].length; c++){
                board[r][c] = new Square(c);
                board[r][c].addActionListener(game);
                add(board[r][c]);
            }
        }
        update();
    }
    
    public void restart(){
        for(int r = 0; r < board.length; r++){
            for(int c = 0; c < board[r].length; c++){
                board[r][c].restart();
            }
        }
        update();
    }
    
    private void initializeUI(){
        setLayout(new GridLayout(6, 7));
    }
    
    public boolean addPiece(int c, String turn){
        Square[] column = Board.getColumn(board, c);
        int i = 0;
        while(i < column.length && !column[i].hasPiece) i++;
        if(i == 0) return false;
        switch(turn){
            case "RED": column[i - 1].addPiece(new RedPiece()); break;
            case "YELLOW": column[i - 1].addPiece(new YellowPiece()); break;
            default: column[i - 1].addPiece(new RedPiece()); break;
        }
        return true;
    }
    
    public static void addPiece(Square[][] board, int c, String turn){
        Square[] column = Board.getColumn(board, c);
        int i = 0;
        while(i < column.length && !column[i].hasPiece) i++;
        if(i == 0) return;
        switch(turn){
            case "RED": column[i - 1].addPiece(new RedPiece()); break;
            case "YELLOW": column[i - 1].addPiece(new YellowPiece()); break;
            default: column[i - 1].addPiece(new RedPiece()); break;
        }
    }
    
    public static boolean isFull(Square[][] board, int c){
        Square[] column = Board.getColumn(board, c);
        for(Square s : column){
            if(!s.hasPiece) return false;
        }
        return true;
    }
    
    public void update(){
        for(Square[] row : board){
            for(Square square : row){
                square.update();
            }
        }
    }
    
    public static Square[] getColumn(Square[][] board, int c){
        Square[] column = new Square[6];
        for(int i = 0; i < column.length; i++){
            column[i] = board[i][c];
        }
        return column;
    }
    
    public void computerMove(String turn) {
        addPiece(Computer.getMove(board, turn), turn);
        update();
    }
    
    
    
    public static Square[][] cloneArr(Square[][] board){
        Square[][] clone = new Square[6][7];
        for(int r = 0; r < 6; r++){
            for(int c = 0; c < 7; c++){
                clone[r][c] = board[r][c].clone();
            }
        }
        return clone;
    }
    
    public static void printArr(Square[][] board){
        for(Square[] row : board){
            for(Square s : row){
                System.out.print(s.getColor() + " ");
            }
            System.out.println();
        }
    }
    
    public static String checkWin(Square[][] board){
        for(int r = 0; r < board.length; r++){
            for(int c = 0; c < board[r].length - 3; c++){
                if(board[r][c].equals(board[r][c + 1]) && board[r][c + 1].equals(board[r][c + 2]) && board[r][c + 2].equals(board[r][c + 3])) return board[r][c].getColor();
            }
        }
        for(int r = 0; r < board.length - 3; r++){
            for(int c = 0; c < board[r].length; c++){
                if(board[r][c].equals(board[r + 1][c]) && board[r + 1][c].equals(board[r + 2][c]) && board[r + 2][c].equals(board[r + 3][c])) return board[r][c].getColor();
            }
        }
        for(int r = 0; r < board.length - 3; r++){
            for(int c = 0; c < board[r].length - 3; c++){
                if(board[r][c].equals(board[r + 1][c + 1]) && board[r + 1][c + 1].equals(board[r + 2][c + 2]) && board[r + 2][c + 2].equals(board[r + 3][c + 3])) return board[r][c].getColor();
                if(board[r + 3][c].equals(board[r + 2][c + 1]) && board[r + 2][c + 1].equals(board[r + 1][c + 2]) && board[r + 1][c + 2].equals(board[r][c + 3])) return board[r + 3][c].getColor();
            }
        }
        for(Square[] row : board){
            for(Square square : row){
                if(!square.hasPiece) return "NONE";
            }
        }
        return "DRAW";
    }
}
