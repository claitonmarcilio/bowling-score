package claiton.github.io.bowling.model;

import java.util.ArrayList;
import java.util.List;

public class Score {
    private List<Frame> frames = new ArrayList<>();
    private Frame currentFrame = null;

    public Frame getCurrentFrame() {
        if (currentFrame != null && !currentFrame.isFinished()) {
            return currentFrame;
        }
        if (frames.size() == 10) {
            throw new IllegalStateException("This game is already over!");
        }
        currentFrame = new Frame();
        frames.add(currentFrame);
        return currentFrame;
    }
}
