package extractors;

import analysis.*;
import parse.AnalysisFragment;
import textPatterns.TextPattern;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 *
 *      Extracts all fragments that are related to Approval by looking for payment related words and combinations
 *
 *      The board approvals clause states that the board has approved the agreement and submitted it to the
 *      company's stockholder's for adoption.
 *
 *      The provision appears as a separate clause in approximately 20% of seller's representations in purchase agreements.
 *
 *          //TODO: look and report exceptions to this according to the rules above
 *          //TODO: Add tags
 *
 *      Example:
 *
 *
 *
 *      (a) Approval: The Board of Directors of Company has approved this Agreement.
 *      (b) Advisable (optional): The Board of Directors determined that this Agreement is advisable and in the best interests of the stockholders of Company.
 *      (c) Recommended (optional): The Board of Directors recommended that the stockholders of Company approve this Agreement and consummation of the Merger.
 *      (d) Submitted: The Board of Directors [has submitted / will submit] the Agreement to the Stockholders for their approval.
 *
 *
 *      "No Consents Required. No consent, authorization, order or approval of, or filing or registration with, or notification to any court, administrative agency or commission or other governmental authority or instrumentality is required by the Seller in connection with the execution, delivery and performance by the Seller of this Agreement or the consummation by the Seller of the transactions contemplated hereby. "
 *
 *      "The affirmative vote of the holders of [a majority / two-thirds / other percentage] of the outstanding shares of Common Stock entitled to vote on this Agreement is the only vote of the holders of securities of the Company necessary to approve this Agreement. "
 */

public class ApprovalExtractor extends FeatureExtractor implements FeatureExtractorInterface {

    // Patterns to look for

    private static final TextPattern[] patterns = {

            // Board Approval

            new TextPattern(new String[] {"the", "board", "has", "approved"}).allPresent(),
            new TextPattern(new String[] {"the", "board", "is", "advisable"}).allPresent(),
            new TextPattern(new String[] {"the", "board", "recommend", "stockholder"}).allPresent(),
            new TextPattern(new String[] {"the", "board", "submit", "stockholder", "approval"}).allPresent(),

            // Government approval

            new TextPattern(new String[] {"consent", "authorization", "order or approval", "filing", "registration", "notification"}).anyOf(),

            // Stockholder approval

            new TextPattern(new String[] {"vote", "holders", "share"}).allPresent(),

    };


    // Name and special keywords that will be used to tag the fragment

    public static final String name = "Approval";
    public static final String searchKeywords[] = new String[] {""};


    public ApprovalExtractor(){

        super();

    }



    public AnalysisOutcome classify(AnalysisFragment fragment){


        AnalysisOutcome outcome = null;

        for(TextPattern p : patterns){

            outcome = new AnalysisOutcome().addDefinition(new FragmentCondition(fragment).contains(p).testClassification(FeatureActionType.CLASSIFY, type, name));

        }

        return outcome;
    }

}
