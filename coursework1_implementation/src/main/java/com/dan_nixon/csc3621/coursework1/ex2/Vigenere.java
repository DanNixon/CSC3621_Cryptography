package com.dan_nixon.csc3621.coursework1.ex2;

import java.io.File;
import java.io.IOException;
import com.dan_nixon.csc3621.coursework1.Utils;

public class Vigenere
{
  /**
   * Creates a new Vigenere encryption/decription with a given key.
   *
   * @param key Key to use, must be at least one character and only contain
   *            letters a-z
   */
  Vigenere(String key) throws IllegalArgumentException
  {
    if (key.isEmpty())
      throw new IllegalArgumentException("Key cannot have zero length");

    for (char c : key.toCharArray())
    {
      if (!Character.isLetter(c))
        throw new IllegalArgumentException("Invalid character found in key");
    }

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
   * Encrypt a message read from a file with the preset key.
   *
   * @param plainTextFile The file containing plain text
   * @return Cipher text
   */
  public String encrypt(File plainTextFile) throws IOException
  {
    String plainText = Utils.readFileToString(plainTextFile);
    return encrypt(plainText);
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
   * Decrypt a message read from a file with the preset key.
   *
   * @param cipherTextFile The file containing cipher text
   * @return Plain text
   */
  public String decrypt(File cipherTextFile) throws IOException
  {
    String cipherText = Utils.readFileToString(cipherTextFile);
    return decrypt(cipherText);
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
