package com.hernandez.rey;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;


/**
 * ComparatorTest represents ...
 * 
 * @author <a href="mailto:Rey.Hernandez@am.sony.com">Rey Hernandez</a>
 * @version $Id$
 * @since Oct 27, 2009
 * 
 * @todo Complete description
 */
public class ComparatorTest
{

   private class MyComparator implements Comparator <String>
   {

      /**
       * Overrides compare
       * 
       * @param o1
       * @param o2
       * @return the normal comparison of objects
       * @since Oct 27, 2009
       * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
       */
      public int compare (final String o1, final String o2)
      {
         return ( (null == o1) && (null == o2)) ? 0 : ( (o1 != null) && (o2 == null))
               ? -1
               : ( (o1 == null) && (o2 != null)) ? 1 : o1.compareTo (o2);

      }

   }



   /**
    * Test method for {@link java.util.Comparator#compare(java.lang.Object, java.lang.Object)}.
    */
   @Test
   public void testCompare ()
   {
      final Set <String> _sortedStrings = new TreeSet <String> (new MyComparator ());

      _sortedStrings.add (null);
      _sortedStrings.add ("1");
      _sortedStrings.add ("z");
      _sortedStrings.add ("2");

      assertEquals ("The desired object is not correct", "1", new ArrayList <String> (_sortedStrings).get (0));
      assertNull ("The last object should be null",
            new ArrayList <String> (_sortedStrings).get (_sortedStrings.size () - 1));
   }
}
