/**
 * @author Aaron
 * CIS 35B Lab 2
 * AutoExceptions.java
 * 
 * This class will catch and log exceptions found by the FileIO class.
 * Error
 */

package exceptionHandler;

import java.io.*;

public class AutoExceptions extends Exception {
	/* Member Variables */
	private int errorNum;
	private String errorName;
	private String fileName = "ExceptionLog.txt";
	FileWriter file = new FileWriter(fileName);
	BufferedWriter out = new BufferedWriter(file);
	
	
	/* Constructors */
	public AutoExceptions() {
		super();
		errorNum = 0;
		errorName = "Default AutoException";
		printAutoExceptionError();
	}
	
	public AutoExceptions(int num, String name) {
		super();
		errorNum = num;
		errorName = name;
		printAutoExceptionError();
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
	public void printAutoExceptionError() throws IOException {
		String error = "AutoException Error occured.\nError name: " + errorName + ", Error Number: " + errorNum;
		System.out.println(error);
		out.write(error);
	}
	
	public void appendAutoExceptionError() {
		
	}
	
}
