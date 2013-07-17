package edu.wm.weightedNames;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * Class Name: Names
 * Responsibilities: manages a set of weighted Names
 * Collaborators: NamesTreeSet, NamesHashSet, WeightedNameZipReader, WeightedNameTextReader, WeightedName, DefaultImplementation
 * 
 * @author jonathanlehman
 *
 */

public abstract class Names implements SetOfWeightedNames {

	//WeightedNameTreeSet of weighted names
	protected Collection<WeightedName> weightedNames;
	
	/**
	 * Add a name and its weight to the set. If the name is already present in the set, then weights will be added.
	 * As it is expected for a set, there is always at most one element in the set that carries a particular name.
	 * Adds to beginning of linked list.
	 * @param name is not null and describes the name of this element
	 * @param weight is a numerical value associated with this name, weight is not equal to Double.NaN
	 */
	public abstract void add(String name, double weight);
	
	/**
	 * Get the weight for a given name if it is contained in the set or Double.NaN if the name is not known.
	 * @param name is not null and describes the name of an element in the set
	 * @return the weight for that element if it exists, Double.NaN otherwise
	 */
	public double getWeight(String name) {
		Iterator<WeightedName> iter = weightedNames.iterator();
		WeightedName current;
		
		//check that name is a valid value
		if(checkNameValidity(name)){
		
			//iterate through set of WeightedNames
			while(iter.hasNext()){
				current = iter.next();
				
				//return the WeightedName if found
				if(current.getName().equalsIgnoreCase(name)){
					return current.getWeight();
				}
			}
		}
		
		//returns null if the WeightedName is not found
		return Double.NaN;
		
	}
	
	/**
	 * Tells if the name is an element of the set.
	 * @param name is not null and describes the name of an element in the set
	 * @return true if an element with that name exists in the set, false otherwise
	 */
	public abstract boolean contains(String name);
	
	/**
	 * Get k names with the highest weights in the set, where k is a positive integer value.
	 * If the cardinality of the set is less than k, all elements are returned.
	 * The entries in the returned array are in decreasing order of weights; the name with the highest weight comes first.
	 * @param k is positive and specifies how many values shall be returned
	 * @return an array with at least 0 and at most k entries with names of elements in the set. 
	 */
	public String[] getTopNames(int k) {
		//array of strings to return
		String[] topNames;
		//size of weightedNamesList
		int listSize = weightedNames.size();
		
		//check if k is greater than 0
		if(k <= 0 || listSize <= 0){
			topNames = new String[0];
		}
		else{
			//check if cardinality of set is less than k
			if(weightedNames.size() < k){
				topNames = fillTopNames(listSize);
			}
			else{
				topNames = fillTopNames(k);
			}
		
		}
		
		return topNames;
	}

	/** return array of top names
	 * @param size the size of the array to return
	 * @return topNames array of top names 
	 */
	public String[] fillTopNames(int size) {
		//temp TreeSet to sort WeightedNames by Weight
		TreeSet<WeightedName> tempSet = new TreeSet<WeightedName>(new ComparatorByWeight());
		tempSet.addAll(weightedNames);
		
		String[] topNames;
		//initialize the array to hold all of the WeightedNames
		topNames = new String[size];
		
		//get highest weight
		WeightedName addNext = tempSet.last();
		//add to array
		topNames[0] = addNext.getName();
		
		//add the WeightedNames to the array
		for(int i = 1; i < size; i++){
			//get WeightedName with next highest weight
			tempSet.remove(addNext);
			addNext = tempSet.last();
			//add to array
			topNames[i] = addNext.getName();
		}
		return topNames;
	}	
	
	/**
	 * Considers only names with a matching prefix and returns k names with the highest weights in this subset, where k is a positive integer value.
	 * The entries in the returned array are in decreasing order of weights; the name with the highest weight comes first.
	 * @param prefix is a string that all returned names must have, i.e. they start with this string. 
	 * If prefix is null, the method behaves like getTopNames(int k).
	 * @param k is positive and specifies how many values shall be returned
	 * @return an array with at least 0 and at most k entries with names of elements in the set. 
	 */
	public String[] getTopNamesWithMatchingPrefix(String prefix, int k) {
		//check if prefix is null 
		if(prefix == null || prefix.trim().equals("") || k <= 0 || getSize() <= 0){
			return getTopNames(k);
		}
		else{
			//array of strings to return
			String[] topNames;
			
			//size of weightedNamesList
			int listSize = weightedNames.size();
									
			//check if cardinality of set is less than k
			if(weightedNames.size() < k){
				//fill array with all names in list that contain prefix
				topNames = fillTopNamesByPrefix(prefix, listSize, listSize);
			}
			else{
				//fill array with up to k names in list that contain prefix
				topNames = fillTopNamesByPrefix(prefix, k, listSize);
			}
			
			return topNames;
		}
		
	}

	/** return array of top names with specified prefix
	 * @param prefix prefix to be contained in top names
	 * @param requestedSize number of top names with prefix desired
	 * @param listSize length of the list
	 * @return topNames , array of top names by specified prefix
	 */
	public String[] fillTopNamesByPrefix(String prefix, int requestedSize, int listSize) {
		//temp TreeSet to sort WeightedNames by Weight
		TreeSet<WeightedName> tempSet = new TreeSet<WeightedName>(new ComparatorByWeight());
		tempSet.addAll(weightedNames);
		
		//counter
		int ctr = 0;
		String[] topNames;
		//initialize the array to hold k of the WeightedNames
		topNames = new String[requestedSize];
		
		//get highest weight
		WeightedName addNext = tempSet.last();
		//check that prefix is not longer than the name
		if(prefix.length() <= addNext.getName().length()){
			//check if contains prefix
			if(addNext.getName().substring(0, prefix.length()).equalsIgnoreCase(prefix)){
				//add to array
				topNames[0] = addNext.getName();
				//increment counter
				ctr++;
			}
		}
		
		//add the WeightedNames to the array
		for(int i = 1; i < listSize; i++){
			//get WeightedName with next highest weight
			tempSet.remove(addNext);
			addNext = tempSet.last();
			//check that prefix is not longer than the name
			if(prefix.length() <= addNext.getName().length()){
				//check if contains prefix
				if(addNext.getName().substring(0, prefix.length()).equalsIgnoreCase(prefix) && ctr < requestedSize){
					//add to array
					topNames[ctr] = addNext.getName();
					//increment counter
					ctr++;
				}
			}
		}
		
		//return an array with the exact capacity to hold the WeightedNames
		if(topNames.length > ctr){
			String[] topNames2 = new String[ctr];
			
			for(int i = 0; i < ctr; i++){
				topNames2[i] = topNames[i];
			}
			
			return topNames2;
		}
		else{
			return topNames;
		}
		
	}
	
	/**
	 * Gets the size of the list
	 * @return size number of items in the list of names
	 */
	public int getSize() {	
		return weightedNames.size();
	}
	
	/**
	 * Removes a WeightedName from the set
	 * @param name name of the WeightedName to be removed
	 */
	public abstract void remove(String name);
	
	/**
	 * Writes its content to a zip archive file with the given filename.
	 * An existing file of this name will be overwritten and the zip archive will 
	 * contain a single file names.txt with one name entry per line in the 
	 * format A,B,C where A is a name string, B is a single character denoting the sex
	 * which will be set to U for "unknown", and C is an integer number for the number
	 * of occurrences of this name. 
	 * @param zipfile specifies the name of the zipfile to be generated
	 * @exception throws an IOException if the file cannot be created or opened or if the file cannot be successfully filled and closed. 
	 */
	public void writeZipFile(String zipfile) throws IOException {
		
		//temp TreeSet to sort WeightedNames by Weight
		TreeSet<WeightedName> tempSet = new TreeSet<WeightedName>(new ComparatorByName());
		tempSet.addAll(weightedNames);
		
		//write weightedNames to text file
		TextFileManager.writeSetOfWeightedNamesToFile("./res/names.txt", tempSet);
		//compress text file to zip file
		ZipCompressor.compressToZipFile(zipfile, "./res/names.txt");
	}
	
	/**
	 * Obtains a subset of the set of weighted names that match with the given prefix.
	 * The cardinality c of the subset has a range 0 <= c <= k. 
	 * If the set contains more than k elements with matching prefix, then the k with highest
	 * weights are selected for the subset. 
	 * @param prefix describes how all names in the resulting subset must begin. 
	 * A value of null or an empty string "" describe an empty prefix such that any name would qualify.
	 * @param k is the upper bound for the number of elements in the return set.
	 * @return a subset of names with matching prefix and highest weights
	 */
	public SetOfWeightedNames getSubsetWithMatchingPrefix(String prefix, int k){
		//alternate method (less eficient)		
//		String[] subsetArray = getTopNamesWithMatchingPrefix(prefix, k);
//		SetOfWeightedNames subset = new NamesTreeSet();
//		
//		for(int i = 0; i < subsetArray.length; i++){
//			subset.add(subsetArray[i], getWeight(subsetArray[i]));
//		}
		
		return getTopNamesWithMatchingPrefixInSet(prefix, k);
		//return subset;
	}
	
	/**
	 * Obtains the union of a set of weighed names and a given one. 
	 * For any element of same name that is contained in both sets, only one element is selected
	 * for the computed set. If the weights for those two elements differ, the one of higher weight is
	 * selected. Being different in semantics to add, this method is not named addAll but join.
	 * The newly generated set is created without modifying any of the two existing sets.
	 * @param other set that is joined with this set
	 */
	public SetOfWeightedNames join(SetOfWeightedNames other){
		//temp TreeSet to sort WeightedNames by Name for Join method
		//difference in how it updates weight (takes the greatest weight rather than combining)
		NamesTreeSet tempSet = new NamesTreeSet(new ComparatorByNameForJoin());
		//add current WeightedNames to tempSet
		tempSet.addAll(weightedNames);
		
		//add other to tempSet (if any Names are the same will take the one with the greatest weight)
		tempSet.addAll(((Names) other).weightedNames);
		
		return tempSet;
	}
	
	/**
	 * Considers only names with a matching prefix and returns k names with the highest weights in this subset, where k is a positive integer value.
	 * The entries in the returned SetOfWeightedNames are in decreasing order of weights; the name with the highest weight comes first.
	 * @param prefix is a string that all returned names must have, i.e. they start with this string. 
	 * If prefix is null, the method behaves like getTopNames(int k).
	 * @param k is positive and specifies how many values shall be returned
	 * @return a SetOfWeightedNames with at least 0 and at most k entries with names of elements in the set. 
	 */
	public SetOfWeightedNames getTopNamesWithMatchingPrefixInSet(String prefix, int k) {
		//check if prefix is null 
		if(prefix == null || prefix.trim().equals("") || k <= 0 || getSize() <= 0){
			return getTopNamesInSet(k);
		}
		else{
			SetOfWeightedNames topNames = new NamesTreeSet(new ComparatorByName());
			
			//size of weightedNamesList
			int listSize = weightedNames.size();
									
			//check if cardinality of set is less than k
			if(weightedNames.size() < k){
				//fill array with all names in list that contain prefix
				topNames = fillTopNamesByPrefixInSet(prefix, listSize, listSize);
			}
			else{
				//fill array with up to k names in list that contain prefix
				topNames = fillTopNamesByPrefixInSet(prefix, k, listSize);
			}
			
			return topNames;
		}
		
	}

	/** return SetOfWeightedNames of top names with specified prefix
	 * @param prefix prefix to be contained in top names
	 * @param requestedSize number of top names with prefix desired
	 * @param listSize length of the list
	 * @return topNames , SetOfWeightedNames by specified prefix
	 */
	public SetOfWeightedNames fillTopNamesByPrefixInSet(String prefix, int requestedSize, int listSize) {
		//temp TreeSet to sort WeightedNames by Weight
		TreeSet<WeightedName> tempSet = new TreeSet<WeightedName>(new ComparatorByWeight());
		tempSet.addAll(weightedNames);
		
		//counter
		int ctr = 0;
		Collection<WeightedName> topNames = new TreeSet<WeightedName>(new ComparatorByName());
		
		//get highest weight
		WeightedName addNext = tempSet.last();
		//check that prefix is not longer than the name
		if(prefix.length() <= addNext.getName().length()){
			//check if contains prefix
			if(addNext.getName().substring(0, prefix.length()).equalsIgnoreCase(prefix)){
				//add to Collection<WeightedName>
				topNames.add(addNext);
				//increment counter
				ctr++;
			}
		}
		
		//add the WeightedNames to the Collection<WeightedName>
		for(int i = 1; i < listSize; i++){
			//get WeightedName with next highest weight
			tempSet.remove(addNext);
			addNext = tempSet.last();
			//check that prefix is not longer than the name
			if(prefix.length() <= addNext.getName().length()){
				//check if contains prefix
				if(addNext.getName().substring(0, prefix.length()).equalsIgnoreCase(prefix) && ctr < requestedSize){
					//add to Collection<WeightedName>
					topNames.add(addNext);
					//increment counter
					ctr++;
				}
			}
		}
		
		NamesTreeSet returnSet = new NamesTreeSet(new ComparatorByWeight());
		returnSet.addAll(topNames);
		
		return returnSet;
	}
	
	/**
	 * Get k names with the highest weights in the set, where k is a positive integer value.
	 * If the cardinality of the set is less than k, all elements are returned.
	 * The entries in the returned SetOfWeightedNames are in decreasing order of weights; the name with the highest weight comes first.
	 * @param k is positive and specifies how many values shall be returned
	 * @return a SetOfWeightedNames with at least 0 and at most k entries with names of elements in the set. 
	 */
	public SetOfWeightedNames getTopNamesInSet(int k) {
		//SetOfWeightedNames to return
		SetOfWeightedNames topNames;
		//size of weightedNamesList
		int listSize = weightedNames.size();
		
		//check if k is greater than 0
		if(k <= 0 || listSize <= 0){
			topNames = new NamesTreeSet(new ComparatorByName());
		}
		else{
			//check if cardinality of set is less than k
			if(weightedNames.size() < k){
				topNames = fillTopNamesInSet(listSize);
			}
			else{
				topNames = fillTopNamesInSet(k);
			}
		
		}
		
		return topNames;
	}

	/** return SetOfWeightedNames of top names
	 * @param size the size of the SetOfWeightedNames to return
	 * @return topNames array of top names 
	 */
	public SetOfWeightedNames fillTopNamesInSet(int size) {
		//temp TreeSet to sort WeightedNames by Weight
		TreeSet<WeightedName> tempSet = new TreeSet<WeightedName>(new ComparatorByWeight());
		tempSet.addAll(weightedNames);
		
		Collection<WeightedName> topNames = new TreeSet<WeightedName>(new ComparatorByName());
				
		//get highest weight
		WeightedName addNext = tempSet.last();
		//add to set
		topNames.add(addNext);
		
		//add the WeightedNames to the Collection
		for(int i = 1; i < size; i++){
			//get WeightedName with next highest weight
			tempSet.remove(addNext);
			addNext = tempSet.last();
			topNames.add(addNext);
		}
		
		NamesTreeSet returnSet = new NamesTreeSet(new ComparatorByWeight());
		returnSet.addAll(topNames);
		
		return returnSet;
	}	
	
	/** checks if name is valid
	 * @param name
	 * @return true if name is valid, false otherwise
	 */
	protected boolean checkNameValidity(String name) {
		return name != null && !name.trim().equals("");
	}
	
	/** checks if weight is valid
	 * @param weight
	 * @return true if weight is valid, false otherwise
	 */
	protected boolean checkWeightValidity(Double weight) {
		return !Double.isNaN(weight) && weight > 0;
	}
	
}
