package com.dan_nixon.csc3621.coursework1.ex1;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.StringBuilder;
import java.lang.IllegalArgumentException;
import com.dan_nixon.csc3621.coursework1.Utils;

public class FrequencyCounter
{
  public FrequencyCounter()
  {
    m_occurrences = new int[26];
  }

  /**
   * Counts all occurrences of English alphabetical characters in a file.
   *
   * Capitalisation is ignored.
   *
   * @param file File to read
   */
  public void count(File file) throws IOException
  {
    FileInputStream fs = new FileInputStream(file);
    while (fs.available() > 0) {
      char c = (char) fs.read();
      try
      {
        // Increment count of this character
        m_occurrences[Utils.getIndexFromChar(c)]++;
      }
      catch (IllegalArgumentException ex)
      {
        // Ignore this character
        continue;
      }
    }
  }

  /**
   * Gets the number of recorded occurrences of a character.
   *
   * @param c Character to get occurrences of
   * @return Number of occurrences
   */
  public int occurrences(char c) throws IllegalArgumentException
  {
    return m_occurrences[Utils.getIndexFromChar(c)];
  }

  /**
   * Calculates the total number of characters recorded.
   *
   * @return Total recorded character count
   */
  public int totalCount()
  {
    int total = 0;
    for (int i = 0; i < 26; i++)
      total += m_occurrences[i];
    return total;
  }

  /**
   * Creates a normalised probability distribution by dividing the occurrence
   * of each character by the total count for all characters.
   *
   * @return Probability distribution normalised to 1.0
   */
  public double[] normalise()
  {
    double total = (double) totalCount();
    double[] norm = new double[26];

    for (int i = 0; i < 26; i++)
      norm[i] = ((double) m_occurrences[i]) / total;

    return norm;
  }

  @Override
  public String toString()
  {
    StringBuilder sb = new StringBuilder();

    sb.append("FrequencyCounter[");
    String prefix = "";
    for (int i = 97; i < 123; i++)
    {
      char c = (char) i;
      int n = occurrences(c);
      sb.append(prefix);
      prefix = ",";
      sb.append(c);
      sb.append("=");
      sb.append(n);
    }
    sb.append("]");

    return sb.toString();
  }

  private int[] m_occurrences;
}
