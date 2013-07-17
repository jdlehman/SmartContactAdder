/**
 * 
 */
package edu.wm.addressExtractor;

import junit.framework.TestCase;

/**
 * @author jonathanlehman
 *
 */
public class AddressEntryTest extends TestCase {

	private Address validAE;
	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		validAE = new AddressEntry("./data/YELLOWPAGES.COM_7572204357.html");
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		validAE = null;
	}

	
	//*************************** BEGIN CONSTRUCTOR TESTS ************************************
	/** Purpose:create an AddressEntry with all valid parameters
	 * Expected Outcome: AddressEntry created ,no exception is thrown
	 */
	public void testFirstContstructor1(){
		boolean isValidAddress = true;
		String errorMsg = "";
		
		//create new AddressEntry
		AddressEntry AE = null;
		try {
			AE = new AddressEntry("Joe", "Walmart", "Williamsburg", "Virginia", "United States", "123 Sesame Street", 543603, "(757)-456-234");
		} catch (Exception e) {
			isValidAddress = false;
			errorMsg = e.getLocalizedMessage();
		}
		
		//check that AddressEntry is valid
		assertEquals(true, isValidAddress);
		assertEquals("", errorMsg);
	}
	
	/** Purpose:create an AddressEntry with all valid parameters, except invalid phone number
	 * Expected Outcome: AddressEntry is not created , exception is thrown
	 */
	public void testFirstContstructor2(){
		boolean isValidAddress = true;
		String errorMsg = "";
		
		//create new AddressEntry
		AddressEntry AE = null;
		try {
			AE = new AddressEntry("Joe", "Walmart", "Williamsburg", "Virginia", "United States", "123 Sesame Street", 543603, "invalid number");
		} catch (Exception e) {
			isValidAddress = false;
			errorMsg = e.getLocalizedMessage();
		}
		
		//check that AddressEntry is valid
		assertEquals(false, isValidAddress);
		assertEquals("Phone number is invalid", errorMsg);
	}
	
	/** Purpose:create an AddressEntry with only one valid parameter (name) besides phone number
	 * Expected Outcome: AddressEntry created ,no exception is thrown
	 */
	public void testFirstContstructor3(){
		boolean isValidAddress = true;
		String errorMsg = "";
		
		//create new AddressEntry
		AddressEntry AE = null;
		try {
			AE = new AddressEntry("", "Walmart", null, null, null, "", -1, "+1 (757)-456-234");
		} catch (Exception e) {
			isValidAddress = false;
			errorMsg = e.getLocalizedMessage();
		}
		
		//check that AddressEntry is valid
		assertEquals(true, isValidAddress);
		assertEquals("", errorMsg);
	}
	
	/** Purpose:create an AddressEntry with only one valid parameter (zip code) besides phone number
	 * Expected Outcome: AddressEntry created ,no exception is thrown
	 */
	public void testFirstContstructor4(){
		boolean isValidAddress = true;
		String errorMsg = "";
		
		//create new AddressEntry
		AddressEntry AE = null;
		try {
			AE = new AddressEntry("", null, null, null, null, "", 123456, "+1 (757)-456-234");
		} catch (Exception e) {
			isValidAddress = false;
			errorMsg = e.getLocalizedMessage();
		}
		
		//check that AddressEntry is valid
		assertEquals(true, isValidAddress);
		assertEquals("", errorMsg);
	}
	
	/** Purpose:create an AddressEntry with no valid parameters besides phone number
	 * Expected Outcome: AddressEntry not created  exception is thrown
	 */
	public void testFirstContstructor5(){
		boolean isValidAddress = true;
		String errorMsg = "";
		
		//create new AddressEntry
		AddressEntry AE = null;
		try {
			AE = new AddressEntry("", "", null, null, null, "", -1, "+1 (757)-456-234");
		} catch (Exception e) {
			isValidAddress = false;
			errorMsg = e.getLocalizedMessage();
		}
		
		//check that AddressEntry is valid
		assertEquals(false, isValidAddress);
		assertEquals("Phone number is invalid, or all other data values are invalid", errorMsg);
	}
	
	/** Purpose:create an AddressEntry with all invalid parameters
	 * Expected Outcome: AddressEntry is not created , exception is thrown
	 */
	public void testFirstContstructor6(){
		boolean isValidAddress = true;
		String errorMsg = "";
		
		//create new AddressEntry
		AddressEntry AE = null;
		try {
			AE = new AddressEntry(null, "", null, null, null, "", -1, "invalid number");
		} catch (Exception e) {
			isValidAddress = false;
			errorMsg = e.getLocalizedMessage();
		}
		
		//check that AddressEntry is valid
		assertEquals(false, isValidAddress);
		assertEquals("Phone number is invalid", errorMsg);
	}
	
	/** Purpose:create an AddressEntry with valid html file parameter (containing found data)
	 * Expected Outcome: AddressEntry is created , exception is not thrown
	 */
	public void testSecondContstructor1(){
		boolean isValidAddress = true;
		String errorMsg = "";
		
		//create new AddressEntry
		AddressEntry AE = null;
		try {
			AE = new AddressEntry("./data/YELLOWPAGES.COM_7572204357.html");
		} catch (Exception e) {
			isValidAddress = false;
			errorMsg = e.getLocalizedMessage();
		}
		
		//check that AddressEntry is valid
		assertEquals(true, isValidAddress);
		assertEquals("", errorMsg);
	}
	
	/** Purpose:create an AddressEntry with valid html file parameter (containing  no found data)
	 * Expected Outcome: AddressEntry is not created , exception is thrown
	 */
	public void testSecondContstructor2(){
		boolean isValidAddress = true;
		String errorMsg = "";
		
		//create new AddressEntry
		AddressEntry AE = null;
		try {
			AE = new AddressEntry("./data/YELLOWPAGES.COM_7572204358.html");
		} catch (Exception e) {
			isValidAddress = false;
			errorMsg = e.getLocalizedMessage();
		}
		
		//check that AddressEntry is valid
		assertEquals(false, isValidAddress);
		assertEquals("Phone number is invalid, or all other data values are invalid", errorMsg);
	}
	
	/** Purpose:create an AddressEntry with invalid html file/ URL parameter
	 * Expected Outcome: AddressEntry is not created , exception is thrown
	 */
	public void testSecondContstructor3(){
		boolean isValidAddress = true;
		String errorMsg = "";
		
		//create new AddressEntry
		AddressEntry AE = null;
		try {
			AE = new AddressEntry("invalid file or URL");
		} catch (Exception e) {
			isValidAddress = false;
			errorMsg = e.getLocalizedMessage();
		}
		
		//check that AddressEntry is valid
		assertEquals(false, isValidAddress);
		assertEquals("Invalid file name or URL", errorMsg);
	}
	
	/** Purpose:create an AddressEntry with valid url parameter (containing found data)
	 * Expected Outcome: AddressEntry is created , exception is not thrown
	 */
	public void testSecondContstructor4(){
		boolean isValidAddress = true;
		String errorMsg = "";
		
		//create new AddressEntry
		AddressEntry AE = null;
		try {
			AE = new AddressEntry("http://www.yellowpages.com/phone/?phone_search_terms=757%29+564-7337+");
		} catch (Exception e) {
			isValidAddress = false;
			errorMsg = e.getLocalizedMessage();
		}
		
		//check that AddressEntry is valid
		assertEquals(true, isValidAddress);
		assertEquals("", errorMsg);
	}
	//*************************** END CONSTRUCTOR TESTS ************************************
	
	
	//*************************** BEGIN SETTER/GETTER TESTS (not needed, but doing to have full code coverage)************************************
	/** Purpose: test set/get for given name
	 * Expected Outcome: get will the correct given name that was set
	 */
	public void testSetterGetter1(){
		//set
		validAE.setGivenName("Patrick Bateman");
		//get
		String nameAfter = validAE.getGivenName();
		
		assertEquals("Patrick Bateman", nameAfter);
	}
	
	/** Purpose: test set/get for name
	 * Expected Outcome: get will the correct name that was set
	 */
	public void testSetterGetter2(){
		//get
		String nameBefore = validAE.getName();
		//set
		validAE.setName("Pierce and Pierce");
		//get
		String nameAfter = validAE.getName();
		
		assertEquals("Pierce and Pierce", nameAfter);
		assertEquals(false, nameBefore.equals(nameAfter));
	}
	
	/** Purpose: test set/get for city
	 * Expected Outcome: get will the correct city that was set
	 */
	public void testSetterGetter3(){
		//get
		String dataBefore = validAE.getCity();
		//set
		validAE.setCity("New York");
		//get
		String dataAfter = validAE.getCity();
		
		assertEquals("New York", dataAfter);
		assertEquals(false, dataBefore.equals(dataAfter));
	}
	
	/** Purpose: test set/get for country
	 * Expected Outcome: get will the correct country that was set
	 */
	public void testSetterGetter4(){
		//set
		validAE.setCountry("USA");
		//get
		String dataAfter = validAE.getCountry();
		
		assertEquals("USA", dataAfter);
	}
	
	/** Purpose: test set/get for phone number
	 * Expected Outcome: get will the correct phone number that was set
	 */
	public void testSetterGetter5(){
		//get
		String dataBefore = validAE.getPhone();
		//set
		validAE.setPhone("+1 (703)-225-5555");
		//get
		String dataAfter = validAE.getPhone();
		
		assertEquals("7032255555", dataAfter);
		assertEquals(false, dataBefore.equals(dataAfter));
	}
	
	/** Purpose: test set/get for zip code
	 * Expected Outcome: get will the correct zip code that was set
	 */
	public void testSetterGetter6(){
		//get
		int dataBefore = validAE.getZip();
		//set
		validAE.setZip(123456);
		//get
		int dataAfter = validAE.getZip();
		
		assertEquals(123456, dataAfter);
		assertEquals(false, dataBefore == dataAfter);
	}
	
	/** Purpose: test set/get for state
	 * Expected Outcome: get will the correct state that was set
	 */
	public void testSetterGetter7(){
		//get
		String dataBefore = validAE.getState();
		//set
		validAE.setState("New York");
		//get
		String dataAfter = validAE.getState();
		
		assertEquals("New York", dataAfter);
		assertEquals(false, dataBefore.equals(dataAfter));
	}
	
	/** Purpose: test set/get for address
	 * Expected Outcome: get will the correct address that was set
	 */
	public void testSetterGetter8(){
		//get
		String dataBefore = validAE.getStreetAddress();
		//set
		validAE.setStreetAddress("123 Main Street");
		//get
		String dataAfter = validAE.getStreetAddress();
		
		assertEquals("123 Main Street", dataAfter);
		assertEquals(false, dataBefore.equals(dataAfter));
	}
	//*************************** END SETTER/GETTER TESTS ************************************
	

	//*************************** BEGIN EQUALS TESTS ************************************
	/** Purpose: test equals for addresses (exact same object)
	 * Expected Outcome: AddressEntry objects are equal
	 */
	public void testEquals1(){
		
		assertEquals(true, validAE.equals(validAE));
	}
	
	/** Purpose: test equals for addresses, same phone number
	 * Expected Outcome: AddressEntry objects are equal
	 */
	public void testEquals2(){
		boolean isValidAddress = true;
		String errorMsg = "";
		
		//create new AddressEntry's with same phone number
		AddressEntry AE = null;
		AddressEntry AE2 = null;
		try {
			AE = new AddressEntry("Joe", "Walmart", "Williamsburg", "Virginia", "United States", "123 Sesame Street", 543603, "(757)-456-2345");
			AE2 = new AddressEntry("Bob", "Target", "Dallas", "Texas", "United States", "535 James Street", 402424, "+1 (757) -456-2345");
		} catch (Exception e) {
			isValidAddress = false;
			errorMsg = e.getLocalizedMessage();
		}
		
		//check that AddressEntry is valid
		//assertEquals(true, isValidAddress);
		assertEquals("", errorMsg);
		assertEquals(true, AE.equals(AE2));
	}
	
	/** Purpose: test equals for addresses, same data values except phone number
	 * Expected Outcome: AddressEntry objects are not equal
	 */
	public void testEquals3(){
		boolean isValidAddress = true;
		String errorMsg = "";
		
		//create new AddressEntry's with same phone number
		AddressEntry AE = null;
		AddressEntry AE2 = null;
		try {
			AE = new AddressEntry("Joe", "Walmart", "Williamsburg", "Virginia", "United States", "123 Sesame Street", 543603, "(757)-456-234");
			AE2 = new AddressEntry("Joe", "Walmart", "Williamsburg", "Virginia", "United States", "123 Sesame Street", 543603, "(757)-456-235");
		} catch (Exception e) {
			isValidAddress = false;
			errorMsg = e.getLocalizedMessage();
		}
		
		//check that AddressEntry is valid
		assertEquals(true, isValidAddress);
		assertEquals("", errorMsg);
		assertEquals(false, AE.equals(AE2));
	}
	//*************************** END EQUALS TESTS ************************************
	
	
	//*************************** BEGIN TOSTRING TESTS ************************************
	/** Purpose: get string of valid AddressEntry
	 * Expected Outcome: correct string of AddressEntry
	 */
	public void testToString1(){
		assertEquals("Name:Williamsburg Heating & Air Conditioning, Inc.\nCity:Toano\nState:VA\nAddress:8888 Richmond Rd W\nZip Code:23168\nPhone Number:7572204357\n", validAE.toString());
	}
	
	/** Purpose: get string for an null AddressEntry
	 * Expected Outcome: throws exception
	 */
	public void testToString2(){
		boolean isExceptionThrown = false;
		
		AddressEntry AE = null;
		try {
			AE = new AddressEntry(null, null, null, null, null, null, -1, null);
		} catch (Exception e) {
			isExceptionThrown = true;
		}
		
		assertEquals(true, isExceptionThrown);
	}
	//*************************** END TOSTRING TESTS ************************************
}
