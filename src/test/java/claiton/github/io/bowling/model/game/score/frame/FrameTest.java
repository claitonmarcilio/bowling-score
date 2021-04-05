package claiton.github.io.bowling.model.game.score.frame;

import claiton.github.io.bowling.infra.ValidationException;
import claiton.github.io.bowling.model.game.Game;
import claiton.github.io.bowling.model.game.score.frame.roll.result.evaluator.DefaultFrameRollResultEvaluator;
import claiton.github.io.bowling.model.player.Player;
import claiton.github.io.bowling.model.roll.Roll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FrameTest {

    @Test
    void mustFailWhenFrameScoreIsGreaterThanTen() {
        Frame frame = Frame.builder()
                .rollResultEvaluator(new DefaultFrameRollResultEvaluator())
                .withAdditionalRoll(false)
                .build();

        frame.newRoll(Roll.builder().score(8).build());

        Assertions.assertThrows(ValidationException.class,
                () -> frame.newRoll(Roll.builder().score(8).build()));
    }

    @Test
    void mustFailWhenMissingResultEvaluatorTest() {
        Assertions.assertThrows(NullPointerException.class,
                () -> Frame.builder()
                        .withAdditionalRoll(false)
                        .build());
    }

    @Test
    void initialStatusNotFinishedTest() {
        Frame frame = getSimpleFrame();
        Assertions.assertFalse(frame.isFinished());
    }

    @Test
    void waitingExtraScoreTest() {
        Frame frame = getSimpleFrame();
        Assertions.assertFalse(frame.isWaitingExtraScore());
    }

    private Frame getSimpleFrame() {
        return Frame.builder()
                .rollResultEvaluator(new DefaultFrameRollResultEvaluator())
                .withAdditionalRoll(false)
                .build();
    }
}
