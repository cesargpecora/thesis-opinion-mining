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
public class WiebeIntensifierFeatureDictionaryLineParser implements IFeatureDictionaryLineParser{
    //"data/intensifiers2.tff"

    public WiebeIntensifierFeatureDictionaryLineParser() {}

    public List<String> parseLine(String line){
        String[] lineSplitted = line.split("word");
        String intensifier = "";
        String[] internalLineSplitted;
        List<String> intensifiers = new ArrayList<String>();
        internalLineSplitted = lineSplitted[1].split(" ");
        intensifier += internalLineSplitted[0].substring(2,internalLineSplitted[0].length()) +" ";
        intensifier = intensifier.substring(0, intensifier.length()-1);
        intensifiers.add(intensifier);
        return intensifiers;
    }
}
