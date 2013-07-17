package edu.wm.addressExtractor;

/**
 * Class Name: DefaultImplementation
 * Responsibilities: use AddressFromYellowPages to get data from yellowpages using phone numbers 
 * Collaborators: AddressFromYellowPages
 * 
 * @author jonathanlehman
 *
 */

public class DefaultImplementation {

	
	public static void main(String[] args){
	
		AddressFromYellowPages add = new AddressFromYellowPages();
		
		Address[] array;
		
		//add addresses
		add.setFilename("./data/YELLOWPAGES.COM_7572204357.html");
		add.setFilename("./data/YELLOWPAGES.COM_7572204358.html");
		add.setNumber("(757) -565- 2007");
		add.setNumber("7575652009");
		add.setNumber("7572298885");
		add.setFilename("http://www.yellowpages.com/phone/?phone_search_terms=757%29+564-7337+");
		
		//get array of Addresses from linked list
		array = add.getAddresses();
		
		System.out.println("The addresses in the list were:\n");
		//print addresses
		for(Address a : array){
			System.out.println(a);
		}
			
	}

}
