/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tesis.util;

import java.util.TimerTask;
import javax.swing.JFrame;
import tesis.view.OpinionMiningJFrame;

/**
 *
 * @author Cesar
 */
public class CloseFrameTimer extends TimerTask{
    JFrame frame;    
    
    public CloseFrameTimer(JFrame frame){
        super();
        this.frame = frame;
    }

    @Override
    public void run() {
        frame.dispose();        
        new OpinionMiningJFrame().setVisible(true);
    }
}
