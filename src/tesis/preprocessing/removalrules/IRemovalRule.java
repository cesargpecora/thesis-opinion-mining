/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tesis.preprocessing.removalrules;

/**
 *
 * @author ezequielaranda
 */
public interface IRemovalRule {
    public boolean matches(String tweetText);
}
