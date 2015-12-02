package com.dan_nixon.csc3621.cw3;

import java.math.BigInteger;

public class RSAAttackApp
{
  public static void main(String[] args)
  {
    BigInteger n = new BigInteger(args[0]);
    BigInteger e = new BigInteger(args[1]);
    BigInteger c = new BigInteger(args[2]);
    
    String m = RSAAttack.retrieveMessage(n, e, c);
    
    System.out.println(m);
  }
}
