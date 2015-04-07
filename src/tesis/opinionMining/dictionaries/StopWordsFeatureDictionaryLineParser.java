/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tesis.opinionMining.dictionaries;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author César García Pécora
 */
public class StopWordsFeatureDictionaryLineParser implements IFeatureDictionaryLineParser{

    public StopWordsFeatureDictionaryLineParser() {}
    
    public List<String> parseLine(String line) {
        String[] words = line.split(",");
        String stopWord;
        List<String> stopWords = new ArrayList<String>();
        for (int i = 0; i < words.length; i++) {
            stopWord = words[i];
            stopWords.add(stopWord);
        }
        return stopWords;
    }
    
}
