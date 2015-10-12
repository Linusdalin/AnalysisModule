package document;

/**********************************************************************
 *
 *          The current chapter/list number for a specific
 *          numbering in the document
 *
 */

public class ListNumbering {

    public int[] chapterLevels = { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};

    ListNumbering(){

    }

    public void setStartValues(int[] startValues, int level) {

        // Fill up start values until level.

        for(int i= 0; i <= level; i++){

            if(chapterLevels[i] == -1){
                //System.out.println("    - Setting start value for level "+ i +" to " + startValues[i]);
                chapterLevels[i] = startValues[i];
            }
            //else
                //System.out.println("    - Ignoring level "+ i +" value is " + chapterLevels[i]);
        }

    }



    public String toString(){

        return toString(10);
    }

    public String toString(int level) {
        String output = "ListNumbering:";

        for (int i = 0; i <= level && i < 10; i++) {
              output += chapterLevels[i] + ", ";
        }

        return output;

    }

    //When upping a chapter level all sublevels should reset

    public void resetFrom(int level, int[] startValue) {

        for (int i = level+1; i < startValue.length && i < 10; i++) {
              chapterLevels[i] = -1;
              //chapterLevels[i] = startValue[i];
        }

    }
}
