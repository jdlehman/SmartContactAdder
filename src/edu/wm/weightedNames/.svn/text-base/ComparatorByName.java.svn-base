package edu.wm.weightedNames;

/**
 * Class Name: ComparatorByName
 * Responsibilities: compare WeightedNames by name for the purpose of sorting
 * Collaborators: ZipFileReader, Names, TextFileReader, WeightedNameComparator
 * 
 * @author jonathanlehman
 *
 */

public class ComparatorByName implements WeightedNameComparator{

	/** compares two WeightedNames by name
	 * @return int returns 1 if name1 > name2, -1 if name1 < name2 & 0 if they are equal (after updating the weight of the existing WeightedName)
	 */
	public int compare(WeightedName wn1, WeightedName wn2) {
		String name1 = wn1.getName();
		String name2 = wn2.getName();
		
		if(name1.equalsIgnoreCase(name2)){//names are equal
			//update weight if name is the same
			wn2.setWeight(wn2.getWeight() + wn1.getWeight());
			return 0;
		}
		else if(name1.compareToIgnoreCase(name2) < 0){//name comes before wn's name in alphabet
			return 1;
		}
		else {//name comes after wn's name in alphabet
			return -1;
		}
	}

}
