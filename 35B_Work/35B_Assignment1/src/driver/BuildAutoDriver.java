/**
 * @author Aaron
 * CIS 35B - Lab 2
 * BuildAutoDriver.java
 */

package driver;

import adapter.*;
import exceptionHandler.*;

public class BuildAutoDriver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String fileName = "Automobile.txt";
		String modelName = "Ford Wagon ZTW";
		BuildAuto a1 = new BuildAuto();
		a1.buildAuto(fileName);
		a1.printAuto(modelName);
		a1.updateOptionSetName(modelName, "Color", "potato");
		System.out.println("Changing 'color' to 'potato'");
		a1.printAuto(modelName);
		a1.updateOptionPrice(modelName, "Brakes", "ABS", 6969);
		System.out.println("Changing price of ABS Brakes to $6,969.00");
		a1.printAuto(modelName);
	}

}
