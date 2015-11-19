package com.dan_nixon.csc3621.cw2.ex3;

import java.math.BigInteger;
import java.util.EnumMap;
import java.util.Map;
import org.junit.*;
import static org.junit.Assert.*;

public class DiffieHellmanKeyExchangeTest
{
  @Test
  public void testToString()
  {
    DiffieHellmanKeyExchange a = new DiffieHellmanKeyExchange();
    DiffieHellmanKeyExchange b = new DiffieHellmanKeyExchange();

    Map<MessagePayload, BigInteger> msgAtoB = new EnumMap<MessagePayload, BigInteger>(MessagePayload.class);
    msgAtoB.put(MessagePayload.MOD, BigInteger.valueOf(98));
    msgAtoB.put(MessagePayload.BASE, BigInteger.valueOf(10));
    msgAtoB.put(MessagePayload.A, BigInteger.valueOf(88));

    Map<MessagePayload, BigInteger> msgBtoA = new EnumMap<MessagePayload, BigInteger>(MessagePayload.class);
    msgBtoA.put(MessagePayload.B, BigInteger.valueOf(23));

    final String s = DiffieHellmanKeyExchange.toString(a, b, msgAtoB, msgBtoA);

    final String newline = System.getProperty("line.separator");
    final String[] ss = s.split(newline);

    assertEquals(8, ss.length);

    assertEquals("msg1.modulus = 98", ss[2]);
    assertEquals("msg1.base = 10", ss[3]);
    assertEquals("msg1.a = 88", ss[4]);
    assertEquals("msg2.b = 23", ss[5]);
    assertEquals("keyA = null", ss[6]);
    assertEquals("keyB = null", ss[7]);
  }

  @Test
  public void testExchange()
  {
    DiffieHellmanKeyExchange a = new DiffieHellmanKeyExchange();
    DiffieHellmanKeyExchange b = new DiffieHellmanKeyExchange();

    final Map<MessagePayload, BigInteger> msgAtoB = a.computeMessageAtoB();
    final Map<MessagePayload, BigInteger> msgBtoA = b.computeMessageBtoA(msgAtoB);

    a.computeKey(msgBtoA);
    b.computeKey(msgAtoB);

    assertEquals(a.getKey(), b.getKey());
  }
}
