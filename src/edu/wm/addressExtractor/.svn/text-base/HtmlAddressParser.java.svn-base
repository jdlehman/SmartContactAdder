package edu.wm.addressExtractor;

import java.io.*;
import java.net.*;
import org.htmlcleaner.*;

/**
 * Class Name: HTtmlAddressParser
 * Responsibilities: clean html from htm file or url, extract data from cleaned html
 * Collaborators: AddressEntry
 * 
 * @author jonathanlehman
 *
 */

public class HtmlAddressParser {

	private HtmlCleaner cleaner;
	private TagNode data;
	
	/** constructor encapsulates HtmlCleaner*/
	public HtmlAddressParser(){
		cleaner = new HtmlCleaner();
	}
	
	/**cleans data in an html file
	 * 
	 * @param fileName of an html file to clean
	 * @throws IOException if file is not valid or not an HTML file
	 */
	public void cleanFile(String fileName) throws IOException{
		//check that file is a .html file
		if(fileName.length() > 5 && fileName.substring(fileName.length() - 5).equalsIgnoreCase(".html")){
			data = cleaner.clean(new File(fileName));
		}
		else{
			throw new IOException("Invalid file type.  Needs to be an html file.");
		}
		
	}
	
	/**cleans data from a url
	 * 
	 * @param url url of a page to clean
	 * @throws IOException if url is not valid
	 */
	public void cleanURL(String url) throws IOException{
		
		data = cleaner.clean(new URL(url));
	}
	
	/**get the givenName from the cleaned html
	 * 
	 * @return givenName return the givenName if it exists, otherwise null
	 */
	public String getGivenName(){
		//get all TagNodes with class=name
		TagNode value = data.findElementByAttValue("class", "name", true, false);
		
		if(value != null){
			return value.getText().toString().trim();
		}
		else{
			return null;
		}
	}
	
	/**get the name from the cleaned html
	 * 
	 * @return name return the name if it exists, otherwise null
	 */
	public String getName(){
		  
		//get all TagNodes with class=business-name fn org
		TagNode value = data.findElementByAttValue("class", "business-name fn org", true, false);
		
		if(value != null){
			return value.getText().toString().replace("amp;","").trim();
		}
		else{
			return null;
		}
	}
	
	/**get the address from the cleaned html
	 * 
	 * @return address return the address if it exists, otherwise null
	 */
	public String getStreetAddress(){
		  
		//get all TagNodes with class=street-address
		TagNode value = data.findElementByAttValue("class", "street-address", true, false);
		
		if(value != null){
			return value.getText().toString().replace(",", "").trim();
		}
		else{
			return null;
		}
	}
	
	/**get the city from the cleaned html
	 * 
	 * @return city return the city if it exists, otherwise null
	 */
	public String getCity(){
		  
		//get all TagNodes with class=locality
		TagNode value = data.findElementByAttValue("class", "locality", true, false);
		
		if(value != null){
			return value.getText().toString().replace(",", "").trim();
		}
		else{
			return null;
		}
	}
	
	/**get the state from the cleaned html
	 * 
	 * @return state return the state if it exists, otherwise null
	 */
	public String getState(){
		  
		//get all TagNodes with class=region
		TagNode value = data.findElementByAttValue("class", "region", true, false);
		
		if(value != null){
			return value.getText().toString().replace(",", "").trim();
		}
		else{
			return null;
		}
	}
	
	/**get the zip code from the cleaned html
	 * 
	 * @return zip code return the zip code if it exists, otherwise -1
	 */
	public int getZipCode(){
		//get all TagNodes with class=postal-code
		TagNode value = data.findElementByAttValue("class", "postal-code", true, false);
		
		if(value != null){
			return Integer.parseInt(value.getText().toString().replaceAll("\\b\\s{1,}\\b", "").replace("-",""));
		}
		else{
			return -1;
		}
	}
	
	/**get the country from the cleaned html
	 * 
	 * @return country return the country if it exists, otherwise null
	 */
	public String getCountry(){
		  
		//get all TagNodes with class=country
		TagNode value = data.findElementByAttValue("class", "country", true, false);
		
		if(value != null){
			return value.getText().toString().replace(",", "").trim();
		}
		else{
			return null;
		}
	}
	
	/**get the number from the cleaned html
	 * 
	 * @return number return the number if it exists, otherwise null
	 */
	public String getNumber(){
		  
		//get all TagNodes with class=business-phone phone
		TagNode value = data.findElementByAttValue("class", "business-phone phone", true, false);
		if(value != null){
			String number = value.getText().toString().replace("(","").replace(")", "").replace("-", "").replace("+", "").replaceAll("\\b\\s{1,}\\b", "").trim();
			if(number.length() > 10){
				//if user had +1 at beginning, will remove the 1 here (removes country code)
				number = number.substring(1);
			}
			return number;
		}
		else{
			return null;
		}
	}


}
