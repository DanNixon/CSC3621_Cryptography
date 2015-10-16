package com.dan_nixon.csc3621.coursework1.ex1;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import com.dan_nixon.csc3621.coursework1.Utils;

public class DecipherRotation
{
  public static void main(String[] args) throws IOException
  {
    // Negate key to decipher
    int rotation = -Integer.parseInt(args[0]);

    // Load cipher file
    File cipherFile = new File(args[1]);
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
