package com.dan_nixon.csc3621.coursework1.ex2;

import java.util.Arrays;
import java.io.File;
import java.io.IOException;
import com.dan_nixon.csc3621.coursework1.Utils;
import com.dan_nixon.csc3621.coursework1.ex1.FrequencyAnalysis;
import com.dan_nixon.csc3621.coursework1.ex1.FrequencyCounter;

public class VigenereAnalysis
{
  /**
   * Splits a string into strings for analysis based on an assumed key length.
   *
   * e.g. "hello", key 2 = {"hlo", "el"}
   *
   * @param str The string to split
   * @param keySize Assumed key size
   * @return Array of strings
   */
  public static String[] splitStringIntoKeyedStrings(String str, int keySize, boolean uniformLen)
  {
    StringBuilder[] builders = new StringBuilder[keySize];

    for (int i = 0; i < keySize; i++)
      builders[i] = new StringBuilder();

    for (int i = 0; i < str.length(); i++)
    {
      char c = str.charAt(i);
      int pos = i % keySize;
      builders[pos].append(c);
    }

    String[] strings = new String[keySize];
    for (int i = 0; i < keySize; i++)
      strings[i] = builders[i].toString();

    if (uniformLen)
    {
      int len = strings[strings.length - 1].length();
      for (int i = 0; i < keySize - 1; i++)
        strings[i] = strings[i].substring(0, len);
    }

    return strings;
  }

  public VigenereAnalysis(double tolerance)
  {
    m_tolerance = tolerance;
    m_plainCount = null;
    m_cipherCount = null;
    m_cipherText = "";
  }

  /**
   * Sets the count for plain text characters.
   *
   * @param plainCount COunt of plain text characters
   */
  public void setPlainTextCount(FrequencyCounter plainCount)
  {
    m_plainCount = plainCount;
  }

  /**
   * Sets the cipher text.
   *
   * @param cipherText Cipher text
   */
  public void setCipherText(String cipherText)
  {
    m_cipherText = cipherText;
    m_cipherCount = new FrequencyCounter();
    m_cipherCount.count(m_cipherText);
  }

  /**
   * Obtains the most likely key length.
   *
   * @return Key length
   */
  public int getKeyLength()
  {
    IOCAnalysis ioca = new IOCAnalysis(m_tolerance);
    ioca.setPlainTextCount(m_plainCount);
    ioca.setCipherTextCount(m_cipherCount);
    return ioca.obtainKeySize();
  }

  public String obtainKey(int keySize)
  {
    String[] strings = splitStringIntoKeyedStrings(m_cipherText, keySize, true);

    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < keySize; i++)
    {
      FrequencyCounter fc = new FrequencyCounter();
      fc.count(strings[i]);

      FrequencyAnalysis fa = new FrequencyAnalysis();
      fa.setPlainTextCount(m_plainCount);
      fa.setCipherTextCount(fc);

      System.out.println(i);
      System.out.println(Arrays.toString(fc.normalise()));

      int keyCharIdx = fa.rotationAnalysis();
      char keyChar = Utils.getCharFromIndex(keyCharIdx);
      sb.append(keyChar);
    }

    return sb.toString();
  }

  private double m_tolerance;
  private FrequencyCounter m_plainCount;
  private FrequencyCounter m_cipherCount;
  private String m_cipherText;
}
