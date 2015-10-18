package com.dan_nixon.csc3621.coursework1.ex2;

import org.junit.*;
import static org.junit.Assert.*;
import com.dan_nixon.csc3621.coursework1.ex2.Vigenere;

public class VigenereTest
{
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
}
