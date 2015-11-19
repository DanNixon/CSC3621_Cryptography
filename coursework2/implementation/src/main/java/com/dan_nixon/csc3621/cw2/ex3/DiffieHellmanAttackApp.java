package com.dan_nixon.csc3621.cw2.ex3;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Map;

public class DiffieHellmanAttackApp
{
  /**
   * Main entry point.
   * Performs a "man in the middle" attack on the Diffie-Hellman key exchange
   * between participants A and B (all parties played by the local
   * application).
   * Usage: [transcript filename]
   * @param args Parameters
   */
  public static void main(String[] args) throws IOException
  {
    // Perform attack
    DiffieHellmanKeyExchange a = new DiffieHellmanKeyExchange();
    DiffieHellmanKeyExchange b = new DiffieHellmanKeyExchange();
    DiffieHellmanAttack e = new DiffieHellmanAttack();

    final Map<MessagePayload, BigInteger> msgAtoE = a.computeMessageAtoB();
    final Map<MessagePayload, BigInteger> msgEtoA = e.computeMessageEtoA(msgAtoE);

    final Map<MessagePayload, BigInteger> msgEtoB = e.computeMessageEtoB();
    final Map<MessagePayload, BigInteger> msgBtoE = b.computeMessageBtoA(msgEtoB);

    a.computeKey(msgEtoA);
    b.computeKey(msgEtoB);
    e.computeKeyB(msgBtoE);

    // Create transcript string
    final String s = DiffieHellmanAttack.toString(a, b, e,
                                                  msgAtoE, msgEtoA,
                                                  msgEtoB, msgBtoE);

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
