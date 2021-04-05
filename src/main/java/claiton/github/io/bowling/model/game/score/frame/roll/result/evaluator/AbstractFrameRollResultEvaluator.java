package claiton.github.io.bowling.model.game.score.frame.roll.result.evaluator;

import claiton.github.io.bowling.model.game.score.frame.roll.result.*;
import claiton.github.io.bowling.model.roll.Roll;
import lombok.AllArgsConstructor;

@AllArgsConstructor
abstract class AbstractFrameRollResultEvaluator implements FrameRollResultEvaluator {

    private final int strikeExtraScoreRolls;
    private final int spareExtraScoreRolls;

    @Override
    public FrameRollResult evaluate(Roll roll, int previousScore, int rollsCount) {
        if (roll.isFoul()) {
            return new FrameRollFoulResult();
        }
        if (isStrike(roll.getScore(), previousScore, rollsCount)) {
            return new FrameRollStrikeResult(strikeExtraScoreRolls);
        }
        if (isSpare(roll.getScore(), previousScore, rollsCount)) {
            return new FrameRollSpareResult(spareExtraScoreRolls);
        }
        return new FrameRollPartialResult();
    }

    abstract boolean isSpare(int score, int previousScore, int rollsCount);

    abstract boolean isStrike(int score, int previousScore, int rollsCount);
}
