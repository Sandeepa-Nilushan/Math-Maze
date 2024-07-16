/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package mathmaze;

import java.awt.Color;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

public class UserDashboardForm extends javax.swing.JFrame {

    private final String username;

    public UserDashboardForm(String username) {
        initComponents();
        populateUserProgressTable(username);

        //center the form of the window
        this.setLocationRelativeTo(null);

        //import users_name from data table
        this.username = username;
        jLabel_welcome.setText("Well Played ! " + username + ",");

        // Fetch and display the user's highest score and time
        Object[] userStats = getUserStats(username);
        if (userStats[0] != null) {
            lbl_score.setText(userStats[0].toString()); //-----highest Score
        } else {
            lbl_score.setText("N/A"); // Set a default value
        }

        if (userStats[1] != null) {
            lbl_time.setText(userStats[1].toString()); //----------best Time
        } else {
            lbl_time.setText("N/A"); // Set a default value
        }
    }
//-------------------------------------------------------------------- image locations which i used ffor the labels
    ImageIcon initialIcon_minimize = new ImageIcon("C:\\Users\\sanju\\Documents\\NetBeansProjects\\MathMaze\\src\\images\\LbL_images\\Minimize button.png");
    ImageIcon hoverIcon_minimize = new ImageIcon("C:\\Users\\sanju\\Documents\\NetBeansProjects\\MathMaze\\src\\images\\LbL_images\\Minimize button after clicking.png");

    ImageIcon initialIcon_close = new ImageIcon("C:\\Users\\sanju\\Documents\\NetBeansProjects\\MathMaze\\src\\images\\LbL_images\\Exit button.png");
    ImageIcon hoverIcon_close = new ImageIcon("C:\\Users\\sanju\\Documents\\NetBeansProjects\\MathMaze\\src\\images\\LbL_images\\Exit button after clicking.png");

    ImageIcon initialIcon_previous = new ImageIcon("C:\\Users\\sanju\\Documents\\NetBeansProjects\\MathMaze\\src\\images\\LbL_images\\Lbl_button_previous.png");
    ImageIcon hoverIcon_previous = new ImageIcon("C:\\Users\\sanju\\Documents\\NetBeansProjects\\MathMaze\\src\\images\\LbL_images\\Lbl_button_previous_afterClicking.png");

    //retrieves the highest score and maximum time of the user's beginner mode
    private Object[] getUserStats(String username) {
        Object[] userStats = new Object[2];
        try {
            int playerId = getPlayerIdByUsername(username);

            String query = "SELECT MAX(`score`) AS highestScore, MAX(`time`) AS maxTime FROM `beginner` WHERE `player_id` = ?";
            try (PreparedStatement preparedStatement = db_mathmaze.getConnection().prepareStatement(query)) {
                preparedStatement.setInt(1, playerId);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        userStats[0] = resultSet.getObject("highestScore");
                        userStats[1] = resultSet.getObject("maxTime");
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDashboardForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        return userStats;
    }

    //----------------------------user progress data retrieved from a database table 
    private void populateUserProgressTable(String username) {
        try {

            int playerId = getPlayerIdByUsername(username);

            String progressQuery = "SELECT `level_id`, MAX(`correct_answers`) AS `correct_answers`, `time_to_complete` "
                    + "FROM `levelcompletion` WHERE `player_id` = ? GROUP BY `level_id`";
            try (PreparedStatement progressStatement = db_mathmaze.getConnection().prepareStatement(progressQuery)) {
                progressStatement.setInt(1, playerId);

                try (ResultSet progressResultSet = progressStatement.executeQuery()) {
                    DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
                    model.setRowCount(0);

                    while (progressResultSet.next()) {
                        int levelId = progressResultSet.getInt("level_id");
                        int correctAnswers = progressResultSet.getInt("correct_answers");
                        String timeToComplete = progressResultSet.getString("time_to_complete");

                        model.addRow(new Object[]{levelId, correctAnswers, timeToComplete});
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDashboardForm.class.getName()).log(Level.SEVERE, null, ex);
        }
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

        jLabel_minmize = new javax.swing.JLabel();
        jLabel_close = new javax.swing.JLabel();
        jLabel_title = new javax.swing.JLabel();
        jLabel_welcome = new javax.swing.JLabel();
        jLabel_highestScore = new javax.swing.JLabel();
        jLabel_time = new javax.swing.JLabel();
        jLabel_congrates = new javax.swing.JLabel();
        jLabel_congratesSecond = new javax.swing.JLabel();
        lbl_score = new javax.swing.JLabel();
        lbl_time = new javax.swing.JLabel();
        jLabel_previousButton = new javax.swing.JLabel();
        jLabel_Advancedplayer = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel_background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel_minmize.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 24)); // NOI18N
        jLabel_minmize.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/LbL_images/Minimize button.png"))); // NOI18N
        jLabel_minmize.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel_minmize.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_minmizeMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel_minmizeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel_minmizeMouseExited(evt);
            }
        });
        getContentPane().add(jLabel_minmize, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 10, 40, 40));

        jLabel_close.setBackground(new java.awt.Color(153, 255, 255));
        jLabel_close.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18)); // NOI18N
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
        getContentPane().add(jLabel_close, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 10, 40, 40));

        jLabel_title.setFont(new java.awt.Font("Lucida Calligraphy", 1, 40)); // NOI18N
        jLabel_title.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Label_Game Dashboard.png"))); // NOI18N
        getContentPane().add(jLabel_title, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 50, 640, 70));

        jLabel_welcome.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 22)); // NOI18N
        jLabel_welcome.setText("Well Played! {user_name},");
        getContentPane().add(jLabel_welcome, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 260, -1, -1));

        jLabel_highestScore.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 15)); // NOI18N
        jLabel_highestScore.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Label_Highest Score.png"))); // NOI18N
        getContentPane().add(jLabel_highestScore, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 350, 190, 40));

        jLabel_time.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 15)); // NOI18N
        jLabel_time.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Label_Best Time.png"))); // NOI18N
        getContentPane().add(jLabel_time, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 410, 150, 40));

        jLabel_congrates.setFont(new java.awt.Font("Bahnschrift", 1, 18)); // NOI18N
        jLabel_congrates.setText("You have got good scores in");
        getContentPane().add(jLabel_congrates, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 290, 260, 30));

        jLabel_congratesSecond.setFont(new java.awt.Font("Bahnschrift", 1, 18)); // NOI18N
        jLabel_congratesSecond.setText("Beginner mode.");
        getContentPane().add(jLabel_congratesSecond, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 320, 160, 30));

        lbl_score.setBackground(new java.awt.Color(204, 204, 204));
        lbl_score.setFont(new java.awt.Font("Calibri Light", 1, 20)); // NOI18N
        lbl_score.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(102, 102, 102), new java.awt.Color(102, 102, 102), new java.awt.Color(102, 102, 102), new java.awt.Color(102, 102, 102)));
        lbl_score.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        getContentPane().add(lbl_score, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 350, 100, 40));

        lbl_time.setBackground(new java.awt.Color(204, 204, 204));
        lbl_time.setFont(new java.awt.Font("Calibri Light", 1, 20)); // NOI18N
        lbl_time.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(102, 102, 102), new java.awt.Color(102, 102, 102), new java.awt.Color(102, 102, 102), new java.awt.Color(102, 102, 102)));
        getContentPane().add(lbl_time, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 410, 100, 40));

        jLabel_previousButton.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
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
        getContentPane().add(jLabel_previousButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 40, 40));

        jLabel_Advancedplayer.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel_Advancedplayer.setText("Your Advanced Mode scores are,");
        getContentPane().add(jLabel_Advancedplayer, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 180, 290, 30));

        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jTable1.setAutoCreateRowSorter(true);
        jTable1.setBackground(new java.awt.Color(204, 204, 204));
        jTable1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jTable1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Completed Level", "Score", "Time"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 220, 370, 350));

        jLabel_background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Background_Score board.png"))); // NOI18N
        getContentPane().add(jLabel_background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1050, 650));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //-----------------------------------------------------minmize button function

    private void jLabel_minmizeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_minmizeMouseEntered

        jLabel_minmize.setIcon(hoverIcon_minimize);
    }//GEN-LAST:event_jLabel_minmizeMouseEntered

    private void jLabel_minmizeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_minmizeMouseExited

        jLabel_minmize.setIcon(initialIcon_minimize);
    }//GEN-LAST:event_jLabel_minmizeMouseExited

    private void jLabel_minmizeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_minmizeMouseClicked
        //minimize the screen
        this.setState(JFrame.ICONIFIED);
    }//GEN-LAST:event_jLabel_minmizeMouseClicked

    //-----------------------------------------------------close the form

    private void jLabel_closeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_closeMouseEntered

        jLabel_close.setIcon(hoverIcon_close);
    }//GEN-LAST:event_jLabel_closeMouseEntered

    private void jLabel_closeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_closeMouseExited

        jLabel_close.setIcon(initialIcon_close);
    }//GEN-LAST:event_jLabel_closeMouseExited

    private void jLabel_closeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_closeMouseClicked
        //cloe the login window
        System.exit(0);
    }//GEN-LAST:event_jLabel_closeMouseClicked

    //-----------------------------------------------------prevous form game dashboard

    private void jLabel_previousButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_previousButtonMouseEntered

       jLabel_previousButton.setIcon(hoverIcon_previous);
    }//GEN-LAST:event_jLabel_previousButtonMouseEntered

    private void jLabel_previousButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_previousButtonMouseExited

        jLabel_previousButton.setIcon(initialIcon_previous);
    }//GEN-LAST:event_jLabel_previousButtonMouseExited

    private void jLabel_previousButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_previousButtonMouseClicked

        GameModeForm form = new GameModeForm(username);
        form.setVisible(true);
        form.pack();
        form.setLocationRelativeTo(null);
        this.dispose();
    }//GEN-LAST:event_jLabel_previousButtonMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            new UserDashboardForm("YourUsername").setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel_Advancedplayer;
    private javax.swing.JLabel jLabel_background;
    private javax.swing.JLabel jLabel_close;
    private javax.swing.JLabel jLabel_congrates;
    private javax.swing.JLabel jLabel_congratesSecond;
    private javax.swing.JLabel jLabel_highestScore;
    private javax.swing.JLabel jLabel_minmize;
    private javax.swing.JLabel jLabel_previousButton;
    private javax.swing.JLabel jLabel_time;
    private javax.swing.JLabel jLabel_title;
    private javax.swing.JLabel jLabel_welcome;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lbl_score;
    private javax.swing.JLabel lbl_time;
    // End of variables declaration//GEN-END:variables
}
