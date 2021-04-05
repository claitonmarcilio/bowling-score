package claiton.github.io.bowling.model.game.score.frame;

import claiton.github.io.bowling.model.roll.Roll;
import claiton.github.io.bowling.model.roll.RollResult;

public interface Frame {

    RollResult newRoll(final Roll roll);

    void addExtraScore(final int extraScore);

    int getTotalScore();

    boolean isFinished();

    boolean isWaitingExtraScore();

}
