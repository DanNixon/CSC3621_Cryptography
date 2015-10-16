package com.dan_nixon.csc3621.coursework1.ex1;

import java.io.File;
import java.io.IOException;
import com.dan_nixon.csc3621.coursework1.Utils;
import com.dan_nixon.csc3621.coursework1.ex1.FrequencyAnalysis;

public class Analysis
{
  public static void main(String[] args) throws IOException
  {
    String mode = args[0];

    // Just do frequency analysis of a single file
    if (mode.equals("frequency"))
    {
      File[] plainFiles = new File[args.length - 1];
      for (int i = 1; i < args.length; i++)
        plainFiles[i - 1] = new File(args[i]);
      runFreqAnalysisMode(plainFiles);
    }
    // Do frequency analysis on two files and perform analysis on the results
    else if (mode.equals("cipher"))
    {
      File cipherFile = new File(args[1]);
      File[] plainFiles = new File[args.length - 2];
      for (int i = 2; i < args.length; i++)
        plainFiles[i - 2] = new File(args[i]);
      runCipherAnalysisMode(plainFiles, cipherFile);
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
    FrequencyAnalysis fa = new FrequencyAnalysis();
    fa.readPlainText(plainFiles);
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
    FrequencyAnalysis fa = new FrequencyAnalysis();
    fa.readPlainText(plainFiles);
    fa.readCipher(cipherFile);
    double[] plainDistrib = fa.getPlainTextDistribution();
    double[] cipherDistrib = fa.getCipherTextDistribution();

    // Output probability distributions of plain and cipher text characters
    System.out.println("Cryptanalysis results:");
    System.out.println("(probability distribution normalised to 1.0)");
    System.out.println("PLAINTEXT\t\t-   -\tCIPHERTEXT");
    for (int i = 0; i < plainDistrib.length; i++)
    {
      StringBuilder sb = new StringBuilder();
      sb.append(plainDistrib[i]);
      sb.append("\t- ");
      sb.append(Utils.getCharFromIndex(i));
      sb.append(" -\t");
      sb.append(cipherDistrib[i]);
      System.out.println(sb);
    }
    System.out.println();

    // Perform analysis to brute force test for a simple shift cipher
    int likelyRotation = fa.rotationAnalysis();
    System.out.println("Best fit rotation: " + likelyRotation);
  }
}
