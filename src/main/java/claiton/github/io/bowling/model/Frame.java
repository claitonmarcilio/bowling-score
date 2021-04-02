package claiton.github.io.bowling.model;


public class Frame {

    private final Roll[] rolls = new Roll[3];
    private int rollsCounter = 0;
    private int totalScore;
    private FrameStatus status = FrameStatus.OPEN;

    public void newRoll(Roll roll) {
        this.validate();
        this.rollsCounter++;
        this.totalScore += roll.getScore();
        rolls[rollsCounter] = roll;
        if (rollsCounter == 2 || totalScore == 10) {
            status = FrameStatus.FINISHED;
        }
    }

    public void addExtraScore(final int extraScore) {
        this.totalScore += extraScore;
    }

    private void validate() {
        if (status == FrameStatus.FINISHED) {
            throw new IllegalStateException("This frame is already finished");
        }
    }

    public boolean isFinished() {
        return status == FrameStatus.FINISHED;
    }
}
