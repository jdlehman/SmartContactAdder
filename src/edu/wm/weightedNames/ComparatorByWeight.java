package edu.wm.weightedNames;

/**
 * Class Name: ComparatorByWeight
 * Responsibilities: compare WeightedNames by weight for the purpose of sorting
 * Collaborators: ZipFileReader, Names, TextFileReader, WeightedNameComparator
 * 
 * @author jonathanlehman
 *
 */

public class ComparatorByWeight implements WeightedNameComparator{

	/** compares two WeightedNames by weight
	 * @return int returns 1 if weight1 > weight2, -1 if weight1 < weight2 & 0 if they are equal
	 */
	public int compare(WeightedName wn1, WeightedName wn2) {
		double weight1 = wn1.getWeight();
		double weight2 = wn2.getWeight();
		
		//compares WeightedName's weight to wn's weight
		if(weight1 > weight2){//weight > wn's weight
			return 1;
		}
		else if(weight1 < weight2){//weight < wn's weight
			return -1;
		}
		else{//weight = wn's weight
			String name1 = wn1.getName();
			String name2 = wn2.getName();
			
			//compare names if weights are equal, to differentiate WeightedNames with same weight
			//names that would come first in alphabet are treated as higher
			if(name1.compareTo(name2) > 0){//name comes before wn's name in alphabet
				return 1;
			}
			else if(name1.compareTo(name2) < 0){//name comes after wn's name in alphabet
				return -1;
			}
			else{//names are equal
				return 0;
			}
		}
	}

}
