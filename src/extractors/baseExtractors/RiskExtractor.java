package extractors.baseExtractors;

import analysis.*;
import featureTypes.FeatureTypeTree;
import parse.AnalysisFragment;
import parse.POSClassification;
import parse.Word;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 *
 *
 */

@Deprecated
public abstract class RiskExtractor extends FeatureExtractor implements FeatureExtractorInterface {


    public RiskExtractor(){

        //type = FeatureType.RISK;
        type = FeatureTypeTree.Risk;

        actionType = FeatureActionType.CLASSIFY;

    }

    @Override
    public void classify(AnalysisFragment fragment, AnalysisOutcome outcome, FeatureTypeTree tree){

        super.classify(fragment, outcome, tree);

        for(Word word : fragment.getWords()){

            if(word.getPos() == POSClassification.JJS){

                // Identifying a risk will create both the risk and the classificaton

                FeatureDefinition definition = new FeatureDefinition(name, FeatureHit.CONTAINS, word.getText(), new ArrayList<String>(), FeatureActionType.CREATE_RISK, FeatureTypeTree.Risk);
                definition.addTag("Scope");
                outcome.addDefinition(definition);

            }
        }

    }

}
