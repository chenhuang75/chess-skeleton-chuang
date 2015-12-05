package chess.pieces;

import java.util.ArrayList;
import java.util.List;

import chess.GameState;
import chess.Player;
import chess.Position;

/**
 * The Pawn
 */
public class Pawn extends Piece {
    public Pawn(Player owner) {
        super(owner);
    }

    @Override
    protected char getIdentifyingCharacter() {
        return 'p';
    }

    /**
     * get all possible next positions
     */
    public List<Position> getNextPositions(Position initPos, GameState state) {
    	char col;
    	int row;
    	List<Position> positions = new ArrayList<Position>();
    	Position newPos;
    	
    	if( getOwner() == Player.White) {
        	// move up
        	col = initPos.getColumn();
        	row = initPos.getRow();
    		newPos = new Position(col, ++row);
    		if(isMoveValid(newPos, state) && state.getPieceAt(newPos) == null) {
    			positions.add(newPos);
    			if(row == 3) {
    				newPos = new Position(col, ++row);
    				if(isMoveValid(newPos, state) && state.getPieceAt(newPos) == null) {
    					positions.add(newPos);
    				}
    			}
    		}
    		
    		// move up left
        	col = initPos.getColumn();
        	row = initPos.getRow();
    		newPos = new Position(--col, ++row);
    		if(isMoveValid(newPos, state) && state.getPieceAt(newPos) != null) {
    			positions.add(newPos);
    		}

    		// move up right
        	col = initPos.getColumn();
        	row = initPos.getRow();
    		newPos = new Position(++col, ++row);
    		if(isMoveValid(newPos, state) && state.getPieceAt(newPos) != null) {
    			positions.add(newPos);
    		}
    	} else {
    		// move down
        	col = initPos.getColumn();
        	row = initPos.getRow();
    		newPos = new Position(col, --row);
    		if(isMoveValid(newPos, state) && state.getPieceAt(newPos) == null ) {
    			positions.add(newPos);
    			if(row == 6) {
    				newPos = new Position(col, --row);
    				if(isMoveValid(newPos, state) && state.getPieceAt(newPos) == null ) {
    					positions.add(newPos);
    				}
    			}
    		}

    		// move down left
        	col = initPos.getColumn();
        	row = initPos.getRow();
    		newPos = new Position(--col, --row);
    		if(isMoveValid(newPos, state) && state.getPieceAt(newPos) != null) {
    			positions.add(newPos);
    		}

    		// move down right
        	col = initPos.getColumn();
        	row = initPos.getRow();
    		newPos = new Position(++col, --row);
    		if(isMoveValid(newPos, state) && state.getPieceAt(newPos) != null) {
    			positions.add(newPos);
    		}
    	}

    	return positions;
    }   
}
