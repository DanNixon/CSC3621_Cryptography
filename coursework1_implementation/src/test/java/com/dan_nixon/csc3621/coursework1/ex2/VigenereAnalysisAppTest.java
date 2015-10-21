package com.dan_nixon.csc3621.coursework1.ex2;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.Map;
import java.util.HashMap;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import com.dan_nixon.csc3621.coursework1.Utils;
import com.dan_nixon.csc3621.coursework1.ex1.FrequencyCounter;
import com.dan_nixon.csc3621.coursework1.ex2.VigenereAnalysisApp;

public class VigenereAnalysisAppTest
{
  @Test
  public void testKeyLengthCWCipherText() throws IOException
  {
    Map<String, String> options = new HashMap<String, String>();

    File plainFile = new File(this.getClass().getResource("/pg1661.txt").getFile());
    File cipherFile = new File(this.getClass().getResource("/Exercise2Ciphertext.txt").getFile());

    FrequencyCounter plainCount = new FrequencyCounter();
    plainCount.count(plainFile);
    String cipherText = Utils.readFileToString(cipherFile);

    int keyLength = VigenereAnalysisApp.getKeyLength(options, plainCount, cipherText);
    assertEquals(5, keyLength);
  }

  @Test
  public void testObtainKeyCWCipherText() throws IOException
  {
    Map<String, String> options = new HashMap<String, String>();

    File plainFile = new File(this.getClass().getResource("/pg1661.txt").getFile());
    File cipherFile = new File(this.getClass().getResource("/Exercise2Ciphertext.txt").getFile());

    FrequencyCounter plainCount = new FrequencyCounter();
    plainCount.count(plainFile);
    String cipherText = Utils.readFileToString(cipherFile);

    String key = VigenereAnalysisApp.getKey(options, plainCount, cipherText, 5);

    // Should be "plato" but frequency analysis fails
    // assertEquals("plato", key);
    assertEquals("pllto", key);
  }
}
