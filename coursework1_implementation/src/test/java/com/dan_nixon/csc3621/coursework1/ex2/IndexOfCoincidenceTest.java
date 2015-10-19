package com.dan_nixon.csc3621.coursework1.ex2;

import org.junit.*;
import static org.junit.Assert.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import com.dan_nixon.csc3621.coursework1.ex2.IndexOfCoincidence;

public class IndexOfCoincidenceTest
{
  public static final double TOLERANCE = 0.001;

  @Test
  public void testCreate()
  {
    IndexOfCoincidence ioc = new IndexOfCoincidence();
    assertEquals(null, ioc.distribution());
    assertEquals(0.0, ioc.indexOfCoincidence(), 0.0000001);
  }

  @Test
  public void testIoCCalculationPlainText() throws IOException
  {
    File file = new File(this.getClass().getResource("/pg1661.txt").getFile());

    IndexOfCoincidence ioc = new IndexOfCoincidence();
    ioc.calculate(file);

    // Plain English text should have IC~=0.065
    assertEquals(0.065, ioc.indexOfCoincidence(), TOLERANCE);
  }

  @Test
  public void testIoCCalculationEvenText() throws IOException
  {
    File file = new File(this.getClass().getResource("/test_ex1_text1.txt").getFile());

    IndexOfCoincidence ioc = new IndexOfCoincidence();
    ioc.calculate(file);

    // Random (linear distribution) text should have IC~=0.038
    assertEquals(0.038, ioc.indexOfCoincidence(), TOLERANCE);
  }

  @Test
  public void testIoCCalculationMultipleFiles() throws IOException
  {
    File file1 = new File(this.getClass().getResource("/test_ex1_text1.txt").getFile());
    File file2 = new File(this.getClass().getResource("/pg1661.txt").getFile());
    File[] files = {file1, file2};

    IndexOfCoincidence ioc = new IndexOfCoincidence();
    ioc.calculate(files);

    // The counts of the true plain English file far outweigh those in the even
    // distribution so this value of IC is expected
    assertEquals(0.065, ioc.indexOfCoincidence(), TOLERANCE);
  }
}
