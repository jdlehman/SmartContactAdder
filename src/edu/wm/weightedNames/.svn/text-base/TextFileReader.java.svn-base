package edu.wm.weightedNames;

import java.io.*;
import java.util.*;

/**
 * Class Name: TextFileReader
 * Responsibilities: reads data from file and stores WeightedNames to WeightedNameTreeSet
 * Collaborators: Names, WeightedNameTreeSet, TreeSet, WeightedNameByName, WeightedName
 * 
 * @author jonathanlehman
 *
 */

public class TextFileReader {

	//name of file to be read
	protected File fileName;
	
	/** Constructor creates FileReader that holds file name of the file */
	public TextFileReader(String fileName) throws IOException{
		this.fileName = new File(fileName);
	}
	
	/**
     * Reads a text file and stores data 
     *
     * @param set WeightedNameTreeSet to hold the WeightedNames
     * @throws IOException if reading the file fails for various reasons
     */
	public void readFile(Collection<WeightedName> coll) throws IOException{
		//buffered reader to read the contents of the file
		BufferedReader bReader = new BufferedReader(new FileReader(fileName));
		
		//TreeSet to hold the names that have been added, used to quickly determine if a WeightedName needs to be added
		//or the weight needs to be updated
		TreeSet<WeightedName> nameTree = new TreeSet<WeightedName>(new ComparatorByName());
		
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
					nameTree.add(n);		
				}
			}

		}

		bReader.close();
		
		  for(WeightedName wn : nameTree){
	    	  coll.add(wn);
	      }
	}
	
	
}
