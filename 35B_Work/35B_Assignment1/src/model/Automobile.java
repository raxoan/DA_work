/*
 * Aaron Wong
 * 9/27/2020
 * CIS35B - Assignment 1
 * Automobile.java
 */

package model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Automobile extends OptionSet implements Serializable{

	/* Member Variables */
	private String name;
	private float basePrice, totalPrice; // basePrice does not change
	private OptionSet[] optSet;
	private int count; // number of non-null optSets in array
	
	//////////////////
	/* Constructors */
	//////////////////
	
	// Default
	public Automobile() {
		this.name = "default Automobile name";
		this.basePrice = 0;
		this.optSet = new OptionSet[1];
		this.count = 0;
	}
	
	// Construct an Automobile using name and baseprice
	public Automobile(String name, float basePrice) {
		this.name = name;
		this.basePrice = basePrice;
		this.optSet = new OptionSet[1]; 
		this.count = 0;
	}
	
	// One parameter
	public Automobile(String name) {
		this(name, new OptionSet[1], 0);
	}
	
	public Automobile(OptionSet[] optSet) {
		this("Name unknown", optSet, 0);
	}
	
	public Automobile(float price) {
		this("Name unknown", new OptionSet[1], price);
	}
	
	// Two parameters
	public Automobile(String name, OptionSet[] optSet) {
		this(name, optSet, 0);
	}

	
	public Automobile(OptionSet[] optSet, float price) {
		this("Name unknown", optSet, price);
	}
	
	// Three parameters
	public Automobile(String name, OptionSet[] optSet, float price) {
		this.name = name;
		this.optSet = optSet;
		this.basePrice = price;
	}
	
	public Automobile(String name, float basePrice, int size) {
		this.name = name;
		this.basePrice = basePrice;
		this.optSet = new OptionSet[size];
		for (int i = 0; i < size; i++) {
			optSet[i] = new OptionSet();
			count++;
		}
	}	
	
	////////////////////
	/* Getter Methods */
	////////////////////
	
	public String getName() {
		return name;
	}

	public float getBasePrice() {
		return basePrice;
	}
	
	public float getTotalPrice() {
		return totalPrice;
	}
	public OptionSet[] getOptSetArr() {
		return optSet;
	}
	
	public String getOptSetName(OptionSet optSet) {
		return optSet.getName();
	}
	
	public String getOptName(OptionSet.Option opt) {
		return opt.getName();
	}
	
	// Returns the OptionSet in the given position in the OptionSet array.
	public OptionSet getOptSet(int pos) {
		return optSet[pos];
	}
	
	// Return array of options given an optionSet 
	public Option[] getOptArr(OptionSet optSet) {
		return optSet.getOptions();
	}
	
	public int getCount() {
		return count;
	}	
	
	////////////////////
	/* Setter Methods */
	////////////////////
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setOptSet(OptionSet[] newOptSet) {
		optSet = newOptSet;
	}
	
	public void setOptSetName(OptionSet optSet, String name) {
		optSet.setName(name);
	}
	
	public void setBasePrice(float price) {
		basePrice = price;
	}
	
	/*
	 * Method: setTotalPrice()
	 * Sums up the prices of all optionSets in array
	 */
	public void setTotalPrice() {
		for (int i = 0; i < optSet.length; i++) { 
			totalPrice += optSet[i].getPrices();
		}
	}
	
	public void setOptionPrice(OptionSet.Option opt, float price) {
		opt.setPrice(price);
	}
	
	public void setCount(int count) {
		this.count = count;
	}
	
	//////////////////////
	/* Instance Methods */
	//////////////////////
	
	/*
	 * Method: findOptSet()
	 * @Param OptionSet: a single optionset of the Automobile
	 * Searches the OptionSet[] array for the param optionset and returns it's index position.
	 */
	public int findOptSet(OptionSet findOptSet) {
		for (int i = 0; i < optSet.length; i++) {
			if (this.optSet[i].equals(findOptSet)) {
				return i;
			}
		}
		return -1; // if not found
	}
	
	/*
	 * Method: addOptSet()
	 * @Param addOptSet: an array of options, or an instance of the OptionSet class
	 * First checks the Automobile's OptionSet[] array (variable optSet) to see if it is nearing capacity 
	 * via the variable 'count', which holds the number of non-null objects in the array.
	 * If adding the param brings the array to full capacity, it will double the size of the array.
	 * Once the param is added to optSet, it will increase the count by one.
	 */
	public void addOptSet(OptionSet addOptSet) {
		if (count + 1 >= optSet.length) {
			OptionSet[] temp = new OptionSet[optSet.length * 2];
			for (int i = 0; i < count; i++) {
				temp[i] = optSet[i];
			}
			optSet = temp;
		}
		optSet[count] = addOptSet;
		count++;
	}
			
	/*
	 * Method: addOption()
	 * @Param OptionSet oS; an instance of OptionSet class
	 * @Param Option o; an instance of the Option class
	 * Given an OptionSet and Option have been constructed, adds the param o
	 * into the OptionSet by calling the OptionSet instance method.
	 */
	public void addOption(OptionSet oS, Option o) {
		oS.addOption(o);
		
	}
	
	/*
	 * Method: deleteOptSet()
	 * @Param OptionSet delOptSet; an instance of the OptionSet class
	 * Using the param delOptSet; the program will first search the Automobile class 
	 * for a matching OptionSet using the findOptSet() method.
	 * If this method returns a non-negative integer, it will use that as the index position
	 * to copy over objects in the following index.
	 */
	public void deleteOptSet(OptionSet delOptSet) {
		int find = findOptSet(delOptSet);
		if (find != -1) {
			OptionSet[] temp = new OptionSet[optSet.length - 1];
			for (int i = find; i < optSet.length; i++) {
				optSet[i] = optSet[i + 1];
			}
			for (int j = 0; j < temp.length; j++) {
				temp[j] = optSet[j];
			}
			optSet = temp;
		} else {
			System.out.println("Option Set: '" + delOptSet.getName() + "' could not be found.");
		}
	}
	
	/*
	 * Method: deleteAllOther()
	 * @Param OptionSet oS; an instance of the OptionSet class.
	 * @Param int pos; the position of the option in the array
	 * Calls the similarily name deleteAllOther() method in the OptionSet class
	 */
	public void deleteAllOther(OptionSet oS, int pos) {
		oS.deleteAllOther(pos);
	}
	
	
	/*
	 * Method printOptSets()
	 * Method will print the toString() of the optionSet[] of the current Automobile Object
	 */
	public void printOptSets() {
		for (int i = 0; i < count; i++) {
			System.out.println((i + 1) + ". " + optSet[i]);
		}
	}
	
	/*
	 * Method printOptions(pos, a)
	 * @Param int pos; the position of the optionSet in question
	 * Method will print all of the Options within a particular OptionSet, determined by the index position provided
	 */
	public void printOptions(int pos) {
		for (int i = 0; i < optSet[pos].getCount(); i++) {
			System.out.println((i + 1) + ". " + optSet[pos].getOptions()[i]);
		}
			
	}
	
	////////////////////////
	/* Overridden Methods */
	////////////////////////
	
	@Override public String toString() {
		String priceF = String.format("%.2f", basePrice);
		StringBuffer str = new StringBuffer("Automobile Name: " + name + "\nPrice: $" + priceF);
		for (int i = 0; i < count; i++) {
			str.append("\n" + optSet[i]);
		}
		return str.toString();
	}

}
