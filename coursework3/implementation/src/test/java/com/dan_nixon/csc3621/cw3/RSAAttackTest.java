package com.dan_nixon.csc3621.cw3;

import java.math.BigInteger;
import org.junit.*;
import static org.junit.Assert.*;

public class RSAAttackTest
{
  @Test
  public void test_EncodeString()
  {
    final BigInteger v = RSAAttack.encodeString("GREAT");
    assertEquals(new BigInteger("7182696584"), v);
  }
  
  @Test
  public void test_EncodeString_LowerCase()
  {
    final BigInteger v = RSAAttack.encodeString("grEaT");
    assertEquals(new BigInteger("7182696584"), v);
  }
  
  @Test
  public void test_DecodeString()
  {
    final String s = RSAAttack.decodeString(new BigInteger("7182696584"));
    assertEquals("GREAT", s);
  }
  
  @Test
  public void test_NRoot_Square()
  {
    final BigInteger a = BigInteger.valueOf(100);
    final BigInteger n = BigInteger.valueOf(2);
    
    assertEquals(BigInteger.valueOf(10), RSAAttack.nRoot(a, n));
  }
  
  @Test
  public void test_NRoot_Cube()
  {
    final BigInteger a = BigInteger.valueOf(512);
    final BigInteger n = BigInteger.valueOf(3);
    
    assertEquals(BigInteger.valueOf(8), RSAAttack.nRoot(a, n));
  }
  
  @Test
  public void test_DecipherCW()
  {
    final BigInteger n = new BigInteger("23095675100376460353980581297675223373026833410647478222648288977449481620360427");
    final BigInteger e = new BigInteger("3");
    final BigInteger c = new BigInteger("674472526620593903800497637242400187916753185909");
    
    assertEquals("WELLDONE", RSAAttack.retrieveMessage(n, e, c));
  }
}
