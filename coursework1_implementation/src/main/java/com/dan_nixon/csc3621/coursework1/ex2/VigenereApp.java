package com.dan_nixon.csc3621.coursework1.ex2;

import java.io.File;
import java.io.IOException;
import com.dan_nixon.csc3621.coursework1.ex2.Vigenere;

public class VigenereApp
{
  public static void main(String[] args) throws IOException
  {
    File sourceFile = new File(args[2]);
    String result = process(args[0], args[1], sourceFile);

    System.out.println(result);
  }

  public static String process(String mode, String key, File sourceFile)
    throws IOException
  {
    Vigenere v = new Vigenere(key);

    mode = mode.toLowerCase();
    if (mode.equals("encrypt"))
      return v.encrypt(sourceFile);
    else if (mode.equals("decrypt"))
      return v.decrypt(sourceFile);

    throw new IllegalArgumentException("Invalid operation");
  }
}
