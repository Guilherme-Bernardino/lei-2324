package pt.pa.refactoring.pieces;

import pt.pa.refactoring.board.Board;
import pt.pa.refactoring.board.Spot;

/**
 * @author amfs
 */
public class King extends Piece {
    private boolean castlingDone = false;

    public King(boolean white)
    {
        super(white);
    }

    @Override
    public int getValue() {
        return 10;
    }

    public boolean isCastlingDone()
    {
        return this.castlingDone;
    }

    public void setCastlingDone(boolean castlingDone)
    {
        this.castlingDone = castlingDone;
    }

    @Override
    public String getSymbol() {
        return isWhite() ? "K" : "k";
    }

    @Override
    public boolean canMove(Board board, Spot start, Spot end)
    {
        // we can't move the piece to a Spot that
        // has a piece of the same color
        if (end.getPiece().isWhite() == this.isWhite()) {
            return false;
        }

        int x = Math.abs(start.getX() - end.getX());
        int y = Math.abs(start.getY() - end.getY());
        if (x + y == 1) {
            // check if this move will not result in the king
            // being attacked if so return true
            return true;
        }

        return this.isValidCastling(board, start, end);
    }

    private boolean isValidCastling(Board board,
                                    Spot start, Spot end)
    {

        if (this.isCastlingDone()) {
            return false;
        }

        // TODO implement castling strategy
        return false;
    }

    public boolean isCastlingMove(Spot start, Spot end)
    {
        // TODO check if the starting and
        // ending position are correct
        return false;
    }
}
