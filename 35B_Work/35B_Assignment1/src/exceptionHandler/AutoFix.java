/**
 * @author Aaron Wong
 * CIS 35B - Lab 2
 * AutoFix.java
 * This class holds the methods that call the fixer classes to fix any potential exceptions.
 * Only fixes one exception: FileNotFoundException.
 */

package exceptionHandler;

import java.io.*;

public class AutoFix {
	private String fileName;
//	= "D:\\Libraries\\GitHub\\DA_work\\35B_Work\\35B_Assignment1\\Automobile.txt";
	
	public AutoFix() {}
	
	public AutoFix(String fileName) {
		super();
		this.fileName = fileName;
	}
	
	public String getFileName() {
		return fileName;
	}
	/**
	 * Method openFile()
	 * Purpose is to make sure the file that is being opened exists and matches the primary text file: "Automobile.txt"
	 * @return boolean
	 * @throws AutoExceptions
	 * @throws IOException
	 */
	public boolean openFile() throws AutoExceptions, IOException {
		boolean check = false;
		while (check == false) { 
				try {
				@SuppressWarnings("unused")
				FileInputStream a1 = new FileInputStream(fileName);
				System.out.println("File Name: " + fileName + " Exists");
				check = true;
			} catch (Exception e) {
				fixCase(e);
			}
		}
		return check;
	}

	/**
	 * Method fixCase()
	 * Used to identify and call the fixer classes to fix exceptions that are found.
	 * Currently can only fix a FileNotFoundException
	 * The other exception that is likely to be caught based on current implementation of BuildAuto() is a NumberFormatException
	 * This exception can occur in every line after the FileReader is successfully instantiated.
	 * @param e
	 * @throws AutoExceptions
	 * @throws IOException
	 */
	public void fixCase(Exception e) throws AutoExceptions, IOException {
		AutoExceptions e1 = new AutoExceptions(e);
		int num = e1.getErrorNum();
		switch (num) {
			case 100:
				FileFix ff = new FileFix(); 
				fileName = ff.fileNameFix(); 
				System.out.println("***FileNotFoundException fixed***");
				break;
			case 200:
				System.out.println("//Unfixable error//");
				break;
			case 300:
				System.out.println("//Unfixable error//");
				break;
			case 400:
				System.out.println("//Unfixable error//");
				break;
			case 500:
				System.out.println("//Unfixable error//");
				break;
		}
	}

}
