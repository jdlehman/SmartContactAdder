package edu.wm.weightedNames;

import java.io.IOException;

/**
 * Class Name: DefaultImplementation
 * Responsibilities: creates a new set of Names from a zip or text file, returns data from names
 * Collaborators: Names, SetOfWeightedNames
 * 
 * @author jonathanlehman
 *
 */

public class DefaultImplementation {
	
	public static final String FILENAME ="./res/names.zip";
	public static final String ALPH = "abcdefghijklmnopqrstuvwxyz0";
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		try {
			SetOfWeightedNames wNames;
			SetOfWeightedNames topNames1;
			SetOfWeightedNames topNames2 = NamesFactory.createNamesVersion1(null);
			
			System.out.println("Reading weighted names from " + FILENAME);
			//store all of the WeightedNames in fileName to wNames
			wNames = NamesFactory.createNamesVersion1(FILENAME);
		
			//store the top 5000 WeightedNames from wNames in topNames1
			System.out.println("Getting top 5000 names from " + FILENAME);
			topNames1 = ((Names) wNames).getSubsetWithMatchingPrefix("", 5000);
			//write WeightedNames in topNames1 to TopNames1.zip
			System.out.println("Writing TopNames1.zip");
			topNames1.writeZipFile("./res/TopNames1.zip");
			
			//store the 5 most common names for each prefix of length 2 from wNames in topNames2
			System.out.println("Getting top 5 names for each possible prefix in " + FILENAME);
			for(int i = 0; i < ALPH.length() - 1; i++){
				for(int j = 0; j < ALPH.length() - 1; j++){
					
					topNames2 = topNames2.join(wNames.getSubsetWithMatchingPrefix(ALPH.substring(i,i + 1) + ALPH.substring(j, j + 1), 5));
				}
			}
			//write WeightedNames in topNames2 to TopNames2.zip
			System.out.println("Writing TopNames2.zip");
			topNames2.writeZipFile("./res/TopNames2.zip");
			
			System.out.println("Done.");
			
			
// 			OLD IMPLEMENTATION
//			String[] topNames = wNames.getTopNames(5);
//			
//			System.out.println("The top 5 names are:");
//			
//			//print top 5 names
//			for(String e : topNames){
//				System.out.println(e);
//			} 
			
		} catch (IOException e) {
			//prints error message
			System.out.println("Unable to process request because of the following exception:\n" + e.getLocalizedMessage());
		}
		
	}

}//end DefaultImplementation
