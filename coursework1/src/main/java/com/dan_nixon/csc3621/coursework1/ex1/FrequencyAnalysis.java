package com.dan_nixon.csc3621.coursework1.ex1;

import java.io.File;
import java.io.IOException;
import com.dan_nixon.csc3621.coursework1.Utils;

public class FrequencyAnalysis
{
  public FrequencyAnalysis()
  {
    m_plainDistrib = null;
    m_cipherDistrib = null;
  }

  /**
   * Reads a series of plain text files to create a normal plain text
   * probability distribution for alphabetical characters.
   *
   * @param files Array of files to read
   */
  public void readPlainText(File[] files) throws IOException
  {
    FrequencyCounter fc = new FrequencyCounter();
    for (int i = 0; i < files.length; i++)
      fc.count(files[i]);
    m_plainDistrib = fc.normalise();
  }

  /**
   * Reads a cipher file and creates a probability distribution for
   * alphabetical characters.
   *
   * @param file Cipher text file to read
   */
  public void readCipher(File file) throws IOException
  {
    FrequencyCounter fc = new FrequencyCounter();
    fc.count(file);
    m_cipherDistrib = fc.normalise();
  }

  /**
   * Gets the plain text probability distribution.
   *
   * @return Array of probability for alphabetical characters normalised to 1.0
   */
  public double[] getPlainTextDistribution()
  {
    return m_plainDistrib;
  }

  /**
   * Gets the cipher text probability distribution.
   *
   * @return Array of probability for alphabetical characters normalised to 1.0
   */
  public double[] getCipherTextDistribution()
  {
    return m_cipherDistrib;
  }

  /**
   * Performs brute force analysis to check for a shift (rotational) cipher
   * and returns the most likely shift offset (key).
   *
   * @return Most likely shift offset (key)
   */
  public int rotationAnalysis()
  {
    int bestFitRotation = 0;
    double bestFitDiff = Double.MAX_VALUE;

    for (int i = 1; i < 26; i++)
    {
      double[] rotatedPlainDistrib = Utils.rotateArray(m_plainDistrib, i);

      double totalDiff = 0.0;
      for (int j = 0; j < 26; j++)
        totalDiff += Math.abs(m_cipherDistrib[j] - rotatedPlainDistrib[j]);

      if (totalDiff < bestFitDiff)
      {
        bestFitRotation = i;
        bestFitDiff = totalDiff;
      }
    }

    return bestFitRotation;
  }

  private double[] m_plainDistrib;
  private double[] m_cipherDistrib;
}
