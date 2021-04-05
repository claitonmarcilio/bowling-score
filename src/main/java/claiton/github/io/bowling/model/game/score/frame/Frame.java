package claiton.github.io.bowling.model.game.score.frame;


import claiton.github.io.bowling.infra.ResultFormatter;
import claiton.github.io.bowling.infra.ValidationException;
import claiton.github.io.bowling.model.game.score.frame.roll.FrameRoll;
import claiton.github.io.bowling.model.game.score.frame.roll.result.FrameRollResult;
import claiton.github.io.bowling.model.game.score.frame.roll.result.evaluator.FrameRollResultEvaluator;
import claiton.github.io.bowling.model.roll.Roll;
import claiton.github.io.bowling.model.roll.RollResult;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Frame {

    protected final List<FrameRoll> rolls = new ArrayList<>();
    private final FrameRollResultEvaluator rollResultEvaluator;
    private final boolean withAdditionalRoll;
    private final ResultFormatter<Frame> resultFormatter;

    @Getter
    private int totalScore;
    private int extraScoreCounter;
    private FrameStatus status;

    @Builder
    public Frame(final boolean withAdditionalRoll, final FrameRollResultEvaluator rollResultEvaluator) {
        Objects.requireNonNull(rollResultEvaluator, "ResultEvaluator not defined");
        this.withAdditionalRoll = withAdditionalRoll;
        this.rollResultEvaluator = rollResultEvaluator;

        //TODO: factory
        resultFormatter = withAdditionalRoll ?
                new AdditionalRollFrameResultFormatter() :
                new DefaultFrameResultFormatter();

        //TODO: state pattern?
        this.status = FrameStatus.WAITING_NEXT_ROLL;
    }

    public RollResult newRoll(final Roll roll) {
        validateRoll(roll.getScore());

        //TODO: return FrameRoll - rollProcessor?
        final FrameRollResult result = rollResultEvaluator.evaluate(roll, totalScore, rolls.size() + 1);
        extraScoreCounter = result.getExtraScoreRolls();

        FrameRoll frameRoll = new FrameRoll(roll, result);
        rolls.add(frameRoll);

        updateTotalScore(roll.getScore());
        updateStatus();

        //TODO: review this return
        return new RollResult(roll.getScore(), isFinished());
    }

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

    protected Optional<FrameRoll> getThirdRoll() {
        return getRoll(2);
    }

    private Optional<FrameRoll> getRoll(final int index) {
        if (rolls.size() < index + 1) {
            return Optional.empty();
        }
        return Optional.of(rolls.get(index));
    }

    private void updateStatus() {
        if (extraScoreCounter > 0) {
            status = FrameStatus.WAITING_EXTRA_SCORE;
        } else if (rolls.size() == 1 || hasAdditionalRoll()) {
            status = FrameStatus.WAITING_NEXT_ROLL;
        } else {
            status = FrameStatus.FINISHED;
        }
    }

    private boolean hasAdditionalRoll() {
        return withAdditionalRoll && madeStrikeOrSpare(totalScore) && rolls.size() < 3;
    }

    private boolean madeStrikeOrSpare(final int totalScore) {
        return totalScore >= 10;
    }

    private void updateTotalScore(final int additionalScore) {
        totalScore += additionalScore;
    }

    private void validateRoll(final int score) {
        int maxScore = withAdditionalRoll ? 30 : 10;
        if (totalScore + score > maxScore) {
            throw new ValidationException("Total score for frame is invalid.");
        }
    }

    @Override
    public String toString() {
        return resultFormatter.getFormattedResult(this);
    }

}
