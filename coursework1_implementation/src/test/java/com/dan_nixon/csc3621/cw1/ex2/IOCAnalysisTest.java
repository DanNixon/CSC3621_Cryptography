package com.dan_nixon.csc3621.cw1.ex2;

import org.junit.*;
import static org.junit.Assert.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import com.dan_nixon.csc3621.cw1.Utils;
import com.dan_nixon.csc3621.cw1.ex1.FrequencyCounter;
import com.dan_nixon.csc3621.cw1.ex2.IOCAnalysis;

public class IOCAnalysisTest
{
  public static final double IOC_TOLERANCE = 0.0005;
  public static final double TOLERANCE = 0.001;

  @Test
  public void testIoCCalculationPlainText() throws IOException
  {
    File file = new File(this.getClass().getResource("/pg1661.txt").getFile());
    FrequencyCounter fc = new FrequencyCounter();
    fc.count(file);
    double ioc = IOCAnalysis.calculateIndexOfCoincidence(fc);

    // Plain English text should have IC~=0.065
    assertEquals(0.065, ioc, TOLERANCE);
  }

  @Test
  public void testIoCCalculationEvenText() throws IOException
  {
    File file = new File(this.getClass().getResource("/test_ex2_eventext.txt").getFile());
    FrequencyCounter fc = new FrequencyCounter();
    fc.count(file);
    double ioc = IOCAnalysis.calculateIndexOfCoincidence(fc);

    // Random (linear distribution) text should have IC~=0.038
    assertEquals(0.038, ioc, TOLERANCE);
  }

  @Test
  public void testIoCCalculationCipherText() throws IOException
  {
    File file = new File(this.getClass().getResource("/test_ex2_cipher.txt").getFile());
    FrequencyCounter fc = new FrequencyCounter();
    fc.count(file);
    double ioc = IOCAnalysis.calculateIndexOfCoincidence(fc);

    assertEquals(0.0467, ioc, TOLERANCE);
  }

  @Test
  public void testIoCCalculationMultipleFiles() throws IOException
  {
    File file1 = new File(this.getClass().getResource("/test_ex1_text1.txt").getFile());
    File file2 = new File(this.getClass().getResource("/pg1661.txt").getFile());
    File[] files = {file1, file2};
    FrequencyCounter fc = new FrequencyCounter();
    fc.count(files);
    double ioc = IOCAnalysis.calculateIndexOfCoincidence(fc);

    // The counts of the true plain English file far outweigh those in the even
    // distribution so this value of IC is expected
    assertEquals(0.065, ioc, TOLERANCE);
  }

  @Test(expected=RuntimeException.class)
  public void testIOCToKeySizeNotFound()
  {
    IOCAnalysis ioca = new IOCAnalysis(IOC_TOLERANCE);
    int keySize = ioca.keySizeFromIoC(1.0);
  }

  @Test
  public void testIOCToKeySize()
  {
    IOCAnalysis ioca = new IOCAnalysis(IOC_TOLERANCE);
    int keySize = ioca.keySizeFromIoC(0.0475);
    assertEquals(4, keySize);
  }

  @Test
  public void testReadFiles() throws IOException
  {
    File plainFile = new File(this.getClass().getResource("/pg1661.txt").getFile());
    File cipherFile = new File(this.getClass().getResource("/test_ex2_cipher.txt").getFile());

    FrequencyCounter plainCount = new FrequencyCounter();
    plainCount.count(plainFile);

    String cipherText = Utils.readFileToString(cipherFile);

    IOCAnalysis ioca = new IOCAnalysis(IOC_TOLERANCE);
    ioca.setPlainTextCount(plainCount);
    ioca.setCipherText(cipherText);

    assertEquals(0.065, ioca.getPlainTextIoC(), TOLERANCE);
    assertEquals(0.0467, ioca.getCipherTextIoC(), TOLERANCE);
  }

  @Test
  public void testAnalysis() throws IOException
  {
    File plainFile = new File(this.getClass().getResource("/pg1661.txt").getFile());
    File cipherFile = new File(this.getClass().getResource("/test_ex2_cipher.txt").getFile());

    FrequencyCounter plainCount = new FrequencyCounter();
    plainCount.count(plainFile);

    String cipherText = Utils.readFileToString(cipherFile);

    IOCAnalysis ioca = new IOCAnalysis(0.005);
    ioca.setPlainTextCount(plainCount);
    ioca.setCipherText(cipherText);

    int keySize = ioca.obtainKeySize();
    assertEquals(0, keySize % 3);
  }

  @Test
  public void testAnalysisBruteForce_CW() throws IOException
  {
    File plainFile = new File(this.getClass().getResource("/pg1661.txt").getFile());
    File cipherFile = new File(this.getClass().getResource("/Exercise2Ciphertext.txt").getFile());

    FrequencyCounter plainCount = new FrequencyCounter();
    plainCount.count(plainFile);

    String cipherText = Utils.readFileToString(cipherFile);

    IOCAnalysis ioca = new IOCAnalysis(0.005);
    ioca.setPlainTextCount(plainCount);
    ioca.setCipherText(cipherText);

    int keySize = ioca.obtainKeySizeBruteForce(2, 5);
    assertEquals(5, keySize);

    assertFalse(ioca.validateKeySize(2));
    assertFalse(ioca.validateKeySize(3));
    assertFalse(ioca.validateKeySize(4));
    assertTrue(ioca.validateKeySize(5));
    assertFalse(ioca.validateKeySize(6));
    assertFalse(ioca.validateKeySize(7));
    assertFalse(ioca.validateKeySize(8));
  }
}
