package claiton.github.io.bowling.model.game.score.frame;

import claiton.github.io.bowling.formatter.ResultFormatter;
import claiton.github.io.bowling.exception.ValidationException;
import claiton.github.io.bowling.model.game.score.frame.roll.FrameRoll;
import claiton.github.io.bowling.model.game.score.frame.roll.result.evaluator.FrameRollResultEvaluator;
import claiton.github.io.bowling.model.game.score.frame.roll.result.processor.FrameRollProcessor;
import claiton.github.io.bowling.model.roll.Roll;
import claiton.github.io.bowling.model.roll.RollResult;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

abstract class AbstractFrame implements Frame, ResultFormatter<Frame> {

    protected final List<FrameRoll> rolls = new ArrayList<>();
    private final FrameRollProcessor rollProcessor;
    private final int maxTotalScore;

    @Getter
    private int totalScore;
    private int extraScoreCounter;
    private FrameStatus status;

    public AbstractFrame(final FrameRollResultEvaluator resultEvaluator, final int maxTotalScore) {
        this.rollProcessor = new FrameRollProcessor(resultEvaluator);
        this.maxTotalScore = maxTotalScore;
        this.status = FrameStatus.WAITING_NEXT_ROLL;
    }

    public RollResult newRoll(final Roll roll) {
        validateRoll(roll);

        FrameRoll frameRoll = rollProcessor.processRoll(roll, totalScore, rolls.size() + 1);
        extraScoreCounter = frameRoll.getExtraScoreRolls();
        rolls.add(frameRoll);

        updateTotalScore(roll.getScore());
        updateStatus();
        return new RollResult(roll.getScore(), isFinished());
    }

    abstract boolean hasNextRoll();

    public void addExtraScore(final int extraScore) {
        totalScore += extraScore;
        extraScoreCounter--;
        if (extraScoreCounter == 0) {
            status = FrameStatus.FINISHED;
        }
    }

    public boolean isFinished() {
        return status != FrameStatus.WAITING_NEXT_ROLL;
    }

    public boolean isWaitingExtraScore() {
        return status == FrameStatus.WAITING_EXTRA_SCORE;
    }

    protected Optional<FrameRoll> getFirstRoll() {
        return getRoll(0);
    }

    protected Optional<FrameRoll> getSecondRoll() {
        return getRoll(1);
    }

    protected Optional<FrameRoll> getRoll(final int index) {
        if (rolls.size() < index + 1) {
            return Optional.empty();
        }
        return Optional.of(rolls.get(index));
    }

    private void validateRoll(final Roll roll) {
        if (totalScore + roll.getScore() > maxTotalScore) {
            throw new ValidationException("Total score for frame is invalid.");
        }
    }

    private void updateStatus() {
        if (extraScoreCounter > 0) {
            status = FrameStatus.WAITING_EXTRA_SCORE;
        } else if (rolls.size() == 1 || hasNextRoll()) {
            status = FrameStatus.WAITING_NEXT_ROLL;
        } else {
            status = FrameStatus.FINISHED;
        }
    }

    private void updateTotalScore(final int additionalScore) {
        totalScore += additionalScore;
    }

    @Override
    public String getFormattedResult(final Frame frame) {
        if (getSecondRoll().isPresent()) {
            return String.format("%s\t%s", getFirstRoll().get(), getSecondRoll().get());
        }
        return String.format("\t%s", getFirstRoll().get());
    }

    @Override
    public String toString() {
        return getFormattedResult(this);
    }

}
