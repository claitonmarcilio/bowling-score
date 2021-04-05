package claiton.github.io.bowling.model.game;

import claiton.github.io.bowling.formatter.ResultFormatter;

public class GameResultFormatter implements ResultFormatter<Game> {

    @Override
    public String getFormattedResult(Game game) {
        final StringBuilder sb = new StringBuilder();
        appendFrames(game, sb);
        appendScores(game, sb);
        return sb.toString();
    }

    private void appendScores(Game game, StringBuilder sb) {
        game.playerScores.forEach((player, score) -> {
            sb.append(player.getName()).append("\n");
            sb.append(score.toString()).append("\n");
        });
    }

    private void appendFrames(Game game, StringBuilder sb) {
        sb.append("Frame");
        for (int i = 0; i < game.getNumberOfFrames(); i++) {
            sb.append("\t\t").append(i + 1);
        }
        sb.append("\n");
    }
}
