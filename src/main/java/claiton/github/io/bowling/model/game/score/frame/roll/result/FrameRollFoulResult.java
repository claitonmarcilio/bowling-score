package claiton.github.io.bowling.model.game.score.frame.roll.result;

public class FrameRollFoulResult extends AbstractFrameRollResult {

    public FrameRollFoulResult() {
        super(0);
    }

    @Override
    public String getResultFormat(int score) {
        return "F";
    }
}
