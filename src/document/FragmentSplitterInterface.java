package document;


import java.util.List;

/**
 *
 *      Interface for all fragment splitters
 *
 *
 */
public interface FragmentSplitterInterface {


    /********************************************************
     *
     *      Splits a text into fragments.
     *
     * @return - list of the fragments as text strings
     */

    public List<AbstractFragment> getFragments();

    /********************************************************
     *
     *      Get all comments from the document.
     *
     * @return - list of the abstract comments
     */

    public List<AbstractComment> getComments();

    /********************************************************
     *
     *      Get all structure Items
     *
     *
     * @return - list of the clauses as text strings
     */
    public List<AbstractStructureItem> getStructureItems();


    public String getDocumentTitle();


    /*******************************************************
     *
     *      Get the entire body text (for language
     *      and document type analysis)
     *
     * @return - raw text from the document
     */

    public String getBody();

}
