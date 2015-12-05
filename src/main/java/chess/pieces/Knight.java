package chess.pieces;

import java.util.ArrayList;
import java.util.List;

import chess.GameState;
import chess.Player;
import chess.Position;

/**
 * The Knight class
 */
public class Knight extends Piece {
    public Knight(Player owner) {
        super(owner);
    }

    @Override
    protected char getIdentifyingCharacter() {
        return 'n';
    }

    /**
     * get all possible next positions
     */
    public List<Position> getNextPositions(Position initPos, GameState state) {
    	char col = initPos.getColumn();
    	int row = initPos.getRow();
    	List<Position> positions = new ArrayList<Position>();
    	Position newPos;
    	
    	// 1 up, 2 right
    	newPos = new Position((char)(col + 2), row + 1);
    	if(isMoveValid(newPos, state)) {
    		positions.add(newPos);
    	}
    	
    	// 2 up, 1 right
    	newPos = new Position((char)(col + 1), row + 2);
    	if(isMoveValid(newPos, state)) {
    		positions.add(newPos);
    	}
    	
    	// 1 up, 2 left
    	newPos = new Position((char)(col - 2), row + 1);
    	if(isMoveValid(newPos, state)) {
    		positions.add(newPos);
    	}
    	
    	// 2 up, 1 left
    	newPos = new Position((char)(col - 1), row + 2);
    	if(isMoveValid(newPos, state)) {
    		positions.add(newPos);
    	}
    	
    	// 1 down, 2 right
    	newPos = new Position((char)(col + 2), row - 1);
    	if(isMoveValid(newPos, state)) {
    		positions.add(newPos);
    	}
    	
    	// 2 down, 1 right
    	newPos = new Position((char)(col + 1), row - 2);
    	if(isMoveValid(newPos, state)) {
    		positions.add(newPos);
    	}
    	
    	// 1 down, 2 left
    	newPos = new Position((char)(col - 2), row - 1);
    	if(isMoveValid(newPos, state)) {
    		positions.add(newPos);
    	}
    	
    	// 2 down, 1 left
    	newPos = new Position((char)(col - 1), row - 2);
    	if(isMoveValid(newPos, state)) {
    		positions.add(newPos);
    	}
    	
    	return positions;
    }   
}
