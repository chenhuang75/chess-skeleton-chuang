package chess.pieces;

import java.util.ArrayList;
import java.util.List;

import chess.GameState;
import chess.Player;
import chess.Position;

/**
 * The Queen
 */
public class Queen extends Piece{
    public Queen(Player owner) {
        super(owner);
    }

    @Override
    protected char getIdentifyingCharacter() {
        return 'q';
    }

    /**
     * get all possible next positions
     */
    public List<Position> getNextPositions(Position initPos, GameState state) {
    	char col;
    	int row;
    	List<Position> positions = new ArrayList<Position>();
    	Position newPos;
    	
    	// move up
    	col = initPos.getColumn();
    	row = initPos.getRow();
    	newPos = new Position(col, ++row);
    	while(isMoveValid(newPos, state)) {
    		positions.add(newPos);
    		if( state.getPieceAt(newPos) != null)
    			break;
        	newPos = new Position(col, ++row);
    	}

    	// move down
    	col = initPos.getColumn();
    	row = initPos.getRow();
    	newPos = new Position(col, --row);
    	while(isMoveValid(newPos, state)) {
    		positions.add(newPos);
    		if( state.getPieceAt(newPos) != null)
    			break;
        	newPos = new Position(col, --row);
    	}

    	// move left
    	col = initPos.getColumn();
    	row = initPos.getRow();
    	newPos = new Position(--col, row);
    	while(isMoveValid(newPos, state)) {
    		positions.add(newPos);
    		if( state.getPieceAt(newPos) != null)
    			break;
        	newPos = new Position(--col, row);
    	}

    	// move right
    	col = initPos.getColumn();
    	row = initPos.getRow();
    	newPos = new Position(++col, row);
    	while(isMoveValid(newPos, state)) {
    		positions.add(newPos);
    		if( state.getPieceAt(newPos) != null)
    			break;
        	newPos = new Position(++col, row);
    	}

    	// move up right
    	col = initPos.getColumn();
    	row = initPos.getRow();
    	newPos = new Position(++col, ++row);
    	while(isMoveValid(newPos, state)) {
    		positions.add(newPos);
    		if( state.getPieceAt(newPos) != null)
    			break;
        	newPos = new Position(++col, ++row);
    	}

    	// move up left
    	col = initPos.getColumn();
    	row = initPos.getRow();
    	newPos = new Position(--col, ++row);
    	while(isMoveValid(newPos, state)) {
    		positions.add(newPos);
    		if( state.getPieceAt(newPos) != null)
    			break;
        	newPos = new Position(--col, ++row);
    	}

    	// move down right
    	col = initPos.getColumn();
    	row = initPos.getRow();
    	newPos = new Position(++col, --row);
    	while(isMoveValid(newPos, state)) {
    		positions.add(newPos);
    		if( state.getPieceAt(newPos) != null)
    			break;
        	newPos = new Position(++col, --row);
    	}

    	// move down left
    	col = initPos.getColumn();
    	row = initPos.getRow();
    	newPos = new Position(--col, --row);
    	while(isMoveValid(newPos, state)) {
    		positions.add(newPos);
    		if( state.getPieceAt(newPos) != null)
    			break;
        	newPos = new Position(--col, --row);
    	}

    	return positions;
    }   
}
