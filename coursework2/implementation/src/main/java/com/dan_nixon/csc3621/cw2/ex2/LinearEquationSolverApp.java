package com.dan_nixon.csc3621.cw2.ex2;

import java.math.BigInteger;

public class LinearEquationSolverApp
{
  /**
   * Main entry point.
   * Usage: [a] [b] [n]
   * @param args Parameters
   */
  public static void main(String[] args)
  {
    BigInteger a = new BigInteger(args[0]);
    BigInteger b = new BigInteger(args[1]);
    BigInteger n = new BigInteger(args[2]);
    
    BigInteger x = LinearEquationSolver.solve(a, b, n);
    
    if (x == null)
      System.out.println("No solution");
    else
      System.out.println(x);
  }
}
