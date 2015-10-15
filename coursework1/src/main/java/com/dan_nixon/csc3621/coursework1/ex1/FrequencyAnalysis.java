package com.dan_nixon.csc3621.coursework1.ex1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.System;
import java.lang.StringBuilder;
import java.lang.IllegalArgumentException;
import com.dan_nixon.csc3621.coursework1.Utils;

public class FrequencyAnalysis
{
  public FrequencyAnalysis()
  {
    m_plainDistrib = null;
    m_cipherDistrib = null;
  }

  public void readPlainText(File[] files) throws IOException, FileNotFoundException
  {
    FrequencyCounter fc = new FrequencyCounter();
    for (int i = 0; i < files.length; i++)
      fc.count(files[i]);
    m_plainDistrib = fc.normalise();
  }

  public void readCipher(File file) throws IOException, FileNotFoundException
  {
    FrequencyCounter fc = new FrequencyCounter();
    fc.count(file);
    m_cipherDistrib = fc.normalise();
  }

  public double[] getPlainTextDistribution()
  {
    return m_plainDistrib;
  }

  public double[] getCipherTextDistribution()
  {
    return m_cipherDistrib;
  }

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
