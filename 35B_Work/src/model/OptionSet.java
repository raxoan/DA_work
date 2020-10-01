/*
 * Aaron Wong
 * 9/27/2020
 * CIS35B - Assignment 1
 * OptionSet.java
 */

package model;

public class OptionSet {
	
	public static void main(String[] args) {
		OptionSet arrThing = new OptionSet("Poop array", 4); // testing purposes
		Option thing = new Option("Poop", 25);
		arrThing.addOption(thing);
		System.out.println(arrThing.toString());
	}

	/* Inner Class */
	public static class Option {

		/* Member Variables */
		private String name;
		private float price;

		/* Constructors */
		Option() {
			name = "Default name";
			price = -69;
		}

		Option(String name) {
			this(name, 0);
		}

		Option(float price) {
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

		/* Instance methods */

		/* Overridden Methods */
		@Override
		public String toString() {
			String priceF = String.format("%.2f", price); 
			StringBuffer str = new StringBuffer("Option: " + name + "\nPrice: $" + priceF);
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
	private float price, optPrices;

	/* Constructors */
	// Default
	public OptionSet() {
		this.opt = new Option[1];
		this.name = "Name unknown";
		this.count = 0;
//		this.optPrices = 0;
	}

	// Construct an Object
	public OptionSet(String name, float price) {
		this(new Option(name, price));
	}
	
	// One parameter
	OptionSet(Option[] opt) {
		this("Name unknown", opt);
	}

	public OptionSet(String name) {
		this(name, new Option[1]);
	}
	
	// Super constructor
	OptionSet(Option o) {
		super();
	}
	
//	OptionSet(float optPrices) {
//		this("Name Unknown", new Option[1], optPrices);
//	}

//	// Two Parameters
//	OptionSet(String name, Option[] opt) {
//		this(name, opt, 0);
//	}
//	
//	public OptionSet(String name, float optPrices) {
//		this(name, new Option[1], optPrices);
//	}
//	
//	OptionSet(Option[] opt, float optPrices) {
//		this("Name unknown", opt, optPrices);
//	}
	
	// Two parameters
	OptionSet(String name, Option[] opt) {
		this.opt = opt;
		this.name = name;
//		this.optPrices = optPrices;
	}
	
	public OptionSet(String name, int num) {
		this.name = name;
		this.opt = new Option[num];
		for (int i = 0; i < num; i++) {
			opt[i] = new Option();
			count++;
		}
	}

	/* Getter Methods */
	protected Option[] getOptions() {
		return opt;
	}

	protected String getName() {
		return name;
	}

//	protected float getPrices() {
//		return optPrices;
//	}

	/* Setter Methods */
	protected void setName(String name) {
		this.name = name;
	}

	protected void setTotalPrice() {
		float temp = 0;
		for (int i = 0; i < opt.length; i++) {
			temp += opt[i].getPrice();
		}
		optPrices = temp;
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
	
	protected void setFinalOption(int pos) { // select single option, remove all other options from list
		Option[] temp = new Option[1];
		temp[1] = opt[pos];
		opt = temp;
	}
	protected void addOption(Option addOpt) {
		if (count + 1 >= opt.length) {
			Option[] tempOpt = new Option[opt.length * 2];
			for (int j = 0; j < count; j++) {
				tempOpt[j] = opt[j];
			}
			opt = tempOpt;
		}
		opt[count+1] = addOpt;
		count++;
	}

//	protected void addOption(Option addOpt) {
//		for (int i = 0; i < opt.length; i++) { // check to see if there is any available space in current optset[]
//			if (opt[i] == null) {
//				opt[i] = addOpt;
//				i = opt.length; // end loop because an option was added
//			} else { // when there's no more room in the array
//				Option[] temp = new Option[opt.length + 1]; // create a temp array for adding new option
//				for (int j = 0; j < opt.length; i++) { // copy current opt[] into temp
//					temp[j] = opt[j];
//				}
//				temp[temp.length - 1] = addOpt; // add newest option to the end of temp[]
//				opt = temp; // copy temp array into member variable;
//			}
//				
//			}
//	}
//		if (opt.length == 1) {
//			opt[1] = addOpt;
//		} else {
//			Option[] temp = new Option[opt.length + 1]; // create a temp array for adding new option
//			for (int i = 0; i < opt.length; i++) { // copy current opt[] into temp
//				temp[i] = opt[i];
//			}
//			temp[temp.length - 1] = addOpt; // add newest option to the end of temp[]
//			opt = temp; // copy temp array into member variable;
//		}
	

	protected void deleteOption(int pos) { // delete based on index pos provided
//		int find = findOption(delOpt);
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
	 * @Param Option o; the option in an optionset that the user wants to keep over other options
	 * creates a temp option[] of size 1 with the @param as the only option in the array
	 * replaces the opt in the class with temp.
	 */
	protected void deleteAllOther(Option o) {
		Option[] temp = new Option[1];
		temp[0] = opt[findOption(o)];
		opt = temp;
	}

	protected void updateOption(Option update, int pos) {
		opt[pos] = update; // reassign the object at index pos to new update option
	}

	/* Overridden Methods */
	@Override
	public String toString() {
		StringBuffer str = new StringBuffer("Option Array Name: " + name);
		for (int i = 0; i < opt.length; i++) {
			if (opt[i] != null) {
				str.append("\n" + opt[i].toString()); // append all options to stringbuffer
			}
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

	protected float getPrices() {
		// TODO Auto-generated method stub
		return price;
	}

}
