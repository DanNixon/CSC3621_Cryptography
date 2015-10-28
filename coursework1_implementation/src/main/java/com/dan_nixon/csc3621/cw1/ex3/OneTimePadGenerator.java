package com.dan_nixon.csc3621.cw1.ex3;

import java.security.SecureRandom;
import java.nio.ByteBuffer;

public class OneTimePadGenerator
{
  /**
   * Generate a one time pad using the current system time as the seed.
   *
   * @param length Length of pad to generate
   * @return Generated pad
   */
  public static byte[] generateOneTimePad(int length)
  {
    return generateOneTimePad(System.currentTimeMillis(), length);
  }

  /**
   * Generate a one time pad given an integer seed.
   *
   * @param seed Integer seed
   * @param length Length of pad to generate
   * @return Generated pad
   */
  public static byte[] generateOneTimePad(long seed, int length)
  {
    ByteBuffer seedBuff = ByteBuffer.allocate(8);
    seedBuff.putLong(seed);

    SecureRandom rand = new SecureRandom(seedBuff.array());

    byte[] pad = new byte[length];
    rand.nextBytes(pad);

    return pad;
  }
}
