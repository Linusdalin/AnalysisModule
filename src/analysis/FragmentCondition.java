package analysis;

import featureTypes.FeatureTypeInterface;
import parse.AnalysisFragment;
import textPatterns.TextPattern;

import java.util.ArrayList;
import java.util.List;

/**
 *          Created by Linus
 *          A fragment condition is a list of condition added to a fragment to define
 *          which chunks are relevant to extract.
 *
 *          The actual chunks are then retrieved with the extractChunk() method
 */


public class FragmentCondition {


    // The list of conditions that make up the selection
    private List<ConditionType> conditions = new ArrayList<ConditionType>();


    private AnalysisFragment fragment;      // The fragment used
    private String tag;             // Tags for the match


    public FragmentCondition(AnalysisFragment f){

        this.fragment = f;
    }


    public FragmentCondition contains(TextPattern text) {

        conditions.add(new MatchesTextCondition(text));
        return this;
    }


    /***********************************************************************'
     *
     * @return
     *
     *          Side effect: Updates the tag
     *          TODO: This only returns one match. We should be able to get more than one hit
     *
     */

    public FeatureDefinition testClassification(FeatureActionType action, FeatureTypeInterface type, String name) {

        boolean foundMatch = false;
        tag = "";

        for(ConditionType c : conditions){

            if( c instanceof  MatchesTextCondition){

                MatchesTextCondition condition = (MatchesTextCondition)c;

                if(!condition.textPattern.evaluateMeta(fragment)){

                    System.out.println("Failing meta data test for "+ condition.textPattern.display() + "against fragment \"" +
                            (fragment.getText().length() > 30 ? fragment.getText().substring(0, 30) + "..." : fragment.getText()) + "\"" );

                    return null;
                }
                else{

                    //TODO: This is the wrong way. Move match from the textPattern to avoid side effects and variables

                    boolean thisMatches = condition.textPattern.matches(fragment);

                    if(thisMatches){

                        FeatureDefinition outcome = new FeatureDefinition(name, condition.textPattern.getHitType(), condition.textPattern.getExtraction(), new ArrayList<String>(),  action, type);

                        outcome.addTag((tag.length() > 0 ? " " : "") + condition.textPattern.getTag());


                        System.out.println("Now looking up the position for the extraction " + condition.textPattern.getRealExtraction() + "("+ condition.textPattern.getExtraction()+")" + " in " + fragment.getText() );

                        outcome.setPos(fragment.getText().indexOf(condition.textPattern.getRealExtraction()), condition.textPattern.getExtraction().length());

                        return outcome;
                    }
                }
            }
        }

        return null;
    }

}
