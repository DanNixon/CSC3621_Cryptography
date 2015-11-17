package com.dan_nixon.csc3621.cw2.ex3;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.EnumMap;
import java.util.Map;

/**
 * Class representing the adversary participant in a Diffie-Hellman "man in the
 * middle" attack.
 */
public class DiffieHellmanAttack
{
  /**
   * Creates a string representation of an attack on aDiffie-Hellman key
   * exchange.
   * @param a Participant A
   * @param b Participant B
   * @param e Adversary
   * @param msgAtoE Message from A to adversary
   * @param msgEtoA Message from adversary to A
   * @param msgEtoB Message from adversary to B
   * @param msgBtoE Message from B to adversary
   * @return String representation of exchange
   */
  public static String toString(DiffieHellmanKeyExchange a,
                                DiffieHellmanKeyExchange b,
                                DiffieHellmanAttack e,
                                Map<MessagePayload, BigInteger> msgAtoE,
                                Map<MessagePayload, BigInteger> msgEtoA,
                                Map<MessagePayload, BigInteger> msgEtoB,
                                Map<MessagePayload, BigInteger> msgBtoE)
  {
    StringBuilder sb = new StringBuilder();

    final String newline = System.getProperty("line.separator");

    // Secrets
    sb.append("secretA = ");
    sb.append(a.getSecret());
    sb.append(newline);

    sb.append("secretE = ");
    sb.append(e.m_secret);
    sb.append(newline);

    sb.append("secretB = ");
    sb.append(b.getSecret());
    sb.append(newline);

    // Messages
    sb.append("msgAtoE.modulus = ");
    sb.append(msgAtoE.get(MessagePayload.MOD));
    sb.append(newline);

    sb.append("msgAtoE.base = ");
    sb.append(msgAtoE.get(MessagePayload.BASE));
    sb.append(newline);

    sb.append("msgAtoE.a = ");
    sb.append(msgAtoE.get(MessagePayload.A));
    sb.append(newline);

    sb.append("msgEtoA.b = ");
    sb.append(msgEtoA.get(MessagePayload.B));
    sb.append(newline);

    sb.append("msgEtoB.modulus = ");
    sb.append(msgEtoB.get(MessagePayload.MOD));
    sb.append(newline);

    sb.append("msgEtoB.base = ");
    sb.append(msgEtoB.get(MessagePayload.BASE));
    sb.append(newline);

    sb.append("msgEtoB.a = ");
    sb.append(msgEtoB.get(MessagePayload.A));
    sb.append(newline);

    sb.append("msgBtoE.b = ");
    sb.append(msgBtoE.get(MessagePayload.B));
    sb.append(newline);

    // Keys
    sb.append("keyA = ");
    sb.append(a.getKey());
    sb.append(newline);

    sb.append("keyA_E = ");
    sb.append(e.m_keyA);
    sb.append(newline);

    sb.append("keyB_E = ");
    sb.append(e.m_keyB);
    sb.append(newline);

    sb.append("keyB = ");
    sb.append(b.getKey());

    return sb.toString();
  }

  /**
   * Creates a new instance of a Diffie-Hellman attack adversary.
   * @param secret The adversary's secret for this session
   */
  public DiffieHellmanAttack(BigInteger secret)
  {
    m_secret = secret;
    m_g = null;
    m_n = null;
    m_keyA = null;
    m_keyB = null;
  }

  /**
   * Compute the message from the adversary to participant A given the message
   * from participant A.
   * Also computes the session key for communication with participant A.
   * @param msgAtoE Message from participant A
   * @return Message to participant A
   */
  public Map<MessagePayload, BigInteger> computeMessageEtoA(Map<MessagePayload, BigInteger> msgAtoE)
  {
    // Use g and N from participant A
    m_g = msgAtoE.get(MessagePayload.BASE);
    m_n = msgAtoE.get(MessagePayload.MOD);

    // Compute key A
    m_keyA = msgAtoE.get(MessagePayload.A).modPow(m_secret, m_n);

    // Calculate B
    BigInteger e = m_g.modPow(m_secret, m_n);

    // Generate message from adversary to participant A
    Map<MessagePayload, BigInteger> msgEtoA = new EnumMap<MessagePayload, BigInteger>(MessagePayload.class);
    msgEtoA.put(MessagePayload.B, e);

    return msgEtoA;
  }

  /**
   * Computes the message to participant B using the generator (g) and modulus
   * (N) sent by participant A.
   * Must be called after computeMessageEtoA().
   * @return Message to participant B
   */
  public Map<MessagePayload, BigInteger> computeMessageEtoB() throws IllegalStateException
  {
    if(m_n == null || m_g == null)
      throw new IllegalStateException("No modulus or generator");

    Map<MessagePayload, BigInteger> msgEtoB = new EnumMap<MessagePayload, BigInteger>(MessagePayload.class);

    BigInteger e = m_g.modPow(m_secret, m_n);

    msgEtoB.put(MessagePayload.MOD, m_n);
    msgEtoB.put(MessagePayload.BASE, m_g);
    msgEtoB.put(MessagePayload.A, e);

    return msgEtoB;
  }

  /**
   * Compute the session key for communication with participant B.
   * Must be called after computeMessageEtoA().
   * @param msgBtoE Message from participant B
   */
  public void computeKeyB(Map<MessagePayload, BigInteger> msgBtoE) throws IllegalStateException
  {
    if(m_n == null)
      throw new IllegalStateException("No modulus");

    m_keyB = msgBtoE.get(MessagePayload.B).modPow(m_secret, m_n);
  }

  /**
   * Returns the secret for the adversary.
   * @return Secret
   */
  public BigInteger getSecret()
  {
    return m_secret;
  }

  /**
   * Returns the computed key for participant A.
   * Must be called after computeMessageEtoA().
   * @return Key A, null if not yet computed
   */
  public BigInteger getKeyA()
  {
    return m_keyA;
  }

  /**
   * Returns the computed key for participant B.
   * Must be called after computeKeyB().
   * @return Key B, null if not yet computed
   */
  public BigInteger getKeyB() throws IllegalStateException
  {
    return m_keyB;
  }

  private final BigInteger m_secret;
  private BigInteger m_g;
  private BigInteger m_n;
  private BigInteger m_keyA;
  private BigInteger m_keyB;
}
