package com.dan_nixon.csc3621.coursework1;

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

  public void learnPlainTextDistribution(File[] files) throws IOException, FileNotFoundException
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

  //TODO

  private double[] m_plainDistrib;
  private double[] m_cipherDistrib;
}
