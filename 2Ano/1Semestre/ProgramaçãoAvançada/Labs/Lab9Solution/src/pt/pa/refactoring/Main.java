package pt.pa.refactoring;

import pt.pa.refactoring.game.Game;
import pt.pa.refactoring.player.ComputerPlayer;
import pt.pa.refactoring.player.HumanPlayer;
import pt.pa.refactoring.player.Player;

/**
 * @author amfs
 */
public class Main {

    // Incomplete chess game
    public static void main(String[] args) {
        Player p1 = new HumanPlayer(true);
        Player p2 = new ComputerPlayer(false);
        Game game = new Game(p1, p2);

        System.out.println(game.getStatus());
        System.out.println(game.drawBoard());

        try {
            game.playerMove(p1, 1, 0, 3, 0);
            game.playerMove(p2, 6, 6, 4, 6);
            System.out.println(game.drawBoard());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
