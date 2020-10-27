/*
 * Aaron Wong
 * CIS35B - Assignment 3
 * OptionSet.java
 */

package model;

import java.io.Serializable;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class OptionSet implements Serializable{

	/* Inner Class */
	public static class Option implements Serializable {

		/* Member Variables */
		private String name;
		private float price;

		/* Constructors */
		public Option() {
			name = "Default name";
			price = 0;
		}

		public Option(String name) {
			this(name, 0);
		}

		public Option(float price) {
			this("name unknown", price);
		}

		public Option(String name, float price) {
			this.name = name;
			this.price = price;
		}

		/* Getter Methods */
		protected String getName() {
			return name;
		}

		protected float getPrice() {
			return price;
		}

		/* Setter Methods */
		protected void setName(String name) {
			this.name = name;
		}

		protected void setPrice(float price) {
			this.price = price;
		}

		/* Overridden Methods */
		@Override
		public String toString() {
			String priceF = String.format("%.2f", price); 
			StringBuffer str = new StringBuffer(name + " | Price: $" + priceF);
			return str.toString();
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) {
				return true;
			} else if (!(o instanceof Option)) {
				return false;
			} else {
				Option op = (Option) o;
				return name.equals(op.name) && price == op.price;
			}
		}
	}

	///////////////////////////////////
	//       End of Inner Class      //
	///////////////////////////////////

	/* Member Variables */
	private String name;
//	private Option[] opt;
	private ArrayList<Option> opt;
//	private int count;
	private float optPrice;

	/* Constructors */
	// Default
	public OptionSet() {
		this.opt = new ArrayList<Option>();
		this.name = "Name unknown";
//		this.count = 0;
//		this.optPrices = 0;
	}

	// Construct an Object by calling the parent constructor
	public OptionSet(String name, float price) {
		this(new Option(name, price));
	}
	
	// One parameter
	public OptionSet(ArrayList<Option> opt) {
		this("Name unknown", opt);
	}

	public OptionSet(String name) {
		this(name, new ArrayList<Option>());
	}
	
	// Super constructor
	public OptionSet(Option o) {
		super();
	}
	
	// Two parameters
	public OptionSet(String name, ArrayList<Option> opt) {
		this.opt = opt;
		this.name = name;
	}
	
	// Creates an optionset of size num with name
//	public OptionSet(String name, int num) {
//		this.name = name;
//		this.opt = new Option[num];
//		for (int i = 0; i < num; i++) {
//			opt[i] = new Option();
////			count++;
//		}
//	}

	/* Getter Methods */
	protected ArrayList<Option> getOptions() {
		return opt;
	}

	protected String getName() {
		return name;
	}
	
	/**
	 * Returns the total price of the option in this optionSet, can be negative.
	 * This optionset may hold one option, in the event of a final product,
	 * or it can hold many options for the base model that contains all possible options
	 * @return optPrice
	 */
	protected float getPrices() {
		for (int i = 0; i < opt.size(); i++) {
			optPrice += opt.get(i).getPrice();
		}
		return optPrice;
	}

	protected int getSize() {
		return opt.size();
	}

	/* Setter Methods */
	protected void setName(String name) {
		this.name = name;
	}
	
	protected void setOpt(ArrayList<Option> opt) {
		this.opt = opt;
	}
	
//	protected void setFinalOption(int pos) { // select single option, remove all other options from list
//		ArrayList<Option> temp = new ArrayList<Option>();
//		temp[1] = opt[pos];
//		opt = temp;
//	}
	
	/* Instance methods */
	protected int findOption(Option opt) {
		if (this.opt.contains(opt)) {
			return this.opt.indexOf(opt);
		}
		return -1; // return -1 if not found
	}
	
	
	protected void addOption(Option addOpt) {
		opt.add(addOpt);
	}

	/*
	 * Method deleteOption();
	 * 
	 * @param int pos;
	 * 
	 */
	protected void deleteOption(int pos) { // delete based on index pos provided
		opt.remove(pos);
	}
	
	
	/*
	 * deleteAllOther();
	 * @Param int pos; the pos in an optionset that the user wants to keep over other options
	 * creates a temp option[] of size 1 with the @param as the only option in the array
	 * replaces the opt in the class with temp.
	 */
//	protected void deleteAllOther(int pos) {
//		Option[] temp = new ArrayList<Option>();
//		temp[0] = opt[pos];
//		opt = temp;
//	}

	/*
	 * Method: updateOption();
	 * @Param Option update; an instance of the Option class
	 * @Param int pos; the position within the Option[] array where the param option will go
	 */
	
//	protected void updateOption(Option update, int pos) {
//		opt[pos] = update; // reassign the object at index pos to new update option
//	}

	
	/* Overridden Methods */
	@Override
	public String toString() {
		StringBuffer str = new StringBuffer(name + "\n" + opt.get(0).getName());
		for (int i = 1; i < opt.size(); i++) {
			str.append(", " + opt.get(i).getName());
		}
		String price1 = String.format("%.2f", opt.get(0).getPrice());
		str.append("\n$" + price1);
		for (int j = 1; j < opt.size(); j++) {
			String priceF = String.format("%.2f", opt.get(j).getPrice());
			str.append(", $" + priceF);
		}
		return str.toString();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		} else if (!(o instanceof OptionSet)) {
			return false;
		} else {
			OptionSet os = (OptionSet) o;
			return this.opt.equals(os.opt) && this.name.equals(os.name);
		}
	}


}
