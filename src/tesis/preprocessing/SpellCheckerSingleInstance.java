/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tesis.preprocessing;

import java.util.logging.Level;
import java.util.logging.Logger;
import pt.tumba.spell.SpellChecker;
import tesis.util.Config;


/**
 *
 * @author ezequielaranda
 */
public class SpellCheckerSingleInstance {
    
    private SpellChecker spellchecker;
    
    private SpellCheckerSingleInstance() {
        try {
            this.spellchecker = new SpellChecker();
            
             spellchecker.initialize(Config.getJaspellEnglishFilePath(), Config.getJaspellMisspellFilePath());
        } catch (Exception ex) {
            Logger.getLogger(SpellCheckerSingleInstance.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public String findMostSimilar(String word)
    {
        return this.spellchecker.findMostSimilar(word, true);
    }
    
    
    
    public static SpellCheckerSingleInstance getInstance() {
        return SpellCheckerSingleInstanceHolder.INSTANCE;
    }
    
    private static class SpellCheckerSingleInstanceHolder {
        private static final SpellCheckerSingleInstance INSTANCE;

        static {
            try {
                INSTANCE = new SpellCheckerSingleInstance();
            } catch (Exception ex) {
                Logger.getLogger(SpellCheckerSingleInstance.class.getName()).log(Level.SEVERE, null, ex);
                throw new ExceptionInInitializerError(ex);
            }
        }
        
    }
}
