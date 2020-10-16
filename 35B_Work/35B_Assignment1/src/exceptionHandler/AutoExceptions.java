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
import java.util.ArrayList;

public class AutoExceptions extends Exception {
	/* Member Variables */
	private int errorNum;
	private String errorName, fileName = "ExceptionLog.txt";
//	private Exception exc;
	
	
	/* Constructors */
	public AutoExceptions() {
		super();
		errorNum = 0;
		errorName = "Default AutoException";
//		printAutoExceptionError();
	}
	
	public AutoExceptions(Exception e) {
		super();
		identifyException(e);	
	}
	
	public AutoExceptions(String message) {
		super();
		errorName = message;
	}
	
	public AutoExceptions(int num, String name) {
		super();
		errorNum = num;
		errorName = name;
//		identifyException();
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
	public void printAutoExceptionError() {
		System.out.println("AutoException Error occured.\nError name: " + errorName + ", Error Number: " + errorNum);
	}
	
	public void appendAutoExceptionError() {
		
	}
	
	public void identifyException(Exception e) {
		System.out.print("AutoException Error occured.\n");
		if (e instanceof FileNotFoundException) {
			errorNum = 100; // Error numbers between 100 and 199 are of the File type.
			errorName = "FileNotFoundException";
		} else if (e instanceof NumberFormatException) {
			errorNum = 200; // Error Numbers between 200 and 299 are related to numbers.
			errorName = "NumberFormatException";
		} else if (e instanceof IOException) {
			errorNum = 300; 
			errorName = "IOException";
		}
		printAutoExceptionError();
	}
}
