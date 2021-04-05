package claiton.github.io.bowling.model.game.score.frame.roll.result.processor;

import claiton.github.io.bowling.model.game.score.frame.roll.FrameRoll;
import claiton.github.io.bowling.model.game.score.frame.roll.result.FrameRollResult;
import claiton.github.io.bowling.model.game.score.frame.roll.result.evaluator.FrameRollResultEvaluator;
import claiton.github.io.bowling.model.roll.Roll;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FrameRollProcessor {

    private final FrameRollResultEvaluator resultEvaluator;

    public FrameRoll processRoll(final Roll roll, final int previousScore, final int rollsCount) {
        FrameRollResult result = resultEvaluator.evaluate(roll, previousScore, rollsCount);
        return new FrameRoll(roll, result);
    }
}
