package claiton.github.io.bowling.model.game;

import claiton.github.io.bowling.model.game.coordinator.FreePlayersCoordinator;
import claiton.github.io.bowling.model.game.coordinator.OrderedPlayersCoordinator;

public class GameFactory {

    private static final GameOptions DEFAULT_OPTIONS = GameOptions.builder().build();

    public Game newGame() {
        return newGame(DEFAULT_OPTIONS);
    }

    public Game newGame(final GameOptions options) {
        return Game.builder()
                .numberOfFrames(options.getNumberOfFrames())
                .playersCoordinator(options.isCheckPlayersOrder() ? new OrderedPlayersCoordinator() : new FreePlayersCoordinator())
                .resultFormatter(new GameResultFormatter())
                .build();
    }
}
