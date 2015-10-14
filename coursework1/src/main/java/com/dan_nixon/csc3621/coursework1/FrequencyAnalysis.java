package com.dan_nixon.csc3621.coursework1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.HashMap;
import java.lang.StringBuilder;

public class FrequencyAnalysis
{
  public FrequencyAnalysis()
  {
    m_occurrences = new int[26];
  }

  public void read(File file) throws IOException, FileNotFoundException
  {
    FileInputStream fs = new FileInputStream(file);
    while (fs.available() > 0) {
      char c = Character.toLowerCase((char) fs.read());
      int charIdx = (int) c - 97;
      if (charIdx >= 0 && charIdx < 26)
        m_occurrences[charIdx]++;
    }
  }

  public int occurrences(char c)
  {
    int charIdx = (int) c - 97;
    return m_occurrences[charIdx];
  }

  public int totalCount()
  {
    int total = 0;
    for (int i = 0; i < 26; i++)
      total += m_occurrences[i];
    return total;
  }

  public double[] getNormalised()
  {
    double total = (double) totalCount();
    double[] norm = new double[26];

    for (int i = 0; i < 26; i++)
      norm[i] = (double) m_occurrences[i] / total;

    return norm;
  }

  @Override
  public String toString()
  {
    StringBuilder sb = new StringBuilder();

    sb.append("FrequencyAnalysis[");
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
