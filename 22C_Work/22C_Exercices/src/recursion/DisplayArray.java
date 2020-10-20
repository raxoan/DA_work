/**
 * @author Aaron Wong
 * CIS 22C - Recursion Exercise 1
 * DisplayArray.java
 */

package recursion;

public class DisplayArray {
	/* Member Variables */
	private int array[]= {25,96,87,41};
	private LinkedStack<Integer> stack = new LinkedStack<Integer>();

	public static void main(String[] args) {

		   DisplayArray da = new DisplayArray();
		   da.createStack(da.array);
		   System.out.print("displayArrayRecursively(): ");
		   da.displayArrayRecursively(0,da.array.length-1);
		   System.out.println();
		   da.displayArrayWithStack(0,da.array.length-1);
		}

	private void displayArrayWithStack(int i, int j) {
		System.out.print("displayArrayWithStack(): {");
		while (i < j) {
			System.out.print(stack.pop() + ",");
			i++;
		}
		System.out.print(stack.pop() + "}");
		
	}

	private void displayArrayRecursively(int i, int j) {
		if (i <= j) {
			System.out.print(array[i]+" ");
			displayArrayRecursively(i+1, j);
		}
		
	}

	private void createStack(int[] arr) { // creates a stack that will display in the original order
		for (int i = (array.length - 1); i >= 0; i--) {
			stack.push(arr[i]);
		}
	}

		

	
}
