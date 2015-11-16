package com.dan_nixon.csc3621.cw2.ex3;

import java.math.BigInteger;
import org.junit.*;
import static org.junit.Assert.*;

public class DiffieHellmanKeyExchangeTest
{
  @Test
  public void testCreate()
  {
    DiffieHellmanKeyExchange dhke = new DiffieHellmanKeyExchange(BigInteger.valueOf(1), BigInteger.valueOf(1));
  }
  
  @Test(expected=IllegalStateException.class)
  public void testComputeMessageBtoAWithoutAtoB()
  {
    DiffieHellmanKeyExchange dhke = new DiffieHellmanKeyExchange(BigInteger.valueOf(1), BigInteger.valueOf(1));
    dhke.computeMessageBtoA();
  }
  
  @Test(expected=IllegalStateException.class)
  public void testComputeKeyAWithoutMessageBtoA()
  {
    DiffieHellmanKeyExchange dhke = new DiffieHellmanKeyExchange(BigInteger.valueOf(1), BigInteger.valueOf(1));
    dhke.computeKeyA();
  }
  
  @Test(expected=IllegalStateException.class)
  public void testComputeKeyBWithoutMessageAtoB()
  {
    DiffieHellmanKeyExchange dhke = new DiffieHellmanKeyExchange(BigInteger.valueOf(1), BigInteger.valueOf(1));
    dhke.computeKeyB();
  }
}
