package edu.wm.weightedNames;

import java.io.IOException;

import junit.framework.TestCase;

public class NamesTest2 extends TestCase {

	SetOfWeightedNames n;
	//test Constructor
	public void testNames() {
		try {
			n  = NamesFactory.createNamesVersion1("res/gradertest.zip");
		}
		catch(IOException ex)
		{
			fail("File I/O error. ");
			return;
		}
	}
	public void setUp()
	{
		try {
			n  = NamesFactory.createNamesVersion1("res/gradertest.zip");
		}
		catch(IOException ex)
		{
			fail("File I/O error. ");
			return;
		}
	}

	//test name that appeared twice
	public void testGetWeight() {
		double weight = n.getWeight("Isabella");
		assertTrue(weight == 22067 + 18520);
	}
	
	//test name that appeared once
	public void testGetWeight2() {
		double weight = n.getWeight("Emily");
		assertTrue(weight == 17364);
	}
	
	//test name that did not appear	
	public void testGetWeight3() {
		double weight = n.getWeight("Zack");
		assertTrue(Double.isNaN(weight));
	}
	
	//test name that did not appear	
	public void testContains1() {
		boolean contain;
		contain = n.contains("Zack");
		assertFalse(contain == true);
	}

	//test name that appeared
	public void testContains2() {
		boolean contain;
		contain = n.contains("Isabella");
		assertTrue(contain == true);
	}

	//test getting a few names	
	public void testGetTopNames3() {
		String str[] = n.getTopNames(3);
		assertTrue(str.length == 3);
		assertEquals(str[0],"Isabella");
		assertEquals(str[1],"Emma");
		assertEquals(str[2],"Olivia");
	}

	//test getting too many names
	public void testGetTopNames10() {
		String str[] = n.getTopNames(10);
		assertTrue(str.length == 5);
		assertEquals(str[0],"Isabella");
		assertEquals(str[1],"Emma");
		assertEquals(str[2],"Olivia");
		assertEquals(str[3],"Emily");
		assertEquals(str[4],"Sophia");
	}	
	
	//test getting top 0 names
	public void testGetTopNamesWithMatchingPrefix0() {
		String str[] = n.getTopNamesWithMatchingPrefix(null, 10);
		assertTrue(str.length == 5);
		assertEquals(str[0],"Isabella");
		assertEquals(str[1],"Emma");
		assertEquals(str[2],"Olivia");
		assertEquals(str[3],"Emily");
		assertEquals(str[4],"Sophia");
	}
	
	//test getting too many names with common prefix
	public void testGetTopNamesWithMatchingPrefix1() {
		String str[] = n.getTopNamesWithMatchingPrefix("Em", 10);
		assertTrue(str.length == 2);
		assertEquals(str[0],"Emma");
		assertEquals(str[1],"Emily");
	}

	//test getting too many names with non-existent prefix
	public void testGetTopNamesWithMatchingPrefix2() {
		String str[] = n.getTopNamesWithMatchingPrefix("Xa", 10);
		assertTrue(str.length == 0);
	}

	
}
