package edu.wm.weightedNames;

import java.io.IOException;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * Class Name: NamesTreeSet
 * Responsibilities: manages a set of weighted Names in a TreeSet
 * Collaborators: ZipFileReader, TextFileReader, Names
 * 
 * @author jonathanlehman
 *
 */

public class NamesTreeSet extends Names{

	/**constructor creates an empty TreeSet of names*/
	public NamesTreeSet() {
		weightedNames = new TreeSet<WeightedName>(new ComparatorByName());
	}
	
	/**constructor creates an empty TreeSet of names with a defined comparator*/
	public NamesTreeSet(Comparator comp) {
		weightedNames = new TreeSet<WeightedName>(comp);
	}
	
	/** constructor creates a new TreeSet of names from a text file or zip file*/
	public NamesTreeSet(String fileName) throws IOException{
		weightedNames = new TreeSet<WeightedName>(new ComparatorByName());
		
		//check if fileName is null
		if(fileName != null){
			//check if file is a zip file
			if(fileName.substring(fileName.length() - 4).equals(".zip")){
				//read data from zip file and store in weightedNameTree
				ZipFileReader reader = new ZipFileReader(fileName);
				reader.readZipFile(weightedNames);
			}
			else if(fileName.substring(fileName.length() - 4).equals(".txt")){//check if file is a txt file
				//read data from text file and store in weightedNameTree
				TextFileReader reader = new TextFileReader(fileName);
				reader.readFile(weightedNames);
			}
		}
	}
	
	/**
	 * Add a name and its weight to the set. If the name is already present in the set, then weights will be added.
	 * As it is expected for a set, there is always at most one element in the set that carries a particular name.
	 * Adds to beginning of linked list.
	 * @param name is not null and describes the name of this element
	 * @param weight is a numerical value associated with this name, weight is not equal to Double.NaN
	 */
	public void add(String name, double weight) {
		
		//check that parameters are valid values
		if(checkNameValidity(name) && checkWeightValidity(weight)){
			//add the weighted name to the set, if it already exists, update the weight
			WeightedName newWN = new WeightedName(name, weight);
			weightedNames.add(newWN);
		}
	}	
	
	/**
	 * Tells if the name is an element of the set.
	 * @param name is not null and describes the name of an element in the set
	 * @return true if an element with that name exists in the set, false otherwise
	 */
	public boolean contains(String name) {	
		
		if(checkNameValidity(name)){
			return weightedNames.contains(new WeightedName(name, 0));
		}
		else{
			return false;
		}
	}
	
	/**
	 * Removes a WeightedName from the set
	 * @param name name of the WeightedName to be removed
	 */
	public void remove(String name) {
		
		//check that name is valid value
		if(checkNameValidity(name)){	
			weightedNames.remove(new WeightedName(name, 0));
		}
		
	}
	
	/**Add collection to weightedNames
	 * 
	 * @param coll collection to add
	 */
	public void addAll(Collection<WeightedName> coll){
		
		weightedNames.addAll(coll);
	}
	
}
