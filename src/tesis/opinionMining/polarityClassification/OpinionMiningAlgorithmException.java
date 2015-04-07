/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tesis.opinionMining.polarityClassification;

/**
 *
 * @author Cesar
 */
public class OpinionMiningAlgorithmException extends Exception{
    
    private String message = null;
 
    public OpinionMiningAlgorithmException() {
        super();
    }
 
    public OpinionMiningAlgorithmException(String message) {
        super(message);
        this.message = message;
    }
 
    public OpinionMiningAlgorithmException(Throwable cause) {
        super(cause);
    }
 
    @Override
    public String toString() {
        return message;
    }
 
    @Override
    public String getMessage() {
        return message;
    }
}
