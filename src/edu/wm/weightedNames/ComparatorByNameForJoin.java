package edu.wm.weightedNames;

/**
 * Class Name: ComparatorByName
 * Responsibilities: compare WeightedNames by name for the purpose of sorting for the join method
 * Collaborators: ZipFileReader, Names, TextFileReader, WeightedNameComparator
 * 
 * @author jonathanlehman
 *
 */

public class ComparatorByNameForJoin implements WeightedNameComparator{

	/** compares two WeightedNames by name
	 * @return int returns 1 if name1 > name2, -1 if name1 < name2 & 0 if they are equal (after updating the weight of the existing WeightedName to the weight of the WeightedName with the greatest weight)
	 */
	public int compare(WeightedName wn1, WeightedName wn2) {
		String name1 = wn1.getName();
		String name2 = wn2.getName();
		
		if(name1.equalsIgnoreCase(name2)){//names are equal
			//take name with the greater weight
			if(wn1.getWeight() > wn2.getWeight()){
				//set the weight to wn1's weight
				wn2.setWeight(wn1.getWeight());
			}
			//else do nothing because wn2's weight is already the greater weight 
			
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
