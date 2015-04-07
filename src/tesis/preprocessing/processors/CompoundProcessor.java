/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tesis.preprocessing.processors;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ezequielaranda
 */
public class CompoundProcessor implements IPreprocessor{

    private List<IPreprocessor> preprocessors;

    public CompoundProcessor() {
    
        this.preprocessors = new ArrayList<IPreprocessor>();
    }
    
    public void addPreprocessor(IPreprocessor processor)
    {
        this.preprocessors.add(processor);
    }
    
    public String processTweet(String tweetText) {
        String tweet = tweetText;
        for (IPreprocessor iPreprocessor : preprocessors) {
            tweet = iPreprocessor.processTweet(tweet);
        }
        return tweet;
    }
    
}
