/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tesis.opinionMining.polarityClassification;

import java.util.concurrent.Callable;

/**
 *
 * @author Cesar
 */
public class OpinionMiningAlgorithmExecutor implements Callable<String>{

    private OpinionMiningAlgorithm opinionMiningAlgorithm;
    private String phrase;

    public OpinionMiningAlgorithmExecutor(OpinionMiningAlgorithm opinionMiningAlgorithm, String phrase) {
        this.opinionMiningAlgorithm = opinionMiningAlgorithm;
        this.phrase = phrase;
    }    
    
    public void setOpinionMiningAlgorithm(OpinionMiningAlgorithm opinionMiningAlgorithm) {
        this.opinionMiningAlgorithm = opinionMiningAlgorithm;
    }        

    public String call() throws OpinionMiningAlgorithmException {        
        return opinionMiningAlgorithm.getSemanticOrientation(phrase)+","+phrase;
    }
    
}
