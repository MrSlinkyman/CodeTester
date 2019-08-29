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

	private static final String STRING_SEPARATOR = ", ";
	private static final int NUMBER_OF_RUNS=100000000;
	private static final int MAX_SIZE_OF_RANDOM_STRINGS=50;
	
	@Test
	public void testStringBuilder() {
		List<String> responses = generateRandomStrings();
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

	private String joinStringsBad(String[] inputStrings) {
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

	private String joinStringsGood(String[] inputStrings) {
		StringBuilder sb = new StringBuilder(MAX_SIZE_OF_RANDOM_STRINGS * (STRING_SEPARATOR.length()+inputStrings.length));
		boolean first = true;
		for (String url : inputStrings) {
			sb.append(url);
			if (!first)
				sb.append(STRING_SEPARATOR);
			first = false;
		}
		return sb.toString();
	}
	
	private List<String> generateRandomStrings() {
		ArrayList<String> randomStrings = new ArrayList<String>(NUMBER_OF_RUNS);
		for(int i = 0; i < NUMBER_OF_RUNS; i++) {
			StringBuilder randomString = new StringBuilder(MAX_SIZE_OF_RANDOM_STRINGS);
			for(int n = 0; n < Math.random()*MAX_SIZE_OF_RANDOM_STRINGS; n++) {
				randomString.append((char)(126*Math.random()+32));
			}
			randomStrings.add(randomString.toString());
		}
		return randomStrings;
	}
}

