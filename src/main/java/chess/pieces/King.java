package chess.pieces;

import java.util.ArrayList;
import java.util.List;

import chess.GameState;
import chess.Player;
import chess.Position;

/**
 * The King class
 */
public class King extends Piece {
    public King(Player owner) {
        super(owner);
    }

    @Override
    protected char getIdentifyingCharacter() {
        return 'k';
    }
    
    /** 
     * get possible next positions based on the game
     */
    public List<Position> getNextPositions(Position initPos, GameState state) {
    	char col = initPos.getColumn();
    	int row = initPos.getRow();
    	List<Position> positions = new ArrayList<Position>();
    	Position newPos;
    	
    	boolean a = state.areKingsAdjacent();
    	
    	// move up
    	newPos = new Position(col, row + 1);
    	if(isMoveValid(newPos, state) ) {
    		positions.add(newPos);
    	}

    	// move down
    	newPos = new Position(col, row - 1);
    	if(isMoveValid(newPos, state) ) {
    		positions.add(newPos);
    	}

    	// move left
    	newPos = new Position((char)(col - 1), row);
    	if(isMoveValid(newPos, state) ) {
    		positions.add(newPos);
    	}

    	// move right
    	newPos = new Position((char)(col + 1), row);
    	if(isMoveValid(newPos, state) ) {
    		positions.add(newPos);
    	}

    	// move up right
    	newPos = new Position((char)(col + 1), row + 1);
    	if(isMoveValid(newPos, state) ) {
    		positions.add(newPos);
    	}

    	// move up left
    	newPos = new Position((char)(col - 1), row + 1);
    	if(isMoveValid(newPos, state) ) {
    		positions.add(newPos);
    	}

    	// move down right
    	newPos = new Position((char)(col + 1), row - 1);
    	if(isMoveValid(newPos, state) ) {
    		positions.add(newPos);
    	}

    	// move down left
    	newPos = new Position((char)(col - 1), row - 1);
    	if(isMoveValid(newPos, state) ) {
    		positions.add(newPos);
    	}
    	
    	return positions;
    }   
}
