package claiton.github.io.bowling.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Game {
    private final List<Player> players = new ArrayList<>();
    private final Map<Player, Score> scores = new HashMap<>();
    private Player currentPlayer;
    private boolean allPlayersSet = false;

    public void newRoll(final String playerName, final int numberOfPinfalls) {
        Player player = new Player(playerName);
        validatePlayer(player);
        Roll roll = new Roll(numberOfPinfalls);
        Frame frame = getPlayerScore(player).getCurrentFrame();
        frame.newRoll(roll);
        defineNextPlayer(frame);
    }

    private void defineNextPlayer(Frame frame) {
        if (frame.isFinished()) {
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

    private void validatePlayer(Player player) {
        if (currentPlayer != null && currentPlayer != player) {
            throw new IllegalStateException("Invalid player");
        }
        if (!allPlayersSet) {
            if (isRepeatingPlayer(player)) {
                allPlayersSet = true;
            } else {
                players.add(player);
            }
        }
        currentPlayer = player;
    }

    private boolean isRepeatingPlayer(Player player) {
        return currentPlayer == null && players.contains(player);
    }

    private Score getPlayerScore(Player player) {
        if (!scores.containsKey(player)) {
            scores.put(player, new Score());
        }
        return scores.get(player);
    }
}
