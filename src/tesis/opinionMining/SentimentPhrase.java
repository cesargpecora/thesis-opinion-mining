/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tesis.opinionMining;

/**
 *
 * @author César García Pécora
 */
public class SentimentPhrase {
    String phrase;
    int sentiment;

    public SentimentPhrase() {
    }

    public SentimentPhrase(String phrase) {
        this.phrase = phrase;
    }
    
    public SentimentPhrase(int sentiment,String phrase) {
        this.phrase = phrase;
        this.sentiment = sentiment;
    }

    public int getSentiment() {
        return sentiment;
    }

    public void setSentiment(int sentiment) {
        this.sentiment = sentiment;
    }

    public void setPhrase(String phrase) {
        this.phrase = phrase;
    }

    public String getPhrase() {
        return phrase;
    }

    @Override
    public String toString() {
        return phrase;
    }

    @Override
    public boolean equals(Object obj) {
        return phrase.equals(obj.toString());
    }        

}
