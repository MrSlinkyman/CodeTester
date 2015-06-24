package com.hernandez.rey.crypto;


/**
 * JcePublicCipher.java Copyright (c) 2002 by Dr. Herong Yang
 */
import java.security.Key;
import java.security.KeyFactory;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import javax.crypto.Cipher;


class JcePublicCipher
{
   public static void main (final String[] a) throws Exception
   {
      if (a.length < 5)
      {
         System.out.println ("Usage:");
         System.out.println ("java JcePublicCipher algorithm mode keyFile input output");
         return;
      }
      final String algorithm = a[0];
      final String mode = a[1];
      final String keyFile = a[2];
      final String input = a[3];
      final String output = a[4];
      System.out.println (String.format ("Algorithm:  %s\n"+
                                         "Mode:       %s\n"+
                                         "keyFile:    %s\n"+
                                         "input data: %s\n"+
                                         "output:     %s",
            (Object[]) a));
      final Key ky = readKey (algorithm, mode, keyFile);
      publicCipher (algorithm, mode, ky, input, output);
   }


   private static Key readKey (final String algorithm, final String mode, final String input) throws Exception
   {
      final KeyFactory keyFactory = KeyFactory.getInstance (algorithm);
      System.out.println ();
      System.out.println ("KeyFactory Object Info: ");
      System.out.println ("Algorithm = " + keyFactory.getAlgorithm ());
      System.out.println ("Provider = " + keyFactory.getProvider ());
      System.out.println ("toString = " + keyFactory.toString ());

      final File _inputFile = new File (input);
      final FileInputStream fis = new FileInputStream (_inputFile);
      System.out.println ("Input File: " + _inputFile.getCanonicalPath ());
      final int _inputAvailableBytes = fis.available ();
      System.out.println ("Input File size:            " + _inputFile.length ());
      System.out.println ("Input File available bytes: " + _inputAvailableBytes);
      final byte[] kb = new byte[_inputAvailableBytes];
      fis.read (kb);
      System.out.println ("Input File acquired bytes:  " + kb.length);
      fis.close ();
      Key ky = null;
      if (mode.equalsIgnoreCase ("encrypt"))
      {
         final X509EncodedKeySpec pubKeySpec = new X509EncodedKeySpec (kb);
         ky = keyFactory.generatePublic (pubKeySpec);
      }
      else if (mode.equalsIgnoreCase ("decrypt"))
      {
         final PKCS8EncodedKeySpec priKeySpec = new PKCS8EncodedKeySpec (kb);
         ky = keyFactory.generatePrivate (priKeySpec);
      }
      else
      {
         throw new Exception ("Invalid mode: " + mode);
      }
      System.out.println ();
      System.out.println ("Key Object Info: ");
      System.out.println ("Algorithm = " + ky.getAlgorithm ());
      System.out.println ("Saved File = " + input);
      System.out.println ("Length = " + _inputAvailableBytes);
      System.out.println ("Format = " + ky.getFormat ());
      System.out.println ("toString = " + ky.toString ());
      return ky;
   }


   private static void publicCipher (final String algorithm, final String mode, final Key ky, final String input,
         final String output) throws Exception
   {
      final Cipher cf = Cipher.getInstance (algorithm);
      if (mode.equalsIgnoreCase ("encrypt"))
      {
         cf.init (Cipher.ENCRYPT_MODE, ky);
      }
      else if (mode.equalsIgnoreCase ("decrypt"))
      {
         cf.init (Cipher.DECRYPT_MODE, ky);
      }
      else
      {
         throw new Exception ("Invalid mode: " + mode);
      }
      System.out.println ();
      System.out.println ("Cipher Object Info: ");
      System.out.println ("Block Size = " + cf.getBlockSize ());
      System.out.println ("Algorithm = " + cf.getAlgorithm ());
      System.out.println ("Provider = " + cf.getProvider ());
      System.out.println ("toString = " + cf.toString ());

      final FileInputStream fis = new FileInputStream (input);
      final FileOutputStream fos = new FileOutputStream (output);
      final int bufSize = 1024;
      final byte[] buf = new byte[bufSize];
      int n = fis.read (buf, 0, bufSize);
      int fisSize = 0;
      int fosSize = 0;
      while (n != -1)
      {
         fisSize += n;
         final byte[] out = cf.update (buf, 0, n);
         fosSize += out.length;
         fos.write (out);
         n = fis.read (buf, 0, bufSize);
      }
      final byte[] out = cf.doFinal ();
      fosSize += out.length;
      fos.write (out);
      fis.close ();
      fos.close ();
      System.out.println ();
      System.out.println ("Cipher Process Info: ");
      System.out.println ("Input Size = " + fisSize);
      System.out.println ("Output Size = " + fosSize);
   }
}
