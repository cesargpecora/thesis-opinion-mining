/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tesis.util;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Tweet;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

/**
 *
 * @author ezequielaranda
 * 
 * Uses Twitter4j's TwitterFactory and Query classes to configure and run a search for a desired term.
 */
public class TweetSearchEngine {
    
    public TweetSearchEngine() {
    }
      
    public static List<String> search(String searchTerm, int maxSearchResults){
        List<String> results = new ArrayList<String>();
        try {
            Twitter twitter = new TwitterFactory().getInstance();
            Query query = new Query(searchTerm);
            query.setLang(Config.getSearchLanguage());
            int pages = (maxSearchResults / 100) +1;                                        
            query.setRpp(100);
            for (int i=1; i<=pages; i++){                
                query.setPage(i);
                QueryResult result = twitter.search(query);
                for (Tweet t : result.getTweets()) {                    
                    results.add(t.getText());
                    --maxSearchResults;
                    if (maxSearchResults == 0)
                        return results;
                }
            }            
        }
        catch (TwitterException ex) {
            Logger.getLogger(TweetSearchEngine.class.getName()).log(Level.SEVERE, null, ex);            
        }
        finally
        {
            return results;
        }
        
    }  
}
