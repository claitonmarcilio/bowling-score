package claiton.github.io.bowling.model.game;

import claiton.github.io.bowling.formatter.ResultFormatter;

/**
 * Formatter for game result.
 */
public class GameResultFormatter implements ResultFormatter<BowlingGame> {

    @Override
    public String getFormattedResult(final BowlingGame game) {
        final StringBuilder sb = new StringBuilder();
        appendFrames(game, sb);
        appendScores(game, sb);
        return sb.toString();
    }

    private void appendScores(final BowlingGame game, final StringBuilder sb) {
        game.playerScores.forEach((player, score) -> {
            sb.append(player.getName()).append("\n");
            sb.append(score.toString()).append("\n");
        });
    }

    private void appendFrames(final BowlingGame game, final StringBuilder sb) {
        sb.append("Frame");
        for (int i = 0; i < game.getNumberOfFrames(); i++) {
            sb.append("\t\t").append(i + 1);
        }
        sb.append("\n");
    }
}
