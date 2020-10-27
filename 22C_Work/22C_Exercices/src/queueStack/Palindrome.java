/**
 * @author Aaron Wong
 * CIS 22C - Queue & Stack Exercise
 * Palindrome.java
 * 
 * Program uses LinkedQueues and LinkedStacks to see if a word is a palindrome or not.
 * Technically doesn't need the LinkedQueue since it just contains the word in its normal order.
 */

package queueStack;

public class Palindrome {

	public static void main(String[] args) {
		LinkedQueue<String> queue = new LinkedQueue<String>();
		LinkedStack<String> stack = new LinkedStack<String>();

		String palindrome = "racecar";
		String notPalindrome = "tomato";
		String longPalindrome = "Are we not pure? “No, sir!” Panama’s moody Noriega brags. “It is garbage!” Irony dooms a man—a prisoner up to new era";
		String emptyWord = "";

		palindromeCheck(palindrome, queue, stack);

		queue.clear();
		stack.clear();

		palindromeCheck(notPalindrome, queue, stack);

		queue.clear();
		stack.clear();
		
		palindromeCheck(longPalindrome, queue, stack);
		
		queue.clear();
		stack.clear();
		
		palindromeCheck(emptyWord, queue, stack);
	}

	/**
	 * Creates a queue structure with each node containing each letter of a given
	 * word/phrase
	 * 
	 * @param queue LinkedQueue
	 * @param str   the word to be equeued into the queue
	 */
	public static void makeQueue(LinkedQueue<String> queue, String str) {
		for (int i = 0; i < str.length(); i++) {
			queue.enqueue(str.substring(i, i + 1));
		}
	}

	/**
	 * Creates a stack structure with each node containing each letter of a given
	 * word/phrase
	 * 
	 * @param stack LinkedStack
	 * @param str   the word to be pushed into the stack
	 */
	public static void makeStack(LinkedStack<String> stack, String str) {
		for (int i = 0; i < str.length(); i++) {
			stack.push(str.substring(i, i + 1));
		}
	}

	/**
	 * Given a word/phrase, creates a stack and queue data structure to see if the word is
	 * spelled the same way forwards and backwards
	 * 
	 * @param palindrome the word to be checked
	 * @param queue      the LinkedQueue data structure
	 * @param stack      the LinkedStack data structure
	 */
	public static void palindromeCheck(String palindrome, LinkedQueue<String> queue, LinkedStack<String> stack) {
		makeQueue(queue, palindrome);
		makeStack(stack, palindrome);
		String queuePalindrome = removeSpecialChar(queue.toString());
		String stackPalindrome = removeSpecialChar(stack.toString());
		System.out.println("String: " + palindrome);
		System.out.println("Queue of word: " + queuePalindrome);
		System.out.println("Stack of word: " + stackPalindrome);
		System.out.println("Is this a palindrome? " + queuePalindrome.equalsIgnoreCase(stackPalindrome));
	}
	
	/**
	 * Given a string, removes all whitespace and non alphanumeric characters.
	 * @param word
	 * @return
	 */
	public static String removeSpecialChar(String word) {
		String trimmed = word;
		trimmed = word.trim(); // remove all whitespace
		trimmed = word.replaceAll("[^a-zA-Z0-9]",""); // remove all non alphanumeric characters
		return trimmed;
	}
}
