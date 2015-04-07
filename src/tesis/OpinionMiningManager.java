/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tesis;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import tesis.opinionMining.AlgorithmResults;
import tesis.opinionMining.TweetCSVFileParser;
import tesis.opinionMining.polarityClassification.OpinionMiningAlgorithmsContainer;
import tesis.preprocessing.processors.*;
import tesis.preprocessing.removalrules.CompoundRule;
import tesis.preprocessing.removalrules.ContainsWordsRule;
import tesis.preprocessing.removalrules.IRemovalRule;
import tesis.preprocessing.removalrules.StartsWithRule;
import tesis.subjectivityClassification.classifiers.NaiveBayesClassifier;
import tesis.subjectivityClassification.classifiers.SubjectivityClassifier;
import tesis.subjectivityClassification.exceptions.BayesianClassifierTrainingException;
import tesis.util.TweetSearchEngine;

/**
 *
 * @author César García Pécora
 */
public class OpinionMiningManager {
private OpinionMiningAlgorithmsContainer opinionMiningAlgorithmsContainer;
private static OpinionMiningManager opinionMiningManager;
private SubjectivityClassifier subjectivityClassifier;
private IRemovalRule removalRule;
private IPreprocessor preprocessor;
private HashMap<String,String> originalTweets;

    private OpinionMiningManager() {        
    }        

    public void init(){        
        opinionMiningAlgorithmsContainer = new OpinionMiningAlgorithmsContainer();        
        this.removalRule = this.buildRemovalRule();
        subjectivityClassifier = new SubjectivityClassifier();
        originalTweets = new HashMap<String, String>();
    }
    
    public void initSubjectiveAlgorithms(){
        this.removalRule = this.buildRemovalRule();
        subjectivityClassifier = new SubjectivityClassifier();
        if (originalTweets == null)
            originalTweets = new HashMap<String, String>();
    }
    
    public static OpinionMiningManager getInstance() {
        if (opinionMiningManager == null)
            opinionMiningManager = new OpinionMiningManager();
        return opinionMiningManager;
    }
    
    public void initSubjectivityAlgorithm(){
        try {
            NaiveBayesClassifier.getInstance().reTrain();
        } catch (BayesianClassifierTrainingException ex) {
            Logger.getLogger(OpinionMiningManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public OpinionMiningAlgorithmsContainer getOpinionMiningAlgorithmsContainer() {
        return opinionMiningAlgorithmsContainer;
    }
    
    public List<String> loadTweets(String search, int maxSearchResults){
        originalTweets.clear();
        List<String> loadedTweets = TweetSearchEngine.search(search, maxSearchResults);
        for (String tweet : loadedTweets) {
            originalTweets.put(tweet, tweet);
        }
        return TweetSearchEngine.search(search, maxSearchResults);
    }
    
    public AlgorithmResults testSubjectivityAlgorithm(Hashtable<String,String> parsedTweets){
        return subjectivityClassifier.testAlgorithm(parsedTweets);
    }
    
    public Hashtable<String,String> getTweetsFromFile(String filePath) {
        TweetCSVFileParser parser = new TweetCSVFileParser(filePath);
        Hashtable<String,String> parsedTweets = parser.getParsedTweets();
        return parsedTweets;
    }
    
    public List<String> getSubjectiveTweets(List<String> incomingTweets){
        List<String> result = new ArrayList<String>();
        for(String tweet : incomingTweets)
        {
            if(subjectivityClassifier.isSubjective(tweet))
            {
               result.add(tweet);                 
            }
        }
        return result;
    }
    
    public Hashtable<String,String> getSubjectiveTweets(Hashtable<String,String> incomingTweets){
        Hashtable<String,String> result = new Hashtable<String,String>();
        String tweet;
        for (Enumeration<String> e = incomingTweets.keys(); e.hasMoreElements();)
        {
            tweet = e.nextElement();        
            if(subjectivityClassifier.isSubjective(tweet))
            {
               result.put(tweet,incomingTweets.get(tweet));                 
            }
        }
        return result;
    }

    public HashMap<String, String> getOriginalTweets() {
        return originalTweets;
    }        
    
    public List<String> applyRemovalRule(List<String> incomingTweets) {
        List<String> result = new ArrayList<String>();
        for(String tweet : incomingTweets)
        {
            if(!this.removalRule.matches(tweet))
            {
               result.add(tweet);                 
            }
        }
        return result;
    }
    
    public Hashtable<String,String> applyRemovalRule(Hashtable<String,String> incomingTweets) {
        Hashtable<String,String> result = new Hashtable<String,String>();
        String tweet;
        for (Enumeration<String> e = incomingTweets.keys(); e.hasMoreElements();){            
            tweet = e.nextElement();
            if(!this.removalRule.matches(tweet))
            {
               result.put(tweet,incomingTweets.get(tweet));                 
            }
        }
        return result;
    }

    public List<String> applyPreprocessing(List<String> incomingTweets, String search) {
        this.preprocessor = this.buildPreprocessor(search);
        originalTweets.clear();
        List<String> result = new ArrayList<String>();
        String processedTweet;
        for(String tweet : incomingTweets)
        {
            processedTweet = this.preprocessor.processTweet(tweet);
            result.add(processedTweet);
            originalTweets.put(processedTweet, tweet);
        }
        return result;
    }

    private IRemovalRule buildRemovalRule() {
        CompoundRule resultingRule = new CompoundRule();
        
        // Removes retweets
        IRemovalRule rt = new StartsWithRule("RT");
        resultingRule.addRemovalRule(rt);
        
        // Removes tweets containing URLs
        IRemovalRule urls = new ContainsWordsRule("http");
        resultingRule.addRemovalRule(urls);
        
        // Removes replies
        IRemovalRule reply = new StartsWithRule("@");
        resultingRule.addRemovalRule(reply);
        
        return resultingRule;
    }

    private IPreprocessor buildPreprocessor(String search) {
        CompoundProcessor resultingPreprocessor = new CompoundProcessor();
        
        // Removes search term
        IPreprocessor searchTerm = new RemoveWordsPreprocessor(search); 
        resultingPreprocessor.addPreprocessor(searchTerm);
        // Translates slang words.
        IPreprocessor slang = new SlangPreprocessor();
        resultingPreprocessor.addPreprocessor(slang);
        // Replaces mispelled words
        IPreprocessor spellCheck = new SpellcheckProcessor();
        //resultingPreprocessor.addPreprocessor(spellCheck);
        
        return resultingPreprocessor;
    }
    
    
}
