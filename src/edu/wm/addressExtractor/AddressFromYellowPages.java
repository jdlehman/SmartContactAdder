package edu.wm.addressExtractor;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Class Name: AddressFromYellowPages
 * Responsibilities: manage a linked list of Address objects, add Address objects to list using yellowpages.com via file, url or number
 * Collaborators: AddressFromWebpage, DefaultImplementation
 * 
 * @author jonathanlehman
 *
 */

public class AddressFromYellowPages implements AddressFromWebpage{

	//private variables
	private LinkedList<Address> addressEntries;
	private int size;
	
	/** constructor create new LinkedList to hold AddressEntry objects */
	public AddressFromYellowPages(){
		addressEntries = new LinkedList<Address>();
	}
	
	/**
	 * Sets the filename. 
	 * The corresponding file is parsed and address data for a single address is extracted.  
	 * @param filename 
	 */
	public void setFilename(String filename) {
		boolean isValidAddress = true;
		AddressEntry newAE = null;
		
		//try to create a new AddressEntry using filename (URL would also work here)
		try {
			newAE = new AddressEntry(filename);
		} catch (Exception ex) {
			//if an exception was thrown, address is not valid
			isValidAddress = false;
		}
		
		//check if address was valid
		if(isValidAddress){
			//check if number is already in the linked list
			if(getAddress(newAE.getPhone()) == null){
				//add new Address Entry to linked list if it does not already exist
				addressEntries.add(newAE);
				
				//increment size of linked list
				size++;
			}			
		}
	}
	/**
	 * Gets all addresses extracted from file. An individual entry need not be complete in all of its attributes, 
	 * but should have at least one attribute being different from null.
	 * @return <b> addresses</b> an array of addresses which can have length 0 if no addresses are available
	 */
	public Address[] getAddresses() {
		Iterator<Address> iter = addressEntries.iterator();
		//array of addresses to return
		Address[] rtn = new Address[size];
		
		Address current;
		int ctr = 0;
			
		//check that there are Addresses in the list
		if(size > 0){
			//iterate through list of Addresses
			while(iter.hasNext()){
				current = iter.next();
				//add to array
				rtn[ctr] = current;
				
				//increment counter
				ctr++;
			}
		}
		return  rtn;
	}
	/**
	 * Gets an address with a matching phone number that was extracted from file. 
	 * An individual entry need not be complete in all of its attributes, 
	 * but should have at least one attribute being different from null.
	 * The comparison for phone numbers tolerates different formats. It recognizes that
	 * +1-123-123-1234 equals (123) 123 1234 equals 1231231234.
	 * @return <b> address</b> with matching phone number if available, null otherwise
	 */
	public Address getAddress(String phone) {
		Iterator<Address> iter = addressEntries.iterator();
		Address current;
		
		//put phone number into standard numeric format for comparison
		phone = phone.replace("(","").replace(")", "").replace("-", "").replace("+", "").replaceAll("\\b\\s{1,}\\b", "").trim();
		
		if(phone.length() > 10){
			//if user had +1 at beginning, will remove the 1 here (removes country code)
			phone = phone.substring(1);
		}
		
		//check that name is a valid value
		if(phone != null && !phone.trim().equals("")){
			
			//iterate through list of Addresses
			while(iter.hasNext()){
				current = iter.next();
				
				//return true if a Address with equal phone number is found
				if(current.getPhone().equals(phone)){
					return current;
				}
			}
		}
		
		return null;
	}
	
	/**  Sets the phone number of a new Address and gets corresponding data. 
	 * The corresponding url with the phone number is parsed and address data for a single address is extracted. 
	 * 
	 * @param phone the phone number of the AddressEntry
	 */
	public void setNumber(String phone){
		//edit phone number so it is only the number
		phone = phone.replace("(","").replace(")", "").replace("-", "").replace("+", "").replaceAll("\\b\\s{1,}\\b", "").trim();
		
		if(phone.length() > 10){
			//if user had +1 at beginning, will remove the 1 here (removes country code)
			phone = phone.substring(1);
		}
		
		//check that number is valid
		if(phone.length() >= 7){
			//set the yellowpages search by phone number url with the phone number
			String url = "http://www.yellowpages.com/phone/?phone_search_terms=" + phone;
			
			//add address to linked list, if data is found for the number
			setFilename(url);
		}
	}
	
	/** gets the size of the linked list
	 * @return size returns the size of the linked list
	 */
	public int getSize(){
		return size;
	}
}	
