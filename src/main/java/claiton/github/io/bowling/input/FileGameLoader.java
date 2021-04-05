package claiton.github.io.bowling.input;


import claiton.github.io.bowling.exception.ValidationException;
import claiton.github.io.bowling.model.game.BowlingGame;
import claiton.github.io.bowling.model.game.Game;
import claiton.github.io.bowling.model.player.Player;
import claiton.github.io.bowling.model.roll.Roll;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class FileGameLoader implements GameLoader {

    @Override
    public Game loadGame(final String filePath) {

        final Game game = BowlingGame.newStandardGame();

        try (Stream<String> linesStream = getLinesStream(filePath)) {
            linesStream.forEach(line -> {
                String[] lineValues = line.split("\t");
                if (lineValues.length != 2) {
                    throw new ValidationException(String.format("Invalid line: \"%s\".", line));
                }
                final String playerName = lineValues[0];
                game.newRoll(new Player(playerName), getRoll(lineValues[1]));
            });
        }

        return game;
    }

    private Stream<String> getLinesStream(String filePath) {
        final Stream<String> lines;
        try {
            lines = Files.lines(Paths.get(filePath));
        } catch (IOException e) {
            throw new ValidationException(String.format("Invalid filepath: \"%s\".", filePath));
        }
        return lines;
    }

    private Roll getRoll(final String scoreValue) {
        try {
            boolean isFoul = "F".equals(scoreValue);
            return Roll.builder()
                    .foul(isFoul)
                    .score(isFoul ? 0 : Integer.parseInt(scoreValue))
                    .build();
        } catch (NumberFormatException e) {
            throw new ValidationException(String.format("Invalid score value: \"%s\".", scoreValue));
        }
    }
}
