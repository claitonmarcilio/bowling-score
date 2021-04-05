package claiton.github.io.bowling.model.game;

import claiton.github.io.bowling.model.player.Player;
import claiton.github.io.bowling.model.roll.Roll;

/**
 * This entity represents a game.
 * It's implementations should ensure all rules and interactions with players and rolls.
 */
public interface Game {
    void newRoll(Player player, Roll roll);

    int getPlayerTotalScore(Player player);
}
