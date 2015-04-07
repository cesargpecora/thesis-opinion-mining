/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tesis.opinionMining;

/**
 *
 * @author Cesar
 */
public class AlgorithmResults {
    private int tweetsAmount = 0;
    private int accurateClassifiedTweetsAmount = 0;
    private int positiveTweets = 0;
    private int negativeTweets = 0;
    private int falsePositiveTweets = 0;
    private int falseNegativeTweets = 0;
    private int originalPositiveTweets = 0;
    private int originalNegativeTweets = 0;

    public AlgorithmResults() {
    }        

    public int getAccurateClassifiedTweetsAmount() {
        return accurateClassifiedTweetsAmount;
    }

    public int getFalseNegativeTweets() {
        return falseNegativeTweets;
    }

    public int getFalsePositiveTweets() {
        return falsePositiveTweets;
    }

    public int getNegativeTweets() {
        return negativeTweets;
    }

    public int getOriginalNegativeTweets() {
        return originalNegativeTweets;
    }

    public int getOriginalPositiveTweets() {
        return originalPositiveTweets;
    }

    public int getPositiveTweets() {
        return positiveTweets;
    }

    public int getTweetsAmount() {
        return tweetsAmount;
    }
    
    public void incrementAccurateClassifiedTweetsAmount(){
        ++accurateClassifiedTweetsAmount;
    }
    
    public void incrementFalseNegativeTweets(){
        ++falseNegativeTweets;
    }
    
    public void incrementFalsePositiveTweets(){
        ++falsePositiveTweets;
    }
    
    public void incrementNegativeTweets(){
        ++negativeTweets;
    }
    
    public void incrementOriginalNegativeTweets(){
        ++originalNegativeTweets;
    }
    
    public void incrementOriginalPositiveTweets(){
        ++originalPositiveTweets;
    }
    
    public void incrementPositiveTweets(){
        ++positiveTweets;
    }
    
    public void incrementTweetsAmount(){
        ++tweetsAmount;
    }        
    
}
