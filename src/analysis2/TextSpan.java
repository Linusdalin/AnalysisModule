package analysis2;

/***********************************************************************************''
 *
 *              The text span is the extracted result from a classification
 *
 *              It points out the text that is matched, where it is found
 *              and the optional semantic extraction
 */


public class TextSpan {


    private final String text;                      // The extracted text
    private final int startingPoint;                // Where in the fragment this starts
    private int extractionGroup;                    // The group in the regex the extraction comes from
    private String semanticExtraction;              // The semantic extraction


    public TextSpan(String text, int startingPoint, int extractionGroup, String semanticExtraction){

        this.text = text;
        this.startingPoint = startingPoint;
        this.extractionGroup = extractionGroup;
        this.semanticExtraction = semanticExtraction;
    }

    public String getText() {
        return text;
    }

    public int getStartingPoint() {
        return startingPoint;
    }


    public int getExtractionGroup() {
        return extractionGroup;
    }

    public String getSemanticExtraction() {
        return semanticExtraction;
    }
}
