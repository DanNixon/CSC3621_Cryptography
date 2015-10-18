package com.dan_nixon.csc3621.coursework1.ex2;

import org.junit.*;
import static org.junit.Assert.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import com.dan_nixon.csc3621.coursework1.ex2.Vigenere;

public class VigenereTest
{
  @Test
  public void testCreate()
  {
    Vigenere v = new Vigenere("ncl");
  }

  @Test(expected=IllegalArgumentException.class)
  public void testCreateWithEmptyKey()
  {
    Vigenere v = new Vigenere("");
  }

  @Test(expected=IllegalArgumentException.class)
  public void testCreateWithInvalidKey()
  {
    Vigenere v = new Vigenere("nc1");
  }

  @Test
  public void testEncrypt()
  {
    String key = "ncl";
    String plainText = "newcastleuniversity";

    Vigenere v = new Vigenere(key);
    String cipherText = v.encrypt(plainText);

    assertEquals("aghpcdgnphptigcfkel", cipherText);
  }

  @Test
  public void testDecrypt()
  {
    String key = "ncl";
    String cipherText = "aghpcdgnphptigcfkel";

    Vigenere v = new Vigenere(key);
    String plainText = v.decrypt(cipherText);

    assertEquals("newcastleuniversity", plainText);
  }

  @Test
  public void testEncryptFile() throws IOException
  {
    File plainFile = new File(this.getClass().getResource("/pg1661.txt").getFile());
    String key = "ncl";

    Vigenere v = new Vigenere(key);
    String cipherText = v.encrypt(plainFile);
  }
}
