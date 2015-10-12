package extractors;

import analysis.*;
import parse.AnalysisFragment;
import textPatterns.TextPattern;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 *
 The books and records clause states that the representing party has made its records available and that all such records and accurate, complete and maintained in accordance with standard business practices. The clause is typically found in the acquisition of private companies and may be more or less detailed and onerous depending on the circumstances.

 The language of the representation is highly consistent and provides that: (a) the records are compete and correct; (b) have been maintained in accordance with reasonable business practices; and (c) are stated in reasonable detail and accurately and fairly reflect the transactions and dispositions. The first two elements are standard, the third appears less frequently.

 The scope of the clause varies significantly. It may be limited to minute books or may be applied to all books of account, stock records, minute books and other corporate and financial records.

 *      Example:
 *
 *
 *
 * Books and Records.
 *
 *      The minute books, books of account, stock records, and other corporate and financial records of the Seller
 *      [all of which have been made available to the Buyers] are [to the knowledge of Seller] complete and correct
 *      [in all material respects], have been maintained in accordance with reasonable business practices for companies
 *      similar to the Seller, [and are stated in reasonable detail and accurately and fairly reflect the transactions and dispositions].  *
 *
 */

public class BooksExtractor extends FeatureExtractor implements FeatureExtractorInterface {

    // Patterns to look for

    private static final TextPattern[] patterns = {


            new TextPattern(new String[] {"minute books", "books of account", "records"}).someOf()
                    .and(new TextPattern(new String[]{"complete", "correct", "maintained"}).someOf()),

    };


    // Name and special keywords that will be used to tag the fragment

    public static final String name = "Books";
    public static final String searchKeywords[] = new String[] {"records"};


    public BooksExtractor(){

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
