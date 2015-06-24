/*
 * Copyright © 2000 - 2008 Sony Network Entertainment. All rights reserved.
 */
package com.hernandez.rey;


import static org.junit.Assert.assertEquals;

import org.junit.Test;


/**
 * StaticTest represents ...
 * 
 * @author <a href="mailto:Rey.Hernandez@am.sony.com">Rey Hernandez</a>
 * @version $Id$
 * @since Jun 1, 2010
 * 
 * @todo Complete description
 */
public class StaticTest
{

   @Test
   public void testStaticMehtod ()
   {
      // final String obj = null;
      // assertEquals ("should be the same", "1", obj.valueOf (1));
      assertEquals ("should be the same", "1", String.valueOf (1));

   }


   @Test
   public void testGetAString1 () throws SecurityException, NoSuchFieldException, IllegalArgumentException,
         IllegalAccessException
   {
      // get a reference to the private field
      // value in String class.
      final java.lang.reflect.Field stringValue = String.class.getDeclaredField ("value");
      // make it accessible
      stringValue.setAccessible (true);
      // unsuspecting string
      final String sittingDuck = "sittingDuck!!!!!";
      // black magic happening here
      stringValue.set (sittingDuck, "hastaLaVistaBaby".toCharArray ());
      // guess the output of this!
      System.out.println ("sittmngDuck!!!!!");
      System.out.println ("sittingDuck!!!!!");
      System.out.println (sittingDuck);

   }


   @Test
   public void testGetAString1a () throws SecurityException, NoSuchFieldException, IllegalArgumentException,
         IllegalAccessException
   {
      int rey = 1;
      System.out.print ("1:");
      System.out.print ("sittingDuck!!!!!");
      System.out.println (":1");
      System.out.println ( ( ++rey) + "sittingDuck!!!!!" + ( ++rey));
      System.out.println ("s" + "ittingDuck!!!!!");

   }


   @Test
   public void testGetAString2 () throws SecurityException, NoSuchFieldException, IllegalArgumentException,
         IllegalAccessException
   {
      // get a reference to the private field
      // value in String class.
      final java.lang.reflect.Field stringValue = String.class.getDeclaredField ("value");
      // make it accessible
      stringValue.setAccessible (true);
      // unsuspecting string
      final String sittingDuck = "sittingDuck!!!!!";
      // black magic happening here
      stringValue.set (sittingDuck, "ReyIsAwesome!!!!".toCharArray ());
      // guess the output of this!
      System.out.println ("sittmngDuck!!!!!");
      System.out.println ("sittingDuck!!!!!");

   }


   @Test
   public void testGetAString2a () throws SecurityException, NoSuchFieldException, IllegalArgumentException,
         IllegalAccessException
   {
      int rey = 1;
      System.out.print ("1:");
      System.out.print ("sittingDuck!!!!!");
      System.out.println (":1");
      System.out.println ( ( ++rey) + "sittingDuck!!!!!" + ( ++rey));
      System.out.println (1 + "sittingDuck!!!!!" + 2);

   }

   // public class ThrowingNull {
   // public void doSomething(String[] args) {
   // ThrowingNull nully = new ThrowingNull();
   //
   // try {
   // nully.throwNull();
   // } catch (Exception e) {
   // System.out.println("Caught: " + e);
   // }
   // }
   //
   // public void throwNull() throws Exception {
   // throw (Exception)null;
   // }
   // }

   // @Test
   // public void testThrowNull()
   // {
   // ThrowingNull nullThrower = new ThrowingNull();
   // nullThrower.doSomething (null);
   // }
}
