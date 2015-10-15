package com.dan_nixon.csc3621.coursework1.ex1;

import java.io.File;
import java.io.IOException;
import com.dan_nixon.csc3621.coursework1.Utils;
import com.dan_nixon.csc3621.coursework1.ex1.FrequencyAnalysis;

public class Analysis
{
  public static void main(String[] args) throws IOException
  {
    File cipherFile = new File(args[0]);
    File[] plainFiles = new File[args.length - 1];
    for (int i = 1; i < args.length; i++)
      plainFiles[i - 1] = new File(args[i]);

    FrequencyAnalysis fa = new FrequencyAnalysis();
    fa.readPlainText(plainFiles);
    fa.readCipher(cipherFile);

    double[] plainDistrib = fa.getPlainTextDistribution();
    double[] cipherDistrib = fa.getCipherTextDistribution();

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

    int likelyRotation = fa.rotationAnalysis();
    System.out.println("Best fit rotation: " + likelyRotation);
  }
}
