/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tesis.preprocessing.processors;

/**
 *
 * @author ezequielaranda
 */
public class RemoveWordsPreprocessor implements IPreprocessor{
    
    private String words;

    public RemoveWordsPreprocessor(String words) {
        this.words = words;
    }

    public String processTweet(String tweetText) {
        return tweetText.replaceAll(words, "");
    }
   
    
}
