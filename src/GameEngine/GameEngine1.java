package GameEngine;

import mathmaze.Levels;
import java.awt.image.BufferedImage;
import java.util.Arrays;

public class GameEngine1 {

    private String thePlayer = null;
    private int counter = 0;
    private int score = 0;
    private GameServer theGames = new GameServer();
    private Game current = null;
    private int answeredQuestions = 0;
    private int totalAttempts = 0;
    private int correctAnswers = 0;

    private Levels[] gameLevels;
    private Levels currentLevel;

    public GameEngine1(String player) {
        thePlayer = player;
        initializeGameLevels();
        currentLevel = gameLevels[0];

    }

    private void initializeGameLevels() {
        //----------------------------------level initialize to picture count and time limit
        // Can add more levels as needed if you need
        gameLevels = new Levels[]{
            new Levels(3, 60, 2),     // level 01
            new Levels(8, 150, 7),    // level 02
            new Levels(12, 210, 10),  // level 03
            new Levels(16, 300, 13),  // level 04
            new Levels(20, 420, 17),  // level 05
            new Levels(24, 540, 21),  // level 06
            new Levels(26, 600, 22),  // level 07
            new Levels(28, 780, 23),  // level 08
            new Levels(30, 900, 24),  // level 09
            new Levels(33, 1080, 26), // level 10
            
        };
    }

    public BufferedImage nextGame() {
        if (answeredQuestions <= currentLevel.getAnswerCount()) {
            current = theGames.getRandomGame();
            counter++;

            return current.getImage();
        } else {

        }
        return null;
    }

    public int getNextLevelIndex() {
        for (int i = 0; i < gameLevels.length - 1; i++) {
            if (currentLevel == gameLevels[i]) {
                return i + 1;
            }
        }
        return -1;  // Indicates that there are no more levels
    }
  //game
    public boolean checkSolution(int i) {
        if (!isGameOver()) {
            totalAttempts++;

            if (i == current.getSolution()) {
                score++;
                correctAnswers++;
                answeredQuestions++;  
                return true;  
            } else {
                answeredQuestions++;
                return false;  
            }
        } else {
            return false;
        }
    }

    public void resetAnsweredQuestions() {
        answeredQuestions = 0;
    }

    public void resetCorrectAnswers() {
        correctAnswers = 0;
    }

    public void resetTotalAttempts() {
        totalAttempts = 0;
    }

    private boolean isGameOver() {
        return answeredQuestions >= currentLevel.getAnswerCount();
    }

    private boolean hasEnoughCorrectAnswers() {
        return correctAnswers >= currentLevel.getCorrectAnswersToPass();
    }

    public int getScore() {
        return score;
    }

    public int getGameCounter() {
        return counter;
    }

    public int getTotalAttempts() {
        return totalAttempts;
    }

    public int getCorrectAnswers() {
        return correctAnswers;
    }

    public Levels getCurrentLevel() {
        return currentLevel;
    }

    public Levels[] getGameLevels() {
        return gameLevels;
    }

    public void setCurrentLevel(Levels level) {
        currentLevel = level;
    }

    public int getCurrentLevelNumber() {
        return Arrays.asList(gameLevels).indexOf(currentLevel) + 1;
    }

}
