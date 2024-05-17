/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package View;

import Model.Vehicle;
import static MyAPI.textManagement.isValidWithMinus;
import javax.swing.JOptionPane;

/**
 *
 * @author ericb
 */
public class createVehicle extends javax.swing.JDialog {

    /**
     * Creates new form createVehicle
     */
    public createVehicle(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    public static boolean matriculaCorrectFormat(String matricula) {
        if (matricula.length() != 7) {
            return false;
        }
        for (int i = 0; i < 4; i++) {
            if (!Character.isDigit(matricula.charAt(i))) {
                return false;
            }
        }
        for (int i = 4; i < 7; i++) {
            if (!Character.isLetter(matricula.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        colorLabel = new javax.swing.JLabel();
        licensePlateTextField = new javax.swing.JTextField();
        colorTextField = new javax.swing.JTextField();
        crearVehicle_Button1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jLabel1.setFont(new java.awt.Font("Anta", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("MATRICULA");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel1.add(jLabel1, gridBagConstraints);

        colorLabel.setFont(new java.awt.Font("Anta", 0, 24)); // NOI18N
        colorLabel.setForeground(new java.awt.Color(0, 0, 0));
        colorLabel.setText("COLOR");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel1.add(colorLabel, gridBagConstraints);

        licensePlateTextField.setBackground(new java.awt.Color(0, 0, 0));
        licensePlateTextField.setFont(new java.awt.Font("Anta", 0, 24)); // NOI18N
        licensePlateTextField.setForeground(new java.awt.Color(255, 255, 255));
        licensePlateTextField.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        licensePlateTextField.setText("1234ABC");
        licensePlateTextField.setPreferredSize(new java.awt.Dimension(150, 40));
        licensePlateTextField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                licensePlateTextFieldMouseClicked(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel1.add(licensePlateTextField, gridBagConstraints);

        colorTextField.setBackground(new java.awt.Color(0, 0, 0));
        colorTextField.setFont(new java.awt.Font("Anta", 0, 24)); // NOI18N
        colorTextField.setForeground(new java.awt.Color(255, 255, 255));
        colorTextField.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        colorTextField.setText("Blanco");
        colorTextField.setPreferredSize(new java.awt.Dimension(150, 40));
        colorTextField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                colorTextFieldMouseClicked(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel1.add(colorTextField, gridBagConstraints);

        crearVehicle_Button1.setBackground(new java.awt.Color(0, 0, 0));
        crearVehicle_Button1.setFont(new java.awt.Font("Anta", 0, 36)); // NOI18N
        crearVehicle_Button1.setForeground(new java.awt.Color(255, 255, 255));
        crearVehicle_Button1.setText("CREAR");
        crearVehicle_Button1.setBorder(null);
        crearVehicle_Button1.setPreferredSize(new java.awt.Dimension(200, 45));
        crearVehicle_Button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crearVehicle_Button1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 0);
        jPanel1.add(crearVehicle_Button1, gridBagConstraints);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private static Vehicle v = null;

    private void crearVehicle_Button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_crearVehicle_Button1ActionPerformed
        String licensePlate = licensePlateTextField.getText();
        String color = colorTextField.getText();
        
        if (!matriculaCorrectFormat(licensePlate)) {
            JOptionPane.showMessageDialog(this, "WRONG FORMAT LICENSE PLATE", "WRONG FORMAT", JOptionPane.ERROR_MESSAGE);
        } else {
            if (!isValidWithMinus(color)) {
                JOptionPane.showMessageDialog(this, "WRONG FORMAT COLOR", "WRONG FORMAT", JOptionPane.ERROR_MESSAGE);
            } else {
                v = new Vehicle(licensePlate, color);
                JOptionPane.showMessageDialog(this, "VEHICLE SUCCESSFULLY CREATED", "CREATED", JOptionPane.INFORMATION_MESSAGE);
                this.dispose();
            }
        }

    }//GEN-LAST:event_crearVehicle_Button1ActionPerformed

    private void licensePlateTextFieldMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_licensePlateTextFieldMouseClicked
        licensePlateTextField.setText("");
    }//GEN-LAST:event_licensePlateTextFieldMouseClicked

    private void colorTextFieldMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_colorTextFieldMouseClicked
        colorTextField.setText("");
    }//GEN-LAST:event_colorTextFieldMouseClicked

    public static Vehicle getVehicle() {
        return v;
    }

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
            java.util.logging.Logger.getLogger(createVehicle.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(createVehicle.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(createVehicle.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(createVehicle.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                createVehicle dialog = new createVehicle(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel colorLabel;
    private javax.swing.JTextField colorTextField;
    private javax.swing.JButton crearVehicle_Button1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField licensePlateTextField;
    // End of variables declaration//GEN-END:variables
}
