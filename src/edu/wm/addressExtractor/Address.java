/**
 * 
 */
package edu.wm.addressExtractor;

/** Class: Address
 * Responsibilities:
 * This interface is for a simple data container to encapsulate data for a single address of a person or a business.
 * Any implementing class may provide constructors with parameters to assign values to attributes as needed to avoid 
 * lengthy sequences of calls to setters.
 * Address attributes are: 
 * Given name, e.g. Tom (private) or "" (Business)
 * Name, e.g., Sample or SampleCompany (Business)
 * Street address, e.g. 123 Sample Street
 * City, e.g., Sampletown
 * State, e.g., VA
 * Zipcode, e.g., 12345
 * Country, e.g., USA
 * Phone, e.g., 1231231234 or (123) 123-1234 or +1-123-123-1234
 * 
 * Collaborators: AddressEntry, AddressFromYellowPages
 * 
 * @author peterkemper
 *
 */
public interface Address {
	/**
	 * Sets the given name, for businesses this is usually an empty string
	 *
	 * @param <b>given name</b> given name or first name
	 */
	public void setGivenName(String givenName) ;

	/**
	 * Sets the name of the person or business
	 *
	 * @param <b>name</b> name of person or business
	 */
	public void setName(String name) ;

	/**
	 * Sets the name of the city
	 *
	 * @param <b>city</b> city name
	 */
	public void setCity(String city) ;
	/**
	 * Sets the state
	 *
	 * @param <b>state</b> state name
	 */
	public void setState(String state) ;
	/**
	 * Sets the country code
	 *
	 * @param <b>country</b> country code
	 */
	public void setCountry(String country) ;
	/**
	 * Sets the street address
	 *
	 * @param <b>address</b> street address
	 */
	public void setStreetAddress(String address);
	/**
	 * Sets the zip code
	 *
	 * @param <b>zip</b> zip code
	 */
	public void setZip(int zip) ;
	/**
	 * Sets the phone number. 
	 * The number is given as a string to allow for formats like +43-123-123-1234 for international.
	 * @param <b>phone</b> phone number
	 */
	public void setPhone(String phone) ;
	/**
	 * Gets the given name of a person or an empty string if left blank for a business address
	 *
	 * @return <b>given name</b> given name or first name
	 */
	public String getGivenName() ;

	/**
	 * Gets the name of the person or business
	 *
	 * @return <b>name</b> name of person or business
	 */
	public String getName() ;

	/**
	 * Gets the name of the city
	 *
	 * @return <b>city</b> city name
	 */
	public String getCity() ;
	/**
	 * Gets the state
	 *
	 * @return <b>state</b> state name
	 */
	public String getState() ;
	/**
	 * Gets the country code
	 *
	 * @return <b>country</b> country code
	 */
	public String getCountry() ;
	/**
	 * Gets the street address
	 *
	 * @return <b>address</b> street address
	 */
	public String getStreetAddress();
	/**
	 * Gets the zip code
	 *
	 * @return <b>zip</b> zip code
	 */
	public int getZip() ;
	/**
	 * Gets the phone number. 
	 * The number is returned as a string to allow for formats like +43-123-123-1234 for international.
	 * @return <b>phone</b> phone number
	 */
	public String getPhone() ;
}
