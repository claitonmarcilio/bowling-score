package claiton.github.io.bowling.model.game.score.frame.roll.result.evaluator;

import claiton.github.io.bowling.model.game.score.frame.roll.result.FrameRollResult;
import claiton.github.io.bowling.model.roll.Roll;

public interface FrameRollResultEvaluator {
    FrameRollResult evaluate(Roll roll, int previousScore, int rollsCount);
}
