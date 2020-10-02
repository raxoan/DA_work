/*
 * Aaron Wong
 * 9/27/2020
 * CIS35B - Assignment 1
 * Automotive.java
 */

package model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Automotive extends OptionSet implements Serializable{

	/* Member Variables */
	private String name;
	private float basePrice, totalPrice; // basePrice does not change
	private OptionSet[] optSet;
	private int count; // number of non-null optSets in array
//	private float totalPrice;
	
	/* Constructors */
	// Default
	public Automotive() {
		this.name = "automotive name";
		this.basePrice = 0;
		this.optSet = new OptionSet[1];
		this.count = 0;
	}
	
	// Construct an Object
	public Automotive(String name, float price) {
		this.name = name;
		basePrice = price;
		this.optSet = new OptionSet[1];
		this.count = 0;
	}
	
	// Construct an ObjectSet
	public Automotive(String name, Option[] opt) {
		super(name, opt);
	}
	
	// One parameter
	public Automotive(String name) {
		this(name, new OptionSet[1], 0);
	}
	
	Automotive(OptionSet[] optSet) {
		this("Name unknown", optSet, 0);
	}
	
	Automotive(float totalPrice) {
		this("Name unknown", new OptionSet[1], totalPrice);
	}
	
	// Two parameters
	Automotive(String name, OptionSet[] optSet) {
		this(name, optSet, 0);
	}
	
//	public Automotive(String name, float totalPrice) {
//		this(name, new OptionSet[1], totalPrice);
//	}
	
	Automotive(OptionSet[] optSet, float totalPrice) {
		this("Name unknown", optSet, totalPrice);
	}
	
	// Three parameters
	Automotive(String name, OptionSet[] optSet, float totalPrice) {
//		super(optSet);
		this.name = name;
		this.optSet = optSet;
		this.totalPrice = totalPrice;
	}
	
	public Automotive(String name, float basePrice, int size) {
		this.name = name;
		this.basePrice = basePrice;
		this.optSet = new OptionSet[size];
		for (int i = 0; i < size; i++) {
			optSet[i] = new OptionSet();
			count++;
		}
	}
	
	/* 'Model' Methods */
	public void Model(int size, String name) {
		optSet = new OptionSet[size];
		this.name = name;
	}
	
	
	/* Getter Methods */
	public String getName() {
		return name;
	}
	
	public OptionSet[] getOptSet() {
		return optSet;
	}
	
	public OptionSet getOptArr(int pos) {
		return optSet[pos];
	}
	
	public float getBasePrice() {
		return basePrice;
	}
	
	public float getTotalPrice() {
		return totalPrice;
	}
	
	/* Setter Methods */
	public void setName(String name) {
		this.name = name;
	}
	
	public void setOptSet(OptionSet[] newOptSet) {
		optSet = newOptSet;
	}
	
	public void setBasePrice(float price) {
		basePrice = price;
	}
	
	public void setTotalPrice() {
		for (int i = 0; i < optSet.length; i++) { 
			totalPrice += optSet[i].getPrices();
		}
	}
	
	/* Instance Methods */
	public int findOptSet(OptionSet findOptSet) {
		for (int i = 0; i < optSet.length; i++) {
			if (this.optSet[i].equals(findOptSet)) {
				return i;
			}
		}
		return -1;
	}
	
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
		
		
//		OptionSet[] temp = new OptionSet[optSet.length + 1];
//		for (int i = 0; i < optSet.length; i++) {
//			temp[i] = optSet[i];
//		}
//		temp[temp.length - 1] = addOptSet; // add new optSet at end of temp array
//		optSet = temp; // copy temp back into member variable
	
	
	public void addOption(OptionSet oS, Option o) {
		oS.addOption(o);
		
	}
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
	
	public void deleteAllOther(OptionSet oS, int pos) {
		oS.deleteAllOther(pos);
	}
	/* Overridden Methods */
	@Override public String toString() {
		String priceF = String.format("%.2f", basePrice);
		StringBuffer str = new StringBuffer("Automotive Name: " + name + "\nPrice: $" + priceF);
		for (int i = 0; i < count; i++) {
			str.append("\n" + optSet[i]);
		}
		return str.toString();
	}

	public int getCount() {
		// TODO Auto-generated method stub
		return count;
	}
	
	/*
	 * Method printOptSets()
	 * Method will print the toString() of the optionSet[] of the current Automotive Object
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
	
}
