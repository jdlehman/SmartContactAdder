package edu.wm.weightedNames;

import junit.framework.TestCase;

/** Tests methods in WeightedName that have not yet been tested
 * @author jdlehman
 *
 */
public class WeightedNameTest extends TestCase {

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	//****************************** BEGIN SET/GETWEIGHT TEST **************************
	/** Purpose: set/get the weight of the WeightedName
	 * 	Expected Outcome: sets the weight to the specified value and gets the correct value
	 */
	public void testSetWeight1(){
		WeightedName wn = new WeightedName("Bob", 5);
		
		double initialWeight = wn.getWeight();
		wn.setWeight(10);
		
		assertEquals(5.0, initialWeight);
		assertEquals(10.0, wn.getWeight());
	}
	//****************************** END SET/GETWEIGHT TEST **************************
	
	
	//****************************** BEGIN SET/GETNAME TEST **************************
	/** Purpose: set/get the name of the WeightedName
	 * 	Expected Outcome: sets the name to the specified value and gets the correct value
	 */
	public void testGetWeight1(){
		WeightedName wn = new WeightedName("Bob", 5);
				
		String initialName = wn.getName();
		wn.setName("Frank");
		
		assertEquals("Bob", initialName);
		assertEquals("Frank", wn.getName());
	}
	//****************************** END SET/GETNAME  TEST **************************
	
	
	//****************************** BEGIN TOSTRING TEST **************************
	/** Purpose: get the weight of the WeightedName
	 * 	Expected Outcome: get the weight to the specified value
	 */
	public void testToString1(){
		WeightedName wn = new WeightedName("Bob", 5);
				
		assertEquals("Name: Bob Weight: 5.0", wn.toString());
	}
	//****************************** END TOSTRING TEST **************************
}
