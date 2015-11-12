package com.dan_nixon.csc3621.cw2.ex1;

import java.math.BigInteger;

public class ExtendedEuclidean
{
  public ExtendedEuclidean()
  {
    m_d = null;
    m_s = null;
    m_t = null;
  }
  
  /**
   * Calculate the GCD of x and y using the extended Euclidean algorithm.
   * @param x Value of x
   * @param y Value of y
   */
  public void gcd(BigInteger x, BigInteger y)
  {
    BigInteger r0 = x;
    BigInteger r1 = y;
    BigInteger s0 = new BigInteger("1");
    BigInteger s1 = new BigInteger("0");
    BigInteger t0 = new BigInteger("0");
    BigInteger t1 = new BigInteger("1");
    
    while(!r1.equals(BigInteger.ZERO))
    {
      BigInteger q = r0.divide(r1);
      BigInteger r2 = r0.remainder(r1);
      
      BigInteger s2 = s0.subtract(s1.multiply(q));
      BigInteger t2 = t0.subtract(t1.multiply(q));
      
      r0 = r1;
      s0 = s1;
      t0 = t1;
      r1 = r2;
      s1 = s2;
      t1 = t2;
    }
    
    m_d = r0;
    m_s = s0;
    m_t = t0;
  }
  
  /**
   * Return value of d such that d = gcd(x, y).
   * @return Value of d, null if GCD not computed
   */
  public BigInteger getD()
  {
    return m_d;
  }
  
  /**
   * Return value of s such that xs + ty = d.
   * @return Value of s, null if GCD not computed
   */
  public BigInteger getS()
  {
    return m_s;
  }
  
  /**
   * Return value of s such that xs + ty = d.
   * @return Value of t, null if GCD not computed
   */
  public BigInteger getT()
  {
    return m_t;
  }
  
  @Override
  public String toString()
  {
    StringBuilder sb = new StringBuilder();
    
    sb.append("d=");
    sb.append(m_d);
    
    sb.append(System.getProperty("line.separator"));
    
    sb.append("s=");
    sb.append(m_s);
    
    sb.append(System.getProperty("line.separator"));
    
    sb.append("t=");
    sb.append(m_t);

    return sb.toString();
  }
  
  private BigInteger m_d;
  private BigInteger m_s;
  private BigInteger m_t;
}
