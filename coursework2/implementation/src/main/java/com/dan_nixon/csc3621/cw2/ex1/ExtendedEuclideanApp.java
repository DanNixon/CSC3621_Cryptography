package com.dan_nixon.csc3621.cw2.ex1;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;

public class ExtendedEuclideanApp
{
  /**
   * Main entry point.
   * Usage: [integer x] [integer y] [filename]
   * @param args Parameters
   * @throws java.io.IOException
   */
  public static void main(String[] args) throws IOException
  {
    // Get input data
    BigInteger x = new BigInteger(args[0]);
    BigInteger y = new BigInteger(args[1]);

    // Run GCD
    ExtendedEuclidean ee = new ExtendedEuclidean();
    ee.gcd(x, y);

    // Create output file
    File outFile = new File(args[2]);
    outFile.createNewFile();

    // Output results to file
    PrintWriter writer = new PrintWriter(new FileOutputStream(outFile));
    try
    {
      writer.print(ee);
    }
    finally
    {
      writer.close();
    }
  }
}
