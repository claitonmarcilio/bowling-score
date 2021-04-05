package claiton.github.io.bowling.model.game.score.frame.roll.result;

public abstract class AbstractFrameRollResult implements FrameRollResult {

    private final int extraScoreRolls;

    public AbstractFrameRollResult(int extraScoreRolls) {
        this.extraScoreRolls = extraScoreRolls;
    }

    @Override
    public int getExtraScoreRolls() {
        return extraScoreRolls;
    }
}
