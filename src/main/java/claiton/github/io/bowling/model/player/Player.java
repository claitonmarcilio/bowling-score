package claiton.github.io.bowling.model.player;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * This class represents a player.
 */
@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = "name")
public class Player {
    /**
     * Player's name
     */
    private final String name;
}
