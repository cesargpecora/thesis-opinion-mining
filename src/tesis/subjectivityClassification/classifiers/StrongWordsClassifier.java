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
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import tesis.subjectivityClassification.exceptions.StrongWordsClassifierTrainingException;
import tesis.util.Config;
import tesis.subjectivityClassification.exceptions.PropertyKeyNotFoundException;

/**
 *
 * @author ezequielaranda
 */
public class StrongWordsClassifier implements IClassifier{
    
    private int wordNumber;
    
    private List<String> strongWords;

    private StrongWordsClassifier() throws StrongWordsClassifierTrainingException {
        strongWords = new LinkedList<String>();
        train();
    }
    
    public static StrongWordsClassifier getInstance() {
        return StrongWordsClassifierHolder.INSTANCE;
    }

    private void train() throws StrongWordsClassifierTrainingException {

        String wordField = "word1=";

        try {

            FileInputStream fstream = new FileInputStream(Config.getStrongWordsFilePath());
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            String currentLine = "";
            while ((currentLine = br.readLine()) != null) {
                int index = currentLine.indexOf(wordField) + 6;
                String word = (currentLine.subSequence(index, currentLine.indexOf(" ", index))).toString();
                String cat = (currentLine.subSequence(5, currentLine.indexOf(" ", 5))).toString();
                if (cat.equals("strongsubj")) {
                    this.strongWords.add(word);
                }
            }
            fstream.close();
            in.close();
            br.close();
        } catch (IOException ex) {
            Logger.getLogger(StrongWordsClassifier.class.getName()).log(Level.SEVERE, null, ex);
            throw new StrongWordsClassifierTrainingException();
        } catch (PropertyKeyNotFoundException ex)
        {
            Logger.getLogger(StrongWordsClassifier.class.getName()).log(Level.SEVERE, null, ex);
            throw new StrongWordsClassifierTrainingException();
        }
        
    }
    
    private static class StrongWordsClassifierHolder{

        private static final StrongWordsClassifier INSTANCE;

        static {
            try {
                INSTANCE = new StrongWordsClassifier();
            } catch (Exception ex) {
                Logger.getLogger(StrongWordsClassifier.class.getName()).log(Level.SEVERE, null, ex);
                throw new ExceptionInInitializerError(ex);
            }
        }
    }
    
    // TODO: Move all params to constructors.
    
    public OrientationEnum isSubjective(String sentence) {
        int counter = 0;
        int maxwords = 2;
        try {
            maxwords = Config.getNumberOfStrongWords();
        } catch (Exception ex) {
            Logger.getLogger(StrongWordsClassifier.class.getName()).log(Level.SEVERE, null, ex);
        } 
       
        for(String word : this.strongWords){
            if(sentence.contains(word))
            {
                counter++;
                if (counter == maxwords)
                {
                    return  OrientationEnum.SUBJECTIVE;
                }
            }
        }
        return OrientationEnum.OBJECTIVE;
    }
    
    
    
}
