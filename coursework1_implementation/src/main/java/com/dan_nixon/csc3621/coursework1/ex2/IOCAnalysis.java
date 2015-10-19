package com.dan_nixon.csc3621.coursework1.ex2;

import java.util.Map;
import java.util.HashMap;
import java.io.File;
import java.io.IOException;
import com.dan_nixon.csc3621.coursework1.Utils;

public class IOCAnalysis
{
  /**
   * Create anew instance of the analysis tool.
   *
   * @param tolerance Tolerance to use when comparing IoC to enumerated values
   */
  public IOCAnalysis(double tolerance)
  {
    m_tolerance = Math.abs(tolerance);
    m_plainIoC = 0.0;
    m_cipherIoC = 0.0;
  }

  /**
   * Reads a series of plain text files to create a normal plain text
   * probability distribution for alphabetical characters.
   *
   * @param files Array of files to read
   */
  public void readPlainText(File[] files) throws IOException
  {
    IndexOfCoincidence ioc = new IndexOfCoincidence();
    ioc.calculate(files);
    m_plainIoC = ioc.indexOfCoincidence();
  }

  /**
   * Reads a cipher file and creates a probability distribution for
   * alphabetical characters.
   *
   * @param file Cipher text file to read
   */
  public void readCipher(File file) throws IOException
  {
    IndexOfCoincidence ioc = new IndexOfCoincidence();
    ioc.calculate(file);
    m_cipherIoC = ioc.indexOfCoincidence();
  }

  /**
   * Gets the plain text probability distribution.
   *
   * @return Array of probability for alphabetical characters normalised to 1.0
   */
  public double getPlainTextIoC()
  {
    return m_plainIoC;
  }

  /**
   * Gets the cipher text probability distribution.
   *
   * @return Array of probability for alphabetical characters normalised to 1.0
   */
  public double getCipherTextIoC()
  {
    return m_cipherIoC;
  }

  /**
   * Finds the most likely key size from a list of enumerated values.
   *
   * @param ioc Index of coincidence
   * @return Key size
   */
  public int keySizeFromIoC(double ioc)
  {
    Map<Double, Integer> iocToKeySize = new HashMap<Double, Integer>();
    iocToKeySize.put(getPlainTextIoC(), 0);
    iocToKeySize.put(0.045, 1);
    iocToKeySize.put(0.046, 2);
    iocToKeySize.put(0.041, 2);
    iocToKeySize.put(0.043, 3);
    iocToKeySize.put(0.050, 3);
    iocToKeySize.put(0.047, 3);
    iocToKeySize.put(0.042, 4);
    iocToKeySize.put(0.039, 4);
    iocToKeySize.put(0.045, 4);
    iocToKeySize.put(0.040, 4);
    iocToKeySize.put(0.063, 5);
    iocToKeySize.put(0.068, 5);
    iocToKeySize.put(0.069, 5);
    iocToKeySize.put(0.061, 5);
    iocToKeySize.put(0.072, 5);

    for (Map.Entry<Double, Integer> e : iocToKeySize.entrySet())
    {
      double delta = Math.abs(e.getKey() - ioc);
      if (delta < m_tolerance)
        return e.getValue();
    }

    throw new RuntimeException("No enumerated key size within tolerance of IoC");
  }

  /**
   * Attempts to find the correct key size based on the index of coincidence of
   * the cipher text.
   *
   * @return Key size determined from analysis (0 indicates the text is likely
   *         to be plain text)
   */
  public int obtainKeySize()
  {
    int keySize = keySizeFromIoC(getCipherTextIoC());
    return keySize;
  }

  private double m_tolerance;
  private double m_plainIoC;
  private double m_cipherIoC;
}
