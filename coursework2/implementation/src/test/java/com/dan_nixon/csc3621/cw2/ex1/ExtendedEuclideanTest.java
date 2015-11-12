package com.dan_nixon.csc3621.cw2.ex1;

import java.math.BigInteger;
import org.junit.*;
import static org.junit.Assert.*;

public class ExtendedEuclideanTest
{
  @Test
  public void testCreate()
  {
    ExtendedEuclidean ee = new ExtendedEuclidean();
    
    assertEquals(null, ee.getD());
    assertEquals(null, ee.getS());
    assertEquals(null, ee.getT());
  }
  
  @Test
  public void testCalculateDocumentedExample()
  {
    final BigInteger x = new BigInteger("240");
    final BigInteger y = new BigInteger("46");
    
    ExtendedEuclidean ee = new ExtendedEuclidean();
    ee.gcd(x, y);
    
    final BigInteger expectedD = x.gcd(y);
    
    final BigInteger xs = x.multiply(ee.getS());
    final BigInteger yt = y.multiply(ee.getT());
    final BigInteger v = xs.add(yt);
    
    assertEquals(expectedD, ee.getD());
    assertEquals(expectedD, v);
    
    assertEquals(new BigInteger("2"), ee.getD());
    assertEquals(new BigInteger("-9"), ee.getS());
    assertEquals(new BigInteger("47"), ee.getT());
    
    final String expectedStr = "d=2" + System.getProperty("line.separator")
                             + "s=-9" + System.getProperty("line.separator")
                             + "t=47";
    assertEquals(expectedStr, ee.toString());
  }
  
  @Test
  public void testCWExample()
  {
    final BigInteger x = new BigInteger("1572855870797393");
    final BigInteger y = new BigInteger("630065648824575");
    
    ExtendedEuclidean ee = new ExtendedEuclidean();
    ee.gcd(x, y);
    
    final BigInteger expectedD = x.gcd(y);
    
    final BigInteger xs = x.multiply(ee.getS());
    final BigInteger yt = y.multiply(ee.getT());
    final BigInteger v = xs.add(yt);
    
    assertEquals(expectedD, ee.getD());
    assertEquals(expectedD, v);
  }
}
