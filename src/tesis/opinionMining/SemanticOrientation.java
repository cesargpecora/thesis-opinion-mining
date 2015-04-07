/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tesis.opinionMining;

/**
 *
 * @author César García Pécora
 */
public class SemanticOrientation {

    private double negativeOrientation;
    private double positiveOrientation;
    private double neutralOrientation;

    public SemanticOrientation() {
        negativeOrientation = 0.0;
        positiveOrientation = 0.0;
        neutralOrientation = 0.0;
    }

    public void setNegativeOrientation(double negativeOrientation) {
        this.negativeOrientation = negativeOrientation;
    }

    public void setNeutralOrientation(double neutralOrientation) {
        this.neutralOrientation = neutralOrientation;
    }

    public void setPositiveOrientation(double positiveOrientation) {
        this.positiveOrientation = positiveOrientation;
    }

    public double getNegativeOrientation() {
        return negativeOrientation;
    }

    public double getNeutralOrientation() {
        return neutralOrientation;
    }

    public double getPositiveOrientation() {
        return positiveOrientation;
    }

}
