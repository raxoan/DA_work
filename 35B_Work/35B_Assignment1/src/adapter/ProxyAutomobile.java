/**
 * @author Aaron
 * CIS 35B - Lab 3
 * ProxyAutomobile.java
 */

package adapter;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import model.*;
import model.OptionSet.Option;
import util.*;

/**
 * This class should have the necessary methods to construct new Automobile
 * objects in the BuildAuto class that is inheriting this class. This class is
 * internal and whenever the API needs to be updated, the methods within this
 * class will be changed to reflect the needs of the user.
 */
public abstract class ProxyAutomobile implements CreateAuto {

	/* Member Variables */
	protected static Automobile a1;
	protected static LinkedHashMap<String, Automobile> map;

	/* Implementation Methods */

	public Automobile getAutoMap(String name) {
		return map.get(name);
	}

	/**
	 * Given a text file name a method called BuildAuto can be written to build an
	 * instance of Automobile. This method does not have to return the Auto
	 * instance.
	 * 
	 * @param filename
	 */
	public void buildAuto(String filename) {
		try {
			a1 = FileIO.buildAutoObject(filename);
			System.out.println("buildAuto() completed on " + filename);
//			map.put(filename, a1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This function searches and prints the properties of a given Automodel. Cannot
	 * be called if an Automobile object has not been created.
	 * 
	 * @param modelName
	 */
	public void printAuto(String modelName) {
		if (a1.getName().equalsIgnoreCase(modelName)) {
			System.out.println(a1.toString());
		} else {
			System.out.println("print failed");
		}
	}

	/**
	 * This function searches the Model for a given OptionSet and sets the name of
	 * OptionSet to newName.
	 * 
	 * @param modelName
	 * @param optionSetname
	 * @param newName
	 */
	public void updateOptionSetName(String modelName, String optionSetName, String newName) {
		try {
			OptionSet optSet = findOptionSet(a1, optionSetName);
			a1.setOptSetName(optSet, newName);
		} catch (Exception e) {
			System.out.println("updateOptionSetName() failed");
		}
	}

	/**
	 * Updates the name of a given option on an Automobile. Method is synchronized
	 * as it is a non-static method operating on the instantiated object, a1, in this
	 * class
	 * 
	 * @param modelName
	 * @param optionSetName
	 * @param oldName
	 * @param newName
	 */
	public synchronized void updateOptionName(String optionSetName, String oldName, String newName) {
		// Since parameters are Strings and not object types, need to find the
		// corresponding object to run methods off of
		OptionSet optSet = findOptionSet(a1, optionSetName);
		ArrayList<Option> optArr = a1.getOptArr(optSet);
		OptionSet.Option opt = optArr.get(0);;
		boolean found = false;
		if (a1.getOptName(optArr.get(0)).equalsIgnoreCase(oldName)) {
			// this Auto object should only have one option per optionset.
			a1.changeOptionName(opt, newName); // change the name of the given option
			found = true;
		}
		if (found == true) {
			System.out.println(optionSetName + " changed successfully.");
		} else {
			System.out.println("Could not change " + oldName + " to " + newName);
			System.out.println(
					"The option for " + optionSetName + " is currently set to " + a1.getOptName(opt));
		}
	}

	/**
	 * This function searches the Model for a given OptionSet and Option name, and
	 * sets the price to newPrice.
	 * 
	 * @param modelName
	 * @param optionSetName - optionSet name
	 * @param optionName    - Option name
	 * @param newPrice
	 */
	public void updateOptionPrice(String modelName, String optionSetName, String optionName, float newPrice) {
		if (a1.getName().equalsIgnoreCase(modelName)) {
			OptionSet optSet = findOptionSet(a1, optionSetName);
			ArrayList<Option> optArr = a1.getOptArr(optSet);
			OptionSet.Option opt;
			int count = 0;
			while (count < optArr.size()) {
				if (a1.getOptName(optArr.get(count)).equalsIgnoreCase(optionName)) {
					opt = optArr.get(count);
					a1.setOptionPrice(opt, newPrice);
					count = optArr.size();
				} else {
					count++;
				}
			}
		} else {
			System.out.println("updateOptionPrice() failed");
		}
	}

	/**
	 * Searches automobile object for an optionSet with the given optionSetName
	 * 
	 * @param auto
	 * @param optionSetName
	 * @return
	 */
	public OptionSet findOptionSet(Automobile auto, String optionSetName) {
		ArrayList<OptionSet> optSetArr = auto.getOptSetArr(); // retrieves the OptionSet array of the object a1.
		OptionSet optSet = new OptionSet();
		int count = 0;
		while (count < optSetArr.size()) {
			if (auto.getOptSetName(optSetArr.get(count)).equalsIgnoreCase(optionSetName)) { // searches array for
																							// matching
				// name
				optSet = auto.getOptSet(count);
				return optSet;
			} else {
				count++;
			}
		}
		return optSet;
	}
}
