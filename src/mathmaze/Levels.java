package mathmaze;

import java.util.List;

public class Levels {
    private int answerCount;
    private int timeLimitInSeconds;
    private int correctAnswersToPass; // New variable

    public Levels(int answerCount, int timeLimitInSeconds, int correctAnswersToPass) {
        this.answerCount = answerCount;
        this.timeLimitInSeconds = timeLimitInSeconds;
        this.correctAnswersToPass = correctAnswersToPass;
    }

    public int getAnswerCount() {
        return answerCount;
    }

    public int getTimeLimitInSeconds() {
        return timeLimitInSeconds;
    }

    public int getCorrectAnswersToPass() {
        return correctAnswersToPass;
    }
}
