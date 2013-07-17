package edu.wm.weightedNames;

import java.io.IOException;

/**
 * Class Name: NamesFactory
 * Responsibilities: create sets (implemented with different data structures) of WeightedNames from data extracted from a valid file  
 * Collaborators: NamesTest, NamesTest2
 * 
 * @author jonathanlehman
 *
 */

public class NamesFactory {

	/**
	 * 
	 * @param filename name of file to extract WeightedNames from
	 * @return NamesTreeSet set of WeightedNames implemented with TreeSet
	 * @throws IOException throws exception if the file is invalid
	 */
	static SetOfWeightedNames createNamesVersion1(String filename) throws IOException {
		return new NamesTreeSet(filename);
	}
}
