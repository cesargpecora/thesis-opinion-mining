/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tesis.opinionMining.polarityClassification;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import tesis.opinionMining.AlgorithmResults;
import tesis.opinionMining.POSTagger;
import tesis.subjectivityClassification.exceptions.PropertyKeyNotFoundException;
import tesis.util.Config;
import tesis.util.Resources;

/**
 *
 * @author César García Pécora
 */
public class TurneyAlgorithm  extends OpinionMiningAlgorithm{
    POSTagger POStagger;
    double hitsPoor;
    double hitsExcellent;

    public TurneyAlgorithm() {
        POStagger = new POSTagger();
        name = "Turney Algorithm";        
    }
    
    @Override
    public void init() throws OpinionMiningAlgorithmException {
        hitsPoor = this.getResultsAmount("poor","");
        hitsExcellent = this.getResultsAmount("excellent","");
    }

    @Override
    public String getSemanticOrientation(String phrase) throws OpinionMiningAlgorithmException{
        phrase = phrase.replaceAll("\\.+", "\\.");
        String[] sentences = phrase.split("\\.");
        String sentence;       
        double semanticOrientation = 0;
        String taggedPhrase;
        Vector<String> internalPhrases;
                
        for (int i = 0; i < sentences.length; i++) {
            sentence = sentences[i];
            // Remove all innecessary characters of the sentence.            
            sentence = sentence.replaceAll("[^a-zA-Z]", " ");
            // Remove all the redundant blank spaces of the sentence.
            sentence = sentence.trim().replaceAll(" +", " ");            
            
            taggedPhrase = POStagger.tagPhrase(sentence);        
            internalPhrases = this.extractPhrases(taggedPhrase);                                             
            
            for (String internalPhrase : internalPhrases) {
                semanticOrientation += this.semanticOrientation(internalPhrase,hitsPoor,hitsExcellent);
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
    
    // Turney's Algorithm has been modified to take common and superlative adjectives into account (JJ y JJS) 
    // that are alone but are really important when analyzing the semantic orientation of a phrase.
    private Vector<String> extractPhrases(String taggedPhrase){
        Vector<String> phrases = new Vector<String>();
        String[] wordsList = taggedPhrase.split(" ");
        String[] word1Parts;
        String[] word2Parts;
        String[] word3Parts;

        for (int i = 0; i < wordsList.length; i++) {           
            word1Parts = wordsList[i].split("_");                             
            if ((word1Parts.length > 1) && word1Parts[1].equals("JJ")){
                if (i < (wordsList.length-1)){
                    word2Parts = wordsList[i+1].split("_");
                    if ((word2Parts.length > 1) && ((word2Parts[1].equals("NN")) || (word2Parts[1].equals("NNS")))){
                        phrases.add(word1Parts[0]+" "+word2Parts[0]);
                        i = i+1;
                    }
                    else
                        if ((word2Parts.length > 1) && word2Parts[1].equals("JJ") ){
                            if (i < (wordsList.length-2)){
                                word3Parts = wordsList[i+2].split("_");
                                if ((word3Parts.length > 1) && !((word3Parts[1].equals("NN")) || (word3Parts[1].equals("NNS")))){
                                    phrases.add(word1Parts[0]+" "+word2Parts[0]);
                                    i = i+1;
                                }
                            }else
                            {
                                phrases.add(word1Parts[0]+" "+word2Parts[0]);
                                i = i+1;
                            }
                        }
                        else{
                            //Turney's Algorithm modification
                            phrases.add(word1Parts[0]);                            
                        }
                }else{
                    //Turney's Algorithm modification
                    phrases.add(word1Parts[0]);                    
                }
            }else
                if ((word1Parts.length > 1) && ((word1Parts[1].equals("RB")) || (word1Parts[1].equals("RBR")) || (word1Parts[1].equals("RBS")))){
                    if (i < (wordsList.length-1)){
                        word2Parts = wordsList[i+1].split("_");
                        if ((word2Parts.length > 1) && ((word2Parts[1].equals("VB")) || (word2Parts[1].equals("VBD")) || (word2Parts[1].equals("VBN")) || (word2Parts[1].equals("VBG")))){
                            phrases.add(word1Parts[0]+" "+word2Parts[0]);
                            i = i+1;
                        }
                        else
                            if ((word2Parts.length > 1) && word2Parts[1].equals("JJ") ){
                                if (i < (wordsList.length-2)){
                                    word3Parts = wordsList[i+2].split("_");
                                    if ((word3Parts.length > 1) && !((word3Parts[1].equals("NN")) || (word3Parts[1].equals("NNS")))){
                                        phrases.add(word1Parts[0]+" "+word2Parts[0]);
                                        i = i+1;
                                    }
                                }else
                                {
                                    phrases.add(word1Parts[0]+" "+word2Parts[0]);
                                    i = i+1;
                                }
                            }
                    }
                }else
                    if ((word1Parts.length > 1) && ((word1Parts[1].equals("NN")) || (word1Parts[1].equals("NNS")))){
                        if (i < (wordsList.length-1)){
                            word2Parts = wordsList[i+1].split("_");
                            if ((word2Parts.length > 1) && word2Parts[1].equals("JJ") ){
                                if (i < (wordsList.length-2)){
                                    word3Parts = wordsList[i+2].split("_");
                                    if ((word3Parts.length > 1) && !((word3Parts[1].equals("NN")) || (word3Parts[1].equals("NNS")))){
                                        phrases.add(word1Parts[0]+" "+word2Parts[0]);
                                        i = i+1;
                                    }
                                }else
                                {
                                    phrases.add(word1Parts[0]+" "+word2Parts[0]);
                                    i = i+1;
                                }
                            }
                        }
                    }
                    else{
                        //Turney's Algorithm modification
                        if ((word1Parts.length > 1) && word1Parts[1].equals("JJS") ){
                            phrases.add(word1Parts[0]);                            
                        }
                    }                        
        }
        return phrases;
    }

    private double getResultsAmount(String phrase,String type) throws OpinionMiningAlgorithmException {
        String resultsAmount = "";
        BufferedReader rd = null;
        try {
            phrase = phrase.replace(" ","+");
            String request;
            if (!type.equals(""))
                request = "http://ar.search.yahoo.com/search?p=%22"+phrase+"%22+NEAR+"+type+"&fr=yfp&toggle=1&cop=&ei=UTF-8&rd=r1";
            else
                request = "http://ar.search.yahoo.com/search?p=%22"+phrase+"%22&fr=yfp&toggle=1&cop=&ei=UTF-8&rd=r1";
            URL url = new URL(request);
            URLConnection conn = url.openConnection();
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while (((line = rd.readLine()) != null)&&(!line.contains("id=\"resultCount\">"))) {
            }
            String[] string1 = line.split("id=\"resultCount\">");
            String[] string2 = string1[1].split("<");
            resultsAmount = string2[0];
        } catch (IOException e) {            
            throw new OpinionMiningAlgorithmException("Yahoo Search Failure. "+e.getMessage());
        } finally {
            if (rd != null) {
                try {
                    rd.close();
                } catch (IOException ex) {                    
                }
            }
        }
        if (!resultsAmount.equals("")){
            resultsAmount = resultsAmount.replace(".","");
            return new Double(resultsAmount).doubleValue();
        }
        else
            return 0.0;
    }

    private double semanticOrientation(String phrase,double hitsPoor,double hitsExcellent) throws OpinionMiningAlgorithmException{
        double hitsPhraseNearExcellent,hitsPhraseNearPoor,semanticOrientation;
        hitsPhraseNearExcellent = this.getResultsAmount(phrase, "excellent")+ 0.01;
        hitsPhraseNearPoor = this.getResultsAmount(phrase, "poor") + 0.01;
        semanticOrientation = (hitsPhraseNearExcellent*hitsPoor)/(hitsPhraseNearPoor*hitsExcellent);
        semanticOrientation = (Math.log10(semanticOrientation)/Math.log10((double)2));
        return semanticOrientation;
    }

    @Override
    public AlgorithmResults testAlgorithm(Hashtable<String,String> parsedTweets) throws OpinionMiningAlgorithmException {
        String tweet;
        String opinion;
        String originalOpinion;
        AlgorithmResults algorithmResults = new AlgorithmResults();
        String unparsedResult;
        
        // Create a Thread Pool to execute the algorithm
        ExecutorService calcExecutor;            
        try {
            calcExecutor = Executors.newFixedThreadPool(Config.getOMAlgThreadPoolSize());        
                
            ArrayList<OpinionMiningAlgorithmExecutor> algorithmExecutors = new ArrayList<OpinionMiningAlgorithmExecutor>();

            // Reference to the results.
            List<Future<String>> allResults = null;

            // Create the Threads
            for (Enumeration<String> en = parsedTweets.keys(); en.hasMoreElements();)
            {
                algorithmExecutors.add(new OpinionMiningAlgorithmExecutor(this, en.nextElement()));
            }
                
            // Perform the calculations.
            allResults = calcExecutor.invokeAll(algorithmExecutors);
            
            // Get the results.
        
            for (Future<String> algorithmResult : allResults) 
            {
                unparsedResult = algorithmResult.get();
                algorithmResults.incrementTweetsAmount();
                opinion = unparsedResult.substring(0,unparsedResult.indexOf(","));                
                tweet = unparsedResult.substring(unparsedResult.indexOf(",")+1, unparsedResult.length());
                
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
        } catch (InterruptedException ex){
            Logger.getLogger(OpinionMiningAlgorithm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExecutionException ex) {
            throw new OpinionMiningAlgorithmException(ex.getMessage());        
        } catch (PropertyKeyNotFoundException ex) {
            Logger.getLogger(OpinionMiningAlgorithm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(OpinionMiningAlgorithm.class.getName()).log(Level.SEVERE, null, ex);
        }
        return algorithmResults;
    }
}
