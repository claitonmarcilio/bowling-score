package claiton.github.io.bowling.model.roll;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RollResult {
    private final int score;
    private final boolean finished;
}
