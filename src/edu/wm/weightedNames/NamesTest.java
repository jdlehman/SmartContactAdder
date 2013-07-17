package edu.wm.weightedNames;

import java.io.IOException;

import junit.framework.TestCase;

/**
 * @author jonathanlehman 
 *
 */
public class NamesTest extends TestCase {

	private SetOfWeightedNames validMultFilesNames;
	private SetOfWeightedNames emptyNames;
	
	
	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		validMultFilesNames =  NamesFactory.createNamesVersion1("./res/test.zip");
		emptyNames = new NamesTreeSet();
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		//super.tearDown();
		validMultFilesNames = null;
		emptyNames = null;
	}
	
	/* ***************BEGIN TESTS FOR ADD METHOD********************** */
	
	/**Purpose: adds a valid weighted name to an existing list constructed from multiple files with multiple entries (valid and invalid data in file)
	 * Expected Outcome: weightedName is added to list, size increments, WeightedName now contained in list  */
	public void testAddValidWeightedNameValidList(){
		//size of list before adding
		int sizeBefore = ((Names) validMultFilesNames).getSize();
		//add name to list
		validMultFilesNames.add("TestName", 20);
		
		//check that the valid WeightedName has been added to the list
		assertEquals(sizeBefore + 1, ((Names) validMultFilesNames).getSize());
		assertEquals(true, validMultFilesNames.contains("TestName"));
	}
	
	/**Purpose: adds a valid weighted name to a list constructed from a zip file with an empty text file in it
	 * Expected Outcome: weightedName is added to list, size increments, WeightedName now contained in list  */
	public void testAddValidWeightedNameEmptyList(){
		//size of list before adding
		int sizeBefore = ((Names) emptyNames).getSize();
		
		//add name to list
		emptyNames.add("TestName", 20);
		
		//check that the valid WeightedName has been added to the list
		assertEquals(sizeBefore + 1, ((Names) emptyNames).getSize());
		assertEquals(true, emptyNames.contains("TestName"));
	}
	
	/**Purpose: adds a WeightedName with an invalid name to an existing list
	* Expected Outcome: weightedName is not added to list  */
	public void testAddInvalidNameValidList(){
		//size of list before adding
		int sizeBefore = ((Names) validMultFilesNames).getSize();
		
		//add name to list
		validMultFilesNames.add(null, 20);
		
		//check that the invalid WeightedName has not been added to the list and that IllegalArgumetnException has been thrown
		assertEquals(sizeBefore, ((Names) validMultFilesNames).getSize());
		assertEquals(false, validMultFilesNames.contains(null));
	}
	
	/**Purpose: adds a WeightedName with an invalid name to an empty list
	 * Expected Outcome: weightedName is added to list, size increments, WeightedName now contained in list */
	public void testAddInvalidNameEmptyList(){
		//size of list before adding
		int sizeBefore = ((Names) emptyNames).getSize();
		
		//add name to list
		emptyNames.add("", 20);
		
		//check that the invalid WeightedName has not been added to the list and that IllegalArgumetnException has been thrown
		assertEquals(sizeBefore, ((Names) emptyNames).getSize());
		assertEquals(false, emptyNames.contains(""));
	}
	
	/** Purpose: adds a WeightedName with an invalid weight to an existing list
	 * Expected Outcome: weightedName is not added to the list, list size remains the same */
	public void testAddInvalidWeightValidList(){
		//size of list before adding
		int sizeBefore = ((Names) validMultFilesNames).getSize();
		
		//add name to list
		validMultFilesNames.add("Name", Double.NaN);
		
		//check that the invalid WeightedName has not been added to the list and that IllegalArgumetnException has been thrown
		assertEquals(sizeBefore, ((Names) validMultFilesNames).getSize());
		assertEquals(false, validMultFilesNames.contains("Name"));
	}
	
	/**Purpose: adds a WeightedName with an invalid weight to an empty list
	 * Expected Outcome: weightedName is not added to the list, list size remains the same */
	public void testAddInvalidWeightEmptyList(){
		//size of list before adding
		int sizeBefore = ((Names) emptyNames).getSize();
		
		//add name to list
		emptyNames.add("Name", Double.NaN);
		
		//check that the invalid WeightedName has not been added to the list and that IllegalArgumetnException has been thrown
		assertEquals(sizeBefore, ((Names) emptyNames).getSize());
		assertEquals(false, emptyNames.contains("Name"));
	}
	
	/**Purpose: adds an invalid WeightedName to an existing list
	 * Expected Outcome: weightedName is not added to list, size remains the same */
	public void testAddInvalidWeightedNameValidList(){
		//size of list before adding
		int sizeBefore = ((Names) validMultFilesNames).getSize();
		
		//add name to list
		validMultFilesNames.add(null, Double.NaN);
		
		//check that the invalid WeightedName has not been added to the list and that IllegalArgumetnException has been thrown
		assertEquals(sizeBefore, ((Names) validMultFilesNames).getSize());
		assertEquals(false, validMultFilesNames.contains(null));
	}
	
	/**Purpose: adds an invalid WeightedName to an empty list
	 * Expected Outcome: weightedName is not added to list, size remains the same */
	public void testAddInvalidWeightedNameEmptyList(){
		//size of list before adding
		int sizeBefore = ((Names) emptyNames).getSize();
		
		//add name to list
		emptyNames.add("   ", Double.NaN);
		
		//check that the invalid WeightedName has not been added to the list and that IllegalArgumetnException has been thrown
		assertEquals(sizeBefore, ((Names) emptyNames).getSize());
		assertEquals(false, emptyNames.contains("   "));
	}
		
	/**Purpose: adds a WeightedName with an already existing name to a valid list
	 * Expected Outcome: size remains the same, weightedName's weight increments */
	public void testAddExistingWeightedNameValidList(){
		//size of list before adding
		int sizeBefore = ((Names) validMultFilesNames).getSize();
		//weight of name before adding the same name again
		double weightBefore = validMultFilesNames.getWeight("Mary");
		
		//add name to list
		validMultFilesNames.add("Mary", 100);
		
		//check that the already existing WeightedName was not added and its weight was just modified
		assertEquals(sizeBefore, ((Names) validMultFilesNames).getSize());
		assertEquals(weightBefore + 100, validMultFilesNames.getWeight("Mary"));
	}
	
	/**Purpose: adds a new WeightedName with a negative weight
	 * Expected Outcome: item should not be added to list */
	public void testAddWeightedNameNegativeNum(){
		//size of list before adding
		int sizeBefore = ((Names) validMultFilesNames).getSize();
		
		//add name to list
		validMultFilesNames.add("NewName", -4);
		
		//check that the already existing WeightedName was not added and its weight was just modified
		assertEquals(sizeBefore, ((Names) validMultFilesNames).getSize());
		assertEquals(false, validMultFilesNames.contains("NewName"));
	}
	
	/**Purpose: adds a WeightedName with an existing name with a negative weight
	 * Expected Outcome: weightedName's weight should not be updated */
	public void testAddExistingWeightedNameNegativeNum(){
		//size of list before adding
		int sizeBefore = ((Names) validMultFilesNames).getSize();
		//weight of name before adding the same name again
		double weightBefore = validMultFilesNames.getWeight("Mary");
		
		//add name to list
		validMultFilesNames.add("Mary", -4);
		
		//check that the already existing WeightedName was not added and its weight was just modified
		assertEquals(sizeBefore, ((Names) validMultFilesNames).getSize());
		assertEquals(weightBefore, validMultFilesNames.getWeight("Mary"));
	}
	
	/**Purpose: adds a WeightedName with an existing name (capitalized differently)
	 * Expected Outcome: weightedName's weight should be updated */
	public void testAddExistingWeightedNameSpelledDifferently(){
		//size of list before adding
		int sizeBefore = ((Names) validMultFilesNames).getSize();
		//weight of name before adding the same name again
		double weightBefore = validMultFilesNames.getWeight("Mary");
		
		//add name to list
		validMultFilesNames.add("Mary", 5);
		validMultFilesNames.add("mary", 5);
		validMultFilesNames.add("mArY", 5);
		validMultFilesNames.add("mARy", 5);
		
		//check that the already existing WeightedName was not added and its weight was just modified
		assertEquals(sizeBefore, ((Names) validMultFilesNames).getSize());
		assertEquals(weightBefore + 20, validMultFilesNames.getWeight("Mary"));
	}
	/* ***************END TESTS FOR add METHOD********************** */
	
	
	/* ***************BEGIN TESTS FOR getWeight METHOD********************** */
	/**Purpose: gets the weight from an existing weightedName
	 * Expected Outcome: correct weight is returned*/
	public void testGetWeightExistingName(){
		//weight of name before adding the same name again
		double weight = 7175;
		
		//check that the weight is correct
		assertEquals(weight, validMultFilesNames.getWeight("Mary"));
	}
	
	/**Purpose: gets updated weight of an existing weightedName
	 * Expected Outcome: returns increased weight*/
	public void testGetWeightUpdateExistingName(){
		//weight of name before adding the same name again
		double weight =  validMultFilesNames.getWeight("Mary");
		
		validMultFilesNames.add("Mary", 10);
		
		//check that the first weight is correct
		assertEquals(weight + 10, validMultFilesNames.getWeight("Mary"));
		
	}
	
	/**Purpose: gets updated weight of an item added to an empty list twice
	 * Expected Outcome: gets correct incremented weight both times the weightedName is added*/
	public void testGetWeightChangeEmptyList(){
		//weights to be used for adding name
		double weight1 = 100;
		double weight2 = 50;
		
		emptyNames.add("Bob", weight1);
		//check that the weight is correct
		assertEquals(weight1, emptyNames.getWeight("Bob"));
		emptyNames.add("Bob", weight2);
		
		//check that the weight is correct
		assertEquals(weight1 + weight2, emptyNames.getWeight("Bob"));
	}
		
	/**Purpose: gets the weight from a non existing weightedName
	 * Expected Outcome: gets invalid weight (double.nan)*/
	public void testGetWeightNonExistingName(){
		//weight of name before adding the same name again
		double weight = Double.NaN;
		
		//check that it did returned Double.NaN instead since the name does not exist
		assertEquals(weight, validMultFilesNames.getWeight("invalid name"));
	}
	
	/**Purpose: gets the weight from an existing weightedName (capitalized incorrectly)
	 * Expected Outcome: correct weight is returned, capitalization does not matter*/
	public void testGetWeightExistingNameCapitalizedDif(){
		//weight of name before adding the same name again
		double weight = 7175;
		
		//check that the weight is correct and that each capitalization works
		assertEquals(weight, validMultFilesNames.getWeight("mary"));
		assertEquals(weight, validMultFilesNames.getWeight("mAry"));
		assertEquals(weight, validMultFilesNames.getWeight("MARY"));
	}
	/* ***************End TESTS FOR getWeight METHOD********************** */
	
	
	/* ***************BEGIN TESTS FOR contains METHOD********************** */
	/**Purpose: checks if an existing WeightedName is in the list
	 * Expected Outcome: returns true*/
	public void testContainsExistingName(){
		
		//check that the existing name is in the list
		assertEquals(true, validMultFilesNames.contains("Mary"));
	}
	
	/**Purpose: checks that a removed WeightedName is no longer in the list
	 * Expected Outcome: returns false*/
	public void testNotContainRemovedWeightedName(){
		//remove weighted name
		((Names) validMultFilesNames).remove("Mary");
		
		//check that the removed name is not in the list
		assertEquals(false, validMultFilesNames.contains("Mary"));
	}
	
	/**Purpose: checks that a nonExisting name is not in the list
	 * Expected Outcome: returns false*/
	public void testNotContainNonExistingName(){
		
		//check that the nonExisting names are not in the list
		assertEquals(false, emptyNames.contains("Mary"));
	}
	
	/**Purpose: checks that null is not in the list
	 * Expected Outcome: returns false*/
	public void testContainsNull(){
		
		//check that null is not in the list
		assertEquals(false, validMultFilesNames.contains(null));
		assertEquals(false, emptyNames.contains(null));
	}	
	
	
	/**Purpose: checks that nothing is not in the list
	 * Expected Outcome: returns false*/
	public void testContainsNothing(){
		
		//check that nothing is not in the list
		assertEquals(false, validMultFilesNames.contains("  "));
		assertEquals(false, emptyNames.contains(""));
	}	
	
	/**Purpose: checks if an existing WeightedName is in the list, using dif capitalizations
	 * Expected Outcome: returns true despite capitalization b/c chars in string are same*/
	public void testContainsExistingNameDifCapital(){
		
		//check that the existing name is in the list
		assertEquals(true, validMultFilesNames.contains("Mary"));
		assertEquals(true, validMultFilesNames.contains("Mary"));
		assertEquals(true, validMultFilesNames.contains("Mary"));
		assertEquals(true, validMultFilesNames.contains("Mary"));
	}
	/* ***************END TESTS FOR contains METHOD********************** */
	
	
	/* ***************BEGIN TESTS FOR getTopNames METHOD********************** */
	/**Purpose: get top names where k < set size
	 * Expected Outcome: returns an array with k top names*/
	public void testTopNamesKLessSetSize(){
		//get top names
		int k = 5;
		String[] topNames = validMultFilesNames.getTopNames(k);
		
		//check that correct top names were returned
		assertEquals(topNames.length, k);
		assertEquals("Mary", topNames[0]);
		assertEquals("Anna", topNames[1]);
		assertEquals("Emma", topNames[2]);
		assertEquals("Elizabeth", topNames[3]);
		assertEquals("Minnie", topNames[4]);
	}	
	
	/**Purpose: get top names where k > set size valid set
	 * Expected Outcome: returns an array of all of the names in the list */
	public void testTopNamesKGreaterSetSizeValidSet(){
		//get top names
		String[] topNames = validMultFilesNames.getTopNames(200000);
		
		//check that correct top names were returned
		assertEquals(topNames.length , ((Names) validMultFilesNames).getSize());
		assertEquals("Mary", topNames[0]);
		assertEquals("Anna", topNames[1]);
		assertEquals("Emma", topNames[2]);
		assertEquals("Elizabeth", topNames[3]);
		assertEquals("Minnie", topNames[4]);
		assertEquals("Margaret", topNames[5]);
		assertEquals("Ida", topNames[6]);
		assertEquals("Alice", topNames[7]);
		assertEquals("Julia", topNames[8]);
		assertEquals("Rose", topNames[9]);
		assertEquals("Pedro", topNames[10]);
		assertEquals("Joe", topNames[11]);
		assertEquals("Norris", topNames[12]);
		assertEquals("Rollie", topNames[13]);
		assertEquals("Nat", topNames[14]);
		assertEquals("Newell", topNames[15]);
		assertEquals("Sandy", topNames[16]);
	}	
	
	/**Purpose: get top names where k > set size empty set
	 * Expected Outcome: returns an empty array*/
	public void testTopNamesKGreaterSetSizeNullSet(){
		//get top names
		String[] topNames = emptyNames.getTopNames(200000);
		
		//check that correct top names were returned
		assertEquals(topNames.length , 0);
	}
	
	/**Purpose: get top names where k = 0 valid set
	 * Expected Outcome: returns an empty array*/
	public void testTopNamesKZeroValidSet(){
		//get top names
		String[] topNames = validMultFilesNames.getTopNames(0);
		
		//check that correct top names were returned
		assertEquals(topNames.length , 0);
	}
	
	/**Purpose: get top names where k = 0 empty set
	 * Expected Outcome: returns an empty array*/
	public void testTopNamesKZeroInvalidSet(){
		//get top names
		String[] topNames = emptyNames.getTopNames(0);
		
		//check that correct top names were returned
		assertEquals(topNames.length , 0);
	}
	
	/**Purpose: get top names where k < 0 valid set
	 * Expected Outcome: returns an empty array*/
	public void testTopNamesKLessZeroValidSet(){
		//get top names
		String[] topNames = validMultFilesNames.getTopNames(-3);
		
		//check that correct top names were returned
		assertEquals(topNames.length , 0);
	}
	
	/**Purpose: get top names where k < 0 empty set
	 * Expected Outcome: returns an empty array*/
	public void testTopNamesKLessZeroinvalidSet(){
		//get top names
		String[] topNames = emptyNames.getTopNames(-1);
		
		//check that correct top names were returned
		assertEquals(topNames.length , 0);
	}
	/* ***************END TESTS FOR getTopNames METHOD********************** */
	
	
	/* ***************BEGIN TESTS FOR getTopNamesWithMatchingPrefix METHOD********************** */
	/**Purpose: get top names by prefix where k < set size and prefix is null
	 * Expected Outcome: return an array of the top k names*/
	public void testPrefixTopNamesKLessSetSizePrefixNull(){
		//get top names
		int k = 5;
		String[] topNames = validMultFilesNames.getTopNamesWithMatchingPrefix(null, k);
		
		//check that correct top names were returned
		assertEquals(topNames.length, k);
		assertEquals("Mary", topNames[0]);
		assertEquals("Anna", topNames[1]);
		assertEquals("Emma", topNames[2]);
		assertEquals("Elizabeth", topNames[3]);
		assertEquals("Minnie", topNames[4]);
	}	
	
	/**Purpose: get top names by prefix where k < set size, where the prefix is valid and exists in the list
	 * Expected Outcome: returns an array of k elements that are filled with names <= k that contain the prefix*/
	public void testPrefixTopNamesKLessSetSizePrefixValid(){
		//get top names
		int k = 5;
		String[] topNames = validMultFilesNames.getTopNamesWithMatchingPrefix("Ma", k);
		
		//check that correct top names were returned
		assertEquals(topNames.length, 2);
		assertEquals("Mary", topNames[0]);
		assertEquals("Margaret", topNames[1]);
	}
	/**Purpose: get top names by prefix where k < set size, prefix valid and not existing in the list
	 * Expected Outcome: returns an array of k elements where all of the elements are null */
	public void testPrefixTopNamesKLessSetSizePrefixNonExistant(){
		//get top names
		int k = 5;
		String[] topNames = validMultFilesNames.getTopNamesWithMatchingPrefix("fasdf", k);
		
		//check that correct top names were returned
		assertEquals(topNames.length, 0);
	}
	
	/**Purpose: get top names by prefix where k < set size, prefix is null
	 * Expected Outcome: returns an array of all of the elements in the list (from greatest to least weight)*/
	public void testPrefixTopNamesKGreatSetSizePrefixNull(){
		//get top names
		int k = 10000;
		String[] topNames = validMultFilesNames.getTopNamesWithMatchingPrefix(null, k);
		
		//check that correct top names were returned
		assertEquals(topNames.length , ((Names) validMultFilesNames).getSize());
		assertEquals("Mary", topNames[0]);
		assertEquals("Anna", topNames[1]);
		assertEquals("Emma", topNames[2]);
		assertEquals("Elizabeth", topNames[3]);
		assertEquals("Minnie", topNames[4]);
		assertEquals("Margaret", topNames[5]);
		assertEquals("Ida", topNames[6]);
		assertEquals("Alice", topNames[7]);
		assertEquals("Julia", topNames[8]);
		assertEquals("Rose", topNames[9]);
		assertEquals("Pedro", topNames[10]);
		assertEquals("Joe", topNames[11]);
		assertEquals("Norris", topNames[12]);
		assertEquals("Rollie", topNames[13]);
		assertEquals("Nat", topNames[14]);
		assertEquals("Newell", topNames[15]);
		assertEquals("Sandy", topNames[16]);
	}	
	
	/**Purpose: get top names by prefix where k > set size, prefix is valid and exists in the list
	 * Expected Outcome: returns an array size = number of names in the list, with names <= k in the array*/
	public void testPrefixTopNamesKGreatSetSizePrefixValid(){
		//get top names
		int k = 10000;
		String[] topNames = validMultFilesNames.getTopNamesWithMatchingPrefix("E", k);
		
		//check that correct top names were returned
		assertEquals(topNames.length , 2);
		assertEquals("Emma", topNames[0]);
		assertEquals("Elizabeth", topNames[1]);
	}
	/**Purpose: get top names by prefix where k > set size, prefix valid and not existing
	 * Expected Outcome: returns an array where size = list size where all of the elements are null*/
	public void testPrefixTopNamesKGreatSetSizePrefixNonExistant(){
		//get top names
		int k = 10000;
		String[] topNames = validMultFilesNames.getTopNamesWithMatchingPrefix("fasdf", k);
		
		//check that correct top names were returned
		assertEquals(topNames.length, 0);
	}
	
	/**Purpose: get top names by prefix where k = 0, prefix valid 
	 * Expected Outcome: returns an array with no elements*/
	public void testPrefixTopNamesKZeroPrefixValid(){
		//get top names
		String[] topNames = validMultFilesNames.getTopNamesWithMatchingPrefix("A", 0);
		
		//check that correct top names were returned
		assertEquals(topNames.length , 0);
	}
	
	/**Purpose: get top names by prefix where k = 0, prefix invalid 
	 * Expected Outcome: returns an array with no elements*/
	public void testPrefixTopNamesKZeroPrefixInValid(){
		//get top names
		String[] topNames = validMultFilesNames.getTopNamesWithMatchingPrefix("", 0);
		
		//check that correct top names were returned
		assertEquals(topNames.length , 0);
	}
	
	/**Purpose: get top names by prefix where k < 0, prefix valid
	 * Expected Outcome: returns an array with no elements */
	public void testPrefixTopNamesKLessZeroPrefixValid(){
		//get top names
		String[] topNames = validMultFilesNames.getTopNamesWithMatchingPrefix("A", -4);
		
		//check that correct top names were returned
		assertEquals(topNames.length , 0);
	}
	
	/**Purpose: get top names by prefix where k < 0, prefix invalid 
	 * Expected Outcome: returns an array with no elements*/
	public void testPrefixTopNamesKLessZeroPrefixInValid(){
		//get top names
		String[] topNames = validMultFilesNames.getTopNamesWithMatchingPrefix("", -3);
		
		//check that correct top names were returned
		assertEquals(topNames.length , 0);
	}
	
	/**Purpose: get top names by prefix where k > size of empty set, prefix valid
	 * Expected Outcome: returns an array with no elements */
	public void testPrefixTopNamesZeroPrefixValidinvalidSet(){
		//get top names
		String[] topNames = emptyNames.getTopNamesWithMatchingPrefix("A", 4);
		
		//check that correct top names were returned
		assertEquals(topNames.length , 0);
	}
	
	/**Purpose: get top names by prefix from empty set, prefix invalid 
	 * Expected Outcome: returns an array with no elements*/
	public void testPrefixTopNamesPrefixInValidEmptySet(){
		//get top names
		String[] topNames = emptyNames.getTopNamesWithMatchingPrefix("", 5);
		
		//check that correct top names were returned
		assertEquals(topNames.length , 0);
	}
	
	/**Purpose: get top names by prefix from empty set, prefix valid 
	 * Expected Outcome: returns an array with no elements*/
	public void testPrefixTopNamesZeroPrefixValidEmptySet(){
		//get top names
		String[] topNames = emptyNames.getTopNamesWithMatchingPrefix("A", 4);
		
		//check that correct top names were returned
		assertEquals(topNames.length , 0);
	}
	
	/**Purpose: get top names by prefix where k < set size, where the prefix is valid (although capitalized dif then items in list) and exists in the list
	 * Expected Outcome: returns an array of k elements that are filled with names <= k that contain the prefix*/
	public void testPrefixTopNamesKLessSetSizePrefixValidDifCapitalization(){
		//get top names
		int k = 5;
		String[] topNames = validMultFilesNames.getTopNamesWithMatchingPrefix("mA", k);
		
		//check that correct top names were returned
		assertEquals(topNames.length, 2);
		assertEquals("Mary", topNames[0]);
		assertEquals("Margaret", topNames[1]);
	}
	/* ***************END TESTS FOR getTopNamesWithMatchingPrefix METHOD********************** */
	
	
	/* ***************BEGIN TESTS FOR SPECIAL FILE CASES********************** */
	/**Purpose: try to read names from a file that does not have read access 
	 * Expected Outcome: throws IOException*/
	public void testNoReadAccessZipFile(){
		SetOfWeightedNames names;
		boolean isErrorThrown = false;
		
		try {
			names = NamesFactory.createNamesVersion1(".res/test 2.zip");
		} catch (IOException e) {
			isErrorThrown = true;
		}
		//check that IOException was thrown
		assertEquals(true, isErrorThrown);
	}
	
	/**Purpose: try to read names from a null file name
	 * Expected Outcome: no values are read, list is empty*/
	public void testNullFileName(){
		SetOfWeightedNames names = null;
		boolean isErrorThrown = false;
		
		try {
			names = NamesFactory.createNamesVersion1(null);
		} catch (IOException e) {
			isErrorThrown = true;
		}
		
		//check that file was not read
		assertEquals(0, ((Names) names).getSize());
		//check that error was not thrown
		assertEquals(false, isErrorThrown);
	}
	
	/**Purpose: try to read names from an invalid file name
	 * Expected Outcome: no values are read, list is empty*/
	public void testInvalidFileName(){
		SetOfWeightedNames names = null;
		boolean isErrorThrown = false;
		
		try {
			names = NamesFactory.createNamesVersion1("invalid file name");
		} catch (IOException e) {
			isErrorThrown = true;
		}
		
		//check that file was not read
		assertEquals(0, ((Names) names).getSize());
		//check that error was not thrown
		assertEquals(false, isErrorThrown);
	}
	
	/**Purpose: try to read names from an empty zip file
	 * Expected Outcome: no values are read, list is empty*/
	public void testEmptyZipFile(){
		SetOfWeightedNames names = null;
		boolean isErrorThrown = false;
		
		try {
			names = NamesFactory.createNamesVersion1("./res/empty.zip");
		} catch (IOException e) {
			isErrorThrown = true;
		}
		
		//check that file read no data
		assertEquals(0, ((Names) names).getSize());
		//check that error was not thrown
		assertEquals(false, isErrorThrown);
	}
	
	/**Purpose: try to read names from a zip file that contains invalid files (pdf, jar, exe)
	 * Expected Outcome: no values are read, list is empty*/
	public void testZipWithInvalidContents(){
		SetOfWeightedNames names = null;
		boolean isErrorThrown = false;
		
		try {
			names = NamesFactory.createNamesVersion1("./res/invalidFilesInside.zip");
		} catch (IOException e) {
			isErrorThrown = true;
		}
		
		//check that files were not read
		assertEquals(0, ((Names) names).getSize());
		//check that error was not thrown
		assertEquals(false, isErrorThrown);
	}
		
	/**Purpose: try to read names from a zip file that contains an empty text file
	 * Expected Outcome: reads empty text file, stores nothing, list is empty*/
	public void testZipWithEmptyTextFile(){
		SetOfWeightedNames names = null;
		boolean isErrorThrown = false;
		
		try {
			names = NamesFactory.createNamesVersion1("./res/singleEmptyFileInside.zip");
		} catch (IOException e) {
			isErrorThrown = true;
		}
		
		//check that file read no data
		assertEquals(0, ((Names) names).getSize());
		//check that error was not thrown
		assertEquals(false, isErrorThrown);
	}
	
	/**Purpose: try to read names from a zip file that contains a text file with one row
	 * Expected Outcome: reads single value, list size = 1*/
	public void testZipWithSingleRow(){
		SetOfWeightedNames names = null;
		boolean isErrorThrown = false;
		
		try {
			names = NamesFactory.createNamesVersion1("./res/validFileOneRow.zip");
		} catch (IOException e) {
			isErrorThrown = true;
		}
		
		//check that file data was read
		assertEquals(1, ((Names) names).getSize());
		assertEquals(true, names.contains("Julia"));
		//check that error was not thrown
		assertEquals(false, isErrorThrown);
	}
	
	/**Purpose: try to read names from a zip file that contains a text file with multiple rows
	 * Expected Outcome: reads all of the values, list has values in it, first and last names in file are in the list*/
	public void testZipWithMultipleRows(){
		SetOfWeightedNames names = null;
		boolean isErrorThrown = false;
		
		try {
			names = NamesFactory.createNamesVersion1("./res/validFileMultipleRows.zip");
		} catch (IOException e) {
			isErrorThrown = true;
		}
		
		//check that file data was read
		assertEquals(true, ((Names) names).getSize() > 0);
		//check if contains first entry in text file
		assertEquals(true, names.contains("Isabella"));
		//check if contains last entry in text file
		assertEquals(true, names.contains("Zyvion"));
		//check that error was not thrown
		assertEquals(false, isErrorThrown);
	}
	
	/**Purpose: try to read names from a zip file that contains multiple text files with multiple rows
	 * Expected Outcome: reads all of the values, list has values in it, first and last names in each file are in the list*/
	public void testZipWithMultipleFilesMultipleRows(){
		SetOfWeightedNames names = null;
		boolean isErrorThrown = false;
		
		try {
			names = NamesFactory.createNamesVersion1("./res/multipleFilesMultipleRows.zip");
		} catch (IOException e) {
			isErrorThrown = true;
		}
		
		//check that file data was read
		assertEquals(true, ((Names) names).getSize() > 0);
		//check if contains first entry in each text file
		assertEquals(true, names.contains("Isabella"));
		assertEquals(true, names.contains("Mary"));
		assertEquals(true, names.contains("Helen"));
		//check if contains last entry in each text file
		assertEquals(true, names.contains("Zyvion"));
		assertEquals(true, names.contains("Zelma"));
		assertEquals(true, names.contains("Zina"));
		//check that error was not thrown
		assertEquals(false, isErrorThrown);
	}
	
	/**Purpose: try to read names from an empty text file
	 * Expected Outcome: reads file, stores nothing, list empty*/
	public void testTextEmpty(){
		SetOfWeightedNames names = null;
		boolean isErrorThrown = false;
		
		try {
			names = NamesFactory.createNamesVersion1("./res/empty.txt");
		} catch (IOException e) {
			isErrorThrown = true;
		}
		
		//check that no data was stored
		assertEquals(0, ((Names) names).getSize());
		//check that error was not thrown
		assertEquals(false, isErrorThrown);
	}
	
	/**Purpose: try to read names from an invalid file type (pdf)
	 * Expected Outcome: does not read file, stores nothing, list empty*/
	public void testInvalidFileType(){
		SetOfWeightedNames names = null;
		boolean isErrorThrown = false;
		
		try {
			names = NamesFactory.createNamesVersion1("./res/hw2.pdf");
		} catch (IOException e) {
			isErrorThrown = true;
		}
		
		//check that no data was stored
		assertEquals(0, ((Names) names).getSize());
		//check that error was not thrown
		assertEquals(false, isErrorThrown);
	}
	
	/**Purpose: try to read names from a text file with data
	 * Expected Outcome: reads file, stores data, list contains at least first and last entry in file*/
	public void testReadTextFile(){
		SetOfWeightedNames names = null;
		boolean isErrorThrown = false;
		
		try {
			names = NamesFactory.createNamesVersion1("./res/yob1881.txt");
		} catch (IOException e) {
			isErrorThrown = true;
		}
		
		//check that data was stored
		assertEquals(true, ((Names) names).getSize() > 0);
		//check that first and last entry are in list
		assertEquals(true, names.contains("Julia"));
		assertEquals(true, names.contains("Anna"));
		//check that error was not thrown
		assertEquals(false, isErrorThrown);
	}
	
	/**Purpose: try to read names from a text file with all invalid data
	 * Expected Outcome: reads file, stores no data, list contains nothing*/
	public void testReadTextFileInvalidData(){
		SetOfWeightedNames names = null;
		boolean isErrorThrown = false;
		
		try {
			names = NamesFactory.createNamesVersion1("./res/yob1882.txt");
		} catch (IOException e) {
			isErrorThrown = true;
		}
		
		//check that data was not stored
		assertEquals(0, ((Names) names).getSize());
		//check that error was not thrown
		assertEquals(false, isErrorThrown);
	}
	/* ***************END TESTS FOR SPECIAL FILE CASES********************** */
	

}
