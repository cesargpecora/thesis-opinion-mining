/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tesis.util;

import tesis.subjectivityClassification.exceptions.PropertyKeyNotFoundException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author ezequielaranda
 */
public class Config {
    
    private static final String CONFIG_FILE = "app.config";
    
    private static final String FILE_DIR = "app.filedir";
    
    private static final String POLARITY_TWEETS = "app.polaritytweets";        
    
    private static final String SUBJECTIVE_FILE = "app.subjectivefile";
    
    private static final String OBJECTIVE_FILE = "app.objectivefile";
    
    private static final String SUBJ_THRESHOLD = "app.subjthreshold";        
    
    private static final String SEARCH_LANG = "app.searchlang";        
    
    private static final String BAYESIAN_VARIATION = "app.bayesianvariation";
    
    private static final String STRONG_WORDS_FILE = "app.strongwordsfile";
    
    private static final String NUMBER_OF_STRONG_WORDS = "app.strongwordsnumber";
    
    private static final String JASPELL_DICT_DIR = "app.dictdir";
    
    private static final String JASPELL_ENGLISH_DICT = "app.englishdict";
    
    private static final String JASPELL_MISSPELL_DICT = "app.common-misspells";
    
    private static final String POL_THRESHOLD = "app.polthreshold";
    
    private static final String WIEBE_SENTIMENT_DICT = "app.wiebesentimentdict";
    
    private static final String WIEBE_SENTIMENT_DICT_NGRAM_COUNT = "app.wiebesentimentdictngramcount";
    
    private static final String INTENSIFIER_DICT = "app.intensifierdict";
    
    private static final String POLARITY_SHIFTER_DICT = "app.polarityshifterdict";
    
    private static final String STOP_WORDS_DICT = "app.stopwordsdict";
    
    private static final String POS_TAGGER = "app.postagger";
    
    private static final String POS_MINED_TWEETS = "app.posminedtweets";
    
    private static final String NEG_MINED_TWEETS = "app.negminedtweets";
    
    private static final String SUBJ_MINED_TWEETS = "app.subjminedtweets";
    
    private static final String OBJ_MINED_TWEETS = "app.objminedtweets";
    
    private static final String OMALG_THREAD_POOL_SIZE = "app.omalgthreadpoolsize";
    
    private static String getPropertyValue(String propertyKey) throws PropertyKeyNotFoundException, IOException{
        
        InputStream is = null;
            
            Properties prop = new Properties();
            String fileName = Config.CONFIG_FILE;
            
            is = new FileInputStream(fileName);
            prop.load(is);
            is.close();
            
            if (prop.containsKey(propertyKey)){
                return prop.getProperty(propertyKey);
            }
            else
            {
                throw new PropertyKeyNotFoundException();
            }           
    }
    
    public static String getFilePath(String fileName) throws PropertyKeyNotFoundException, IOException
    {
        return Config.getPropertyValue(Config.FILE_DIR).concat(System.getProperty("file.separator")).concat(fileName);
    }
    
    public static String getPolarityTweetsFilePath() throws PropertyKeyNotFoundException, IOException
    {
        return Config.getFilePath(Config.getPropertyValue(Config.POLARITY_TWEETS));
    } 
    
    private static String getJaspellFilePath(String fileName) throws PropertyKeyNotFoundException, IOException
    {
        return Config.getPropertyValue(Config.FILE_DIR).concat(System.getProperty("file.separator")).concat(Config.getPropertyValue(Config.JASPELL_DICT_DIR)).concat(System.getProperty("file.separator")).concat(fileName);
    }
    
    public static String getSubjectiveFilePath() throws PropertyKeyNotFoundException, IOException
    {
        return Config.getFilePath(Config.getPropertyValue(Config.SUBJECTIVE_FILE));
    }        
    
     public static String getJaspellEnglishFilePath() throws PropertyKeyNotFoundException, IOException
    {
        return Config.getJaspellFilePath(Config.getPropertyValue(Config.JASPELL_ENGLISH_DICT));
    }
     
      public static String getJaspellMisspellFilePath() throws PropertyKeyNotFoundException, IOException
    {
        return Config.getJaspellFilePath(Config.getPropertyValue(Config.JASPELL_MISSPELL_DICT));
    }
    
    
    public static String getStrongWordsFilePath() throws PropertyKeyNotFoundException, IOException
    {
        return Config.getFilePath(Config.getPropertyValue(Config.STRONG_WORDS_FILE));
    }   
    
    public static String getObjectiveFilePath() throws PropertyKeyNotFoundException, IOException
    {
        return Config.getFilePath(Config.getPropertyValue(Config.OBJECTIVE_FILE));
    }
    
    public static double getMinSubjectivityRate() throws PropertyKeyNotFoundException, IOException{
        return Double.parseDouble(Config.getPropertyValue(Config.SUBJ_THRESHOLD));
    }
    
    public static String getSearchLanguage() throws PropertyKeyNotFoundException, IOException{
        return Config.getPropertyValue(Config.SEARCH_LANG);
    }    
    
    public static int getNumberOfStrongWords() throws PropertyKeyNotFoundException, IOException{
        return Integer.parseInt(Config.getPropertyValue(Config.NUMBER_OF_STRONG_WORDS));
    }

    public static double getBayesianNeutralVariation() throws PropertyKeyNotFoundException, IOException {
        return Double.parseDouble(Config.getPropertyValue(Config.BAYESIAN_VARIATION));
    }
    
    public static double getPolThreshold() throws PropertyKeyNotFoundException, IOException {
        return Double.parseDouble(Config.getPropertyValue(Config.POL_THRESHOLD));
    }
    
    public static int getWiebeSentimentDictionaryNGramCount() throws PropertyKeyNotFoundException, IOException{
        return Integer.parseInt(Config.getPropertyValue(Config.WIEBE_SENTIMENT_DICT_NGRAM_COUNT));
    }
    
    public static String getWiebeSentimentDictionaryFilePath() throws PropertyKeyNotFoundException, IOException
    {
        return Config.getFilePath(Config.getPropertyValue(Config.WIEBE_SENTIMENT_DICT));
    }
            
    public static String getIntensifierDictionaryFilePath() throws PropertyKeyNotFoundException, IOException
    {
        return Config.getFilePath(Config.getPropertyValue(Config.INTENSIFIER_DICT));
    }
    
    public static String getPolarityShifterDictionaryFilePath() throws PropertyKeyNotFoundException, IOException
    {
        return Config.getFilePath(Config.getPropertyValue(Config.POLARITY_SHIFTER_DICT));
    }
    
    public static String getStopWordsDictionaryFilePath() throws PropertyKeyNotFoundException, IOException
    {
        return Config.getFilePath(Config.getPropertyValue(Config.STOP_WORDS_DICT));
    }
    
    public static String getPOSTaggerFilePath() throws PropertyKeyNotFoundException, IOException
    {
        return Config.getFilePath(Config.getPropertyValue(Config.POS_TAGGER));
    }
    
    public static String getPosMinedTweetsFilePath() throws PropertyKeyNotFoundException, IOException
    {
        return Config.getFilePath(Config.getPropertyValue(Config.POS_MINED_TWEETS));
    }
    
    public static String getNegMinedTweetsFilePath() throws PropertyKeyNotFoundException, IOException
    {
        return Config.getFilePath(Config.getPropertyValue(Config.NEG_MINED_TWEETS));
    }
    
    public static String getSubjMinedTweetsFilePath() throws PropertyKeyNotFoundException, IOException
    {
        return Config.getFilePath(Config.getPropertyValue(Config.SUBJ_MINED_TWEETS));
    }
    
    public static String getObjMinedTweetsFilePath() throws PropertyKeyNotFoundException, IOException
    {
        return Config.getFilePath(Config.getPropertyValue(Config.OBJ_MINED_TWEETS));
    }
    
    public static int getOMAlgThreadPoolSize() throws PropertyKeyNotFoundException, IOException{
        return Integer.parseInt(Config.getPropertyValue(Config.OMALG_THREAD_POOL_SIZE));
    }
}
