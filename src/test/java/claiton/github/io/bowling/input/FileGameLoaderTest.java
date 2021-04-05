package claiton.github.io.bowling.input;

import claiton.github.io.bowling.exception.ValidationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FileGameLoaderTest {

    @Test
    void shouldBeAbleToLoadFile() {
        final GameLoader gameLoader = new FileGameLoader();

        Assertions.assertDoesNotThrow(() ->
                gameLoader.loadGame("src/test/resources/small-game.txt"));
    }

    @Test
    void shouldFailWhenInvalidLine() {
        final GameLoader gameLoader = new FileGameLoader();

        Assertions.assertThrows(ValidationException.class, () ->
                gameLoader.loadGame("src/test/resources/invalid-line.txt"));
    }

    @Test
    void shouldFailWhenInvalidScore() {
        final GameLoader gameLoader = new FileGameLoader();

        Assertions.assertThrows(ValidationException.class, () ->
                gameLoader.loadGame("src/test/resources/invalid-score.txt"));
    }
}
