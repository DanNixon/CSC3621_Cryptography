package com.dan_nixon.csc3621.cw1;

import org.junit.*;
import static org.junit.Assert.*;
import java.io.UnsupportedEncodingException;
import com.dan_nixon.csc3621.cw1.ex3.OneTimePadEncryption;

public class OneTimePadEncryptionTest
{
  @Test
  public void testEncryptCWSpec() throws UnsupportedEncodingException
  {
    String message = "Every cloud has a silver lining";
    byte[] pad = {(byte)0x6d, (byte)0xc7, (byte)0x2f, (byte)0xc5,
                  (byte)0x95, (byte)0xe3, (byte)0x5d, (byte)0xcd,
                  (byte)0x38, (byte)0xc0, (byte)0x5d, (byte)0xca,
                  (byte)0x2a, (byte)0x0d, (byte)0x2d, (byte)0xbd,
                  (byte)0x8e, (byte)0x2d, (byte)0xf2, (byte)0x0b,
                  (byte)0x12, (byte)0x9b, (byte)0x2c, (byte)0xfa,
                  (byte)0x29, (byte)0xad, (byte)0x17, (byte)0x97,
                  (byte)0x29, (byte)0x22, (byte)0xa2};

    byte[] cipherData = {(byte)0x28, (byte)0xb1, (byte)0x4a, (byte)0xb7,
                         (byte)0xec, (byte)0xc3, (byte)0x3e, (byte)0xa1,
                         (byte)0x57, (byte)0xb5, (byte)0x39, (byte)0xea,
                         (byte)0x42, (byte)0x6c, (byte)0x5e, (byte)0x9d,
                         (byte)0xef, (byte)0x0d, (byte)0x81, (byte)0x62,
                         (byte)0x7e, (byte)0xed, (byte)0x49, (byte)0x88,
                         (byte)0x09, (byte)0xc1, (byte)0x7e, (byte)0xf9,
                         (byte)0x40, (byte)0x4c, (byte)0xc5};

    OneTimePadEncryption otpe = new OneTimePadEncryption(pad);

    assertArrayEquals(cipherData, otpe.encrypt(message));
  }

  @Test
  public void testDecryptCWSpec() throws UnsupportedEncodingException
  {
    String message = "Every cloud has a silver lining";

    byte[] pad = {(byte)0x6d, (byte)0xc7, (byte)0x2f, (byte)0xc5,
                  (byte)0x95, (byte)0xe3, (byte)0x5d, (byte)0xcd,
                  (byte)0x38, (byte)0xc0, (byte)0x5d, (byte)0xca,
                  (byte)0x2a, (byte)0x0d, (byte)0x2d, (byte)0xbd,
                  (byte)0x8e, (byte)0x2d, (byte)0xf2, (byte)0x0b,
                  (byte)0x12, (byte)0x9b, (byte)0x2c, (byte)0xfa,
                  (byte)0x29, (byte)0xad, (byte)0x17, (byte)0x97,
                  (byte)0x29, (byte)0x22, (byte)0xa2};

    byte[] cipherData = {(byte)0x28, (byte)0xb1, (byte)0x4a, (byte)0xb7,
                         (byte)0xec, (byte)0xc3, (byte)0x3e, (byte)0xa1,
                         (byte)0x57, (byte)0xb5, (byte)0x39, (byte)0xea,
                         (byte)0x42, (byte)0x6c, (byte)0x5e, (byte)0x9d,
                         (byte)0xef, (byte)0x0d, (byte)0x81, (byte)0x62,
                         (byte)0x7e, (byte)0xed, (byte)0x49, (byte)0x88,
                         (byte)0x09, (byte)0xc1, (byte)0x7e, (byte)0xf9,
                         (byte)0x40, (byte)0x4c, (byte)0xc5};

    OneTimePadEncryption otpe = new OneTimePadEncryption(pad);

    assertEquals(message, otpe.decrypt(cipherData));
  }

  @Test
  public void testLoadPadFromFileEncrypt()
  {
    //TODO
  }

  @Test
  public void testLoadPadFromFileDecrypt()
  {
    //TODO
  }
}
