package claiton.github.io.bowling.model.game.score.frame.roll;

import claiton.github.io.bowling.model.game.score.frame.roll.result.FrameRollResult;
import claiton.github.io.bowling.model.roll.Roll;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FrameRoll {
    private final Roll roll;
    private final FrameRollResult result;

    public int getScore() {
        return roll.getScore();
    }

    @Override
    public String toString() {
        return result.getResultFormat(roll.getScore());
    }
}
