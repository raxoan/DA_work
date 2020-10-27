/*
 * Aaron Wong
 * CIS35B - Assignment 3
 * Automobile.java
 */

package model;

import java.io.Serializable;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class Automobile extends OptionSet implements Serializable {

	/* Member Variables */
	private String name, make, model;
	private float basePrice, totalPrice; // basePrice does not change
	private ArrayList<OptionSet> optSet;
	private ArrayList<Option> choice;
	//////////////////
	/* Constructors */
	//////////////////

	// Default
	public Automobile() {
		this.name = "default Automobile name";
		this.basePrice = 0;
//		this.optSet = new OptionSet[1];
		this.optSet = new ArrayList<OptionSet>();
	}

	// Construct an Automobile using name and baseprice
	public Automobile(String name, float basePrice) {
		this.name = name;
		this.basePrice = basePrice;
		this.optSet = new ArrayList<OptionSet>();
	}

	// One parameter
	public Automobile(String name) {
		this(name, new ArrayList<OptionSet>(), 0);
	}

	public Automobile(ArrayList<OptionSet> optSet) {
		this("Name unknown", optSet, 0);
	}

	public Automobile(float price) {
		this("Name unknown", new ArrayList<OptionSet>(), price);
	}

	// Two parameters
	public Automobile(String name, ArrayList<OptionSet> optSet) {
		this(name, optSet, 0);
	}

	public Automobile(ArrayList<OptionSet> optSet, float price) {
		this("Name unknown", optSet, price);
	}

	// Three parameters
	public Automobile(String name, ArrayList<OptionSet> optSet, float price) {
		this.name = name;
		this.optSet = optSet;
		this.basePrice = price;
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

	public ArrayList<OptionSet> getOptSetArr() {
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
		return optSet.get(pos);
	}

	// Return array of options given an optionSet
	public ArrayList<Option> getOptArr(OptionSet optSet) {
		return optSet.getOptions();
	}

	public String getMake() {
		return make;
	}

	public String getModel() {
		return model;
	}

	public String getOptionChoice(String optSetName) {
		OptionSet set = new OptionSet(optSetName);
		String choiceName = "Option Choice not found";
		if (optSet.contains(set)) {
			int pos = optSet.indexOf(set);
			choiceName = optSet.get(pos).getOptions().toString();
		}
		return choiceName;
	}

	public float getOptionChoicePrice(String optSetName) {
		OptionSet set = new OptionSet(optSetName);
		float choicePrice = -1;
		if (optSet.contains(set)) {
			int pos = optSet.indexOf(set);
			choicePrice = optSet.get(pos).getPrices();
		}
		return choicePrice;
	}

	////////////////////
	/* Setter Methods */
	////////////////////

	public void setName(String name) {
		this.name = name;
	}

	public void setOptSet(ArrayList<OptionSet> newOptSet) {
		optSet = newOptSet;
	}

	public void setOptSetName(OptionSet optSet, String name) {
		optSet.setName(name);
	}

	public void setBasePrice(float price) {
		basePrice = price;
	}

	/*
	 * Method: setTotalPrice() Sums up the prices of all optionSets in array
	 */
	public void setTotalPrice() {
		totalPrice = basePrice;// start with the base price
		for (int i = 0; i < optSet.size(); i++) { // add price of options
			totalPrice += optSet.get(i).getPrices();
		}

	}

	public void setOptionPrice(OptionSet.Option opt, float price) {
		opt.setPrice(price);
	}

	public void setMake(String make) {
		this.make = make;
	}

	public void setModel(String model) {
		this.model = model;
	}

	/**
	 * Set the current automobile's options to the given optionset name and choice.
	 * 
	 * @param optSetName
	 * @param optName
	 */
	public void setOptionChoice(String optSetName, String optName) {
		OptionSet set = new OptionSet(optSetName);
		OptionSet.Option opt = new OptionSet.Option(optName);
		optSet.add(set);
		choice.add(opt);

	}
	//////////////////////
	/* Instance Methods */
	//////////////////////

	/*
	 * Method: findOptSet()
	 * 
	 * @Param OptionSet: a single optionset of the Automobile Searches the
	 * OptionSet[] array for the param optionset and returns it's index position.
	 */
	public int findOptSet(OptionSet findOptSet) {
		if (optSet.contains(findOptSet)) {
			return optSet.indexOf(findOptSet);
		}
		return -1; // if not found
	}
	
	/*
	 * Method: addOptSet()
	 * 
	 * @Param addOptSet: an array of options, or an instance of the OptionSet class
	 * First checks the Automobile's OptionSet[] array (variable optSet) to see if
	 * it is nearing capacity via the variable 'count', which holds the number of
	 * non-null objects in the array. If adding the param brings the array to full
	 * capacity, it will double the size of the array. Once the param is added to
	 * optSet, it will increase the count by one.
	 */
	public void addOptSet(OptionSet addOptSet) {
//		if (count + 1 >= optSet.length) {
//			OptionSet[] temp = new OptionSet[optSet.length * 2];
//			for (int i = 0; i < count; i++) {
//				temp[i] = optSet[i];
//			}
//			optSet = temp;
//		}
//		optSet[count] = addOptSet;
		optSet.add(addOptSet);
	}

	/*
	 * Method: addOption()
	 * 
	 * @Param OptionSet oS; an instance of OptionSet class
	 * 
	 * @Param Option o; an instance of the Option class Given an OptionSet and
	 * Option have been constructed, adds the param o into the OptionSet by calling
	 * the OptionSet instance method.
	 */
	public void addOption(OptionSet oS, Option o) {
		oS.addOption(o);
	}

	/*
	 * Method: deleteOptSet()
	 * 
	 * @Param OptionSet delOptSet; an instance of the OptionSet class Using the
	 * param delOptSet; the program will first search the Automobile class for a
	 * matching OptionSet using the findOptSet() method. If this method returns a
	 * non-negative integer, it will use that as the index position to copy over
	 * objects in the following index.
	 */
	public void deleteOptSet(OptionSet delOptSet) {
		if (optSet.contains(delOptSet)) {
			optSet.remove(delOptSet);
		} else {
			System.out.println("Option Set: '" + delOptSet.getName() + "' could not be found.");
		}
	}

	/*
	 * Method: deleteAllOther()
	 * 
	 * @Param OptionSet oS; an instance of the OptionSet class.
	 * 
	 * @Param int pos; the position of the option in the array Calls the similarily
	 * name deleteAllOther() method in the OptionSet class
	 */
//	public void deleteAllOther(OptionSet oS, int pos) {
//		oS.deleteAllOther(pos);
//	}

	/*
	 * Method printOptSets() Method will print the toString() of the optionSet[] of
	 * the current Automobile Object
	 */
	public void printOptSets() {
		for (int i = 0; i < optSet.size(); i++) {
			System.out.println((i + 1) + ". " + optSet.get(i));
		}
	}

	/*
	 * Method printOptions(pos, a)
	 * 
	 * @Param int pos; the position of the optionSet in question Method will print
	 * all of the Options within a particular OptionSet, determined by the index
	 * position provided
	 */
//	public void printOptions(int pos) {
//		for (int i = 0; i < optSet.get(pos).getSize(); i++) {
//			System.out.println((i + 1) + ". " + optSet.get(pos).getOptions()[i]);
//		}
//			
//	}

	////////////////////////
	/* Overridden Methods */
	////////////////////////

	@Override
	public String toString() {
		String priceBase = String.format("%.2f", basePrice);
		StringBuffer str = new StringBuffer("\nAutomobile Name: " + name + "\nBase Price: $" + priceBase);
		for (int i = 0; i < optSet.size(); i++) {
			str.append("\n" + optSet.get(i).toString());

		}
		String priceFinal = String.format("%.2f", totalPrice);
		str.append("\n\nTotal Price: $" + priceFinal + "\n");
		return str.toString();
	}

}
