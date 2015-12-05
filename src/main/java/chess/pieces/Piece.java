package chess.pieces;

import java.util.List;

import chess.GameState;
import chess.Player;
import chess.Position;

/**
 * A base class for chess pieces
 */
public abstract class Piece {
    private final Player owner;

    protected Piece(Player owner) {
        this.owner = owner;
    }

    public char getIdentifier() {
        char id = getIdentifyingCharacter();
        if (owner.equals(Player.White)) {
            return Character.toLowerCase(id);
        } else {
            return Character.toUpperCase(id);
        }
    }

    public Player getOwner() {
        return owner;
    }

    protected abstract char getIdentifyingCharacter();

    public abstract List<Position> getNextPositions(Position initPos, GameState state);
    
    /**
     * check if a move is valid
     * @param newPos
     * @param state
     * @return
     */
    protected boolean isMoveValid(Position newPos, GameState state) {
    	return (newPos.isvalid() && (state.getPieceAt(newPos) == null || state.getPieceAt(newPos).getOwner() != this.getOwner()));
    }
}
