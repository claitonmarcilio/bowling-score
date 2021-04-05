package claiton.github.io.bowling.input;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FileGameLoaderTest {

    @Test
    void shouldBeAbleToLoadFile() {
        final GameLoader gameLoader = new FileGameLoader();

        Assertions.assertDoesNotThrow(() ->
                gameLoader.loadGame("src/test/resources/small-game.txt"));
    }
}
