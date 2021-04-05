package claiton.github.io.bowling.model.game;

import claiton.github.io.bowling.exception.ValidationException;
import claiton.github.io.bowling.model.player.Player;
import claiton.github.io.bowling.model.roll.Roll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class GameTest {

    @Test
    void twoPlayersGameTest() {
        final Game game = BowlingGame.newGameWithOptions(BowlingGameOptions.builder()
                .numberOfFrames(1)
                .build());
        final Player playerOne = new Player("Player One");
        final Player playerTwo = new Player("Player Two");

        game.newRoll(playerOne, getRoll(10));
        game.newRoll(playerOne, getRoll(0));
        game.newRoll(playerOne, getRoll(3));

        game.newRoll(playerTwo, getRoll(3));
        game.newRoll(playerTwo, getRoll(2));

        Assertions.assertEquals(13, game.getPlayerTotalScore(playerOne));
        Assertions.assertEquals(5, game.getPlayerTotalScore(playerTwo));
    }

    @Test
    void twoPlayersWithoutOrderGameTest() {
        final Game game = BowlingGame.newGameWithOptions(BowlingGameOptions.builder()
                .checkPlayersOrder(false)
                .build());
        final Player playerOne = new Player("Player One");
        final Player playerTwo = new Player("Player Two");

        game.newRoll(playerOne, getRoll(2));

        Assertions.assertDoesNotThrow(() -> game.newRoll(playerTwo, getRoll(3)));
    }

    @Test
    void singleFrameGameTest() {
        final Game game = BowlingGame.newGameWithOptions(BowlingGameOptions.builder()
                .numberOfFrames(1)
                .build());
        final Player player = new Player("Player One");
        game.newRoll(player, getRoll(10));
        game.newRoll(player, getRoll(10));
        game.newRoll(player, getRoll(10));

        Assertions.assertEquals(30, game.getPlayerTotalScore(player));
    }

    @Test
    void perfectGameTest() {
        final Game game = BowlingGame.newGameWithOptions(BowlingGameOptions.builder()
                .numberOfFrames(2)
                .build());
        final Player player = new Player("Player One");
        game.newRoll(player, getRoll(10));
        game.newRoll(player, getRoll(10));
        game.newRoll(player, getRoll(10));
        game.newRoll(player, getRoll(10));

        Assertions.assertEquals(60, game.getPlayerTotalScore(player));
    }

    @Test
    void foulRollTest() {
        final Game game = BowlingGame.newStandardGame();
        final Player player = new Player("Player One");
        game.newRoll(player, Roll.builder().foul(true).build());

        Assertions.assertEquals(0, game.getPlayerTotalScore(player));
    }

    @Test
    void mustFailWhenPlayerNotFinishFrame() {
        final Player firstPlayer = new Player("Player One");
        final Player secondPlayer = new Player("Player Two");
        final Roll roll = new Roll(8);
        final Game game = BowlingGame.newStandardGame();
        game.newRoll(firstPlayer, roll);
        Assertions.assertThrows(ValidationException.class,
                () -> game.newRoll(secondPlayer, roll));
    }

    private Roll getRoll(int score) {
        return Roll.builder().score(score).build();
    }
}