/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tesis.opinionMining.dictionaries;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Vector;

/**
 *
 * @author César García Pécora
 */
public class FeatureDictionary {
    private Vector<String> featureDictionary = new Vector<String>();
    private IFeatureDictionaryLineParser featureDictionaryLineParser;

    public FeatureDictionary(String filePath, IFeatureDictionaryLineParser featureDictionaryLineParser) {
        this.featureDictionaryLineParser = featureDictionaryLineParser;
        createDictionary(filePath);
    }    

    protected boolean createDictionary(String filePath){
        boolean execution = true;
        BufferedReader file = null;
        List<String> features;
        try {
            file = new BufferedReader(new FileReader(filePath));
            String line = file.readLine();
            while (line != null){
                features = featureDictionaryLineParser.parseLine(line);
                featureDictionary.addAll(features);
                line = file.readLine();
            }
        } catch (FileNotFoundException ex) {
            execution = false;
        } finally {
            try {
                file.close();
                return execution;
            } catch (IOException ex) {
                return false;
            }
        }
    }     

    public boolean hasFeature(String word){
        if (featureDictionary.contains(word)){
            return true;
        }
        return false;
    }

}
