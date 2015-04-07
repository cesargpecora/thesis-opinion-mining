/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tesis.opinionMining;

import edu.stanford.nlp.tagger.maxent.MaxentTagger;
import tesis.util.Config;

/**
 *
 * @author César García Pécora
 */
public class POSTagger {
    MaxentTagger tagger;

    public POSTagger() {
        try {            
            tagger = new MaxentTagger(Config.getPOSTaggerFilePath());
        } catch (Exception ex) {            
        }
    }       

    public String tagPhrase(String phrase){
        return tagger.tagString(phrase);
    }    

}
