package com.dan_nixon.csc3621.coursework1.ex2;

import java.io.File;
import java.io.IOException;
import com.dan_nixon.csc3621.coursework1.ex1.FrequencyCounter;

public class VigenereAnalysis
{
  public VigenereAnalysis(double tolerance)
  {
    m_tolerance = tolerance;
    m_plainCount = null;
    m_cipherCount = null;
  }

  public void setPlainTextCount(FrequencyCounter plainCount)
  {
    m_plainCount = plainCount;
  }

  public void setCipherTextCount(FrequencyCounter cipherCount)
  {
    m_cipherCount = cipherCount;
  }

  public int getKeyLength()
  {
    IOCAnalysis ioca = new IOCAnalysis(m_tolerance);
    ioca.setPlainTextCount(m_plainCount);
    ioca.setCipherTextCount(m_cipherCount);
    return ioca.obtainKeySize();
  }

  //TODO

  private double m_tolerance;
  private FrequencyCounter m_plainCount;
  private FrequencyCounter m_cipherCount;
}
