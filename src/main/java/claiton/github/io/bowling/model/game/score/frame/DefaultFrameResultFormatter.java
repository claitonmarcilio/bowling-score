package claiton.github.io.bowling.model.game.score.frame;

import claiton.github.io.bowling.infra.ResultFormatter;

public class DefaultFrameResultFormatter implements ResultFormatter<Frame> {

    @Override
    public String getFormattedResult(final Frame frame) {
        if (frame.getSecondRoll().isPresent()) {
            return String.format("%s\t%s", frame.getFirstRoll().get(), frame.getSecondRoll().get());
        }
        return String.format("\t%s", frame.getFirstRoll().get());
    }
}
