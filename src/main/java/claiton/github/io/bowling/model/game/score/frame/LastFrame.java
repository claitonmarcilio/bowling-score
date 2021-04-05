package claiton.github.io.bowling.model.game.score.frame;

import claiton.github.io.bowling.model.game.score.frame.roll.FrameRoll;
import claiton.github.io.bowling.model.game.score.frame.roll.result.evaluator.LastFrameRollResultEvaluator;

import java.util.Optional;

public class LastFrame extends AbstractFrame {

    public LastFrame() {
        super(new LastFrameRollResultEvaluator(), 30);
    }

    @Override
    boolean hasNextRoll() {
        return madeStrikeOrSpare(getTotalScore()) && rolls.size() < 3;
    }

    private Optional<FrameRoll> getThirdRoll() {
        return getRoll(2);
    }

    private boolean madeStrikeOrSpare(final int totalScore) {
        return totalScore >= 10;
    }

    @Override
    public String getFormattedResult(Frame value) {
        if (getThirdRoll().isPresent()) {
            return String.format("%s\t%s\t%s",
                    getFirstRoll().get(),
                    getSecondRoll().get(),
                    getThirdRoll().get()
            );
        }
        return super.getFormattedResult(value);
    }
}
