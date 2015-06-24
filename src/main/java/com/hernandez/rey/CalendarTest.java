/*
 * Copyright © 2000 - 2008 Sony Network Entertainment. All rights reserved.
 */
package com.hernandez.rey;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TreeSet;


/**
 * CalendarTest represents ...
 * 
 * @author <a href="mailto:Rey.Hernandez@am.sony.com">Rey Hernandez</a>
 * @version $Id$
 * @since Oct 14, 2009
 * 
 * @todo Complete description
 */
public class CalendarTest
{

   @Test
   public void testComparator ()
   {
      final Calendar _left = new GregorianCalendar (2009, 0, 3);
      final Calendar _right = new GregorianCalendar (2009, 0, 4);
      assertTrue ("The first date should be before the second", _left.before (_right));
      assertTrue ("The first date should compare to earlier than the second", _left.compareTo (_right) < 0);
      assertFalse ("The second date should not compare to earlier than the first", _right.compareTo (_left) < 0);
   }


   @Test
   public void testSortedCalendarSet ()
   {
      final Calendar _earliest = new GregorianCalendar (2009, 0, 3);
      final Calendar _nextEarliest = new GregorianCalendar (2009, 0, 4);
      final Calendar _priorLatest = new GregorianCalendar (2009, 0, 5);
      final Calendar _latest = new GregorianCalendar (2009, 0, 6);

      final List <Calendar> _unsortedCalendarList = new ArrayList <Calendar> ();
      _unsortedCalendarList.add (_priorLatest);
      _unsortedCalendarList.add (_latest);
      _unsortedCalendarList.add (_nextEarliest);
      _unsortedCalendarList.add (_earliest);


      final List <Calendar> _sortedCalendarList = new ArrayList <Calendar> (new TreeSet <Calendar> (
            _unsortedCalendarList));
      assertEquals ("The first in the set should be earliest", _earliest, _sortedCalendarList.get (0));
      assertEquals ("The last in the set should be the latest", _latest, _sortedCalendarList.get (_sortedCalendarList
            .size () - 1));
      assertEquals ("The first in the unsorted list should be priorLatest", _priorLatest, _unsortedCalendarList.get (0));
      assertEquals ("The last in the unsorted list should be earliest", _earliest, _unsortedCalendarList
            .get (_unsortedCalendarList.size () - 1));
   }
}
