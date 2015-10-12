package document;

import log.AnalysisLogger;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;

import java.math.BigInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*************************************************************************************
 *
 *          Handle list and chapter numbering
 *
 *
 *          The representation in the document includes
 *              - Numbering is a sequence in the document
 *              - Abstract numbering is a template for format, start values etc.
 *
 *          A fragment refers to a numbering that refers to a abstractNumbering
 *
 *
 *          The usage of the numberer includes:
 *
 *           - Set up a numberer for the document
 *           - For each fragment do the following:
 *
 *                  - updateListNumbering (increasing the numbering for the chapter)
 *                  - getCurrentListStile (getting the style and format for the list element)
 *
 *
 */
public class ListNumberManager {

    // Limits for static allocations. These should be enough

    private static final int MAX_LEVEL = 30;
    private static final int MAX_NUMBERINGS = 100;

    XWPFNumbering numbering = null;
    private XWPFStyles styles = null;


    ListNumbering currentLevel[] = new ListNumbering[MAX_NUMBERINGS];       // The current list status for all the numbering
    int[][] startValues = new int[MAX_NUMBERINGS][MAX_LEVEL];

    //Extract values

    private XWPFNum fragmentNumbering;
    private String pattern;
    private STNumberFormat.Enum format;
    private boolean overrideBold;
    private int level;

    // The level in the created logical structure

    private int chapterLevel = 0;
    private int listLevel = 0;
    private int pixelIndentation = 0;

    private ListNumbering paragraphNumbering;       // Current numbering for the paragraph


    /*********************************************************
     *
     *
     *          Create a ListNumberManager for the
     *
     * @param document - the document to analyse
     */

    public ListNumberManager(XWPFDocument document){

        numbering = document.getNumbering();
        styles = document.getStyles();

        for(int i = 0; i < MAX_NUMBERINGS; i++)
            currentLevel[i] = new ListNumbering();

        for(int i = 0; i < MAX_NUMBERINGS ; i++)
            startValues[i] = null;

    }


    /***************************************************************
     *
     *          Analyze the paragraph and return the appropriate
     *          Structure type
     *
     *
     * @param paragraph      - current paragraph
     * @return               - type of the paragraph
     */

    public StructureType getStructureType( XWPFParagraph paragraph, String paragraphText) {

        AnalysisLogger.log(AnalysisLogger.Level.DEBUG, "Analysing structure type for " +  paragraph.getStyleID()  );

        if(isWhitespace(paragraph, paragraphText))
            return StructureType.WHITESPACE;

        if(isHeadline( paragraph ))
            return StructureType.HEADING;

        if(isList(paragraph))
            return StructureType.LISTITEM;

        if(isTOC(paragraph))
            return StructureType.TOC;

        if(isHeader(paragraph))
            return StructureType.PAGEHEADER;

        if(isFooter(paragraph))
            return StructureType.PAGEFOOTER;

        AnalysisLogger.log(AnalysisLogger.Level.DEBUG, "Not matching any. Using default style TEXT " );

        return StructureType.TEXT;

    }

    /******************************************************************
     *
     *          Detect empty paragraph to ignore
     *
     *
     * @param paragraph
     * @return
     */


    private boolean isWhitespace(XWPFParagraph paragraph, String paragraphText) {

        return paragraphText.trim().length() == 0;
    }

    /****************************************************************
     *
     *          Detect headlines by looking at the styles.
     *
     * @param paragraph
     * @return
     */


    private boolean isHeadline(XWPFParagraph paragraph) {

        String styleName = paragraph.getStyleID();

        if(styleName == null)
            return false;


        XWPFStyle style = styles.getStyle(styleName);
        String realName = style.getName().toLowerCase();
        return realName.contains("heading") || realName.contains("title");

    }

    private boolean isList(XWPFParagraph paragraph) {

        BigInteger numberingID = paragraph.getNumID();

        // All headings already detected. We assume it is a list

        if(numberingID != null)
            return true;

        // Check if there is a predefined style here

        String styleName = paragraph.getStyleID();
        XWPFStyle style = styles.getStyle(styleName);

        if(style != null){

            String realName = style.getName().toLowerCase();
            System.out.println(" --- Real style name = " + realName);
        }


        return false;

    }

    /**********************************************************'
     *
     *          Detect Table of content
     *
     *
     * @param paragraph
     * @return
     *
     *
     */

    private boolean isTOC(XWPFParagraph paragraph) {

        String styleName = paragraph.getStyleID();

        if(styleName == null)
            return false;


        XWPFStyle baseStyle = styles.getStyle(styleName);
        String baseStyleName = baseStyle.getName().toLowerCase();
        AnalysisLogger.log(AnalysisLogger.Level.DEBUG, " --- Style: " + styleName + " BaseStyle: " + baseStyleName );
        return (baseStyleName.startsWith("toc"));

    }

    //TODO: Improve by Lookup style here

    private boolean isHeader(XWPFParagraph paragraph){

        String name = paragraph.getStyleID();

        if(name == null)
            return false;
        return name.startsWith("Tablehead") || name.startsWith("Tabellsidhuvud");
    }

    //TODO: Improve by Lookup style here

    private boolean isFooter(XWPFParagraph paragraph){

        String name = paragraph.getStyleID();

        if(name == null)
            return false;
        return name.startsWith("Tablefooter") || name.startsWith("Tabellsidfot");
    }



    /**********************************************************************
     *
     *          Update numbering and indentation with
     *          the information from a list paragraph
     *
     *
     *          SideEffect: Setting paragraphNumbering and indentation
     *
     * @param paragraph    - paragraph in the document
     */



    public int updateListNumbering(XWPFParagraph paragraph) {

        BigInteger numberingID = paragraph.getNumID();
        this.level = paragraph.getNumIlvl().intValue();
        this.listLevel = level + 1;
        handleNumber(numberingID, level);

        AnalysisLogger.log(AnalysisLogger.Level.DEBUG, " - Indentation is " + getLogicalLevel() + "( chapter: " + level + " list: " + listLevel + ") and " + pixelIndentation + " pixels");
        return getLogicalLevel();
    }


    /********************************************************************
     *
     *          Update numbering and indentation with
     *          the information from a headline paragraph
     *
     *          SideEffect: Setting paragraphNumbering and indentation
     *
     * @param paragraph     - the analysed paragraph in the document
     * @return
     */

    public int updateHeadingNumbering( XWPFParagraph paragraph ) {

        String styleName = paragraph.getStyleID();

        if(styleName == null) {

            // This is no heading element
            // Keep current numbering status and don't update

            this.paragraphNumbering = null;
            //System.out.println("    !!1 ParagraphNumbering = null");
            return level;
        }


        // First look if there is a numbering override in the paragraph. If that is the case we use it
        // to define the numbering. If not, we use the numbering from the style default

        XWPFStyle style = styles.getStyle(styleName);
        CTPPr stylePPr = style.getCTStyle().getPPr();
        CTPPr paragraphPPr = paragraph.getCTP().getPPr();

        // If there is a basedOn, we use it

        CTNumPr numPr;

        if(paragraphPPr.getNumPr() != null){

            //System.out.println("    !!Using paragraph numPr");
            numPr = paragraphPPr.getNumPr();


        }else{

            if(stylePPr.getNumPr() != null && stylePPr.getNumPr().getNumId() != null){

                //System.out.println("    !!Using Style numPr");
                numPr = stylePPr.getNumPr();

            }else{

                //System.out.println("    !!Using Parent numPr");
                numPr = getDerivedStyle(style);

            }

        }

        /*

        if(stylePPr.getRPr() != null)
            System.out.println("    ! Paragraph Style B is  " + stylePPr.getRPr().getB().getVal().toString());
        else
            System.out.println("    ! Paragraph rPr is null: " + stylePPr.toString());

        */

        if(numPr == null || numPr.getNumId() == null) {

            this.paragraphNumbering = null;
            //System.out.println("    !!2 ParagraphNumbering = null");
            return getLogicalLevel();
        }



        BigInteger numberingID = numPr.getNumId().getVal();
        AnalysisLogger.log(AnalysisLogger.Level.DEBUG, " - - Style = " + style.getName());

        // This is a bit volatile. The level is found in different places

        if(numPr.getIlvl() != null){

            this.level = numPr.getIlvl().getVal().intValue();
            AnalysisLogger.log(AnalysisLogger.Level.DEBUG, " --- Got level "+ this.level+" from Ilvl ");
        }
        else if(stylePPr.getNumPr() != null && stylePPr.getNumPr().getIlvl() != null){

            this.level = stylePPr.getNumPr().getIlvl().getVal().intValue();
            AnalysisLogger.log(AnalysisLogger.Level.DEBUG, " --- Got level "+ this.level+" from Ilvl ");
        }
        else if(stylePPr.getOutlineLvl() != null){

            this.level = stylePPr.getOutlineLvl().getVal().intValue();
            AnalysisLogger.log(AnalysisLogger.Level.DEBUG, " --- Got level "+ this.level+" from outline ");

        }
        else
            this.level = 0;

        this.chapterLevel = level + 1;

        AnalysisLogger.log(AnalysisLogger.Level.DEBUG, " - Updating heading with numbering " + numberingID.intValue() + " on level " + this.level);

        handleNumber(numberingID, level);

        AnalysisLogger.log(AnalysisLogger.Level.DEBUG, " - Indentation is " + getLogicalLevel() + "( chapter: " + level + " list: " + listLevel + ") and " + pixelIndentation + " pixels");
        return getLogicalLevel();
    }

    private CTNumPr getDerivedStyle(XWPFStyle style) {

        if(style == null || style.getBasisStyleID() == null)
            return null;

        XWPFStyle parentStyle = styles.getStyle(style.getBasisStyleID());

        if(parentStyle == null)
            return null;

        if(parentStyle.getCTStyle().getPPr() != null && parentStyle.getCTStyle().getPPr().getNumPr() != null && parentStyle.getCTStyle().getPPr().getNumPr().getNumId() != null)
            return parentStyle.getCTStyle().getPPr().getNumPr();

        return getDerivedStyle(parentStyle);

    }

    /********************************************************************
     *
     *          Update numbering and indentation with
     *          the information from a text paragraph
     *
     *
     *
     * @param paragraph     - the analysed paragraph in the document
     * @return
     */



    public int updateTextIndentation(XWPFParagraph paragraph){

        int oldIndentation = pixelIndentation;
        pixelIndentation = getPixelIndentation( paragraph.getCTP().getPPr() );

        if(listLevel > 0 && pixelIndentation >= oldIndentation){

            // The text is indented. We keep the indentation level
            AnalysisLogger.log(AnalysisLogger.Level.DEBUG, " - text is indented in a list.(pixelIndentation " + pixelIndentation +" >= " + oldIndentation + ") Keeping the level " + listLevel);

        }
        else{

            AnalysisLogger.log(AnalysisLogger.Level.DEBUG, " - Resetting text indentation. (pixelIndentation " + pixelIndentation +" < " + oldIndentation + ")");
            listLevel = 0;

        }

        return getLogicalLevel();
    }

    /************************************************************************
     *
     *
     *          Common functionality given the extracted data from different fragment types
     *
     *
     * @param numberingID   - the numbering from the paragraph or style
     * @param level         - indentation level in the paragraph
     */


    private void handleNumber(BigInteger numberingID, int level){

        if(numberingID == null || numberingID.intValue() == 0) {

            // This is no list element (or a list without numbering.
            // Keep current numbering status and don't update

            this.paragraphNumbering = null;
            //System.out.println("    !!3 ParagraphNumbering = null");

            return;
        }

        fragmentNumbering = numbering.getNum(numberingID);
        int numIDValue = numberingID.intValue();

        BigInteger abstractNumId = fragmentNumbering.getCTNum().getAbstractNumId().getVal();

        AnalysisLogger.log(AnalysisLogger.Level.DEBUG, " *** Calculating numbering details of List paragraph ");
        AnalysisLogger.log(AnalysisLogger.Level.DEBUG, " - It's numbering is " + numIDValue + ", abstract numID is " + abstractNumId.intValue() + " and level = " + level);

        XWPFAbstractNum abstractNumbering = numbering.getAbstractNum(abstractNumId);
        CTLvl[] ctLvlList = abstractNumbering.getCTAbstractNum().getLvlArray();

        if(startValues[abstractNumId.intValue()] == null){

            AnalysisLogger.log(AnalysisLogger.Level.DEBUG, " - No Start Values for abstract numbering " + abstractNumId.intValue());

            // No start vales stored for the abstract numbering. The start values are
            // taken from the document when encountered the first time.

            startValues[abstractNumId.intValue()] = new int[ctLvlList.length];

            for (int i = 0; i < ctLvlList.length; i++) {

                // Get the start value for each level in the abstractNumbering

                int startValue = 1;  // Default is to start from 1
                if(ctLvlList[i] != null && ctLvlList[i].getStart() != null )
                    startValue = ctLvlList[i].getStart().getVal().intValue();

                startValues[abstractNumId.intValue()][i] = startValue;
                //AnalysisLogger.log(AnalysisLogger.Level.DEBUG, "    - Storing start value for level " + i + " to " + startValue);


            }

        }

        AnalysisLogger.log(AnalysisLogger.Level.DEBUG, " ---- NumId: " + numIDValue + " currentLevel[i]" + currentLevel[numIDValue]);

        if(currentLevel[numIDValue].chapterLevels[level] == -1){

            // Not found. Set the start value up to current level. (Higher levels are now implicitly used
            // Start values for lower levels are set when encountered first time.

            currentLevel[numIDValue].setStartValues(startValues[abstractNumId.intValue()], level);
            AnalysisLogger.log(AnalysisLogger.Level.DEBUG, " - Updating Initial Values for numbering " + numIDValue + " up to level " + level);

        }
        else{

            // Found. Updating the value on the current level
            // Reset all numbers on the lower levels.

            currentLevel[numIDValue].chapterLevels[level]++;  //Increment the current level
            AnalysisLogger.log(AnalysisLogger.Level.DEBUG, "Incrementing level " + level + " to " + currentLevel[numIDValue].chapterLevels[level] + " for numbering " + numIDValue);
            currentLevel[numIDValue].resetFrom(level, startValues[abstractNumId.intValue()]);  // Reset the rest
        }
        pixelIndentation = getPixelIndentation(ctLvlList[level].getPPr());
        AnalysisLogger.log(AnalysisLogger.Level.DEBUG, " - Chapter Level is now: " + currentLevel[numIDValue].toString(level));

        // Keep the current paragraph numbering for the rendering

        this.paragraphNumbering = currentLevel[numIDValue];
    }


    /*********************************************************************************'
     *
     *          Extract pixel indentation
     *
     * @param ppr
     */

    private int getPixelIndentation(CTPPr ppr){

        if(ppr == null)
            return 0;

        CTInd ind = ppr.getInd();

        if( ind != null && ind.getLeft() != null)
            return ppr.getInd().getLeft().intValue();
        else
            return 0;


    }






    /*****************************************************************************
     *
     *
     *
     * @param paragraph
     * @param numbering
     */

    void getCurrentListStyle(XWPFParagraph paragraph, XWPFNumbering numbering) {


        if(fragmentNumbering == null) {

            AnalysisLogger.log(AnalysisLogger.Level.DEBUG, "  - Nu numbering. Abort getCurrentListStyle");
            return;
        }

        BigInteger abstractNumId = fragmentNumbering.getCTNum().getAbstractNumId().getVal();


        AnalysisLogger.log(AnalysisLogger.Level.DEBUG, " *** Getting numbering details of List paragraph " + paragraph.getText());
        AnalysisLogger.log(AnalysisLogger.Level.DEBUG, " - It's abstract numID is " + abstractNumId.intValue() + " and level = " + level);

        XWPFAbstractNum currentParagraphAbstractNum = numbering.getAbstractNum(abstractNumId);
        CTAbstractNum currentParagraphAbstractNumFormatting = currentParagraphAbstractNum.getCTAbstractNum();
        CTLvl levelStyle = currentParagraphAbstractNumFormatting.getLvlArray(level);
        String lvlText = extractLevelText(levelStyle.toString());

        //TODO: Handle other styles here too.

        overrideBold = (levelStyle.getRPr() != null && levelStyle.getRPr().getB() != null);

        AnalysisLogger.log(AnalysisLogger.Level.DEBUG, "-- Numbering Format: " + levelStyle.getNumFmt().getVal().toString() + " Pattern:" + lvlText + " Indentation: "
                + levelStyle.getPPr().getInd().getLeft() + "overrideBold: " + overrideBold );
        AnalysisLogger.log(AnalysisLogger.Level.DEBUG, "*****************");

        // Store the current values

        this.pattern =  lvlText;
        this.format = levelStyle.getNumFmt().getVal();
    }


    private String extractLevelText(String s) {

        Pattern pattern = Pattern.compile("<w:lvlText w:val=\"(.*?)\"/>");
        Matcher matcher = pattern.matcher(s);
        if (matcher.find())
            return matcher.group( 1 );
        return "";
    }


    public String renderNumber() {

        String numberString = pattern;

        if(paragraphNumbering == null){

            AnalysisLogger.log(AnalysisLogger.Level.DEBUG, " - No numbering for paragraph. ");
            return "";


        }
        if(pattern.contains("%")){

            AnalysisLogger.log(AnalysisLogger.Level.DEBUG, " - Replacing up to level " + level + " in pattern " + pattern);

            for(int i = 0; i<= level; i++){

                numberString = numberString.replace("%"+(i+1), "" + getSymbolForFormat(format.intValue(), paragraphNumbering.chapterLevels[i]));
            }

            AnalysisLogger.log(AnalysisLogger.Level.DEBUG, " -Result: " + numberString);
            numberString += "&nbsp; ";

        }
        else{
            String symbol = getSymbolForFormat(format.intValue(), paragraphNumbering.chapterLevels[0]);
            numberString = symbol + "&nbsp; ";
            AnalysisLogger.log(AnalysisLogger.Level.DEBUG, " -Setting symbol: " + symbol);

        }

        //TODO: Not implemented: Handle override bold

        return numberString;

    }

    private static final String[] lower_letter_items = {".", "a", "b", "c", "d","e", "f","g", "h","i", "j","k", "l","m", "n","o", "p","q", "r","s", "t", "u"};
    private static final String[] upper_letter_items = {".", "A", "B", "C", "D","E", "F","G", "H","I", "J","K", "L","M", "N","O", "P","Q", "R","S", "T", "U"};
    private static final String[] roman_letter_items = {".", "i", "ii", "iii", "iv","v", "vi","vii", "viii","ix", "x","xi", "xii","xiii", "xiv","xv", "xvi","xvii", "xviii","xix", "xx", "xxi"};


    /*************************************************************************'
     *
     *              Get the appropriate symbol for the list item
     *
     *
     * @param format
     * @param chapterNumber
     * @return
     *
     *              //TODO: display option should dictate if we want to extract different numberings and bullets or use standard
     *
     */

    private String getSymbolForFormat(int format, int chapterNumber) {

        switch(format){

            case STNumberFormat.INT_BULLET:
                return "&mdash;";                   // Using long dash instead of bullet ( &bull; )

            case STNumberFormat.INT_LOWER_LETTER:

                if(chapterNumber > lower_letter_items.length)
                    return "-";
                return lower_letter_items[chapterNumber];

            case STNumberFormat.INT_UPPER_LETTER:

                if(chapterNumber > upper_letter_items.length)
                    return "-";
                return upper_letter_items[chapterNumber];

            case STNumberFormat.INT_UPPER_ROMAN:
            case STNumberFormat.INT_LOWER_ROMAN:

                if(chapterNumber > roman_letter_items.length)
                    return "-";
                return roman_letter_items[chapterNumber];

            case STNumberFormat.INT_ORDINAL:
            case STNumberFormat.INT_ORDINAL_TEXT:
            case STNumberFormat.INT_DECIMAL:
            case STNumberFormat.INT_DECIMAL_ENCLOSED_CIRCLE:
            case STNumberFormat.INT_DECIMAL_ENCLOSED_CIRCLE_CHINESE:
            case STNumberFormat.INT_DECIMAL_ENCLOSED_FULLSTOP:
            case STNumberFormat.INT_DECIMAL_ENCLOSED_PAREN:
            case STNumberFormat.INT_DECIMAL_FULL_WIDTH:
            case STNumberFormat.INT_DECIMAL_FULL_WIDTH_2:
            case STNumberFormat.INT_DECIMAL_HALF_WIDTH:
            case STNumberFormat.INT_DECIMAL_ZERO:

                return "" + chapterNumber;
        }

        return "" + chapterNumber;
    }


    private int getLogicalLevel(){

        return chapterLevel + listLevel;
    }

    /********************************************************************
     *
     *
     *               static final int INT_DECIMAL = 1;
                     static final int INT_UPPER_ROMAN = 2;
                     static final int INT_LOWER_ROMAN = 3;
                     static final int INT_UPPER_LETTER = 4;
                     static final int INT_LOWER_LETTER = 5;
                     static final int INT_ORDINAL = 6;
                     static final int INT_CARDINAL_TEXT = 7;
                     static final int INT_ORDINAL_TEXT = 8;
                     static final int INT_HEX = 9;
                     static final int INT_CHICAGO = 10;
                     static final int INT_IDEOGRAPH_DIGITAL = 11;
                     static final int INT_JAPANESE_COUNTING = 12;
                     static final int INT_AIUEO = 13;
                     static final int INT_IROHA = 14;
                     static final int INT_DECIMAL_FULL_WIDTH = 15;
                     static final int INT_DECIMAL_HALF_WIDTH = 16;
                     static final int INT_JAPANESE_LEGAL = 17;
                     static final int INT_JAPANESE_DIGITAL_TEN_THOUSAND = 18;
                     static final int INT_DECIMAL_ENCLOSED_CIRCLE = 19;
                     static final int INT_DECIMAL_FULL_WIDTH_2 = 20;
                     static final int INT_AIUEO_FULL_WIDTH = 21;
                     static final int INT_IROHA_FULL_WIDTH = 22;
                     static final int INT_DECIMAL_ZERO = 23;
                     static final int INT_BULLET = 24;
                     static final int INT_GANADA = 25;
                     static final int INT_CHOSUNG = 26;
                     static final int INT_DECIMAL_ENCLOSED_FULLSTOP = 27;
                     static final int INT_DECIMAL_ENCLOSED_PAREN = 28;
                     static final int INT_DECIMAL_ENCLOSED_CIRCLE_CHINESE = 29;
                     static final int INT_IDEOGRAPH_ENCLOSED_CIRCLE = 30;
                     static final int INT_IDEOGRAPH_TRADITIONAL = 31;
                     static final int INT_IDEOGRAPH_ZODIAC = 32;
                     static final int INT_IDEOGRAPH_ZODIAC_TRADITIONAL = 33;
                     static final int INT_TAIWANESE_COUNTING = 34;
                     static final int INT_IDEOGRAPH_LEGAL_TRADITIONAL = 35;
                     static final int INT_TAIWANESE_COUNTING_THOUSAND = 36;
                     static final int INT_TAIWANESE_DIGITAL = 37;
                     static final int INT_CHINESE_COUNTING = 38;
                     static final int INT_CHINESE_LEGAL_SIMPLIFIED = 39;
                     static final int INT_CHINESE_COUNTING_THOUSAND = 40;
                     static final int INT_KOREAN_DIGITAL = 41;
                     static final int INT_KOREAN_COUNTING = 42;
                     static final int INT_KOREAN_LEGAL = 43;
                     static final int INT_KOREAN_DIGITAL_2 = 44;
                     static final int INT_VIETNAMESE_COUNTING = 45;
                     static final int INT_RUSSIAN_LOWER = 46;
                     static final int INT_RUSSIAN_UPPER = 47;
                     static final int INT_NONE = 48;
                     static final int INT_NUMBER_IN_DASH = 49;
                     static final int INT_HEBREW_1 = 50;
                     static final int INT_HEBREW_2 = 51;
                     static final int INT_ARABIC_ALPHA = 52;
                     static final int INT_ARABIC_ABJAD = 53;
                     static final int INT_HINDI_VOWELS = 54;
                     static final int INT_HINDI_CONSONANTS = 55;
                     static final int INT_HINDI_NUMBERS = 56;
                     static final int INT_HINDI_COUNTING = 57;
                     static final int INT_THAI_LETTERS = 58;
                     static final int INT_THAI_NUMBERS = 59;
                     static final int INT_THAI_COUNTING = 60;

     */
}
