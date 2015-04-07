/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tesis.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ezequielaranda
 */
public class FileAppender {
    
    public static boolean append(String filePath, String textToAppend)
    {
        BufferedWriter bw = null;
        try {
            
            bw = new BufferedWriter(new FileWriter(filePath, true));
            bw.write(textToAppend);
            bw.newLine();
            bw.flush();
            return true;
        } catch (IOException ex) {            
            Logger.getLogger(FileAppender.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        finally
        {
            try {
                bw.close();
            } catch (IOException ex) {
                Logger.getLogger(FileAppender.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        }


        
    }
}
