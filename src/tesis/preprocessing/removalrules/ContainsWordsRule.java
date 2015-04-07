/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tesis.preprocessing.removalrules;

/**
 *
 * @author ezequielaranda
 */
public class ContainsWordsRule implements IRemovalRule{

    private String words;

    public ContainsWordsRule(String words) {
        this.words = words;
    }
    
    
    
    public boolean matches(String tweetText) {
        return tweetText.contains(words);
    }
    
    
}
