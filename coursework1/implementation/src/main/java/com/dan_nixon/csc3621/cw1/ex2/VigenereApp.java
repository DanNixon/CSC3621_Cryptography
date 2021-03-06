package com.dan_nixon.csc3621.cw1.ex2;

import java.util.Map;
import java.io.File;
import java.io.IOException;
import com.dan_nixon.csc3621.cw1.Utils;
import com.dan_nixon.csc3621.cw1.ex2.Vigenere;

public class VigenereApp
{
  /**
   * Main application entry point.
   *
   * @param args Program arguments
   */
  public static void main(String[] args) throws IOException
  {
    Map<String, String> options = Utils.parseCommandLine(args);
    File sourceFile = new File(options.get("file"));
    String result = process(options, sourceFile);

    System.out.println(result);
  }

  /**
   * Process an encryption or decryption operation.
   *
   * @param options Parsed program options
   * @param File Source file to encrypt/decrypt
   * @return Result of operation
   */
  public static String process(Map<String, String> options, File sourceFile)
    throws IOException
  {
    String key = options.get("key");
    Vigenere v = new Vigenere(key);

    if (options.containsKey("encrypt"))
      return v.encrypt(sourceFile);
    else if (options.containsKey("decrypt"))
      return v.decrypt(sourceFile);

    throw new IllegalArgumentException("Invalid operation");
  }
}
