/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tesis.opinionMining.dictionaries;

import edu.stanford.nlp.ling.HasWord;
import edu.stanford.nlp.ling.SentenceProcessor;
import tesis.opinionMining.SentimentPhrase;
import tesis.util.Resources;

/**
 *
 * @author César García Pécora
 */
public class MSOLSentimentDictionaryLineParser implements ISentimentDictionaryLineParser {
    //"data/MSOL-June15-09.txt"
    //nGRAMCOUNT = 9

    public MSOLSentimentDictionaryLineParser() {}   

    public SentimentPhrase parseLine(String line){
        String[] lineSplitted = line.split(" ");
        Integer sentiment;
        if (lineSplitted[1].equals(Resources.POSITIVE_VALUE))
            sentiment = new Integer(1);
        else
            sentiment = new Integer(-1);
        String phrase = lineSplitted[0].replace("_"," ");
        return new SentimentPhrase(sentiment,phrase);
    }
}
