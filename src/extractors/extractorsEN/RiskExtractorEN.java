package extractors.extractorsEN;

import analysis.FeatureActionType;
import analysis.FeatureExtractorInterface;
import extractors.baseExtractors.RiskExtractor;
import textPatterns.TextPattern;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 *
 *       Extracts all fragments that are related to risk by looking for payment related words and combinations
 *
 *
 * //TODO: Risk extractor has to be elaborated. Too weak...
 */

@Deprecated
public class RiskExtractorEN extends RiskExtractor implements FeatureExtractorInterface {


    public RiskExtractorEN(){

        super();

        patterns = new ArrayList<TextPattern>(){{

                add(new TextPattern(new String[] {"[P|p]enalt[y|i]"}).extractWordSpan().withTag("Penalty Risk"));
                add(new TextPattern(new String[] {"dedicated", "(?:team|group|resources)" }).veryClose().extractWordSpan().withTag("Exclusivity"));


        }};

        name = "Potential Risk";
        searchKeywords = new String[] {"Warning"};
        actionType = FeatureActionType.CREATE_RISK;


    }


}
