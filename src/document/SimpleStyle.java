package document;

import log.AnalysisLogger;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFStyle;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPr;

import java.math.BigInteger;


/***************************************************************************************''
 *
 *          Simple style are typically the styles we are working with
 *          in the analysis and frontend
 *
 *          This class abstracts the analysis and creates a simpler access
 *          to these attributes. (document independent)
 */

public class SimpleStyle {


    public SimpleStyle(StructureType type) {

        this.type = type;
    }

    public enum Numbering {STANDARD, RESTART, NONE}

    public StructureType type;
    public int level;
    public String name;
    public Numbering numbering = Numbering.STANDARD;
    public int id = -1;


    public boolean restartNumbering = false;


    /*****************************************************************'
     *
     *      SimpleSytyle is based on the style name and the level.
     *
     * @param p - the paragraph
     */

    public SimpleStyle(XWPFParagraph p) {

        type = StructureType.TEXT;  // This is default

        String name = p.getStyleID();
        String name2 = p.getStyle();

        if(isWhitespace(p.getText())){
            type = StructureType.WHITESPACE;
            return;
        }

        if(isHeader(name))
            type = StructureType.TITLE;
        if(isFooter(name))
            type = StructureType.PAGEFOOTER;
        if(isHeadline( p ))
            type = StructureType.HEADING;
        if(isList(p))
            type = StructureType.LISTITEM;
        if(isTOC(name))
            type = StructureType.TOC;

        checkNumbering(p);

    }

    private boolean isWhitespace(String text) {
        return text.trim().length() == 0;
    }

    /********************************************************************'
     *
     *          This is detecting the numbering schema used.
     *
     *
     *          //TODO: Get the start numbering from the styles.xml document
     *
     *
     * @param p
     */


    private void checkNumbering(XWPFParagraph p) {

        // If there are no NumPr attribute, follow document standard
        if(p.getCTP().getPPr() == null || p.getCTP().getPPr().getNumPr() == null)
            return;

        if(p.getCTP().getPPr().getNumPr().getNumId().getVal().intValue() == 0){

            // There is a numPr attribute overrriding the standard numbering.
            // It is also pointing at 0 which means NO numbering

            numbering = Numbering.NONE;

        }
        else{

            // We are pointing out a new Numbering Id, which means we are starting a new list

            numbering = Numbering.RESTART;
        }

    }

    private boolean isList(XWPFParagraph p) {

        // Both list and headings can have NumPr attributes
        try{

            if(p.getCTP().getPPr() == null)
                return false;


            if(p.getCTP().getPPr().getNumPr() != null){
                id = p.getCTP().getPPr().getNumPr().getNumId().getVal().intValue();
                level = getListIndentation( p );




            }

            return type != StructureType.HEADING && p.getCTP().getPPr().getNumPr() != null;

        }catch(Exception e){

            e.printStackTrace();
        }

        return false;
    }



    private boolean isTOC(String name){

        if(name == null)
            return false;
        return name.startsWith("TOC") || name.startsWith("Innehll");
    }

    private boolean isHeader(String name){

        if(name == null)
            return false;
        return name.startsWith("Tablehead") || name.startsWith("Tabellsidhuvud");
    }


    private boolean isFooter(String name){

        if(name == null)
            return false;
        return name.startsWith("Tablefooter") || name.startsWith("Tabellsidfot");
    }

    /**********************************************************************************
     *
     *          Determine if a paragraph is a headline
     *          Current implementation looks at the name. This should be replaced with an analysis
     *          of the actual definition of the format
     *
     *
     *
     * @param paragraph
     * @return
     */


    private boolean isHeadline(XWPFParagraph paragraph){



        if(name == null)
            return false;

        String washedName = name.toLowerCase().trim();

        if(! (washedName.contains("title") || washedName.contains("rubrik") || washedName.contains("heading")))
            return false;

        try{

            level = getLevel(name);
            //AnalysisLogger.log(Level.DEBUG, " Found indentation " + level + " for " + name);

        }catch(Exception e){

            level = 0;
        }

        if(level > 9 )
            level = 9;      // Max for the numberer


        return true;
    }

    private int getListIndentation(XWPFParagraph p) {

        BigInteger l = p.getNumIlvl();

        int level = AbstractStructureItem.NO_INDENTATION;

        if (l != null)
            level = l.intValue();

        //AnalysisLogger.log(AnalysisLogger.Level.DEBUG, "Got list indentation: " + level);
        return level;
    }



    private int getLevel(String name){

        int i = name.length();
        while (i > 0 && Character.isDigit(name.charAt(i - 1))) {
            i--;
        }
        return new Integer(name.substring(i));
    }


    public static boolean isList(String style) {

        if(style == null)
            return false;

        return style.startsWith("List");
    }
}
