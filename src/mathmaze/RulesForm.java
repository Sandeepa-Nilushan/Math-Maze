/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package mathmaze;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.border.Border;

/**
 *
 * @author SANDEEPA
 */
public class RulesForm extends javax.swing.JFrame {

    /**
     * Creates new form RulesForm
     */
    public RulesForm() {
        initComponents();
        
        //center the form
        this.setLocationRelativeTo(null);
    }
    
    ImageIcon initialIcon_minimize = new ImageIcon("C:\\Users\\sanju\\Documents\\NetBeansProjects\\MathMaze\\src\\images\\LbL_images\\Minimize button.png");
    ImageIcon hoverIcon_minimize = new ImageIcon("C:\\Users\\sanju\\Documents\\NetBeansProjects\\MathMaze\\src\\images\\LbL_images\\Minimize button after clicking.png");

    ImageIcon initialIcon_close = new ImageIcon("C:\\Users\\sanju\\Documents\\NetBeansProjects\\MathMaze\\src\\images\\LbL_images\\Exit button.png");
    ImageIcon hoverIcon_close = new ImageIcon("C:\\Users\\sanju\\Documents\\NetBeansProjects\\MathMaze\\src\\images\\LbL_images\\Exit button after clicking.png");

    ImageIcon initialIcon_previous = new ImageIcon("C:\\Users\\sanju\\Documents\\NetBeansProjects\\MathMaze\\src\\images\\LbL_images\\Lbl_button_previous.png");
    ImageIcon hoverIcon_previous = new ImageIcon("C:\\Users\\sanju\\Documents\\NetBeansProjects\\MathMaze\\src\\images\\LbL_images\\Lbl_button_previous_afterClicking.png");
    
    ImageIcon initialIcon_agree = new ImageIcon("C:\\Users\\sanju\\Documents\\NetBeansProjects\\MathMaze\\src\\images\\LbL_images\\lbl_agreedButton.png");
    ImageIcon hoverIcon_agree = new ImageIcon("C:\\Users\\sanju\\Documents\\NetBeansProjects\\MathMaze\\src\\images\\LbL_images\\lbl_agreedButton_afterClicking.png");
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel_minimize = new javax.swing.JLabel();
        jLabel_close = new javax.swing.JLabel();
        jLabel_Title = new javax.swing.JLabel();
        jLabel_DescriptionBody = new javax.swing.JLabel();
        jLabel_backword = new javax.swing.JLabel();
        jLabel_agreeButton = new javax.swing.JLabel();
        jLabel1_background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
        getContentPane().add(jLabel_minimize, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 10, 40, 40));

        jLabel_close.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18)); // NOI18N
        jLabel_close.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/LbL_images/Exit button.png"))); // NOI18N
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
        getContentPane().add(jLabel_close, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 10, -1, 40));

        jLabel_Title.setFont(new java.awt.Font("Lucida Calligraphy", 1, 24)); // NOI18N
        jLabel_Title.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Label_Rules_&_Regulation.png"))); // NOI18N
        getContentPane().add(jLabel_Title, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 60, 670, 80));

        jLabel_DescriptionBody.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Label_Rules_description.png"))); // NOI18N
        getContentPane().add(jLabel_DescriptionBody, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, 890, 370));

        jLabel_backword.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
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

        jLabel_agreeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/LbL_images/lbl_agreedButton.png"))); // NOI18N
        jLabel_agreeButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel_agreeButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_agreeButtonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel_agreeButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel_agreeButtonMouseExited(evt);
            }
        });
        getContentPane().add(jLabel_agreeButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 490, 220, 70));

        jLabel1_background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Background_Rules.png"))); // NOI18N
        getContentPane().add(jLabel1_background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 950, 600));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //------------------------------minmize the rules form
    
    private void jLabel_minimizeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_minimizeMouseEntered
        
        jLabel_minimize.setIcon(hoverIcon_minimize);
    }//GEN-LAST:event_jLabel_minimizeMouseEntered

    private void jLabel_minimizeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_minimizeMouseExited
       
        jLabel_minimize.setIcon(initialIcon_minimize);
    }//GEN-LAST:event_jLabel_minimizeMouseExited

    private void jLabel_minimizeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_minimizeMouseClicked

        //minimize the screen
        this.setState(JFrame.ICONIFIED); 
    }//GEN-LAST:event_jLabel_minimizeMouseClicked

    //-----------------------------------------exit from the rule form
    
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

    // ---------------------------------------login to welcome form
    
    private void jLabel_backwordMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_backwordMouseEntered
        jLabel_backword.setIcon(hoverIcon_previous);
    }//GEN-LAST:event_jLabel_backwordMouseEntered

    private void jLabel_backwordMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_backwordMouseExited
        jLabel_backword.setIcon(initialIcon_previous);
    }//GEN-LAST:event_jLabel_backwordMouseExited

    private void jLabel_backwordMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_backwordMouseClicked
        //click the button to enter the welcome page
        WelcomeForm Wf = new WelcomeForm();
        Wf.setVisible(true);
        Wf.pack();
        Wf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.dispose();
    }//GEN-LAST:event_jLabel_backwordMouseClicked

    //-------------------------------label button agree to the rules
    
    private void jLabel_agreeButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_agreeButtonMouseEntered
        
        jLabel_agreeButton.setIcon(hoverIcon_agree);
    }//GEN-LAST:event_jLabel_agreeButtonMouseEntered

    private void jLabel_agreeButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_agreeButtonMouseExited
        
        jLabel_agreeButton.setIcon(initialIcon_agree);
    }//GEN-LAST:event_jLabel_agreeButtonMouseExited

    private void jLabel_agreeButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_agreeButtonMouseClicked
        
        //click the button to enter the Register Form
        RegistrationForm Rf = new RegistrationForm();
        Rf.setVisible(true);
        Rf.pack();
        Rf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.dispose();
    }//GEN-LAST:event_jLabel_agreeButtonMouseClicked

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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(RulesForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RulesForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RulesForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RulesForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RulesForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1_background;
    private javax.swing.JLabel jLabel_DescriptionBody;
    private javax.swing.JLabel jLabel_Title;
    private javax.swing.JLabel jLabel_agreeButton;
    private javax.swing.JLabel jLabel_backword;
    private javax.swing.JLabel jLabel_close;
    private javax.swing.JLabel jLabel_minimize;
    // End of variables declaration//GEN-END:variables
}