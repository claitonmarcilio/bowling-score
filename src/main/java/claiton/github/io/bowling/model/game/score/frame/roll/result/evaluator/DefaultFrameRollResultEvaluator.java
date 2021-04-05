package claiton.github.io.bowling.model.game.score.frame.roll.result.evaluator;

public class DefaultFrameRollResultEvaluator extends AbstractFrameRollResultEvaluator {

    public DefaultFrameRollResultEvaluator() {
        super(2, 1);
    }

    @Override
    boolean isSpare(final int score, final int previousScore, final int rollsCount) {
        return score + previousScore == 10;
    }

    @Override
    boolean isStrike(final int score, final int previousScore, final int rollsCount) {
        return score == 10 && rollsCount == 1;
    }
}
