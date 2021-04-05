package claiton.github.io.bowling.model.game.score.frame.roll.result;

public class FrameRollPartialResult extends AbstractFrameRollResult {

    public FrameRollPartialResult() {
        super(0);
    }

    @Override
    public String getResultFormat(int score) {
        return String.valueOf(score);
    }
}
