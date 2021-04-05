package claiton.github.io.bowling.model.roll;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * This entity represents a result of a roll.
 */
@AllArgsConstructor
@Getter
public class RollResult {

    /**
     * Indicates if the player has another roll for current frame.
     */
    private final boolean isFinished;
}
