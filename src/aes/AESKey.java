package aes;

/************************************************************
 *
 *          The key class
 *
 *
 */
public class AESKey extends AESCommon{


    private int[] keyValues;
    private static final int rcon[] = {0x01,0x02,0x04,0x08,0x10,0x20,0x40,0x80,0x1B,0x36};


    /***********************************************
     *
     *          Create a key from a key string of hex chars
     *
     * @param keyString
     */



    public AESKey(String keyString) {

        // Check that the key length is correct.
        if (keyString.length() != 32)
            throw new RuntimeException("Key should be 32 characters (128 bit)");

        keyValues = new int[44];   // Add 10 for expanding the key

        for (int i = 0; i < keyString.length(); i++){

            keyValues[i/8] = (keyValues[i/8] << 4) + hexVal(keyString.charAt(i));

        }
    }

    public int[] getValues(){

        return keyValues;
    }

    /****************************************************
     *
     *          Expand the key
     *
     *
     * @param sbox      - the sbox for transformation
     *
     *          This uses precalculated rcon values
     *
     */


      public void expand(SboxInterface sbox) {


          int temp;

          // We are applying the rcon on the tree following bytes of the key. Shift 3 bytes first

          for (int  i = 0; i < 10; i++){

              rcon[i] = (rcon[i] << 24);
          }

          for (int i=4; i < 44; i++) {

              temp = keyValues[i-1];

              if ((i % 4) == 0) {

                  temp = (temp << 8) + (((temp & 0xFF000000) >> 24) & 0x000000FF);
                  temp = substitute(temp, sbox);

                  temp = temp ^ rcon[i/4 - 1];

            }
            keyValues[i] = keyValues[i-4]^temp; // Final xor.

          }
      }

}
