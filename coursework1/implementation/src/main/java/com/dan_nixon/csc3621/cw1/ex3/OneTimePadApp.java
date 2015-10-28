package com.dan_nixon.csc3621.cw1.ex3;

import java.util.Map;
import java.io.File;
import java.io.IOException;
import com.dan_nixon.csc3621.cw1.Utils;
import com.dan_nixon.csc3621.cw1.ex3.OneTimePadGenerator;

public class OneTimePadApp
{
  /**
   * Main application entry point.
   *
   * Usage:
   * Generate pad: --generate-pad-file pad.bin --length 256 --seed 12345
   * Generate pad (clock seed): --generate-pad-file pad.bin --length 256
   * Encrypt: --encrypt --message-file message.txt --pad-file pad.bin --cipher-file cipher.bin
   * Decrypt: --decrypt --pad-file pad.bin --cipher-file cipher.bin
   *
   * @param args Program arguments
   */
  public static void main(String[] args) throws IOException
  {
    Map<String, String> options = Utils.parseCommandLine(args);

    if (options.containsKey("generate-pad-file"))
    {
      File padFile = new File(options.get("generate-pad-file"));
      runGeneratePadMode(options, padFile);
    }
    else if (options.containsKey("pad-file"))
    {
      File padFile = new File(options.get("pad-file"));
      File cipherFile = new File(options.get("cipher-file"));

      OneTimePadEncryption otpe = new OneTimePadEncryption(padFile);

      if (options.containsKey("encrypt"))
      {
        File plainFile = new File(options.get("message-file"));
        String message = Utils.readFileToString(plainFile);
        byte[] cipherData = otpe.encrypt(message);
        Utils.writeBinaryFile(cipherFile, cipherData);
      }
      else if (options.containsKey("decrypt"))
      {
        byte[] cipherData = Utils.readBinaryFile(cipherFile);
        String message = otpe.decrypt(cipherData);
        System.out.println(message);
      }
    }
  }

  /**
   * Handles generating a new one time pad.
   *
   * @param options Options parsed from command line
   * @param padFile File to save data to
   */
  public static void runGeneratePadMode(Map<String, String> options, File padFile)
    throws IOException
  {
    int length = Integer.parseInt(options.get("length"));
    byte[] padData = null;
    if (options.containsKey("seed"))
    {
      int seed = Integer.parseInt(options.get("seed"));
      padData = OneTimePadGenerator.generateOneTimePad(seed, length);
    }
    else
    {
      padData = OneTimePadGenerator.generateOneTimePad(length);
    }

    Utils.writeBinaryFile(padFile, padData);
  }
}
