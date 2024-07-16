/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package mathmaze;

import GameEngine.GameEngine1;
import mathmaze.db_mathmaze;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.border.Border;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JDialog;

/**
 *
 * @author SANDEEPA
 */
public class AdvancedModeFrom extends javax.swing.JFrame implements ActionListener {

    /**
     * Creates new form BeginnerModeForm
     */
    private GameEngine1 gameEngine;
    private BufferedImage currentGame;
    private final String username;

    private Timer gameTimer;
    private int remainingTimeInSeconds;

    private Levels[] gameLevels;
    private Levels currentLevel;

    public AdvancedModeFrom(String username) {
        initComponents();

        //center the form of the window
        this.setLocationRelativeTo(null);

        initGame();
        initTimer();
        this.username = username; // Store the username
        jLabel_Title.setText("Welcome  " + username + " to Advanced Mode of MathMaze. Enjoy your Game!");
        //jLabel_Title.setForeground(new Color(255,255,255));
    }

    //  ----------------------------------------------------------image Location
    ImageIcon initialIcon_minimize = new ImageIcon("C:\\Users\\sanju\\Documents\\NetBeansProjects\\MathMaze\\src\\images\\LbL_images\\Minimize button.png");
    ImageIcon hoverIcon_minimize = new ImageIcon("C:\\Users\\sanju\\Documents\\NetBeansProjects\\MathMaze\\src\\images\\LbL_images\\Minimize button after clicking.png");

    ImageIcon initialIcon_close = new ImageIcon("C:\\Users\\sanju\\Documents\\NetBeansProjects\\MathMaze\\src\\images\\LbL_images\\Exit button.png");
    ImageIcon hoverIcon_close = new ImageIcon("C:\\Users\\sanju\\Documents\\NetBeansProjects\\MathMaze\\src\\images\\LbL_images\\Exit button after clicking.png");

    ImageIcon initialIcon_viewScores = new ImageIcon("C:\\Users\\sanju\\Documents\\NetBeansProjects\\MathMaze\\src\\images\\LbL_images\\lbl_viewAllScores.png");
    ImageIcon hoverIcon_viewScores = new ImageIcon("C:\\Users\\sanju\\Documents\\NetBeansProjects\\MathMaze\\src\\images\\LbL_images\\lbl_viewAllScores_AfterClicking.png");

    ImageIcon initialIcon_previous = new ImageIcon("C:\\Users\\sanju\\Documents\\NetBeansProjects\\MathMaze\\src\\images\\LbL_images\\Lbl_button_previous.png");
    ImageIcon hoverIcon_previous = new ImageIcon("C:\\Users\\sanju\\Documents\\NetBeansProjects\\MathMaze\\src\\images\\LbL_images\\Lbl_button_previous_afterClicking.png");

    // ----------------------------------------------------load the first image
    private void initGame() {

        gameEngine = new GameEngine1("Player");
        currentGame = gameEngine.nextGame();

        jPanel_tomatoImages.setLayout(new BorderLayout());

        JLabel imageLabel = new JLabel();
        ImageIcon initialImageIcon = new ImageIcon(currentGame);

        jPanel_tomatoImages.add(imageLabel);

        Image originalImage = initialImageIcon.getImage();

        int panelWidth = jPanel_tomatoImages.getWidth();
        int panelHeight = jPanel_tomatoImages.getHeight();
        Image scaledImage = originalImage.getScaledInstance(panelWidth, panelHeight, Image.SCALE_SMOOTH);

        ImageIcon scaledImageIcon = new ImageIcon(scaledImage);
        imageLabel.setIcon(scaledImageIcon);

        JButton[] answerButtons = {
            jButton_0, jButton_1, jButton_2, jButton_3, jButton_4,
            jButton_5, jButton_6, jButton_7, jButton_8, jButton_9
        };

        for (int i = 0; i < answerButtons.length; i++) {
            answerButtons[i].setText(String.valueOf(i));
            answerButtons[i].addActionListener(this);
        }
    }

    //--------------------------------------------------------------------TIMER
    //initialize the timer 
    private void initTimer() {
        remainingTimeInSeconds = gameEngine.getCurrentLevel().getTimeLimitInSeconds();
        System.out.println("Initial Time Limit: " + remainingTimeInSeconds);
        gameTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleTimerTick();
            }
        });
        gameTimer.start();
    }

    //countdown tmer which stop timer whne 0 tik
    private void handleTimerTick() {
        remainingTimeInSeconds--;

        if (remainingTimeInSeconds >= 0) {
            // Format the time as mm:ss
            int minutes = remainingTimeInSeconds / 60;
            int seconds = remainingTimeInSeconds % 60;
            String formattedTime = String.format("%02d:%02d", minutes, seconds);
            jLabel_Timer.setText(formattedTime);
        } else {
            // If the timer has reached 0, handle game over 
            gameTimer.stop();
            handleGameTimer();
        }
    }

    private void handleGameTimer() {
        // Display game over message with total attempts, correct answers, and time 
        JOptionPane.showMessageDialog(
                null, // Replace with your parent component if needed
                "Game Over!\nTotal Attempts: " + gameEngine.getTotalAttempts()
                + "\nCorrect Answers: " + gameEngine.getCorrectAnswers()
                + "\nTime Elapsed: " + (60 - remainingTimeInSeconds) + " seconds",
                "Game Over",
                JOptionPane.INFORMATION_MESSAGE
        );
        stopTimer();
        gameTimer = null;
        //redirect to the game mode form
        GameModeForm form = new GameModeForm(username);
        form.setVisible(true);
        form.pack();
        form.setLocationRelativeTo(null);
        this.dispose();
    }

    private void stopTimer() {
        if (gameTimer != null && gameTimer.isRunning()) {
            gameTimer.stop();
        }
    }

    private void updateScoreLabel() {
        int score = gameEngine.getScore();
        jLabel_YourScore.setText(score + " ");
    }
//------------------------------------------------- check the answers and give new image
    //give question from second image loading
    public void actionPerformed(ActionEvent e) {

        int solution = Integer.parseInt(e.getActionCommand());
        boolean correct = gameEngine.checkSolution(solution);

        System.out.println("ActionPerformed - Correct: " + correct);

        if (correct) {
            System.out.println("Correct solution entered!");
            showMessageDialogWithTimer("Good! Your skill is impressive.");

            currentGame = gameEngine.nextGame();
            checkGameOver();
            jPanel_tomatoImages.removeAll();
            jPanel_tomatoImages.setLayout(new BorderLayout());

            JLabel imageLabel = new JLabel();
            jPanel_tomatoImages.add(imageLabel);

            // Set the new image
            ImageIcon ii = new ImageIcon(currentGame);

            int panelWidth = jPanel_tomatoImages.getWidth();
            int panelHeight = jPanel_tomatoImages.getHeight();

            ImageIcon initialImageIcon = new ImageIcon(currentGame);
            Image originalImage = initialImageIcon.getImage();
            Image scaledImage = originalImage.getScaledInstance(panelWidth, panelHeight, Image.SCALE_SMOOTH);
            ImageIcon scaledImageIcon = new ImageIcon(scaledImage);
            imageLabel.setIcon(scaledImageIcon);

            // ADD JOptionPane.showMessageDialog to display GOOD and close it after 2 secs.
            //jLabel_Comment.setText("Good!");

        } else {
            System.out.println("Not Correct");
            //showMessageDialogWithTimer("Oops. Try again!");

            currentGame = gameEngine.nextGame();
            checkGameOver(); // Check game over condition before updating the image
            jPanel_tomatoImages.removeAll();
            jPanel_tomatoImages.setLayout(new BorderLayout());

            JLabel imageLabel = new JLabel();
            jPanel_tomatoImages.add(imageLabel);

            // Set the new image
            ImageIcon ii = new ImageIcon(currentGame);

            int panelWidth = jPanel_tomatoImages.getWidth();
            int panelHeight = jPanel_tomatoImages.getHeight();

            ImageIcon initialImageIcon = new ImageIcon(currentGame);
            Image originalImage = initialImageIcon.getImage();
            Image scaledImage = originalImage.getScaledInstance(panelWidth, panelHeight, Image.SCALE_SMOOTH);
            ImageIcon scaledImageIcon = new ImageIcon(scaledImage);
            imageLabel.setIcon(scaledImageIcon);

        }

        updateScoreLabel();

        jPanel_tomatoImages.revalidate();
        jPanel_tomatoImages.repaint();

    }

  // - ------------------------------- ------------------------- massge dialog boox pop up timer
    private void showMessageDialogWithTimer(String message) {
        JOptionPane optionPane = new JOptionPane(message, JOptionPane.INFORMATION_MESSAGE);
        JDialog dialog = optionPane.createDialog("Message");

        Timer timer = new Timer(1000, (ActionEvent e) -> {
            dialog.dispose();
        });
        timer.setRepeats(false);
        timer.start();

        dialog.setVisible(true);
    }
//-------------------------------------------------- check the game is over
    private void checkGameOver() {

        System.out.println("Total Attempts: " + gameEngine.getTotalAttempts());
        System.out.println("Correct Answers: " + gameEngine.getCorrectAnswers());
        System.out.println("Correct Answers for the level: " + gameEngine.getCurrentLevel().getCorrectAnswersToPass());

        if (gameEngine.getTotalAttempts() >= gameEngine.getCurrentLevel().getAnswerCount()) {

            if (gameEngine.getCorrectAnswers() >= gameEngine.getCurrentLevel().getCorrectAnswersToPass()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Congratulations!!! You passed the Current level!\nTotal Attempts: " + gameEngine.getTotalAttempts()
                        + "\nCorrect Answers: " + gameEngine.getCorrectAnswers()
                );

                storeLevelProgressToDatabase();

                moveNextLevel();
            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Game Over!!!\nTotal Attempts: " + gameEngine.getTotalAttempts()
                        + "\nCorrect Answers: " + gameEngine.getCorrectAnswers()
                );
                stopTimer();
                gameTimer = null;

                GameModeForm form = new GameModeForm(username);
                form.setVisible(true);
                form.pack();
                form.setLocationRelativeTo(null);
                this.dispose();
            }
        } else {

        }
    }
// ----------------- ------------ if user gives correct answers moved next image questions
    private void moveNextLevel() {
        int nextLevelIndex = gameEngine.getNextLevelIndex();
        if (nextLevelIndex != -1) {
            gameEngine.setCurrentLevel(gameEngine.getGameLevels()[nextLevelIndex]);
            gameEngine.resetAnsweredQuestions();
            gameEngine.resetCorrectAnswers();
            gameEngine.resetTotalAttempts();
            stopTimer();
            gameTimer = null;
            initTimer();
            updateLevelLabel();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Congratulations! You completed the campaign!\nTotal Score: " + gameEngine.getScore()
            );
            this.dispose();
        }
    }

    private void updateLevelLabel() {
        jLabel_Level.setText(gameEngine.getCurrentLevelNumber() + " "); //current level update to label
    }
// ----------------------------------------------------------- update DATABASE

    private void storeLevelProgressToDatabase() {
        try (Connection connection = db_mathmaze.getConnection()) {
            int playerId = getPlayerIdByUsername(username);
            int levelId = gameEngine.getCurrentLevelNumber();
            int correctAnswers = gameEngine.getCorrectAnswers();
            int wrongAnswers = gameEngine.getTotalAttempts() - correctAnswers;

            int timeToCompleteInSeconds = gameEngine.getCurrentLevel().getTimeLimitInSeconds() - remainingTimeInSeconds;
            int minutes = timeToCompleteInSeconds / 60;
            int seconds = timeToCompleteInSeconds % 60;
            String formattedTime = String.format("%02d:%02d", minutes, seconds);

            String insertQuery = "INSERT INTO levelcompletion (player_id, level_id, completed, correct_answers, wrong_answers, time_to_complete) VALUES (?, ?, 'yes', ?, ?, ?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                preparedStatement.setInt(1, playerId);
                preparedStatement.setInt(2, levelId);
                preparedStatement.setInt(3, correctAnswers);
                preparedStatement.setInt(4, wrongAnswers);
                preparedStatement.setString(5, formattedTime);
                preparedStatement.executeUpdate();

                System.out.println("Level progress and score stored to the database.");

            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private int getPlayerIdByUsername(String username) {
        int playerId = -1;

        try (Connection connection = db_mathmaze.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement("SELECT ID FROM users WHERE username = ?")) {

            preparedStatement.setString(1, username);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    playerId = resultSet.getInt("ID");
                    System.out.println("ID is:" + resultSet.getInt("ID"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return playerId;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel_tomatoImages = new javax.swing.JPanel();
        jLabel_minimize = new javax.swing.JLabel();
        jLabel_close = new javax.swing.JLabel();
        jLabel_YourScore = new javax.swing.JLabel();
        jLabel_ViewScore = new javax.swing.JLabel();
        jButton_0 = new javax.swing.JButton();
        jButton_1 = new javax.swing.JButton();
        jButton_3 = new javax.swing.JButton();
        jButton_4 = new javax.swing.JButton();
        jButton_6 = new javax.swing.JButton();
        jButton_7 = new javax.swing.JButton();
        jButton_8 = new javax.swing.JButton();
        jButton_9 = new javax.swing.JButton();
        jButton_2 = new javax.swing.JButton();
        jButton_5 = new javax.swing.JButton();
        jLabel_Level = new javax.swing.JLabel();
        jLabel_Timer = new javax.swing.JLabel();
        jLabel_backword = new javax.swing.JLabel();
        jLabel_Title = new javax.swing.JLabel();
        jLabel_BackGround = new javax.swing.JLabel();
        jLabel_Comment = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(1100, 680));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel_tomatoImages.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(51, 51, 51), new java.awt.Color(51, 51, 51), new java.awt.Color(51, 51, 51), new java.awt.Color(51, 51, 51)));

        javax.swing.GroupLayout jPanel_tomatoImagesLayout = new javax.swing.GroupLayout(jPanel_tomatoImages);
        jPanel_tomatoImages.setLayout(jPanel_tomatoImagesLayout);
        jPanel_tomatoImagesLayout.setHorizontalGroup(
            jPanel_tomatoImagesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 916, Short.MAX_VALUE)
        );
        jPanel_tomatoImagesLayout.setVerticalGroup(
            jPanel_tomatoImagesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 426, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel_tomatoImages, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 90, 920, 430));

        jLabel_minimize.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 24)); // NOI18N
        jLabel_minimize.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/LbL_images/Minimize button.png"))); // NOI18N
        jLabel_minimize.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel_minimize.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_minimizeMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel_minimizeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel_minimizeMouseExited(evt);
            }
        });
        getContentPane().add(jLabel_minimize, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 10, 40, 50));

        jLabel_close.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 24)); // NOI18N
        jLabel_close.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/LbL_images/Exit button.png"))); // NOI18N
        jLabel_close.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel_close.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_closeMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel_closeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel_closeMouseExited(evt);
            }
        });
        getContentPane().add(jLabel_close, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 10, 40, 50));

        jLabel_YourScore.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18)); // NOI18N
        jLabel_YourScore.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Label_Your Score is.png"))); // NOI18N
        getContentPane().add(jLabel_YourScore, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 50, 230, 40));

        jLabel_ViewScore.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 15)); // NOI18N
        jLabel_ViewScore.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/LbL_images/lbl_viewAllScores.png"))); // NOI18N
        jLabel_ViewScore.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel_ViewScore.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_ViewScoreMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel_ViewScoreMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel_ViewScoreMouseExited(evt);
            }
        });
        getContentPane().add(jLabel_ViewScore, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 610, 190, 60));

        jButton_0.setBackground(new java.awt.Color(255, 255, 0));
        jButton_0.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton_0.setText("0");
        jButton_0.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(153, 153, 0), new java.awt.Color(153, 153, 0)));
        getContentPane().add(jButton_0, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 530, 45, 45));

        jButton_1.setBackground(new java.awt.Color(255, 255, 0));
        jButton_1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton_1.setText("1");
        jButton_1.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(153, 153, 0), new java.awt.Color(153, 153, 0)));
        getContentPane().add(jButton_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 530, 45, 45));

        jButton_3.setBackground(new java.awt.Color(255, 255, 0));
        jButton_3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton_3.setText("3");
        jButton_3.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(153, 153, 0), new java.awt.Color(153, 153, 0)));
        getContentPane().add(jButton_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 530, 45, 45));

        jButton_4.setBackground(new java.awt.Color(255, 255, 0));
        jButton_4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton_4.setText("4");
        jButton_4.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(153, 153, 0), new java.awt.Color(153, 153, 0)));
        getContentPane().add(jButton_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 530, 45, 45));

        jButton_6.setBackground(new java.awt.Color(255, 255, 0));
        jButton_6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton_6.setText("6");
        jButton_6.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(153, 153, 0), new java.awt.Color(153, 153, 0)));
        getContentPane().add(jButton_6, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 530, 45, 45));

        jButton_7.setBackground(new java.awt.Color(255, 255, 0));
        jButton_7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton_7.setText("7");
        jButton_7.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(153, 153, 0), new java.awt.Color(153, 153, 0)));
        getContentPane().add(jButton_7, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 530, 45, 45));

        jButton_8.setBackground(new java.awt.Color(255, 255, 0));
        jButton_8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton_8.setText("8");
        jButton_8.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(153, 153, 0), new java.awt.Color(153, 153, 0)));
        getContentPane().add(jButton_8, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 530, 45, 45));

        jButton_9.setBackground(new java.awt.Color(255, 255, 0));
        jButton_9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton_9.setText("9");
        jButton_9.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(153, 153, 0), new java.awt.Color(153, 153, 0)));
        jButton_9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_9ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton_9, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 530, 45, 45));

        jButton_2.setBackground(new java.awt.Color(255, 255, 0));
        jButton_2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton_2.setText("2");
        jButton_2.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(153, 153, 0), new java.awt.Color(153, 153, 0)));
        getContentPane().add(jButton_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 530, 45, 45));

        jButton_5.setBackground(new java.awt.Color(255, 255, 0));
        jButton_5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton_5.setText("5");
        jButton_5.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(153, 153, 0), new java.awt.Color(153, 153, 0)));
        getContentPane().add(jButton_5, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 530, 45, 45));

        jLabel_Level.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 24)); // NOI18N
        jLabel_Level.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Label_Current Level_1.png"))); // NOI18N
        jLabel_Level.setText("1");
        getContentPane().add(jLabel_Level, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 50, 210, 40));

        jLabel_Timer.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18)); // NOI18N
        jLabel_Timer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Label_Time.png"))); // NOI18N
        getContentPane().add(jLabel_Timer, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 50, 150, 40));

        jLabel_backword.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel_backword.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/LbL_images/Lbl_button_previous.png"))); // NOI18N
        jLabel_backword.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel_backword.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_backwordMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel_backwordMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel_backwordMouseExited(evt);
            }
        });
        getContentPane().add(jLabel_backword, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 40, 40));

        jLabel_Title.setFont(new java.awt.Font("Garamond", 1, 30)); // NOI18N
        jLabel_Title.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_Title.setText("Welcome   to Advanced Mode of MathMaze. Enjoy your Game!");
        getContentPane().add(jLabel_Title, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 610, 920, 50));

        jLabel_BackGround.setBackground(new java.awt.Color(255, 255, 255));
        jLabel_BackGround.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Background_gameServer.jpg"))); // NOI18N
        jLabel_BackGround.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        getContentPane().add(jLabel_BackGround, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1100, 680));

        jLabel_Comment.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18)); // NOI18N
        getContentPane().add(jLabel_Comment, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 580, 310, 50));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // -----------------------------------------------------   minimize the form 
    private void jLabel_minimizeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_minimizeMouseEntered

        jLabel_minimize.setIcon(hoverIcon_minimize);
    }//GEN-LAST:event_jLabel_minimizeMouseEntered

    private void jLabel_minimizeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_minimizeMouseExited

        jLabel_minimize.setIcon(initialIcon_minimize);
    }//GEN-LAST:event_jLabel_minimizeMouseExited

    private void jLabel_minimizeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_minimizeMouseClicked

        this.setState(JFrame.ICONIFIED);
    }//GEN-LAST:event_jLabel_minimizeMouseClicked

    //------------------------------------------------------------- close the form 
    private void jLabel_closeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_closeMouseEntered

        jLabel_close.setIcon(hoverIcon_close);
    }//GEN-LAST:event_jLabel_closeMouseEntered

    private void jLabel_closeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_closeMouseExited

        jLabel_close.setIcon(initialIcon_close);
    }//GEN-LAST:event_jLabel_closeMouseExited

    private void jLabel_closeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_closeMouseClicked

        System.exit(0);
    }//GEN-LAST:event_jLabel_closeMouseClicked

    //--------------------------------------------------go to the user dash board
    private void jLabel_ViewScoreMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_ViewScoreMouseEntered

        jLabel_ViewScore.setIcon(hoverIcon_viewScores);
    }//GEN-LAST:event_jLabel_ViewScoreMouseEntered

    private void jLabel_ViewScoreMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_ViewScoreMouseExited

        jLabel_ViewScore.setIcon(initialIcon_viewScores);
    }//GEN-LAST:event_jLabel_ViewScoreMouseExited

    private void jLabel_ViewScoreMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_ViewScoreMouseClicked

        //click the button to enter the Register Form
        UserDashboardForm UDF = new UserDashboardForm(username);
        UDF.setVisible(true);
        UDF.pack();
        UDF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.dispose();
    }//GEN-LAST:event_jLabel_ViewScoreMouseClicked

    //-------------------------------------------------- previous form loading

    private void jLabel_backwordMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_backwordMouseClicked

        stopTimer();
        gameTimer = null;

        GameModeForm form = new GameModeForm(username);
        form.setVisible(true);
        form.pack();
        form.setLocationRelativeTo(null);
        this.dispose();
    }//GEN-LAST:event_jLabel_backwordMouseClicked

    private void jLabel_backwordMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_backwordMouseEntered

        jLabel_backword.setIcon(hoverIcon_previous);
    }//GEN-LAST:event_jLabel_backwordMouseEntered

    private void jLabel_backwordMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_backwordMouseExited

        jLabel_backword.setIcon(initialIcon_previous);
    }//GEN-LAST:event_jLabel_backwordMouseExited

    private void jButton_9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton_9ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
     * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdvancedModeFrom.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                // Provide a default username when creating an instance
                new AdvancedModeFrom("YourDefaultUsername").setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_0;
    private javax.swing.JButton jButton_1;
    private javax.swing.JButton jButton_2;
    private javax.swing.JButton jButton_3;
    private javax.swing.JButton jButton_4;
    private javax.swing.JButton jButton_5;
    private javax.swing.JButton jButton_6;
    private javax.swing.JButton jButton_7;
    private javax.swing.JButton jButton_8;
    private javax.swing.JButton jButton_9;
    private javax.swing.JLabel jLabel_BackGround;
    private javax.swing.JLabel jLabel_Comment;
    private javax.swing.JLabel jLabel_Level;
    private javax.swing.JLabel jLabel_Timer;
    private javax.swing.JLabel jLabel_Title;
    private javax.swing.JLabel jLabel_ViewScore;
    private javax.swing.JLabel jLabel_YourScore;
    private javax.swing.JLabel jLabel_backword;
    private javax.swing.JLabel jLabel_close;
    private javax.swing.JLabel jLabel_minimize;
    private javax.swing.JPanel jPanel_tomatoImages;
    // End of variables declaration//GEN-END:variables

}
