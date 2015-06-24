package com.hernandez.rey;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Stack;
import java.util.TreeSet;
import java.util.Vector;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ListsTest {
	private static final int ITERATIONS = 100000;
	private static final int CLIENT_CODE_STACK_INDEX;
	private static final String format = "%20s : %3d\n";
	static {
		// Finds out the index of "this code" in the returned stack trace - funny but it differs in JDK 1.5 and 1.6
		int i = 0;
		for (StackTraceElement ste : Thread.currentThread().getStackTrace()) {
			i++;
			if (ste.getClassName().equals(ListsTest.class.getName())) {
				break;
			}
		}
		CLIENT_CODE_STACK_INDEX = i;
	}
	private static Map<String, Long> resultMap;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		resultMap = new HashMap<String, Long>();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {

		for (Entry<String, Long> result : resultMap.entrySet()) {
			System.out.printf(format, result.getKey(), result.getValue());
		}
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testTreeSet() {
		populateList(methodName(), new TreeSet<String>());
	}

	@Test
	public void testVector() {
		populateList(methodName(), new Vector<String>());
	}

	@Test
	public void testStack() {
		populateList(methodName(), new Stack<String>());
	}

	@Test
	public void testEmptySet() {
		populateList(methodName(), Collections.<String> emptySet());
	}

	@Test
	public void testHashSet() {
		populateList(methodName(), new HashSet<String>());
	}

	@Test
	public void testEmptyList() {
		populateList(methodName(), Collections.<String> emptyList());
	}

	@Test
	public void testArrayList() {
		populateList(methodName(), new ArrayList<String>());
	}

	@Test
	public void testLinkedHashSet() {
		populateList(methodName(), new LinkedHashSet<String>());
	}

	@Test
	public void testLinkedList() {
		populateList(methodName(), new LinkedList<String>());
	}

	public static String methodName() {
		return Thread.currentThread().getStackTrace()[CLIENT_CODE_STACK_INDEX].getMethodName();
	}

	private void populateList(String methodName, Collection<String> stringList) {
		long totalTime = 0L;
		try {
			long _start = System.currentTimeMillis();
			for (int i = 0; i < ITERATIONS; i++) {
				stringList.add(String.valueOf(i));
			}
			long _end = System.currentTimeMillis();
			totalTime = _end - _start;
		} catch (UnsupportedOperationException e) {
			totalTime = -1L;
		}
		resultMap.put(methodName, totalTime);
	}

}
