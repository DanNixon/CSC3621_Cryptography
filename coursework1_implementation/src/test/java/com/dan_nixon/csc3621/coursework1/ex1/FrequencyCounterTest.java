package com.dan_nixon.csc3621.coursework1.ex1;

import org.junit.*;
import static org.junit.Assert.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import com.dan_nixon.csc3621.coursework1.ex1.FrequencyCounter;

public class FrequencyCounterTest
{
  public static final double TOLERANCE = 0.0000000001;

  @Test
  public void testAllZeroAtInstantiation()
  {
    FrequencyCounter fa = new FrequencyCounter();

    for (int i = 97; i < 123; i++)
    {
      char c = (char) i;
      assertEquals(0, fa.occurrences(c));
    }
  }

  @Test
  public void testToStringAtInstantiation()
  {
    FrequencyCounter fa = new FrequencyCounter();

    assertEquals("FrequencyCounter[a=0,b=0,c=0,d=0,e=0,f=0,g=0,h=0,i=0,j=0,k=0,l=0,m=0,n=0,o=0,p=0,q=0,r=0,s=0,t=0,u=0,v=0,w=0,x=0,y=0,z=0]",
                 fa.toString());
  }

  @Test
  public void testReadSimpleFile() throws IOException
  {
    URL url = this.getClass().getResource("/test_ex1_text1.txt");
    File file = new File(url.getFile());

    FrequencyCounter fa = new FrequencyCounter();
    fa.count(file);

    for (int i = 97; i < 123; i++)
    {
      char c = (char) i;
      assertEquals(1, fa.occurrences(c));
    }

    assertEquals(26, fa.totalCount());
  }

  @Test
  public void testReadFileWithAllDifferentCounts() throws IOException
  {
    URL url = this.getClass().getResource("/test_ex1_text2.txt");
    File file = new File(url.getFile());

    FrequencyCounter fa = new FrequencyCounter();
    fa.count(file);

    for (int i = 97; i < 123; i++)
    {
      char c = (char) i;
      int numExpected = i - 96;
      assertEquals(numExpected, fa.occurrences(c));
    }

    assertEquals(351, fa.totalCount());
  }

  @Test
  public void testNormalise() throws IOException
  {
    URL url = this.getClass().getResource("/test_ex1_text2.txt");
    File file = new File(url.getFile());

    FrequencyCounter fa = new FrequencyCounter();
    fa.count(file);

    double[] norm = fa.normalise();
    double total = 0.0;
    for (int i = 0; i < norm.length; i++)
      total += norm[i];
    assertEquals(1.0, total, TOLERANCE);
  }
}
