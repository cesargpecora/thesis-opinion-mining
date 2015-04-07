/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tesis.subjectivityClassification.classifiers;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.classifier4J.ClassifierException;
import net.sf.classifier4J.bayesian.BayesianClassifier;
import net.sf.classifier4J.bayesian.WordsDataSourceException;
import tesis.subjectivityClassification.exceptions.BayesianClassifierTrainingException;
import tesis.util.Config;
import tesis.subjectivityClassification.exceptions.PropertyKeyNotFoundException;

/**
 *
 * @author ezequielaranda
 */
public final class NaiveBayesClassifier implements IClassifier{
    
    private BayesianClassifier bc;

    private NaiveBayesClassifier() throws BayesianClassifierTrainingException {
      
        bc = new BayesianClassifier();
        train();
    }
    
    public static NaiveBayesClassifier getInstance() {
        return NaiveBayesClassifierHolder.INSTANCE;
    } 
    
    private static class NaiveBayesClassifierHolder{

        private static final NaiveBayesClassifier INSTANCE;

        static {
            try {
                INSTANCE = new NaiveBayesClassifier();
            } catch (Exception ex) {
                Logger.getLogger(NaiveBayesClassifier.class.getName()).log(Level.SEVERE, null, ex);
                throw new ExceptionInInitializerError(ex);
            }
        }
    }
    
    public void resetBayesianClassifier(){
        bc = new BayesianClassifier();
    }
    
    public void reTrain() throws BayesianClassifierTrainingException{
        bc = new BayesianClassifier();
        train();
    }

    public OrientationEnum isSubjective(String sentence) {
        
        OrientationEnum result = OrientationEnum.NEUTRAL;
        
        try {
            double classification = bc.classify(sentence);
            
            double variation = Math.abs(classification - Config.getMinSubjectivityRate());
            
            if (variation < Config.getBayesianNeutralVariation())
            {
                result = OrientationEnum.NEUTRAL;
            }    
            else
            {
                if (Double.compare(classification, Config.getMinSubjectivityRate()) > 0){
                    result = OrientationEnum.SUBJECTIVE;
                }
                else{
                    result = OrientationEnum.OBJECTIVE;
                }
            }
            
        }
        catch (WordsDataSourceException ex) {
            Logger.getLogger(NaiveBayesClassifier.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassifierException ex) {
            Logger.getLogger(NaiveBayesClassifier.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            return result;
        }
        
        
    }        
    
    public void train(Vector<String> trainingTweets) {
        String line;
        String tweet;
        String classification;        
        for (int i = 0; i < trainingTweets.size();i++){
            line = trainingTweets.get(i);
            tweet = line.substring(line.indexOf(",")+1, line.length());            
            classification = line.substring(0,line.indexOf(","));
            try {
                if (classification.equals("subjective"))
                    bc.teachMatch(tweet);
                else
                    bc.teachNonMatch(tweet);
            } catch (WordsDataSourceException ex) {
            } catch (ClassifierException ex) {
            }
        }
    }

     protected void train() throws BayesianClassifierTrainingException {
         FileInputStream subjectiveFileStream = null;
         FileInputStream objectiveFileStream = null;
         try {
           
           subjectiveFileStream = new FileInputStream(Config.getSubjectiveFilePath());
           objectiveFileStream = new FileInputStream(Config.getObjectiveFilePath());


           DataInputStream subjectiveInputStream = new DataInputStream(subjectiveFileStream);
           DataInputStream objectiveInputStream = new DataInputStream(objectiveFileStream);


           BufferedReader subjectiveBufferedReader = new BufferedReader(new InputStreamReader(subjectiveInputStream));
           BufferedReader objectiveBufferedReader = new BufferedReader(new InputStreamReader(objectiveInputStream));


           String currentLine = "";
           while ((currentLine = subjectiveBufferedReader.readLine()) != null) {
                try {
                    bc.teachMatch(currentLine);
                } catch (WordsDataSourceException ex) {
                    Logger.getLogger(NaiveBayesClassifier.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassifierException ex) {
                    Logger.getLogger(NaiveBayesClassifier.class.getName()).log(Level.SEVERE, null, ex);
                }

           }
           currentLine = "";
           while ((currentLine = objectiveBufferedReader.readLine()) != null) {
                try {
                    bc.teachNonMatch(currentLine);
                } catch (WordsDataSourceException ex) {
                    Logger.getLogger(NaiveBayesClassifier.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassifierException ex) {
                    Logger.getLogger(NaiveBayesClassifier.class.getName()).log(Level.SEVERE, null, ex);
                }
           }
        } catch (PropertyKeyNotFoundException ex) {
            Logger.getLogger(NaiveBayesClassifier.class.getName()).log(Level.SEVERE, null, ex);
            throw new BayesianClassifierTrainingException();
        } catch (IOException ex) {
            Logger.getLogger(NaiveBayesClassifier.class.getName()).log(Level.SEVERE, null, ex);
            throw new BayesianClassifierTrainingException();
        }
        finally
        {   
            try {
                subjectiveFileStream.close();
                objectiveFileStream.close();
            } catch (IOException ex) {
                Logger.getLogger(NaiveBayesClassifier.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
}
