package claiton.github.io.bowling.model.game.coordinator;

import claiton.github.io.bowling.model.player.Player;
import claiton.github.io.bowling.model.roll.RollResult;

public interface PlayersCoordinator {

    void startPlayerRoll(Player player);

    void finalizePlayerRoll(RollResult rollResult);
}
