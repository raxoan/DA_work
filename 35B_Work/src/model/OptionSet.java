/*
 * Aaron Wong
 * 9/27/2020
 * CIS35B - Assignment 1
 * OptionSet.java
 */

package model;

import java.io.Serializable;

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
	private Option[] opt;
	private int count;
	private float price;

	/* Constructors */
	// Default
	public OptionSet() {
		this.opt = new Option[1];
		this.name = "Name unknown";
		this.count = 0;
//		this.optPrices = 0;
	}

	// Construct an Object by calling the parent constructor
	public OptionSet(String name, float price) {
		this(new Option(name, price));
	}
	
	// One parameter
	public OptionSet(Option[] opt) {
		this("Name unknown", opt);
	}

	public OptionSet(String name) {
		this(name, new Option[1]);
	}
	
	// Super constructor
	public OptionSet(Option o) {
		super();
	}
	
	// Two parameters
	OptionSet(String name, Option[] opt) {
		this.opt = opt;
		this.name = name;
	}
	
	// Creates an optionset of size num with name
	public OptionSet(String name, int num) {
		this.name = name;
		this.opt = new Option[num];
		for (int i = 0; i < num; i++) {
			opt[i] = new Option();
//			count++;
		}
	}

	/* Getter Methods */
	protected Option[] getOptions() {
		return opt;
	}

	protected String getName() {
		return name;
	}
	

	protected float getPrices() {
		return price;
	}

	protected int getCount() {
		return count;
	}

	/* Setter Methods */
	protected void setName(String name) {
		this.name = name;
	}
	
	protected void setOpt(Option[] opt) {
		this.opt = opt;
	}
	
	protected void setCount(int count) {
		this.count = count;
	}
	
	protected void setPrice(float price) {
		this.price = price;
	}
	
	protected void setFinalOption(int pos) { // select single option, remove all other options from list
		Option[] temp = new Option[1];
		temp[1] = opt[pos];
		opt = temp;
	}
	
	/* Instance methods */
	protected int findOption(Option opt) {
		for (int i = 0; i < this.opt.length; i++) {
			if (this.opt[i].equals(opt)) { // search array of options for specified option
				return i;
			}
		}
		return -1; // return -1 if not found
	}
	
	
	protected void addOption(Option addOpt) {
		if (count + 1 >= opt.length) {
			Option[] tempOpt = new Option[opt.length * 2];
			for (int j = 0; j < count; j++) {
				tempOpt[j] = opt[j];
			}
			opt = tempOpt;
		}
		opt[count] = addOpt;
		count++;
	}

	/*
	 * Method deleteOption();
	 * Pre-requisite: findOption() method to return an int value, the index position of an Option in an array;
	 * @Param int pos;
	 * 
	 */
	protected void deleteOption(int pos) { // delete based on index pos provided
		if (pos != -1) {
			Option[] temp = new Option[opt.length - 1]; // create copy of opt[] that is 1 unit smaller
			for (int i = pos; i < opt.length; i++) { // starting at the index at which the option is found
				opt[i] = opt[i + 1]; // simple removal by copying in the next object in the array
			}
			for (int j = 0; j < temp.length; j++) {
				temp[j] = opt[j]; // copy the opt[] into a temp[] since it has the correct array size
			}
			opt = temp; // replace the old opt array with the clean version
		} else {
			throw new IndexOutOfBoundsException();
		}
	}
	
	
	/*
	 * deleteAllOther();
	 * @Param int pos; the pos in an optionset that the user wants to keep over other options
	 * creates a temp option[] of size 1 with the @param as the only option in the array
	 * replaces the opt in the class with temp.
	 */
	protected void deleteAllOther(int pos) {
		Option[] temp = new Option[1];
		temp[0] = opt[pos];
		opt = temp;
	}

	/*
	 * Method: updateOption();
	 * @Param Option update; an instance of the Option class
	 * @Param int pos; the position within the Option[] array where the param option will go
	 */
	
	protected void updateOption(Option update, int pos) {
		opt[pos] = update; // reassign the object at index pos to new update option
	}

	
	/* Overridden Methods */
	@Override
	public String toString() {
		StringBuffer str = new StringBuffer(name);
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
