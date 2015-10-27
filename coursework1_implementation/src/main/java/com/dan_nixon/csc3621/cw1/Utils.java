package com.dan_nixon.csc3621.cw1;

import java.util.Map;
import java.util.HashMap;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.StringBuilder;

public class Utils
{
  /**
   * Parses the array of strings passed to main into a key/value list based on
   * the dash and double dash options.
   *
   * e.g. -key 65 first_positional --encrypt --string hello positional
   * parses to {key:65, _p0:first_positional, encrypt:"", string:hello,
   *            _p1:positional}
   *
   * @param args Array of strings
   * @return Map of key:value pairs
   */
  public static Map<String, String> parseCommandLine(String[] args)
  {
    Map<String, String> parsed = new HashMap<String, String>();
    int numPositional = 0;

    for(int i = 0; i < args.length; i++)
    {
      String str = new String(args[i]);
      if (str.startsWith("-"))
      {
        // Trim off the dashes
        int subLower = 1;
        if (str.startsWith("--"))
          subLower = 2;
        str = str.substring(subLower);

        // Get the value
        String value = null;
        if (i + 1 < args.length && !args[i + 1].startsWith("-"))
        {
          value = new String(args[i + 1]);
          i++;
        }

        parsed.put(str, value);
      }
      else
      {
        String key = "_p" + numPositional;
        numPositional++;
        parsed.put(key, str);
      }
    }

    parsed.put("_num_positional", Integer.toString(numPositional));

    return parsed;
  }

  /**
   * Converts a character to a numerical index.
   *
   * e.g. a=0, z=25, A=0, Z=25
   *
   * @param c Character to convert
   * @return Index
   */
  public static int getIndexFromChar(char c) throws IllegalArgumentException
  {
    char ch = Character.toLowerCase(c);
    int charIdx = (int) ch - 97;
    if (charIdx >= 0 && charIdx < 26)
      return charIdx;
    throw new IllegalArgumentException("Invalid char");
  }

  /**
   * Gets a character from a numerical index.
   *
   * e.g. 0=a, 25=z
   *
   * @param idx Index to convert
   * @return Character
   */
  public static char getCharFromIndex(int idx)
  {
    if (idx < 0 || idx > 25)
      throw new IllegalArgumentException("Index out of bounds (0-25)");
    return (char) (idx + 97);
  }

  /**
   * Rotates (shifts) a character as per the rules of a shift cipher.
   *
   * @param c Character to rotate
   * @param rotation Offset (key)
   * @return Rotated character
   */
  public static char rotateChar(char c, int rotation)
  {
    if (rotation < 0)
      rotation = 26 + rotation;
    int index = (getIndexFromChar(c) + rotation) % 26;
    return getCharFromIndex(index);
  }

  /**
   * Rotates an array of doubles by a given offset.
   *
   * e.g. [1, 2, 3] rotated by 1 is [3, 1, 2]
   *
   * @param in Array to rotate
   * @param rotation Offset
   * @return Rotated array
   */
  public static double[] rotateArray(double[] in, int rotation)
  {
    double[] out = new double[in.length];
    for (int i = 0; i < in.length; i++)
    {
      int newIdx = (i + rotation) % in.length;
      out[newIdx] = in[i];
    }
    return out;
  }

  /**
   * Reads the contents of a file and generates a String.
   *
   * @param file The file to read
   * @return A string read from the file
   */
  public static String readFileToString(File file) throws IOException
  {
    StringBuilder sb = new StringBuilder();
    FileInputStream fs = new FileInputStream(file);
    while (fs.available() > 0) {
      char c = (char) fs.read();
      sb.append(c);
    }
    return sb.toString();
  }

  /**
   * Writes binary data to a file.
   *
   * @param file File to write to
   * @param data Data to write
   */
  public static void writeBinaryFile(File file, byte[] data) throws IOException
  {
    FileOutputStream out = new FileOutputStream(file);
    out.write(data);
    out.close();
  }

  /**
   * Reads binary data from a file.
   *
   * @param file File to read from
   * @return Data read from file
   */
  public static byte[] readBinaryFile(File file) throws IOException
  {
    if (!file.exists() && !file.isDirectory())
    {
      file.getParentFile().mkdirs();
      file.createNewFile();
    }

    FileInputStream in = new FileInputStream(file);
    int len = (int) file.length();

    // Read to buffer
    byte[] buffer = new byte[len];
    int read = in.read(buffer);

    return buffer;
  }

  /**
   * Computes the XOR of two byte arrays.
   *
   * @param a First array
   * @param b Second array
   * @return XOR array
   */
  public static byte[] arrayXor(byte[] a, byte[]b)
  {
    int minLength = Math.min(a.length, b.length);
    int maxLength = Math.max(a.length, b.length);
    byte[] xor = new byte[maxLength];
    int i;
    for (i = 0; i < minLength; i++)
      xor[i] = (byte) (a[i] ^ b[i]);
    for (; i < maxLength; i++)
      xor[i] = 0;
    return xor;
  }

  /**
   * Converts a byte array to a String.
   *
   * @param arr Byte array to convert
   * @return Byte array as string
   */
  public static String byteArrayToString(byte[] arr)
  {
    StringBuilder sb = new StringBuilder();

    for (int i = 0; i < arr.length; i++)
    {
      byte b = arr[i];
      if ((b >= 'A' && b <= 'Z') || (b >= 'a' && b < 'z'))
        sb.append((char)(b & 0xff));
      else
        sb.append("-");
    }

    return sb.toString();
  }
}
