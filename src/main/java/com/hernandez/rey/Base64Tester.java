/*
 * Copyright © 2000 - 2008 Sony Network Entertainment. All rights reserved.
 */
package com.hernandez.rey;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.apache.commons.codec.binary.Base64;
import org.junit.Test;

import org.apache.log4j.Logger;


/**
 * Base64Tester represents ...
 * 
 * @author <a href="mailto:Rey.Hernandez@am.sony.com">Rey Hernandez</a>
 * @version $Id$
 * @since Oct 13, 2009
 * 
 * @todo Complete description
 */
public class Base64Tester
{

   private final static transient Logger m_log = Logger.getLogger (Base64Tester.class);


   /**
    * Test method for {@link org.apache.commons.codec.binary.Base64#encodeBase64(byte[])}.
    */
   @Test
   public void testEncodeBase64ByteArray ()
   {
      final byte[] _binaryData = "abc".getBytes ();
      final byte[] _encoded = Base64.encodeBase64 (_binaryData);
      m_log.info (String.format ("The original data: %s, the encoded data: %s", new String (_binaryData), new String (
            _encoded)));
      final byte[] _decoded = Base64.decodeBase64 (_encoded);
      assertEquals ("Encoded and decoded should be equal", new String (_binaryData), new String (_decoded));
      final byte[] _encoded2 = new byte[] {'a'};
      final byte[] _decoded2 = Base64.decodeBase64 (_encoded2);
      m_log.info (String.format ("decoded 2 = %s", new String (_decoded2)));
   }


   /**
    * Test method for {@link org.apache.commons.codec.binary.Base64#encodeBase64String(byte[])}.
    */
   @Test
   public void testEncodeBase64String ()
   {
      fail ("Not yet implemented");
   }


   /**
    * Test method for {@link org.apache.commons.codec.binary.Base64#decodeBase64(java.lang.String)}.
    */
   @Test
   public void testDecodeBase64String ()
   {
      fail ("Not yet implemented");
   }


   /**
    * Test method for {@link org.apache.commons.codec.binary.Base64#decodeBase64(byte[])}.
    */
   @Test
   public void testDecodeBase64ByteArray ()
   {
      fail ("Not yet implemented");
   }

}
