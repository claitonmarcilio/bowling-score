package claiton.github.io.bowling.model;

import claiton.github.io.bowling.exception.ValidationException;
import claiton.github.io.bowling.model.roll.Roll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class RollTest {

    @Test
    void mustReturnScore() {
        final int score = 8;
        final Roll roll = new Roll(score, false);
        Assertions.assertEquals(score, roll.getScore());
    }

    @Test
    void mustFailWhenScoreIsLessThanZero() {
        Assertions.assertThrows(ValidationException.class,
                () -> new Roll(-1, false));
    }

    @Test
    void mustFailWhenScoreIsGreaterThanZero() {
        Assertions.assertThrows(ValidationException.class,
                () -> new Roll(11, false));
    }

}