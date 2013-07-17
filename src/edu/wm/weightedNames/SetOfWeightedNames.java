package edu.wm.weightedNames;

import java.io.IOException;

/**
 * This interface describes methods to interact with a set of names (strings), where each name carries a numerical weight.
 * As it is expected for a set, there is always at most one element in the set that carries a particular name.
 * The set of legal names excludes null, the set of legal weights excludes Double.NaN.
 * 
 * @author peterkemper
 *
 */
public interface SetOfWeightedNames {
	/**
	 * Add a name and its weight to the set. If the name is already present in the set, then weights will be added.
	 * As it is expected for a set, there is always at most one element in the set that carries a particular name.
	 * @param name is not null and describes the name of this element
	 * @param weight is a numerical value associated with this name, weight is not equal to Double.NaN
	 */
	public void add(String name, double weight) ;
	/**
	 * Get the weight for a given name if it is contained in the set or Double.NaN if the name is not known.
	 * @param name is not null and describes the name of an element in the set
	 * @return the weight for that element if it exists, Double.NaN otherwise
	 */
	public double getWeight(String name) ;
	/**
	 * Tells if the name is an element of the set.
	 * @param name is not null and describes the name of an element in the set
	 * @return true if an element with that name exists in the set, false otherwise
	 */
	public boolean contains(String name) ;
	/**
	 * Get k names with the highest weights in the set, where k is a positive integer value.
	 * If the cardinality of the set is less than k, all elements are returned.
	 * The entries in the returned array are in decreasing order of weights, the name with highest weight comes first.
	 * @param k is positive and specifies how many values shall be returned
	 * @return an array with at least 0 and at most k entries with names of elements in the set. 
	 */
	public String[] getTopNames(int k) ;
	/**
	 * Considers only names with a matching prefix and returns k names with the highest weights in this subset, where k is a positive integer value.
	 * The entries in the returned array are in decreasing order of weights, the name with highest weight comes first.
	 * @param prefix is a string that all returned names must have, i.e. they start with this string. 
	 * If prefix is null, the method behaves like getTopNames(int k).
	 * @param k is positive and specifies how many values shall be returned
	 * @return an array with at least 0 and at most k entries with names of elements in the set. 
	 */
	public String[] getTopNamesWithMatchingPrefix(String prefix, int k) ;
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
	public void writeZipFile(String zipfile) throws IOException ;
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
	public SetOfWeightedNames getSubsetWithMatchingPrefix(String prefix, int k) ;
	/**
	 * Obtains the union of a set of weighed names and a given one. 
	 * For any element of same name that is contained in both sets, only one element is selected
	 * for the computed set. If the weights for those two elements differ, the one of higher weight is
	 * selected. Being different in semantics to add, this method is not named addAll but join.
	 * The newly generated set is created without modifying any of the two existing sets.
	 * @param other set that is joined with this set
	 */
	public SetOfWeightedNames join(SetOfWeightedNames other) ;
}
