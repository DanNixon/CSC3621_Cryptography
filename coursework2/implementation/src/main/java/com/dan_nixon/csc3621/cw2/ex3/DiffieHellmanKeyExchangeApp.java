package com.dan_nixon.csc3621.cw2.ex3;

import java.math.BigInteger;
import java.util.Map;

public class DiffieHellmanKeyExchangeApp
{
  /**
   * Main entry point.
   * Performs a Diffie-Hellman key exchange between participants A and B (both
   * played by the local application).
   * Usage: [participant A secret] [participant B secret]
   * @param args Parameters
   */
  public static void main(String[] args)
  {
    final BigInteger secretA = new BigInteger(args[0]);
    final BigInteger secretB = new BigInteger(args[1]);

    DiffieHellmanKeyExchange a = new DiffieHellmanKeyExchange(secretA);
    DiffieHellmanKeyExchange b = new DiffieHellmanKeyExchange(secretB);

    final Map<MessagePayload, BigInteger> msgAtoB = a.computeMessageAtoB();
    final Map<MessagePayload, BigInteger> msgBtoA = b.computeMessageBtoA(msgAtoB);

    a.computeKey(msgBtoA);
    b.computeKey(msgAtoB);

    final String s = DiffieHellmanKeyExchange.toString(a, b, msgAtoB, msgBtoA);

    System.out.println(s);
  }
}
