package connectFour;

import java.util.*;

public class Computer {
    public static final SplittableRandom sr = new SplittableRandom();
    
    public static int getMove(Square[][] board, String color) {
        ArrayList<Integer> moves = new ArrayList<>();
        if(checkForImmWin(board, color)) return getImmWin(board, color);
        for(int i = 0; i < 7; i++){
            if(!Board.isFull(board, i)){
                Square[][] boardC = cloneMove(board, i, color);
                if(!checkForImmWin(boardC, switchTurn(color))) moves.add(i);
            }
        }
        if(moves.isEmpty()){
            int move;
            do{
                move = sr.nextInt(7);
            } while(Board.isFull(board, move));
            return move;
        }
        return moves.get(sr.nextInt(moves.size()));
    }
    
    // Should work in theory... prob takes a year though
    public static int getMove2(Square[][] board, String color, String turn, int alpha, int beta, boolean getMove){
        String win = Board.checkWin(board);
        if(win.equals("DRAW")) return 0;
        if(win.equals(color)) return 100;
        if(!win.equals("NONE")) return -100;
        
        int val;
        
        if(getMove){
            System.out.println("THINKING...");
            int[] values = {Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE};
            for(int i = 0; i < 7; i++){
                System.out.println("CHECKING>>> " + i);
                if(!Board.isFull(board, i)){
                    values[i] = getMove2(cloneMove(board, i, color), color, switchTurn(turn), alpha, beta, false);
                    if(values[i] == 100) return i;
                }
            }
            int max = values[0];
            for(int n : values){
                max = Math.max(n , max);
            }
            for(int i = 0; i < values.length; i++){
                if(values[i] == max) return i;
            }
        }
        if(color.equals(turn)){
            val = Integer.MIN_VALUE;
            for(int i = 0; i < 7; i++){
                if(!Board.isFull(board, i)){
                    val = Math.max(val, getMove2(cloneMove(board, i, color), color, switchTurn(turn), alpha, beta, false));
                    if(val == 100) return 100;
                    alpha = Math.max(val, alpha);
                    if(beta <= alpha) break;
                }
            }
            return val;
        } else {
            val = Integer.MAX_VALUE;
            for(int i = 0; i < 7; i++){
                if(!Board.isFull(board, i)){
                    val = Math.min(val, getMove2(cloneMove(board, i, switchTurn(color)), color, color, alpha, beta, false));
                    if(val == -100) return -100;
                    beta = Math.min(val, beta);
                    if(beta <= alpha) break;
                }
            }
            return val;   
        }
        
    }
    
    public static Square[][] cloneMove(Square[][] board, int c, String turn){
        Square[][] clone = Board.cloneArr(board);
        Board.addPiece(clone, c, turn);
        return clone;
    }
    
    public static String switchTurn(String turn){
        switch(turn){
            case "RED": return "YELLOW";
            default: return "RED";
        }
    }
    
    public static boolean checkForImmWin(Square[][] board, String turn){
        for(int i = 0; i < 7; i++){
            if(!Board.isFull(board, i)){
                String win = Board.checkWin(cloneMove(board, i, turn));
                if(!win.equals("NONE") && !win.equals("DRAW")) return true;
            }
        }
        return false;
    }
    
    public static int getImmWin(Square[][] board, String turn){
        for(int i = 0; i < 7; i++){
            if(!Board.isFull(board, i)){
                String win = Board.checkWin(cloneMove(board, i, turn));
                if(!win.equals("NONE") && !win.equals("DRAW")) return i;
            }
        }
        return -1;
    }
}
