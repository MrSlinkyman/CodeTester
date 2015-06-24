/*
 * Copyright © 2000 - 2008 Sony Network Entertainment. All rights reserved.
 */
package com.hernandez.rey;


import static org.junit.Assert.assertEquals;

import org.apache.commons.codec.binary.Hex;
import org.junit.Test;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 * Sha1Tester represents ...
 * 
 * @author <a href="mailto:Rey.Hernandez@am.sony.com">Rey Hernandez</a>
 * @version $Id$
 * @since Aug 19, 2010
 * 
 * @todo Complete description
 */
public class Sha1Tester
{

   /**
    * Test method for {@link java.security.MessageDigest#digest()}.
    * 
    * @throws NoSuchAlgorithmException
    */
   @Test
   public void testDigest () throws NoSuchAlgorithmException
   {
      final MessageDigest _digest = MessageDigest.getInstance ("SHA1");
      System.out.println ("Message digest object info: ");
      System.out.println ("   Algorithm = " + _digest.getAlgorithm ());
      System.out.println ("   Provider = " + _digest.getProvider ());
      System.out.println ("   toString = " + _digest.toString ());

      String _input = "";
      _digest.update (_input.getBytes ());
      byte[] _output = _digest.digest ();
      System.out.println ();
      System.out.println ("SHA1(\"" + _input + "\") =");
      System.out.println ("   " + bytesToHex (_output));

      _input = "abc";
      _digest.update (_input.getBytes ());
      _output = _digest.digest ();
      System.out.println ();
      System.out.println ("SHA1(\"" + _input + "\") =");
      System.out.println ("   " + bytesToHex (_output));

      _input = "abcdefghijklmnopqrstuvwxyz";
      _digest.update (_input.getBytes ());
      _output = _digest.digest ();
      System.out.println ();
      System.out.println ("SHA1(\"" + _input + "\") =");
      System.out.println ("   " + bytesToHex (_output));

      _input = "1027Myapex01";
      _digest.update (_input.getBytes ());
      _output = _digest.digest ();
      String _hex = bytesToHex (_output);
      assertEquals ("sha1 value not expected", "62c09ec018e77b80e0850e5ecd4feb35577fafa0".toUpperCase (), _hex);
      System.out.println ();
      System.out.println ("SHA1(\"" + _input + "\") =");
      System.out.println ("   " + _hex);

      _hex = new String (Hex.encodeHex (_output));
      System.out.println ("   " + _hex);
   }

   private static final String HEXES = "0123456789ABCDEF";


   private static String bytesToHex (final byte[] raw)
   {
      if (raw == null)
      {
         return null;
      }
      final StringBuilder hex = new StringBuilder (2 * raw.length);
      for (final byte b : raw)
      {
         hex.append (HEXES.charAt ( (b & 0xF0) >> 4)).append (HEXES.charAt ( (b & 0x0F)));
      }
      return hex.toString ();
   }

}
