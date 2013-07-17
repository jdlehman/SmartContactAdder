package edu.wm.addressExtractor;

import junit.framework.TestCase;

public class AddressFromYellowPagesTest extends TestCase {

	
	private AddressFromYellowPages validAdd;
	private AddressFromYellowPages multValidAdds;
	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		validAdd = new AddressFromYellowPages();
		validAdd.setFilename("./data/YELLOWPAGES.COM_7572204357.html");
		
		multValidAdds = new AddressFromYellowPages();
		multValidAdds.setFilename("./data/YELLOWPAGES.COM_7572204357.html");
		multValidAdds.setFilename("./data/YELLOWPAGES.COM_7575652007.html");
		multValidAdds.setNumber("17572298885");
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		validAdd = null;
		multValidAdds = null;
	}

	//******************************* BEGIN SETFILENAME TESTS **************************************
	/** Purpose: add address from valid fileName (with valid data) to linked list
	 * Expected Outcome: adds Address to linked list
	 */
	public void testSetFileName1(){
		AddressFromYellowPages add = new AddressFromYellowPages();
		
		add.setFilename("./data/YELLOWPAGES.COM_7572204357.html");
		
		assertEquals(1, add.getSize());
		assertEquals(true, add.getAddress("7572204357") != null);
	}
	
	/** Purpose: add address from valid fileName (with no data) to linked list
	 * Expected Outcome: does not add Address to linked list because Address does not have valid data
	 */
	public void testSetFileName2(){
		AddressFromYellowPages add = new AddressFromYellowPages();
		
		add.setFilename("./data/YELLOWPAGES.COM_7572204358.html");
		
		assertEquals(0, add.getSize());
		assertEquals(true, add.getAddress("7572204358") == null);
	}
	
	/** Purpose: add address from invalid fileName to linked list
	 * Expected Outcome: does not add Address to linked list 
	 */
	public void testSetFileName3(){
		AddressFromYellowPages add = new AddressFromYellowPages();
		
		add.setFilename("./data/YELLOWPAGES.COM_7572204360.html");
		
		assertEquals(0, add.getSize());
		assertEquals(true, add.getAddress("7572204358") == null);
	}
	
	/** Purpose: add address from valid url (with valid data) to linked list
	 * Expected Outcome: adds Address to linked list 
	 */
	public void testSetFileName4(){
		AddressFromYellowPages add = new AddressFromYellowPages();
		
		add.setFilename("http://www.yellowpages.com/phone/?phone_search_terms=757%29+564-7337+");
		
		assertEquals(1, add.getSize());
		assertEquals(true, add.getAddress("7575647337") != null);
	}
	
	/** Purpose: add address from valid url (with no valid data) to linked list
	 * Expected Outcome: adds Address to linked list 
	 */
	public void testSetFileName5(){
		AddressFromYellowPages add = new AddressFromYellowPages();
		
		add.setFilename("http://www.yellowpages.com/phone/?phone_search_terms=757%29+555-7337+");
		
		assertEquals(0, add.getSize());
		assertEquals(false, add.getAddress("7575557337") != null);
	}
	//******************************* END SETFILENAME TESTS **************************************
	
	
	//******************************* BEGIN GETADDRESS TESTS **************************************
	/** Purpose: get array of Addresses from empty linked list
	 * Expected Outcome: returns empty array
	 */
	public void testGetAddresses1(){
		AddressFromYellowPages add = new AddressFromYellowPages();
		
		Address[] a = add.getAddresses(); 
		        
		assertEquals(0, add.getSize());
		assertEquals(0, a.length);
	}
	
	/** Purpose: get array of Addresses from linked list with 1 Address
	 * Expected Outcome: returns array with one address
	 */
	public void testGetAddresses2(){
		
		Address[] a = validAdd.getAddresses(); 
		        
		assertEquals(1, validAdd.getSize());
		assertEquals(1, a.length);
		assertEquals(true, a[0] != null);
	}
	
	/** Purpose: get array of Addresses from linked list with multiple Addresses
	 * Expected Outcome: returns array with multiple addresses
	 */
	public void testGetAddresses3(){
		
		Address[] a = multValidAdds.getAddresses(); 
		        
		assertEquals(3, multValidAdds.getSize());
		assertEquals(3, a.length);
		assertEquals(true, a[0] != null);
		assertEquals(true, a[1] != null);
		assertEquals(true, a[2] != null);
	}
	//******************************* END GETADDRESSES TESTS **************************************
	
	
	//******************************* BEGIN GETADDRESS TESTS **************************************
	/** Purpose: find address of a number existing in the list
	 * Expected Outcome: returns Address of number
	 */
	public void testGetAddress1(){
		
		Address a = multValidAdds.getAddress("+1 (757) - 229-8885"); 
		
		assertEquals(true, a != null);
		assertEquals("7572298885", a.getPhone());
	}
	
	/** Purpose: find address of a number not existing in the list
	 * Expected Outcome: returns null
	 */
	public void testGetAddress2(){
		
		Address a = multValidAdds.getAddress("+1 (757) - 229-8888"); 
		
		assertEquals(true, a == null);
	}
	
	/** Purpose: find address of a invalid number
	 * Expected Outcome: returns null
	 */
	public void testGetAddress3(){
		
		Address a = multValidAdds.getAddress("invalid number"); 
		
		assertEquals(true, a == null);
	}
	//******************************* END GETADDRESS TESTS **************************************
	
	
	//******************************* BEGIN SETNUMBER TESTS **************************************
	/** Purpose: add an Address to the linked list with valid phone number 
	 * Expected Outcome: Address added to linked list
	 */
	public void testSetNumber1(){
		
		validAdd.setNumber("+1 (757) - 229-8885"); 
		
		assertEquals(2, validAdd.getSize());
		assertEquals(true, validAdd.getAddress("7572298885") != null);
	}
	
	/** Purpose: add an Address to the linked list with invalid phone number 
	 * Expected Outcome: Address added to linked list
	 */
	public void testSetNumber2(){
		
		validAdd.setNumber("invalid number"); 
		
		assertEquals(1, validAdd.getSize());
		assertEquals(true, validAdd.getAddress("invalid number") == null);
	}
	
	/** Purpose: add an Address to the linked list with valid phone number that is not associated with data
	 * Expected Outcome: Address added to linked list
	 */
	public void testSetNumber3(){
		
		validAdd.setNumber("7572341234"); 
		
		assertEquals(1, validAdd.getSize());
		assertEquals(true, validAdd.getAddress("7572341234") == null);
	}
	//******************************* END SETNUMBER TESTS **************************************
}
