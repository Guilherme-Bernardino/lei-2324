package pt.pa.refactoring.player;

/**
 * @author amfs
 */
public class HumanPlayer extends Player {

    public HumanPlayer(boolean whiteSide)
    {
        this.whiteSide = whiteSide;
        this.humanPlayer = true;
    }
}