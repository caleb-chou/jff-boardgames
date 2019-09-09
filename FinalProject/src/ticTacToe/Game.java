package ticTacToe;

import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import finalproject.*;

public class Game extends JFrame implements ActionListener{
    Main main;
    
    int mode;
    
    Board board;
    
    End endFrame;
    
    String turn;
    
    JPanel options;
    customButton computer;
    JPanel turnPanel;
    JLabel turnLabel;
    JLabel turnIcon;
    
    public Game(){
        main = new Main(this);
    }
    
    public void initializeGame(){
        setBounds(0, 0, 3 * Square.SIZE, 3 * Square.SIZE + 90);
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Tic Tac Toe");
        setIconImage(Resources.getImage("TicTacToe_Icon.png"));
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
        
        Color background = new Color(200, 200, 200);
        Color hovered = new Color(180, 180, 180);
        Color pressed = new Color(160, 160, 160);
        
            options = new JPanel();
            options.setLayout(new GridLayout(1, 1));

                computer = new customButton(background, hovered, pressed);
                computer.setBorderPainted(false);
                computer.setText("Press for Computer");
                computer.setFont(new Font("", Font.PLAIN, 20));
                computer.setPreferredSize(new Dimension(7 * Square.SIZE, 60));
                computer.addActionListener(this);
                computer.setActionCommand("COMPUTER");
                computer.setAlignmentX(Component.CENTER_ALIGNMENT);
                
                turnPanel = new JPanel();
                turnPanel.setLayout(new BoxLayout(turnPanel, BoxLayout.LINE_AXIS));
                
                    turnLabel = new JLabel("Placing: ");
                    turnLabel.setFont(new Font("", Font.PLAIN, 30));
                    turnLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                    
                    turnIcon = new JLabel();
                    updateTurnIcon();
                
                turnPanel.add(turnLabel);
                turnPanel.add(turnIcon);
            
            options.add(computer);
        
        add(board);
        add(options);
        add(turnPanel);
        
        setVisible(true);
    }
    
    public void startGame(){
        board = new Board(this, mode);
        turn = "X";
        initializeGame();
        if(mode == Main.CVP){
            board.computerMove(turn);
            nextTurn();
        } else {
            setUpTurn();
        }
    }
    
    public void updateTurnIcon(){
        if(turn.equals("X")) turnIcon.setIcon(Resources.scaleImage(Resources.getImage("X.png"), 40));
        else turnIcon.setIcon(Resources.scaleImage(Resources.getImage("O.png"), 40));
    }
    
    public void restart(){
        isEnded = false;
        getContentPane().removeAll();
        revalidate();
        startGame();
    }
    
    public void nextTurn(){
        turn = otherTurn(turn);
        setUpTurn();
    }
    
    public void setUpTurn(){
        updateTurnIcon();
        board.update();
        int end = checkWin();
        if(end != NOT_ENDED){
            endFrame = new End(this, end);
            isEnded = true;
            return;
        }
    }
    
    
    public String otherTurn(String turn){
        if(turn.equals("X")) return "O";
        return "X";
    }
    
    private int findMoveNumber(String[] board) {return 9 - Collections.frequency(Arrays.asList(board), "");}
    
    public static final int NOT_ENDED = 0;
    public static final int X_WIN = 1;
    public static final int O_WIN = 2;
    public static final int DRAW = 3;
    public int checkWin(){
        String[] board = Board.convertBoard(this.board);
        for(int i = 0; i < 3; i++) // columns/row
            if(!board[i * 3].equals("") && board[i * 3].equals(board[i * 3 + 1]) && board[i * 3 + 1].equals(board[i * 3 + 2])){
                if(board[i * 3].equals("X")) return X_WIN;
                return O_WIN;
            }
            else if (!board[i].equals("") && board[i].equals(board[i + 3]) && board[i + 3].equals(board[i + 6])){
                if(board[i].equals("X")) return X_WIN;
                return O_WIN;
            }
        if(!board[4].equals("")) // diagonals
            if(board[0].equals(board[4]) && board[4].equals(board[8]) || board[2].equals(board[4]) && board[4].equals(board[6])){
                if(board[0].equals("X")) return X_WIN;
                return O_WIN;
            }
        if(findMoveNumber(board) == 9) return DRAW; // draw
        return NOT_ENDED; // game has not ended
    }
    
    boolean isEnded = false;
    
    public void makeSound(){
        if(turn.equals("X")) Resources.playAudio("TicTacToe_Draw_X.wav");
        else Resources.playAudio("TicTacToe_Draw_O.wav");
    }
    
    public void actionPerformed(ActionEvent ae){
        String command = ae.getActionCommand();
        switch(command){
            case "Restart": endFrame.dispose();
                        restart(); break;
            case "No": endFrame.dispose();
                       dispose(); break;
        }
        if(isEnded) return;
        switch(command){
            case "COMPUTER": board.computerMove(turn);
                             makeSound();
                             nextTurn();
                             if (mode != Main.PVP && !isEnded) {
                                 board.computerMove(turn);
                                 makeSound();
                                 nextTurn();
                             } break;
            case "Start": mode = main.mode;
                          if(mode == Main.NONE) break;
                          main.dispose();
                          startGame(); break;
            default: main.updateCommand(command);
        }
        if(!command.matches("[0-9]*")) return;
        int column = Integer.parseInt(command);
        if(board.setPiece(column, turn)){
            board.update();
            makeSound();
            nextTurn();
            if (mode != Main.PVP && !isEnded) {
                board.computerMove(turn);
                makeSound();
                nextTurn();
            }
        }
    }
    
    
}
