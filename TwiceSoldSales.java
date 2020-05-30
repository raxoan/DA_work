
/**
 * TwiceSoldTales.java
 * Note: the name of an awesome
 * used bookstore in Seattle, WA!
 * @author Fang Zheng
 * @author Aaron Wong
 * CIS 36B, Lab 13
 */

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;

public final class TwiceSoldTales implements Catalogue { // update the signature to inherit from Catalogue
	private ArrayList<Book> books = new ArrayList<Book>();
	private static final String filename = "books.txt";

	public static void main(String[] args) throws IOException {
		TwiceSoldTales t = new TwiceSoldTales();
		String title, author, isbn;
		double price;
		int numCopies;
		File file = new File(filename);
		Scanner input = new Scanner(file);

		t.populateCatalogue(input);
		input.close();
		input = new Scanner(System.in);
		t.bubbleSort();

		System.out.println("Welcome to Twice Sold Tales!\n");
		System.out.println("We currently have " + Book.getNumBooks() + " books in stock!");

		int pos;
		Book b;
		String choice = "";
		DecimalFormat df = new DecimalFormat("0.00");

		while (!choice.equals("X")) {
			t.printMenu();
			System.out.print("Enter your choice: ");
			choice = input.nextLine().toUpperCase();
			final double P = .25;

			if (choice.equals("A")) {
				System.out.println("\nEnter the book information below:\n");
				System.out.print("Title: ");
				title = input.nextLine();
				System.out.print("Author: ");
				author = input.nextLine();
				b = new Book(title, author);
				pos = t.binarySearch(b);

				if (pos != -1) {
					if (t.books.get(pos).availableToPurchase()) {
						System.out.println("\nWe have " + t.books.get(pos).getTitle() + " in stock!");
						System.out.println(t.books.get(pos));

						System.out.print("\nWould you like to purchase it (y/n): ");
						String yn = t.getValidYOrN(input);
						if (yn.equals("y")) {
							t.books.get(pos).decreaseCopies();
							t.books.get(pos).updatePrice();
							System.out.println("Thank you for your purchase!");
						}
					} else {
						System.out.println("Sorry, that book is currently out of stock.");
					}
				} else {
					System.out.println("Sorry! We don't carry that title right now.");
				}
			} else if (choice.equals("B")) {
				System.out.println("\nEnter the book information below:\n");
				System.out.print("Title: ");
				title = input.nextLine();
				System.out.print("Author: ");
				author = input.nextLine();
				b = new Book(title, author);
				pos = t.binarySearch(b);

				if (pos != -1) {
					System.out.println(
							"Thank you! We will purchase the book for: $" + df.format(t.books.get(pos).getPrice() * P));
					numCopies = 1;
					t.books.get(pos).updateNumCopies(numCopies);
				} else {
					System.out.print("\nISBN #: ");
					isbn = input.nextLine();
					System.out.print("\nPlease enter the price you paid: $");
					price = t.getValidPrice(input);
					System.out.println("\nThank you! We will purchase the book for $" + df.format(price * P));
					numCopies = 1;
					b = new Book(title, author, price, isbn, numCopies);
					t.books.add(b);
					t.bubbleSort();
				}
			} else if (choice.equals("C")) {
				System.out.println("Current Selection of Books:\n");
				t.printStock();
			} else if (choice.equals("X")) {
				System.out.println("\nPlease come again!");
				break;
			} else {
				System.out.println("\nInvalid Option!\n");
			}
		}
		input.close();

	}

	/**
	 * Reads from a file and populates the catalogue
	 * 
	 * @param input the Scanner used to read in the data
	 */
	@Override
	public void populateCatalogue(Scanner input) throws IOException {
		String title, author, isbn;
		double price;
		int numCopies;
		while (input.hasNextLine()) {
			title = input.nextLine();
			author = input.nextLine();
			price = input.nextDouble();
			input.nextLine();
			isbn = input.nextLine();
			numCopies = input.nextInt();
			Book b = new Book(title, author, price, isbn, numCopies);
			Book.updateNumBooks(numCopies);
			books.add(b);
			if (input.hasNextLine()) {
				input.nextLine();
				input.nextLine();
			}
		}
	}

	/**
	 * Searches for b in the catalogue
	 * 
	 * @param b the Book to locate
	 * @return the location of b in the catalogue
	 */
	@Override
	public int binarySearch(Book b) {
		int mid, low = 0, high = books.size() - 1;
		while (low <= high) {
			mid = (low + high) / 2;
			if (books.get(mid).equals(b)) {
				return mid;
			} else if (b.compareTo(books.get(mid)) < 0) {
				high = mid - 1;
			} else {
				low = mid + 1;
			}
		}
		return -1;
	}

	/**
	 * Sorts the catalogue into ascending order
	 */
	@Override
	public void bubbleSort() {
		for (int i = 0; i < books.size() - 1; i++) {
			for (int j = 0; j < books.size() - i - 1; j++) {
				if (books.get(j).compareTo(books.get(j + 1)) > 0) {
					Book temp = books.get(j);
					books.set(j, books.get(j + 1));
					books.set(j + 1, temp);
				}
			}
		}
	}

	/**
	 * Prints a menu of options to interact with the catalogue
	 */
	@Override
	public void printMenu() {
		System.out.println("\nPlease select from one of the options:\n");
		System.out.println("A. Search for a book to purchase" + "\nB. Sell a book\nC. Print catalogue\nX. Exit\n");
	}

	/**
	 * Prints out the current catalogue
	 */
	@Override
	public void printStock() {
		for (int i = 0; i < books.size(); i++) {
			System.out.println(books.get(i) + "\n");
		}
	}

	/**
	 * Validates the input to make sure get y or n
	 * 
	 * @param input the Scanner object
	 * @return y or n
	 */
	public String getValidYOrN(Scanner input) {
		String y_n = input.nextLine().toLowerCase();
		while (!y_n.equals("y") && !y_n.equals("n")) {
			System.out.println("Error. Input must be y or n, ignore case, with no empty space");
			System.out.print("\nAnother seat (y/n)?: ");
			y_n = input.nextLine().toLowerCase();
		}
		return y_n;
	}

	/**
	 * Checks if a value is numeric
	 * 
	 * @param val the String value to check
	 * @return true if the string value is numeric, false otherwise
	 */
	public boolean isNumeric(String val) {
		boolean haveDP = false; // Check decimal point
		for (int i = 0; i < val.length(); i++) {
			if (val.charAt(i) == '.') {
				if (haveDP)
					return false;
				else
					haveDP = true;
			} else if (val.charAt(i) < '0' || val.charAt(i) > '9') {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Validates input to get a double value
	 * 
	 * @param input the Scanner
	 * @return an double value
	 */
	public double getValidPrice(Scanner input) {
		boolean needValue = true;
		double n = 0;
		while (needValue) {
			String s = input.nextLine();
			int len = s.length();
			int idx = -1, n1 = 0, n2 = 0;
			if (!isNumeric(s) || len < 1) {
				System.out.println("Error. Value must be a non-negative number, with no empty space\n");
				System.out.print("\nPlease enter the price you paid: $");
			} else {
				idx = s.indexOf('.'); // Or below:
				// for (int i = 0; i < s.length(); i++) {
				// if (s.charAt(i) == '.') {
				// idx = i;
				// break;
				// }
				// }
				if (idx == -1) {
					for (int i = len - 1; i >= 0; i--) {
						n += (int) (s.charAt(i) - 48) * Math.pow(10, len - i - 1);
					}
				} else {
					String s1 = s.substring(0, idx);
					String s2 = s.substring(idx + 1);
					int len1 = s1.length(), len2 = s2.length();

					for (int i = len1 - 1; i >= 0; i--) {
						n1 += (int) (s1.charAt(i) - 48) * Math.pow(10, len1 - i - 1);
					}
					for (int i = len2 - 1; i >= 0; i--) {
						n2 += (int) (s2.charAt(i) - 48) * Math.pow(10, len2 - i - 1);
					}
					n = n1 + n2 * Math.pow(0.1, len2);
				}
				needValue = false;
			}
		}
		return n;
	}

}
