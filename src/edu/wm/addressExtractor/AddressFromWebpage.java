/**
 * 
 */
package edu.wm.addressExtractor;

/**Class: AddressFromWebpage
 * Responsibilities: This interface describes methods to obtain address information from an HTML formatted file.
 * Collaborators: AddressFromYellowPages
 *
 * @author peterkemper
 *
 */
public interface AddressFromWebpage {
	/**
	 * Sets the filename. 
	 * The corresponding file is parsed and address data for a single address is extracted.  
	 * @param filename 
	 */
	void setFilename(String filename) ;
	/**
	 * Gets all addresses extracted from file. An individual entry need not be complete in all of its attributes, 
	 * but should have at least one attribute being different from null.
	 * @return <b> addresses</b> an array of addresses which can have length 0 if no addresses are available
	 */
	Address[] getAddresses() ;
	/**
	 * Gets an address with a matching phone number that was extracted from file. 
	 * An individual entry need not be complete in all of its attributes, 
	 * but should have at least one attribute being different from null.
	 * The comparison for phone numbers tolerates different formats. It recognizes that
	 * +1-123-123-1234 equals (123) 123 1234 equals 1231231234.
	 * @return <b> address</b> with matching phone number if available, null otherwise
	 */
	Address getAddress(String phone) ;
}
