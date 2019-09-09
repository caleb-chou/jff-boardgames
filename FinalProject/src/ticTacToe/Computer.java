package ticTacToe;

import java.util.*;

public class Computer {
    public static final SplittableRandom sr = new SplittableRandom();
    
    private static int findMoveNumber(String[] board) {return 9 - Collections.frequency(Arrays.asList(board), "");}
    
    private static boolean isEmptySquare(String[] board, int move){
        if(!board[move].equals("")) return false; // is the move an empty square?
        return true;
    }
    
    private static int checkifGameEnd(String[] board) {
        for(int i = 0; i < 3; i++) // columns/row
            if(!board[i * 3].equals("") && board[i * 3].equals(board[i * 3 + 1]) && board[i * 3 + 1].equals(board[i * 3 + 2])) return 1;
            else if (!board[i].equals("") && board[i].equals(board[i + 3]) && board[i + 3].equals(board[i + 6])) return 1;
        if(!board[4].equals("")) // diagonals
            if(board[0].equals(board[4]) && board[4].equals(board[8]) || board[2].equals(board[4]) && board[4].equals(board[6])) return 1;
        if(findMoveNumber(board) == 9) return 0; // draw
        return 2; // game has not ended
    }
    
    private static String otherTurn(String turn){
        if(turn.equals("X")) return "O";
        return "X";
    }
    
    public static int getComputerMove(String[] board, int turn, boolean returnBestMove, String character) {
        /**
         * Recursively determine the "most valued" move
         * Values:
         * Win       :  100
         * Loss      : -100
         * Draw      :   0
         * Faster Win:  +5 per move -> beats you as quick as possible
         * Recursively determines Opponents best move (and Opponent will find your best move to determine this)
         * Note: To make it more interesting, it's coded so it thinks the opponent will play around the 2nd best move every 20 turns
         * 
         * int[] Moves holds the "values" for the 9 different squares (the value is -1000 if the move is already taken)
         * Simply returns the best valued move (when doing recursively, multiply by -1 because your opponent winning is bad!)
         */
        if(checkifGameEnd(board) != 2) return checkifGameEnd(board) * 100 + 5 * (9 - findMoveNumber(board)); // gives the "ending" value -> base case for recursion
        int[] moves = new int[9];
        for(int i = 0; i < 9; i++){
            if(isEmptySquare(board, i)){
                String[] nboard = board.clone();
                nboard[i] = (turn == 1) ? character : otherTurn(character);
                moves[i] = getComputerMove(nboard, -turn, false, character);
            } else {
                moves[i] = -1000;
            }
        }
        if(returnBestMove){
            ArrayList<Integer> dupeMoves = new ArrayList(); // will contain the "best" moves -> used if there are multiple best moves to randomize it :P
            int max = Arrays.stream(moves).max().getAsInt();
            for(int i = 0; i < moves.length; i++)
                if(moves[i] == max)
                    dupeMoves.add(i);
            return dupeMoves.get(sr.nextInt(dupeMoves.size())); // return random move out of the best (this is the move the computer will make)
        }
        Arrays.sort(moves); // finds second best move
        int secondLargest = -1000;
        int max = moves[moves.length - 1];
        for(int i = moves.length - 1; i > 0; i--)
            if(moves[i] != max){secondLargest = moves[i]; break;}
        return (secondLargest == -1000 || turn == 1) ? -moves[8] : - ((19 * moves[8] + secondLargest) / 20); //return the value (negative explained up top)
        //Basically -> it assumes opponent may sometimes play the second best move (opponent makes mistake) so that the game is more interesting
    }
}
