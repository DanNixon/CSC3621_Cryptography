package com.dan_nixon.csc3621.coursework1.ex2;

import java.util.Map;
import java.util.HashMap;
import java.io.File;
import java.io.IOException;
import com.dan_nixon.csc3621.coursework1.ex1.FrequencyCounter;

public class IOCAnalysis
{
  public static double calculateIndexOfCoincidence(FrequencyCounter count)
  {
    int[] counts = count.occurrences();
    double total = count.totalCount();

    double num = 0.0;
    for (int i = 0; i < 26; i++)
    {
      double c = counts[i];
      num += c * (c - 1);
    }

    double denom = total * (total - 1);
    double indexOfCoincidence = num / denom;

    return indexOfCoincidence;
  }

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
    m_cipherText = "";
  }

  /**
   * Sets the normal plain text probability distribution for alphabetical
   * characters using counts from a FrequencyCounter.
   *
   * @param plainCount Count of plain text characters
   */
  public void setPlainTextCount(FrequencyCounter plainCount)
  {
    m_plainIoC = calculateIndexOfCoincidence(plainCount);
  }

  /**
   * Sets the cipher text.
   *
   * @param cipherText Cipher text
   */
  public void setCipherText(String cipherText)
  {
    m_cipherText = cipherText;
    FrequencyCounter fc = new FrequencyCounter();
    fc.count(cipherText);
    m_cipherIoC = calculateIndexOfCoincidence(fc);
  }

  /**
   * Gets the plain text index of coincidence.
   *
   * @return Index of coincidence
   */
  public double getPlainTextIoC()
  {
    return m_plainIoC;
  }

  /**
   * Gets the cipher text index of coincidence.
   *
   * @return Index of coincidence
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
    iocToKeySize.put(0.0449443523561, 1);
    iocToKeySize.put(0.0457833618884, 2);
    iocToKeySize.put(0.0435885364312, 3);
    iocToKeySize.put(0.0474962292609, 4);
    iocToKeySize.put(0.0393612078978, 5);
    iocToKeySize.put(0.0471437059672, 6);
    iocToKeySize.put(0.0909922589726, 7);
    iocToKeySize.put(0.0461858974359, 8);
    iocToKeySize.put(0.0407804755631, 9);
    iocToKeySize.put(0.0361152882206, 10);
    iocToKeySize.put(0.0491603339901, 11);
    iocToKeySize.put(0.0512663398693, 12);
    iocToKeySize.put(0.0446886446886, 13);
    iocToKeySize.put(0.0988487702773, 14);
    iocToKeySize.put(0.0334554334554, 15);

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

  /**
   * Attempts to find the correct key size by columising the cipher text by an
   * assumed key size and calculating the index of coincidence for each column.
   *
   * @param lower Lower bound of the key size search space
   * @param upper Upper bound of the key size search space
   * @return First valid key size found
   */
  public int obtainKeySizeBruteForce(int lower, int upper)
  {
    for (int i = lower; i <= upper; i++)
    {
      if (validateKeySize(i))
        return i;
    }

    throw new RuntimeException("No valid key found");
  }

  /**
   * Checks if a key size is valid based on the index of coincidence of the
   * cipher text when split into n columns, where n is the assumed key size.
   *
   * @param keySize Assumed key size
   * @return True if key size is valid
   */
  public boolean validateKeySize(int keySize)
  {
    String[] strings = VigenereAnalysis.splitStringIntoKeyedStrings(m_cipherText, keySize, false);

    for (int i = 0; i < strings.length; i++)
    {
      FrequencyCounter fc = new FrequencyCounter();
      fc.count(strings[i]);
      double ioc = calculateIndexOfCoincidence(fc);
      double delta = Math.abs(ioc - m_plainIoC);
      if (delta > m_tolerance)
        return false;
    }

    return true;
  }

  private double m_tolerance;
  private double m_plainIoC;
  private double m_cipherIoC;
  private String m_cipherText;
}
