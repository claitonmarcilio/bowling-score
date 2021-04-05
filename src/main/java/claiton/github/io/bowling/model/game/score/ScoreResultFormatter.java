package claiton.github.io.bowling.model.game.score;

import claiton.github.io.bowling.formatter.ResultFormatter;
import claiton.github.io.bowling.model.game.score.frame.Frame;

public class ScoreResultFormatter implements ResultFormatter<Score> {
    private StringBuilder pinfallsStringBuilder;
    private StringBuilder scoreStringBuilder;
    private int cumulativeScore;

    @Override
    public String getFormattedResult(final Score score) {
        initialize();
        pinfallsStringBuilder.append("Pinfalls");
        scoreStringBuilder.append("Score")
                .append("\t")
                .append("\t");

        for (Frame frame : score.frames) {
            appendPinfalls(frame);
            appendScore(frame);
        }

        return pinfallsStringBuilder
                .append("\n")
                .append(scoreStringBuilder.toString())
                .toString();
    }

    private void initialize() {
        pinfallsStringBuilder = new StringBuilder();
        scoreStringBuilder = new StringBuilder();
        cumulativeScore = 0;
    }

    private void appendPinfalls(Frame frame) {
        pinfallsStringBuilder
                .append("\t")
                .append(frame.toString());
    }

    private void appendScore(Frame frame) {
        cumulativeScore += frame.getTotalScore();
        scoreStringBuilder
                .append(cumulativeScore)
                .append("\t")
                .append("\t");
    }
}
