package analysis2;

import log.AnalysisLogger;
import openNLP.NLParser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-11-04
 * Time: 09:12
 * To change this template use File | Settings | File Templates.
 */
public class TokenReplacer extends Replacer implements ReplacerInterface {

    public static final int NO_EXTRACTION = 0;
    public static final int WORD_SPAN = -1;
    public static final int SENTENCE_SPAN = -2;

    private int span = NO_EXTRACTION;
    private int semanticSpan = NO_EXTRACTION;



    @Override
    public ReplacerInterface withExtraction(int span) {

        this.span = span;
        return this;
    }

    @Override
    public ReplacerInterface withSemanticExtraction(int span) {

        this.semanticSpan = span;
        return this;
    }

    @Override
    public ReplacerInterface withSemanticExtraction(String extraction) {

        // TODO: Not implemented
        return this;
    }

    @Override
    public ReplacerInterface withGroupRestrictionCriteria(int group, SemanticRestriction.RestrictionType restriction) {

        // TODO: Not implemented
        return this;
    }

    @Override
    public int getMainExtractionSpan() {

        // Default is to extract span 1 for a token replacer

        return 1;
    }

    @Override
    public int getSemanticExtractionSpan() {

        // Default is to extract span 1 for a token replacer
        return 1;
    }

    @Override
    public boolean evaluateExtractionRestrictions(List<TextSpan> newNode, NLParser spans) {
        return true;  // Not implemented
    }


    @Override
    public List<TextSpan> extractSpan(ParseNodeInterface originalSentence) {

        List<TextSpan> span = new ArrayList<>();

        for(Criteria criterium : criteria){

            if(criterium.matchMeta(originalSentence)){

                TextSpan hit = criterium.match(originalSentence, 0, semanticSpan);

                while(hit != null){

                    //System.out.println(" !!!! found match " + hit.getText());
                    span.add(hit);

                    int rest = getRest(originalSentence, hit);
                    if(rest < originalSentence.getChildren().size()){

                        AnalysisLogger.log(AnalysisLogger.Level.DEBUG," ---- Got rest " + rest + ". Trying to match again!");
                        hit = criterium.match(originalSentence, rest + 1, semanticSpan);
                    }else
                        hit = null;
                }

            }
            else
                AnalysisLogger.log(AnalysisLogger.Level.DEBUG, "Failed to match meta criterium");

        }

        return span;
    }

    private int getRest(ParseNodeInterface originalSentence, TextSpan hit) {

        int tokenNo = 0;

        for (ParseNodeInterface token : originalSentence.getChildren()) {

            if(token.getTextPosition() > hit.getStartingPoint() + hit.getText().length())

                return tokenNo;

            tokenNo++;
        }

        return 99999;  //No rest
    }


}
