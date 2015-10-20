package com.dan_nixon.csc3621.coursework1.ex2;

import org.junit.*;
import static org.junit.Assert.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import com.dan_nixon.csc3621.coursework1.Utils;
import com.dan_nixon.csc3621.coursework1.ex1.FrequencyCounter;
import com.dan_nixon.csc3621.coursework1.ex2.VigenereAnalysis;

public class VigenereAnalysisTest
{
  @Test
  public void testSplitStringsToKeySize()
  {
    String str = "helloworld";
    int keySize = 3;

    String[] strings = VigenereAnalysis.splitStringIntoKeyedStrings(str, keySize, false);

    assertEquals(keySize, strings.length);
    assertEquals("hlod", strings[0]);
    assertEquals("eor", strings[1]);
    assertEquals("lwl", strings[2]);
  }

  @Test
  public void testSplitStringsToKeySizeUniformLen()
  {
    String str = "helloworld";
    int keySize = 3;

    String[] strings = VigenereAnalysis.splitStringIntoKeyedStrings(str, keySize, true);

    assertEquals(keySize, strings.length);
    assertEquals("hlo", strings[0]);
    assertEquals("eor", strings[1]);
    assertEquals("lwl", strings[2]);
  }

  @Test
  public void testCreate()
  {
    VigenereAnalysis va = new VigenereAnalysis(0.0);
  }

  @Test
  public void testKeySize() throws IOException
  {
    File plainFile = new File(this.getClass().getResource("/pg1661.txt").getFile());
    File cipherFile = new File(this.getClass().getResource("/test_ex2_cipher.txt").getFile());

    FrequencyCounter plainCount = new FrequencyCounter();
    plainCount.count(plainFile);

    String cipherText = Utils.readFileToString(cipherFile);

    VigenereAnalysis va = new VigenereAnalysis(0.0005);
    va.setPlainTextCount(plainCount);
    va.setCipherText(cipherText);

    int keySize = va.getKeyLength();
    assertEquals(3, keySize);
  }

  @Test
  public void testGetKey() throws IOException
  {
    File plainFile = new File(this.getClass().getResource("/pg1661.txt").getFile());
    File cipherFile = new File(this.getClass().getResource("/test_ex2_cipher.txt").getFile());

    FrequencyCounter plainCount = new FrequencyCounter();
    plainCount.count(plainFile);

    String cipherText = Utils.readFileToString(cipherFile);

    VigenereAnalysis va = new VigenereAnalysis(0.0005);
    va.setPlainTextCount(plainCount);
    va.setCipherText(cipherText);

    String key = va.obtainKey(3);

    // assertEquals("ncl", key);
  }

  @Test
  public void testGetKeyCW() throws IOException
  {
    File plainFile = new File(this.getClass().getResource("/pg1661.txt").getFile());
    File cipherFile = new File(this.getClass().getResource("/Exercise2Ciphertext.txt").getFile());

    FrequencyCounter plainCount = new FrequencyCounter();
    plainCount.count(plainFile);

    String cipherText = Utils.readFileToString(cipherFile);

    VigenereAnalysis va = new VigenereAnalysis(0.0005);
    va.setPlainTextCount(plainCount);
    va.setCipherText(cipherText);

    String key = va.obtainKey(5);

    // assertEquals("plato", key);
  }
}
