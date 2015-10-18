package com.dan_nixon.csc3621.coursework1.ex2;

import com.dan_nixon.csc3621.coursework1.Utils;

public class Vigenere
{
  Vigenere(String key)
  {
    m_key = key.toCharArray();
  }

  /**
   * Encrypt a message with the preset key.
   *
   * @param plainText Plain text to encrypt
   * @return Cipher text
   */
  public String encrypt(String plainText)
  {
    return process(plainText, 1);
  }

  /**
   * Decrypt a message with the preset key.
   *
   * @param cipherText Cipher text to decrypt
   * @return Plain text
   */
  public String decrypt(String cipherText)
  {
    return process(cipherText, -1);
  }

  /**
   * Performs the encryption and decryption processing.
   *
   * Operation is set using keyMultiplier; 1 for encryption and -1 for
   * decryption.
   *
   * @param test Text to process
   * @param keyMultiplier Multiplier for key value
   * @return Processed text
   */
  private String process(String text, int keyMultiplier)
  {
    StringBuilder sb = new StringBuilder();

    int currentKeyIndex = 0;
    for (char c: text.toCharArray())
    {
      try
      {
        int keyIdx = Utils.getIndexFromChar(m_key[currentKeyIndex]);
        c = Utils.rotateChar(c, keyIdx * keyMultiplier);

        currentKeyIndex++;
        currentKeyIndex %= m_key.length;
      }
      catch (IllegalArgumentException ex) {}
      sb.append(c);
    }

    return sb.toString();
  }

  private char[] m_key;
}
