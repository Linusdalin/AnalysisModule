package document;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-05-13
 * Time: 19:06
 * To change this template use File | Settings | File Templates.
 *
 *
 *
 */

public class AbstractFragment {

    private String fragmentBody;
    private AbstractStructureItem structureItem = null;
    private List<AbstractImage> images = new ArrayList<>();

    private CellInfo cellinfo = null;
    private StructureType type;

    private List<String> keywords;


    public CellInfo getCellInfo() {
        return cellinfo;
    }


    private int indentation = 0;

    public AbstractFragment(String text) {


        // These are Windows-1252 characters that we want to remove.
        //TODO: Optimize this and go through the chars only once


        String washedText = text
                .replace( (char)145, (char)'\'')
                .replace( (char)8216, (char)'\'') // left single quote
                .replace( (char)146, (char)'\'')
                .replace( (char)8217, (char)'\'') // right single quote
                .replace( (char)147, (char)'\"')
                .replace( (char)148, (char)'\"')
                .replace( (char)8220, (char)'\"') // left double
                .replace( (char)8221, (char)'\"') // right double
                .replace( (char)8211, (char)'-' ) // em dash??
                .replace( (char)150, (char)'-' );


        this.fragmentBody = washedText;
    }

    public AbstractFragment setStructureParent(AbstractStructureItem item){

        this.structureItem = item;
        return this;
    }

    public AbstractStructureItem getStructureItem(){

        return this.structureItem;
    }


    public String getBody() {

        return fragmentBody;
    }



    public AbstractFragment setIsCell(CellInfo cellInfo) {

        this.type = StructureType.TABLE;
        this.cellinfo = cellInfo;
        return this;
    }



    public AbstractFragment setKeywords(List<String> keywords) {

        this.keywords = keywords;
        return this;
    }


    public List<String> getKeywords() {

        return keywords;
    }



    public AbstractFragment setIndentation(int level) {

        this.indentation = level;
        return this;
    }


    public long getIndentation() {

        return indentation;
    }

    public AbstractFragment setStyle(StructureType type ){

        this.type = type;
        return this;
    }

    public StructureType getStyle( ){

        return this.type;
    }


    public List<AbstractImage> getImages() {
        return images;
    }

    public void setImages(List<AbstractImage> imagesForFragment) {
        images = imagesForFragment;
    }
}

