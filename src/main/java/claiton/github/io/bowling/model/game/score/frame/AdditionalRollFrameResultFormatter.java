package claiton.github.io.bowling.model.game.score.frame;

public class AdditionalRollFrameResultFormatter extends DefaultFrameResultFormatter {

    @Override
    public String getFormattedResult(final Frame frame) {
        if (frame.getThirdRoll().isPresent()) {
            return String.format("%s\t%s\t%s",
                    frame.getFirstRoll().get(),
                    frame.getSecondRoll().get(),
                    frame.getThirdRoll().get()
            );
        }
        return super.getFormattedResult(frame);
    }


}
