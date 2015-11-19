package com.dan_nixon.csc3621.cw2.ex3;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Map;

public class DiffieHellmanKeyExchangeApp
{
  /**
   * Main entry point.
   * Performs a Diffie-Hellman key exchange between participants A and B (both
   * played by the local application).
   * Usage: [transcript filename]
   * @param args Parameters
   */
  public static void main(String[] args) throws IOException
  {
    // Do key exchange
    DiffieHellmanKeyExchange a = new DiffieHellmanKeyExchange();
    DiffieHellmanKeyExchange b = new DiffieHellmanKeyExchange();

    final Map<MessagePayload, BigInteger> msgAtoB = a.computeMessageAtoB();
    final Map<MessagePayload, BigInteger> msgBtoA = b.computeMessageBtoA(msgAtoB);

    a.computeKey(msgBtoA);
    b.computeKey(msgAtoB);

    // Create transcript string
    final String s = DiffieHellmanKeyExchange.toString(a, b, msgAtoB, msgBtoA);

    // Create output file
    File outFile = new File(args[0]);
    outFile.createNewFile();

    // Output results to file
    PrintWriter writer = new PrintWriter(new FileOutputStream(outFile));
    try
    {
      writer.print(s);
      writer.print(System.getProperty("line.separator"));
    }
    finally
    {
      writer.close();
    }
  }
}
