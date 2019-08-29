package com.hernandez.rey;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;


/**
 * EqualsTest represents ...
 * 
 * @author <a href="mailto:Rey.Hernandez@am.sony.com">Rey Hernandez</a>
 * @version $Id$
 * @since Sep 11, 2009
 * 
 * @todo Complete description
 */
public class EqualsTest
{

   @Test
   public void testIntEquals ()
   {
      final int _myInt = 3;
      final Integer _myBadInteger = null;

      assertNull ("bad integer should be null", _myBadInteger);
      assertFalse ("Using == for int equals should be false", new Integer (_myInt).equals (_myBadInteger));

   }


   @Test
   public void testIntegerEquals ()
   {
      final Integer _myInteger = new Integer (3);

      assertTrue ("Integer.intValue == int should be fine", _myInteger.intValue () == 3);
      assertTrue ("Integer equals int should be fine", _myInteger.equals (3));

   }


   /**
    * testIntegerEqualsNull takes advantage of auto-unboxing to show that an object Integer set to null does not behave
    * like a regular int.
    * 
    * @since Jun 1, 2010
    */
   @SuppressWarnings("null")
@Test (expected = NullPointerException.class)
   public void testIntegerEqualsNull ()
   {
      final Integer _myInteger = null;


      assertTrue ("This will not work because of autounboxing", _myInteger == 5);

   }


   @Test
   public void testIntValueEqualsEquals ()
   {
      final Integer _myInteger = new Integer (3);

      assertTrue ("Integer.intValue == int should be fine", _myInteger.intValue () == 3);
      assertTrue ("Integer == int should be fine", _myInteger == 3);

   }


   @Test
   public void testStringEquals ()
   {
      final String CONSTANT = "my constant";
      final String _myNullVar = null;
      final String _myEqualsVar = "my constant";

      assertFalse ("CONSTANT and Null should not be equal", CONSTANT.equals (_myNullVar));
      assertTrue ("CONSTANT and EqualsVar should be equal", CONSTANT.equals (_myEqualsVar));
   }


   @SuppressWarnings("null")
@Test (expected = NullPointerException.class)
   public void testNPEStringEquals ()
   {
      final String CONSTANT = "my constant";
      final String _myNullVar = null;

      assertFalse ("CONSTANT and Null should not be equal", _myNullVar.equals (CONSTANT));
   }
}
