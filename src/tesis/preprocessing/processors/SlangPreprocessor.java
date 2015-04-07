/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tesis.preprocessing.processors;

import tesis.preprocessing.Tokenizer;
import slangtranslator.SlangTranslator;
import slangtranslator.util.SlangTranslationNotFoundException;

/**
 *
 * @author ezequielaranda
 */
public class SlangPreprocessor implements IPreprocessor{

    public String processTweet(String tweetText) {
        
        String result = new String();
        
        String translation;
            
        for (String currentWord : Tokenizer.tokenize(tweetText))
        {
                result = result.concat(" ");
                try {
                    
                    translation = SlangTranslator.getInstance().getTranslation(tweetText);
                    
                    result = result.concat(translation);
                    
                } catch (SlangTranslationNotFoundException ex) {
                    result = result.concat(currentWord);
                }       
        }
        if (result.startsWith(" "))
        {
           return result.substring(1);
        }
        return result;
    }
    
}
