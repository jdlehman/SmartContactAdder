package edu.wm.weightedNames;

import java.io.IOException;

import junit.framework.TestCase;

public class ExtendedNamesTest extends TestCase {

	SetOfWeightedNames emptySet;
	SetOfWeightedNames  testSet;
	
	protected void setUp() throws Exception {
		emptySet = NamesFactory.createNamesVersion1(null);
		testSet = NamesFactory.createNamesVersion1("./res/test.zip");
	}

	protected void tearDown() throws Exception {
		emptySet = null;
		testSet = null;
	}

	/* ***************BEGIN TESTS FOR getSubsetWithMatchingPrefix METHOD********************** */
	/** Purpose: get subset from an empty set
	 * Expected Outcome: returns empty SetOfWeightedNames
	 */
	public void testGetSubset1(){
		SetOfWeightedNames test = emptySet.getSubsetWithMatchingPrefix("A", 10);
		
		assertEquals(0, ((Names) test).getSize());
	}
	
	/** Purpose: get subset from an empty set
	 * Expected Outcome: returns empty SetOfWeightedNames
	 */
	public void testGetSubset2(){
		SetOfWeightedNames test = emptySet.getSubsetWithMatchingPrefix(null, 5);
		
		assertEquals(0, ((Names) test).getSize());
	}
	
	/** Purpose: get subset from a valid set with valid prefix
	 * Expected Outcome: returns SetOfWeightedNames with <= k items containing the prefix
	 */
	public void testGetSubset3(){
		SetOfWeightedNames test = testSet.getSubsetWithMatchingPrefix("A", 5);
		
		assertEquals(2, ((Names) test).getSize());
	}
	
	/** Purpose: get subset from an valid set with null prefix
	 * Expected Outcome: returns SetOfWeightedNames with <= k items of any prefix
	 */
	public void testGetSubset4(){
		SetOfWeightedNames test = testSet.getSubsetWithMatchingPrefix(null, 5);
		
		assertEquals(5, ((Names) test).getSize());
	}
	/* ***************END TESTS FOR getSubsetWithMatchingPrefix METHOD********************** */
	
	
	/* ***************BEGIN TESTS FOR join METHOD********************** */
	/** Purpose: join empty set and valid set
	 * Expected Outcome: the sets size is the size of the valid set
	 */
	public void testJoin1(){
		SetOfWeightedNames test = testSet;
		int sizeBefore = ((Names) test).getSize();
		test.join(emptySet);
		
		int sizeAfter = ((Names) test).getSize();
		
		assertEquals(sizeBefore, sizeAfter);
	}
	
	/** Purpose: join set and with itself
	 * Expected Outcome: the sets size remains the same
	 */
	public void testJoin2(){
		SetOfWeightedNames test = testSet;
		int sizeBefore = ((Names) test).getSize();
		test.join(testSet);
		
		int sizeAfter = ((Names) test).getSize();
		
		assertEquals(sizeBefore, sizeAfter);
	}
	
	/** Purpose: join two valid sets containing different and the same names
	 * Expected Outcome: the sets are joined such that only unique elements are added, and if WeightedNames
	 * have the same name it takes the WeightedName with the higher weight
	 */
	public void testJoin3(){
		boolean exThrown = false;
		
		SetOfWeightedNames test1 = null;
		try {
			test1 = NamesFactory.createNamesVersion1(null);
		} catch (IOException e) {
			exThrown = true;
		}
		test1.add("Mary", 10);
		test1.add("Joe", 5);
		test1.add("Bob", 20);
		test1.add("Mark", 15);
		
		SetOfWeightedNames test2 = null;
		try {
			test2 = NamesFactory.createNamesVersion1(null);
		} catch (IOException e) {
			exThrown = true;
		}
		test2.add("Mary", 5);
		test2.add("John", 4);
		test2.add("Joe", 7);
		test2.add("Luke", 11);
		
		SetOfWeightedNames combined = test1.join(test2);
		
		assertEquals(false, exThrown);
		assertEquals(6, ((Names) combined).getSize());
		assertEquals(10.0, combined.getWeight("Mary"));
		assertEquals(7.0, combined.getWeight("Joe"));
	}
	/* ***************END TESTS FOR join METHOD********************** */
	
	
	/* ***************BEGIN TESTS FOR writeZipFile METHOD********************** */
	/** Purpose: write zip file to empty file location
	 * 	Expected Outcome: throws exception
	 */
	public void testWriteZipFile1(){
		boolean exceptionThrown = false;
		
		try {
			testSet.writeZipFile("");
		} catch (IOException e) {
			exceptionThrown = true;
		}
		
		assertEquals(true, exceptionThrown);
	}
	
	/** Purpose: write valid set of names (testSet) to zip file
	 * 	Expected Outcome: writes names to zip file
	 */
	public void testWriteZipFile2(){
		boolean exThrown = false;
		SetOfWeightedNames dataWritten = null;
		
		try {
			testSet.writeZipFile("./res/testWriteZipFile2.zip");
			dataWritten = NamesFactory.createNamesVersion1("./res/testWriteZipFile2.zip");
		} catch (IOException e) {
			exThrown = true;
		}
		
		assertEquals(false, exThrown);
		//check that data written to zip file is the same as set used to write it
		assertEquals(((Names) dataWritten).getSize(), ((Names) testSet).getSize());
		assertEquals(((Names) testSet).getSize(), ((Names) testSet.join(dataWritten)).getSize());
		assertEquals(dataWritten.getWeight("Mary"), testSet.getWeight("Mary"));
		
	}
	/* ***************END TESTS FOR writeZipFile METHOD********************** */
}
