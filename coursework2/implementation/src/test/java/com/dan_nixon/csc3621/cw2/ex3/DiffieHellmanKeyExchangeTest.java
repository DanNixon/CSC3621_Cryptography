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
    DiffieHellmanKeyExchange a = new DiffieHellmanKeyExchange(BigInteger.valueOf(1234));
    DiffieHellmanKeyExchange b = new DiffieHellmanKeyExchange(BigInteger.valueOf(9876));

    Map<MessagePayload, BigInteger> msgAtoB = new EnumMap<MessagePayload, BigInteger>(MessagePayload.class);
    msgAtoB.put(MessagePayload.MOD, BigInteger.valueOf(98));
    msgAtoB.put(MessagePayload.BASE, BigInteger.valueOf(10));
    msgAtoB.put(MessagePayload.A, BigInteger.valueOf(88));

    Map<MessagePayload, BigInteger> msgBtoA = new EnumMap<MessagePayload, BigInteger>(MessagePayload.class);
    msgBtoA.put(MessagePayload.B, BigInteger.valueOf(23));

    final String s = DiffieHellmanKeyExchange.toString(a, b, msgAtoB, msgBtoA);

    final String newline = System.getProperty("line.separator");
    final String expected = "secretA = 1234" + newline +
                            "secretB = 9876" + newline +
                            "msg1.modulus = 98" + newline +
                            "msg1.base = 10" + newline +
                            "msg1.a = 88" + newline +
                            "msg2.b = 23" + newline +
                            "keyA = null" + newline +
                            "keyB = null";

    assertEquals(expected, s);
  }

  @Test
  public void testExchange()
  {
    DiffieHellmanKeyExchange a = new DiffieHellmanKeyExchange(BigInteger.valueOf(1234));
    DiffieHellmanKeyExchange b = new DiffieHellmanKeyExchange(BigInteger.valueOf(9876));

    final Map<MessagePayload, BigInteger> msgAtoB = a.computeMessageAtoB();
    final Map<MessagePayload, BigInteger> msgBtoA = b.computeMessageBtoA(msgAtoB);

    a.computeKey(msgBtoA);
    b.computeKey(msgAtoB);

    assertEquals(a.getKey(), b.getKey());
  }
}
