package claiton.github.io.bowling.model.game.score;

import claiton.github.io.bowling.formatter.ResultFormatter;
import claiton.github.io.bowling.exception.ValidationException;
import claiton.github.io.bowling.model.game.score.frame.DefaultFrame;
import claiton.github.io.bowling.model.game.score.frame.Frame;
import claiton.github.io.bowling.model.game.score.frame.LastFrame;
import claiton.github.io.bowling.model.roll.Roll;
import claiton.github.io.bowling.model.roll.RollResult;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * This entity encapsulates the control of a player score by keeping track of all frames
 */
@RequiredArgsConstructor
public class Score {

    @NonNull
    private final int numberOfFrames;
    protected final List<Frame> frames = new ArrayList<>();
    private final ResultFormatter<Score> resultFormatter = new ScoreResultFormatter();
    private Frame currentFrame;

    /**
     * Insert a new roll on players score.
     *
     * @param roll Roll to be inserted
     * @return the result of roll evaluation
     */
    public RollResult newRoll(final Roll roll) {
        preparePlayerFrame();
        final RollResult rollResult = currentFrame.newRoll(roll);
        applyExtraScore(roll);
        return rollResult;
    }

    public int getTotalScore() {
        return frames.stream()
                .mapToInt(Frame::getTotalScore)
                .sum();
    }

    private void applyExtraScore(final Roll roll) {
        frames.stream()
                .filter(frame -> frame != currentFrame && frame.isWaitingExtraScore())
                .forEach(frame -> frame.addExtraScore(roll.getScore()));
    }

    private void preparePlayerFrame() {
        if (currentFrame == null || currentFrame.isFinished()) {
            if (frames.size() == numberOfFrames) {
                throw new ValidationException("This game is over.");
            }
            currentFrame = newFrame();
            frames.add(currentFrame);
        }
    }

    private Frame newFrame() {
        return isLastFrame() ?
                new LastFrame() :
                new DefaultFrame();
    }

    private boolean isLastFrame() {
        return frames.size() + 1 == numberOfFrames;
    }

    @Override
    public String toString() {
        return resultFormatter.getFormattedResult(this);
    }
}
