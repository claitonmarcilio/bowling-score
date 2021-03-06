package claiton.github.io.bowling.model.roll;

import claiton.github.io.bowling.exception.ValidationException;
import lombok.Builder;
import lombok.Getter;

/**
 * This entity represents a roll.
 */
@Getter
public class Roll {

    /**
     * Roll score. The value must be between 0 and 10.
     */
    private final int score;

    /**
     * Indicates that this rolls is a foul.
     */
    private final boolean foul;

    public Roll(int score) {
        this(score, false);
    }

    @Builder
    public Roll(final int score, final boolean foul) {
        if (score < 0 || score > 10) {
            throw new ValidationException(String.format("Invalid score: %s.", score));
        }
        this.score = score;
        this.foul = foul;
    }
}
