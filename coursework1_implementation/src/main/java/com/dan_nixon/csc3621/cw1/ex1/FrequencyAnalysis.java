package com.dan_nixon.csc3621.cw1.ex1;

import java.io.File;
import java.io.IOException;
import com.dan_nixon.csc3621.cw1.Utils;

public class FrequencyAnalysis
{
  public FrequencyAnalysis()
  {
    m_plainDistrib = null;
    m_cipherDistrib = null;
  }

  /**
   * Loads a character count for plain text.
   *
   * @param plainCount Plain text count
   */
  public void setPlainTextCount(FrequencyCounter plainCount)
  {
    m_plainDistrib = plainCount.normalise();
  }

  /**
   * Loads a character count for cipher text.
   *
   * @param cipherCount Cipher text count
   */
  public void setCipherTextCount(FrequencyCounter cipherCount)
  {
    m_cipherDistrib = cipherCount.normalise();
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

    for (int i = 0; i < 26; i++)
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

  @Override
  public String toString()
  {
    StringBuilder sb = new StringBuilder();
    sb.append("PLAINTEXT\t\t-   -\tCIPHERTEXT\n");
    for (int i = 0; i < m_plainDistrib.length; i++)
    {
      sb.append(m_plainDistrib[i]);
      sb.append("\t- ");
      sb.append(Utils.getCharFromIndex(i));
      sb.append(" -\t");
      sb.append(m_cipherDistrib[i]);
      sb.append("\n");
    }
    return sb.toString();
  }

  private double[] m_plainDistrib;
  private double[] m_cipherDistrib;
}
