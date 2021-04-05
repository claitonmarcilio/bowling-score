package claiton.github.io.bowling.model.game.score.frame.roll.result;

public class FrameRollSpareResult extends AbstractFrameRollResult {

    public FrameRollSpareResult(int extraScoreRolls) {
        super(extraScoreRolls);
    }

    @Override
    public String getResultFormat(int score) {
        return "/";
    }
}
