package com.dan_nixon.csc3621.cw1.ex1;

import java.util.Map;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import com.dan_nixon.csc3621.cw1.Utils;

public class DecipherRotation
{
  /**
   * Main application entry point.
   *
   * @param args Program arguments
   */
  public static void main(String[] args) throws IOException
  {
    Map<String, String> options = Utils.parseCommandLine(args);

    // Negate key to decipher
    int rotation = -Integer.parseInt(options.get("shift"));

    // Load cipher file
    File cipherFile = new File(options.get("file"));
    FileInputStream fs = new FileInputStream(cipherFile);

    System.out.println("Key: " + rotation);
    System.out.println("Deciphered plain text:");
    while (fs.available() > 0) {
      char c = (char) fs.read();
      try
      {
        // Get rotated character
        c = Utils.rotateChar(c, rotation);
      }
      // Not an alphabetical char, just output as per cipher text
      catch (IllegalArgumentException ex) {}
      System.out.print(c);
    }
    System.out.println();
    System.out.println("END OF TEXT");
  }
}
