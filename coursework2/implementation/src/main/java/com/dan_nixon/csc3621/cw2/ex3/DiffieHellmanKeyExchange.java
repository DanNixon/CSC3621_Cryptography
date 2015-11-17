package com.dan_nixon.csc3621.cw2.ex3;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.EnumMap;
import java.util.Map;

/**
 * Represents an element in a message sent in the Diffie-Helman key exchange.
 */
enum MessagePayload
{
  MOD,
  BASE,
  A,
  B
}

/**
 * Class representing one participant in a Diffie-Hellman key exchange (either A or B).
 */
public class DiffieHellmanKeyExchange
{
  public static final int PRIME = 15485863;

  /**
  * Creates a string representation of a Diffie-Hellman key exchange.
  * @param a Participant A
  * @param b Participant B
  * @param msgAtoB Message from A to B
  * @param msgBtoA Message from B to A
  * @return String representation of exchange
  */
  public static String toString(DiffieHellmanKeyExchange a,
                                DiffieHellmanKeyExchange b,
                                Map<MessagePayload, BigInteger> msgAtoB,
                                Map<MessagePayload, BigInteger> msgBtoA)
  {
    StringBuilder sb = new StringBuilder();

    final String newline = System.getProperty("line.separator");

    sb.append("secretA = ");
    sb.append(a.m_secret);
    sb.append(newline);

    sb.append("secretB = ");
    sb.append(b.m_secret);
    sb.append(newline);

    sb.append("msg1.modulus = ");
    sb.append(msgAtoB.get(MessagePayload.MOD));
    sb.append(newline);

    sb.append("msg1.base = ");
    sb.append(msgAtoB.get(MessagePayload.BASE));
    sb.append(newline);

    sb.append("msg1.a = ");
    sb.append(msgAtoB.get(MessagePayload.A));
    sb.append(newline);

    sb.append("msg2.b = ");
    sb.append(msgBtoA.get(MessagePayload.B));
    sb.append(newline);

    sb.append("keyA = ");
    sb.append(a.m_key);
    sb.append(newline);

    sb.append("keyB = ");
    sb.append(b.m_key);

    return sb.toString();
  }

  public DiffieHellmanKeyExchange(BigInteger secret)
  {
    m_secret = secret;
    m_g = null;
    m_n = null;
    m_key = null;
  }

  /**
   * Computes the message to be sent from participant A to participant B.
   * @return Message structure
   */
  public Map<MessagePayload, BigInteger> computeMessageAtoB()
  {
    Map<MessagePayload, BigInteger> msgAtoB = new EnumMap<MessagePayload, BigInteger>(MessagePayload.class);

    SecureRandom rng = new SecureRandom();
    m_n = new BigInteger(1024, rng);
    m_g = BigInteger.valueOf(rng.nextInt(PRIME - 1) + 2);

    BigInteger a = m_g.modPow(m_secret, m_n);

    msgAtoB.put(MessagePayload.MOD, m_n);
    msgAtoB.put(MessagePayload.BASE, m_g);
    msgAtoB.put(MessagePayload.A, a);

    return msgAtoB;
  }

  /**
   * Computes the message to be sent from participant B to participant A.
   * @param msgAtoB Message from participant A to participant B
   * @return Message structure
   */
  public Map<MessagePayload, BigInteger> computeMessageBtoA(Map<MessagePayload, BigInteger> msgAtoB)
  {
    Map<MessagePayload, BigInteger> msgBtoA = new EnumMap<MessagePayload, BigInteger>(MessagePayload.class);

    m_g = msgAtoB.get(MessagePayload.BASE);
    m_n = msgAtoB.get(MessagePayload.MOD);

    BigInteger b = m_g.modPow(m_secret, m_n);

    msgBtoA.put(MessagePayload.B, b);

    return msgBtoA;
  }

  /**
   * Computes key from message.
   * @param msg Message containing a or b
   */
  public void computeKey(Map<MessagePayload, BigInteger> msg) throws IllegalStateException
  {
    if(m_n == null)
      throw new IllegalStateException("No modulus");

    BigInteger v = null;
    if (msg.containsKey(MessagePayload.A))
      v = msg.get(MessagePayload.A);
    else if (msg.containsKey(MessagePayload.B))
      v = msg.get(MessagePayload.B);
    else
      throw new IllegalStateException("No valid value");

    m_key = v.modPow(m_secret, m_n);
  }

  /**
   * Returns the secret for this participant.
   * @return Secret
   */
  public BigInteger getSecret()
  {
    return m_secret;
  }

  /**
   * Returns the computed key.
   * Must be called after computeKey().
   * @return Key, null if not yet computed
   */
  public BigInteger getKey() throws IllegalStateException
  {
    return m_key;
  }

  private final BigInteger m_secret;
  private BigInteger m_g;
  private BigInteger m_n;
  private BigInteger m_key;
}
