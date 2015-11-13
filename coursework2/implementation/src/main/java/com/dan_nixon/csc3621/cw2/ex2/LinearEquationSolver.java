package com.dan_nixon.csc3621.cw2.ex2;

import com.dan_nixon.csc3621.cw2.ex1.ExtendedEuclidean;
import java.math.BigInteger;

public class LinearEquationSolver
{
  /**
   * Solves the linear equation ax + b = 0 in Z_n
   * @param a Value of a
   * @param b Value of b
   * @param n Modulus size
   * @return Value of x, null if could not solve
   */
  public static BigInteger solve(BigInteger a, BigInteger b, BigInteger n)
  {
    BigInteger inverseA = modularInverse(a, n);
    if (inverseA == null)
      return null;

    // x = -b . a^-1
    BigInteger x = b.negate().multiply(inverseA).mod(n);

    return x;
  }

  public static BigInteger modularInverse(BigInteger x, BigInteger y)
  {
    ExtendedEuclidean ee = new ExtendedEuclidean();
    ee.gcd(x, y);

    if (!ee.getD().equals(BigInteger.ONE))
      return null;

    return ee.getS().mod(y);
  }
}
