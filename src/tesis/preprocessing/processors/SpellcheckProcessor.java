/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tesis.preprocessing.processors;

import tesis.preprocessing.SpellCheckerSingleInstance;
import tesis.preprocessing.Tokenizer;

/**
 *
 * @author ezequielaranda
 */
public class SpellcheckProcessor implements IPreprocessor{

    
    
    public String processTweet(String tweetText) {
        String result = new String();
            
        for (String currentWord : Tokenizer.tokenize(tweetText))
        {
            String spellCheckedWord = SpellCheckerSingleInstance.getInstance().findMostSimilar(currentWord);
            
            if (spellCheckedWord != null)
            {            
                result = result.concat(" " + spellCheckedWord); 
            }
            else
            {
                result = result.concat(" " + currentWord); 
            }
            
        }
        
        return result;
    }
    
}
