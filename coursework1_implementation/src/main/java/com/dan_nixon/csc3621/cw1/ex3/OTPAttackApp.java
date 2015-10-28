package com.dan_nixon.csc3621.cw1.ex3;

import java.util.Map;
import java.io.File;
import java.io.IOException;
import com.dan_nixon.csc3621.cw1.Utils;

public class OTPAttackApp
{
  /**
   * Main application entry point.
   *
   * Usage:
   * Analysis: --analysis --target-cipher cipher.bin analysis_cipher.bin (--verbose) (--match-threshold 0.9)
   *
   * @param args Program arguments
   */
  public static void main(String[] args) throws IOException
  {
    Map<String, String> options = Utils.parseCommandLine(args);
    String result = "";

    if (options.containsKey("analysis"))
    {
      System.out.println("Cryptanalysis for file: " + options.get("target-cipher"));
      result = runCryptanalysisMode(options);
    }
    else if (options.containsKey("xor"))
    {
      File fileA = new File(options.get("a"));
      File fileB = new File(options.get("b"));

      byte[] dataA = Utils.readBinaryFile(fileA);
      byte[] dataB = Utils.readBinaryFile(fileB);

      byte[] xor = Utils.arrayXor(dataA, dataB);

      if (options.containsKey("ascii"))
        result = Utils.byteArrayToString(xor);
      else if (options.containsKey("hex"))
        result = Utils.byteArrayToHexString(xor);
      System.out.println(result);

      if (options.containsKey("output"))
      {
        File out = new File(options.get("output"));
        Utils.writeBinaryFile(out, xor);
      }
    }

    System.out.println(result);
  }

  /**
   * Process running in cryptanalysis mode.
   *
   * @param options Parsed program options
   * @return Best guess for plain text message
   */
  public static String runCryptanalysisMode(Map<String, String> options)
    throws IOException
  {
    // Also output analysis strings
    boolean verbose = options.containsKey("verbose");

    // Get character match tolerance
    double charMatchThreshold = 0.95;
    if (options.containsKey("match-threshold"))
      charMatchThreshold = Double.parseDouble(options.get("match-threshold"));

    // Cipher file to be cryptanalysed
    File targetCipherFile = new File(options.get("target-cipher"));

    // Cipher files to use in analysis
    int numCipherFiles = Integer.parseInt(options.get("_num_positional"));
    File[] analysisCipherFiles = new File[numCipherFiles];
    for (int i = 0; i < numCipherFiles; i++)
      analysisCipherFiles[i] = new File(options.get("_p" + i));

    // Run analysis
    byte[] targetCipher = Utils.readBinaryFile(targetCipherFile);
    OTPAttack otpa = new OTPAttack(analysisCipherFiles);
    String[] analysisStrings = otpa.getAnalysisStrings(targetCipher);
    String messageGuess = otpa.guessPlainTextFromAnalysis(analysisStrings, targetCipher.length, charMatchThreshold);

    if (verbose)
    {
      for (int i = 0; i < analysisStrings.length; i++)
        System.out.println(i + ":\t" + analysisStrings[i]);
    }

    return messageGuess;
  }
}
