/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tesis.subjectivityClassification.classifiers;

/**
 *
 * @author ezequielaranda
 */
public interface IClassifier {
    public OrientationEnum isSubjective(String sentence);
    
}
