package claiton.github.io.bowling;

import claiton.github.io.bowling.model.game.Game;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.stream.Stream;

public class BowlingScoreApplicationIT {

    @Test
    void loadFilesIntegrationTest() throws IOException {
        final File resourcesFolder = new File("src/integration-test/resources/input");
        for (final File file : Objects.requireNonNull(resourcesFolder.listFiles())) {
            final Game game = BowlingScoreApplication.loadGameFromFile(file.getAbsolutePath());
            final String expectedOutput = readFile("src/integration-test/resources/output/" + file.getName());

            Assertions.assertEquals(expectedOutput, game.toString());
        }
    }

    private String readFile(String path) throws IOException {
        final StringBuilder sb = new StringBuilder();
        try (Stream<String> lines = Files.lines(Paths.get(path))) {
            lines.forEach(s -> sb.append(s).append("\n"));
        }
        return sb.toString();
    }
}
