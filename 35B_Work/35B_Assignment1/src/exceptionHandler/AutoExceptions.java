/**
 * @author Aaron Wong
 * CIS 35B Lab 2
 * AutoExceptions.java
 * 
 * This class will catch and log exceptions found by the FileIO class.
 * Error
 */

package exceptionHandler;

import java.io.*;
import java.util.*;

@SuppressWarnings("serial")
public class AutoExceptions extends Exception {
	/* Member Variables */
	private int errorNum;
	private String errorName;

	/* Constructors */
	public AutoExceptions() {
		super();
		errorNum = 0;
		errorName = "Default AutoException";
	}

	public AutoExceptions(Exception e) {
		super();
		identifyException(e);
	}

	/* Getter Methods */
	public int getErrorNum() {
		return errorNum;
	}

	public String getErrorName() {
		return errorName;
	}

	/* Setter Methods */
	public void setErrorNum(int num) {
		errorNum = num;
	}

	public void setErrorName(String name) {
		errorName = name;
	}

	/* Class Methods */
	/**
	 * Method printAutoExceptionError()
	 * Prints and logs the custom exception error message to a text file "Log.txt"
	 */
	public void printAutoExceptionError() {
		FileWriter wr;
		java.util.Date date = new java.util.Date();
		try {
			wr = new FileWriter("Log.txt", true);
			BufferedWriter bw = new BufferedWriter(wr);
			String errMsg = "AutoException Error occured--Error name: " + errorName + ", Error Number: " + errorNum;
			System.out.println(errMsg);
			bw.append(date + "|" + errMsg + "\n");
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Method identifyException()
	 * Uses an input of a generic Exception class and uses the instanceof keyword to identify
	 * what type of exception it is.
	 * If the type is found, it sets the errorNum and errorName then prints the error message.
	 * @param e
	 */
	public void identifyException(Exception e) {
		if (e instanceof FileNotFoundException) {
			errorNum = 100; // Error numbers between 100 and 199 are of the File type.
			errorName = "FileNotFoundException";
		} else if (e instanceof NumberFormatException) {
			errorNum = 200; // Error Numbers between 200 and 299 are related to numbers.
			errorName = "NumberFormatException";
		} else if (e instanceof IOException) {
			errorNum = 300;
			errorName = "IOException";
		} else if (e instanceof NullPointerException) {
			errorNum = 400;
			errorName = "NullPointerException";
		} else if (e instanceof InputMismatchException) {
			errorNum = 500;
			errorName = "InputMismatchException";
		}
		printAutoExceptionError();
	}
}
