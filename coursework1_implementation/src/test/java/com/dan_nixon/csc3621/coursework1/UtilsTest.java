package com.dan_nixon.csc3621.coursework1;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.Map;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import com.dan_nixon.csc3621.coursework1.Utils;

public class UtilsTest
{
  public static final double TOLERANCE = 0.0000000001;

  @Test
  public void testParseCommandLine()
  {
    String[] args = {"-key",
                     "65",
                     "first_positional",
                     "--encrypt",
                     "--string",
                     "hello",
                     "positional"};

    Map<String, String> parsed = Utils.parseCommandLine(args);

    assertEquals("2", parsed.get("_num_positional"));

    assertEquals("65", parsed.get("key"));
    assertEquals("first_positional", parsed.get("_p0"));
    assertTrue(parsed.containsKey("encrypt"));
    assertEquals("hello", parsed.get("string"));
    assertEquals("positional", parsed.get("_p1"));
  }

  @Test
  public void testGetCharIdxLowercase()
  {
    assertEquals(0, Utils.getIndexFromChar('a'));
    assertEquals(25, Utils.getIndexFromChar('z'));
  }

  @Test
  public void testGetCharIdxUppercase()
  {
    assertEquals(0, Utils.getIndexFromChar('A'));
    assertEquals(25, Utils.getIndexFromChar('Z'));
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
    assertEquals('a', Utils.getCharFromIndex(0));
    assertEquals('z', Utils.getCharFromIndex(25));
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
  public void testRotateChar()
  {
    assertEquals('a', Utils.rotateChar('z', 1));
    assertEquals('e', Utils.rotateChar('z', 5));
    assertEquals('a', Utils.rotateChar('x', 3));
    assertEquals('b', Utils.rotateChar('d', -2));
    assertEquals('z', Utils.rotateChar('d', -4));
  }

  @Test
  public void testRotateArray()
  {
    double[] a = {1.0, 2.0, 3.0, 4.0, 5.0};

    assertArrayEquals(new double[] {5.0, 1.0, 2.0, 3.0, 4.0},
                      Utils.rotateArray(a, 1),
                      TOLERANCE);

    assertArrayEquals(new double[] {3.0, 4.0, 5.0, 1.0, 2.0},
                      Utils.rotateArray(a, 3),
                      TOLERANCE);
  }

  @Test public void testReadFileToString() throws IOException
  {
    URL url = this.getClass().getResource("/test_ex1_text1.txt");
    File file = new File(url.getFile());
    String str = Utils.readFileToString(file);
    assertEquals("abcdefghijklmnopqrstuvwxyz\n", str);
  }
}
