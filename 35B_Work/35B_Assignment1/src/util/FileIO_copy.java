/* 
 * Aaron Wong
 * 9/29/2020
 * CIS 35B - Assignment 1
 * FileIO.java
 * Contains a copy of FileIO.java class used for Assignment 1.
 */
package util;

import java.io.*;
import model.*;

public class FileIO_copy {
	/*
	 * using entire Automobile class, build an auto object with specified options
	 * selected (one color, one transmission, etc)
	 * 
	 * @param String filename: name of file
	 * 
	 * @Param Automobile a1: Automobile object created from text file. methods,
	 * removing options that are not wanted
	 */
	public static Automobile buildAutoObject(String fileName) throws FileNotFoundException {

		FileReader fr = new FileReader(fileName);
		BufferedReader br = new BufferedReader(fr);
		String autoName, optSetName, allOpt;
		float basePrice;
		Automobile auto = new Automobile();
		try {
			// while there are still lines in the text
			autoName = br.readLine(); // first line is autoname
			auto.setName(autoName);
			String numStringTemp;
			numStringTemp = br.readLine();
			basePrice = Float.parseFloat(numStringTemp);// next line is baseprice
			auto.setBasePrice(basePrice);

			do { // do-while loop to create and add option & optionSets
				optSetName = br.readLine(); // Name of current option set
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
				}
				auto.addOptSet(optSet);
			} while (br.ready());
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return auto;
		
	}

}
