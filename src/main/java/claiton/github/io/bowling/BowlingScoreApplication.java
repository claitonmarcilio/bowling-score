package claiton.github.io.bowling;


import claiton.github.io.bowling.exception.ValidationException;
import claiton.github.io.bowling.input.FileGameLoader;
import claiton.github.io.bowling.input.GameLoader;
import claiton.github.io.bowling.model.game.Game;

public class BowlingScoreApplication {

    public static void main(String[] args) {
        try {
            final String filePath = getFilePath(args);

            final GameLoader gameLoader = new FileGameLoader();
            final Game game = gameLoader.loadGame(filePath);

            System.out.println(game);
        } catch (Throwable error) {
            System.out.println("Error: " + error.getMessage());
        }
    }


    private static String getFilePath(String[] args) {
        if (args.length < 1) {
            throw new ValidationException("The file path is required.");
        }
        return args[0];
    }
}
