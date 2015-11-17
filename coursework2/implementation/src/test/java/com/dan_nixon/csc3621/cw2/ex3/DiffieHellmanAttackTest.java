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
    DiffieHellmanKeyExchange a = new DiffieHellmanKeyExchange(BigInteger.valueOf(1234));
    DiffieHellmanKeyExchange b = new DiffieHellmanKeyExchange(BigInteger.valueOf(9876));
    DiffieHellmanAttack e = new DiffieHellmanAttack(BigInteger.valueOf(3456));

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
    final String expected = "secretA = 1234" + newline +
                            "secretE = 3456" + newline +
                            "secretB = 9876" + newline +
                            "msgAtoE.modulus = 98" + newline +
                            "msgAtoE.base = 10" + newline +
                            "msgAtoE.a = 88" + newline +
                            "msgEtoA.b = 23" + newline +
                            "msgEtoB.modulus = 27" + newline +
                            "msgEtoB.base = 46" + newline +
                            "msgEtoB.a = 91" + newline +
                            "msgBtoE.b = 87" + newline +
                            "keyA = null" + newline +
                            "keyA_E = null" + newline +
                            "keyB_E = null" + newline +
                            "keyB = null";

    assertEquals(expected, s);
  }

  @Test
  public void testAttack()
  {
    DiffieHellmanKeyExchange a = new DiffieHellmanKeyExchange(BigInteger.valueOf(1234));
    DiffieHellmanKeyExchange b = new DiffieHellmanKeyExchange(BigInteger.valueOf(9876));
    DiffieHellmanAttack e = new DiffieHellmanAttack(BigInteger.valueOf(3456));

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
