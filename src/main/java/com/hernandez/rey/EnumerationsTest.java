package com.hernandez.rey;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;


/**
 * EnumerationsTest represents ...
 * 
 * @author <a href="mailto:Rey.Hernandez@am.sony.com">Rey Hernandez</a>
 * @version $Id$
 * @since Aug 28, 2009
 * 
 * @todo Complete description
 */
public class EnumerationsTest
{

   public enum TestEnums
   {
      TEST1 ("value1"), TEST2 ("value2"), TEST3 ("value3");

      private final String m_value;


      TestEnums (final String value)
      {
         m_value = value;
      }


      public String getValue ()
      {
         return m_value;
      }


      public static TestEnums getEnumForValue (final String value)
      {
         TestEnums _theEnum = null;
         for (final TestEnums _enum : TestEnums.values ())
         {
            if (_enum.getValue ().equals (value))
            {
               _theEnum = _enum;
               break;
            }
         }
         return _theEnum;
      }
   }


   @Test
   public void testValueOf ()
   {
      final TestEnums _testEnums = TestEnums.valueOf ("TEST2");
      assertEquals ("The enum for the value was not correct", TestEnums.TEST2, _testEnums);
   }


   @Test
   public void testValueOfTwoParams ()
   {
      final TestEnums _testEnums = Enum.valueOf (TestEnums.class, "TEST1");
      assertEquals ("The enum for the value was not correct", TestEnums.TEST1, _testEnums);
   }


   @Test
   public void testBadValueOf ()
   {
      final TestEnums _testEnums = TestEnums.getEnumForValue ("someBadEnumValue");
      assertNull ("The enum for the value should have been null", _testEnums);
   }


   @Test
   public void testEquivalence ()
   {
      final TestEnums _firstValue = TestEnums.TEST1;

      assertTrue ("Should be .equal(...)", TestEnums.TEST1.equals (_firstValue));
      assertTrue ("Should be ==", TestEnums.TEST1 == _firstValue);
   }
}
