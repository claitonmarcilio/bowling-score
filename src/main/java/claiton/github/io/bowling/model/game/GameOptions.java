package claiton.github.io.bowling.model.game;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class GameOptions {

    public static final int DEFAULT_NUMBER_OF_FRAMES = 10;

    @Builder.Default
    private final int numberOfFrames = DEFAULT_NUMBER_OF_FRAMES;
    @Builder.Default
    private final boolean checkPlayersOrder = true;
}
