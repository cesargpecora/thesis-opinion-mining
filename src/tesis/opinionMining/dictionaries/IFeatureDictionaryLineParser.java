/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tesis.opinionMining.dictionaries;

import java.util.List;

/**
 *
 * @author Cesar
 */
public interface IFeatureDictionaryLineParser {
    
    public List<String> parseLine(String Line);
    
}
