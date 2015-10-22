package com.dan_nixon.csc3621.cw1.ex1;

import java.util.Map;
import java.io.File;
import java.io.IOException;
import com.dan_nixon.csc3621.cw1.Utils;
import com.dan_nixon.csc3621.cw1.ex1.FrequencyAnalysis;

public class Analysis
{
  public static void main(String[] args) throws IOException
  {
    Map<String, String> options = Utils.parseCommandLine(args);

    int numPlainFiles = Integer.parseInt(options.get("_num_positional"));
    File[] plainFiles = new File[numPlainFiles];
    for (int i = 0; i < numPlainFiles; i++)
      plainFiles[i] = new File(options.get("_p"+i));

    // Do frequency analysis on two files and perform analysis on the results
    if (options.containsKey("cipher-file"))
    {
      File cipherFile = new File(options.get("cipher-file"));
      runCipherAnalysisMode(plainFiles, cipherFile);
    }
    // Just do frequency analysis of plain text
    else
    {
      runFreqAnalysisMode(plainFiles);
    }
  }

  /**
   * Perform simple frequency analysis on a set of plain text files.
   *
   * @param plainFiles Array of files to read
   */
  private static void runFreqAnalysisMode(File[] plainFiles) throws IOException
  {
    // Process plain text files and get probability distribution
    FrequencyCounter plainCount = new FrequencyCounter();
    plainCount.count(plainFiles);

    FrequencyAnalysis fa = new FrequencyAnalysis();
    fa.setPlainTextCount(plainCount);

    double[] plainDistrib = fa.getPlainTextDistribution();

    // Output probability distribution
    System.out.println("Frequency analysis results:");
    System.out.println("(probability distribution normalised to 1.0)");
    for (int i = 0; i < plainDistrib.length; i++)
    {
      StringBuilder sb = new StringBuilder();
      sb.append(Utils.getCharFromIndex(i));
      sb.append(" -\t");
      sb.append(plainDistrib[i]);
      System.out.println(sb);
    }
  }

  /**
   * Perform cryptanalysis of a cipher text using several plain text files to
   * obtain a normal plain text probability distribution.
   *
   * @param plainFiles Array of plain text files
   * @param cipherFile Cipher text file to analyse
   */
  private static void runCipherAnalysisMode(File[] plainFiles, File cipherFile) throws IOException
  {
    // Load files and get probability distributions
    FrequencyCounter plainCount = new FrequencyCounter();
    plainCount.count(plainFiles);
    FrequencyCounter cipherCount = new FrequencyCounter();
    cipherCount.count(cipherFile);

    FrequencyAnalysis fa = new FrequencyAnalysis();
    fa.setPlainTextCount(plainCount);
    fa.setCipherTextCount(cipherCount);

    double[] plainDistrib = fa.getPlainTextDistribution();
    double[] cipherDistrib = fa.getCipherTextDistribution();

    // Output probability distributions of plain and cipher text characters
    System.out.println("Cryptanalysis results:");
    System.out.println("(probability distribution normalised to 1.0)");
    System.out.println(fa.toString());

    // Perform analysis to brute force test for a simple shift cipher
    int likelyRotation = fa.rotationAnalysis();
    System.out.println("Best fit rotation: " + likelyRotation);
  }
}
