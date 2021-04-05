package claiton.github.io.bowling.model.roll;

import claiton.github.io.bowling.exception.ValidationException;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Roll {

    private final int score;
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

    @Override
    public String toString() {
        return foul ? "F" : String.valueOf(score);
    }
}
