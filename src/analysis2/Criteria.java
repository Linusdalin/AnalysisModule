package analysis2;

import log.AnalysisLogger;
import org.apache.commons.lang.StringUtils;
import parse.POSClassification;

import java.util.ArrayList;
import java.util.List;

/*****************************************************************************************''
 *
 *          Defining a criteria for a classification
 *
 *          This could be a sequence of words or nodes with a vicinity
 *
 *          A classifier is made up by a list of these criteria
 *
 */


public class Criteria {

    Vicinity vicinity = Vicinity.NONE;
    List<TokenMatchPattern> tokenList = new ArrayList<>();
    List<MetaMatchPattern> metaTokenList = new ArrayList<>();

    public Criteria(){

    }


    public Criteria adjacent() {

        vicinity = Vicinity.ADJACENT;
        return this;
    }

    public Criteria anyOf() {

        vicinity = Vicinity.ANY_OF;
        return this;
    }


    public Criteria close() {

        vicinity = Vicinity.CLOSE;
        return this;
    }

    public Criteria prettyClose() {

        vicinity = Vicinity.PRETTY_CLOSE;
        return this;
    }

    public Criteria veryClose() {

        vicinity = Vicinity.VERY_CLOSE;
        return this;
    }

    public Criteria allOf() {

        vicinity = Vicinity.ALL_PRESENT;
        return this;
    }


    public Criteria anyWord(POSClassification posClassification) {

        tokenList.add(new TokenMatchPattern(posClassification.name() , TokenMatchPattern.TokenType.WORDCLASS));
        return this;
    }

    public Criteria meta(MetaMatchPattern metaPattern) {

        metaTokenList.add(metaPattern);
        return this;
    }



    /***************************************************
     *
     *          Match any of the words in the wordlist (with endings)
     *
     * @param wordList
     * @return
     */


    public Criteria stemmedPattern(String wordList) {

        tokenList.add(new TokenMatchPattern(wordList, TokenMatchPattern.TokenType.STEMMED_REGEX));
        return this;
    }


    public Criteria word(String word) {

        tokenList.add(new TokenMatchPattern(word, TokenMatchPattern.TokenType.ANYWORD));
        return this;
    }

    public Criteria node(NodeClass.Type nodeType) {

        return node(new NodeClass(nodeType));

    }

    public Criteria node(NodeClass nodeClass) {

        tokenList.add(new TokenMatchPattern(nodeClass));
        return this;
    }



    public Criteria subordinateClause() {

        tokenList.add(new TokenMatchPattern("subordinate clause", TokenMatchPattern.TokenType.DISTANCE));
        return this;
    }


    public Criteria node(NodeClass.Type nodeType, String semanticExtractionPattern) {

        tokenList.add(new TokenMatchPattern(nodeType.name(), TokenMatchPattern.TokenType.TAG)
                .withSemanticRestriction(semanticExtractionPattern));
        return this;
    }


    public Criteria pattern(String regex) {

        tokenList.add(new TokenMatchPattern(regex, TokenMatchPattern.TokenType.REGEX));
        return this;
    }


    public boolean matchMeta(ParseNodeInterface sentence) {

        return matchMeta(sentence, metaTokenList);
    }

    /**************************************************************'
     *
     *          match the sentence against the token list
     *
     * @param sentence
     * @param semanticSpan     - extract one of the tokens as a semantic span
     * @return
     */


    public TextSpan match(ParseNodeInterface sentence, int startToken, int semanticSpan){

        boolean metaMatch = matchMeta(sentence, metaTokenList);

        if(!metaMatch){

            return null;
        }

        AnalysisLogger.log(AnalysisLogger.Level.DEBUG, " *** Passing meta Evaluating criterium " + vicinity.name() + " " + tokenList);


        switch(vicinity){

            case NONE:

                return new TextSpan("", 0, 0, "");

            case ADJACENT:

                return matchDistance(sentence, tokenList, startToken,  0, semanticSpan);

            case VERY_CLOSE:

                return matchDistance(sentence, tokenList, startToken, 20, semanticSpan);

            case CLOSE:

                return matchDistance(sentence, tokenList, startToken, 90, semanticSpan);

            case PRETTY_CLOSE:

                return matchDistance(sentence, tokenList, startToken, 170, semanticSpan);


            case ALL_PRESENT:

                return matchDistance(sentence, tokenList, startToken, 1000000, semanticSpan);

            case ANY_OF:

                return matchSome(sentence, tokenList, startToken, 1, semanticSpan);

            default:
                AnalysisLogger.log(AnalysisLogger.Level.WARNING, "Vicinity " + vicinity.name() + " not implemented");
                return null;
        }



    }


    /***************************************************************************
     *
     *          Match some will count the number of hits. Extract the pattern over all possible hits
     *          and return true if it is above the
     *
     *
     *
     *
     * @param sentence      - the sentence or structure to match
     * @param tokenList     - tokens to match in the criterium
     * @param hitThreshold  - number of hits required for it to be a match
     * @param semanticSpan
     * @return              - text span from the first to the last word
     *
     *
     *          //TODO: Verify that hit threshold is not 0 here
     *          //TODO: Loop from StartToken
     *          //TODO: Handle semanticSpan
     *
     */

    private TextSpan matchSome(ParseNodeInterface sentence, List<TokenMatchPattern> tokenList, int startToken, int hitThreshold, int semanticSpan) {

        int hitCount = 0;
        StringBuilder extractPattern = new StringBuilder();
        int extractPosition = -1;

        for(int i = startToken; i < sentence.getChildren().size(); i++ ){

        ParseNodeInterface node = sentence.getChildren().get( i );

            for(TokenMatchPattern matchPattern : tokenList){

                if(matchPattern.matches(node)){

                    hitCount++;

                    if(hitCount == 1){
                        extractPosition = node.getTextPosition();  // The first hit. Start extraction from here
                        extractPattern.append(node.getText());

                    }
                }
                if(hitCount > 1){

                    extractPattern.append(" ").append(node.getText());

                }

            }

        }

        if(hitCount >= hitThreshold){

            return new TextSpan(extractPattern.toString(), extractPosition, 1, extractPattern.toString());

        }
        else{

            return null;
        }

    }

    /************************************************************************
     *
     *          matching meta data is only about qualifying the hit, it will
     *          not make any extractions. (Extractions make little sense
     *          anyway as they are primarily the base for highlighting.
     *
     *
     *
     * @param node
     * @param metaTokenList
     * @return
     */


    public boolean matchMeta(ParseNodeInterface node, List<MetaMatchPattern> metaTokenList) {

        boolean matching = true;

        if(metaTokenList.size() > 0)
            AnalysisLogger.log(AnalysisLogger.Level.DEBUG,"*** Checking "+ metaTokenList.size() +" meta criteria for node " + node.getText());

        for(MetaMatchPattern metaMatch : metaTokenList){

            boolean isMatch = metaMatch.matches(node);
            AnalysisLogger.log(AnalysisLogger.Level.DEBUG,"*** Matching "+ metaMatch.type.name()+": "+ isMatch);
            matching = matching && isMatch;

        }

        return matching;

    }

    /**********************************************************************************'
     *
     *              Match if the distance between two words are below a threshold
     *
     *
     *
     * @param sentence              - sentence
     * @param tokenList             - list of tokens to match
     * @param startToken            - Where in the sentence to start look. This is to find multiple occurrences
     * @param maxDistance           - the maximum allowed distance
     * @param semanticSpan          - which word to extract as semantic value
     * @return
     *
     *              The matching is done by looping through the sentence while matching the tokens in order
     *              and keeping track of the accumulated distance.
     *
     *              All tokens are expected to be found, and in-between, any miss is adding to the distance
     *
     *              Once the threshold is exceeded, the count is reset and we restart from the first token
     *
     *              //TODO: Improvement: Implement ANY_ORDER
     *              //TODO: Improvement: Implement SOME_OF and MOST_OF
     *              //TODO: Improvement  Implement OPTIONAL in-between words, that are not required, but are not distance
     *
     *
     *
     */

    private TextSpan matchDistance(ParseNodeInterface sentence, List<TokenMatchPattern> tokenList, int startToken, int maxDistance, int semanticSpan) {

        int matchToken = 0;
        StringBuilder extractPattern = new StringBuilder();
        String semanticExtraction = "";
        int extractPosition = -1;
        int distance = -1;

        //System.out.println("Sentence= "+ sentence.getText() + " tokens: " + sentence.getChildren().size());

        if(tokenList.size() > 2)
            maxDistance += WordNode.DISTANCE_FOR_MINOR * tokenList.size()-2;  // Modification for long lists. Allow some minor words

        int currentDistance = maxDistance;  // The active distance we are checking against may vary with match tokens

        StringBuffer inBetween = new StringBuffer();  // To store minor words inBetween (for close) that do not match but should go in the pattern

        //for( ParseNodeInterface node : sentence.getChildren()){
        for(int tokenNumber = startToken; tokenNumber < sentence.getChildren().size(); tokenNumber++){

            ParseNodeInterface node = sentence.getChildren().get(tokenNumber);
            TokenMatchPattern matchPattern = tokenList.get(matchToken);

            if(matchToken > 0 && !matchPattern.matches(node) ){

                // If we are looking for tokens but misses, we should increase distance and possibly restart

                int additionalDistance = getDistanceForWords(node);
                distance += additionalDistance;

                if(distance > currentDistance){

                        // Restart the count.

                    matchToken = 0;
                    distance = 0;
                    extractPattern = new StringBuilder();
                    extractPosition = -1;
                    inBetween = new StringBuffer();
                }
                else{

                    // Store the word inbetween. We are still within distance for matching and then we want the inbetween words too
                    //TODO: If there are no space inbetween the tokens this will fail the replace

                    int position = node.getTextPosition();
                    String nodeText = node.getText();

                    inBetween.append(" ").append(node.getText());
                }

            }


            if(tokenList.get(matchToken).matches(node)){

                // We found the active token in the text

                AnalysisLogger.log(AnalysisLogger.Level.DEBUG, "Matched token " + matchToken);


                if(matchToken == 0){

                    // First word

                    extractPosition = node.getTextPosition();
                    extractPattern.append(node.getText());
                    distance = 0;
                    semanticExtraction = "";
                }
                else{

                    if(inBetween.length() > 0){

                        extractPattern.append(inBetween).append(" ");
                        inBetween = new StringBuffer();
                    }

                    String nodeText = node.getText();
                    int nodePosition = node.getTextPosition();

                    padAndAppend(extractPattern, extractPosition, node);


                }

                if(matchToken == semanticSpan - 1){

                    // We here found the word we want to extract as the semantic meaning
                    AnalysisLogger.log(AnalysisLogger.Level.DEBUG, "  --- Extracting semantic ");
                    semanticExtraction = node.getText();

                }


                matchToken++;
                if(matchToken < tokenList.size() && tokenList.get(matchToken).type == TokenMatchPattern.TokenType.DISTANCE ){

                    // A match pattern DISTANCE means that we will match pretty much anything before the next token

                    System.out.println("  --- We found a match token DISTANCE. Increasing match to ANY_OF ");

                    currentDistance = 10000;
                    matchToken++;
                }else
                    currentDistance = maxDistance; // Restore with original value

            }
            //else
            //    AnalysisLogger.log(AnalysisLogger.Level.DEBUG, "Not matching token " + matchToken );


            if(matchToken == tokenList.size())
                return new TextSpan(extractPattern.toString(), extractPosition, 1, semanticExtraction) ;

        }

        return null;

    }

    /********************************************************************'''''
     *
     *          The token does not contain the whitespace between. However by looking at the positions we
     *          can detect how many characters we need.
     *
     *          This is important for the later replacement
     *
     * @param extractPattern
     * @param extractPosition
     * @param node
     */

    private void padAndAppend(StringBuilder extractPattern, int extractPosition, ParseNodeInterface node) {

        int whitespaces = node.getTextPosition() - extractPosition - extractPattern.length();

        AnalysisLogger.log(AnalysisLogger.Level.DEBUG, "Appending " + node.getText() + " to extract pattern. Adding " + whitespaces + " whitespaces");

        extractPattern.append(StringUtils.leftPad(node.getText(), node.getTextLength() + whitespaces));

    }

    private int getDistanceForWords(ParseNodeInterface node) {

        int distance = 0;

        if(node instanceof WordNode)
            return ((WordNode)node).getDistanceValue();

        for(ParseNodeInterface child : node.getChildren()){

            distance += getDistanceForWords(child);
        }

        return distance;

    }

}
