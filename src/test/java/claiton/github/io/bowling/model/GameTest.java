package claiton.github.io.bowling.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class GameTest {

    @Test
    void mustFailWhenFrameIsOpen() {
        final Game game = new Game();
        game.newRoll("Player One", 8);
        Assertions.assertThrows(IllegalStateException.class,
                () -> game.newRoll("Player Two", 3));
    }
}