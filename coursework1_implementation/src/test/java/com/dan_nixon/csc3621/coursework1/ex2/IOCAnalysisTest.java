package com.dan_nixon.csc3621.coursework1.ex2;

import org.junit.*;
import static org.junit.Assert.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import com.dan_nixon.csc3621.coursework1.ex2.IOCAnalysis;

public class IOCAnalysisTest
{
  public static final double IOC_TOLERANCE = 0.0001;
  public static final double TOLERANCE = 0.001;

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
    int keySize = ioca.keySizeFromIoC(0.04205);
    assertEquals(4, keySize);
  }

  @Test
  public void testReadFiles() throws IOException
  {
    File plainFile1 = new File(this.getClass().getResource("/pg1661.txt").getFile());
    File[] plainFiles = {plainFile1};
    File cipherFile = new File(this.getClass().getResource("/Exercise2Ciphertext.txt").getFile());

    IOCAnalysis ioca = new IOCAnalysis(IOC_TOLERANCE);
    ioca.readPlainText(plainFiles);
    ioca.readCipher(cipherFile);

    assertEquals(0.065, ioca.getPlainTextIoC(), TOLERANCE);
    assertEquals(0.045, ioca.getCipherTextIoC(), TOLERANCE);
  }

  @Test
  public void testAnalysis() throws IOException
  {
    File plainFile1 = new File(this.getClass().getResource("/pg1661.txt").getFile());
    File[] plainFiles = {plainFile1};
    File cipherFile = new File(this.getClass().getResource("/Exercise2Ciphertext.txt").getFile());

    IOCAnalysis ioca = new IOCAnalysis(0.0005);
    ioca.readPlainText(plainFiles);
    ioca.readCipher(cipherFile);

    System.out.println(ioca.getCipherTextIoC());

    int keySize = ioca.obtainKeySize();
    assertEquals(5, keySize);
  }
}
