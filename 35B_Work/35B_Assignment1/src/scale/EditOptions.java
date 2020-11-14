package scale;

import java.io.File;

import adapter.*;

public class EditOptions extends BuildAuto implements Runnable {
	// access the LinkedHashMap object
	// synchronization of program

	// create a thread object
	private Thread t;
	private String optSetName;
	private String oldName;
	private String newName;
	private BuildAuto auto;

	// pass values in constructor
	public EditOptions(String optSetName, String oldName, String newName, BuildAuto auto) {
		t = new Thread(this);
		this.optSetName = optSetName;
		this.oldName = oldName;
		this.newName = newName;
		this.auto = auto;
	}

	// constructor that uses an input file for instructions, rather than raw String
	// input
	public EditOptions(File file, BuildAuto auto) {
		t = new Thread(this);
		this.optSetName = "";
		this.oldName = "";
		this.newName = "";
		this.auto = auto;
	}

	public void start() {
		System.out.println("//// Thread started for " + optSetName + "-" + newName + " " + t.toString());
		t.start();
	}

	// run editing of options
	@Override
	public void run() {
		// Based on the option set name in the constructor, update the OptionName to the newName 
		try {
			auto.updateOptionName(optSetName, oldName, newName);
			System.out.println("---run() has completed for " + newName + "---\n");
		} catch (Exception e) {

		}
	}
}
