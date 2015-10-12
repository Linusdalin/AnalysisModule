package classifiers.baseClassifiers;

import analysis2.*;
import classifiers.Classifier;
import classifiers.ClassifierInterface;
import featureTypes.FeatureTypeTree;

/**
 *          Language generic Definitions
 *
 *
 */



public abstract class DefinitionSourceClassifier extends Classifier implements ClassifierInterface {


    private static final ReplacerInterface[] RuleList = {


            new RegExpReplacer("^\\s*([A-Z].*?)\\t+(.*)$")
                    .withGroupRestrictionCriteria(1, SemanticRestriction.RestrictionType.IS_NAME_ENTITY)
                    .withGroupRestrictionCriteria(2, SemanticRestriction.RestrictionType.IS_DESCRIBING)
                    .withExtraction( 1 ),

            new RegExpReplacer("^\\s*\"([A-Z].*?)\"\\s{3,}\\w+")
                .withExtraction( 1 ),

            new RegExpReplacer("^\\s*\"([A-Z].*)\"\\s*(.*)$")
                    .withGroupRestrictionCriteria(1, SemanticRestriction.RestrictionType.IS_NAME_ENTITY)
                    .withGroupRestrictionCriteria(2, SemanticRestriction.RestrictionType.IS_DESCRIBING)
                    .withExtraction( 1 ),


            new RegExpReplacer("^\\s*\"([A-Z].*)\"\\s*$")
                    .withCriteria(new Criteria().meta(new MetaMatchPattern(MetaMatchPattern.MetaType.LEFT_COLUMN_OF_2)))                              // ex: "Agreement"    in the left column
                    .withTag("LeftColumn"),


            // Anything in the left column that starts with a character

            new RegExpReplacer("^([A-Za-z].*)$")
                    .withGroupRestrictionCriteria(1, SemanticRestriction.RestrictionType.IS_NAME_ENTITY)
                    .withCriteria(new Criteria()
                            .meta(new MetaMatchPattern(MetaMatchPattern.MetaType.LEFT_COLUMN_OF_2)))
                    .verbose()
                    .withTag("LeftColumn")
            ,


            //new RegExpReplacer("^\\s*([A-Z].*?)\\:.{3,}")
            //        .withExtraction( 1 ),



    };


    public DefinitionSourceClassifier(ReplacerInterface[] languageSpecific){

        super(RuleList, languageSpecific, NodeClass.Type.DEFINITION, FeatureTypeTree.DEFINITION);
    }



}
