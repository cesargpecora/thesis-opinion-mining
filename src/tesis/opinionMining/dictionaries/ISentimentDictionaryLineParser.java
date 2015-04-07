/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tesis.opinionMining.dictionaries;

import tesis.opinionMining.SentimentPhrase;

/**
 *
 * @author Cesar
 */
public interface ISentimentDictionaryLineParser {
    
    public SentimentPhrase parseLine(String line);
    
}
