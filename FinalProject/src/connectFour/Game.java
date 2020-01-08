package connectFour;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import finalproject.*;

public class Game extends JFrame implements ActionListener{
    public static final int COLUMNS = 7;
    public static final int ROWS = 6;
    
    String turn;
    
    Board board;
    
    JPanel options;
    customButton computer;
    customButton switchTurn;
    
    End win;
    
    int mode;
    
    Main main;
    
    End endFrame;
    
    public Game(){
        main = new Main(this);
    }
    
    public Game(int mode){
        this.mode = mode;
        startGame();
    }
    
    private void initializeUI(){
        setBounds(0, 0, 7 * Square.SIZE, 6 * Square.SIZE + 120);
        setLocationRelativeTo(null);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Connect Four");
        setIconImage(Resources.getImage("Connect_Four_Icon.png"));
        setForeground(Color.blue);
        setBackground(Color.blue);
        
            board = new Board(this, mode);
            
            Color background = new Color(200, 200, 200);
            Color hovered = new Color(180, 180, 180);
            Color pressed = new Color(160, 160, 160);
            
            options = new JPanel();
            options.setLayout(new GridLayout(2, 1));
            
                computer = new customButton(background, hovered, pressed);
                computer.setBorderPainted(false);
                computer.setText("Press for Computer");
                computer.setFont(new Font("", Font.PLAIN, 20));
                computer.setPreferredSize(new Dimension(7 * Square.SIZE, 60));
                computer.addActionListener(this);
                computer.setActionCommand("COMPUTER");
                computer.setAlignmentX(Component.CENTER_ALIGNMENT);

                switchTurn = new customButton(background, hovered, pressed);
                switchTurn.setBorderPainted(false);
                switchTurn.setText("Switch Turn");
                switchTurn.setFont(new Font("", Font.PLAIN, 20));
                switchTurn.setPreferredSize(new Dimension(7 * Square.SIZE, 60));
                switchTurn.addActionListener(this);
                switchTurn.setActionCommand("SWITCH TURN");
                switchTurn.setAlignmentX(Component.CENTER_ALIGNMENT);
                switchTurnUpdate();
            
            options.add(computer);
            options.add(switchTurn);
            
        add(board);
        add(options);
        setVisible(true);
    }
    
    private void startGame(){
        Resources.playAudio("Start_End.wav");
        try{Thread.sleep(100);} catch(Exception ex){};
        turn = "RED";
        initializeUI();
        if(mode == Main.CVP){
            board.computerMove(turn);
            Resources.playAudio("Connect_Four_Piece.wav");
            nextTurn();
        } else {
            setUpTurn();
        }
    }
    
    public void switchTurnUpdate(){
        if(turn.equals("RED")){
            switchTurn.update(Color.RED, new Color(150, 0, 0), new Color(200, 0, 0));
        }
        else switchTurn.update(Color.YELLOW, new Color(150, 150, 0), new Color(200, 200, 0));
    }
    
    public void restart(){
        isEnded = false;
        getContentPane().removeAll();
        revalidate();
        startGame();
    }
    
    public String otherTurn(String turn){
        if(turn.equals("RED")) return "YELLOW";
        return "RED";
    }
    
    public void nextTurn(){
        turn = otherTurn(turn);
        setUpTurn();
    }
    
    boolean isEnded = false;
    
    public void setUpTurn(){
        switchTurnUpdate();
        int end = checkWin();
        if(end != NOT_ENDED){
            endFrame = new End(this, end);
            isEnded = true;
            return;
        }
    }
    
    
    public void actionPerformed(ActionEvent ae){
        String command = ae.getActionCommand();
        switch(command){
            case "Restart": endFrame.dispose();
                            restart(); return;
            case "No": endFrame.dispose();
                       dispose(); return;
        }
        if(isEnded) return;
        switch(command){
            case "COMPUTER": board.computerMove(turn);
                             Resources.playAudio("Connect_Four_Piece.wav");
                             nextTurn();
                             if (mode != Main.PVP && !isEnded) {
                                 board.computerMove(turn);
                                 Resources.playAudio("Connect_Four_Piece.wav");
                                 nextTurn();
                             } return;
            case "SWITCH TURN": turn = otherTurn(turn); 
                                switchTurnUpdate(); return;
            case "Start": mode = main.mode;
                          if(mode == Main.NONE) return;
                          main.dispose();
                          startGame(); return;
            default: main.updateCommand(command);
        }
        if(!command.matches("[0-9]*")) return;
        int column = Integer.parseInt(command);
        if(board.addPiece(column, turn)){
            board.update();
            Resources.playAudio("Connect_Four_Piece.wav");
            nextTurn();
            if (mode != Main.PVP && !isEnded) {
                board.computerMove(turn);
                Resources.playAudio("Connect_Four_Piece.wav");
                nextTurn();
            }
        }
    }
    
    
    public static final int NOT_ENDED = 0;
    public static final int RED_WIN = 1;
    public static final int YELLOW_WIN = 2;
    public static final int DRAW = 3;
    public int checkWin(){
        Square[][] board = this.board.board;
        for(int r = 0; r < board.length; r++){
            for(int c = 0; c < board[r].length - 3; c++){
                if(board[r][c].equals(board[r][c + 1]) && board[r][c + 1].equals(board[r][c + 2]) && board[r][c + 2].equals(board[r][c + 3])){
                    if(board[r][c].getColor().equals("RED")) return RED_WIN;
                    else return YELLOW_WIN;
                }
            }
        }
        for(int r = 0; r < board.length - 3; r++){
            for(int c = 0; c < board[r].length; c++){
                if(board[r][c].equals(board[r + 1][c]) && board[r + 1][c].equals(board[r + 2][c]) && board[r + 2][c].equals(board[r + 3][c])){
                    if(board[r][c].getColor().equals("RED")) return RED_WIN;
                    else return YELLOW_WIN;
                }
            }
        }
        for(int r = 0; r < board.length - 3; r++){
            for(int c = 0; c < board[r].length - 3; c++){
                if(board[r][c].equals(board[r + 1][c + 1]) && board[r + 1][c + 1].equals(board[r + 2][c + 2]) && board[r + 2][c + 2].equals(board[r + 3][c + 3])){
                    if(board[r][c].getColor().equals("RED")) return RED_WIN;
                    else return YELLOW_WIN;
                }
                if(board[r + 3][c].equals(board[r + 2][c + 1]) && board[r + 2][c + 1].equals(board[r + 1][c + 2]) && board[r + 1][c + 2].equals(board[r][c + 3])){
                    if(board[r + 3][c].getColor().equals("RED")) return RED_WIN;
                    else return YELLOW_WIN;
                }
            }
        }
        for(Square[] row : board){
            for(Square square : row){
                if(!square.hasPiece) return NOT_ENDED;
            }
        }
        return DRAW;
    }
}
