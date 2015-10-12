package document;

import analysis2.AnalysisException;
import log.AnalysisLogger;
import org.apache.poi.POIXMLDocumentPart;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGraphicalObject;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGraphicalObjectData;
import org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTInline;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;
import sun.swing.text.TextComponentPrintable;

import java.io.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/***************************************************************************************************'
 *
 *          The docX splitter splits a word document into pieces that can be retrieved for
 *          later storing in the database.
 *
 *          It stores the clauses and fragments from a one pass parsing
 *
 *
 *          The structure is built up with fragments and structure items
 *
 *           - A fragment belongs a structure item that defines the position in the document
 *            - A structure item is a head line, list or list item
 *            - Structure items have indentations creating a tree structure
 *            - a structure item also have a top item which is the textual representation
 *            - A list may not have a top element, as there is no heading for it.
 *
 */



public class DocumentManager implements FragmentSplitterInterface {

    // The max depth of the indentation levels.
    private static final int MAX_DEPTH = 20;
    private static final String defaultColour = "FFFFFFFF";     // ARGB

    public enum FileType {WORD, EXCEL, UNKNOWN, OLD, PDF, XML}

    private List<AbstractStructureItem> structureItems = new ArrayList<AbstractStructureItem>();
    private List<AbstractClause> clauses = new ArrayList<AbstractClause>();
    private List<AbstractComment> comments = new ArrayList<AbstractComment>();
    private List<AbstractFragment> fragments = new ArrayList<AbstractFragment>();
    private List<AbstractImage> images = new ArrayList<AbstractImage>();
    private StringBuffer body = new StringBuffer();

    private FileType fileType;
    private String fileName;
    private String documentTitle;   // Title found in document

    /*****************************************************************
     *
     *          //TODO: The different parse() methods should really be in different classes with a factory creating the parser  from the file type
     *
     *
     *
     * @param file
     * @param stream
     */



    public DocumentManager(String file, InputStream stream ) throws AnalysisException {

        try {

            fileType = getFileType(file);
            this.fileName = file;
            this.documentTitle = file;      // Default is to use the filename

            AnalysisLogger.log(AnalysisLogger.Level.INFO, "Parsing the uploaded file " + file);

            switch (fileType) {


                case WORD:

                    XWPFDocument docX = new XWPFDocument(stream);
                    handleImages(docX);
                    parse(docX);
                    //handleListElements(docX);
                    break;

                case EXCEL:

                    XSSFWorkbook workbook = new XSSFWorkbook(stream);
                    parse(workbook);
                    break;

                case PDF:
                    throw new AnalysisException("PDF format not supported yet").inDocument(fileName);

                case UNKNOWN:

                    AnalysisLogger.log(AnalysisLogger.Level.ERROR, "Unknown file format for document " + fileName);
                    throw new AnalysisException("Unknown format)").inDocument(fileName);

                case OLD:
                    throw new AnalysisException("Old Microsoft format not supported").inDocument(fileName);
            }


        } catch (IOException e) {

            AnalysisLogger.log( e );

        }


    }


    /*
    private void handleListElements(XWPFDocument document) {
        XWPFNumbering numbering = null;
        XWPFParagraph para = null;
        XWPFNum num = null;
        List<XWPFParagraph> paraList = null;
        Iterator<XWPFParagraph> paraIter = null;

        numbering = document.getNumbering();

        System.out.println("Going through list elements");


        paraList = document.getParagraphs();
        paraIter = paraList.iterator();
        while(paraIter.hasNext()) {

            para = paraIter.next();
            BigInteger numID = para.getNumID();

            if(numID != null) {

                int level = para.getNumIlvl().intValue();
                num = numbering.getNum(numID);
                BigInteger abstractNumId = num.getCTNum().getAbstractNumId().getVal();


                AnalysisLogger.log(Level.DEBUG, " Getting numbering details of List paragraph " + para.getText());
                System.out.println(" - It's abstract numID is " + abstractNumId.intValue() + " an3d level = " + level);

                XWPFNum currentParagraphNumbering = para.getDocument().getNumbering().getNum(para.getNumID());
                XWPFAbstractNum currentParagraphAbstractNum = numbering.getAbstractNum(abstractNumId);
                CTAbstractNum currentParagraphAbstractNumFormatting = currentParagraphAbstractNum.getCTAbstractNum();
                CTLvl levelStyle = currentParagraphAbstractNumFormatting.getLvlArray(level);
                String lvlText = extractLevelText(levelStyle.toString());

                System.out.println("-- Numbering Format: " + levelStyle.getNumFmt().getVal().toString() + " Pattern:" +  lvlText + " Indentation: " + levelStyle.getPPr().getInd().getLeft());
                System.out.println("*****************");

            }
            else {
                System.out.print("Null numID ");
            }
            System.out.println("Text " + para.getParagraphText());
        }

    }

      */

    /*********************************************************************************
     *
     *      Parsing excel document
     *
     *
     *
     *
     * @param workbook
     */


    private void parse(XSSFWorkbook workbook) {

        AbstractClause defaultClause = new AbstractClause("").setId(0);
        clauses.add(defaultClause);

        int numberOfWorkSheets = workbook.getNumberOfSheets();

        for(int sheetNo = 0; sheetNo < numberOfWorkSheets; sheetNo++){

            //For each worksheet we create a headline and go through the sheet

            XSSFSheet currentSheet = workbook.getSheetAt( sheetNo );
            String sheetName = makeSheetTitle(currentSheet.getSheetName());

            // Create a fragment

            AbstractFragment fragment = new AbstractFragment(sheetName)
                     .setStyle(StructureType.HEADING)
                     .setIndentation(0);

            // Create a structure item for the headline and store it on the correct indentation level
            // including connecting it to the fragment

            AbstractStructureItem sheetTitle = new AbstractStructureItem()
                    .setId( sheetNo )
                    .setStyle(StructureType.HEADING, "")
                    .setIndentation(0)
                    .setTopElement(fragment);

            fragment.setStructureParent(sheetTitle);

            structureItems.add(sheetTitle);
            fragments.add(fragment);


            parse(workbook, currentSheet, sheetTitle);


        }
    }

    /******************************************************************
     *
     *          Creating a special title for a sheet
     *
     * @param sheetName      - name of sheet from document
     * @return               - custom title for sheet
     */

    private String makeSheetTitle(String sheetName) {

        return  "Sheet: " + sheetName;
    }

    /*********************************************************************************
     *
     *          Parse a work sheet
     *
     * @param sheet         - the xml sheet
     * @param sheetTitle    - structure item for the sheet
     *
     *               NOTE: The sheet does not generate any structure items. There is only one H1 per sheet
     */

    private void parse(XSSFWorkbook workBook, XSSFSheet sheet, AbstractStructureItem sheetTitle) {

        //Iterate through each rows one by one

        for (Row row : sheet) {

            int rowNo = row.getRowNum();

            //For each row, iterate through all the columns
            Iterator<Cell> cellIterator = row.cellIterator();

            int rowWidth = getRowWidth(row);

            while (cellIterator.hasNext()) {

                Cell cell = cellIterator.next();

                try{

                    String content = null;
                    int colNo = cell.getColumnIndex();

                    try {


                        //Check the cell type and format accordingly
                        switch (cell.getCellType()) {
                            case Cell.CELL_TYPE_NUMERIC:

                                content = "" + (int)Math.round(cell.getNumericCellValue() *1000) / 1000;
                                System.out.println(" *** Numeric content: " + content);
                                break;

                            case Cell.CELL_TYPE_STRING:

                                content = cell.getStringCellValue();
                                break;

                            case Cell.CELL_TYPE_FORMULA:

                                content = getFormula(cell);

                                break;

                            case Cell.CELL_TYPE_BLANK:

                                content = "";
                                break;

                            case Cell.CELL_TYPE_BOOLEAN:

                                content = "" + cell.getBooleanCellValue();
                                break;

                            case Cell.CELL_TYPE_ERROR:

                                content = "#error";
                                break;

                            default:

                                AnalysisLogger.log(AnalysisLogger.Level.WARNING, "Error parsing " + fileName + " cell:(" + rowNo + ", " + colNo + " ) has unknown type " + cell.getCellType());
                                content = "????";

                        }

                    } catch (IllegalStateException e) {

                        e.printStackTrace();
                        AnalysisLogger.log(AnalysisLogger.Level.ERROR, "Error parsing " + fileName + e.getLocalizedMessage() + " cell:(" + rowNo + ", " + colNo + " )");
                        content = "????";

                    }


                    AnalysisLogger.log(AnalysisLogger.Level.DEBUG, " --- Getting cell " + content);
                    String colour = getColour(cell);

                    // We want to skip empty cells. However, check so that there
                    // are no unwanted side effects in the rendering from this

                    if ( !isEmptyCell(content, colour) ) {

                        // We are not adding empty cells. It will be the responsibility
                        // of the frontend to display the cells correct given the coordinates.

                        //System.out.println("Found width " + sheet.getColumnWidth(colNo) + " for column " + colNo );

                        int width = toPoints(sheet.getColumnWidth(colNo));
                        XSSFFont font = workBook.getFontAt(cell.getCellStyle().getFontIndex());
                        boolean bold = font.getBold();
                        boolean italic = font.getItalic();
                        int fontHeight = font.getFontHeightInPoints();
                        int border = 0; // Border not implemented. Passing empty value here
                        boolean wrap = cell.getCellStyle().getWrapText();

                        cell.getCellStyle().getFontIndex();

                        TableSpan span = getColSpanForRegion(sheet, colNo, rowNo);


                        body.append(content).append(" ");

                        CellInfo cellInfo = new CellInfo(colNo, rowNo, colour, width, fontHeight, span, border, wrap, rowWidth);

                        AnalysisLogger.log(AnalysisLogger.Level.INFO, "       - adding cell(" + colNo + "," + rowNo + "): " + content + " with Structure: " + sheetTitle.getTopElement().getBody());
                        AnalysisLogger.log(AnalysisLogger.Level.DEBUG, cellInfo.toString());

                        //AnalysisLogger.log(Level.DEBUG, " Cell background color: " + colour);


                        AbstractFragment fragment = new AbstractFragment(style(font,  content))
                                .setStyle(StructureType.TEXT)
                                .setIsCell(cellInfo)
                                .setStructureParent(sheetTitle);   // All fragments in the sheet have the same parent

                        fragments.add(fragment);

                    } else {
                        AnalysisLogger.log(AnalysisLogger.Level.DEBUG, "       - skipping cell(" + rowNo + "," + colNo + "): " + content);
                    }

                }catch(Exception e){

                   AnalysisLogger.log( e , " Failed parsing cell: " + cell.toString() );
                }



            }
        }

    }

    private boolean isEmptyCell(String content, String colour) {

        boolean isEmpty = (content == null || content.equals(""));
        return isEmpty && colour.equals(defaultColour);
    }

    /*******************************************************'
     *
     *          manually counting the number of cells in the row.
     *          This should be possible to do easier by accessing a collection of some sort
     *
     * @param row         - the row object to count cells in
     * @return            - numbers of used cells
     */

    private int getRowWidth(Row row) {

        Iterator<Cell> cellIterator = row.cellIterator();

        int i = 0;
        while(cellIterator.hasNext()) {
            i++;
            cellIterator.next();
        }
        return i;
    }



    private String getFormula(Cell cell) {

        try{

            return  "" + (double)Math.round(cell.getNumericCellValue() *1000) / 1000;

        } catch (IllegalStateException e) {

            AnalysisLogger.log(AnalysisLogger.Level.INFO, "Error parsing int from formula in " + fileName + e.getLocalizedMessage());
            return "#ERROR";

        }
    }

    private TableSpan getColSpanForRegion(XSSFSheet sheet, int colNo, int rowNo) {

        int regions = sheet.getNumMergedRegions();
        for(int i = 0; i < regions; i++){

            CellRangeAddress rangeAddress = sheet.getMergedRegion(i);

            int x = rangeAddress.getFirstColumn();
            int y = rangeAddress.getFirstRow();

            //System.out.println("Looking for region for: " + colNo + " " + rowNo);


            if(x == colNo && y == rowNo){

                //System.out.println("Got colspan for cell " + x + " " + y);

                int colSpan = 1 + rangeAddress.getLastColumn() - x;
                int rowSpan = 1 + rangeAddress.getLastRow() - y;
                return new TableSpan(rowSpan, colSpan);
            }

        }
        return new TableSpan(1, 1);


    }

    // TODO: Handle font size too.
    // TODO: Replace with a span

    private String style(XSSFFont font, String content) {
        String html = content;
        if(font.getBold())
            html = "<b>" + html + "</b>";
        if(font.getItalic())
            html = "<i>" + html + "</i>";
        if(font.getStrikeout())
            html = "<strike>" + html + "</strike>";

        return html;
    }


    private FileType getFileType(String file) {

        if(file.endsWith(".docx"))
            return FileType.WORD;
        if(file.endsWith(".xlsx"))
            return FileType.EXCEL;
        if(file.endsWith(".pdf"))
            return FileType.PDF;

        if(file.endsWith(".doc") || file.endsWith(".xls"))
            return FileType.OLD;

        System.out.println("Could not understand file format for \"" + file + "\"");

        return FileType.UNKNOWN;
    }

    @Override
    public List<AbstractFragment> getFragments() {

        return fragments;
    }

    @Override
    public List<AbstractComment> getComments() {

        return comments;
    }


    @Override
    public String getDocumentTitle(){
        return documentTitle;
    }

    @Override
    public List<AbstractStructureItem> getStructureItems() {

        System.out.println("Returning " + structureItems.size() + " structure items in document manager");

        return structureItems;
    }

    /***************************************************************************************'
     *
     *          parse a document
     *
     *          The output is:
     *
     *           - list of structure items
     *           - list of fragments
     *           - list of comments
     *
     *
     * @param docX
     */

    private void parse(XWPFDocument docX) {

        try{

            //List<XWPFParagraph> paragraphs = docX.getParagraphs();
            List<IBodyElement> elements = docX.getBodyElements();
            XWPFComment[] documentComments = docX.getComments();
            ListNumberManager numberer = new ListNumberManager(docX);


            AnalysisLogger.log(AnalysisLogger.Level.DEBUG, "Found " + elements.size() + " elements");

            int pNo = 1;
            int itemNumber = 0;

            // Create a structure and

            AbstractStructureItem[] structureItemLevel = new AbstractStructureItem[MAX_DEPTH];
            for(int i = 0; i < MAX_DEPTH; i++)
               structureItemLevel[i] = null;


            //Keeping track of the indentations
            int currentIndentation = 0;
            int lastIndentation = 0;


            int elementNumber = 0;
            boolean isFirstHeading = true;

            AbstractStructureItem currentStructureItem = null;

            SectionManager paragraphGrouper = new SectionManager();

            for(IBodyElement element : elements){

                try{
                    // Catch exceptions for each element separately here to be able to continue properly


                    boolean handled = false;
                        AbstractFragment fragment = null;

                        if(element instanceof XWPFParagraph){

                            XWPFParagraph p = (XWPFParagraph)element;
                            RunManager runManager = new RunManager( p );

                            List<XWPFRun> runs = runManager.getRunsForParagraph( p );
                            CommentManager commentManager = new CommentManager( runs, fragments.size(), documentComments );


                            String paragraphText = p.getText();
                            String fragmentText = runManager.convertToText();

                            //AnalysisLogger.log(AnalysisLogger.Level.DEBUG, "Handling paragraph element");
                            handled = true;

                            String name = (paragraphText.length() > 40 ? paragraphText.substring(0, 40) : paragraphText);

                            //SimpleStyle style = new SimpleStyle( p );
                            StructureType fragmentStructureType = numberer.getStructureType( p, paragraphText );

                            lastIndentation = currentIndentation;
                            List<AbstractImage> imagesForFragment = new ArrayList<>();

                            if( p != null && runs.size() > 0 ){

                                boolean inAnchor = false;
                                String anchor = "";
                                /*

                                    Moved this to AFTER the fragment parsing to know where to put the comment
                                    Remove this when we know it works.

                                for(XWPFRun run : runs){

                                    //System.out.println(" *** Run: " + run.toString());

                                    boolean markupFragment = run != null && run.getText(0) != null && run.getText(0).startsWith(RunManager.MARKUP_TOKEN);

                                    if(inAnchor && !markupFragment){

                                        anchor += run.getText(0);
                                    }

                                    if(markupFragment){

                                        inAnchor = !inAnchor;
                                    }

                                    CTR ctr = run.getCTR();

                                    if(ctr.getCommentReferenceArray().length > 0){

                                       CTMarkup markup = ctr.getCommentReferenceArray(0);

                                       AnalysisLogger.log(AnalysisLogger.Level.INFO, "Found a comment in paragraph " + paragraphText + ". Adding annotation");
                                       AnalysisLogger.log(AnalysisLogger.Level.DEBUG, "no=" + elementNumber + "id= " + markup.getId() + " - RsidR: " + Arrays.toString(p.getCTP().getRsidR()));

                                       comments.add(createComment(markup, xwpfComments, elementNumber, anchor));
                                        anchor = "";
                                        inAnchor = false;
                                    }

                                    List<CTDrawing> drawings = ctr.getDrawingList();

                                    if(drawings.size() > 0){
                                        AnalysisLogger.log(AnalysisLogger.Level.INFO, "*** Found " + drawings.size() + " drawings for paragraph!");
                                        //AnalysisLogger.log(AnalysisLogger.Level.WARNING, " Found drawing in the document " + fileName + " . Drawing is not supported - Ignored");

                                        for (CTDrawing drawing : drawings) {

                                            //TODO: Handle anchorArray too

                                            if(drawing.sizeOfInlineArray() > 0){



                                                for (CTInline ctInline : drawing.getInlineList()) {

                                                    long width = ctInline.getExtent().getCx() / 200;
                                                    long height = ctInline.getExtent().getCy() / 200;

                                                    try {

                                                        String relationshipId = getRelationshipId(ctInline);
                                                        imagesForFragment.add(getImageForAnchor(width, height, relationshipId, docX));
                                                        name = "images";
                                                        fragmentStructureType = StructureType.IMAGE;

                                                    } catch (AnalysisException e) {

                                                        AnalysisLogger.log( e );
                                                    }

                                                }
                                            }
                                        }

                                    }

                                    List<CTPicture> pictures = ctr.getPictList();

                                    if(pictures.size() > 0){

                                        System.out.println("*** Found " + pictures.size() + " pictures for paragraph!");
                                        AnalysisLogger.log(AnalysisLogger.Level.WARNING, " Found picture in the document " + fileName + " . Picture is not supported - Ignored");

                                    }
                                }

                                */

                                String activeParagraph = "  --    Analyzing paragraph " + elementNumber + "(" + name + ")..:";

                               AnalysisLogger.log(AnalysisLogger.Level.DEBUG, "***************************************");
                               AnalysisLogger.log(AnalysisLogger.Level.INFO, activeParagraph);

                               AnalysisLogger.log(AnalysisLogger.Level.DEBUG, "  StructureType = "+ fragmentStructureType.name()+" (Last indentation level " + lastIndentation + ")");

                               List<String> keywords = new ArrayList<String>();

                               // Check for paragraph style

                               switch (fragmentStructureType) {

                                   case WHITESPACE:

                                       // Ignoring whitespace lines
                                       AnalysisLogger.log(AnalysisLogger.Level.DEBUG, "Ignoring the line as it is only whitespace ");

                                       fragment = paragraphGrouper.releaseFragment();
                                       if(fragment != null)
                                           fragments.add(fragment);
                                   break;

                                   case HEADING:

                                       // Handle the headline. This is a structure item and will be stored as a structure with the
                                       // fragment as top element

                                       fragment = paragraphGrouper.releaseFragment();
                                       if(fragment != null)
                                           fragments.add(fragment);


                                        currentIndentation = numberer.updateHeadingNumbering(p);
                                        numberer.getCurrentListStyle(p, docX.getNumbering());
                                        AnalysisLogger.log(AnalysisLogger.Level.DEBUG, "In Heading. Current indentation = " + currentIndentation);


                                       fragmentText = numberer.renderNumber() + fragmentText;

                                       if(isFirstHeading && currentIndentation == 0){

                                           // The first heading on the top level. This is a title

                                           documentTitle = fragmentText;
                                           isFirstHeading = false;
                                           AnalysisLogger.log(AnalysisLogger.Level.INFO, "Found title " + documentTitle + " in document " + fileName);
                                           break;
                                       }

                                       currentStructureItem = getCurrentStructureItem(structureItemLevel, currentIndentation, lastIndentation);


                                       // Create a fragment

                                       fragment = new AbstractFragment(fragmentText)
                                               //.setStyle(new SimpleStyle(StructureType.HEADING))
                                                .setStyle(StructureType.HEADING)
                                                .setIndentation(currentIndentation);

                                       // Create a structure item for the headline and store it on the correct indentation level
                                       // including connecting it to the fragment

                                       AbstractStructureItem headline = new AbstractStructureItem()
                                               .setId(itemNumber++)
                                               .setStyle(StructureType.HEADING, p.getStyleID())
                                               .setIndentation(currentIndentation)
                                               .setTopElement(fragment);

                                       AnalysisLogger.log(AnalysisLogger.Level.DEBUG, " - Created a new headline on indentation level " + currentIndentation + " (no:" + (itemNumber - 1) + ")");

                                       setItemLevel(currentIndentation, headline, structureItemLevel);

                                       fragment.setStructureParent(headline);
                                       structureItems.add(headline);
                                       fragments.add(fragment);


                                       break;

                                   case IMAGE:

                                       fragment = paragraphGrouper.releaseFragment();
                                       if(fragment != null)
                                           fragments.add(fragment);

                                       currentIndentation = numberer.updateTextIndentation(p);

                                       currentStructureItem = getCurrentStructureItem(structureItemLevel, lastIndentation, lastIndentation);
                                       if (currentStructureItem == null ) {

                                           // There are text before the first heading. If this is the case,
                                           // we need to add an empty clause anyway to be able to store the fragment

                                           AbstractStructureItem implicit = new AbstractStructureItem()
                                                   .setId(itemNumber++)
                                                   .setStyle(StructureType.IMPLICIT, p.getStyleID())
                                                   .setIndentation(lastIndentation);

                                           AnalysisLogger.log(AnalysisLogger.Level.INFO, " - Found text without structure parent for fragment \"" + paragraphText
                                                   + "\". Creating implicit structure item on indentation level " + lastIndentation);

                                           structureItems.add(implicit);
                                           currentStructureItem = implicit;
                                           setItemLevel(lastIndentation, implicit, structureItemLevel);

                                           fragment = new AbstractFragment( name )
                                                    .setStyle(StructureType.IMPLICIT)
                                                    .setStructureParent(currentStructureItem)
                                                    .setIndentation(lastIndentation);

                                           fragments.add(fragment);

                                       }
                                       fragment = new AbstractFragment( name )
                                                .setStyle(StructureType.IMAGE)
                                                .setStructureParent(currentStructureItem)
                                                .setIndentation(lastIndentation);

                                       AbstractImage firstImage = imagesForFragment.get(0);
                                       AnalysisLogger.log(AnalysisLogger.Level.DEBUG, " --- Setting image " + firstImage.toString());

                                       fragment.setImages(imagesForFragment);
                                       fragments.add(fragment);
                                        break;


                                   case LISTITEM:

                                       fragment = paragraphGrouper.releaseFragment();
                                       if(fragment != null)
                                           fragments.add(fragment);

                                        currentIndentation = numberer.updateListNumbering(p);
                                        numberer.getCurrentListStyle(p, docX.getNumbering());

                                        currentStructureItem = getCurrentStructureItem(structureItemLevel, currentIndentation, lastIndentation);

                                       // There is a list item but no list structure on this indentation level. (First item in the list)
                                       // We create a new structure item for the list

                                       if (currentStructureItem == null || currentStructureItem.getStructureType() != StructureType.LIST) {


                                           AbstractStructureItem list = new AbstractStructureItem()
                                                   .setId(itemNumber++)
                                                   .setStyle(StructureType.LIST, p.getStyleID())
                                                   .setIndentation(currentIndentation - 1);

                                           AnalysisLogger.log(AnalysisLogger.Level.DEBUG, "Created a new list on indentation level " + (currentIndentation));

                                           AbstractFragment implicitFragment = new AbstractFragment("---")
                                                   .setStyle(StructureType.IMPLICIT)
                                                   .setIndentation(currentIndentation - 1)
                                                   .setStructureParent(list);

                                           structureItems.add(list);
                                           fragments.add(implicitFragment);

                                           setItemLevel(currentIndentation, list, structureItemLevel);

                                       }

                                       AnalysisLogger.log(AnalysisLogger.Level.DEBUG, "In List. Current indentation = " + currentIndentation);

                                       fragmentText = numberer.renderNumber() + fragmentText;


                                       // The list item is also a structure

                                       AbstractStructureItem listItem = new AbstractStructureItem()
                                               .setId(itemNumber++)
                                               .setStyle(StructureType.LISTITEM, p.getStyleID())
                                               .setIndentation(currentIndentation);

                                       AnalysisLogger.log(AnalysisLogger.Level.DEBUG, "Created a new list item on indentation level " + (currentIndentation + 1));

                                       setItemLevel(currentIndentation+1, listItem, structureItemLevel);
                                       structureItems.add(listItem);

                                       // Create the fragment as a list

                                       fragment = new AbstractFragment(fragmentText)
                                               .setStyle(fragmentStructureType)
                                               .setIndentation(currentIndentation)
                                               .setStructureParent(listItem);

                                       listItem.setTopElement(fragment);
                                       fragments.add(fragment);

                                       break;


                                   case TOC:

                                       // Ignore this. We do not want to add TOC to the fragments.
                                       // TOC is really the tools speciality

                                       fragment = paragraphGrouper.releaseFragment();
                                       if(fragment != null)
                                           fragments.add(fragment);

                                       AnalysisLogger.log(AnalysisLogger.Level.DEBUG, "Ignoring TOC style for fragment \"" + paragraphText + "\".");

                                       break;

                                   case PAGEHEADER:
                                   case PAGEFOOTER:


                                       fragment = paragraphGrouper.releaseFragment();
                                       if(fragment != null)
                                           fragments.add(fragment);

                                       // Ignore this. We do not want to add headers or footers to the fragments.
                                       // The documents are presented in a continuous flow
                                       AnalysisLogger.log(AnalysisLogger.Level.DEBUG, " - Ignoring Header/Footer style  for fragment \"" + paragraphText + "\".");

                                       break;


                                   default:

                                       // Default is text. We don't care about the indentation here. The fragments will still have the same structure parent

                                       currentIndentation = numberer.updateTextIndentation(p);

                                       // If the text looks like a headline, the section is split and we release what we have so far

                                       if(runManager.isImplicitHeadline()){

                                           fragment = paragraphGrouper.releaseFragment();
                                           if(fragment != null)
                                               fragments.add(fragment);

                                       }


                                       currentStructureItem = getCurrentStructureItem(structureItemLevel, currentIndentation, lastIndentation);
                                       if (currentStructureItem == null ) {

                                           // There are text before the first heading. If this is the case,
                                           // we need to add an empty clause anyway to be able to store the fragment

                                           AbstractStructureItem implicit = new AbstractStructureItem()
                                                   .setId(itemNumber++)
                                                   .setStyle(StructureType.IMPLICIT, p.getStyleID())
                                                   .setIndentation(currentIndentation);

                                           AnalysisLogger.log(AnalysisLogger.Level.DEBUG, " - Found text without structure parent for fragment \"" + paragraphText
                                                   + "\". Creating implicit structure item on indentation level " + currentIndentation);

                                           structureItems.add(implicit);
                                           currentStructureItem = implicit;
                                           setItemLevel(currentIndentation, implicit, structureItemLevel);

                                           fragment = new AbstractFragment("---")
                                                    .setStyle(StructureType.IMPLICIT)
                                                    .setStructureParent(currentStructureItem)
                                                    .setIndentation(currentIndentation);

                                           fragments.add(fragment);

                                       }

                                       fragment = paragraphGrouper.storeToFragment(fragmentText, currentStructureItem, currentIndentation);
                                        /*
                                       fragment = new AbstractFragment(fragmentText)
                                                .setStyle(StructureType.TEXT)
                                                .setStructureParent(currentStructureItem)
                                                .setIndentation(currentIndentation);
                                          */

                                       setItemLevel(currentIndentation, currentStructureItem, structureItemLevel);

                                       if(fragment != null){
                                            fragments.add(fragment);

                                           AnalysisLogger.log(AnalysisLogger.Level.DEBUG, " - Adding text fragment on indentation level " + currentIndentation + " with structure item " +
                                                   currentStructureItem.getStructureType() + "( id " + currentStructureItem.getID() + ")");

                                       }

                               }


                                body.append(paragraphText); // Add to the total body
                                body.append(" ");

                                comments.addAll(commentManager.handleCommentsInParagraph( ));

                                //TODO: Refactor: Add a drawing manager

                                for(XWPFRun run : runs){

                                    //System.out.println("     !  Run: " + run.toString());

                                    CTR ctr = run.getCTR();


                                    List<CTDrawing> drawings = ctr.getDrawingList();

                                    if(drawings.size() > 0){
                                        AnalysisLogger.log(AnalysisLogger.Level.INFO, "*** Found " + drawings.size() + " drawings for paragraph!");
                                        //AnalysisLogger.log(AnalysisLogger.Level.WARNING, " Found drawing in the document " + fileName + " . Drawing is not supported - Ignored");

                                        for (CTDrawing drawing : drawings) {

                                            //TODO: Handle anchorArray too

                                            if(drawing.sizeOfInlineArray() > 0){



                                                for (CTInline ctInline : drawing.getInlineList()) {

                                                    long width = ctInline.getExtent().getCx() / 200;
                                                    long height = ctInline.getExtent().getCy() / 200;

                                                    try {

                                                        String relationshipId = getRelationshipId(ctInline);
                                                        imagesForFragment.add(getImageForAnchor(width, height, relationshipId, docX));
                                                        name = "images";
                                                        fragmentStructureType = StructureType.IMAGE;

                                                    } catch (AnalysisException e) {

                                                        AnalysisLogger.log( e );
                                                    }

                                                }
                                            }
                                        }

                                    }

                                    List<CTPicture> pictures = ctr.getPictList();

                                    if(pictures.size() > 0){

                                        System.out.println("*** Found " + pictures.size() + " pictures for paragraph!");
                                        AnalysisLogger.log(AnalysisLogger.Level.WARNING, " Found picture in the document " + fileName + " . Picture is not supported - Ignored");

                                    }
                                }
                           }
                            else{

                               AnalysisLogger.log(AnalysisLogger.Level.DEBUG, "No runs in paragraph " + elementNumber + "(" + p.getStyleID() + ") so it is ignored");

                           }
                       } // end paragraph



                        if(element instanceof XWPFTable){

                            XWPFTable table = (XWPFTable)element;
                            AnalysisLogger.log(AnalysisLogger.Level.DEBUG, "Handling table element");
                            handled = true;

                            fragment = paragraphGrouper.releaseFragment();
                            if(fragment != null)
                                fragments.add(fragment);


                            currentStructureItem = getCurrentStructureItem(structureItemLevel, currentIndentation, lastIndentation);

                            AnalysisLogger.log(AnalysisLogger.Level.DEBUG, " - Found a table cell");

                            if (currentStructureItem == null ||
                                    currentStructureItem.getStructureType() != StructureType.TABLE){

                                // There are text before the first heading. If this is the case,
                                // we need to add an empty clause anyway to be able to store the fragment

                                AbstractStructureItem tableItem = new AbstractStructureItem()
                                        .setId(itemNumber++)
                                        .setStyle(StructureType.TABLE, table.getStyleID())
                                        .setIndentation(currentIndentation);


                                structureItems.add(tableItem);
                                currentStructureItem = tableItem;
                                AnalysisLogger.log(AnalysisLogger.Level.DEBUG, " - Adding a TABLE structure item number " + (itemNumber - 1));

                            }

                            // Parse the table

                            List<XWPFTableRow> tableRows = table.getRows();


                            AnalysisLogger.log(AnalysisLogger.Level.DEBUG, "  (Found " + tableRows.size() + " rows)");
                            int rowNo = 0;

                            for(XWPFTableRow row : tableRows){

                                List<XWPFTableCell> cellsForRow = row.getTableCells();
                                int rowWidth = cellsForRow.size();        // Remember this to store in all cellInfo. It is used to analyse special two-column tables.

                                AnalysisLogger.log(AnalysisLogger.Level.DEBUG, "   - Adding row with " + rowWidth + " cells");

                                int colNo = 0;

                                for(XWPFTableCell cell : cellsForRow){

                                    // Handling a cell. It works on the entire cell, even if this could be divided into
                                    // fragments itself. That would however complicate the front-end with multiple fragments fro the same cell.


                                    //if(!isEmpty(cell)){

                                    String cellContent = handleDocumentCellContent(cell.getBodyElements());

                                    //System.out.println("***********************\nCell content:\n" + cellContent  +" **************************");


                                        String content = cell.getText();
                                        SimpleStyle cellStyle = getStyleForCell(cell);

                                        if(cellStyle != null && (cellStyle.type == StructureType.PAGEHEADER  || cellStyle.type == StructureType.PAGEFOOTER )){

                                            // Found a cell style that should be ignored
                                            AnalysisLogger.log(AnalysisLogger.Level.DEBUG, "   (skipping header/footer cell(" + rowNo + "," + colNo + "): " + content + ")");

                                        }else{


                                            // We are not adding empty cells. It will be the responsibility
                                            // of the frontend to display the cells correct given the coordinates.

                                            String colour = cell.getColor();

                                            if(colour == null)
                                                colour = "FFFFFFFF";       // ARGB

                                            int width = getWidth(cell);
                                            boolean wrap = true;           // Wrapping always true for docx table
                                            int  border = 0;               // Not implemented
                                            int fontHeight = 0;            // Not implemented

                                            TableSpan span = getColSpanForCell(cell);

                                            CellInfo cellInfo = new CellInfo(colNo, rowNo, colour, width, fontHeight, span, border, wrap, rowWidth);


                                            AnalysisLogger.log(AnalysisLogger.Level.DEBUG, "        - adding cell(" + rowNo + "," + colNo + "/" + rowWidth + "): " + content);
                                            AnalysisLogger.log(AnalysisLogger.Level.DEBUG, "        - "+ cellInfo.toString());
                                            AnalysisLogger.log(AnalysisLogger.Level.DEBUG, "        - with Structure Parent:" + currentStructureItem.getStructureType().name());

                                            //AnalysisLogger.log(AnalysisLogger.Level.DEBUG, " --- Cell width: " +  width);


                                            fragment = new AbstractFragment(content)
                                                    .setStyle(StructureType.TEXT)
                                                    .setIsCell(cellInfo)
                                                    .setStructureParent(currentStructureItem);



                                            body.append(content); // Add to the total body
                                            fragments.add(fragment);
                                            }

                                    //}
                                    //else{
                                    //
                                    //    AnalysisLogger.log(AnalysisLogger.Level.DEBUG, "   (skipping empty cell @(" + rowNo + "," + colNo + "))");
                                    //
                                    //}

                                    colNo++;

                                }
                                rowNo++;
                            }

                            fragment = null;     // Already added all the fragments.

                        }  // end table


                        if(!handled){

                            AnalysisLogger.log(AnalysisLogger.Level.DEBUG, "NOT handled paragraph element" + elementNumber);

                        }

                        elementNumber++;

                }catch(Exception e){

                    // Parsing failed for this fragment. Log this and continue.
                    //TODO: Add feedback message for this
                    //TODO: Concatenate errors and send ONE fatal mail


                    AnalysisLogger.log( e , "Failed fragment: " + element.toString());
                }


            }

            // There may be a fragment left withheld. This must be released now when the document is completed

            AbstractFragment lastFragment = paragraphGrouper.releaseFragment();
            if(lastFragment != null)
                fragments.add(lastFragment);



        }catch(Exception e){

            AnalysisLogger.log( e );


        }
    }

    /********************************************************************
     *
     *          Extracting fragment content from the
     *
     *
     *
     * @param bodyElements
     * @return
     *
     *          //TODO: Not supported indentation in the cell
     */


    private String handleDocumentCellContent(List<IBodyElement> bodyElements) {

        StringBuffer cellContent = new StringBuffer();

        for (IBodyElement bodyElement : bodyElements) {

            if(bodyElement instanceof XWPFParagraph){

                XWPFParagraph paragraph = (XWPFParagraph)bodyElement;

                RunManager runManager = new RunManager(paragraph);
                cellContent.append( runManager.convertToText() + "<br/>");


            }
            else{

                // No other types of information within a cell is supported.

                AnalysisLogger.log(AnalysisLogger.Level.WARNING, "Not supported content in cell");
                return "???";

            }

        }
        return cellContent.toString();


    }

    /***********************************************************************
     *
     *      Parse col span
     *
     * @param cell
     * @return
     *
     *      NOTE: Row span not implemented
     */

    private TableSpan getColSpanForCell(XWPFTableCell cell) {

        TableSpan span = new TableSpan(1, 1);

        CTTc cttc = cell.getCTTc();
        if(cttc == null)
            return span;

        CTTcPr cttcpr = cttc.getTcPr();
        if(cttcpr == null)
            return span;

        CTDecimalNumber gridSpan = cttcpr.getGridSpan();
        if(gridSpan == null)
            return span;

        return new TableSpan(1, gridSpan.getVal().intValue());


    }

    private String getRelationshipId(CTInline ctInline) {

        if(ctInline == null )
            return null;

        CTGraphicalObject graphicalObject = ctInline.getGraphic();

        if(graphicalObject == null)
            return null;

        CTGraphicalObjectData graphicalObjectData = graphicalObject.getGraphicData();

        if(graphicalObjectData == null)
            return null;

        String xml = graphicalObject.toString();

        //System.out.println("XML:" + xml);
        int pos = xml.indexOf("a:blip r:embed=") + 16;
        String rest = xml.substring(pos, pos + 10);
        String rid = rest.substring(0, rest.indexOf("\""));

        return rid;
    }

    /**********************************************************
     *
     *        When finding a reference to an image in the text, lookup the
     *        image in the relations table, create an abstract image from it,
     *        set the size and return it to attach it to the fragment
     *
     * @param width         - width
     * @param height        - height
     * @param rId           - relationship id to find the correct embedded object
     * @param docX          - This document
     * @return              - An abstract image for the abstract document
     */

    private AbstractImage getImageForAnchor(long width, long height, String rId, XWPFDocument docX) throws AnalysisException{


        // Lookup the picture data here from the document relations table
        XWPFPictureData pictureData = getPictureDataByID(rId, docX);


        //AbstractImage image = images.get(id - 1);
        AbstractImage image = new AbstractImage(pictureData.getFileName())
            .withAltTag(pictureData.getFileName())
            .withRId(rId)
            .setSize(toPoints((int) width), toPoints((int) height));


        System.out.println(" --- Getting image for Anchor: name: " + image.toString());

        return image;
    }


    public XWPFPictureData getPictureDataByID(String blipID, XWPFDocument docX ) {

           POIXMLDocumentPart relatedPart = docX.getRelationById(blipID);

           if (relatedPart != null && relatedPart instanceof XWPFPictureData) {
               return (XWPFPictureData) relatedPart;
           }
           return null;
       }

    /************************************************************************
     *
     *          Extract images from the document and store them in the blob store
     *
     *
     *          // TODO: Storing is not implemented
     *
     *
     * @param docx
     */

    private void handleImages(XWPFDocument docx) {

        try{

            //get all images from the document and store them in the list piclist
            List<XWPFPictureData> documentImages = docx.getAllPictures();

            //traverse through the list and write each image to a file

            int imageNumber = 0;

            for (XWPFPictureData imageData : documentImages) {

                byte[] bytePic= imageData.getData();
                String name = imageData.getFileName();


                //BufferedImage image = ImageIO.read(new ByteArrayInputStream(bytePic));

                System.out.println("Image["+ imageNumber++ +"]: " + name + " (" + imageData.toString() + ")");
                //ImageIO.write(image, "jpg", new File("imageFromWord" + imageNumber + ".jpg"));

                for (POIXMLDocumentPart poixmlDocumentPart : imageData.getRelations()) {

                    System.out.println(" --- Relations: " + poixmlDocumentPart.toString());
                }


                AbstractImage image = new AbstractImage(name).withAltTag(name);

                images.add(image);

            }


        }catch(Exception e){

            AnalysisLogger.log( e );
        }

    }


    /***********************************************************
     *
     *          Empty cells are cells without text and without fill colour
     *
     *
     * @param cell
     * @return
     */

    private boolean isEmpty(XWPFTableCell cell) {
        return  cell.getText() != null &&
                cell.getText().equals("") &&
                cell.getColor() != null;
    }

    /****************************************************************************
     *
     *          Dividing the cell width in the excel document by 5.67 gets centimeters
     *
     *
     * @param cell
     * @return
     *
     *          //TODO: detect percentage of table width and test teh percentage figure
     *
     */

    private static final int SCALING_FACTOR = 140;   // Scale up tables to fit the frontend

    private int getWidth(XWPFTableCell cell) {



        try{

            CTTblWidth cellWidth = cell.getCTTc().getTcPr().getTcW();

            int value = cellWidth.getW().intValue();
            STTblWidth.Enum type = cellWidth.getType();

            if(type == STTblWidth.DXA){

                AnalysisLogger.log(AnalysisLogger.Level.DEBUG, "Width value is: " + value + "DXA");
                return (SCALING_FACTOR* value / (20 * 100));
            }

            if(type == STTblWidth.PCT){

                // For percentage we estimate 10000 DXA for the full table (across the document)
                // Then we divide it by 100 (for the percentage) and 20 (for points)

                return ((5 * value * SCALING_FACTOR) / 100);
            }

            return -1;

        }catch(Exception e){

            AnalysisLogger.log(AnalysisLogger.Level.ERROR, "Error getting width from cell!");

        }
        return -1;
    }

    /*********************************************************************
     *
     *          The width parameters is given as 1/256 of a characters width.
     *          Assuming about 8 points pr character gives:  36,56
     *
     *
     * @param width - width from the document
     * @return      - width in points
     */


    private int toPoints(int width) {

        return (100*width)/3656;
    }



    /*******************************************************************'
     *
     *          Get the colour value from the cell
     *
     *
     * @param cell
     * @return
     */


    private String getColour(Cell cell) {


        try{

            CellStyle style = cell.getCellStyle();
            Color colour = style.getFillForegroundColorColor();
            if(colour != null){

                //System.out.println(" *** Forground:" + style.getFillForegroundColor());
                //System.out.println(" *** Background:" + style.getFillBackgroundColor());

                String renderedColour = renderColor(colour);
                if(renderedColour != null){
                    //System.out.println(" *** Rendered:" + renderedColour);
                    return renderedColour;
                }
            }
        }catch(Exception e){

            AnalysisLogger.log(AnalysisLogger.Level.ERROR, "Error getting colour from cell!");
            e.printStackTrace();

        }

        return defaultColour;


    }


    private static String renderColor(Color color) {
       if(color instanceof HSSFColor) {
          return ((HSSFColor)color).getHexString();
       } else if(color instanceof XSSFColor) {
          return ((XSSFColor)color).getARGBHex();
       } else {
          return "(none)";
       }
    }


    private int getTextIndentation(XWPFParagraph p) {

        CTP ctp = p.getCTP();
        if(ctp == null)
            return 0;

        CTPPr ppr = ctp.getPPr();
        if(ppr == null)
            return 0;

        if(ppr.getInd() == null)
            return 0;

        BigInteger bi = ppr.getInd().getLeft();

        if(bi== null)
            return 0;

        AnalysisLogger.log(AnalysisLogger.Level.DEBUG, "Getting indentation " + bi.intValue() + " for PPR: " + p.getCTP().getPPr());

        return bi.intValue();
    }

    private void setItemLevel(int listIndentation, AbstractStructureItem item, AbstractStructureItem[] structureItems) {

        if(item != null)
            structureItems[listIndentation] = item;

        for(int i = listIndentation + 1; i < MAX_DEPTH; i++){

            structureItems[i] = null;

        }

    }

    private AbstractStructureItem getCurrentStructureItem(AbstractStructureItem[] structureItemLevel, int indentation, int lastIndentation) {

        int activeIndentation = indentation;

        if(indentation == AbstractStructureItem.NO_INDENTATION)
            activeIndentation = lastIndentation;

        AnalysisLogger.log(AnalysisLogger.Level.DEBUG, "    - current structureItem: " + (structureItemLevel[activeIndentation] == null ? "---" : structureItemLevel[activeIndentation].getStructureType().name()) +
                "(lvl: " + activeIndentation + " Last: " + lastIndentation + " )");

        return structureItemLevel[activeIndentation];

    }


    /****************************************************************************
     *
     *              looking for specific styles for cell.
     *
     *
     * @param cell
     * @return
     */

    private SimpleStyle getStyleForCell(XWPFTableCell cell) {

        return new SimpleStyle(StructureType.TEXT);

    }





    /**********************************************************************
     *
     *          Has number is used both for headings and lists
     *
     *
     *
     * @param p
     * @return
     *
     *
     *      A new numbered list starts:
     *
     *      <w:numPr>
     *          <w:ilvl w:val="0"/>
     *          <w:numId w:val="10"/>
     *      </w:numPr>
     *
     *      A heading without number
     *
     *      <w:numPr>
     *          <w:ilvl w:val="0"/>
     *          <w:numId w:val="0"/>
     *      </w:numPr>
     *
     */

    private boolean hasNumber(XWPFParagraph p) {

        return p.getNumIlvl() == null || p.getNumID().intValue() > 0;
    }

    public String getBody(){

        return body.toString();
    }

    public FileType getFileType(){

        return fileType;
    }


}
