/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tesis.opinionMining.polarityClassification;

import java.util.Hashtable;

/**
 *
 * @author César García Pécora
 */
public class OpinionMiningAlgorithmsContainer {

    Hashtable<String,OpinionMiningAlgorithm> opinionMiningAlgorithms;

    public OpinionMiningAlgorithmsContainer() {
        opinionMiningAlgorithms = new Hashtable<String, OpinionMiningAlgorithm>();
        opinionMiningAlgorithms.put("Turney Algorithm", new TurneyAlgorithm());
        opinionMiningAlgorithms.put("Mixed Algorithm",new MixedAlgorithm());
        opinionMiningAlgorithms.put("Bayesian Algorithm",new BayesianAlgorithm());
    }

    public Hashtable<String, OpinionMiningAlgorithm> getOpinionMiningAlgorithms() {
        return opinionMiningAlgorithms;
    }
    
}
