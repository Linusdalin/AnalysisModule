package aes;

/********************************************************'
 *
 *
 *          Common interface to be able to handle sbox and invSbox as different classes
 *
 *
 */
public interface SboxInterface {

    public int[][] matrix();

}
