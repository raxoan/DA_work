/**
 * @author Aaron Wong
 * CIS 22C - Lab5 - StackClient
 * StackSort.java
 */
import java.util.*;

/**
 * StackSort is a program that will use two stacks to sort an array of integer
 * values.
 * 
 * @author Charles Hoot
 * @version 4.0
 */
public class StackSort {

	public static void main(String args[]) {

		int data[] = null;
		int result[] = null;

		Scanner input;
		input = new Scanner(System.in);

		System.out.println("This program sorts an array of integer values.");

		// Create an empty array of integers
		data = createArray(0, 1, 1);
		System.out.println("Original array is: " + representationOfArray(data));
		result = doStackSort(data);
		System.out.println("Sorted array is: " + representationOfArray(result));
		System.out.println();

		// Create an array with one integer
		data = createArray(1, 0, 9);
		System.out.println("Original array is: " + representationOfArray(data));
		result = doStackSort(data);
		System.out.println("Sorted array is: " + representationOfArray(result));
		System.out.println();

		// Create an array with two integers
		data = createArray(2, 0, 9);
		System.out.println("Original array is: " + representationOfArray(data));
		result = doStackSort(data);
		System.out.println("Sorted array is: " + representationOfArray(result));
		System.out.println();

		// Create an array with 10 integers
		data = createArray(10, 0, 9999);
		System.out.println("Original array is: " + representationOfArray(data));
		result = doStackSort(data);
		System.out.println("Sorted array is: " + representationOfArray(result));
		System.out.println();

		// Create an array with 20 integers
		data = createArray(20, 0, 9);
		System.out.println("Original array is: " + representationOfArray(data));
		result = doStackSort(data);
		System.out.println("Sorted array is: " + representationOfArray(result));
		System.out.println();

		System.out.println("Please enter the number of values to sort");
		int size = getInt("   It should be an integer value greater than or equal to 1.");
		// Create an array of the given size

		data = createArray(size, 0, 99);
		System.out.println("Original array is: " + representationOfArray(data));
		result = doStackSort(data);
		System.out.println("Sorted array is: " + representationOfArray(result));
		System.out.println();

		input.close();
	}

	/**
	 * Pop values from a stack into an array
	 * @author Aaron Wong
	 * 
	 * @param stack Source of integers
	 * @param data  destination array
	 */
	private static void popStack(VectorStack<Integer> stack, int data[]) {
		int count = 0;
		while (!stack.isEmpty()) {
			data[count] = stack.pop();
			count++;
		}
	}

	/**
	 * Use two stacks to sort the data in an array
	 * @author Aaron Wong
	 * 
	 * @param data An array of integer values to be sorted.
	 * @return An array of sorted integers.
	 */
	private static int[] doStackSort(int data[]) {
		VectorStack<Integer> lowerValues = new VectorStack<Integer>();
		VectorStack<Integer> upperValues = new VectorStack<Integer>();
		int result[] = new int[data.length];
		if (data.length < 2) {
			result = data; // if array is of length 0 or 1, it does not need to be sorted.
		} else {
			boolean check = false;
			int pos = 0;
			while (lowerValues.isEmpty() || upperValues.isEmpty()) { // sort and add at least one element into lower and upper
				if (data[pos] < data[pos + 1]) {
					lowerValues.push(data[pos]);
					upperValues.push(data[pos + 1]);
				} else if (data[pos + 1] < data[pos]) {
					lowerValues.push(data[pos + 1]);
					upperValues.push(data[pos]);
				} else { // the first two integers are equal, need to move to the next value
					pos++;
					check = true;
				}
			}
			if (check == true) { // adding any equal-value integers to the lower stack
				for (int i = pos - 1; i >= 0; i--) {
					lowerValues.push(data[i]);
				}
			}
			pos += 2; // first two elements of the array are sorted, so need to shift the iterator
						// position up by 2
			for (int j = pos; j < data.length; j++) { // sorting loop
				if (data[j] < lowerValues.peek()) {
					while (!lowerValues.isEmpty() && data[j] < lowerValues.peek()) {				
						upperValues.push(lowerValues.pop());
					}
					lowerValues.push(data[j]);
				} else if (data[j] >= lowerValues.peek() && data[j] < upperValues.peek()) {
					lowerValues.push(data[j]);
				} else {
					while (!upperValues.isEmpty() && data[j] > upperValues.peek()) {
						lowerValues.push(upperValues.pop());
					}
					upperValues.push(data[j]);
				}
			} // end of sort
			while (!lowerValues.isEmpty()) { // place sorted values into upperValues stack
				upperValues.push(lowerValues.pop());
			}
			popStack(upperValues, result); // pop sorted stack into results
		}

		return result;

	}

	/**
	 * Load an array with data values
	 *
	 * @param size The number of data values to generate and place in the array.
	 * @param min  The minimum value to generate.
	 * @param max  The maximum value to generate.
	 * @return An array of randomly generated integers.
	 */
	private static int[] createArray(int size, int min, int max) {

		Random generator = new Random();

		// If we get a negative size, just make the size 1
		if (size < 0) {
			size = 1;
		}
		// We need max > min for the random number generator to be happy

		if (max <= min) {
			max = min + 1;
		}

		int[] data = new int[size];

		for (int i = 0; i < size; i++) {
			data[i] = min + generator.nextInt(max - min);
		}

		return data;
	}

	/**
	 * Create a string with the data values from an array
	 *
	 * @param data An array of integer values.
	 * @return A string representation of the array.
	 */
	private static String representationOfArray(int data[]) {
		String result = new String("< ");
		for (int i = 0; i < data.length; i++) {
			result += data[i] + " ";
		}
		result += ">";

		return result;
	}

	/**
	 * Get an integer value
	 *
	 * @return An integer.
	 */
	private static int getInt(String rangePrompt) {
		Scanner input;
		int result = 10; // default value is 10
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
