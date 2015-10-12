package aes;

/*************************************************************************************
 *
 *          Common functionality for the AES calculations
 *
 *
 *
 */
public class AESCommon {


    /**********************************************************
     *
     *          Get integer value of a hex char
     *
     *
     * @param c
     * @return
     */

    protected static int hexVal(char c) {

        return Character.digit(c, 16);

    }


    /********************************************************
     *
     *          Substitutes one block with 4 characters (one at a time)
     *
     *
     * @param block          integer 4 char block
     * @param sbox           The current sbox
     * @return               the substituted 4 char block
     */


    protected int substitute(int block, SboxInterface sbox) {

        int[][] matrix = sbox.matrix();

        int rotate, substituted = 0;
        rotate = unsign((block & 0xFF000000) >> 24);

        substituted += matrix[rotate / 16][rotate % 16] << 24;

        rotate = unsign((block & 0x00FF0000) >> 16);
        substituted += matrix[rotate / 16][rotate % 16] << 16;

        rotate = unsign((block & 0x0000FF00) >> 8);
        substituted += matrix[rotate / 16][rotate % 16] << 8;

        rotate = unsign((block & 0x000000FF) );
        substituted += matrix[rotate / 16][rotate % 16] ;

        return substituted;
    }


    /*********************************************************************
     *
     *      Compensating for the use of unsigned integer
     *
     * @param c
     * @return
     */

    private int unsign(int c) {

        if (c >=0)
            return c;
        else
            return 256+c;
    }



    protected static char convToHex(int d) {

        if (d < 10)
           return (char)(d+'0');
       else
           return (char)(d-10+'A');
    }



}
