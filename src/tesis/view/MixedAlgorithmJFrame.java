/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * MixedAlgorithmJFrame.java
 *
 * Created on 21/06/2011, 18:21:25
 */

package tesis.view;

import java.awt.Color;
import java.io.File;
import javax.swing.ImageIcon;
import tesis.opinionMining.polarityClassification.MixedAlgorithm;

/**
 *
 * @author César García Pécora
 */
public class MixedAlgorithmJFrame extends javax.swing.JFrame {
    MixedAlgorithm mixedAlgorithm;

    /** Creates new form MixedAlgorithmJFrame */
    public MixedAlgorithmJFrame(MixedAlgorithm mixedAlgorithm) {
        initComponents();
        this.setIconImage(new ImageIcon("icons"+File.separator+"tune.png").getImage());
        this.getContentPane().setBackground(Color.WHITE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.mixedAlgorithm = mixedAlgorithm;
        jCheckBoxIntensification.setSelected(mixedAlgorithm.isDoIntensificationTreatment());
        jCheckBoxNegation.setSelected(mixedAlgorithm.isDoNegationTreatment());
        jCheckBoxStopWords.setSelected(mixedAlgorithm.isDoStopWordsTreatment());
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jButtonOK = new javax.swing.JButton();
        jCheckBoxIntensification = new javax.swing.JCheckBox();
        jCheckBoxNegation = new javax.swing.JCheckBox();
        jCheckBoxStopWords = new javax.swing.JCheckBox();
        jSeparator2 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Mixed Algorithm Properties");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Mixed Algorithm");

        jButtonOK.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/check-mark-icon.png"))); // NOI18N
        jButtonOK.setBorderPainted(false);
        jButtonOK.setContentAreaFilled(false);
        jButtonOK.setFocusPainted(false);
        jButtonOK.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/check-mark-icon-pressed.png"))); // NOI18N
        jButtonOK.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/check-mark-icon-rollover.png"))); // NOI18N
        jButtonOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOKActionPerformed(evt);
            }
        });

        jCheckBoxIntensification.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jCheckBoxIntensification.setText("Intensification Treatment");
        jCheckBoxIntensification.setContentAreaFilled(false);
        jCheckBoxIntensification.setFocusPainted(false);
        jCheckBoxIntensification.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxIntensificationActionPerformed(evt);
            }
        });

        jCheckBoxNegation.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jCheckBoxNegation.setText("Negation Treatment");
        jCheckBoxNegation.setContentAreaFilled(false);
        jCheckBoxNegation.setFocusPainted(false);
        jCheckBoxNegation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxNegationActionPerformed(evt);
            }
        });

        jCheckBoxStopWords.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jCheckBoxStopWords.setText("StopWords Treatment");
        jCheckBoxStopWords.setContentAreaFilled(false);
        jCheckBoxStopWords.setFocusPainted(false);
        jCheckBoxStopWords.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxStopWordsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(251, 251, 251)
                        .addComponent(jButtonOK, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jCheckBoxIntensification)
                        .addGap(18, 18, 18)
                        .addComponent(jCheckBoxNegation)
                        .addGap(18, 18, 18)
                        .addComponent(jCheckBoxStopWords)))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBoxIntensification)
                    .addComponent(jCheckBoxNegation)
                    .addComponent(jCheckBoxStopWords))
                .addGap(18, 18, 18)
                .addComponent(jButtonOK)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonOKActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButtonOKActionPerformed

    private void jCheckBoxIntensificationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxIntensificationActionPerformed
        mixedAlgorithm.setDoIntensificationTreatment(jCheckBoxIntensification.isSelected());
    }//GEN-LAST:event_jCheckBoxIntensificationActionPerformed

    private void jCheckBoxNegationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxNegationActionPerformed
        mixedAlgorithm.setDoNegationTreatment(jCheckBoxNegation.isSelected());
    }//GEN-LAST:event_jCheckBoxNegationActionPerformed

    private void jCheckBoxStopWordsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxStopWordsActionPerformed
        mixedAlgorithm.setDoStopWordsTreatment(jCheckBoxStopWords.isSelected());
    }//GEN-LAST:event_jCheckBoxStopWordsActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonOK;
    private javax.swing.JCheckBox jCheckBoxIntensification;
    private javax.swing.JCheckBox jCheckBoxNegation;
    private javax.swing.JCheckBox jCheckBoxStopWords;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    // End of variables declaration//GEN-END:variables

}
