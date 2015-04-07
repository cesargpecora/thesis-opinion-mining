/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tesis.opinionMining;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;

/**
 *
 * @author César García Pécora
 */
public class TweetCSVFileParser {
    Hashtable<String,String> parsedTweets;

    public TweetCSVFileParser(String filePath) {
        parsedTweets = new Hashtable<String, String>();
        parseFile(filePath);
    }

    protected boolean parseFile(String filePath){
        boolean execution = true;
        BufferedReader file = null;
        try {
            file = new BufferedReader(new FileReader(filePath));
            String line = file.readLine();
            while (line != null){
                parseLine(line);
                line = file.readLine();
            }
        } catch (FileNotFoundException ex) {
            execution = false;
        } finally {
            try {
                file.close();
                return execution;
            } catch (IOException ex) {
                return false;
            }
        }
    }

    protected void parseLine(String line){
        String value = line.substring(0,line.indexOf(","));
        String tweet = line.substring(line.indexOf(",")+1, line.length());
        parsedTweets.put(tweet, value);
    }

    public Hashtable<String,String> getParsedTweets(){
        return parsedTweets;
    }

}
