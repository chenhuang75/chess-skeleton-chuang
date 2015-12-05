package chess;


import chess.pieces.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class that represents the current state of the game.  Basically, what pieces are in which positions on the
 * board.
 */
public class GameState {

    /**
     * The current player
     */
    private Player currentPlayer = Player.White;

    /**
     * A map of board positions to pieces at that position
     */
    private Map<Position, Piece> positionToPieceMap;
    
    private Position whiteKingPos;
    
    private Position blackKingPos;
    
    private boolean gameOver;
    
    private String congrats;

    /**
     * Create the game state.
     */
    public GameState() {
        positionToPieceMap = new HashMap<Position, Piece>();
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Call to initialize the game state into the starting positions
     */
    public void reset() {
        // White Pieces
        placePiece(new Rook(Player.White), new Position("a1"));
        placePiece(new Knight(Player.White), new Position("b1"));
        placePiece(new Bishop(Player.White), new Position("c1"));
        placePiece(new Queen(Player.White), new Position("d1"));
        placePiece(new King(Player.White), new Position("e1"));
        placePiece(new Bishop(Player.White), new Position("f1"));
        placePiece(new Knight(Player.White), new Position("g1"));
        placePiece(new Rook(Player.White), new Position("h1"));
        placePiece(new Pawn(Player.White), new Position("a2"));
        placePiece(new Pawn(Player.White), new Position("b2"));
        placePiece(new Pawn(Player.White), new Position("c2"));
        placePiece(new Pawn(Player.White), new Position("d2"));
        placePiece(new Pawn(Player.White), new Position("e2"));
        placePiece(new Pawn(Player.White), new Position("f2"));
        placePiece(new Pawn(Player.White), new Position("g2"));
        placePiece(new Pawn(Player.White), new Position("h2"));

        // Black Pieces
        placePiece(new Rook(Player.Black), new Position("a8"));
        placePiece(new Knight(Player.Black), new Position("b8"));
        placePiece(new Bishop(Player.Black), new Position("c8"));
        placePiece(new Queen(Player.Black), new Position("d8"));
        placePiece(new King(Player.Black), new Position("e8"));
        placePiece(new Bishop(Player.Black), new Position("f8"));
        placePiece(new Knight(Player.Black), new Position("g8"));
        placePiece(new Rook(Player.Black), new Position("h8"));
        placePiece(new Pawn(Player.Black), new Position("a7"));
        placePiece(new Pawn(Player.Black), new Position("b7"));
        placePiece(new Pawn(Player.Black), new Position("c7"));
        placePiece(new Pawn(Player.Black), new Position("d7"));
        placePiece(new Pawn(Player.Black), new Position("e7"));
        placePiece(new Pawn(Player.Black), new Position("f7"));
        placePiece(new Pawn(Player.Black), new Position("g7"));
        placePiece(new Pawn(Player.Black), new Position("h7"));
        
        gameOver = false;
    }

    /**
     * Get the piece at the position specified by the String
     * @param colrow The string indication of position; i.e. "d5"
     * @return The piece at that position, or null if it does not exist.
     */
    public Piece getPieceAt(String colrow) {
        Position position = new Position(colrow);
        return getPieceAt(position);
    }

    /**
     * Get the piece at a given position on the board
     * @param position The position to inquire about.
     * @return The piece at that position, or null if it does not exist.
     */
    public Piece getPieceAt(Position position) {
        return positionToPieceMap.get(position);
    }

    /**
     * Method to place a piece at a given position
     * @param piece The piece to place
     * @param position The position
     */
    private void placePiece(Piece piece, Position position) {
        positionToPieceMap.put(position, piece);
		if(Character.toLowerCase( piece.getIdentifier() ) == 'k') {
			if(piece.getOwner() == Player.White)
				whiteKingPos = position;
			else
				blackKingPos = position;
		}

    }
    
    /**
     * We generate all the possible moves for the current player
     * we also exclude the moves which will keep king under the check
     * @return
     */
    public List<String> allPossibleMoves() {
    	List<String> moves = new ArrayList<String>();
    	
    	// get all the pieces for the current player
    	Map<Position, Piece> tmpMap = new HashMap<Position, Piece>();
    	for (Position pos : positionToPieceMap.keySet()) {
    		Piece piece = positionToPieceMap.get(pos);
    		if(getCurrentPlayer() != piece.getOwner())
    			continue;
    		tmpMap.put(pos, piece);
    	}
    		
    	for (Position pos : tmpMap.keySet()) {
    		Piece piece = tmpMap.get(pos);
    		List<Position> positions = piece.getNextPositions(pos, this);
    		for(int i = 0; i < positions.size(); i ++) {
    			String move = pos.toString() + " " + positions.get(i).toString();
    			
    			// check if the king move is valid
    			if(!isMoveValidforKing(move))
    				continue;
    				
    			moves.add(move);
    		}
    	}
    	return moves;
    }
    
    /**
     * we generate all the possible moves for all the pieces other than the king.
     * notice this function doesn't need to move pieces to backtrack test.
     * @return
     */
    private List<String> allPossibleMovesWOKing() {
    	List<String> moves = new ArrayList<String>();
    	
    	// get all the pieces for the current player
    	Map<Position, Piece> tmpMap = new HashMap<Position, Piece>();
    	for (Position pos : positionToPieceMap.keySet()) {
    		Piece piece = positionToPieceMap.get(pos);
    		if(getCurrentPlayer() != piece.getOwner())
    			continue;
    		tmpMap.put(pos, piece);
    	}
    		
    	for (Position pos : tmpMap.keySet()) {
    		Piece piece = tmpMap.get(pos);
    		List<Position> positions = piece.getNextPositions(pos, this);
    		for(int i = 0; i < positions.size(); i ++) {
    			String move = pos.toString() + " " + positions.get(i).toString();
    			
    			// we ignore king moves in this function to avoid looping
    			if(Character.toLowerCase( piece.getIdentifier() ) == 'k')
    				continue;
    				
    			moves.add(move);
    		}
    	}
    	return moves;
    }
    
    /**
     * Make a move
     * Also check if the game is over
     * @param move
     * @return
     */
    public boolean makeMove(String move) {
    	if(!allPossibleMoves().contains(move))
    		return false;
    	String[] positions = move.split(" ");
    	Position oldPos = new Position(positions[0]);
    	Position newPos = new Position(positions[1]);
    	
    	Piece piece = getPieceAt(oldPos);
    	positionToPieceMap.remove(oldPos);
    	placePiece(piece, newPos);
    	nextTurn();

    	// check if it's checkmate
    	if(isCheckmate()) {
    		nextTurn();
    		gameOver = true;
    		congrats = "The game is over.  Congrats to " + currentPlayer + ".";
    	}
    	    	
    	return true;
    }
    
    /**
     * check if this move will make the king under check.
     * we make the move to change the positions first,
     * then we restore the old positions after the check
     * @param move
     * @return
     */
    private boolean isMoveValidforKing(String move) {
    	String[] positions = move.split(" ");
    	Position oldPos = new Position(positions[0]);
    	Position newPos = new Position(positions[1]);
    	
    	// backtrack testing the move from old pos to new pos
    	Piece piece = getPieceAt(oldPos);
    	Piece replacedPiece = getPieceAt(newPos);
    	positionToPieceMap.remove(oldPos);
    	placePiece(piece, newPos);
    	
    	// check if this move is valid under conditions
    	boolean flag = true;
    	if( isKingUnderCheck() || areKingsAdjacent() )
    		flag = false;
    	
    	// restore the move back to old pos
    	placePiece(piece, oldPos);
    	if(replacedPiece != null)
    		placePiece(replacedPiece, newPos);
    	else
        	positionToPieceMap.remove(newPos);
    	
    	return flag;
    }
    
    /**
     * change turn
     */
    private void nextTurn() {
    	if(currentPlayer == Player.White)
    		currentPlayer = Player.Black;
    	else
    		currentPlayer = Player.White;
    }
    
    /**
     * check if this is checkmate
     * somehow I think stalemate is also included here.
     * I need to seperate them.
     * @return
     */
    private boolean isCheckmate() {
    	return allPossibleMoves().size() == 0;
    }
    
    /**
     * check if the king is under check
     * @return
     */
    private boolean isKingUnderCheck() {
    	String kingPos = whiteKingPos.toString();
    	if(currentPlayer == Player.Black)
    		kingPos = blackKingPos.toString();
    	
    	nextTurn();
    	List<String> allMoves = allPossibleMovesWOKing();
    	boolean flag = false;
    	for (String move : allMoves) {
			if(move.endsWith(kingPos))
				flag = true;
		}
    	
    	nextTurn();
    	return flag;
    }
    
    /**
     * check if two kings are adjacent which is not allowed
     * @return
     */
    public boolean areKingsAdjacent() {
    	return ((whiteKingPos.getColumn() - blackKingPos.getColumn()) * (whiteKingPos.getColumn() - blackKingPos.getColumn()) + 
    			(whiteKingPos.getRow() - blackKingPos.getRow()) * (whiteKingPos.getRow() - blackKingPos.getRow()) <= 2);
    }
    
    /**
     * check if the game is over
     * @return
     */
    public boolean isGameOver() {
    	return gameOver;
    }
    
    /**
     * return congratulations
     * @return
     */
    public String congrats() {
    	return congrats;
    }
}
