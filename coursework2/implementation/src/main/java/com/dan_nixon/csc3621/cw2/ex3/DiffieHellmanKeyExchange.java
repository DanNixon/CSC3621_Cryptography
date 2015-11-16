package com.dan_nixon.csc3621.cw2.ex3;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.EnumMap;
import java.util.Map;

enum ExchangeRole
{
  A,
  B
}

enum MessagePayload
{
  MOD,
  BASE,
  A,
  B
}

public class DiffieHellmanKeyExchange
{
  public static final int PRIME = 15485863;
  
  public DiffieHellmanKeyExchange(BigInteger secretA, BigInteger secretB)
  {
    m_secretA = secretA;
    m_secretB = secretB;
    
    m_msgAtoB = null;
    m_msgBtoA = null;
    m_keyA = null;
    m_keyB = null;
  }
  
  /**
   * Computes the message to be sent from participant A to participant B.
   */
  public void computeMessageAtoB()
  {
    m_msgAtoB = new EnumMap<MessagePayload, BigInteger>(MessagePayload.class);
    
    SecureRandom rng = new SecureRandom();
    BigInteger n = new BigInteger(1024, rng);
    BigInteger g = BigInteger.valueOf(rng.nextInt(PRIME - 1) + 2);
    
    m_msgAtoB.put(MessagePayload.MOD, n);
    m_msgAtoB.put(MessagePayload.BASE, g);
    
    BigInteger a = g.modPow(m_secretA, n);
    
    m_msgAtoB.put(MessagePayload.A, a);
  }
  
  /**
   * Computes the message to be sent from participant B to participant A.
   * Must be called after computeMessageAtoB().
   */
  public void computeMessageBtoA()
  {
    if(m_msgAtoB == null)
      throw new IllegalStateException("Message from A to B has not been computed");
    
    m_msgBtoA = new EnumMap<MessagePayload, BigInteger>(MessagePayload.class);
    
    BigInteger g = m_msgAtoB.get(MessagePayload.BASE);
    BigInteger n = m_msgAtoB.get(MessagePayload.MOD);
    
    BigInteger b = g.modPow(m_secretB, n);
    
    m_msgBtoA.put(MessagePayload.B, b);
  }
  
  /**
   * Computes key for participant A.
   * Must be called after computeMessageBtoA().
   */
  public void computeKeyA() throws IllegalStateException
  {
    if(m_msgBtoA == null)
      throw new IllegalStateException("Message from B to A has not been computed");
    
    BigInteger g = m_msgAtoB.get(MessagePayload.BASE);
    BigInteger n = m_msgAtoB.get(MessagePayload.MOD);
    
    BigInteger b = m_msgBtoA.get(MessagePayload.B);
    
    m_keyA = b.modPow(m_secretA, n);
  }
  
  /**
   * Computes key for participant B.
   * Must be called after computeMessageAtoB().
   */
  public void computeKeyB() throws IllegalStateException
  {
    if(m_msgAtoB == null)
      throw new IllegalStateException("Message from A to B has not been computed");
    
    BigInteger g = m_msgAtoB.get(MessagePayload.BASE);
    BigInteger n = m_msgAtoB.get(MessagePayload.MOD);
    
    BigInteger a = m_msgAtoB.get(MessagePayload.A);
    
    m_keyB = a.modPow(m_secretB, n);
  }
  
  /**
   * Returns the message structure for the message sent from participant A to participant B.
   * Must be called after computeMessageAtoB().
   * @return Message payload
   */
  public Map<MessagePayload, BigInteger> getMessageAtoB() throws IllegalStateException
  {
    if(m_msgAtoB == null)
      throw new IllegalStateException("Message from A to B has not been computed");
    
    return m_msgAtoB;
  }
  
  /**
   * Returns the message structure for the message sent from participant B to participant A.
   * Must be called after computeMessageBtoA().
   * @return Message payload
   */
  public Map<MessagePayload, BigInteger> getMessageBtoA() throws IllegalStateException
  {
    if(m_msgBtoA == null)
      throw new IllegalStateException("Message from B to A has not been computed");
    
    return m_msgBtoA;
  }
  
  /**
   * Returns the computed key for participant A.
   * Must be called after computeKeyA().
   * @return Key A
   */
  public BigInteger getKeyA() throws IllegalStateException
  {
    if(m_keyA == null)
      throw new IllegalStateException("Key A has not been computed");
    
    return m_keyA;
  }
  
  /**
   * Returns the computed key for participant B.
   * Must be called after computeKeyB().
   * @return Key B
   */
  public BigInteger getKeyB() throws IllegalStateException
  {
    if(m_keyB == null)
      throw new IllegalStateException("Key B has not been computed");
    
    return m_keyB;
  }
  
  @Override
  public String toString()
  {
    StringBuilder sb = new StringBuilder();
    
    sb.append("secretA=");
    sb.append(m_secretA);
    sb.append(System.getProperty("line.separator"));
    
    sb.append("secretB=");
    sb.append(m_secretB);
    sb.append(System.getProperty("line.separator"));
    
    sb.append("msg1.modulus=");
    if (m_msgAtoB == null)
      sb.append((Object) null);
    else
      sb.append(m_msgAtoB.get(MessagePayload.MOD));
    sb.append(System.getProperty("line.separator"));
    
    sb.append("msg1.base=");
    if (m_msgAtoB == null)
      sb.append((Object) null);
    else
      sb.append(m_msgAtoB.get(MessagePayload.BASE));
    sb.append(System.getProperty("line.separator"));
    
    sb.append("msg1.valueA=");
    if (m_msgAtoB == null)
      sb.append((Object) null);
    else
      sb.append(m_msgAtoB.get(MessagePayload.A));
    sb.append(System.getProperty("line.separator"));
    
    sb.append("msg2.valueB=");
    if (m_msgBtoA == null)
      sb.append((Object) null);
    else
      sb.append(m_msgBtoA.get(MessagePayload.B));
    sb.append(System.getProperty("line.separator"));
    
    sb.append("keyA=");
    sb.append(m_keyA);
    sb.append(System.getProperty("line.separator"));
    
    sb.append("keyB=");
    sb.append(m_keyB);
    
    return sb.toString();
  }
  
  private final BigInteger m_secretA;
  private final BigInteger m_secretB;
  private Map<MessagePayload, BigInteger> m_msgAtoB;
  private Map<MessagePayload, BigInteger> m_msgBtoA;
  private BigInteger m_keyA;
  private BigInteger m_keyB;
}
