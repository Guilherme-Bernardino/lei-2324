package pt.pa.refactoring.pieces;

import pt.pa.refactoring.board.Board;
import pt.pa.refactoring.board.Spot;

/**
 * @author amfs
 */
public class Bishop extends Piece {

    public Bishop(boolean white) {
        super(white);
    }

    @Override
    public int getValue() {
        return 3;
    }

    @Override
    public String getSymbol() {
        return isWhite() ? "B" : "b";
    }

    @Override
    public boolean canMove(Board board, Spot start, Spot end) {
        return false;
    }
}
