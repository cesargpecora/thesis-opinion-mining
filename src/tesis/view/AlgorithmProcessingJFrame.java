/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tesis.view;

import java.awt.Color;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

/**
 *
 * @author Cesar
 */
public class AlgorithmProcessingJFrame extends javax.swing.JFrame {

    /**
     * Creates new form AlgorithmProcessingJFrame
     */
    public AlgorithmProcessingJFrame() {
        this.setUndecorated(true);
        initComponents();
        this.setIconImage(new ImageIcon("icons"+File.separator+"processing-ico.png").getImage());
        this.getContentPane().setBackground(Color.WHITE);
        this.setLocationRelativeTo(null);        
        Thread setProgressBar = new Thread(){            
            @Override
            public void run(){
                startProgressBar();
        }};
        setProgressBar.start();
    }
    
    private void startProgressBar(){
        while (true){
            if (jProgressBar.getValue() != 100)
                jProgressBar.setValue(jProgressBar.getValue()+1);
            else
                jProgressBar.setValue(0);
            try {
                Thread.sleep(40);
            } catch (InterruptedException ex) {
                Logger.getLogger(AlgorithmProcessingJFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jProgressBar = new javax.swing.JProgressBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

        jProgressBar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jProgressBar.setString("Processing...");
        jProgressBar.setStringPainted(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JProgressBar jProgressBar;
    // End of variables declaration//GEN-END:variables
}
