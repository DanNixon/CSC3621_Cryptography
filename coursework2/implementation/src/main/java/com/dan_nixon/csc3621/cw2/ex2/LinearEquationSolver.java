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

  /**
   * Solves the linear equation ax + b = 0 in Z_n using the
   * BigInteger.modInverse function o obtain the modular inverse.
   * @param a Value of a
   * @param b Value of b
   * @param n Modulus size
   * @return Value of x, null if could not solve
   */
  public static BigInteger solveJSL(BigInteger a, BigInteger b, BigInteger n)
  {
    BigInteger inverseA = a.modInverse(n);

    // x = -b . a^-1
    BigInteger x = b.negate().multiply(inverseA).mod(n);

    return x;
  }

  /**
   * Obtains the modular inverse x^-1 , such that x^-1 * x = 1 (mod n)
   * @param x
   * @param n
   * @return Modular inverse, null if no inverse exists
   */
  public static BigInteger modularInverse(BigInteger x, BigInteger n)
  {
    ExtendedEuclidean ee = new ExtendedEuclidean();
    ee.gcd(x, n);

    if (!ee.getD().equals(BigInteger.ONE))
      return null;

    return ee.getS().mod(n);
  }
}
