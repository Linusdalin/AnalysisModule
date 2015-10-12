package analysis2;

import classifiers.Classification;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Outcome of the analysis
 */

public class NewAnalysisOutcome {

    private final TextFragment textFragment;
    private List<Classification> classifications;
    private List<String> comments;

    public NewAnalysisOutcome(TextFragment textFragment) {

        this.textFragment = textFragment;
        comments = new ArrayList<>();
    }

    public void setClassifications(List<Classification> classifications) {

        this.classifications = classifications;
    }

    public List<Classification> getClassifications() {
        return classifications;
    }

    public TextFragment getTextFragment() {
        return textFragment;
    }


    public List<String> getComments() {
        return comments;
    }

    public void setComment(String comment) {
        this.comments.add(comment);
    }

    public void setComments(List<String> comments) {
        this.comments = comments;
    }

    public void addClassification(Classification classification){

        this.classifications.add(classification);
    }
}
