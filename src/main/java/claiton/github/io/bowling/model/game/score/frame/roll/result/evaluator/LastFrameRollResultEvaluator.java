package claiton.github.io.bowling.model.game.score.frame.roll.result.evaluator;

public class LastFrameRollResultEvaluator extends AbstractFrameRollResultEvaluator {

    public LastFrameRollResultEvaluator() {
        super(0, 0);
    }

    @Override
    boolean isSpare(final int rollScore, final int previousScore, final int rollsCount) {
        return rollsCount > 1 && (rollScore + previousScore) / (rollsCount - 1) == 10;
    }

    @Override
    boolean isStrike(final int rollScore, final int previousScore, final int rollsCount) {
        return (rollScore + previousScore) / rollsCount == 10;
    }
}
