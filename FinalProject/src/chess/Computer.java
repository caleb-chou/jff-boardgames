package chess;

import java.util.*;

public class Computer {
    private static SplittableRandom sr = new SplittableRandom();
    
    
    public static Move getMove(Game game){
        return move2(game, 3);
    }
    
    /**
     * Random move
     */
    public static Move move(Game game){
        return rndMove(getMoves(game));
    }
    
    /**
     * If: can take, take
     * Else: random move
     */
    public static Move move1(Game game){
        ArrayList<Move> moves = new ArrayList<>();
        ArrayList<Move> takes = new ArrayList<>();
        for(Piece p : game.compMoves.keySet()){
            for(Move m : game.compMoves.get(p)){
                if(m.isTaking()) takes.add(m);
                else moves.add(m);
            }
        }
        if(takes.isEmpty()) return rndMove(moves);
        return rndMove(takes);
    }
    
    /**
     * Thinks ahead n steps (Based on piece value)
     */
    public static Move move2(Game game, int n){
        ArrayList<Move> moves = getMoves(game);
        int move = move2value(game, n, game.getCurrentPlayer(), true);
        System.out.println(moves);
        System.out.println(move);
        return moves.get(move);
    }
    
    public static int move2value(Game game, int n, Player player, boolean returnBestMove){
        if(n <= 0) return game.getCurrentOpponent().calculateValue() - game.getCurrentPlayer().calculateValue();
        ArrayList<Move> moves = getMoves(game);
        int[] values = new int[moves.size()];
        for(int i = 0; i < moves.size(); i++){
            Move move = moves.get(i);
            move.move2();
            game.white.takePieces();
            game.white.removePieces();
            game.black.takePieces();
            game.black.removePieces();
            game.switchTurn();
            values[i] = move2value(game, n - 1, player, false);
            move.undo2();
            game.white.takePieces();
            game.white.removePieces();
            game.black.takePieces();
            game.black.removePieces();
            game.switchTurn();
            game.updateCompMoves();
        }
        if(returnBestMove){
            int maxValue = Integer.MIN_VALUE;
            int maxIndex = 0;
            System.out.println(Arrays.toString(values));
            for(int i = 0; i < values.length; i++){
                if(values[i] > maxValue){
                    maxValue = values[i];
                    maxIndex = i;
                }
            }
            return maxIndex;
        }
        Arrays.sort(values);
        return -values[values.length - 1];
    }
    
    
    
    
    
    
    public static Move rndMove(ArrayList<Move> moves){
        return moves.get(sr.nextInt(moves.size()));
    }
    
    public static ArrayList<Move> getMoves(Game game){
        game.updateCompMoves();
        ArrayList<Move> moves = new ArrayList<>();
        for(Piece p : game.compMoves.keySet()){
            for(Move m : game.compMoves.get(p)){
                moves.add(m);
            }
        }
        return moves;
    }
}
