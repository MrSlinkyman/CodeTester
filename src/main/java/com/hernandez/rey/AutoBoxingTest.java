package com.hernandez.rey;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

public class AutoBoxingTest {

	/**
	 * testNullPointer is used to see when an Integer autoboxes to an int if it will generate a NPE if the Integer is
	 * null
	 */
	@SuppressWarnings("null")
	@Test(expected = NullPointerException.class)
	public void testNullPointer() {
		Integer _integer1 = new Integer(135);
		int _int1 = 135;
		assertTrue("Integers not the same", 135 == _integer1);
		assertTrue("Integers not the same", 135 == _int1);

		_integer1 = null;
		_int1 = _integer1;

		fail("Should have gotten a NPE at this point");
	}

	/**
	 * testCachingIntegers tests the limits of caching to see where Integer doesn't cache.
	 * In Oracle's Hotspot JVM the caching is -128 to 127, it may be different or non-existent in other JVMs
	 */
	@Test
	public void testCachingIntegers() {
		int i = -129;
		Integer _a = i, _b = i;
		assertFalse("Integer " + i + " is cached", _a == _b);

		for (i++; i < 128; i++) {
			_a = i;
			_b = i;
			assertTrue("Integer " + i + " is not cached", _a == _b);
		}

		_a = i;
		_b = i;
		assertFalse("Integer " + i + " is cached", _a == _b);
	}

}
