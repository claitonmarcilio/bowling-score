package claiton.github.io.bowling.model.game;

import claiton.github.io.bowling.formatter.ResultFormatter;
import claiton.github.io.bowling.model.game.coordinator.FreePlayersCoordinator;
import claiton.github.io.bowling.model.game.coordinator.OrderedPlayersCoordinator;
import claiton.github.io.bowling.model.game.coordinator.PlayersCoordinator;
import claiton.github.io.bowling.model.game.score.Score;
import claiton.github.io.bowling.model.player.Player;
import claiton.github.io.bowling.model.roll.Roll;
import claiton.github.io.bowling.model.roll.RollResult;
import lombok.NonNull;

import java.util.HashMap;
import java.util.Map;

public class BowlingGame implements Game {

    private static final GameOptions DEFAULT_OPTIONS = BowlingGameOptions.builder().build();

    @NonNull
    private final PlayersCoordinator playersCoordinator;
    @NonNull
    private final ResultFormatter<BowlingGame> resultFormatter;
    @NonNull
    private final int numberOfFrames;

    protected final Map<Player, Score> playerScores = new HashMap<>();

    BowlingGame(final int numberOfFrames, final PlayersCoordinator playersCoordinator, final ResultFormatter<BowlingGame> resultFormatter) {
        this.numberOfFrames = numberOfFrames;
        this.playersCoordinator = playersCoordinator;
        this.resultFormatter = resultFormatter;
    }


    public static BowlingGame newStandardGame() {
        return newGameWithOptions(DEFAULT_OPTIONS);
    }

    public static BowlingGame newGameWithOptions(final GameOptions options) {
        return new BowlingGame(
                options.getNumberOfFrames(),
                options.isCheckPlayersOrder() ? new OrderedPlayersCoordinator() : new FreePlayersCoordinator(),
                new GameResultFormatter()
        );
    }

    @Override
    public void newRoll(final Player player, final Roll roll) {
        playersCoordinator.startPlayerRoll(player);

        final Score playerScore = getPlayerScore(player);
        final RollResult rollResult = playerScore.newRoll(roll);

        playersCoordinator.finalizePlayerRoll(rollResult);
    }

    @Override
    public int getPlayerTotalScore(final Player player) {
        return getPlayerScore(player).getTotalScore();
    }

    private Score getPlayerScore(final Player player) {
        if (!playerScores.containsKey(player)) {
            playerScores.put(player, new Score(getNumberOfFrames()));
        }
        return playerScores.get(player);
    }

    protected int getNumberOfFrames() {
        return numberOfFrames;
    }

    @Override
    public String toString() {
        return resultFormatter.getFormattedResult(this);
    }
}
