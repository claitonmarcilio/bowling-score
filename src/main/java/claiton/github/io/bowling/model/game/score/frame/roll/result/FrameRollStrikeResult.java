package claiton.github.io.bowling.model.game.score.frame.roll.result;

public class FrameRollStrikeResult extends AbstractFrameRollResult {

    public FrameRollStrikeResult(int extraScoreRolls) {
        super(extraScoreRolls);
    }

    @Override
    public String getResultFormat(int score) {
        return "X";
    }
}
