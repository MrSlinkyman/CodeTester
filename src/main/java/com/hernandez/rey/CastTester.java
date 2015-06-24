/*
 * Copyright © 2000 - 2008 Sony Network Entertainment. All rights reserved.
 */
package com.hernandez.rey;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.log4j.Logger;
import org.junit.Ignore;
import org.junit.Test;

import com.hernandez.rey.objects.MyMainObject;
import com.hernandez.rey.objects.MySubObject;
import com.hernandez.rey.objects.MySubObjectImpl;


/**
 * CastTester represents ...
 * 
 * @author <a href="mailto:Rey.Hernandez@am.sony.com">Rey Hernandez</a>
 * @version $Id$
 * @since Sep 21, 2009
 * 
 * @todo Complete description
 */
public class CastTester
{

	private static final Logger log = Logger.getLogger(CastTester.class);

   @Ignore
   public void testLists ()
   {
      final Set <MySubObject> _set1 = new HashSet <MySubObject> ();
      _set1.add (new MySubObjectImpl ("name1"));
      @SuppressWarnings("unchecked")
	final Set <MyMainObject> _set3 = (Set <MyMainObject>) (Set <?>) _set1;
      _set3.add (new MySubObjectImpl ("name2"));

      final Iterator <MyMainObject> _iterator = _set3.iterator ();
      // final MyMainObject _obejct = _iterator.next ();
      _iterator.remove ();
      // _set3.remove (_obejct);
      assertEquals ("The names are wrong", "name1", _iterator.next ().getName ());

      // SecureRandom.getInstance (algorithm);
      // Base64.encodeBase64 (binaryData);

   }


   @Test
   public void testInteger ()
   {
      final Integer x = 3;
      final Integer y = 4;

      assertFalse ("The integers should not be equal", x == y);

      final Integer z = 3;
      assertTrue ("The integers should have been equal", x == z);
   }

	@Test
	public void testInstanceOf()
	{
		final Integer x = 3;
		final int y = 4;
		final Object xObject = x;

		assertTrue("Should be instanceof Integer", x instanceof Integer);
		log.info(x.getClass());

		final Integer z = 3;
		assertTrue("Should be instanceof Integer", z instanceof Integer);

	}

	@Test
	public void testMapObjects() {
		Map<String, Serializable> map = new HashMap<String, Serializable>();
		map.put("String", "String value");
		map.put("Integer", 3);
		map.put("Long", 5l);
		map.put("Byte", (byte) 3);
		map.put("List", new ArrayList<>(Arrays.asList(new Object[] { new Object(), new String("1") })));
		
		for (Entry<String, Serializable> x : map.entrySet()) {
			log.info("Key:" + x.getKey() + ", value:" + x.getValue() + ":" + x.getValue().getClass());
		}

	}
}
