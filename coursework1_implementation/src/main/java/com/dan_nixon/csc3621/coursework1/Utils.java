package com.dan_nixon.csc3621.coursework1;

public class Utils
{
  /**
   * Converts a character to a numerical index.
   *
   * e.g. a=0, z=25, A=0, Z=25
   *
   * @param c Character to convert
   * @return Index
   */
  public static int getIndexFromChar(char c) throws IllegalArgumentException
  {
    char ch = Character.toLowerCase(c);
    int charIdx = (int) ch - 97;
    if (charIdx >= 0 && charIdx < 26)
      return charIdx;
    throw new IllegalArgumentException("Invalid char");
  }

  /**
   * Gets a character from a numerical index.
   *
   * e.g. 0=a, 25=z
   *
   * @param idx Index to convert
   * @return Character
   */
  public static char getCharFromIndex(int idx)
  {
    if (idx < 0 || idx > 25)
      throw new IllegalArgumentException("Index out of bounds (0-25)");
    return (char) (idx + 97);
  }

  /**
   * Rotates (shifts) a character as per the rules of a shift cipher.
   *
   * @param c Character to rotate
   * @param rotation Offset (key)
   * @return Rotated character
   */
  public static char rotateChar(char c, int rotation)
  {
    if (rotation < 0)
      rotation = 26 + rotation;
    int index = (getIndexFromChar(c) + rotation) % 26;
    return getCharFromIndex(index);
  }

  /**
   * Rotates an array of doubles by a given offset.
   *
   * e.g. [1, 2, 3] rotated by 1 is [3, 1, 2]
   *
   * @param in Array to rotate
   * @param rotation Offset
   * @return Rotated array
   */
  public static double[] rotateArray(double[] in, int rotation)
  {
    double[] out = new double[in.length];
    for (int i = 0; i < in.length; i++)
    {
      int newIdx = (i + rotation) % in.length;
      out[newIdx] = in[i];
    }
    return out;
  }
}
