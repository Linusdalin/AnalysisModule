package document;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-12-03
 * Time: 15:39
 * To change this template use File | Settings | File Templates.
 */
public class TableSpan {


    public final int rows;
    public final int cols;

    public TableSpan(int rows, int cols){

        this.rows = rows;
        this.cols = cols;
    }

    public String toString(){

        return "colSpan:" + cols + " rowSpan:" + rows;
    }
}
