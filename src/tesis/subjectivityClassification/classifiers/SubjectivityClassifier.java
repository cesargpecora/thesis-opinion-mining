/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tesis.subjectivityClassification.classifiers;

import java.util.*;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import tesis.opinionMining.AlgorithmResults;
import tesis.util.Resources;

/**
 *
 * @author Cesar
 */
public class SubjectivityClassifier {
    
    public SubjectivityClassifier() {}
    
    public boolean isSubjective(String tweetText){
        OrientationEnum orientation = StrongWordsClassifier.getInstance().isSubjective(tweetText);
        if(orientation.equals(OrientationEnum.SUBJECTIVE))
        {
            orientation = NaiveBayesClassifier.getInstance().isSubjective(tweetText);
        }
        return orientation.equals(OrientationEnum.SUBJECTIVE);
    }
    
    public AlgorithmResults testAlgorithm(Hashtable<String,String> parsedTweets){        
        Vector<String> tweets = new Vector<String>();
        int objectiveTweetsAmount = 0;
        int subjectiveTweetsAmount = 0;
        String tweet;
        for (Enumeration<String> e = parsedTweets.keys(); e.hasMoreElements();)
        {
            tweet = e.nextElement();
            tweets.add(parsedTweets.get(tweet)+","+tweet);
            if (parsedTweets.get(tweet).equals(Resources.SUBJECTIVE_VALUE)){
                ++subjectiveTweetsAmount;
            }
            else
            {
                ++objectiveTweetsAmount;
            }
        }               
        Vector<String> trainingTweets;
        Vector<String> testingTweets;
        int subjIndex = 0;
        int objIndex = 0; 
        tweet = "";
        String classification;
        String originalClassification;
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
            subjIndex = i * (subjectiveTweetsAmount/10);
            objIndex = subjectiveTweetsAmount + i * (objectiveTweetsAmount/10);
            for (int j = 0; j < subjIndex; j++){
                trainingTweets.add(tweets.get(j));
            }            
            for (int j = subjIndex; j < (subjIndex+(subjectiveTweetsAmount/10)); j++){
                testingTweets.add(tweets.get(j));
            }
            for (int j = (subjIndex+(subjectiveTweetsAmount/10)); j < objIndex; j++){
                trainingTweets.add(tweets.get(j));
            }
            for (int j = objIndex; j < (objIndex+(objectiveTweetsAmount/10)); j++){
                testingTweets.add(tweets.get(j));
            }
            for (int j = (objIndex+(objectiveTweetsAmount/10)); j < tweets.size(); j++){
                trainingTweets.add(tweets.get(j));
            }
            NaiveBayesClassifier.getInstance().resetBayesianClassifier();
            NaiveBayesClassifier.getInstance().train(trainingTweets);
            //classifyTweets
            for (int j = 0; j < testingTweets.size();j++){
                algorithmResults.incrementTweetsAmount();
                line = testingTweets.get(j);
                tweet = line.substring(line.indexOf(",")+1, line.length());
                if (isSubjective(tweet))
                    classification = Resources.SUBJECTIVE_VALUE;
                else
                    classification = Resources.OBJECTIVE_VALUE;
                originalClassification = line.substring(0,line.indexOf(","));                
                if (classification.equals(originalClassification)){
                    algorithmResults.incrementAccurateClassifiedTweetsAmount();
                    ++wellClassifiedTweets;
                    if (classification.equals(Resources.SUBJECTIVE_VALUE)){
                        algorithmResults.incrementPositiveTweets();
                    }
                    else{
                        if (classification.equals(Resources.OBJECTIVE_VALUE))
                            algorithmResults.incrementNegativeTweets();
                    }
                }else
                {
                   if (classification.equals(Resources.SUBJECTIVE_VALUE)){
                        algorithmResults.incrementFalsePositiveTweets();
                    }
                    else{
                        if (classification.equals(Resources.OBJECTIVE_VALUE))
                            algorithmResults.incrementFalseNegativeTweets();
                    } 
                }                
                
                if (originalClassification.equals(Resources.SUBJECTIVE_VALUE)){
                    algorithmResults.incrementOriginalPositiveTweets();
                }
                else{
                    if (originalClassification.equals(Resources.OBJECTIVE_VALUE))
                        algorithmResults.incrementOriginalNegativeTweets();
                }                
            }
            stats.addValue((wellClassifiedTweets/(double)testingTweets.size())*100.0);
        }        

        // Get the standard deviation of the 10 fold cross validation process
        //System.out.println("Standard Deviation of the Subjectivity Classifier: " + stats.getStandardDeviation());
        return algorithmResults;
    }      
}
