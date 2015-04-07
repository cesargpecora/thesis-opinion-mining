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
import tesis.opinionMining.AlgorithmResults;
import tesis.opinionMining.SentimentPhrase;
import tesis.opinionMining.dictionaries.*;
import tesis.subjectivityClassification.exceptions.PropertyKeyNotFoundException;
import tesis.util.Config;
import tesis.util.Resources;

/**
 *
 * @author César García Pécora
 */
public class MixedAlgorithm extends OpinionMiningAlgorithm{
    SentimentDictionary wiebeSentimentDictionary;    
    FeatureDictionary intensifierFeatureDictionary;
    FeatureDictionary polarityShifterFeatureDictionary;
    FeatureDictionary stopWordsFeatureDictionary;
    boolean doIntensificationTreatment;
    boolean doNegationTreatment;
    boolean doStopWordsTreatment;

    public MixedAlgorithm() {        
        name = "Mixed Algorithm";
        try {
            wiebeSentimentDictionary = new SentimentDictionary(Config.getWiebeSentimentDictionaryFilePath(),Config.getWiebeSentimentDictionaryNGramCount(), new WiebeSentimentDictionaryLineParser());
            intensifierFeatureDictionary = new FeatureDictionary(Config.getIntensifierDictionaryFilePath(), new WiebeIntensifierFeatureDictionaryLineParser());
            polarityShifterFeatureDictionary = new FeatureDictionary(Config.getPolarityShifterDictionaryFilePath(), new WiebePolarityShifterFeatureDictionaryLineParser());
            stopWordsFeatureDictionary = new FeatureDictionary(Config.getStopWordsDictionaryFilePath(), new StopWordsFeatureDictionaryLineParser());
        } catch (PropertyKeyNotFoundException ex) {
            Logger.getLogger(MixedAlgorithm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MixedAlgorithm.class.getName()).log(Level.SEVERE, null, ex);
        }        
        doIntensificationTreatment = true;
        doNegationTreatment = true;
        doStopWordsTreatment = true;
    }
    
    @Override
    public void init() {}

    @Override
    public String getSemanticOrientation(String phrase) {
        // Remove all innecessary dots of the phrase.
        phrase = phrase.replaceAll("\\.+", "\\.");
        String[] sentences = phrase.split("\\.");
        String sentence;       
        int semanticOrientation = 0;
        Vector<SentimentPhrase> sentimentPhrases;
        Vector<Object> partitionedSentence;
                
        for (int i = 0; i < sentences.length; i++) {
            sentimentPhrases = new Vector<SentimentPhrase>();
            partitionedSentence = new Vector<Object>();
            sentence = sentences[i];
            // Remove all innecessary characters of the sentence.            
            sentence = sentence.replaceAll("[^a-zA-Z]", " ");
            // Remove all the redundant blank spaces of the sentence.
            sentence = sentence.trim().replaceAll(" +", " ");

            applySentimentDictionary(sentimentPhrases, partitionedSentence, sentence);

            if (doIntensificationTreatment){
                applyFeature(sentimentPhrases, partitionedSentence, intensifierFeatureDictionary, 2);                
            }

            if (doNegationTreatment)
                applyFeature(sentimentPhrases, partitionedSentence, polarityShifterFeatureDictionary, -1);
            
            for (SentimentPhrase sentimentPhrase : sentimentPhrases){
                semanticOrientation += sentimentPhrase.getSentiment();                
            }
        }

        if (semanticOrientation > 0){
            return Resources.POSITIVE_VALUE;
        }
        else{
            if (semanticOrientation < 0)
                return Resources.NEGATIVE_VALUE;
            else
                return Resources.NEUTRAL_VALUE;
        }
    }

    @Override
    public AlgorithmResults testAlgorithm(Hashtable<String,String> parsedTweets) {        
        String tweet;
        String opinion;
        String originalOpinion;
        AlgorithmResults algorithmResults = new AlgorithmResults();
        for (Enumeration<String> e = parsedTweets.keys(); e.hasMoreElements();){
            algorithmResults.incrementTweetsAmount();
            tweet = e.nextElement();
            opinion = getSemanticOrientation(tweet);
            originalOpinion = parsedTweets.get(tweet);
            if (opinion.equals(originalOpinion)){
                algorithmResults.incrementAccurateClassifiedTweetsAmount();
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
        return algorithmResults;
    }       

    private void applySentimentDictionary(Vector<SentimentPhrase> sentimentPhrases, Vector<Object> partitionedSentence, String sentence){
        SentimentPhrase sentimentPhrase;
        String[] words;
        String internalPhrase;
        int nGramCount = wiebeSentimentDictionary.getNGRAMCOUNT();
        boolean found = false;        
        words = sentence.split(" ");
        for (int j = 0; j < words.length; j++) {
            partitionedSentence.add(words[j]);
            if ((words.length-j) < wiebeSentimentDictionary.getNGRAMCOUNT())
                nGramCount = words.length-j;
            else
                nGramCount = wiebeSentimentDictionary.getNGRAMCOUNT();
            found = false;
            while ((!found)&&(nGramCount > 0)){
                 internalPhrase = getInternalPhrase(nGramCount,j,words);
                 internalPhrase = internalPhrase.toLowerCase();
                 // if the word is not a stopWord and the feeling dictionary has it, set the sentiment of the word.
                 if (!(isStopWord(nGramCount,internalPhrase)) &&(wiebeSentimentDictionary.hasPhrase(internalPhrase))){
                    sentimentPhrase = new SentimentPhrase();
                    sentimentPhrase.setSentiment(wiebeSentimentDictionary.getSentiment(internalPhrase));
                    sentimentPhrase.setPhrase(internalPhrase);
                    partitionedSentence.remove(partitionedSentence.lastElement());
                    partitionedSentence.add(sentimentPhrase);
                    sentimentPhrases.add(sentimentPhrase);
                    found = true;
                    j += nGramCount-1;
                 }
                 else
                 {
                    --nGramCount;
                 }
            }
        }        
    }           
    
    private void applyFeature(Vector<SentimentPhrase> sentimentPhrases, Vector<Object> partitionedSentence, FeatureDictionary featureDictionary, int sentimentVariation){                
        //works for intensification and negation features.
        int j = 0;
        SentimentPhrase sentimentPhrase;
        int sentimentPhraseIndex = 0;
        int featureSearchIndex;
        boolean outOfBounds;        
        while (j < sentimentPhrases.size()) {                        
            sentimentPhrase = sentimentPhrases.get(j);
            //prevent to set feature to a word that is a feature and it's in the sentiment dictionary
            if (!featureDictionary.hasFeature(sentimentPhrase.getPhrase())){
                sentimentPhraseIndex = partitionedSentence.indexOf(sentimentPhrase, sentimentPhraseIndex + 1);
                featureSearchIndex = sentimentPhraseIndex;                
                --featureSearchIndex;
                outOfBounds = false;
                while ((featureSearchIndex >= (sentimentPhraseIndex-4)) &&(!outOfBounds)) {
                    if (featureSearchIndex >= 0){                                                    
                        if (featureDictionary.hasFeature(partitionedSentence.get(featureSearchIndex).toString())){
                            updateStructures(featureSearchIndex, sentimentPhrase, partitionedSentence, sentimentPhrases, sentimentPhraseIndex, sentimentVariation);
                            // update indexes
                            sentimentPhraseIndex = partitionedSentence.indexOf(sentimentPhrase);
                            featureSearchIndex = sentimentPhraseIndex;
                            j = sentimentPhrases.indexOf(sentimentPhrase);
                        }
                     }
                     else
                        outOfBounds = true;                                                                                              
                    --featureSearchIndex;
                }
            }
            ++j;
        }        
    }        

    private boolean isStopWord(int nGramCount, String internalPhrase){
        if (doStopWordsTreatment)
            return ((nGramCount == 1)&&(stopWordsFeatureDictionary.hasFeature(internalPhrase)));
        return false;
    }

    public void updateStructures(int featureIndex,SentimentPhrase sentimentPhrase, Vector<Object> partitionedSentence, Vector<SentimentPhrase> sentimentPhrases, int sentimentPhraseIndex, int sentimentVariation){
        
        // set new content and sentiment of the phrase
        String newSentimentWord = "";        
        sentimentPhraseIndex = partitionedSentence.indexOf(sentimentPhrase,sentimentPhraseIndex);
        int origFeatureIndex = featureIndex;                
        while (featureIndex != sentimentPhraseIndex){
            newSentimentWord += partitionedSentence.get(featureIndex).toString()+" ";
            ++featureIndex;
        }
        newSentimentWord += sentimentPhrase.getPhrase();
        sentimentPhrase.setPhrase(newSentimentWord);
        sentimentPhrase.setSentiment((sentimentPhrase).getSentiment()*sentimentVariation);
        
        // update structures 
        featureIndex = origFeatureIndex;
        Object phrase;
        phrase = partitionedSentence.get(origFeatureIndex);
        if (sentimentPhrases.indexOf(sentimentPhrase) > 0){           
            if (sentimentPhrases.get(sentimentPhrases.indexOf(sentimentPhrase)-1).equals(phrase))
                sentimentPhrases.remove(sentimentPhrases.indexOf(sentimentPhrase)-1);
        }
        while (featureIndex != sentimentPhraseIndex){                        
            partitionedSentence.remove(origFeatureIndex);
            ++featureIndex;
        }                
                
    }

    private String getInternalPhrase(int nGramCount, int j, String[] words) {
        String internalPhrase = "";
        int end = j + nGramCount;
        while (j < end){
            internalPhrase += words[j] + " ";
            ++j;
        }
        internalPhrase = internalPhrase.substring(0, internalPhrase.length()-1);
        return internalPhrase;
    }   

    public void setDoIntensificationTreatment(boolean doIntensificationTreatment) {
        this.doIntensificationTreatment = doIntensificationTreatment;
    }

    public void setDoNegationTreatment(boolean doNegationTreatment) {
        this.doNegationTreatment = doNegationTreatment;
    }

    public void setDoStopWordsTreatment(boolean doStopWordsTreatment) {
        this.doStopWordsTreatment = doStopWordsTreatment;
    }

    public boolean isDoIntensificationTreatment() {
        return doIntensificationTreatment;
    }

    public boolean isDoNegationTreatment() {
        return doNegationTreatment;
    }

    public boolean isDoStopWordsTreatment() {
        return doStopWordsTreatment;
    }       

}
