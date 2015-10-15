package com.dan_nixon.csc3621.coursework1;

import org.junit.*;
import static org.junit.Assert.*;
import com.dan_nixon.csc3621.coursework1.Utils;

public class UtilsTest
{
  @Test
  public void testGetCharIdxLowercase()
  {
    assertEquals(Utils.getIndexFromChar('a'), 0);
    assertEquals(Utils.getIndexFromChar('z'), 25);
  }

  @Test
  public void testGetCharIdxUppercase()
  {
    assertEquals(Utils.getIndexFromChar('A'), 0);
    assertEquals(Utils.getIndexFromChar('Z'), 25);
  }

  @Test(expected=IllegalArgumentException.class)
  public void testGetCharIdxFailCharOutOfLowerBound()
  {
    Utils.getIndexFromChar('`');
  }

  @Test(expected=IllegalArgumentException.class)
  public void testGetCharIdxFailCharOutOfUpperBound()
  {
    Utils.getIndexFromChar('{');
  }

  @Test
  public void testGetCharFromIndex()
  {
    assertEquals(Utils.getCharFromIndex(0), 'a');
    assertEquals(Utils.getCharFromIndex(25), 'z');
  }

  @Test(expected=IllegalArgumentException.class)
  public void testGetCharFromIndexFailureUpperBound()
  {
    Utils.getCharFromIndex(26);
  }

  @Test(expected=IllegalArgumentException.class)
  public void testGetCharFromIndexFailureLowerBound()
  {
    Utils.getCharFromIndex(-1);
  }

  @Test
  public void testClosestMatchDataset1()
  {
    double[] data = {1.0, 2.0, 3.0, 4.0};
    assertEquals(Utils.getClosestMatch(data, 1.0), 0);
    assertEquals(Utils.getClosestMatch(data, 2.0), 1);
    assertEquals(Utils.getClosestMatch(data, 3.0), 2);
    assertEquals(Utils.getClosestMatch(data, 4.0), 3);
    assertEquals(Utils.getClosestMatch(data, 3.2), 2);
    assertEquals(Utils.getClosestMatch(data, 3.5), 2);
    assertEquals(Utils.getClosestMatch(data, 0.0), 0);
    assertEquals(Utils.getClosestMatch(data, 9.0), 3);
    assertEquals(Utils.getClosestMatch(data, 2.7), 2);
    assertEquals(Utils.getClosestMatch(data, 1.9), 1);
    assertEquals(Utils.getClosestMatch(data, 1.1), 0);
  }
}
