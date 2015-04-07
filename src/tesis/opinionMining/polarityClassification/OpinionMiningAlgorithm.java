/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tesis.opinionMining.polarityClassification;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import tesis.opinionMining.AlgorithmResults;
import tesis.opinionMining.SentimentPhrase;
import tesis.subjectivityClassification.exceptions.PropertyKeyNotFoundException;
import tesis.util.Config;
import tesis.util.Resources;

/**
 *
 * @author César García Pécora
 */
public abstract class OpinionMiningAlgorithm {
    protected String name;

    public abstract void init() throws OpinionMiningAlgorithmException;
    
    public abstract String getSemanticOrientation(String phrase) throws OpinionMiningAlgorithmException;  
    
    public abstract AlgorithmResults testAlgorithm(Hashtable<String,String> parsedTweets) throws OpinionMiningAlgorithmException;
    
    public List<SentimentPhrase> getOrientations(List<String> incomingTweets) throws OpinionMiningAlgorithmException{
        List<SentimentPhrase> result = new ArrayList<SentimentPhrase>();
        int so = 0;
        String semanticOrientation;
        String unparsedResult;
        String tweet;
        
        // Create a Thread Pool to execute the algorithm
        ExecutorService calcExecutor;            
        try {
            calcExecutor = Executors.newFixedThreadPool(Config.getOMAlgThreadPoolSize());                
        
            ArrayList<OpinionMiningAlgorithmExecutor> algorithmExecutors = new ArrayList<OpinionMiningAlgorithmExecutor>();

            // Reference to the results.
            List<Future<String>> allResults = null;

            // Create the Threads
            for(String incomingTweet : incomingTweets)
            {
                algorithmExecutors.add(new OpinionMiningAlgorithmExecutor(this, incomingTweet));
            }                
            // Perform the calculations.
            allResults = calcExecutor.invokeAll(algorithmExecutors);
            
            // Get the results.
            for (Future<String> algorithmResult : allResults) 
            {
                unparsedResult = algorithmResult.get();
                semanticOrientation = unparsedResult.substring(0,unparsedResult.indexOf(","));
                tweet = unparsedResult.substring(unparsedResult.indexOf(",")+1, unparsedResult.length());
                                
                if (semanticOrientation.equals(Resources.POSITIVE_VALUE))
                    so = 1;
                else{
                    if (semanticOrientation.equals(Resources.NEGATIVE_VALUE))
                        so = -1;
                    else
                        so = 0;
                }
                result.add(new SentimentPhrase(so,tweet));
            }
        } catch (InterruptedException ex){            
            Logger.getLogger(OpinionMiningAlgorithm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExecutionException ex) { 
            throw new OpinionMiningAlgorithmException(ex.getMessage());
        } catch (PropertyKeyNotFoundException ex) {
            Logger.getLogger(OpinionMiningAlgorithm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(OpinionMiningAlgorithm.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }        

    @Override
    public String toString() {
        return name;
    }

    public String getName() {
        return name;
    }

}
