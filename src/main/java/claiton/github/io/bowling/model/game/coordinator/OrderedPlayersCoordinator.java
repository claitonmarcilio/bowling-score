package claiton.github.io.bowling.model.game.coordinator;

import claiton.github.io.bowling.infra.ValidationException;
import claiton.github.io.bowling.model.player.Player;
import claiton.github.io.bowling.model.roll.RollResult;

import java.util.ArrayList;
import java.util.List;

public class OrderedPlayersCoordinator implements PlayersCoordinator {

    private final List<Player> players = new ArrayList<>();
    private Player currentPlayer;
    private boolean allPlayersSet = false;

    @Override
    public void startPlayerRoll(final Player player) {
        if (isNotPlayerTurn(player)) {
            throw new ValidationException(String.format("Invalid player: expected \"%s\", actual \"%s\".", currentPlayer.getName(), player.getName()));
        }
        if (!allPlayersSet && currentPlayer == null) {
            if (isRepeatingPlayer(player)) {
                allPlayersSet = true;
            } else {
                players.add(player);
            }
        }
        currentPlayer = player;
    }

    @Override
    public void finalizePlayerRoll(final RollResult rollResult) {
        if (rollResult.isFinished()) {
            currentPlayer = allPlayersSet ? getNextPlayer() : null;
        }
    }

    private Player getNextPlayer() {
        int currentPlayerIndex = players.indexOf(currentPlayer);
        if (currentPlayerIndex == players.size() - 1) {
            return players.get(0);
        }
        return players.get(currentPlayerIndex + 1);
    }

    private boolean isNotPlayerTurn(final Player player) {
        return currentPlayer != null && !currentPlayer.equals(player);
    }

    private boolean isRepeatingPlayer(final Player player) {
        return players.contains(player);
    }
}
