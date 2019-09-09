package finalproject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import chess.*;
import connectFour.*;
import ticTacToe.*;

public class MainScreen extends JFrame implements ActionListener{
    customButton chessB;
    customButton connectFourB;
    customButton ticTacToeB;
    
    Color pressed = new Color(150, 150, 150);
    Color background = new Color(200, 200, 200);
    Color hovered = new Color(180, 180, 180);
    
    JPanel games;
    JPanel chessP;
    Chess chess;
    customButton chessI;
    JPanel connectFourP;
    ConnectFour connectFour;
    customButton connectFourI;
    JPanel ticTacToeP;
    TicTacToe ticTacToe;
    customButton ticTacToeI;
    
    public MainScreen(){
        setBounds(0, 0, 500, 500);
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Games");
        setIconImage(Resources.getImage("Game_Icon.png"));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
        
        games = new JPanel();
        games.setLayout(new GridLayout(3, 1));
        
            chessP = new JPanel();
            chessP.setLayout(new BoxLayout(chessP, BoxLayout.LINE_AXIS));
        
                JPanel chessBP = new JPanel();
                chessBP.setLayout(new GridLayout(1, 1));
                
                    chessB = new customButton(background, pressed, hovered);
                    chessB.setBorderPainted(false);
                    chessB.addActionListener(this);
                    chessB.setActionCommand("Chess");
                    chessB.setText("Chess            ");
                    chessB.setFont(new Font("", Font.PLAIN, 40));
                    chessB.setAlignmentX(Component.LEFT_ALIGNMENT);
                    chessB.setHorizontalAlignment(JTextField.LEFT);
                    chessB.setIcon(Resources.scaleImage(Resources.getImage("Chess_Icon.png"), 100));
                
                JPanel chessIP = new JPanel();
                chessIP.setLayout(new GridLayout(1, 1));
                
                    chessI = new customButton(background, pressed, hovered);
                    chessI.setBorderPainted(false);
                    chessI.addActionListener(this);
                    chessI.setActionCommand("Chess Info");
                    chessI.setIcon(Resources.scaleImage(Resources.getImage("Info_Icon.png"), 50));
                    chessI.setAlignmentX(Component.CENTER_ALIGNMENT);
                    chessI.setAlignmentY(Component.CENTER_ALIGNMENT);
                
                chessIP.add(chessI);
                chessBP.add(chessB);
                
            chessP.add(chessBP);
            chessP.add(chessIP);

            connectFourP = new JPanel();
            connectFourP.setLayout(new BoxLayout(connectFourP, BoxLayout.LINE_AXIS));
        
                JPanel connectFourBP = new JPanel();
                connectFourBP.setLayout(new GridLayout(1, 1));
                
                    connectFourB = new customButton(background, pressed, hovered);
                    connectFourB.setBorderPainted(false);
                    connectFourB.addActionListener(this);
                    connectFourB.setActionCommand("Connect Four");
                    connectFourB.setText("Connect Four");
                    connectFourB.setFont(new Font("", Font.PLAIN, 40));
                    connectFourB.setAlignmentX(Component.LEFT_ALIGNMENT);
                    connectFourB.setHorizontalAlignment(JTextField.LEFT);
                    connectFourB.setIcon(Resources.scaleImage(Resources.getImage("Connect_Four_Icon.png"), 100));
                
                JPanel connectFourIP = new JPanel();
                connectFourIP.setLayout(new GridLayout(1, 1));
                
                    connectFourI = new customButton(background, pressed, hovered);
                    connectFourI.setBorderPainted(false);
                    connectFourI.addActionListener(this);
                    connectFourI.setActionCommand("Connect Four Info");
                    connectFourI.setIcon(Resources.scaleImage(Resources.getImage("Info_Icon.png"), 50));
                    connectFourI.setAlignmentX(Component.CENTER_ALIGNMENT);
                    connectFourI.setAlignmentY(Component.CENTER_ALIGNMENT);
                
                connectFourIP.add(connectFourI);
                connectFourBP.add(connectFourB);
                
            connectFourP.add(connectFourBP);
            connectFourP.add(connectFourIP);

            ticTacToeP = new JPanel();
            ticTacToeP.setLayout(new BoxLayout(ticTacToeP, BoxLayout.LINE_AXIS));
        
                JPanel ticTacToeBP = new JPanel();
                ticTacToeBP.setLayout(new GridLayout(1, 1));
                
                    ticTacToeB = new customButton(background, pressed, hovered);
                    ticTacToeB.setBorderPainted(false);
                    ticTacToeB.addActionListener(this);
                    ticTacToeB.setActionCommand("Tic Tac Toe");
                    ticTacToeB.setText("Tic Tac Toe   ");
                    ticTacToeB.setFont(new Font("", Font.PLAIN, 40));
                    ticTacToeB.setAlignmentX(Component.LEFT_ALIGNMENT);
                    ticTacToeB.setHorizontalAlignment(JTextField.LEFT);
                    ticTacToeB.setIcon(Resources.scaleImage(Resources.getImage("TicTacToe_Icon.png"), 100));
                
                JPanel ticTacToeIP = new JPanel();
                ticTacToeIP.setLayout(new GridLayout(1, 1));
                
                    ticTacToeI = new customButton(background, pressed, hovered);
                    ticTacToeI.setBorderPainted(false);
                    ticTacToeI.addActionListener(this);
                    ticTacToeI.setActionCommand("Tic Tac Toe Info");
                    ticTacToeI.setIcon(Resources.scaleImage(Resources.getImage("Info_Icon.png"), 50));
                    ticTacToeI.setAlignmentX(Component.CENTER_ALIGNMENT);
                    ticTacToeI.setAlignmentY(Component.CENTER_ALIGNMENT);
                
                ticTacToeIP.add(ticTacToeI);
                ticTacToeBP.add(ticTacToeB);
                
            ticTacToeP.add(ticTacToeBP);
            ticTacToeP.add(ticTacToeIP);
        
        games.add(chessP);
        games.add(connectFourP);
        games.add(ticTacToeP);
        
        JLabel title = new JLabel("Welcome to Games");
        title.setFont(new Font("", Font.PLAIN, 30));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel creators = new JLabel("by: dlz9345 & cyc3086");
        creators.setFont(new Font("", Font.PLAIN, 30));
        creators.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        add(title);
        add(creators);
        add(games);
        
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae){
        String command = ae.getActionCommand();
        switch(command){
            case "Chess": playChess(); break;
            case "Connect Four": playConnectFour(); break;
            case "Tic Tac Toe": playTicTacToe(); break;
            case "Chess Info": chessInfo(); break;
            case "Connect Four Info": connectFourInfo(); break;
            case "Tic Tac Toe Info": ticTacToeInfo(); break;
        }
    }
    
    public void playChess(){
        chess = new Chess();
    }
    
    public void playConnectFour(){
        connectFour = new ConnectFour();
    }
    
    public void playTicTacToe(){
        ticTacToe = new TicTacToe();
    }
    
    public void chessInfo(){
        int imageSize = 60;
        String[] instructions = {"The most important piece on the board, you cannot let the king be captured", "The king can move to any adjacent square", "The most powerful piece on the board", "The queen can move in any direction, up, down, left, right, or diagonally", "The pair of rooks work well together", "The rook can move straight up or down, left or right",  "The bishop stays on the same color tile", "The bishop can move diagonally",  "The one piece that can jump over others", "The knight moves in a L shape, two tiles forward and one tile left or right", "The seemingly useless piece, but has the most potential", "The pawn can only move forward one tile, but it captures other pieces diagonally (one tile)", "As well, the pawn may move forward two tiles if it's the pawn's first move", "You can promote a pawn when it reaches the other side of the board to a queen, rook, knight, or bishop", "A special move by pawns", "A pawn may capture another pawn that just moved forward two tiles. This is only available for one turn", "Defend the king!", "The king may move left or right two squares and place the rook on the side closer to the center if:", "The king has not moved", "The rook has not moved", "There are no pieces between them", "the king is not in check and does not pass through check", "The ultimate win", "When the king is in check and there is no way out of it, you have become checkmated and lose!", "Huh? A draw?", "When there are no legal moves for a side, the game is ended in a stalemate"};
        String[] titles = {"King", "Queen", "Rook", "Bishop", "Knight", "Pawn", "Promotion", "En Passant", "Castling", "Checkmate", "Stalemate"};
        ImageIcon[] images = {Resources.scaleImage(Resources.getImage("King.gif"), imageSize), Resources.scaleImage(Resources.getImage("Queen.gif"), imageSize), Resources.scaleImage(Resources.getImage("Rook.gif"), imageSize), Resources.scaleImage(Resources.getImage("Bishop.gif"), imageSize), Resources.scaleImage(Resources.getImage("Knight.gif"), imageSize), Resources.scaleImage(Resources.getImage("Pawn.gif"), imageSize), Resources.scaleImage(Resources.getImage("Promote.png"), imageSize), Resources.scaleImage(Resources.getImage("En_Passant.gif"), imageSize), Resources.scaleImage(Resources.getImage("Castle.gif"), imageSize), Resources.scaleImage(Resources.getImage("Checkmate.gif"), imageSize), null};
        boolean[] hasImage = {true, true, true, true, true, true, true, true, true, true, false};
        int[] num = {2, 2, 2, 2, 2, 3, 1, 2, 6, 2, 2};
        Instructions i = new Instructions(instructions, titles, images, hasImage, num);
    }
    
    public void connectFourInfo(){
        int imageX = 70;
        int imageY = 60;
        String[] instructions = {};
        String[] titles = {"Choose a column each turn to place a piece (Red or Yellow)", "The Objective of the game is to get four pieces in a row: ", "Vertically", "Horizontally", "Diagonally", "Pieces are dropped to the bottom of the board as if there is gravity"};
        ImageIcon[] images = {null, null, Resources.scaleImage(Resources.getImage("Vertically.png"), imageX, imageY), Resources.scaleImage(Resources.getImage("Horizontally.png"), imageX, imageY), Resources.scaleImage(Resources.getImage("Diagonally.png"), imageX, imageY), null};
        boolean[] hasImage = {false, false, true, true, true, false};
        int[] num = {0, 0, 0, 0, 0, 0};
        Instructions i = new Instructions(instructions, titles, images, hasImage, num);
    }
    
    public void ticTacToeInfo(){
        String[] instructions = {};
        String[] titles = {"Choose one of the nine squares to put your piece (X or O)", "The Objective of the game is to get three pieces in a row: ", "Vertically", "Horizontally", "Diagonally", "Unlike Connect Four, there is no gravity so where you place it is where it stays"};
        ImageIcon[] images = {null, null, null, null, null, null};
        boolean[] hasImage = {false, false, false, false, false, false};
        int[] num = {0, 0, 0, 0, 0, 0};
        Instructions i = new Instructions(instructions, titles, images, hasImage, num);
    }
    
}
