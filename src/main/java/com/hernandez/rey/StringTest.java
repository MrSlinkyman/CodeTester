package com.hernandez.rey;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.UnsupportedEncodingException;
import java.text.MessageFormat;

import org.junit.Test;

/**
 * StringTest represents unit tests intended to test out various string methods
 * 
 * @author <a href="mailto:Rey.Hernandez@am.sony.com">Rey Hernandez</a>
 * @version $Id$
 * @since Jun 9, 2010
 * 
 */
public class StringTest
{

	/**
	 * Test method for {@link java.lang.String#contains(java.lang.CharSequence)}.
	 */
	@Test
	public void testContains()
	{
		assertTrue("The string should have been there", "ReyIsGreat".contains("Is"));
		assertFalse("The string should have been there", "ReyIsGreat".contains("is"));
	}

	@Test
	public void testUTF8String() throws UnsupportedEncodingException {
		byte[] bytes = new byte[] { 32, 0 };
		String value = new String(bytes, "UTF-8");
		String space = " ";
		value = value.trim();
		assertEquals("The result was not as expected", space, value);
	}

	@Test
	public void testUTF16String() throws UnsupportedEncodingException {
		byte[] bytes = new byte[] { 0x0020, 0 };
		String value = new String(bytes, "UTF-16");
		String space = " ";
		assertEquals("The result was not as expected", space, value);

	}

	@Test
	public void testTrim() throws UnsupportedEncodingException {
		byte[] bytes = new byte[] { 32, 0 };
		String value = new String(bytes, "UTF-8");
		int len = value.length();
		int st = 0;
		char[] val = value.toCharArray(); /* avoid getfield opcode */

		while ((st < len) && (val[st] <= ' ')) {
			st++;
		}
		while ((st < len) && (val[len - 1] <= ' ')) {
			len--;
		}
		String strReturn = ((st > 0) || (len < value.length())) ? value.substring(st, len) : value;

	}

	/**
	 * Test method for verifying how lone string replacement takes with various implementations.
	 */
	@Test
	public void testStringBuilderVsMessage()
	{
		final String TEST = "This is my first message: some more stuff: some other stuff";
		final String MESSAGE = "This is my first message: {0}: {1}";
		final String REPLACE1 = "some more stuff";
		final String REPLACE2 = "some other stuff";
		final Object[] REPLACEMENTS = { REPLACE1, REPLACE2 };
		int _iterations = 1000000;

		long _start1 = System.currentTimeMillis();
		for (int i = 0; i < _iterations; i++) {
			StringBuilder _builder = new StringBuilder("This is my first message: ").append(REPLACE1).append(": ").append(REPLACE2);
			assertTrue("The strings are not the same", TEST.equals(_builder.toString()));
		}
		long _end1 = System.currentTimeMillis();

		long _start2 = System.currentTimeMillis();
		for (int i = 0; i < _iterations; i++) {
			String _replaced = MESSAGE.replaceFirst("\\{0\\}", REPLACE1).replaceFirst("\\{1\\}", REPLACE2);
			assertTrue("The replaced strings are not the same", TEST.equals(_replaced));
		}
		long _end2 = System.currentTimeMillis();

		long _start3 = System.currentTimeMillis();
		for (int i = 0; i < _iterations; i++) {
			String _replaced = MessageFormat.format(MESSAGE, REPLACEMENTS);
			assertTrue("The replaced strings are not the same", TEST.equals(_replaced));
		}
		long _end3 = System.currentTimeMillis();

		long _start4 = System.currentTimeMillis();
		for (int i = 0; i < _iterations; i++) {
			String _replaced = MESSAGE.replaceAll("\\{0\\}", REPLACE1).replaceAll("\\{1\\}", REPLACE2);
			assertTrue("The replaced strings are not the same", TEST.equals(_replaced));
		}
		long _end4 = System.currentTimeMillis();

		System.out.println("Duration String Builder = " + (_end1 - _start1) + " ms");
		System.out.println("Duration Replace First  = " + (_end2 - _start2) + " ms");
		System.out.println("Duration Replace All    = " + (_end4 - _start4) + " ms");
		System.out.println("Duration MessageFormat  = " + (_end3 - _start3) + " ms");
	}
}
