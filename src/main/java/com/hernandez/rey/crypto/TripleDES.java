package com.hernandez.rey.crypto;


/*
 * Copyright (c) 2000 David Flanagan. All rights reserved. This code is from the book Java Examples in a Nutshell, 2nd
 * Edition. It is provided AS-IS, WITHOUT ANY WARRANTY either expressed or implied. You may study, use, and modify it
 * for any non-commercial purpose. You may distribute it non-commercially as long as you retain this notice. For a
 * commercial use license, or to purchase the book (recommended), visit http://www.davidflanagan.com/javaexamples2.
 */

import org.apache.commons.codec.binary.Base64;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.Security;
import java.security.spec.InvalidKeySpecException;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;


/**
 * This class defines methods for encrypting and decrypting using the Triple DES algorithm and for generating, reading
 * and writing Triple DES keys. It also defines a main() method that allows these methods to be used from the command
 * line.
 */
public class TripleDES
{
   /**
    * @param args The program. The first argument must be -e, -d, or -g to encrypt, decrypt, or generate a key. The
    *           second argument is the name of a file from which the key is read or to which it is written for -g. The
    *           -e and -d arguments cause the program to read from standard input and encrypt or decrypt to standard
    *           output.
    */
   public static void main (final String[] args)
   {
      try
      {
         // Check to see whether there is a provider that can do TripleDES
         // encryption. If not, explicitly install the SunJCE provider.
         try
         {
            Cipher.getInstance ("DESede");
         }
         catch (final Exception e)
         {
            // An exception here probably means the JCE provider hasn't
            // been permanently installed on this system by listing it
            // in the $JAVA_HOME/jre/lib/security/java.security file.
            // Therefore, we have to install the JCE provider explicitly.
            System.err.println ("Installing SunJCE provider.");
//            @SuppressWarnings("restriction")
			final Provider sunjce = new com.sun.crypto.provider.SunJCE ();
            Security.addProvider (sunjce);
         }

         // This is where we'll read the key from or write it to
         final File keyfile = new File (args[1]);

         // Now check the first arg to see what we're going to do
         if (args[0].equals ("-g"))
         { // Generate a key
            System.out.print ("Generating key. This may take some time...");
            System.out.flush ();
            final SecretKey key = generateKey ();
            writeKey (key, keyfile);
            System.out.println ("done.");
            System.out.println ("Secret key written to " + args[1] + ". Protect that file carefully!");
         }
         else if (args[0].equals ("-e"))
         { // Encrypt stdin to stdout
            final SecretKey key = readKey (keyfile);
            encrypt (key, System.in, System.out);
         }
         else if (args[0].equals ("-d"))
         { // Decrypt stdin to stdout
            final SecretKey key = readKey (keyfile);
            decrypt (key, System.in, System.out);
         }
      }
      catch (final Exception e)
      {
         System.err.println (e);
         System.err.println ("Usage: java " + TripleDES.class.getName () + " -d|-e|-g <keyfile>");
      }
   }


   /**
    * Generate a secret TripleDES encryption/decryption key
    * 
    * @return the secret key generated
    * @throws NoSuchAlgorithmException
    */
   public static SecretKey generateKey () throws NoSuchAlgorithmException
   {
      // Get a key generator for Triple DES (a.k.a DESede)
      final KeyGenerator keygen = KeyGenerator.getInstance ("DESede");
      // Use it to generate a key
      return keygen.generateKey ();
   }


   /**
    * Save the specified TripleDES SecretKey to the specified file
    * 
    * @param key the key to write
    * @param keyFile
    * @throws IOException
    * @throws NoSuchAlgorithmException
    * @throws InvalidKeySpecException
    */
   public static void writeKey (final SecretKey key, final File keyFile) throws IOException, NoSuchAlgorithmException,
         InvalidKeySpecException
   {
      // Convert the secret key to an array of bytes like this
      final SecretKeyFactory keyfactory = SecretKeyFactory.getInstance ("DESede");
      final DESedeKeySpec keyspec = (DESedeKeySpec) keyfactory.getKeySpec (key, DESedeKeySpec.class);
      final byte[] rawkey = keyspec.getKey ();

      final byte[] encodedKey = Base64.encodeBase64 (rawkey);

      // Write the raw key to the file
      FileOutputStream out = new FileOutputStream (keyFile);
      out.write (encodedKey);
      out.close ();

      out = new FileOutputStream (new File (keyFile.toString ().concat ("-raw")));
      out.write (rawkey);
      out.close ();
   }


   /**
    * Read a TripleDES secret key from the specified file
    * 
    * @param keyFile key file to read
    * @return secret key appropriate for encryption/decryption with 3DES
    * @throws IOException
    * @throws NoSuchAlgorithmException
    * @throws InvalidKeyException
    * @throws InvalidKeySpecException
    */
   public static SecretKey readKey (final File keyFile) throws IOException, NoSuchAlgorithmException,
         InvalidKeyException, InvalidKeySpecException
   {
      // Read the raw bytes from the keyfile
      final DataInputStream in = new DataInputStream (new FileInputStream (keyFile));
      final byte[] rawkey = new byte[(int) keyFile.length ()];
      in.readFully (rawkey);
      in.close ();

      // Convert the raw bytes to a secret key like this
      final DESedeKeySpec keyspec = new DESedeKeySpec (rawkey);
      final SecretKeyFactory keyfactory = SecretKeyFactory.getInstance ("DESede");
      final SecretKey key = keyfactory.generateSecret (keyspec);
      return key;
   }


   /**
    * Use the specified TripleDES key to encrypt bytes from the input stream and write them to the output stream. This
    * method uses CipherOutputStream to perform the encryption and write bytes at the same time.
    * 
    * @param key the key to use for encryption
    * @param in the input stream to encrypt
    * @param out the encrypted output stream
    * @throws NoSuchAlgorithmException
    * @throws InvalidKeyException
    * @throws NoSuchPaddingException
    * @throws IOException
    */
   public static void encrypt (final SecretKey key, final InputStream in, final OutputStream out)
         throws NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException, IOException
   {
      // Create and initialize the encryption engine
      final Cipher cipher = Cipher.getInstance ("DESede");
      cipher.init (Cipher.ENCRYPT_MODE, key);

      // Create a special output stream to do the work for us
      final CipherOutputStream cos = new CipherOutputStream (out, cipher);

      // Read from the input and write to the encrypting output stream
      final byte[] buffer = new byte[2048];
      int bytesRead;
      while ( (bytesRead = in.read (buffer)) != -1)
      {
         cos.write (buffer, 0, bytesRead);
      }
      cos.close ();

      // For extra security, don't leave any plaintext hanging around memory.
      java.util.Arrays.fill (buffer, (byte) 0);
   }


   /**
    * Use the specified TripleDES key to decrypt bytes ready from the input stream and write them to the output stream.
    * This method uses uses Cipher directly to show how it can be done without CipherInputStream and CipherOutputStream.
    * 
    * @param key the key for decryption
    * @param in
    * @param out
    * @throws NoSuchAlgorithmException
    * @throws InvalidKeyException
    * @throws IOException
    * @throws IllegalBlockSizeException
    * @throws NoSuchPaddingException
    * @throws BadPaddingException
    */
   public static void decrypt (final SecretKey key, final InputStream in, final OutputStream out)
         throws NoSuchAlgorithmException, InvalidKeyException, IOException, IllegalBlockSizeException,
         NoSuchPaddingException, BadPaddingException
   {
      // Create and initialize the decryption engine
      final Cipher cipher = Cipher.getInstance ("DESede");
      cipher.init (Cipher.DECRYPT_MODE, key);

      // Read bytes, decrypt, and write them out.
      final byte[] buffer = new byte[2048];
      int bytesRead;
      while ( (bytesRead = in.read (buffer)) != -1)
      {
         out.write (cipher.update (buffer, 0, bytesRead));
      }

      // Write out the final bunch of decrypted bytes
      out.write (cipher.doFinal ());
      out.flush ();
   }
}
