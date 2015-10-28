package com.dan_nixon.csc3621.cw1.ex3;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.io.IOException;
import com.dan_nixon.csc3621.cw1.Utils;

public class OneTimePadEncryption
{
  /**
   * Create a new instance using a given pad.
   *
   * @param pad One time pad to use in encryption/decryption
   */
  public OneTimePadEncryption(byte[] pad)
  {
    m_pad = pad;
  }

  /**
   * Create a new instance with a pad read from a file.
   *
   * @param padFile File to load pad from
   */
  public OneTimePadEncryption(File padFile) throws IOException
  {
    m_pad = Utils.readBinaryFile(padFile);
  }

  /**
   * Retrieve the pad.
   *
   * @return Pad
   */
  public byte[] getPad()
  {
    return m_pad;
  }

  /**
   * Encrypt a given plain text message.
   *
   * @param plainText Message to encrypt
   * @return Encrypted message data
   */
  public byte[] encrypt(String plainText) throws UnsupportedEncodingException
  {
    // Convert plain text string to bytes
    byte[] plainTextData = plainText.getBytes("US-ASCII");
    if (plainTextData.length > m_pad.length)
      throw new RuntimeException("Pad too short");

    byte[] cipherData = new byte[plainTextData.length];

    // XOR plain text data with pad
    for (int i = 0; i < plainTextData.length; i++)
      cipherData[i] = (byte) (m_pad[i] ^ plainTextData[i]);

    return cipherData;
  }

  /**
   * Decrypt a message from cipher data.
   *
   * @param cipherData Encrypted data/
   * @return Plain text message
   */
  public String decrypt(byte[] cipherData) throws UnsupportedEncodingException
  {
    if (cipherData.length > m_pad.length)
      throw new RuntimeException("Pad too short");

    byte[] plainTextData = new byte[cipherData.length];

    // XOR cipher data with pad
    for (int i = 0; i < cipherData.length; i++)
      plainTextData[i] = (byte) (m_pad[i] ^ cipherData[i]);

    // Convert plan text bytes back to string
    String message = new String(plainTextData, "US-ASCII");

    return message;
  }

  private byte[] m_pad;
}
