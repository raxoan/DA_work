import java.io.*;
import java.util.*;

/**
 * Primes is a program that will compute prime numbers using the sieve of
 * Eratosthenes.
 * 
 * @author Aaron Wong
 * CIS 22C - Lab12 ListClient
 * Primes.java
 */

public class Primes {

	public static void main(String args[]) {
		ListInterface<Integer> candidates = new AList<Integer>();
		ListInterface<Integer> primes = new AList<Integer>();
		ListInterface<Integer> composites = new AList<Integer>();
		int max, start = 2, prime;
		
		System.out.println("Please enter the maximum value to test for primality");
		max = getInt("   It should be an integer value greater than or equal to 2.");

		// COMPLETE THE MAIN
		for (int i = start; i <= max; i++) {
			candidates.add(i);
		}
		System.out.println("Candidates:\n" + candidates);
		System.out.println("--------------------");
		while (candidates.getLength() > 0) {
			prime = candidates.getEntry(1); // save number as a variable
			System.out.println("Prime found: " + prime); // print out that it was found
			primes.add(prime); // add to primes list
			getComposites(candidates, composites, prime);
			System.out.println("Candidates:\n" + candidates);
			System.out.println("Primes:\n" + primes);
			System.out.println("Composites:\n" + composites);		
		}
	}
	
	/**
	 * getComposites - Remove the composite values from possibles list and put them
	 * in the composites list.
	 *
	 * @param candidates A list of integers holding the possible values.
	 * @param composites A list of integers holding the composite values.
	 * @param prime      An Integer that is prime.
	 */
	public static void getComposites(ListInterface<Integer> candidates, ListInterface<Integer> composites,
			Integer prime) {
		// COMPLETE THIS METHOD
		candidates.remove(1); // first entry will always be a prime number; remove from candidates list
		int pos = 1;
		while (pos <= candidates.getLength()) {
			if (candidates.getEntry(pos) % prime == 0) { // if candidates[i] is divisible by prime
				composites.add(candidates.getEntry(pos)); // add to composites list
				candidates.remove(pos); // remove from candidates list
			} else {
				pos++; // update position of iterator for candidates list if number is not divisible by prime		
			}
 
		}
	}

	/**
	 * Get an integer value.
	 *
	 * @return An integer.
	 */
	private static int getInt(String rangePrompt) {
		Scanner input;
		int result = 10; // Default value is 10
		try {
			input = new Scanner(System.in);
			System.out.println(rangePrompt);
			result = input.nextInt();

		} catch (NumberFormatException e) {
			System.out.println("Could not convert input to an integer");
			System.out.println(e.getMessage());
			System.out.println("Will use 10 as the default value");
		} catch (Exception e) {
			System.out.println("There was an error with System.in");
			System.out.println(e.getMessage());
			System.out.println("Will use 10 as the default value");
		}
		return result;

	}

}
