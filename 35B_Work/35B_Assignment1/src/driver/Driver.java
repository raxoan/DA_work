/*
 * @Author: Aaron Wong
 * CIS 35B - Assignment 3
 * Driver.java
 */
package driver;

import java.io.*;
import java.util.LinkedHashMap;

import exceptionHandler.*;
import model.*;
import util.*;

public class Driver {
	
	/**
	 * Main method for building Automobile class using internal class.
	 * Main input files consist of formatted text files that contain the options that the user selects for their vehicle
	 * @param args
	 * @throws AutoExceptions
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException, AutoExceptions {
		LinkedHashMap<Integer, Automobile> cars = new LinkedHashMap<>();
		int itr = 0; // iterator to track the number of Automobiles added to the hashmap
		
		String fileName = "Automobile.txt"; // construct the base model of the Ford ZTW
		Automobile FordZTW = FileIO.buildAutoObject(fileName);
		cars.put(itr++, FordZTW);
		
		Automobile FordZTW2 = FileIO.buildAutoObject("Automobile2.txt"); // build and add customized FordZTW
		cars.put(itr++, FordZTW2);
		
		Automobile FordZTW3 = FileIO.buildAutoObject("Automobile3.txt"); // build and add another customized FordZTW
		cars.put(itr++, FordZTW3);

		Automobile Tesla3 = FileIO.buildAutoObject("Tesla.txt"); // build and add a new Automobile: Tesla Model 3
		cars.put(itr++, Tesla3);
		
		Automobile HondaCivic = FileIO.buildAutoObject("HondaCivic.txt"); // build and add another new Automobile: Honda Civic
		cars.put(itr++, HondaCivic);
		
		try { // serializes a file based on the LinkedHashMap of Automobiles
			FileOutputStream fileOut = new FileOutputStream("AutoList.txt");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(cars);
			out.close();
			fileOut.close();
			System.out.println("\nFinished Serialization of file.\n");
			FileInputStream fileIn = new FileInputStream("AutoList.txt");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			try {
				LinkedHashMap<String, Automobile> newHashMap = (LinkedHashMap<String, Automobile>) in.readObject();
				System.out.println("Deserialization of file complete.\n");
				System.out.print(newHashMap.toString());
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
