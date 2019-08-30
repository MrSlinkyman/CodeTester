package com.hernandez.rey.performance;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * This test will exercise a variety of operations and output information about
 * how long each takes.
 * 
 * @author rey.hernandez
 *
 */
public class TestStringPerformance {

	private static final String EMPTY_STRING = "";
	private static final String STRING_SEPARATOR = ", ";
	private static final int NUMBER_OF_RUNS = 1000000;
	private static final int MAX_SIZE_OF_RANDOM_STRING = 50;

	@Test
	public void testStringBuilder() {
		List<String> responses = generateRandomStrings(NUMBER_OF_RUNS);
		final String[] responsesArray = responses.toArray(new String[] {});

		// inefficient
		final long startTime1 = System.currentTimeMillis();
		String responseBad = joinStringsBad(responsesArray);
		final long endTime1 = System.currentTimeMillis();
		final long inefficientRunTime = endTime1 - startTime1;

		// better
		final long startTime2 = System.currentTimeMillis();
		String responseGood = joinStringsGood(responsesArray);
		final long endTime2 = System.currentTimeMillis();
		final long efficientRunTime = endTime2 - startTime2;

		System.out.println("Time for Inefficient: " + inefficientRunTime + "ms");
		System.out.println("  Time for Efficient: " + efficientRunTime + "ms");

		assertEquals("Joined strings different", responseBad, responseGood);
		assertTrue("Innefficient was better than efficient", efficientRunTime < inefficientRunTime);
	}

	@Test
	public void testConcatenationPerformance() {
		
		long inefficientTime = 0;
		long betterTime = 0;
		long bestTime = 0;
		for (int i = 0; i < NUMBER_OF_RUNS; i++) {
			String url = generateRandomString(200);
			String name = generateRandomString(25);
			String address = generateRandomString(50);
//			System.out.println(url + "\n"+name+"\n"+address);

			long startTime = System.currentTimeMillis();
			String inefficientResult = inefficientStringConcat(url, name, address);
			long endTime = System.currentTimeMillis();
			inefficientTime += endTime - startTime;

			startTime = System.currentTimeMillis();
			String betterResult = betterStringConcat(url, name, address);
			endTime = System.currentTimeMillis();
			betterTime += endTime - startTime;
			assertEquals("inefficient result does not equal better result", inefficientResult, betterResult);

			startTime = System.currentTimeMillis();
			String bestResult = bestStringConcat(url, name, address);
			endTime = System.currentTimeMillis();
			bestTime += endTime - startTime;

			assertEquals("better result does not equal best result", betterResult, bestResult);
		}



		System.out.println("Time for Inefficient: " + inefficientTime + "ms");
		System.out.println("     Time for Better: " + betterTime + "ms");
		System.out.println("       Time for Best: " + bestTime + "ms");

		assertTrue("inefficient time better than better time", betterTime < inefficientTime);
		assertTrue("better time better than best time", betterTime > bestTime);

	}

	private String bestStringConcat(final String url, final String name, final String address) {
		// best
		StringBuilder sb = new StringBuilder((url!=null?url:EMPTY_STRING).length()+(name!=null?name:EMPTY_STRING).length()+(address!=null?address:EMPTY_STRING).length());
		sb.append('[');
		if (url != null)
			sb.append(url).append(STRING_SEPARATOR);
		if (name != null)
			sb.append(name).append(STRING_SEPARATOR);
		return sb.append(address).append(']').toString();
	}

	private String betterStringConcat(final String url, final String name, final String address) {
		// better
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		if (url != null)
			sb.append(url).append(STRING_SEPARATOR);
		if (name != null)
			sb.append(name).append(STRING_SEPARATOR);
		return sb.append(address).append("]").toString();
	}

	private String inefficientStringConcat(final String url, final String name, final String address) {
		// inefficient
		String s = "[";
		if (url != null)
			s += url + STRING_SEPARATOR;
		if (name != null)
			s += name + STRING_SEPARATOR;
		s += address + "]";
		return s;
	}

	private String joinStringsBad(final String[] inputStrings) {
		StringBuilder sb = new StringBuilder();
		boolean first = true;
		for (String url : inputStrings) {
			sb.append(url);
			if (!first)
				sb.append(STRING_SEPARATOR);
			first = false;
		}
		return sb.toString();
	}

	private String joinStringsGood(final String[] inputStrings) {
		StringBuilder sb = new StringBuilder(
				MAX_SIZE_OF_RANDOM_STRING * (STRING_SEPARATOR.length() + inputStrings.length));
		boolean first = true;
		for (String url : inputStrings) {
			sb.append(url);
			if (!first)
				sb.append(STRING_SEPARATOR);
			first = false;
		}
		return sb.toString();
	}

	private List<String> generateRandomStrings(final int numberOfStrings) {
		ArrayList<String> randomStrings = new ArrayList<String>(numberOfStrings);
		for (int i = 0; i < numberOfStrings; i++) {
			randomStrings.add(generateRandomSizeString(MAX_SIZE_OF_RANDOM_STRING));
		}
		return randomStrings;
	}

	private String generateRandomSizeString(final int maxSizeOfRandomString) {
		return generateRandomString((int)(Math.random() * maxSizeOfRandomString));

	}
	private String generateRandomString(final int sizeOfString) {
		StringBuilder randomString = new StringBuilder(sizeOfString);
		for (int n = 0; n < sizeOfString; n++) {
			randomString.append((char) (94 * Math.random() + 32));
		}
		return randomString.toString();
	}
}
