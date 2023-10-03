package pt.pa.refactoring.player;

/**
 * @author amfs
 */
public class ComputerPlayer extends Player {

    public ComputerPlayer(boolean whiteSide)
    {
        this.whiteSide = whiteSide;
        this.humanPlayer = false;
    }
}
