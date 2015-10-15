package com.dan_nixon.csc3621.coursework1.ex1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.System;
import java.lang.StringBuilder;
import java.lang.IllegalArgumentException;
import com.dan_nixon.csc3621.coursework1.Utils;

public class FrequencyCounter
{
  public FrequencyCounter()
  {
    m_occurrences = new int[26];
  }

  public void count(File file) throws IOException, FileNotFoundException
  {
    FileInputStream fs = new FileInputStream(file);
    while (fs.available() > 0) {
      char c = (char) fs.read();
      try
      {
        m_occurrences[Utils.getIndexFromChar(c)]++;
      }
      catch (IllegalArgumentException ex)
      {
        continue;
      }
    }
  }

  public int occurrences(char c) throws IllegalArgumentException
  {
    return m_occurrences[Utils.getIndexFromChar(c)];
  }

  public int totalCount()
  {
    int total = 0;
    for (int i = 0; i < 26; i++)
      total += m_occurrences[i];
    return total;
  }

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
