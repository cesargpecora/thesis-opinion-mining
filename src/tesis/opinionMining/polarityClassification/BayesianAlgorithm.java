/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tesis.opinionMining.polarityClassification;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.classifier4J.ClassifierException;
import net.sf.classifier4J.bayesian.BayesianClassifier;
import net.sf.classifier4J.bayesian.WordsDataSourceException;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import tesis.opinionMining.AlgorithmResults;
import tesis.opinionMining.TweetCSVFileParser;
import tesis.subjectivityClassification.exceptions.PropertyKeyNotFoundException;
import tesis.util.Config;
import tesis.util.Resources;

/**
 *
 * @author César García Pécora
 */
public class BayesianAlgorithm extends OpinionMiningAlgorithm{
    private BayesianClassifier bc;
    private double minPolarityRate;    

    public BayesianAlgorithm() {        
        name = "Bayesian Algorithm";
        this.init();
    }
    
    @Override
    public void init() {        
        try {
            minPolarityRate = Config.getPolThreshold();
            bc = new BayesianClassifier();        
            Hashtable<String,String> parsedTweets;
            parsedTweets = new TweetCSVFileParser(Config.getPolarityTweetsFilePath()).getParsedTweets();
            train(parsedTweets);
        } catch (PropertyKeyNotFoundException ex) {
            Logger.getLogger(BayesianAlgorithm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(BayesianAlgorithm.class.getName()).log(Level.SEVERE, null, ex);
        }            
    }

    @Override
    public String getSemanticOrientation(String phrase) {
        try {
            int match = Double.compare(bc.classify(phrase), minPolarityRate);
            if(match > 0)
                return Resources.POSITIVE_VALUE;
            else {
                if (match < 0)
                    return Resources.NEGATIVE_VALUE;
                else
                    return Resources.NEUTRAL_VALUE;
            }

        }
        catch (WordsDataSourceException ex) {
        } catch (ClassifierException ex) {
        }
        return "";
    }
    
    @Override
    public AlgorithmResults testAlgorithm(Hashtable<String,String> parsedTweets){
        this.minPolarityRate = 0.7;
        Vector<String> tweets = new Vector<String>();
        int negativeTweetsAmount = 0;
        int positiveTweetsAmount = 0;
        String tweet;
        for (Enumeration<String> e = parsedTweets.keys(); e.hasMoreElements();)
        {
            tweet = e.nextElement();
            tweets.add(parsedTweets.get(tweet)+","+tweet);
            if (parsedTweets.get(tweet).equals(Resources.POSITIVE_VALUE)){
                ++positiveTweetsAmount;
            }
            else
            {
                ++negativeTweetsAmount;
            }
        }
        Vector<String> trainingTweets;
        Vector<String> testingTweets;
        int posIndex = 0;
        int negIndex = 0; 
        tweet = "";
        String opinion;
        String originalOpinion;
        String line;
        // Used to calculate the standard deviation
        DescriptiveStatistics stats = new DescriptiveStatistics();
        double wellClassifiedTweets;
        // Used to calculate the standard deviation
        AlgorithmResults algorithmResults = new AlgorithmResults();
        for (int i = 0; i < 10; i++) {
            wellClassifiedTweets = 0;
            trainingTweets = new Vector<String>();
            testingTweets = new Vector<String>();
            posIndex = i * (positiveTweetsAmount/10);
            negIndex = positiveTweetsAmount + i * (negativeTweetsAmount/10);
            for (int j = 0; j < posIndex; j++){
                trainingTweets.add(tweets.get(j));
            }            
            for (int j = posIndex; j < (posIndex+(positiveTweetsAmount/10)); j++){
                testingTweets.add(tweets.get(j));
            }
            for (int j = (posIndex+(positiveTweetsAmount/10)); j < negIndex; j++){
                trainingTweets.add(tweets.get(j));
            }
            for (int j = negIndex; j < (negIndex+(negativeTweetsAmount/10)); j++){
                testingTweets.add(tweets.get(j));
            }
            for (int j = (negIndex+(negativeTweetsAmount/10)); j < tweets.size(); j++){
                trainingTweets.add(tweets.get(j));
            }
            bc = new BayesianClassifier();
            train(trainingTweets);
            //classifyTweets
            for (int j = 0; j < testingTweets.size();j++){
                algorithmResults.incrementTweetsAmount();
                line = testingTweets.get(j);
                tweet = line.substring(line.indexOf(",")+1, line.length());
                opinion = getSemanticOrientation(tweet);
                originalOpinion = line.substring(0,line.indexOf(","));                
                if (opinion.equals(originalOpinion)){
                    algorithmResults.incrementAccurateClassifiedTweetsAmount();
                    ++wellClassifiedTweets;
                    if (opinion.equals(Resources.POSITIVE_VALUE)){
                        algorithmResults.incrementPositiveTweets();
                    }
                    else{
                        if (opinion.equals(Resources.NEGATIVE_VALUE))
                            algorithmResults.incrementNegativeTweets();
                    }
                }else
                {
                   if (opinion.equals(Resources.POSITIVE_VALUE)){
                        algorithmResults.incrementFalsePositiveTweets();
                    }
                    else{
                        if (opinion.equals(Resources.NEGATIVE_VALUE))
                            algorithmResults.incrementFalseNegativeTweets();
                    } 
                }                
                
                if (originalOpinion.equals(Resources.POSITIVE_VALUE)){
                    algorithmResults.incrementOriginalPositiveTweets();
                }
                else{
                    if (originalOpinion.equals(Resources.NEGATIVE_VALUE))
                        algorithmResults.incrementOriginalNegativeTweets();
                }
            }
            stats.addValue((wellClassifiedTweets/(double)testingTweets.size())*100.0);
        }
        // Get the standard deviation of the 10 fold cross validation process
        //System.out.println("Standard Deviation of the Bayesian Polarity Classifier: " + stats.getStandardDeviation());
        return algorithmResults;        
    }        

    private void train(Hashtable<String, String> parsedTweets) {
        String tweet;
        String opinion;
        int i = 0;
        for (Enumeration<String> e = parsedTweets.keys(); e.hasMoreElements();){
            ++i;            
            tweet = e.nextElement();
            opinion = parsedTweets.get(tweet);
            try {
                if (opinion.equals(Resources.POSITIVE_VALUE))
                    bc.teachMatch(tweet);
                else
                    bc.teachNonMatch(tweet);
            } catch (WordsDataSourceException ex) {
            } catch (ClassifierException ex) {
            }
        }
    }

    private void train(Vector<String> trainingTweets) {
        String line;
        String tweet;
        String opinion;        
        for (int i = 0; i < trainingTweets.size();i++){
            line = trainingTweets.get(i);
            tweet = line.substring(line.indexOf(",")+1, line.length());            
            opinion = line.substring(0,line.indexOf(","));
            try {
                if (opinion.equals(Resources.POSITIVE_VALUE))
                    bc.teachMatch(tweet);
                else
                    bc.teachNonMatch(tweet);
            } catch (WordsDataSourceException ex) {
            } catch (ClassifierException ex) {
            }
        }
    }          
}
