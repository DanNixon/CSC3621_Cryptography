package com.dan_nixon.csc3621.cw1.ex2;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.Map;
import java.util.HashMap;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import com.dan_nixon.csc3621.cw1.ex2.VigenereApp;

public class VigenereAppTest
{
  @Test
  public void testEncrypt() throws IOException
  {
    Map<String, String> options = new HashMap<String, String>();
    options.put("encrypt", null);
    options.put("key", "ncl");

    File file = new File(this.getClass().getResource("/test_ex2_plain.txt").getFile());

    String result = VigenereApp.process(options, file);

    assertEquals("aghpcdgnphptigcfkel\n", result);
  }

  @Test
  public void testDecrypt() throws IOException
  {
    Map<String, String> options = new HashMap<String, String>();
    options.put("decrypt", null);
    options.put("key", "ncl");

    File file = new File(this.getClass().getResource("/test_ex2_cipher.txt").getFile());

    String result = VigenereApp.process(options, file);

    assertEquals("newcastleuniversity\n", result);
  }
}
