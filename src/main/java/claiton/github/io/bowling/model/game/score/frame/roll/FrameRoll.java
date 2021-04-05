package claiton.github.io.bowling.model.game.score.frame.roll;

import claiton.github.io.bowling.model.game.score.frame.roll.result.FrameRollResult;
import claiton.github.io.bowling.model.roll.Roll;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FrameRoll {
    private final Roll roll;
    private final FrameRollResult result;

    @Override
    public String toString() {
        return result.getResultFormat(roll.getScore());
    }

    public int getExtraScoreRolls() {
        return result.getExtraScoreRolls();
    }
}
