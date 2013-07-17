package edu.wm.weightedNames;

import java.io.*;
import java.util.*;

/**
 * Class Name: TextFileManager
 * Responsibilities: write formated text files of sets of WeightedNames
 * Collaborators: Names
 * 
 * @author jonathanlehman
 *
 */

public class TextFileManager {
 
	//constant new line character that works on any system
	final static String NEWLINE = System.getProperty("line.separator");
	
	/** Writes Collection of WeightedName to a text file
	 * 
	 * @param fileName name of file for data to be written to
	 * @param set Collection of WeightedNames to be written to file
	 * @throws IOException
	 */
	public static void writeSetOfWeightedNamesToFile(String fileName, Collection<WeightedName> set) throws IOException{
		
		BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
		
		Iterator<WeightedName> iter = ((TreeSet) set).iterator();
		WeightedName current;
		
		
		//iterate through Collection of WeightedNames
		while(iter.hasNext()){
			current = iter.next();
			
			//write Name,Gender,Weight to file
			bw.write(current.getName() + "," + "U" + "," + current.getWeight() + NEWLINE);
		}
		
	    bw.close();
	}
	
//	/** Deletes file if it exists
//	 * 
//	 * @return true if file was deleted, false if it was not (due to security issues or file does not exist)
//	 * @param fileName
//	 */
//	public static boolean deleteFile(String fileName){
//		File tempFile = new File(fileName);
//		boolean deleted = true;
//		
//		//try to delete file
//		try{
//			tempFile.delete();
//		}
//		catch(Exception e){
//			deleted = false;
//		}
//	
//		//if file was deleted return true, otherwise false
//		if(deleted = true){
//			return true;
//		}
//		else{
//			return false;
//		}
//	}

}
