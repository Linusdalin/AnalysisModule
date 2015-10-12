package document;

import log.AnalysisLogger;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.xwpf.usermodel.VerticalAlign;
import org.apache.poi.xwpf.usermodel.XWPFHyperlinkRun;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlObject;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl;

import java.util.ArrayList;
import java.util.List;

/**
 *
 *              Handle runs in a paragraph
 *
 */


public class RunManager {


    private XWPFParagraph p;
    private CTPPr paragraphProvider;
    private CTInd indentationObject = null;
    private static final int CHAR_PER_TAB = 16;   // Estimated number
    private static final int PIXELS_PER_TAB = 105;   // html span

    private int boldCount = 0;
    private int length = 0;


    public static final String MARKUP_TOKEN= "$$(_M4rkUp)!";

    RunManager(XWPFParagraph p){

        this.p = p;
        paragraphProvider = p.getCTP().getPPr();

        if(paragraphProvider != null){

            indentationObject = paragraphProvider.getInd();

            if(indentationObject != null){
                if(indentationObject.getHanging()!= null)
                    AnalysisLogger.log(AnalysisLogger.Level.DEBUG, "  *** Hanging Paragraph. hang: " + indentationObject.getHanging());
                if(indentationObject.getLeft()!= null)
                    AnalysisLogger.log(AnalysisLogger.Level.DEBUG, "  *** Indented Paragraph.  ind: " + indentationObject.getLeft() + "(first:" + indentationObject.getFirstLine() + ")");

            }

        }
    }


    public String convertToText() {

        return convertToText( null );
    }


    /**************************************************************************************
     *
     *          Method for extracting text from a paragraph
     *
     *          This includes going through the runs and extracting:
     *           - bold
     *           - italics
     *           - strikethrough
     *
     *          The rules is that the text must be at least three characters and at most three words.
     *
     *
     * @param keywords      - keyword list to store highlighted words
     * @return              - complete text version of the paragraph
     */



    public String convertToText(List<String> keywords) {

        int tabCount = 0;
        int displayedTabs = 0;
        int textWidth = 0;


        StringBuffer paragraphText = new StringBuffer();

        for(XWPFRun run : p.getRuns()){



            // String text = run.toString();
            String text = strip(run.getText(0));

            if(text.startsWith(MARKUP_TOKEN)){
                AnalysisLogger.log(AnalysisLogger.Level.DEBUG, "  --- Ignoring markup token");
                continue;
            }

            length = text.length();

            int wordCount = StringUtils.countMatches(text, " ");

            int length = text.length();

            if(run.isBold()){

                boldCount += text.length();     // Counting bold for implicit headline;
                text = "<b>" + text + "</b>";

            }
            if(run.isItalic())
                text = "<i>" + text + "</i>";
            if(run.isStrike())
                text = "<strike>" + text + "</strike>";

            //System.out.println("GetSub" + run.getSubscript().name());

            if(run.getSubscript() == VerticalAlign.SUPERSCRIPT)
                text = "<sup>" + text + "</sup>";

            if(run.getSubscript() == VerticalAlign.SUBSCRIPT)
                text = "<sub>" + text + "</sub>";


            if(run.isBold() || run.isItalic() || run.isStrike() ){

                if(keywords != null && length > 2 && wordCount < 4)
                    keywords.add(text);

            }


            if(hasTab(run))
                tabCount++;

            if(text != null && text.length() > 0){

                String tabMarker = addTab(tabCount, displayedTabs, textWidth);
                if(tabMarker != null ){

                    paragraphText.append(tabMarker);
                    displayedTabs = tabCount;
                    textWidth = 0;
                }
                paragraphText.append(text);
                textWidth += text.length();

            }
            AnalysisLogger.log(AnalysisLogger.Level.DEBUG, "Run: \"" + text + "\" TabCount: " + tabCount + " Textwidth: " + textWidth);

        }


        //System.out.println("Found text for paragraph: " + paragraphText.toString());
        return paragraphText.toString();
    }

    /***************************************************************'
     *
     *          Strip will escape all HTML tags and create an empty string for the null string
     *
     * @param text         - the original run text
     * @return             - escaped text
     *
     *
     *          NOTE: Remember the order. &amp would break the other escapes
     */

    private String strip(String text) {

        if(text == null)
            return "";

        text = text.replaceAll("&", "&amp;");
        text = text.replaceAll("<", "&lt;");
        text = text.replaceAll(">", "&gt;");

        return text;

    }

    private boolean isSuperScript(XWPFRun run) {

        if(run.getCTR() == null)
            return false;

        if(run.getCTR().getRPr() == null)
            return false;

        if(run.getCTR().getRPr().getVertAlign() == null)
            return false;

        System.out.println("VertAlign:" + run.getCTR().getRPr().getVertAlign().getVal());

        return false;

    }

    private boolean isSubScript(XWPFRun run) {

        if(run.getCTR() == null)
            return false;

        if(run.getCTR().getRPr() == null)
            return false;

        if(run.getCTR().getRPr().getVertAlign() == null)
            return false;

        if(run.getCTR().getRPr().getVertAlign().getVal().toString().equals("superscript")){

            AnalysisLogger.log(AnalysisLogger.Level.DEBUG, " Found superscript");
            return true;
        }

        return false;

    }


    private String addTab(int tabCount, int displayedTabs, int textWidth) {

        if(tabCount - displayedTabs == 0)
            return null;

        int tabLevel = tabCount + (textWidth / CHAR_PER_TAB);
        return("\t{"+ (tabLevel * PIXELS_PER_TAB) +"}");
    }

    private boolean hasTab(XWPFRun run) {
        //System.out.println("Istab:" + (run.getCTR().getTabArray().length > 0) );
        return run.getCTR().getTabArray().length > 0;
    }

    private boolean hasText(XWPFRun run) {
        //System.out.println("Hastext:" + (run.getText( 0 ) != null  ));
        return run.getText( 0 ) != null;


    }

    /***********************************************************************************
     *
     *          An implicit headline is something that looks like a headline to the user
     *
     *          //TODO: Implicit Headline for Structure is not implemented
     *          //TODO: All caps should be an implicit headline
     *          //TODO: Font size for implicit headline?
     *
     *
     * @return
     *
     *          The focus is on bold text.
     */

    public boolean isImplicitHeadline() {

        if(10 * boldCount > length * 8){

            return true;
        }

        return false;

    }


    /****************************************************************************
     *
     *          Overriding the Apache run extraction to get the markups too.
     *
     *
     * @param p       - paragraph
     * @return        - the list of runs (with markup added)
     */

    public List<XWPFRun> getRunsForParagraph(XWPFParagraph p){

        List<XWPFRun> runs = new ArrayList<XWPFRun>();

        CTP ctp = p.getCTP();

         // Get all our child nodes in order, and process them
         //  into XWPFRuns where we can

         XmlCursor c = ctp.newCursor();
         c.selectPath("child::*");
         while (c.toNextSelection()) {

            XmlObject o = c.getObject();

             if(o instanceof CTR) {
               runs.add(new XWPFRun((CTR)o, p));
            }

             if(o instanceof CTMarkupRange){

                 CTMarkupRange markupRange = (CTMarkupRange)o;
                 System.out.println(" !!! Found Markup range!! Adding a markup here");
                 XWPFRun run = p.createRun();

                 run.setText(MARKUP_TOKEN+ markupRange.getId().intValue());
                 runs.add(run);
             }

            if(o instanceof CTHyperlink) {
               CTHyperlink link = (CTHyperlink)o;
               for(CTR r : link.getRList()) {
                  runs.add(new XWPFHyperlinkRun(link, r, p));
               }
            }
            if(o instanceof CTSdtRun) {
               CTSdtContentRun run = ((CTSdtRun)o).getSdtContent();
               for(CTR r : run.getRList()) {
                  runs.add(new XWPFRun(r, p));
               }
            }
            if(o instanceof CTRunTrackChange) {
               for(CTR r : ((CTRunTrackChange)o).getRList()) {
                  runs.add(new XWPFRun(r, p));
               }
            }
            if(o instanceof CTSimpleField) {
               for(CTR r : ((CTSimpleField)o).getRList()) {
                  runs.add(new XWPFRun(r, p));
               }
            }

        }

        return runs;
    }

}
