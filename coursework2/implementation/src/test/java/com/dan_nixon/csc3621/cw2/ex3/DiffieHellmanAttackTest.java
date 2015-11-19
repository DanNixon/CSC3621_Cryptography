package com.dan_nixon.csc3621.cw2.ex3;

import java.math.BigInteger;
import java.util.EnumMap;
import java.util.Map;
import org.junit.*;
import static org.junit.Assert.*;

public class DiffieHellmanAttackTest
{
  @Test
  public void testToString()
  {
    DiffieHellmanKeyExchange a = new DiffieHellmanKeyExchange();
    DiffieHellmanKeyExchange b = new DiffieHellmanKeyExchange();
    DiffieHellmanAttack e = new DiffieHellmanAttack();

    Map<MessagePayload, BigInteger> msgAtoE = new EnumMap<MessagePayload, BigInteger>(MessagePayload.class);
    msgAtoE.put(MessagePayload.MOD, BigInteger.valueOf(98));
    msgAtoE.put(MessagePayload.BASE, BigInteger.valueOf(10));
    msgAtoE.put(MessagePayload.A, BigInteger.valueOf(88));

    Map<MessagePayload, BigInteger> msgEtoA = new EnumMap<MessagePayload, BigInteger>(MessagePayload.class);
    msgEtoA.put(MessagePayload.B, BigInteger.valueOf(23));

    Map<MessagePayload, BigInteger> msgEtoB = new EnumMap<MessagePayload, BigInteger>(MessagePayload.class);
    msgEtoB.put(MessagePayload.MOD, BigInteger.valueOf(27));
    msgEtoB.put(MessagePayload.BASE, BigInteger.valueOf(46));
    msgEtoB.put(MessagePayload.A, BigInteger.valueOf(91));

    Map<MessagePayload, BigInteger> msgBtoE = new EnumMap<MessagePayload, BigInteger>(MessagePayload.class);
    msgBtoE.put(MessagePayload.B, BigInteger.valueOf(87));

    final String s = DiffieHellmanAttack.toString(a, b, e,
                                                  msgAtoE, msgEtoA,
                                                  msgEtoB, msgBtoE);

    final String newline = System.getProperty("line.separator");
    final String[] ss = s.split(newline);

    assertEquals(15, ss.length);

    assertEquals("msgAtoE.modulus = 98", ss[3]);
    assertEquals("msgAtoE.base = 10", ss[4]);
    assertEquals("msgAtoE.a = 88", ss[5]);
    assertEquals("msgEtoA.b = 23", ss[6]);
    assertEquals("msgEtoB.modulus = 27", ss[7]);
    assertEquals("msgEtoB.base = 46", ss[8]);
    assertEquals("msgEtoB.a = 91", ss[9]);
    assertEquals("msgBtoE.b = 87", ss[10]);
    assertEquals("keyA = null", ss[11]);
    assertEquals("keyA_E = null", ss[12]);
    assertEquals("keyB_E = null", ss[13]);
    assertEquals("keyB = null", ss[14]);
  }

  @Test
  public void testAttack()
  {
    DiffieHellmanKeyExchange a = new DiffieHellmanKeyExchange();
    DiffieHellmanKeyExchange b = new DiffieHellmanKeyExchange();
    DiffieHellmanAttack e = new DiffieHellmanAttack();

    final Map<MessagePayload, BigInteger> msgAtoE = a.computeMessageAtoB();
    final Map<MessagePayload, BigInteger> msgEtoA = e.computeMessageEtoA(msgAtoE);

    final Map<MessagePayload, BigInteger> msgEtoB = e.computeMessageEtoB();
    final Map<MessagePayload, BigInteger> msgBtoE = b.computeMessageBtoA(msgEtoB);

    a.computeKey(msgEtoA);
    b.computeKey(msgEtoB);
    e.computeKeyB(msgBtoE);

    assertFalse(a.getKey().equals(b.getKey()));
    assertEquals(e.getKeyA(), a.getKey());
    assertEquals(e.getKeyB(), b.getKey());
  }
}
