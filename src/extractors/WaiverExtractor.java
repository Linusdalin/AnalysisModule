package extractors;

import analysis.*;
import parse.AnalysisFragment;
import textPatterns.TextPattern;

/**
 * Created with IntelliJ IDEA.
 * User: Ulf Änggård
 *
 "The No Waiver or Waiver clause in the general provisions section of an agreement aims to ensure that a party's failure to enforce its contractual rights, whether intentionally or by oversight, does not result in a waiver of those rights or remedies for their breach.

 A party may decide not to strictly enforce the provisions of the contract on a particular occasion or under certain circumstances. It might, for
 example, accept late payments without insisting on an agreed-upon penalty, or allow the other party to send goods of a different quality on
 occasion. The no waiver clause provides that a party does not waive its rights to insist upon strict compliance with terms of the contract
 in the future simply because it has deviated from the enforcement of those terms in the past."

 *Standard:
 *
 * Waiver.
 *
 *A party's failure to exercise or delay in exercising any right, power or privilege under this Agreement shall not operate as a waiver;
 * nor shall any single or partial exercise of any right, power or privilege preclude any other or further exercise thereof.  *
 *
 * Alternatives:
 *
 *1.1 Narrow Clause

 Waiver. The waiver by the Employer of a breach of any provision of this Agreement by Employee will not operate or be construed as a waiver
 of any other subsequent breach by Employee.

 1.2 Broad Clause

 No General Waivers. The failure of any party at any time to require performance of any provision or to resort to any remedy provided
 under this Agreement shall in no way affect the right of that party to require performance or to resort to a remedy at any time thereafter,
 nor shall the waiver by any party of a breach be deemed to be a waiver of any subsequent breach. A waiver shall not be effective unless it
 is in writing and signed by the party against whom the waiver is being enforced.
 *
 * Additional Elements:
 *
 *
 * 2.1 Course of Dealing

 No course of dealing, nor any failure to exercise, nor any delay in exercising any right, power or privilege hereunder shall operate as a waiver thereof.

 2.2 Writing Requirement

 A party's waiver of any breach, default or right under this Agreement must be in writing and signed by the party against whom the waiver is being enforced. Any such waiver shall not be deemed a waiver of any subsequent breach, default or right, whether of the same nature or otherwise. [57. No Caption (Doc. 16)]

 OR

 The waiver by either party of a breach of any provision of this Agreement must be in writing and shall not operate or be construed as a waiver of any other breach.

 2.3 Cumulative Remedies

 The rights and remedies under this Agreement are cumulative and not exclusive of any rights, remedies, powers and privileges that may otherwise be available to the parties.
 *
 */

public class WaiverExtractor extends FeatureExtractor implements FeatureExtractorInterface {

    // Patterns to look for. Extensive use of skipgrams close() is very good

    //TODO: If statement that might apply different patterns depending on ProjectType. Added methods in FeatureExplanation class.

      private static final TextPattern[] patterns = {

              new TextPattern(new String[]{"right", "waiver"}).allPresent()
                .and(new TextPattern(new String[]{"no", "not", "nor"}).mostOf()),
              new TextPattern(new String[]{"breach", "waiver"}).close(),
              new TextPattern(new String[]{"remedies", "cumulative"}).allPresent()
      };


    // Name and special keywords that will be used to tag the fragment

    public static final String name = "Waiver";
    public static final String searchKeywords[] = new String[] {""};


    public WaiverExtractor(){

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
