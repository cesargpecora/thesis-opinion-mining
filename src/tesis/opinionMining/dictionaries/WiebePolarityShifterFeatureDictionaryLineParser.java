/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tesis.opinionMining.dictionaries;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author César García Pécora
 */
public class WiebePolarityShifterFeatureDictionaryLineParser implements IFeatureDictionaryLineParser{
    //"data/valenceshifters.tff"

    public WiebePolarityShifterFeatureDictionaryLineParser() {}

    public List<String> parseLine(String line){
        String[] lineSplitted = line.split("word");
        String polarityShifter = "";
        String[] internalLineSplitted;
        List<String> polarityShifters = new ArrayList<String>();
        for (int i = 1; i < lineSplitted.length; i++) {
            internalLineSplitted = lineSplitted[i].split(" ");
            polarityShifter += internalLineSplitted[0].substring(2,internalLineSplitted[0].length()) +" ";
        }
        polarityShifter = polarityShifter.substring(0, polarityShifter.length()-1);        
        polarityShifters.add(polarityShifter);
        return polarityShifters;
    }
}
