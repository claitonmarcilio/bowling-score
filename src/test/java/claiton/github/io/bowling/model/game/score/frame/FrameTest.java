package claiton.github.io.bowling.model.game.score.frame;

import claiton.github.io.bowling.exception.ValidationException;
import claiton.github.io.bowling.model.roll.Roll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FrameTest {

    @Test
    void mustFailWhenFrameScoreIsGreaterThanTen() {
        Frame frame = new DefaultFrame();
        frame.newRoll(Roll.builder().score(8).build());

        Assertions.assertThrows(ValidationException.class,
                () -> frame.newRoll(Roll.builder().score(8).build()));
    }

    @Test
    void initialStatusNotFinishedTest() {
        Frame frame = new DefaultFrame();
        Assertions.assertFalse(frame.isFinished());
    }

    @Test
    void waitingExtraScoreTest() {
        Frame frame = new DefaultFrame();
        Assertions.assertFalse(frame.isWaitingExtraScore());
    }
}
