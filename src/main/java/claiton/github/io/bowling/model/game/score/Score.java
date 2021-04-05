package claiton.github.io.bowling.model.game.score;

import claiton.github.io.bowling.infra.ResultFormatter;
import claiton.github.io.bowling.infra.ValidationException;
import claiton.github.io.bowling.model.game.score.frame.Frame;
import claiton.github.io.bowling.model.game.score.frame.roll.result.evaluator.DefaultFrameRollResultEvaluator;
import claiton.github.io.bowling.model.game.score.frame.roll.result.evaluator.FrameRollResultEvaluator;
import claiton.github.io.bowling.model.game.score.frame.roll.result.evaluator.LastFrameRollResultEvaluator;
import claiton.github.io.bowling.model.roll.Roll;
import claiton.github.io.bowling.model.roll.RollResult;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class Score {

    @NonNull
    private final int numberOfFrames;
    protected final List<Frame> frames = new ArrayList<>();
    private final ResultFormatter<Score> resultFormatter = new ScoreResultFormatter();
    private Frame currentFrame;

    public RollResult newRoll(final Roll roll) {
        preparePlayerFrame();
        final RollResult rollResult = currentFrame.newRoll(roll);
        applyExtraScore(rollResult);
        return rollResult;
    }

    public int getTotalScore() {
        return frames.stream()
                .mapToInt(Frame::getTotalScore)
                .sum();
    }

    private void applyExtraScore(final RollResult rollResult) {
        frames.stream()
                .filter(frame -> frame != currentFrame && frame.isWaitingExtraScore())
                .forEach(frame -> frame.addExtraScore(rollResult.getScore()));
    }

    private void preparePlayerFrame() {
        if (currentFrame == null || currentFrame.isFinished()) {
            if (frames.size() == numberOfFrames) {
                throw new ValidationException("This game is over.");
            }

            currentFrame = Frame.builder()
                    .rollResultEvaluator(getFrameRollResultEvaluator())
                    .withAdditionalRoll(isLastFrame())
                    .build();

            frames.add(currentFrame);
        }
    }

    private FrameRollResultEvaluator getFrameRollResultEvaluator() {
        return isLastFrame() ?
                new LastFrameRollResultEvaluator() :
                new DefaultFrameRollResultEvaluator();
    }

    private boolean isLastFrame() {
        return frames.size() + 1 == numberOfFrames;
    }

    @Override
    public String toString() {
        return resultFormatter.getFormattedResult(this);
    }
}
