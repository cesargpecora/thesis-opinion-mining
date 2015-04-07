/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tesis.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author César García Pécora
 * Useful to add classification to Tweet file.
 */
public class filecreator {
    public static void main(String[] args){
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(new File("data/subjective_Tweets.csv"));
            BufferedReader file = new BufferedReader(new FileReader("data/subjective_tweets.txt"));
            String sentiment;            
            String line;           
            line = file.readLine();           
            while (line != null){                                
                sentiment = "subjective";
                fileWriter.append(sentiment+","+line+"\n");
                line = file.readLine();
            }            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                fileWriter.close();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}