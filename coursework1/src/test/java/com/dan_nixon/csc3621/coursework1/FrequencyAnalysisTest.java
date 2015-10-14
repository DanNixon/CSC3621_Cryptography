package com.dan_nixon.csc3621.coursework1;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import com.dan_nixon.csc3621.coursework1.FrequencyAnalysis;

public class FrequencyAnalysisTest extends TestCase
{
  public FrequencyAnalysisTest(String testName)
  {
    super(testName);
  }

  public static Test suite()
  {
    return new TestSuite(FrequencyAnalysisTest.class);
  }

  public void testAllZeroAtInstantiation()
  {
    FrequencyAnalysis fa = new FrequencyAnalysis();

    for (int i = 97; i < 123; i++)
    {
      char c = (char) i;
      assertEquals(fa.occurrences(c), 0);
    }
  }

  public void testToStringAtInstantiation()
  {
    FrequencyAnalysis fa = new FrequencyAnalysis();

    assertEquals(fa.toString(),
                 "FrequencyAnalysis[a=0,b=0,c=0,d=0,e=0,f=0,g=0,h=0,i=0,j=0,k=0,l=0,m=0,n=0,o=0,p=0,q=0,r=0,s=0,t=0,u=0,v=0,w=0,x=0,y=0,z=0]");
  }

  public void testReadSimpleFile() throws IOException
  {
    URL url = this.getClass().getResource("/test_FrequencyAnalysis_1.txt");
    File file = new File(url.getFile());

    FrequencyAnalysis fa = new FrequencyAnalysis();
    fa.read(file);

    for (int i = 97; i < 123; i++)
    {
      char c = (char) i;
      assertEquals(fa.occurrences(c), 1);
    }

    assertEquals(fa.totalCount(), 26);
  }

  public void testReadFileWithAllDifferentCounts() throws IOException
  {
    URL url = this.getClass().getResource("/test_FrequencyAnalysis_2.txt");
    File file = new File(url.getFile());

    FrequencyAnalysis fa = new FrequencyAnalysis();
    fa.read(file);

    for (int i = 97; i < 123; i++)
    {
      char c = (char) i;
      int numExpected = i - 96;
      assertEquals(fa.occurrences(c), numExpected);
    }

    assertEquals(fa.totalCount(), 351);
  }

  public void testReadCourseworkFile() throws IOException
  {
    URL url = this.getClass().getResource("/pg1661.txt");
    File file = new File(url.getFile());

    FrequencyAnalysis fa = new FrequencyAnalysis();
    fa.read(file);

    assertTrue(fa.occurrences('e') > 1);

    System.out.println(fa);
  }
}
