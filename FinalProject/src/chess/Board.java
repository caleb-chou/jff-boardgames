package chess;

import java.awt.*;
import javax.swing.*;
import java.util.*;

public class Board extends JPanel {
    Dimension dimensions;
    
    Tile[][] board;
    
    ArrayList<Piece> pieces;
    
    Game game;
    public Board(int width, int height, Game game){
        this.game = game;
        this.dimensions = new Dimension(width, height);
        pieces = new ArrayList<>();
        initializeUI();
    }
    
    private void initializeUI(){
        setLayout(new GridLayout(dimensions.width, dimensions.height));
        setBackground(Game.color);
        
        board = new Tile[dimensions.width][dimensions.height];
        
        for(int r = 0; r < board.length; r++){
            for(int c = 0; c < board[r].length; c++){
                board[r][c] = new Tile(r, c);
                board[r][c].addActionListener(game);
            }
        }
        for(int c = board.length - 1; c >= 0; c--){
            for(int r = 0; r < board[c].length; r++){
                add(board[r][c]);
            }
        }
    }
    
    public void updatePieces(Player white, Player black){
        for(Tile[] row : board){
            for(Tile tile : row){
                tile.removePiece();
            }
        }
        white.takePieces();
        white.removePieces();
        black.takePieces();
        black.removePieces();
        pieces = new ArrayList<>();
        for(Piece piece : white.getPieces()){
            pieces.add(piece);
        }
        for(Piece piece : black.getPieces()){
            pieces.add(piece);
        }
        for(Piece piece : pieces){
            board[piece.x][piece.y].addPiece(piece);
        }
    }
    
    public void makeCheck(int x, int y){
        board[x][y].makeCheck();
    }
    
    public void update(){
        for(Tile[] row : board){
            for(Tile tile : row){
                tile.update();
            }
        }
    }
    
    public void resetTiles(){
        for(Tile[] row : board){
            for(Tile tile : row){
                tile.removeCheck();
                tile.removeMove();
                tile.removeTake();
                tile.deselect();
                tile.removeLastMove();
            }
        }
    }
    
    public void updateMoves(HashMap<Piece, ArrayList<Move>> moves){
        for(Tile[] row : board){
            for(Tile tile : row){
                tile.removeTake();
                tile.removeMove();
            }
        }
        ArrayList<Move> moveList = moves.get(selectedTile.piece);
        for(Move move: moveList){
            switch(move.move){
                case Move.TAKE: move.moveTo.makeTake(); break;
                case Move.ENPASSANT: move.moveTo.makeTake(); break;
                case Move.KINGTAKE: move.moveTo.makeTake(); break;
                case Move.ROOKTAKE: move.moveTo.makeTake(); break;
                case Move.PROMOTETAKE: move.moveTo.makeTake(); break;
                default: move.moveTo.makeMove();
            }
        }
    }
    
    public void selectTile(Tile t){
        for(Tile[] row : board){
            for(Tile tile : row){
                tile.deselect();
            }
        }
        t.select();
        selectedTile = t;
    }
    
    public Tile getTile(int x, int y){
        return board[x][y];
    }
    
    public Tile getTile(Piece p){
        return board[p.x][p.y];
    }
    
    Tile selectedTile;
    
    public Dimension getPreferredSize(){
        return new Dimension(8 * Tile.SIZE, 8 * Tile.SIZE);
    }
    
}
