/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tesis.opinionMining.dictionaries;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;
import tesis.opinionMining.SentimentPhrase;

/**
 *
 * @author César García Pécora
 */
public class SentimentDictionary {
    private Hashtable<String,Integer> sentimentDictionary = new Hashtable<String,Integer>();
    private int nGRAMCOUNT;
    private ISentimentDictionaryLineParser sentimentDictionaryLineParser;

    public SentimentDictionary(String filePath, int nGRAMCOUNT, ISentimentDictionaryLineParser sentimentDictionaryLineParser) {
        this.nGRAMCOUNT = nGRAMCOUNT;
        this.sentimentDictionaryLineParser = sentimentDictionaryLineParser;
        createDictionary(filePath);
    }  

    private boolean createDictionary(String filePath){
        boolean execution = true;
        BufferedReader file = null;
        SentimentPhrase sentimentPhrase;
        try {
            file = new BufferedReader(new FileReader(filePath));
            String line = file.readLine();                     
            while (line != null){
                sentimentPhrase = sentimentDictionaryLineParser.parseLine(line);
                sentimentDictionary.put(sentimentPhrase.getPhrase(),sentimentPhrase.getSentiment()); 
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

    public int getNGRAMCOUNT() {
        return nGRAMCOUNT;
    }

    public int getSentiment(String phrase){
        return sentimentDictionary.get(phrase).intValue();
    }

    public boolean hasPhrase(String phrase){
        return sentimentDictionary.containsKey(phrase);
    }

}
