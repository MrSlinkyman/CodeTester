package com.hernandez.rey;



import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;


/**
 * ArraysTest represents ...
 * 
 * @author <a href="mailto:Rey.Hernandez@am.sony.com">Rey Hernandez</a>
 * @version $Id$
 * @since Oct 30, 2009
 * 
 * @todo Complete description
 */
public class ArraysTest
{

   @Test
   public void testContainsWithStringArray ()
   {
      final List <String> _list = Arrays.asList (new String[] {"first", "second", "third", "last"});
      assertTrue ("The list should have the requested item", _list.contains ("first"));
   }


   @Test (expected = NullPointerException.class)
   public void testArraysAsListWithNull ()
   {
      final Object[] _array = null;
      Arrays.asList (_array);
      fail ("Expected null pointer exception for the arguments to asList");
   }


@Test
   public void testAsListToString ()
   {
      @SuppressWarnings("rawtypes")
	List _aList = Arrays.asList (new Object[] {null, 1, "two", 3l, null});
      assertEquals ("The list was not what we expected", "[null, 1, two, 3, null]", _aList.toString ());

      _aList = Arrays.asList (new Object[] {null});
      assertEquals ("The list was not what we expected", "[null]", _aList.toString ());

      _aList = Arrays.asList (new Object[] {});
      assertEquals ("The list was not what we expected", "[]", _aList.toString ());

      _aList = Arrays.asList (1);
      assertEquals ("The list was not what we expected", "[1]", _aList.toString ());

      _aList = Arrays.asList (1, "two");
      assertEquals ("The list was not what we expected", "[1, two]", _aList.toString ());

   }
}
