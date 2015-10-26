package com.dan_nixon.csc3621.cw1.ex3;

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
    int seed = (int) (System.currentTimeMillis() % Integer.MAX_VALUE);
    return generateOneTimePad(seed, length);
  }

  /**
   * Generate a one time pad given an integer seed.
   *
   * @param seed Integer seed
   * @param length Length of pad to generate
   * @return Generated pad
   */
  public static byte[] generateOneTimePad(int seed, int length)
  {
    byte[] pad = new byte[length];

    pad[3] = (byte) (0x000000FF & seed);
    pad[2] = (byte) ((0x0000FF00 & seed) >> 8);
    pad[1] = (byte) ((0x00FF0000 & seed) >> 16);
    pad[0] = (byte) ((0xFF000000 & seed) >> 24);

    for (int i = 4; i < length; i++)
      pad[i] = (byte) (pad[i-4] ^ pad[i-3]);

    return pad;
  }
}
