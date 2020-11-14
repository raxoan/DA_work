package driver;

import java.util.LinkedHashMap;

import scale.*;	
import adapter.*;

public class ScaleDriver {
	public static void main(String[] args) {
		BuildAuto build = new BuildAuto();
		String fileName = "AutomobileEdit.txt"; 
		
		build.buildAuto(fileName);
		LinkedHashMap<String, BuildAuto> scaleCars = new LinkedHashMap<>();
		scaleCars.put(fileName, build);
		// In future, these constructors can be based off of file input, rather than coded into the constructor
		EditOptions edit = new EditOptions("Color", "Fort Knox Gold Clearcoat Metallic", "Blue", scaleCars.get(fileName));
		EditOptions edit2 = new EditOptions("Color", "Fort Knox Gold Clearcoat Metallic", "Red", scaleCars.get(fileName));
		EditOptions edit3 = new EditOptions("Brakes", "Standard", "Real good", scaleCars.get(fileName));
		EditOptions edit4 = new EditOptions("Moonroof", "yes", "this method should not work", scaleCars.get(fileName));
		edit2.start(); 	// first test to change 'color' option
		edit.start(); 	// test to see if synchronization works
		edit3.start(); 	// testing EditOptions() on other options
		edit4.start();	// testing EditOptions() when parameters are incorrect
	}
}
