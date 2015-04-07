/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tesis.preprocessing.removalrules;

/**
 *
 * @author ezequielaranda
 */
public class StartsWithRule implements IRemovalRule{

    private String prefix;
    
    public StartsWithRule(String prefix) {
        
        this.prefix = prefix;
        
    }

    
    
    public boolean matches(String tweetText) {
        return tweetText.startsWith(prefix);
    }
    
   
}
