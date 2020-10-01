/* 
 * Aaron Wong
 * 9/29/2020
 * CIS 35B - Assignment 1
 * FileIO.java
 */
package util;

import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

import model.*;

public class FileIO {

	public static void main(String[] args) throws FileNotFoundException {
		FileReader fr = new FileReader("Automotive.txt");
		BufferedReader br = new BufferedReader(fr);
		String autoName, optSetName, allOpt;
//		String[] arrOptSetName, arrOptName;
//		int numOptSet, numOpt;
		float basePrice;
		try {
			// while there are still lines in the text
			autoName = br.readLine(); // first line is autoname
			System.out.println(autoName);
			String numStringTemp;
			numStringTemp = br.readLine();
			basePrice = Float.parseFloat(numStringTemp);// third line is baseprice
			System.out.println("$" + basePrice);
			Automotive auto = new Automotive(autoName, basePrice); // create Automotive object with name and
																	// baseprice
			do { // do-while loop to create and add option & optionSets
				optSetName = br.readLine(); // Name of current option set
//				System.out.println(optSetName);
				OptionSet optSet = new OptionSet(optSetName); // create an optionset with above parameters.
																// Using constructors is not explicitly
																// forbidden according to instructions

				allOpt = br.readLine(); // the name of all of the options
				String[] strArr = allOpt.split(", "); // create an array to hold all separate option names
				String prices = br.readLine(); // read all prices into a string
				String[] priceArr = prices.split(", "); // create a string array of int prices
				int[] intArr = new int[priceArr.length];
				for (int j = 0; j < intArr.length; j++) {
					intArr[j] = Integer.parseInt(priceArr[j]); // parse the integers from a string arr to int arr
				}
				for (int k = 0; k < intArr.length; k++) { // create options objects and add to optionset
					OptionSet.Option opt = new OptionSet.Option(strArr[k], intArr[k]); // create new option
					auto.addOption(optSet, opt); // add option to option[]
					System.out.println("Added option: " + opt.toString());
				}
				auto.addOptSet(optSet);
				System.out.println("Added Option Set: " + optSet.toString());
			} while (br.ready());
			System.out.println("Finished creating Automotive: " + auto.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void readOptionSet() {

	}

	/*
	 * using entire automotive class, build an auto object with specified options
	 * selected (one color, one transmission, etc)
	 * 
	 * @param String filename: name of file
	 * 
	 * @Param Automotive a1: Automotive object created from text file. methods,
	 * removing options that are not wanted
	 */
//	public Automotive buildAutoObject(String filename, Automotive a1) throws FileNotFoundException {
////		Automotive auto = new Automotive(a1);
//
//		return auto;
//	}

//	public static void main(String[] args) {
//		try {
//			File file = new File("Automotive.txt");
//			Automotive car = new Automotive(); // Each unique car can have up to 5 options.
//			Scanner scan = new Scanner(file);
//			while (scan.hasNext("Ford Wagon ZTW")){ // create options & optionsets based on file input
//
//				scan.nextLine(); // skip past the car name line
//				String[] optionNames = new String[]{"Color", "Transmission", "Brakes", "Air Bags", "Moonroof"};
//				
//				while (scan.hasNextLine()) {
//					OptionSet optSet = new OptionSet();
//					
//					String str;
//					float price;
//					for (int i = 0; i < optionNames.length; i++) {
//						str = scan.nextLine();
//						price = scan.nextFloat();
//						OptionSet.Option opt = new OptionSet.Option(str, price); 
//						
//					}
//					
//				}
//			}
//			
//			
//		} catch (FileNotFoundException e) {
//			e.toString();
//		}
//	}
}
