package pt.pa.refactoring.pieces;

import pt.pa.refactoring.board.Board;
import pt.pa.refactoring.board.Spot;

/**
 * @author amfs
 */
public class Rook extends Piece {

    public Rook(boolean white) {
        super(white);
    }

    @Override
    public int getValue() {
        return 5;
    }

    @Override
    public String getSymbol() {
        return isWhite() ? "R" : "r";
    }

    @Override
    public boolean canMove(Board board, Spot start, Spot end) {
        return false;
    }
}
