/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package mathmaze;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author SANDEEPA
 */
public class GameModeForm extends javax.swing.JFrame {

    private final String username;

    /**
     * Creates new form GameMode
     */
    public GameModeForm(String username) {
        initComponents();

        // Center the form on the window
        this.setLocationRelativeTo(null);

        // Store the username in the field
        this.username = username;
        jLabel6.setText("Hello, " + username);

    }

    ImageIcon initialIcon_minimize = new ImageIcon("C:\\Users\\sanju\\Documents\\NetBeansProjects\\MathMaze\\src\\images\\LbL_images\\Minimize button.png");
    ImageIcon hoverIcon_minimize = new ImageIcon("C:\\Users\\sanju\\Documents\\NetBeansProjects\\MathMaze\\src\\images\\LbL_images\\Minimize button after clicking.png");

    ImageIcon initialIcon_close = new ImageIcon("C:\\Users\\sanju\\Documents\\NetBeansProjects\\MathMaze\\src\\images\\LbL_images\\Exit button.png");
    ImageIcon hoverIcon_close = new ImageIcon("C:\\Users\\sanju\\Documents\\NetBeansProjects\\MathMaze\\src\\images\\LbL_images\\Exit button after clicking.png");

    ImageIcon initialIcon_beginnerMode = new ImageIcon("C:\\Users\\sanju\\Documents\\NetBeansProjects\\MathMaze\\src\\images\\LbL_images\\Label_beginnerMode_button.png");
    ImageIcon hoverIcon_beginnerMode = new ImageIcon("C:\\Users\\sanju\\Documents\\NetBeansProjects\\MathMaze\\src\\images\\LbL_images\\Label_beginnerMode_button_afterclicking.png");

    ImageIcon initialIcon_advancedMode = new ImageIcon("C:\\Users\\sanju\\Documents\\NetBeansProjects\\MathMaze\\src\\images\\LbL_images\\Label_advancedMode_button.png");
    ImageIcon hoverIcon_advancedMode = new ImageIcon("C:\\Users\\sanju\\Documents\\NetBeansProjects\\MathMaze\\src\\images\\LbL_images\\Label_advancedMode_button_afterclicking.png");

    ImageIcon initialIcon_viewScores = new ImageIcon("C:\\Users\\sanju\\Documents\\NetBeansProjects\\MathMaze\\src\\images\\LbL_images\\lbl_viewAllScores.png");
    ImageIcon hoverIcon_viewScores = new ImageIcon("C:\\Users\\sanju\\Documents\\NetBeansProjects\\MathMaze\\src\\images\\LbL_images\\lbl_viewAllScores_AfterClicking.png");
    
    ImageIcon initialIcon_previous = new ImageIcon("C:\\Users\\sanju\\Documents\\NetBeansProjects\\MathMaze\\src\\images\\LbL_images\\Lbl_button_previous.png");
    ImageIcon hoverIcon_previous = new ImageIcon("C:\\Users\\sanju\\Documents\\NetBeansProjects\\MathMaze\\src\\images\\LbL_images\\Lbl_button_previous_afterClicking.png");

    ImageIcon initialIcon_next = new ImageIcon("C:\\Users\\sanju\\Documents\\NetBeansProjects\\MathMaze\\src\\images\\LbL_images\\Lbl_button_forward.png");
    ImageIcon hoverIcon_next = new ImageIcon("C:\\Users\\sanju\\Documents\\NetBeansProjects\\MathMaze\\src\\images\\LbL_images\\Lbl_button_forward_afterClicking.png");

    private int getBeginnerScore(int playerId) throws SQLException {
        // Query to get the user's score in the beginner mode
        String query = "SELECT MAX(`score`) AS highestScore FROM `beginner` WHERE `player_id` = ?";
        try (PreparedStatement preparedStatement = db_mathmaze.getConnection().prepareStatement(query)) {
            preparedStatement.setInt(1, playerId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("highestScore");
                }
            }
        }
        return 0; // Default value if the score is not found
    }

    private int getPlayerIdByUsername(String username) throws SQLException {
        int playerId = -1;
        String playerIdQuery = "SELECT `ID` FROM `users` WHERE `username` = ?";
        try (PreparedStatement playerIdStatement = db_mathmaze.getConnection().prepareStatement(playerIdQuery)) {
            playerIdStatement.setString(1, username);

            try (ResultSet playerIdResultSet = playerIdStatement.executeQuery()) {
                if (playerIdResultSet.next()) {
                    playerId = playerIdResultSet.getInt("ID");
                }
            }
        }
        return playerId;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel_GameMode = new javax.swing.JLabel();
        jLabel_PreferedMode = new javax.swing.JLabel();
        jLabel_Minimize = new javax.swing.JLabel();
        jLabel_Close = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel_beginnerMode_button = new javax.swing.JLabel();
        jLabel_advancedMode_button = new javax.swing.JLabel();
        jLabel_ViewYourScoresFromHere = new javax.swing.JLabel();
        jLabel_viewAllScores = new javax.swing.JLabel();
        jLabel_previousButton = new javax.swing.JLabel();
        jLabel_nextPageButton = new javax.swing.JLabel();
        jLabel_backGround = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel_GameMode.setFont(new java.awt.Font("Gabriola", 1, 52)); // NOI18N
        jLabel_GameMode.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Lable_Game_Modes.png"))); // NOI18N
        getContentPane().add(jLabel_GameMode, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 120, 410, 70));

        jLabel_PreferedMode.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 20)); // NOI18N
        jLabel_PreferedMode.setText("Please Select Your Prefered Mode to Play the Game.");
        getContentPane().add(jLabel_PreferedMode, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 280, 510, 50));

        jLabel_Minimize.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 24)); // NOI18N
        jLabel_Minimize.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/LbL_images/Minimize button.png"))); // NOI18N
        jLabel_Minimize.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel_Minimize.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_MinimizeMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel_MinimizeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel_MinimizeMouseExited(evt);
            }
        });
        getContentPane().add(jLabel_Minimize, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 10, 40, 40));

        jLabel_Close.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18)); // NOI18N
        jLabel_Close.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/LbL_images/Exit button.png"))); // NOI18N
        jLabel_Close.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel_Close.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_CloseMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel_CloseMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel_CloseMouseExited(evt);
            }
        });
        getContentPane().add(jLabel_Close, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 10, 40, 40));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel6.setText("Hello, {player name}");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 200, 240, 60));

        jLabel_beginnerMode_button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/LbL_images/Label_beginnerMode_button.png"))); // NOI18N
        jLabel_beginnerMode_button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel_beginnerMode_button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_beginnerMode_buttonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel_beginnerMode_buttonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel_beginnerMode_buttonMouseExited(evt);
            }
        });
        getContentPane().add(jLabel_beginnerMode_button, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 340, 320, 100));

        jLabel_advancedMode_button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/LbL_images/Label_advancedMode_button.png"))); // NOI18N
        jLabel_advancedMode_button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel_advancedMode_button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_advancedMode_buttonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel_advancedMode_buttonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel_advancedMode_buttonMouseExited(evt);
            }
        });
        getContentPane().add(jLabel_advancedMode_button, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 340, 320, 100));

        jLabel_ViewYourScoresFromHere.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Lable_View Your Scores from here.png"))); // NOI18N
        getContentPane().add(jLabel_ViewYourScoresFromHere, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 500, 400, 40));

        jLabel_viewAllScores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/LbL_images/lbl_viewAllScores.png"))); // NOI18N
        jLabel_viewAllScores.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel_viewAllScores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_viewAllScoresMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel_viewAllScoresMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel_viewAllScoresMouseExited(evt);
            }
        });
        getContentPane().add(jLabel_viewAllScores, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 490, 190, -1));

        jLabel_previousButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/LbL_images/Lbl_button_previous.png"))); // NOI18N
        jLabel_previousButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel_previousButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_previousButtonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel_previousButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel_previousButtonMouseExited(evt);
            }
        });
        getContentPane().add(jLabel_previousButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 40, 50));

        jLabel_nextPageButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/LbL_images/Lbl_button_forward.png"))); // NOI18N
        jLabel_nextPageButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel_nextPageButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_nextPageButtonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel_nextPageButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel_nextPageButtonMouseExited(evt);
            }
        });
        getContentPane().add(jLabel_nextPageButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, 40, 50));

        jLabel_backGround.setBackground(new java.awt.Color(153, 0, 153));
        jLabel_backGround.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Background_gameMode.png"))); // NOI18N
        getContentPane().add(jLabel_backGround, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1050, 650));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //-----------------------------------------------------------minimize screen

    private void jLabel_MinimizeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_MinimizeMouseEntered

        jLabel_Minimize.setIcon(hoverIcon_minimize);
    }//GEN-LAST:event_jLabel_MinimizeMouseEntered

    private void jLabel_MinimizeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_MinimizeMouseExited

        jLabel_Minimize.setIcon(initialIcon_minimize);
    }//GEN-LAST:event_jLabel_MinimizeMouseExited

    private void jLabel_MinimizeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_MinimizeMouseClicked

        //minimize the screen
        this.setState(JFrame.ICONIFIED);
    }//GEN-LAST:event_jLabel_MinimizeMouseClicked

    //---------------------------------------------------------close screen

    private void jLabel_CloseMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_CloseMouseEntered

        jLabel_Close.setIcon(hoverIcon_close);
    }//GEN-LAST:event_jLabel_CloseMouseEntered

    private void jLabel_CloseMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_CloseMouseExited

        jLabel_Close.setIcon(initialIcon_close);
    }//GEN-LAST:event_jLabel_CloseMouseExited

    private void jLabel_CloseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_CloseMouseClicked
        //cloe the login window
        System.exit(0);
    }//GEN-LAST:event_jLabel_CloseMouseClicked

    //--------------------------------------------- beginner mode label button
    private void jLabel_beginnerMode_buttonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_beginnerMode_buttonMouseEntered

        jLabel_beginnerMode_button.setIcon(hoverIcon_beginnerMode);
    }//GEN-LAST:event_jLabel_beginnerMode_buttonMouseEntered

    private void jLabel_beginnerMode_buttonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_beginnerMode_buttonMouseExited

        jLabel_beginnerMode_button.setIcon(initialIcon_beginnerMode);
    }//GEN-LAST:event_jLabel_beginnerMode_buttonMouseExited

    private void jLabel_beginnerMode_buttonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_beginnerMode_buttonMouseClicked

        BeginnerModeForm BMF = new BeginnerModeForm(username);
        BMF.setVisible(true);
        BMF.pack();
        BMF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.dispose();
    }//GEN-LAST:event_jLabel_beginnerMode_buttonMouseClicked

    //---------------------------------------------------advanced label button

    private void jLabel_advancedMode_buttonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_advancedMode_buttonMouseEntered

        jLabel_advancedMode_button.setIcon(hoverIcon_advancedMode);
    }//GEN-LAST:event_jLabel_advancedMode_buttonMouseEntered

    private void jLabel_advancedMode_buttonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_advancedMode_buttonMouseExited

        jLabel_advancedMode_button.setIcon(initialIcon_advancedMode);
    }//GEN-LAST:event_jLabel_advancedMode_buttonMouseExited

    private void jLabel_advancedMode_buttonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_advancedMode_buttonMouseClicked

        try {
           //set the correction count to mode that the player should play from the beginner mode
            int playerId = getPlayerIdByUsername(username);

            int beginnerScore = getBeginnerScore(playerId);
            
            if (beginnerScore >= 5) {
                AdvancedModeFrom AMF = new AdvancedModeFrom(username);
                AMF.setVisible(true);
                AMF.pack();
                AMF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                this.dispose();
            } else {

                JOptionPane.showMessageDialog(this, "You need a beginner score of at least 5 to access Advanced Mode.", "Score Requirement", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDashboardForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jLabel_advancedMode_buttonMouseClicked

    //----------------------------------------------------------view all scores

    private void jLabel_viewAllScoresMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_viewAllScoresMouseEntered

        jLabel_viewAllScores.setIcon(hoverIcon_viewScores);
    }//GEN-LAST:event_jLabel_viewAllScoresMouseEntered

    private void jLabel_viewAllScoresMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_viewAllScoresMouseExited

        jLabel_viewAllScores.setIcon(initialIcon_viewScores);
    }//GEN-LAST:event_jLabel_viewAllScoresMouseExited

    private void jLabel_viewAllScoresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_viewAllScoresMouseClicked

        UserDashboardForm UDF = new UserDashboardForm(username);
        UDF.setVisible(true);
        UDF.pack();
        UDF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.dispose();
    }//GEN-LAST:event_jLabel_viewAllScoresMouseClicked

    //------------------------------------------previous page login to login page

    private void jLabel_previousButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_previousButtonMouseEntered

        jLabel_previousButton.setIcon(hoverIcon_previous);
    }//GEN-LAST:event_jLabel_previousButtonMouseEntered

    private void jLabel_previousButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_previousButtonMouseExited

        jLabel_previousButton.setIcon(initialIcon_previous);
    }//GEN-LAST:event_jLabel_previousButtonMouseExited

    private void jLabel_previousButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_previousButtonMouseClicked

        RulesForm Rf = new RulesForm();
        Rf.setVisible(true);
        Rf.pack();
        Rf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.dispose();
    }//GEN-LAST:event_jLabel_previousButtonMouseClicked

    //----------------------------------------------------------------load next page 

    private void jLabel_nextPageButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_nextPageButtonMouseEntered

        jLabel_nextPageButton.setIcon(hoverIcon_next);
    }//GEN-LAST:event_jLabel_nextPageButtonMouseEntered

    private void jLabel_nextPageButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_nextPageButtonMouseExited

        jLabel_nextPageButton.setIcon(initialIcon_next);
    }//GEN-LAST:event_jLabel_nextPageButtonMouseExited

    private void jLabel_nextPageButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_nextPageButtonMouseClicked

        UserDashboardForm UDF = new UserDashboardForm(username);
        UDF.setVisible(true);
        UDF.pack();
        UDF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.dispose();
    }//GEN-LAST:event_jLabel_nextPageButtonMouseClicked

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
            java.util.logging.Logger.getLogger(GameModeForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                // Provide a username when creating an instance
                new GameModeForm("YourUsername").setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel_Close;
    private javax.swing.JLabel jLabel_GameMode;
    private javax.swing.JLabel jLabel_Minimize;
    private javax.swing.JLabel jLabel_PreferedMode;
    private javax.swing.JLabel jLabel_ViewYourScoresFromHere;
    private javax.swing.JLabel jLabel_advancedMode_button;
    private javax.swing.JLabel jLabel_backGround;
    private javax.swing.JLabel jLabel_beginnerMode_button;
    private javax.swing.JLabel jLabel_nextPageButton;
    private javax.swing.JLabel jLabel_previousButton;
    private javax.swing.JLabel jLabel_viewAllScores;
    // End of variables declaration//GEN-END:variables
}