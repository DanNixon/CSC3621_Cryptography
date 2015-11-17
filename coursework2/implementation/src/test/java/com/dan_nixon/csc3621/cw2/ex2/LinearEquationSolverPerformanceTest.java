package com.dan_nixon.csc3621.cw2.ex2;

import java.math.BigInteger;
import org.junit.*;

public class LinearEquationSolverPerformanceTest
{
  public final int NUMBER_OPERATIONS = 10000;

  @Test
  public void testSolveMyImplementation()
  {
    final long startTime = System.currentTimeMillis();

    final BigInteger n = new BigInteger("643808006803554439230129854961492699151386107534013432918073439524138264842370630061369715394739134090922937332590384720397133335969549256322620979036686633213903952966175107096769180017646161851573147596390153");
    final BigInteger a = new BigInteger("34325464564574564564768795534569998743457687643234566579654234676796634378768434237897634345765879087764242354365767869780876543424");
    final BigInteger b = new BigInteger("45292384209127917243621242398573220935835723464332452353464376432246757234546765745246457656354765878442547568543334677652352657235");

    for (int i = 0; i < NUMBER_OPERATIONS; i++)
    {
      BigInteger x = LinearEquationSolver.solve(a, b, n);
    }

    final long endTime = System.currentTimeMillis();
    final long deltaTime = endTime - startTime;

    System.out.println("Using LinearEquationSolver.modularInverse: " + deltaTime + "ms");
  }

  @Test
  public void testSolveJSL()
  {
    final long startTime = System.currentTimeMillis();

    final BigInteger n = new BigInteger("643808006803554439230129854961492699151386107534013432918073439524138264842370630061369715394739134090922937332590384720397133335969549256322620979036686633213903952966175107096769180017646161851573147596390153");
    final BigInteger a = new BigInteger("34325464564574564564768795534569998743457687643234566579654234676796634378768434237897634345765879087764242354365767869780876543424");
    final BigInteger b = new BigInteger("45292384209127917243621242398573220935835723464332452353464376432246757234546765745246457656354765878442547568543334677652352657235");

    for (int i = 0; i < NUMBER_OPERATIONS; i++)
    {
      BigInteger x = LinearEquationSolver.solveJSL(a, b, n);
    }

    final long endTime = System.currentTimeMillis();
    final long deltaTime = endTime - startTime;

    System.out.println("Using BigInteger.modInverse: " + deltaTime + "ms");
  }
}
