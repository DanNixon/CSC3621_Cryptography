package com.dan_nixon.csc3621.cw1.ex3;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.Map;
import com.dan_nixon.csc3621.cw1.ex3.OneTimePadGenerator;

public class OneTimePadGeneratorTest
{
  @Test
  public void testPadGeneratorIntSeed()
  {
    int seed = 0x0F1E2D3C;
    byte[] pad = OneTimePadGenerator.generateOneTimePad(seed, 10);

    assertEquals(10, pad.length);
  }

  @Test
  public void testPadGeneratorTimeSeed()
  {
    byte[] pad = OneTimePadGenerator.generateOneTimePad(10);

    assertEquals(10, pad.length);
  }
}
