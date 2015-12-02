package com.dan_nixon.csc3621.cw3;

import java.math.BigInteger;

public class RSAAttack
{
  /**
   * Encodes a string as a BigInteger.
   * 
   * @param str String to encode
   * @return Encoded value
   */
  public static BigInteger encodeString(String str)
  {
    StringBuilder encoded = new StringBuilder();
    str = str.toUpperCase();
    
    for (char c : str.toCharArray())
      encoded.append((int) c);
    
    return new BigInteger(encoded.toString());
  }
  
  /**
   * Decodes a String from a BigInteger.
   * 
   * @param val Value to decode
   * @return Decoded string
   */
  public static String decodeString(BigInteger val)
  {
    StringBuilder decoded = new StringBuilder();
    
    char[] encoded = val.toString().toCharArray();
    if (encoded.length % 2 == 1)
      throw new IllegalArgumentException("Odd number of digits in integer");
    
    for (int i = 0; i < encoded.length; i+=2)
    {
      String asciiValStr = new String(new char[]{encoded[i], encoded[i+1]});
      int asciiVal = Integer.valueOf(asciiValStr);
      decoded.append((char) asciiVal);
    }
    
    return decoded.toString();
  }

  /**
   * Calculates the nth root of a.
   * 
   * @param a Value
   * @param n Root
   * @return b such that b^n = a
   */
  public static BigInteger nRoot(BigInteger a, BigInteger n)
  {
    BigInteger r = BigInteger.ONE;
    BigInteger s = BigInteger.ONE;
 
    while(true)
    {
      r = (((n.subtract(BigInteger.ONE)).multiply(r)).add(a.divide(r.pow(n.intValue() - 1)))).divide(n);
      
      if (s.equals(r))
        break;
      else if (!s.equals(r.subtract(BigInteger.ONE)))
        s = r;
    }
 
    return r;
  }
  
  /**
   * Retrieves the plain text message for an RSA cipher encrypted with a given
   * (small) encryption exponent.
   * 
   * @param n Modulus
   * @param e Encryption exponent
   * @param c Cipher
   * @return Plain text message
   */
  public static String retrieveMessage(BigInteger n, BigInteger e, BigInteger c)
  { 
    BigInteger m = nRoot(c, e);
    m = m.mod(n);
    return decodeString(m);
  }
}
