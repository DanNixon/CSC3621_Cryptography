package com.dan_nixon.csc3621.coursework1.ex2;

import org.junit.*;
import static org.junit.Assert.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import com.dan_nixon.csc3621.coursework1.ex2.VigenereApp;

public class VigenereAppTest
{
  @Test
  public void testEncrypt() throws IOException
  {
    File file = new File(this.getClass().getResource("/test_ex2_plain.txt").getFile());
    String result = VigenereApp.process("encrypt", "ncl", file);
    assertEquals("aghpcdgnphptigcfkel\n", result);
  }

  @Test
  public void testDecrypt() throws IOException
  {
    File file = new File(this.getClass().getResource("/test_ex2_cipher.txt").getFile());
    String result = VigenereApp.process("decrypt", "ncl", file);
    assertEquals("newcastleuniversity\n", result);
  }

  @Test(expected=IllegalArgumentException.class)
  public void testInvalidMode() throws IOException
  {
    File file = new File(this.getClass().getResource("/test_ex2_cipher.txt").getFile());
    String result = VigenereApp.process("de", "ncl", file);
  }
}
