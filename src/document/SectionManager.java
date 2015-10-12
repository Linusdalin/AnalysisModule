package document;

import log.AnalysisLogger;

/****************************************************************************************
 *
 *          Handling merge of sections
 *
 *          NOTE: This is turned off to allow for injecting comments back in the original document for export
 */


public class SectionManager {

    int currentSize = 0;
    StringBuffer currentSectionText = new StringBuffer();
    int currentIndentation = 0;
    AbstractStructureItem currentStrictureItem;
    private static final int NEW_FRAGMENT_THRESHOLD = 1;  // Grouping paragraphs works badly with injecting text back

    SectionManager(){

    }


    public AbstractFragment storeToFragment(String paragraphText, AbstractStructureItem thisStructureItem, int thisIndentation){

        if(currentSize > 0){
            currentSectionText.append("<br/>");
        }
        else{

            AnalysisLogger.log(AnalysisLogger.Level.DEBUG, "Grouper: Storing structure item and indentation " + thisIndentation);
            currentStrictureItem = thisStructureItem;
            currentIndentation = thisIndentation;
            currentSectionText = new StringBuffer();
        }


        currentSectionText.append(paragraphText);

        currentSize += paragraphText.length();

        if(currentSize > NEW_FRAGMENT_THRESHOLD || thisStructureItem != currentStrictureItem || thisIndentation != currentIndentation){

            return releaseFragment();

        }
        else{

            AnalysisLogger.log(AnalysisLogger.Level.DEBUG, "Grouper: Holding text fragment back to build bigger fragment");
        }


        return null;

    }


    public AbstractFragment releaseFragment(){

        if(currentSize == 0){
            AnalysisLogger.log(AnalysisLogger.Level.DEBUG, "Grouper: Trying to release fragment back, but it is empty");
            return null;
        }

        AnalysisLogger.log(AnalysisLogger.Level.DEBUG, "Grouper: Releasing fragment back: " + currentSectionText.toString());


        AbstractFragment fragment = new AbstractFragment(currentSectionText.toString())
                 .setStyle(StructureType.TEXT)
                 .setStructureParent(currentStrictureItem)
                 .setIndentation(currentIndentation);

        currentSize = 0;

        return fragment;


    }

}
