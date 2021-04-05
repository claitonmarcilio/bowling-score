package claiton.github.io.bowling.model.game.score.frame;


import claiton.github.io.bowling.model.game.score.frame.roll.result.evaluator.DefaultFrameRollResultEvaluator;

public class DefaultFrame extends AbstractFrame {

    public DefaultFrame() {
        super(new DefaultFrameRollResultEvaluator(), 10);
    }

    @Override
    boolean hasNextRoll() {
        return false;
    }
}
