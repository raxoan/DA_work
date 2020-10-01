/*
 * Aaron Wong
 * 9/27/2020
 * CIS35B - Assignment 1
 * Automotive.java
 */

package model;

import util.*;

public class Automotive extends OptionSet{

	/* Member Variables */
	private String name;
	private float price, basePrice, totalPrice; // basePrice does not change
	private OptionSet[] optSet;
//	private float totalPrice;
	
	/* Constructors */
	// Default
	public Automotive() {
		this("Name unknown", new OptionSet[1], 0);
	}
	
	// Construct an Object
	public Automotive(String name, float price) {
		super(name, price);
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
		OptionSet[] temp = new OptionSet[optSet.length + 1];
		for (int i = 0; i < optSet.length; i++) {
			temp[i] = optSet[i];
		}
		temp[temp.length - 1] = addOptSet; // add new optSet at end of temp array
		optSet = temp; // copy temp back into member variable
	}
	
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
	
	/* Overridden Methods */
	@Override public String toString() {
		StringBuffer str = new StringBuffer("Automotive Name: " + name);
		for (int i = 0; i < optSet.length; i++) {
			str.append("\n" + optSet[i].toString());
		}
		return str.toString();
	}
	
	
}
