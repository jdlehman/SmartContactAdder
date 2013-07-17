package edu.wm.weightedNames;

import java.io.*;
import java.util.*;
import java.util.zip.*;

/**
 * Class Name: ZipFileReader
 * Responsibilities: read entries in a zip file, read data in entries in a zip file and stores WeightedNames to WeightedNameTreeSet
 * Collaborators: Names, WeightedNameTreeSet, TreeSet, WeightedNameByName, WeightedName
 * 
 * @author jonathanlehman
 *
 */

public class ZipFileReader{
	
	//name of zip file to be read
	protected ZipFile zipFileName;
	//files within the zip file (so it can iterate through them later on)
	protected Enumeration entries;
	
	/** Constructor creates ZipFileReader that holds file name of the zip file and its entries */
	public ZipFileReader(String fileName) throws IOException{
		zipFileName = new ZipFile(fileName);
		entries = zipFileName.entries();
	}
	
	/**
     * Reads all of the entries in a zipFile 
     * 
     * @param set WeightedNameTreeSet to hold the WeightedNames from each entry
     * @throws IOException if reading the file fails for various reasons
     */
	public void readZipFile(Collection<WeightedName> coll) throws IOException{
		//TreeSet to hold the names that have been added, used to quickly determine if a WeightedName needs to be added
		//or the weight needs to be updated
		TreeSet<WeightedName> nameTree = new TreeSet<WeightedName>(new ComparatorByName());
		
		//iterate through each entry in the zip file
	      while (entries.hasMoreElements()) {
	    	  ZipEntry zEntry = (ZipEntry) entries.nextElement();
	        
	          long size = zEntry.getSize();
	          
	          //check if the entry has data within it
	          if (size > 0) {
	        	  
	        	  //only read text files
	        	  String entryName = zEntry.getName();
	        	  if(entryName.substring(entryName.length() - 4).equals(".txt")){
	        		  //add contents 
	        		   readFile(zEntry, nameTree);
	        	  }
	          }
	      }
	      
	      for(WeightedName wn : nameTree){
	    	  coll.add(wn);
	      }
	      
	}
	
	/**
     * Reads an entry in a zip file and returns data within it
     * 
     * @param zEntry the entry to be read
     * @param treeSet WeightedNameTreeSet to hold the WeightedNames read from the entry
     * @param nameTree TreeSet to hold names that have been added
     * @throws IOException if reading the file fails for various reasons
     */
	public void readFile(ZipEntry zEntry, TreeSet<WeightedName> treeSet) throws IOException{
		BufferedReader bReader = new BufferedReader(new InputStreamReader(zipFileName.getInputStream(zEntry)));
		
		String line;
		

		//read each line of the file
		while ((line = bReader.readLine()) != null) {
			//split data by Name Gender and Weight (separated by commas)
			String[] lineData = line.split(",");

			boolean isInvalidDouble = false;


			//check necessary data is present in line
			if(lineData.length == 3){

				//check if double is valid
				try {
					Double.parseDouble(lineData[2]);
				} catch (NumberFormatException e) {
					isInvalidDouble = true;
				}

				//check that the line is in the correct format
				if(!isInvalidDouble && Double.parseDouble(lineData[2]) > 0  && lineData[0] != null && !lineData[0].equals("") && (lineData[1].trim().equals("M") || lineData[1].trim().equals("F") || lineData[1].trim().equals("U"))){
					//create a new Name with extracted data
					WeightedName n = new WeightedName(lineData[0].trim(), Double.parseDouble(lineData[2]));
					
					//add weighted name to tree, update weight if name is the same
					treeSet.add(n);
				}
			}

		}

		bReader.close();
	}
	
	/**
     * Gets the size of an entry at a certain index
     * 
     * @param index the location of the entry
     * @return size the size of the entry at index
     */
	public long getEntrySize(int index){
		ZipEntry size = null;
		
		//Iterates through entry until one at index is reached
		for(int i = 0; i <= index; i++ ){
			//prevents entries from attempting to get the next entry unless there is one
			if(entries.hasMoreElements()){
				ZipEntry zEntry = (ZipEntry) entries.nextElement();
				//sets the ZipEntry if the corresponding index is found
				if(i == index){
					size = zEntry;
				}
			}
		}
		
		if(size != null){
			return size.getSize();
		}
		else{
			return -1;
		}
	}
	
	 /**
     * Gets the name of an entry at a certain index
     * 
     * @param index the location of the entry
     * @return name the name of the entry at index
     */
	public String getEntryName(int index){
		ZipEntry name = null;
		
		//Iterates through entry until one at index is reached
		for(int i = 0; i <= index; i++ ){
			//prevents entries from attempting to get the next entry unless there is one
			if(entries.hasMoreElements()){
				ZipEntry zEntry = (ZipEntry) entries.nextElement();
				//sets the ZipEntry if the corresponding index is found
				if(i == index){
					name = zEntry;
				}
			}
		}
		
		//returns the name of the ZipEntry
		if(name != null){
			return name.getName();
		}
		else{
			return null;
		}
	}
	
}
