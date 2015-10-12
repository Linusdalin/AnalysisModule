package document;

import net.sf.json.JSONObject;

/**
 *
 *          Info to store and display a cell.
 *
 *          TODO: Improvement: Handle borders and height
 */

public class CellInfo {

    public final int row;               // Cell Y-position
    public final int col;               // Cell X-position
    public final String colour;
    public final int width;             // Cell width
    public final int fontHeight;
    public final TableSpan span;        // Cell span x and Y (for starting cell)
    public final int border;
    public boolean wrap;                // Wrap text or not
    public int rowWidth;                // Total columns in row


    /**************************************************
     *
     *          Set default values
     *
     */

    public CellInfo(){

        this.row = -1;
        this.col = -1;
        this.colour = "FFFFFFFF";   //ARGB
        this.width = -1;
        this.fontHeight = -1;
        this.span = new TableSpan(1, 1);
        this.border = 0;
        this.wrap = true;
        this.rowWidth = -1;
    }


    public CellInfo(int col, int row, String colour, int width, int fontHeight, TableSpan span, int border, boolean wrap, int rowWidth){

        this.row = row;
        this.col = col;
        this.colour = colour;
        this.width = width;
        this.fontHeight = fontHeight;
        this.span = span;
        this.border = border;
        this.wrap = wrap;
        this.rowWidth = rowWidth;
    }

    public String toString(){

        return "Pos:(" + col + ", " + row + ") colour:" + colour + " width:" + width + "Span: "+ span.toString() + "Wrap: " + wrap;
    }

    public String toJSON() {

        JSONObject json = new JSONObject()
                .put("backgroundColour", "#" + this.colour)
                .put("colSpan", this.span.cols)
                .put("rowSpan", this.span.rows)
                .put("width", this.width)
                .put("wrap", this.wrap)

        ;
        return json.toString();

    }

}
