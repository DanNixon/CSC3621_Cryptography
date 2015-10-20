package com.dan_nixon.csc3621.coursework1.ex2;

import org.junit.*;
import static org.junit.Assert.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import com.dan_nixon.csc3621.coursework1.ex1.FrequencyCounter;
import com.dan_nixon.csc3621.coursework1.ex2.VigenereAnalysis;

public class VigenereAnalysisTest
{
  @Test
  public void testCreate()
  {
    VigenereAnalysis va = new VigenereAnalysis(0.0);
  }

  @Test
  public void testKeySize() throws IOException
  {
    File plainFile = new File(this.getClass().getResource("/pg1661.txt").getFile());
    File cipherFile = new File(this.getClass().getResource("/Exercise2Ciphertext.txt").getFile());

    FrequencyCounter plainCount = new FrequencyCounter();
    plainCount.count(plainFile);
    FrequencyCounter cipherCount = new FrequencyCounter();
    cipherCount.count(cipherFile);

    VigenereAnalysis va = new VigenereAnalysis(0.0005);
    va.setPlainTextCount(plainCount);
    va.setCipherTextCount(cipherCount);

    int keySize = va.getKeyLength();
    // assertEquals(5, keySize);
  }

  //TODO
}
