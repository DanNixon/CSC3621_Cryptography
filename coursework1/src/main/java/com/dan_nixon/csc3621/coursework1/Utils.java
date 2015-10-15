package com.dan_nixon.csc3621.coursework1;

public class Utils
{
  public static int getIndexFromChar(char c) throws IllegalArgumentException
  {
    char ch = Character.toLowerCase(c);
    int charIdx = (int) ch - 97;
    if (charIdx >= 0 && charIdx < 26)
      return charIdx;
    throw new IllegalArgumentException("Invalid char");
  }

  public static char getCharFromIndex(int idx)
  {
    if (idx < 0 || idx > 25)
      throw new IllegalArgumentException("Index out of bounds (0-25)");
    return (char) (idx + 97);
  }

  public static int getClosestMatch(double[] a, double value)
  {
    int bestIdx = 0;
    for (int i = 1; i < a.length; i++)
    {
      double bestDiff = Math.abs(a[bestIdx] - value);
      double candidateDiff = Math.abs(a[i] - value);
      if (candidateDiff < bestDiff)
        bestIdx = i;
    }
    return bestIdx;
  }
}
