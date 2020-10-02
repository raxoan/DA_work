/*
 * @Author: Aaron Wong
 * CIS 35B - Assignment 1
 * Driver.java
 */
package driver;

import java.io.*;

import model.*;
import util.*;

public class Driver {

	public static void main(String[] args) throws FileNotFoundException {
		String fileName = "Automotive.txt";
		Automotive FordZTW = FileIO.buildAutoObject(fileName);
		System.out.println(FordZTW.toString());
		
		try {
			FileOutputStream fileOut = new FileOutputStream("FordZTW.txt");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(FordZTW);
			out.close();
			fileOut.close();
			System.out.println("\nFinished Serialization of file.\n");
			
			FileInputStream fileIn = new FileInputStream("FordZTW.txt");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			try {
				Automotive newFordZTW = (Automotive) in.readObject();
				System.out.println("Deserialization of file complete.\n");
				System.out.print(newFordZTW.toString());
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
}
