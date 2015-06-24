package com.hernandez.rey.crypto;


/**
 * JceSecretCipher.java Copyright (c) 2002 by Dr. Herong Yang
 */
import java.security.spec.KeySpec;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.SecretKeySpec;


class JceSecretCipher
{
   public static void main (final String[] a)
   {
      if (a.length < 5)
      {
         System.out.println ("Usage:");
         System.out.println ("java JceSecretCipher algorithm mode keyFile input output");
         return;
      }
      final String algorithm = a[0];
      final String mode = a[1];
      final String keyFile = a[2];
      final String input = a[3];
      final String output = a[4];
      try
      {
         final SecretKey ky = readKey (keyFile, algorithm);
         secretCipher (algorithm, mode, ky, input, output);
      }
      catch (final Exception e)
      {
         System.out.println ("Exception: " + e);
         return;
      }
   }


   private static SecretKey readKey (final String input, final String algorithm) throws Exception
   {
      final String _filePath = input;
      final FileInputStream _fileInputStream = new FileInputStream (_filePath);
      final int _bytesAvailable = _fileInputStream.available ();
      final byte[] _availableBuffer = new byte[_bytesAvailable];
      _fileInputStream.read (_availableBuffer);
      _fileInputStream.close ();
      KeySpec _algorithKeySpec = null;
      SecretKey _secretKey = null;
      SecretKeyFactory _secretKeyFactory = null;
      if (algorithm.equalsIgnoreCase ("DES"))
      {
         _algorithKeySpec = new DESKeySpec (_availableBuffer);
         _secretKeyFactory = SecretKeyFactory.getInstance ("DES");
         _secretKey = _secretKeyFactory.generateSecret (_algorithKeySpec);
      }
      else if (algorithm.equalsIgnoreCase ("DESede"))
      {
         _algorithKeySpec = new DESedeKeySpec (_availableBuffer);
         _secretKeyFactory = SecretKeyFactory.getInstance ("DESede");
         _secretKey = _secretKeyFactory.generateSecret (_algorithKeySpec);
      }
      else
      {
         _algorithKeySpec = new SecretKeySpec (_availableBuffer, algorithm);
         _secretKey = new SecretKeySpec (_availableBuffer, algorithm);
      }
      System.out.println ();
      System.out.println ("KeySpec Object Info: ");
      System.out.println ("Saved File = " + _filePath);
      System.out.println ("Length = " + _availableBuffer.length);
      System.out.println ("toString = " + _algorithKeySpec.toString ());
      System.out.println ();
      System.out.println ("SecretKey Object Info: ");
      System.out.println ("Algorithm = " + _secretKey.getAlgorithm ());
      System.out.println ("toString = " + _secretKey.toString ());
      return _secretKey;
   }


   private static void secretCipher (final String algorithm, final String mode, final SecretKey ky, final String input,
         final String output) throws Exception
   {
      final Cipher _algorithmCipher = Cipher.getInstance (algorithm);
      if (mode.equalsIgnoreCase ("encrypt"))
      {
         _algorithmCipher.init (Cipher.ENCRYPT_MODE, ky);
      }
      else if (mode.equalsIgnoreCase ("decrypt"))
      {
         _algorithmCipher.init (Cipher.DECRYPT_MODE, ky);
      }
      else
      {
         throw new Exception ("Invalid mode: " + mode);
      }
      System.out.println ();
      System.out.println ("Cipher Object Info: ");
      System.out.println ("Block Size = " + _algorithmCipher.getBlockSize ());
      System.out.println ("Algorithm = " + _algorithmCipher.getAlgorithm ());
      System.out.println ("Provider = " + _algorithmCipher.getProvider ());
      System.out.println ("toString = " + _algorithmCipher.toString ());

      final FileInputStream fis = new FileInputStream (input);
      final FileOutputStream fos = new FileOutputStream (output);
      final int bufSize = 1024;
      final byte[] _buffer = new byte[bufSize];
      int _bytesRead = fis.read (_buffer, 0, bufSize);
      int fisSize = 0;
      int fosSize = 0;
      while (_bytesRead != -1)
      {
         fisSize += _bytesRead;
         final byte[] out = _algorithmCipher.update (_buffer, 0, _bytesRead);
         fosSize += out.length;
         fos.write (out);
         _bytesRead = fis.read (_buffer, 0, bufSize);
      }
      final byte[] out = _algorithmCipher.doFinal ();
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
