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
}
