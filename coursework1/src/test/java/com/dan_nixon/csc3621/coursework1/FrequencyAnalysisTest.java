package com.dan_nixon.csc3621.coursework1;

import org.junit.*;
import static org.junit.Assert.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import com.dan_nixon.csc3621.coursework1.FrequencyCounter;
import com.dan_nixon.csc3621.coursework1.FrequencyAnalysis;

public class FrequencyAnalysisTest
{
  public static final double TOLERANCE = 0.0001;

  @Test
  public void testReadPlain() throws IOException
  {
    File plainFile1 = new File(this.getClass().getResource("/pg1661.txt").getFile());
    File[] plainFiles = {plainFile1};

    FrequencyAnalysis fa = new FrequencyAnalysis();
    fa.learnPlainTextDistribution(plainFiles);

    double[] plainDistrib = fa.getPlainTextDistribution();

    assertEquals(0.08083, plainDistrib[0], TOLERANCE);
    assertEquals(0.12288, plainDistrib[4], TOLERANCE);
  }

  @Test
  public void testReadMultiplePlain() throws IOException
  {
    File plainFile1 = new File(this.getClass().getResource("/pg1661.txt").getFile());
    File plainFile2 = new File(this.getClass().getResource("/test_text_1.txt").getFile());
    File[] plainFiles = {plainFile1, plainFile2};

    FrequencyAnalysis fa = new FrequencyAnalysis();
    fa.learnPlainTextDistribution(plainFiles);

    double[] plainDistrib = fa.getPlainTextDistribution();

    assertEquals(0.080826, plainDistrib[0], TOLERANCE);
    assertEquals(0.122872, plainDistrib[4], TOLERANCE);
  }

  @Test
  public void testReadCipher() throws IOException
  {
    File cipherFile = new File(this.getClass().getResource("/Exercise1Ciphertext.txt").getFile());

    FrequencyAnalysis fa = new FrequencyAnalysis();
    fa.readCipher(cipherFile);

    double[] cipherDistrib = fa.getCipherTextDistribution();

    assertEquals(0.02658, cipherDistrib[0], TOLERANCE);
    assertEquals(0.07774, cipherDistrib[4], TOLERANCE);
  }
}
