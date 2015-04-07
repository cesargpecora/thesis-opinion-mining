/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tesis.preprocessing.removalrules;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ezequielaranda
 */
public class CompoundRule implements IRemovalRule{

    private List<IRemovalRule> rules;

    public CompoundRule() {
    
        this.rules = new ArrayList<IRemovalRule>();
    }
    
    public void addRemovalRule(IRemovalRule processor)
    {
        this.rules.add(processor);
    }
    
    public boolean matches(String tweetText) {
        for (IRemovalRule iRule : rules) {
             if (iRule.matches(tweetText))
             {
                 return true;
             }
        }
        return false;
    }
}
