package aes;

import java.io.*;

/**************************************************************************'
 *
 *      AES encoder/decoder
 *
 *
 *
 */




public class AES extends AESCommon{

    private enum Direction{ENCRYPT, DECRYPT}

    private Direction mode;

    private SboxInterface sbox; // Stores all the sbox values.

    /**************************************************************'
     *
     *        Create a new AES manager
     *
     *
     * @param mode                - encrypt or decrypt
     */

    public AES(Direction mode) {

        this.mode = mode;

        if(mode==Direction.ENCRYPT){
            sbox = new Sbox();
        }
        else{
            sbox = new InvSbox();

        }

    }

    /***************************************************************
     *
     *          make the conversion
     *
     *
     * @param keyString           - the key
     * @param outputFile          - destination file
     * @throws IOException
     */

    private void convert(String keyString, String inputFile,  String outputFile) throws IOException{

        // Create a new Key from the key string

        AESKey key = new AESKey(keyString);
        key.expand(sbox);

        int[] message = readMessage(inputFile);

        // Decide operation depending on the direction (Encrypt or Decrypt).
        if (mode == Direction.ENCRYPT) {

            int[] cryptoText = encryptText(message, key);
            writeEncryptedOutput(cryptoText, outputFile);
        }
        else {

            int[] clearText = decryptText(message, key);
            writePlainFile(clearText, outputFile);
        }

    }

    private int getMessageLength(String file, Direction mode){

        int length = (int)new File(file).length();

        if(mode == Direction.DECRYPT)
            length /= 2;

        return length;

    }



    /******************************************************************
     *
     *          Read a message
     *
     * @param file               the file
     * @return                        message as an array of 4 byte ints
     * @throws IOException
     */

      public int[] readMessage(String file) throws IOException {

          int length = getMessageLength(file, mode);

          // Make space to fill out a full 4 byte long word and divide by four to get the number of ints

          int allocationLength = 16*((length+15)/16) /4;
          int[] message = new int[allocationLength];

          BufferedReader inFile = new BufferedReader(new FileReader(file));

          if (mode == Direction.ENCRYPT) {

              // Read a plain text file. Character by character
              for (int count = 0; count < length; count++) {

                  int c = inFile.read();
                  message[count/4] = c + (message[count/4] << 8);

              }

              // Pad the last block with spaces
              for (int count = length; count < 4*message.length; count++){

                  message[count/4] = (message[count/4] << 8) + (int)(' ');
              }

          }
          else {

              // Read in cipher text in one go

              String line = inFile.readLine();

              for (int count = 0;  count < line.length(); count++){

                  message[ count/8] =
                          hexVal(line.charAt(count)) +
                          (message[ count/8] << 4);
              }

          }
          inFile.close();
          return message;
      }


    /******************************************************************
     *
     *          Writes encrypted output to a file as hex string
     *
     *
     * @param message           the internal message representation
     * @param outputFile        output file name
     */

      public void writeEncryptedOutput(int[] message, String outputFile){

          try{

              BufferedWriter outFile = new BufferedWriter(new FileWriter(outputFile));
              for (int i=0; i<message.length; i++) {

                  char[] hex = new char[8];
                  int temp = message[i];

                  // Convert one int into 8 HEX chars.
                  for (int j=7; j>=0; j--) {

                      hex[j] = convToHex(temp & 15);

                      temp = temp >> 4;
                  }

                  for (int j=0; j<8; j++){

                      outFile.write(hex[j]);

                  }

                  // Advance to the next line after 64 chars have been written.
                  //if (i%8 == 7)
                  //    outFile.write('\n');
              }
              outFile.close();

          }catch(IOException e){

              System.out.println("Failed to write to file " + outputFile);

          }

      }


    /********************************************************************
     *
     *      Write a file as plain text
     *
     *
     * @param message
     * @param fail
     * @throws IOException
     */


    public void writePlainFile(int[] message, String fail) throws IOException {

        BufferedWriter outFile = new BufferedWriter(new FileWriter(fail));

        for (int i=0; i<message.length; i++) {


            int temp = message[i];
            char[] letters = new char[4];

            // Convert an int into 4 chars.
            for (int j=3; j>=0; j--) {

                letters[j] = (char)(temp & 255);
                temp = temp >> 8;

            }

          for (int j=0; j<4; j++)
            outFile.write( letters[j] );
        }
        outFile.close();
    }


    /*************************************************************
     *
     *          Add key to the message
     *
     * @param message         - the message
     * @param key             - key values
     * @param round           - round to define the offset
     *
     *
     *          xor message with the part of the key defined by the round
     *
     */


    private void addRoundKey(int[] message, AESKey key, int round) {

        for (int i=0; i< message.length; i++){

            message[i] = message[i] ^ key.getValues()[ (4 * round + i) % 4];
        }
    }


    /**********************************************************
     *
     *          SBox substitution of the message with the predefined sbox for the direction
     *
     * @param message       - message to substitute
     */



    private void sboxSubstitution(int[] message) {

        for (int i=0; i< message.length; i++){

            message[i] = substitute(message[i], sbox);

        }
    }

    /**************************************************************
     *
     *          Shift the rows
     *
     * @param message
     */

    private void shiftRows(int[] message) {

        for (int b = 0; b < message.length/4; b++) {

            // Create a row shift matrix for the block of characters
            AESmatrix temp = new AESmatrix(message, 4 * b);

            // Shift the rows
            temp = temp.shiftRows();
            temp.updateMessage(message, 4 * b);

        }
    }


    /*********************************************************
     *
     *          Shift back
     *
     *
     * @param message       -the crypto
     */

    private void invShiftRows(int[] message) {

        // Go through each block.
        for (int b = 0; b < message.length/4; b++) {

            // Create a row shift matrix for the block of characters
            AESmatrix temp = new AESmatrix(message, 4*b);

            temp = temp.invShiftRows();
            temp.updateMessage(message, 4 * b);
        }
    }

    /**************************************************************
     *
     *          Mix columns
     *
     *
     * @param message    - message to encrypt
     */



    private void mixCols(int[] message) {

        // Loop through each block to mix block by block

        for (int block = 0; block < message.length/4; block++) {

            // Create an AESmatrix

            AESmatrix temp = new AESmatrix(message, 4*block);

            temp = temp.mixCols();
            temp.updateMessage(message, 4 * block);

        }
    }

    /**************************************************************
     *
     *
     *          Reverse mix
     *
     * @param message     - message to decrypt
     */



    private void invMixCols(int[] message) {

    // Loop through each block.

        for (int block=0; block<message.length/4; block++) {

            AESmatrix temp = new AESmatrix(message, 4*block);

            temp = temp.invMixCols();
            temp.updateMessage(message, 4 * block);
        }
    }

    /********************************************************************
     *
     *          Run the decryption. This is the inverse of the encryption;
     *          Everything is done in the reverse order
     *
     * @param message      - the cipher text to decrypt
     * @param key          - the expanded key
     * @return             - decrypted message
     */


      public int[] decryptText(int[] message, AESKey key) {


          addRoundKey(message, key, 10);


          for (int i=9; i>=0; i--) {

              invShiftRows(message);
              sboxSubstitution(message);
              addRoundKey(message, key, i);

              // Skip this the last round

              if(i > 0)
                invMixCols(message);

          }

          return message;

      }

    /********************************************************************
     *
     *          main encryption cycle
     *
     *
     * @param message      - message to encrypt
     * @param key          - expanded key
     * @return             - encrypted message
     */

  public int[] encryptText(int[] message, AESKey key) {

      addRoundKey(message, key, 0);

      for (int i = 1; i <= 10; i++) {

          sboxSubstitution(message);
          shiftRows(message);

          // Skip this the last round
          if(i != 10)
              mixCols(message);

          addRoundKey(message, key, i);
    }

      return message;

  }

    /*************************************************************
     *
     *          Test main
     *
     *
     * @param args
     * @throws IOException
     */

    public static void main(String[] args) throws IOException {

        // This is just for testing. Round trip encoding verifying that the text is back

        String input = "text.txt";
        String encrypted = "aes.txt";
        String back = "back.txt";
        String key = "1234567890ABCDEF1234567890ABCDEF";


        // Create the new AES object.

        AES aesManager = new AES(Direction.ENCRYPT);
        aesManager.convert(key, input, encrypted);

        AES aesManager2 = new AES(Direction.DECRYPT);
        aesManager2.convert(key, encrypted, back);


    }



}
