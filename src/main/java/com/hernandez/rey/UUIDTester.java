package com.hernandez.rey;


import static org.junit.Assert.fail;

import org.junit.Test;

import org.apache.log4j.Logger;

import java.util.UUID;


/**
 * UUIDTester represents ...
 * 
 * @author <a href="mailto:Rey.Hernandez@am.sony.com">Rey Hernandez</a>
 * @version $Id$
 * @since Oct 15, 2009
 * 
 * @todo Complete description
 */
public class UUIDTester
{
   private final static transient Logger m_log = Logger.getLogger (UUIDTester.class);


   /**
    * Test method for {@link java.util.UUID#UUID(long, long)}.
    */
   @Test
   public void testUUID ()
   {
      final UUID _uuid = new UUID (6, 3);
      m_log.debug (String.format ("The uuid is %s", _uuid));
   }


   /**
    * Test method for {@link java.util.UUID#randomUUID()}.
    */
   @Test
   public void testRandomUUID ()
   {
      final UUID _uuid = UUID.randomUUID ();
      final long _mostSigBit = _uuid.getLeastSignificantBits ();
      final long _leastSigBit = _uuid.getMostSignificantBits ();
      m_log.debug (String.format ("The uuid is %s", _uuid));
      m_log.debug (String.format ("UUID msb: %d, lsb: %d", _mostSigBit, _leastSigBit));

      m_log.debug (String.format ("The initialization vector is %s", _uuid.toString ().getBytes ()));

   }


   /**
    * Test method for {@link java.util.UUID#nameUUIDFromBytes(byte[])}.
    */
   @Test
   public void testNameUUIDFromBytes ()
   {
      fail ("Not yet implemented");
   }


   /**
    * Test method for {@link java.util.UUID#fromString(java.lang.String)}.
    */
   @Test
   public void testFromString ()
   {
      fail ("Not yet implemented");
   }

}
