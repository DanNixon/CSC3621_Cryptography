package com.dan_nixon.csc3621.coursework1.ex2;

import java.io.File;
import java.io.IOException;
import com.dan_nixon.csc3621.coursework1.Utils;
import com.dan_nixon.csc3621.coursework1.ex1.FrequencyCounter;

public class IndexOfCoincidence
{
  public IndexOfCoincidence()
  {
    m_distribution = null;
    m_indexOfCoincidence = 0.0;
  }

  /**
   * Return the probability distribution used in calculation.
   *
   * @return Probability distribution
   */
  public double[] distribution()
  {
    return m_distribution;
  }

  /**
   * Return the calculated index of coincidence.
   *
   * @return Calculated index (zero if not yet calculated)
   */
  public double indexOfCoincidence()
  {
    return m_indexOfCoincidence;
  }

  /**
   * Calculate the index of coincidence for a given file using the normalised
   * probability distribution of English alphabetical characters.
   *
   * @param file The file to read
   */
  public void calculate(File file) throws IOException
  {
    FrequencyCounter fc = new FrequencyCounter();
    fc.count(file);
    m_distribution = fc.normalise();

    //TODO
    for (double p : m_distribution)
      m_indexOfCoincidence += p * p;
  }

  private double[] m_distribution;
  private double m_indexOfCoincidence;
}
