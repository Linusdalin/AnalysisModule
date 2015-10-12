package extractors.baseExtractors;

import analysis.AnalysisOutcome;
import analysis.FeatureExtractor;
import analysis.FeatureExtractorInterface;
import featureTypes.FeatureTypeTree;
import parse.AnalysisFragment;
import textPatterns.TextPattern;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 *
 *       Extracts a number
 *
 *           (numerical value)
 */

@Deprecated
public abstract class NumberExtractor extends FeatureExtractor implements FeatureExtractorInterface {


    public NumberExtractor(){

        super();
        //type = FeatureType.NUMEX;
        type = FeatureTypeTree.Numex;
        name = "Number";

        // The other rules that the fragment is part of


        //isPartOfList.add(FeatureType.DATE);
        //isPartOfList.add(FeatureType.AMOUNT);
        //isPartOfList.add(FeatureType.PERCENTAGE);
        //isPartOfList.add(FeatureType.LEGAL_ENTITY);

        regExpList = new ArrayList<TextPattern>(){{

            add(new TextPattern("((\\-|)([1-9]\\d{0,2}([\\,|\\´|\\'|\\s]\\d{3})*|([1-9]\\d*))(\\.\\d{1,10})?)").withSignificance( 20 ));  // Setting low significance. We don't really need to display these


            //add(new TextPattern("\\-?([1-9]{1}[0-9]{0,2}(\\,\\d{3})*(\\.\\d{0,2})?|[1-9]{1}\\d{0,}(\\.\\d{0,2})?|0(\\.\\d{0,2})?|(\\.\\d{1,2}))$|^\\-?\\$?([1-9]{1}\\d{0,2}(\\,\\d{3})*(\\.\\d{0,2})?|[1-9]{1}\\d{0,}(\\.\\d{0,2})?|0(\\.\\d{0,2})?|(\\.\\d{1,2}))$|^\\(\\$?([1-9]{1}\\d{0,2}(\\,\\d{3})*(\\.\\d{0,2})?|[1-9]{1}\\d{0,}(\\.\\d{0,2})?|0(\\.\\d{0,2})?|(\\.\\d{1,2}))\\)"));
        }};


    }


    @Override
    public void classify(AnalysisFragment fragment, AnalysisOutcome outcome, FeatureTypeTree tree){

        super.classify(fragment, outcome, tree);

        // Special case. A table heading starting with a number is a chapter.

        if(outcome.getDefinitions().size() > 0 &&
                fragment.getText().equals(fragment.getHeading()) &&
                outcome.getDefinitions().get(0).getPos() == 0){

            System.out.println("Ignoring a number as it is the chapter title");

            outcome.getDefinitions().remove( 0 );

        }


    }




}
