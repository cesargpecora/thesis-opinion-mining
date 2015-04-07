/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tesis.opinionMining.dictionaries;

import tesis.opinionMining.SentimentPhrase;

/**
 *
 * @author César García Pécora
 */
public class WiebeSentimentDictionaryLineParser implements ISentimentDictionaryLineParser{
    //"data/subjclueslen1polar.tff"
    //"data/subjcluesSentenceClassifiersOpinionFinderJune06.tff"
    //last two archives were mixed in "data/polclues.tff"

    public WiebeSentimentDictionaryLineParser() {}

    public SentimentPhrase parseLine(String line){
        String[] lineSplitted = line.split("word");
        Integer sentiment;
        String phrase = "";
        String[] internalLineSplitted;
        for (int i = 1; i < lineSplitted.length; i++) {
            internalLineSplitted = lineSplitted[i].split(" ");
            phrase += internalLineSplitted[0].substring(2,internalLineSplitted[0].length()) +" ";
        }
        phrase = phrase.substring(0, phrase.length()-1);
        String orientation = line.split("mpqapolarity=")[1];
        if (orientation.equals("strongpos"))
            sentiment = new Integer(2);
        else
            if (orientation.equals("weakpos"))
                sentiment = new Integer(1);
            else
                if (orientation.equals("strongneg"))
                    sentiment = new Integer(-2);
                else
                    if (orientation.equals("weakneg"))
                        sentiment = new Integer(-1);
                    else
                        sentiment = new Integer(0);
        return new SentimentPhrase(sentiment, phrase);
    }
}
