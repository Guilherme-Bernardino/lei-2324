package pt.pa.refactoring.game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.pa.refactoring.pieces.Bishop;
import pt.pa.refactoring.pieces.Pawn;
import pt.pa.refactoring.pieces.Piece;
import pt.pa.refactoring.pieces.Rook;
import pt.pa.refactoring.player.ComputerPlayer;
import pt.pa.refactoring.player.HumanPlayer;
import pt.pa.refactoring.player.Player;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    Player p1;
    Player p2;
    Game game;

    @BeforeEach
    public void setup() {
        p1 = new HumanPlayer(true);
        p2 = new ComputerPlayer(false);
        game = new Game(p1, p2);
    }

    @Test
    void assertWhiteMove() throws Exception {
        assertTrue(game.playerMove(p1, 1, 0, 3, 0));
    }

    @Test
    void assertBlackMove() throws Exception {
        game.playerMove(p1, 1, 0, 3, 0);
        assertTrue(game.playerMove(p2, 6, 6, 4, 6));
    }

    @Test
    void assertGetValueCorrect() {
        Piece p = new Pawn(false);
        Piece b = new Bishop(false);
        Piece r = new Rook(false);
        assertEquals(1, p.getValue());
        assertEquals(3, b.getValue());
        assertEquals(5, r.getValue());
    }
}