package com.dan_nixon.csc3621.cw2.ex3;

import java.math.BigInteger;
import java.util.EnumMap;
import java.util.Map;

enum MessagePayload
{
  PLD_MOD,
  PLD_BASE,
  PLD_A,
  PLD_B
}

public class DiffieHellmanKeyExchange
{
  public DiffieHellmanKeyExchange(BigInteger secretA, BigInteger secretB)
  {
    m_secretA = secretA;
    m_secretB = secretB;
    
    m_msgAtoB = null;
    m_msgBtoA = null;
    m_keyA = null;
    m_keyB = null;
  }
  
  public void computeMessageAtoB()
  {
    m_msgBtoA = new EnumMap<MessagePayload, BigInteger>(MessagePayload.class);
    
    //TODO
  }
  
  public void computeMessageBtoA()
  {
    if(m_msgAtoB == null)
      throw new IllegalStateException("Message from A to B has not been computed");
    
    m_msgAtoB = new EnumMap<MessagePayload, BigInteger>(MessagePayload.class);
    
    //TODO
  }
  
  public void computeKeyA()
  {
    if(m_msgBtoA == null)
      throw new IllegalStateException("Message from B to A has not been computed");
    
    //TODO
  }
  
  public void computeKeyB()
  {
    if(m_msgAtoB == null)
      throw new IllegalStateException("Message from A to B has not been computed");
    
    //TODO
  }
  
  public Map<MessagePayload, BigInteger> getMessageAtoB()
  {
    if(m_msgAtoB == null)
      throw new IllegalStateException("Message from A to B has not been computed");
    
    return m_msgAtoB;
  }
  
  public Map<MessagePayload, BigInteger> getMessageBtoA()
  {
    if(m_msgBtoA == null)
      throw new IllegalStateException("Message from B to A has not been computed");
    
    return m_msgBtoA;
  }
  
  public BigInteger getKeyA()
  {
    if(m_keyA == null)
      throw new IllegalStateException("Key A has not been computed");
    
    return m_keyA;
  }
  
  public BigInteger getKeyB()
  {
    if(m_keyB == null)
      throw new IllegalStateException("Key B has not been computed");
    
    return m_keyA;
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
      sb.append(m_msgBtoA.get(MessagePayload.PLD_MOD));
    sb.append(System.getProperty("line.separator"));
    
    sb.append("msg1.base=");
    if (m_msgAtoB == null)
      sb.append((Object) null);
    else
      sb.append(m_msgBtoA.get(MessagePayload.PLD_BASE));
    sb.append(System.getProperty("line.separator"));
    
    sb.append("msg1.valueA=");
    if (m_msgAtoB == null)
      sb.append((Object) null);
    else
      sb.append(m_msgBtoA.get(MessagePayload.PLD_A));
    sb.append(System.getProperty("line.separator"));
    
    sb.append("msg2.valueB=");
    if (m_msgBtoA == null)
      sb.append((Object) null);
    else
      sb.append(m_msgBtoA.get(MessagePayload.PLD_B));
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
