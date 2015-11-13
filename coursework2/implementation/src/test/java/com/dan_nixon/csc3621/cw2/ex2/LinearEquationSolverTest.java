package com.dan_nixon.csc3621.cw2.ex2;

import java.math.BigInteger;
import org.junit.*;
import static org.junit.Assert.*;

public class LinearEquationSolverTest
{
  @Test
  public void testInverse_Dataset1()
  {
    BigInteger x = BigInteger.valueOf(3);
    BigInteger y = BigInteger.valueOf(7);

    BigInteger inverse = LinearEquationSolver.modularInverse(x, y);
    assertEquals(BigInteger.valueOf(5), inverse);
    assertEquals(x.modInverse(y), inverse);
  }

  @Test
  public void testInverse_Dataset2()
  {
    BigInteger x = BigInteger.valueOf(27);
    BigInteger y = BigInteger.valueOf(392);

    BigInteger inverse = LinearEquationSolver.modularInverse(x, y);
    assertEquals(BigInteger.valueOf(363), inverse);
    assertEquals(x.modInverse(y), inverse);
  }

  @Test
  public void testSolveQ2a()
  {
    final BigInteger n = new BigInteger("643808006803554439230129854961492699151386107534013432918073439524138264842370630061369715394739134090922937332590384720397133335969549256322620979036686633213903952966175107096769180017646161851573147596390153");
    final BigInteger a = new BigInteger("34325464564574564564768795534569998743457687643234566579654234676796634378768434237897634345765879087764242354365767869780876543424");
    final BigInteger b = new BigInteger("45292384209127917243621242398573220935835723464332452353464376432246757234546765745246457656354765878442547568543334677652352657235");

    BigInteger x = LinearEquationSolver.solve(a, b, n);

    final BigInteger v = a.multiply(x).add(b).mod(n);
    assertEquals(BigInteger.ZERO, v);

    final BigInteger expectedX = new BigInteger("421183184045396008847949672535189703891596775623386918532288953516218079938085028752886155666620391419813631317167316631819337277694338966865265179264143550762845428781671313603290317056892475467340480506865970");
    assertEquals(expectedX, x);
  }

  @Test
  public void testSolveQ2b()
  {
    final BigInteger n = new BigInteger("342487235325934582350235837853456029394235268328294285895982432387582570234238487625923289526382379523573265963293293839298595072093573204293092705623485273893582930285732889238492377364284728834632342522323422");
    final BigInteger a = new BigInteger("34325464564574564564768795534569998743457687643234566579654234676796634378768434237897634345765879087764242354365767869780876543424");
    final BigInteger b = new BigInteger("24243252873562935279236582385723952735639239823957923562835832582635283562852252525256882909285959238420940257295265329820035324646");

    BigInteger x = LinearEquationSolver.solve(a, b, n);

    final BigInteger expectedX = null;
    assertEquals(expectedX, x);
  }
}
