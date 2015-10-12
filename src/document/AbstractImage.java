package document;

import net.sf.json.JSONObject;

/**
 *          Representation of an image in the parsing
 *
 */
public class AbstractImage {

    public static final String ImageDirectory   = "_uplImg07126/";  // This has an obscure name that makes direct access more difficult
    public static final String ImageServlet     = "Image";         // Access to the image servlet

    public enum HAlignment {LEFT, CENTER, RIGHT}

    private String name;
    private String alt;
    private int width = 0;
    private int height = 0;
    private HAlignment hAlignment;
    private String rid = null;

    public AbstractImage(String name){

        this.name = name;
        this.alt = name;
        this.hAlignment = HAlignment.CENTER;    // Default setting
    }

    public AbstractImage withAltTag(String tag){

        this.alt = tag;
        return this;
    }

    public AbstractImage withRId(String rid){

        this.rid = rid;
        return this;
    }


    /***********************************************************
     *
     *          Setting the image size
     *
     * @param w     - width
     * @param h     - height
     * @return      - self
     */

    public AbstractImage setSize(int w, int h){

        this.width = w;
        this.height = h;
        return this;
    }

    //TODO: Not implemented

    public AbstractImage setHAlign(HAlignment alignt){

        this.hAlignment = alignt;
        return this;
    }

    /*****************************************************************
     *
     *      Get the link to the actual image
     *
     * @param imageServer           - The server holding images
     * @param documentQualifier     - qualifier for the document when storing the image
     * @return                      - html
     *
     *          Example:
     *                  getRetreivelTag("localhost", "myDoc")
     *
     *          <center>
     *              <img src="localhost/uploaded/myDoc/image.jpg" width="X" height="Y" alt="image.jpg" />
     *          </center>
     *
     *          //TODO: positioning not implemented. Hardcoded to center
     */

    public String getRetrievalTag(String imageServer, String documentQualifier) {

        return "<center><img src=\""+ imageServer +"/"+ getImageAccess(name, documentQualifier) + "\" width=\"" + width + "\" height=\"" + height + "\" alt =\""+ alt+"\" /></center>";
    }


    public String getImageFile(String imageServer, String documentQualifier){

        //Escape for localhost. Removing the document qualifier as it is only for testing

        if(imageServer.contains("localhost"))
            documentQualifier = "allDocuments";

        return ImageDirectory + documentQualifier + "/" + name;
    }

    private String getImageAccess(String name, String documentQualifier){

        return ImageServlet + "?image=" + name + "&document=" + documentQualifier + "&session=$(_SESSION)";
    }


    public String toString(){

        return "Image: " + name + "(rid: " + rid + ")";
    }


    public JSONObject toJSON(){

        return new JSONObject()
                .put("name", name)
                .put("large", name)        // TODO: large image not implemented
                .put("alt", alt)
                .put("height", height)
                .put("width", width)
                .put("align", "center")    // TODO: alignment not implemented

        ;

    }

}
