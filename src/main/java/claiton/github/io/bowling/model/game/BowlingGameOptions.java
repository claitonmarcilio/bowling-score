package claiton.github.io.bowling.model.game;

import lombok.Builder;
import lombok.Getter;

/**
 * Bowling Game options.
 */
@Builder
@Getter
public class BowlingGameOptions implements GameOptions {

    public static final int DEFAULT_NUMBER_OF_FRAMES = 10;

    /**
     * Number of frames of this game.
     * Default: {@value #DEFAULT_NUMBER_OF_FRAMES}.
     */
    @Builder.Default
    private final int numberOfFrames = DEFAULT_NUMBER_OF_FRAMES;

    /**
     * Indicates if the order of players during the game is checked.
     * Default: true.
     */
    @Builder.Default
    private final boolean checkPlayersOrder = true;
}
