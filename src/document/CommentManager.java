package document;

import log.AnalysisLogger;
import org.apache.poi.xwpf.usermodel.XWPFComment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTMarkup;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**********************************************************************
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2015-08-30
 * Time: 12:42
 * To change this template use File | Settings | File Templates.
 */

public class CommentManager {

    private List<XWPFRun> runs;
    private final int fragmentId;
    private final XWPFComment[] documentComments;

    CommentManager(List<XWPFRun> runs, int fragmentId, XWPFComment[] documentComments){

        this.runs = runs;
        this.fragmentId = fragmentId;
        this.documentComments = documentComments;
    }


    /***************************************************************
     *
     *              matching comments with the anchors found in the document
     *
     *
     *
     * @return       - list of comments from the paragraph
     *
     *              // TODO: Capping at 256 anchors per fragment.
     */

    List<AbstractComment> handleCommentsInParagraph(){

        List<AbstractComment> comments = new ArrayList<>();
        String[] anchor = new String[256];
        //System.out.println("  !!! Going through the runs to find anchor ");

        for(XWPFRun run : runs){

            CTR ctr = run.getCTR();

            //System.out.println("     !  Run: " + run.toString());
            int commentNo = getMarkup(run);

            if(commentNo != -1){

                //System.out.println("     !  Comment: " + commentNo);

                if(anchor[commentNo] == null){

                    //System.out.println("     !  New Anchor for comment: " + commentNo);
                    anchor[commentNo] = "";

                }
            }
            else{

                if(run.getText(0) != null){

                    for (int i = 0; i < 256; i++) {

                        if(anchor[i] != null){
                            anchor[i] += " " + run.getText( 0 );
                            AnalysisLogger.log(AnalysisLogger.Level.DEBUG, "     - Adding " + run.getText( 0 ) + " to anchor["+ i +"]");
                        }
                    }
                }


            }

            // Look for comment references to create comments

            for(int i = 0; i < ctr.getCommentReferenceArray().length; i++){

                CTMarkup markup = ctr.getCommentReferenceArray(i);
                int id = markup.getId().intValue();
                comments.add(createComment(markup, documentComments, fragmentId, anchor[ id ]));           // Add it to the last created item

            }



        }


        return comments;

    }


    private int getMarkup(XWPFRun run) {

        if(run == null || run.getText(0) == null)
            return -1;

        if(run.getText(0).startsWith(RunManager.MARKUP_TOKEN)){


            int commentId = new Integer(run.getText(0).substring(RunManager.MARKUP_TOKEN.length()));
            if(commentId > 127)
                return -1;
            return commentId;

        }

        return -1;
    }




        /*****************************************************************************
         *
         *
         *          Create the comment from the markup in the document
         *
         *
         * @param markup
         * @param xwpfComments
         * @param fragmentId
         * @param anchorText         - the marked text in the fragment
         * @return                   - an abstract comment to pass back to analysis
         */

        private AbstractComment createComment(CTMarkup markup, XWPFComment[] xwpfComments, int fragmentId, String anchorText){

            for(XWPFComment comment : xwpfComments){

                if(new BigInteger(comment.getId()).equals(markup.getId())){

                    System.out.println("Creating abstract comment. id=" + markup.getId() + " Anchor: \"" + anchorText + "\" text: " + comment.getText() + "");
                    System.out.println("*** Comment: " + comment.getText());
                    return new AbstractComment("Annotation", anchorText, comment.getText(), fragmentId, comment.getText().indexOf(anchorText), anchorText.length());


                }
            }


            return new AbstractComment("type", "pattern", "unknown", fragmentId, 0, 0);

        }


    /***************************************************************************************'
     *
     * @param document
     *
     */


    private void listComments(XWPFDocument document){

        XWPFComment[] comments = document.getComments();

        for(XWPFComment comment : comments){

            System.out.println("***********************************'\nFound a comment!");
            System.out.println("Author: " + comment.getAuthor());
            System.out.println("Text: " + comment.getText());
            System.out.println("Id: " + comment.getId());

        }

    }



}
