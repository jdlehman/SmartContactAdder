package edu.wm.weightedNames;
/**
 * Class Name: WeightedName
 * Responsibilities: create and manage WeightedName objects
 * Collaborators: Names, WeightedNameTreeSet, ZipFileReader, TextFileReader
 * 
 * @author jonathanlehman
 *
 */


public class WeightedName{

	//protected variables for WeightedName
	protected String name;
	protected double weight;
	
	/** constructor for WeightedName with name and weight properties */
	public WeightedName(String name, double weight){
		this.name = name;
		this.weight = weight;
	}
	
	/**
	 * Get the name of the WeightedName
	 * @return name, returns the name property of the WeightedName
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * Get the weight of the WeightedName
	 * @return weight , returns the weight property of the WeightedName
	 */
	public double getWeight(){
			return weight;
	}
	
	/**
	 * Sets the weight of the WeightedName
	 * @param weight , the weight property of the WeightedName
	 */
	protected void setWeight(double weight){
			 this.weight = weight;
	}
	
	/**
	 * Sets the name of the WeightedName
	 * @param name , the name property of the WeightedName
	 */
	protected void setName(String name){
			 this.name = name;
	}
	
	/**
	 * determine if WeightedName values by name are equal
	 * @param n WeightedName to be compared with
	 * @return boolean , true if equal, false otherwise
	 */
	public boolean equals(WeightedName wn){

		if(name.equalsIgnoreCase(wn.getName())){//names are equal
			return true;
		}
		else{
			return false;
		}
		
	}
	
	/** return a string representing the WeightedName
	@return  a string representation of the WeightedName
    */
    public String toString(){
       	return "Name: " + name + " Weight: " + weight;
    }
	

	
}//end WeightedName
