package com.dan_nixon.csc3621.cw1.ex2;

import java.util.Map;
import java.io.File;
import java.io.IOException;
import com.dan_nixon.csc3621.cw1.Utils;
import com.dan_nixon.csc3621.cw1.ex1.FrequencyAnalysis;
import com.dan_nixon.csc3621.cw1.ex1.FrequencyCounter;
import com.dan_nixon.csc3621.cw1.ex2.IOCAnalysis;
import com.dan_nixon.csc3621.cw1.ex2.VigenereAnalysis;

public class VigenereAnalysisApp
{
  public static void main(String[] args) throws IOException
  {
    Map<String, String> options = Utils.parseCommandLine(args);

    // Load plain and cipher text
    File plainFile = new File(options.get("plain-file"));
    File cipherFile = new File(options.get("cipher-file"));
    FrequencyCounter plainCount = new FrequencyCounter();
    plainCount.count(plainFile);
    String cipherText = Utils.readFileToString(cipherFile);

    int keyLength = getKeyLength(options, plainCount, cipherText);
    System.out.println("Key length: " + keyLength);
    System.out.println();

    String key = getKey(options, plainCount, cipherText, keyLength);
    System.out.println("Key: " + key);
  }

  /**
   * Obtains the key by either manual setting, IoC analysis of entire cipher
   * text of IoC analysis of columnising the cipher text.
   *
   * @param options Map of program options
   * @param plainCount Count of plain text characters
   * @param cipherText Cipher text
   * @return Best key size
   */
  public static int getKeyLength(Map<String, String> options,
                                 FrequencyCounter plainCount,
                                 String cipherText)
  {
    int keyLength = 0;
    if (options.containsKey("key-length"))
    {
      keyLength = Integer.parseInt(options.get("key-length"));
    }
    else
    {
      // Determine tolerance for IoC matching
      double tolerance = 0.005;
      if (options.containsKey("tolerance"))
        tolerance = Double.parseDouble(options.get("tolerance"));

      // Do analysis to obtain key length
      IOCAnalysis ioca = new IOCAnalysis(tolerance);
      ioca.setPlainTextCount(plainCount);
      ioca.setCipherText(cipherText);

      System.out.println("Plain text IoC: " + ioca.getPlainTextIoC());
      System.out.println("Cipher text IoC: " + ioca.getCipherTextIoC());

      // Determine through IoC of entire cipher text and validate
      keyLength = ioca.obtainKeySize();
      System.out.println("IoC of cipher text gives key length: " + keyLength);
      if (!ioca.validateKeySize(keyLength))
      {
        System.out.println("Key length validation failed, trying brute force.");
        // Validation failed, try brute force and validate
        keyLength = ioca.obtainKeySizeBruteForce(2, 10);
        System.out.println("Brute force IoC analysis gives key length: " + keyLength);
        if (!ioca.validateKeySize(keyLength))
        {
          // Out of options to automatically determine key length
          System.out.println("Failed to automatically determine key length!");
          System.out.println("Set manually using --key-length");
          System.exit(1);
        }
      }
    }

    return keyLength;
  }

  /**
   * Obtain the key given the length of the key, cipher text and distribution
   * of plain text characters.
   *
   * @param options Map of program options
   * @param plainCount Count of plain text characters
   * @param cipherText Cipher text
   * @param keyLength Length of key
   * @return Best key found
   */
  public static String getKey(Map<String, String> options,
                              FrequencyCounter plainCount,
                              String cipherText,
                              int keyLength)
  {
    // Setup analysis
    VigenereAnalysis va = new VigenereAnalysis();
    va.setPlainTextCount(plainCount);
    va.setCipherText(cipherText);
    FrequencyAnalysis[] fas = va.frequencyAnalysis(keyLength);

    // Output probability distributions
    if (options.containsKey("verbose"))
    {
      for (int i = 0; i < fas.length; i++)
      {
        System.out.println("Key character " + i + ":");
        System.out.println(fas[i].toString());
      }
    }

    // Obtain key
    String key = va.obtainKey(fas);

    return key;
  }
}
