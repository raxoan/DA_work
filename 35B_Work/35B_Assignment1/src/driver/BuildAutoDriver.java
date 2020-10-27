/**
 * @author Aaron
 * CIS 35B - Lab 3
 * BuildAutoDriver.java
 */

package driver;

import adapter.*;

public class BuildAutoDriver {
	/**
	 * Main method for API building Automobile class.
	 * @param args
	 */
	public static void main(String[] args) {
		BuildAuto a1 = new BuildAuto();
		String fileName = "Automobile.txt"; 
		String modelName = "Ford Wagon ZTW (All options)";
		a1.buildAuto(fileName);
		a1.printAuto(modelName);
		a1.updateOptionSetName(modelName, "Color", "Apples"); // Changing OptionSet name "Color" to "Apples" 
		a1.printAuto(modelName);
		a1.updateOptionPrice(modelName, "Apples", "Fort Knox Gold Clearcoat Metallic", 100); // Changing price to $100
		a1.printAuto(modelName);
		
	}

}
