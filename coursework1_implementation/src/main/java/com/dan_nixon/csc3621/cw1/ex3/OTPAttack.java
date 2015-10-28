package com.dan_nixon.csc3621.cw1.ex3;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import com.dan_nixon.csc3621.cw1.Utils;
import com.dan_nixon.csc3621.cw1.ex1.FrequencyCounter;

public class OTPAttack
{
  /**
   * Loads cipher files to a 2D array of bytes.
   *
   * @param files Files to load
   * @return Array of byte arrays
   */
  public static byte[][] loadCiphersFromFiles(File[] files) throws IOException
  {
    byte[][] ciphers = new byte[files.length][];
    for (int i = 0; i < files.length; i++)
      ciphers[i] = Utils.readBinaryFile(files[i]);
    return ciphers;
  }

  /**
   * Initialise a new OTPAttack.
   *
   * @param ciphers Array of byte arrays containing test ciphers
   */
  public OTPAttack(byte[][] ciphers)
  {
    m_ciphers = ciphers;
  }

  /**
   * Initialise a new OTPAttack.
   *
   * @param cipherFiles Array of cipher files to load
   */
  public OTPAttack(File[] cipherFiles) throws IOException
  {
    m_ciphers = loadCiphersFromFiles(cipherFiles);
  }

  /**
   * Gets an ASCII representation of the target cipher data XORed with each of
   * the analysis cipher data.
   *
   * This data can be used for manual inspection and automated plain text
   * recovery.
   *
   * @param cipher Cipher data to cryptanalyse
   * @return Array of analysis strings
   */
  public String[] getAnalysisStrings(byte[] cipher)
  {
    String[] analysisStrings = new String[m_ciphers.length];
    int n = 0;

    for (int i = 0 ; i < m_ciphers.length; i++)
    {
      if (Arrays.equals(cipher, m_ciphers[i]))
        continue;

      byte[] xor = Utils.arrayXor(cipher, m_ciphers[i]);
      analysisStrings[n] = Utils.byteArrayToString(xor);
      n++;
    }

    String[] result = new String[n];
    System.arraycopy(analysisStrings, 0, result, 0, n);
    return result;
  }

  /**
   * Retrieve as much of the plain text message as possible given the data
   * obtained through analysis of the XOR of the target cipher with analysis
   * ciphers.
   *
   * Unknown characters are represented by a '-'.
   *
   * @param analysisStrings Cryptanalysis strings
   * @param cipherLength Length of the original cipher data
   * @return Best guess of plain text message
   */
  public String guessPlainTextFromAnalysis(String[] analysisStrings,
                                           int cipherLength)
  {
    char[] messageChars = new char[cipherLength];

    // Iterate over characters in the cipher message
    for (int i = 0; i < cipherLength; i++)
    {
      StringBuilder sb = new StringBuilder();

      // Iterate over analysis strings
      for (int j = 0; j < analysisStrings.length; j++)
      {
        if (analysisStrings[j].length() > i)
          sb.append(analysisStrings[j].charAt(i));
      }

      // Do frequency counting over characters in same position over all
      // analysed ciphers
      FrequencyCounter fc = new FrequencyCounter();
      fc.count(sb.toString());

      // In the case that there are no characters recorded in this column,
      // unable to determine the plain text character
      if (fc.totalCount() == 0)
      {
        messageChars[i] = '-';
        continue;
      }

      double[] norm = fc.normalise();

      // Find most frequent character in count
      int normMax = 0;
      for (int j = 1; j < norm.length; j++)
      {
        if (norm[j] > norm[normMax])
          normMax = j;
      }

      // If the majority is all one character then the plain text message is
      // likely to be that character
      if (norm[normMax] >= 0.95)
        messageChars[i] = Utils.getCharFromIndex(normMax);
      // Otherwise it is likely to be a space
      else
        messageChars[i] = ' ';
    }

    return new String(messageChars);
  }

  private byte[][] m_ciphers;
}
