package com.hernandez.rey;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class PrimitivesTest {

	private static final String PRINT_FORMAT = "%1$016x %1$20d\n";
//	private static final String HEX_FORMAT = "%016x";

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	private void printIntAndLong(String message, int intValue, long longValue) {
		System.out.println(message);
		assertEquals("not equal", intValue, (int) longValue);
		// if ((int) longValue == intValue) {
		// System.out.println("intValue and longValue are equal ints");
		// } else if (longValue == (long) intValue) {
		// System.out.println("intValue and longValue are equal longs");
		// } else {
		// System.out.println("intValue and longValue are not equal");
		// }
		System.out.printf("      int value:" + PRINT_FORMAT, intValue);
		System.out.printf("     long value:" + PRINT_FORMAT, longValue);
		System.out.printf("(int)long value:" + PRINT_FORMAT, (int) longValue);
		System.out.printf("(long)int value:" + PRINT_FORMAT, (long) intValue);
		System.out.println();
	}

	@Test
	public void testLongToUINT32() {
		printIntAndLong("test1 Int Max Value", 0x7FFFFFFF, Integer.MAX_VALUE);

		printIntAndLong("test2 Int Max Value + 1", 0x80000000, 1L + (long) Integer.MAX_VALUE);

		printIntAndLong("test3 Int Max Value - 2", 0x7FFFFFFD, -2L + (long) Integer.MAX_VALUE);

		printIntAndLong("test4 Larg Long 0x90000001/2415919105L", 0x90000001, 2415919105L);
		
		/*
		 * System.out.println("test5");
		 * int test5 = -2147483648 - 1;
		 * long test5Actual = 0xFFFFFFFF7FFFFFFFL;
		 * assertEquals("not equal", test5Actual, (long) test5);
		 * assertEquals("not equal", (int) test5Actual, test5);
		 * System.out.printf(PRINT_FORMAT, test5);
		 * System.out.printf(PRINT_FORMAT, test5Actual);
		 * System.out.printf(PRINT_FORMAT, (int) test5Actual);
		 */

		printIntAndLong("test6 Integer Min Value", Integer.MIN_VALUE, 0xFFFFFFFF80000000L);

		printIntAndLong("test6a Huge long value", 0x0000000055667788, 0x1122334455667788L);

		printIntAndLong("test6b value = 1259119123", 1259119123, 1259119123L);
		printIntAndLong("test6c value = 1350030123", 1350030123, 1350030123L);

		System.out.println("test7 Integer Min Value (hex), Long Int Max Value+1");
		int test7 = 0x0000000080000000;
		// long test7Actual = 2147483648L;
		long test7Actual = 0x0000000080000000L;
		System.out.printf("       int value:" + PRINT_FORMAT, test7);
		System.out.printf("(long) int value:" + PRINT_FORMAT, (long) test7);
		System.out.printf("      long value:" + PRINT_FORMAT, test7Actual);
		System.out.printf("(int) long value:" + PRINT_FORMAT, (int) test7Actual);
		// System.out.printf(PRINT_FORMAT, safeLongToInt(test7Actual));
		assertEquals("not equal", (int) test7Actual, test7);
		// assertEquals("not equal", test7Actual, (long) test7);
		System.out.println();

		System.out.println("test8 Int -2, Long = 0xFFFFFFFe");
		// int test8 = 0xFFFFFFFe;
		int test8 = -2;
		// long test8Actual = 4294967294L;
		long test8Actual = 0xFFFFFFFeL;
		System.out.printf("       int value:" + PRINT_FORMAT, test8);
		System.out.printf("(long) int value:" + PRINT_FORMAT, (long) test8);
		System.out.printf("      long value:" + PRINT_FORMAT, test8Actual);
		System.out.printf("(int) long value:" + PRINT_FORMAT, (int) test8Actual);

		// System.out.printf(PRINT_FORMAT, safeLongToInt(test7Actual));
		// assertEquals("not equal", (int) test8Actual, test8);
		// assertEquals("not equal", test8Actual, (long) test8);
		System.out.println();

		long test9Actual = 0xFFFFFFFFL;
		System.out.println("test9 long = " + test9Actual);
		// long test9Actual = 2159271066L;
		// long test9Actual = 9355222441L;
		System.out.printf("                      long value:" + PRINT_FORMAT, test9Actual);
		System.out.printf("                (int) long value:" + PRINT_FORMAT, (int) test9Actual);
		System.out.printf("          (long)(int) long value:" + PRINT_FORMAT, (long) (int) test9Actual);
		System.out.printf("(long)(int) long value bitmasked:" + PRINT_FORMAT, (long) ((int) test9Actual & 0x00000000FFFFFFFFL));
		System.out.println();
		
		test9Actual++;
		System.out.println("test9a long = " + test9Actual);
		System.out.printf("                      long value:" + PRINT_FORMAT, test9Actual);
		System.out.printf("                (int) long value:" + PRINT_FORMAT, (int) test9Actual);
		System.out.printf("          (long)(int) long value:" + PRINT_FORMAT, (long) (int) test9Actual);
		System.out.printf("(long)(int) long value bitmasked:" + PRINT_FORMAT, (long) ((int) test9Actual & 0xFFFFFFFFL));
		System.out.println();

		test9Actual = 9355222441L;
		System.out.println("test9b long = " + test9Actual);
		System.out.printf("                      long value:" + PRINT_FORMAT, test9Actual);
		System.out.printf("                (int) long value:" + PRINT_FORMAT, (int) test9Actual);
		System.out.printf("          (long)(int) long value:" + PRINT_FORMAT, (long) (int) test9Actual);
		System.out.printf("(long)(int) long value bitmasked:" + PRINT_FORMAT, (long) ((int) test9Actual & 0xFFFFFFFFL));
		System.out.println();

		System.out.println("test10 int = -2135696230");
		int test10Actual = -2135696230;
		System.out.printf("                int value:" + PRINT_FORMAT, test10Actual);
		System.out.printf("          (long)int value:" + PRINT_FORMAT, (long) test10Actual);
		System.out.printf("     (int)(long)int value:" + PRINT_FORMAT, (int) (long) test10Actual);
		System.out.printf("(long)int value bitmasked:" + PRINT_FORMAT, ((long) test10Actual & 0xFFFFFFFFL));
		System.out.println();

		System.out.println("test11 long = 2XInteger.MAX_VALUE");
		long test11Actual = 2l * (long) Integer.MAX_VALUE + 1l;
		System.out.printf("               long value:" + PRINT_FORMAT, test11Actual);
		System.out.printf("          (int)long value:" + PRINT_FORMAT, (int) test11Actual);
		System.out.printf("    (long)(int)long value:" + PRINT_FORMAT, (long) (int) test11Actual);
		System.out.printf("(int)long value bitmasked:" + PRINT_FORMAT, ((int) test11Actual & 0xFFFFFFFFL));
		System.out.println();

		System.out.println("test11a long = 2XInteger.MAX_VALUE+1");
		test11Actual++;
		System.out.printf("               long value:" + PRINT_FORMAT, test11Actual);
		System.out.printf("          (int)long value:" + PRINT_FORMAT, (int) test11Actual);
		System.out.printf("    (long)(int)long value:" + PRINT_FORMAT, (long) (int) test11Actual);
		System.out.printf("(int)long value bitmasked:" + PRINT_FORMAT, ((int) test11Actual & 0xFFFFFFFFL));
		System.out.println();

		System.out.println("test11b long = 2XInteger.MAX_VALUE+2");
		test11Actual++;
		System.out.printf("               long value:" + PRINT_FORMAT, test11Actual);
		System.out.printf("          (int)long value:" + PRINT_FORMAT, (int) test11Actual);
		System.out.printf("    (long)(int)long value:" + PRINT_FORMAT, (long) (int) test11Actual);
		System.out.printf("(int)long value bitmasked:" + PRINT_FORMAT, ((int) test11Actual & 0xFFFFFFFFL));
		System.out.println();

		System.out.println("test11c long = 2XInteger.MAX_VALUE-10");
		test11Actual = 2l * (long) Integer.MAX_VALUE - 10l;
		System.out.printf("               long value:" + PRINT_FORMAT, test11Actual);
		System.out.printf("          (int)long value:" + PRINT_FORMAT, (int) test11Actual);
		System.out.printf("    (long)(int)long value:" + PRINT_FORMAT, (long) (int) test11Actual);
		System.out.printf("(int)long value bitmasked:" + PRINT_FORMAT, ((int) test11Actual & 0xFFFFFFFFL));
		System.out.println();

		System.out.println("test11d long = 2^32-10");
		test11Actual = (long) Math.pow(2, 32) - 10l;
		System.out.printf("               long value:" + PRINT_FORMAT, test11Actual);
		System.out.printf("          (int)long value:" + PRINT_FORMAT, (int) test11Actual);
		System.out.printf("    (long)(int)long value:" + PRINT_FORMAT, (long) (int) test11Actual);
		System.out.printf("(int)long value bitmasked:" + PRINT_FORMAT, ((int) test11Actual & 0xFFFFFFFFL));
		System.out.println();

		System.out.println("test11d long = -10");
		test11Actual = -10l;
		System.out.printf("               long value:" + PRINT_FORMAT, test11Actual);
		System.out.printf("          (int)long value:" + PRINT_FORMAT, (int) test11Actual);
		System.out.printf("    (long)(int)long value:" + PRINT_FORMAT, (long) (int) test11Actual);
		System.out.printf("(int)long value bitmasked:" + PRINT_FORMAT, ((int) test11Actual & 0xFFFFFFFFL));
		System.out.println();

		System.out.println("test12 int = Integer.MIN_VALUE");
		int test12Actual = Integer.MIN_VALUE;
		System.out.printf("                int value:" + PRINT_FORMAT, test12Actual);
		System.out.printf("          (long)int value:" + PRINT_FORMAT, (long) test12Actual);
		System.out.printf("     (int)(long)int value:" + PRINT_FORMAT, (int) (long) test12Actual);
		System.out.printf("(long)int value bitmasked:" + PRINT_FORMAT, ((long) test12Actual & 0xFFFFFFFFL));
		System.out.println();

	}

	public static int safeLongToInt(long l) {
		int i = (int) l;
		if ((long) i != l) {
			throw new IllegalArgumentException(l + " cannot be cast to int without changing its value.");
		}
		return i;
	}
	public static long toUnsigned(int b) {
		return (long) (b & 0xff);
	}

	public static byte toSigned(short i) {
		return (byte) i;
	}

}
