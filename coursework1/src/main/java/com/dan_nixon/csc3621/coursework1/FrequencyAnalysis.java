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
    m_normEnglishDistrib = null;
    m_cipherDistrib = null;
  }

  public void learnEnglishDistribution(File[] files) throws IOException, FileNotFoundException
  {
    FrequencyCounter fc = new FrequencyCounter();
    for (int i = 0; i < files.length; i++)
      fc.count(files[i]);
    m_normEnglishDistrib = fc.normalise();
  }

  public void readCipher(File file) throws IOException, FileNotFoundException
  {
    FrequencyCounter fc = new FrequencyCounter();
    fc.count(file);
    m_cipherDistrib = fc.normalise();
  }

  //TODO

  private double[] m_normEnglishDistrib;
  private double[] m_cipherDistrib;
}
