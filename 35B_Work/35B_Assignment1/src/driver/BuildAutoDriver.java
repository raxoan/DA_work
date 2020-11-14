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
		
	}

}
