package claiton.github.io.bowling.input;

import claiton.github.io.bowling.model.game.Game;

public interface GameLoader {
    Game loadGame(String filePath);
}
