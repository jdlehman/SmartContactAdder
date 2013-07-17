package edu.wm.addressExtractor;

import java.io.IOException;

/**
 * Class Name: AddressEntry
 * Responsibilities: creates AddressEntry objects and manages the data within AddressEntry objects
 * Collaborators: Address, HtmlAddressParser
 * 
 * @author jonathanlehman
 *
 */

public class AddressEntry implements Address{

	//private variables
	private String givenName;
	private String name;
	private String city;
	private String state;
	private String country;
	private String address;
	private int zip;
	private String phone;
	
	/** constructor to create an AddressEntry with appropriate values as parameters
	 * @param givenName , name , city , state , country , address , zip , phone : the information to create an AddressEntry*/
	public AddressEntry(String givenName, String name, String city, String state, String country, String address, int zip, String phone) throws Exception{
		//set all private variables
		this.givenName = givenName;
		this.name = name;
		this.city = city;
		this.state = state;
		this.country = country;
		this.address = address;
		this.zip = zip;
		//sets phone number that is consistent for comparison (all numeric)
		this.phone = phone.replace("(","").replace(")", "").replace("-", "").replace("+", "").replaceAll("\\b\\s{1,}\\b", "").trim();
		
		if(this.phone.length() > 10){
			//if user had +1 at beginning, will remove the 1 here (removes country code)
			this.phone = this.phone.substring(1);
		}
		
		//final check to make sure number is numeric, if not set to null
		try{
			Long.parseLong(this.phone);
		}
		catch(Exception ex){
			this.phone = null;
			throw new Exception("Phone number is invalid");
		}
		
		//throw exception if number is null, or all other values are null
		if((this.phone == null || this.phone.equals("")) || ((givenName == null || givenName.equals("")) && (name == null || name.equals("")) && (city == null || city.equals("")) && (state == null || state.equals("")) && (country == null || country.equals("")) && (address == null || address.equals("")) && (zip == -1))){
			throw new Exception("Phone number is invalid, or all other data values are invalid");
		}
	}
	
	/** constructor to create an AddressEntry from values of a URL or file 
	 * @param urlOrFile the url or file name to extract the address data from*/
	public AddressEntry(String urlOrFile) throws Exception{
		HtmlAddressParser htmlParser = new HtmlAddressParser();
		boolean isURL = true;
		boolean isNeither = false;
		
		//try cleaning the URL
		try {
			htmlParser.cleanURL(urlOrFile);
		} catch (IOException e) {
			//if fails the string is not a URL, but a file
			isURL = false;
		}
		
		//check if string was a URL
		if(!isURL){
			//not a url so try cleaning the file
			try {
				htmlParser.cleanFile(urlOrFile);
			} catch (IOException e) {
				isNeither = true;
			}
		}
		
		//check if is valid URL or file
		if(!isNeither){
			//set private variables
			givenName = htmlParser.getGivenName();
			name = htmlParser.getName();
			city = htmlParser.getCity();
			state = htmlParser.getState();
			country = htmlParser.getCountry();
			address = htmlParser.getStreetAddress();
			zip = htmlParser.getZipCode();
			phone = htmlParser.getNumber();
		}
		else{
			throw new Exception("Invalid file name or URL");
		}
		
		//throw exception if number is null, or all other values are null
		if((phone == null || phone.equals("")) || ((givenName == null || givenName.equals("")) && (name == null || name.equals("")) && (city == null || city.equals("")) && (state == null || state.equals("")) && (country == null || country.equals("")) && (address == null || address.equals("")) && (zip == -1))){
			throw new Exception("Phone number is invalid, or all other data values are invalid");
		}
		
	}
	
	/**
	 * Sets the given name, for businesses this is usually an empty string
	 *
	 * @param <b>givenName</b> given name or first name
	 */
	public void setGivenName(String givenName){
		this.givenName = givenName;
	}

	/**
	 * Sets the name of the person or business
	 *
	 * @param <b>name</b> name of person or business
	 */
	public void setName(String name){
		this.name = name;
	}

	/**
	 * Sets the name of the city
	 *
	 * @param <b>city</b> city name
	 */
	public void setCity(String city){
		this.city = city;
	}
	
	/**
	 * Sets the state
	 *
	 * @param <b>state</b> state name
	 */
	public void setState(String state){
		this.state = state;
	}
	
	/**
	 * Sets the country code
	 *
	 * @param <b>country</b> country code
	 */
	public void setCountry(String country){
		this.country = country;
	}
	
	/**
	 * Sets the street address
	 *
	 * @param <b>address</b> street address
	 */
	public void setStreetAddress(String address){
		this.address = address;
	}
	
	/**
	 * Sets the zip code
	 *
	 * @param <b>zip</b> zip code
	 */
	public void setZip(int zip){
		this.zip = zip;
	}
	
	/**
	 * Sets the phone number. 
	 * The number is given as a string to allow for formats like +43-123-123-1234 for international.
	 * @param <b>phone</b> phone number
	 */
	public void setPhone(String phone){
		this.phone = phone.replace("(","").replace(")", "").replace("-", "").replace("+", "").replaceAll("\\b\\s{1,}\\b", "").trim();
		
		if(this.phone.length() > 10){
			//if user had +1 at beginning, will remove the 1 here (removes country code)
			this.phone = this.phone.substring(1);
		}
	}
	
	/**
	 * Gets the given name of a person or an empty string if left blank for a business address
	 *
	 * @return <b>givenName</b> given name or first name
	 */
	public String getGivenName(){
		return givenName;
	}

	/**
	 * Gets the name of the person or business
	 *
	 * @return <b>name</b> name of person or business
	 */
	public String getName(){
		return name;
	}

	/**
	 * Gets the name of the city
	 *
	 * @return <b>city</b> city name
	 */
	public String getCity(){
		return city;
	}
	
	/**
	 * Gets the state
	 *
	 * @return <b>state</b> state name
	 */
	public String getState(){
		return state;
	}
	
	/**
	 * Gets the country code
	 *
	 * @return <b>country</b> country code
	 */
	public String getCountry(){
		return country;
	}
	
	/**
	 * Gets the street address
	 *
	 * @return <b>address</b> street address
	 */
	public String getStreetAddress(){
		return address;
	}
	
	/**
	 * Gets the zip code
	 *
	 * @return <b>zip</b> zip code
	 */
	public int getZip() {
		return zip;
	}
	
	/**
	 * Gets the phone number. 
	 * The number is returned as a string to allow for formats like +43-123-123-1234 for international.
	 * @return <b>phone</b> phone number
	 */
	public String getPhone(){
		return phone;
	}
	
	/** return a string representing the AddressEntry
	@return rtn  a string representation of all non-null data in the AddressEntry
    */
    public String toString(){
    	StringBuilder rtn = new StringBuilder();
    	
    	if(givenName != null && !givenName.equals("")){
    		rtn.append("Given Name:" + givenName + "\n");
    	}
    	if(name != null && !name.equals("")){
    		rtn.append("Name:" + name +"\n");
    	}
    	if(city != null && !city.equals("")){
    		rtn.append("City:" + city + "\n");
    	}
    	if(state != null && !state.equals("")){
    		rtn.append("State:" + state + "\n");
    	}
    	if(country != null && !country.equals("")){
    		rtn.append("Country:" + country + "\n");
    	}
    	if(address != null && !address.equals("")){
    		rtn.append("Address:" + address + "\n");
    	}
    	if(zip != -1){
    		rtn.append("Zip Code:" + zip + "\n");
    	}
    	if(phone != null && !phone.equals("")){
    		rtn.append("Phone Number:" + phone + "\n");
    	}
    	if(rtn.toString().equals("")){
    		rtn.append("Address Entry contains no valid data");
    	}
    	
       	return rtn.toString();
    }

    /** determines if AddressEntry objects are equal based on phone number
	@return boolean true if AddressEntry objects are equal, false otherwise
    */
	public boolean equals(AddressEntry AE) {
		String phoneNum = AE.getPhone();
		
		//check if numbers are equal
		if(getPhone().equals(phoneNum)){
			return true;
		}
		else{
			return false;
		}
	}
}
