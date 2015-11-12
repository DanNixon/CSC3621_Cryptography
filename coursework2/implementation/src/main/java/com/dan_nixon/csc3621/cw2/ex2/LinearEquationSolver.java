package com.dan_nixon.csc3621.cw2.ex2;

import java.math.BigInteger;

public class LinearEquationSolver
{
  public LinearEquationSolver()
  {
    m_x = null;
  }
  
  /**
   * SOlves the linear equation ax + b = 0 in Z_n
   * @param a Value of a
   * @param b Value of x
   * @param n Modulus size
   */
  public void solve(BigInteger a, BigInteger b, BigInteger n)
  {
  }
  
  /**
   * Returns the value of x such that ax+b=0
   * @return Value of x, null if could not solve
   */
  public BigInteger getX()
  {
    return m_x;
  }
  
  private BigInteger m_x;
}
