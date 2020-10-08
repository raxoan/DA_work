
/**
 * The counter class implements a counter that will roll over to the initial
 * value when it hits the maximum value.
 * 
 * @author Aaron Wong CIS 22C - Lab 1 Counter.java
 */
public class Counter {
	// PUT PRIVATE DATA FIELDS HERE
	private int count, min, max;
	private boolean rollOver;

	/**
	 * The default constructor for objects of class Counter. Minimum is 0 and the
	 * maximum is the largest possible integer.
	 */
	public Counter() {
		// ADD CODE FOR THE CONSTRUCTOR
		count = 0;
		min = 0;
		max = Integer.MAX_VALUE;
	}

	/**
	 * The alternate constructor for objects of class Counter. The minimum and
	 * maximum values are given as parameters. The counter starts at the minimum
	 * value.
	 * 
	 * @param min The minimum value that the counter can have
	 * @param max The maximum value that the counter can have
	 */
	public Counter(int min, int max) {
		// ADD CODE FOR THE ALTERNATE CONSTRUCTOR
		if (min >= max) {
			throw new CounterInitializationException("min is not less than max.");
		} else {
			count = min;
			this.min = min;
			this.max = max;
		}
	}

	/**
	 * Determine if two counters are in the same state
	 *
	 * @param otherObject the object to test against for equality
	 * @return true if the objects are in the same state
	 */
	public boolean equals(Object otherObject) {
		if (this == otherObject) {
			return true;
		} else if (!(otherObject instanceof Counter)) {
			return false;
		} else {
			Counter c = (Counter) otherObject; // cast otherObject as type Counter
			return count == c.count && min == c.min && max == c.max && rollOver == c.rollOver;
		}
	}

	/**
	 * Increases the counter by one
	 */
	public void increase() {
		// ADD CODE TO INCREASE THE VALUE OF THE COUNTER
		if (count == max) { // count is allowed to be at max value, if it were to go over, it gets set to
							// min;
			count = min;
			rollOver = true; // count rolled over
		} else {
			count++;
			rollOver = false;
		}
	}

	/**
	 * Decreases the counter by one
	 */
	public void decrease() {
		// ADD CODE TO INCREASE THE VALUE OF THE COUNTER
		if (count == min) { // if count is currently at min, set to max and update rollOver
			count = max;
			rollOver = true;
		} else {
			count--;
			rollOver = false;
		}
	}

	/**
	 * Get the value of the counter
	 *
	 * @return the current value of the counter
	 */
	public int value() {
		// CHANGE THE RETURN TO GIVE THE CURRENT VALUE OF THE COUNTER
		return count;

	}

	/**
	 * Accessor that allows the client to determine if the counter rolled over on
	 * the last count
	 *
	 * @return true if the counter rolled over
	 */
	public boolean rolledOver() {
		// CHANGE THE RETURN TO THE ROLLOVER STATUS OF THE COUNTER
		return rollOver;
	}

	/**
	 * Override the toString method to provide a more informative description of the
	 * counter
	 *
	 * @return a descriptive string about the object
	 */
	public String toString() {
		// CHANGE THE RETURN TO A DESCRIPTION OF THE COUNTER
		return "Count is: " + count + "\nMinimum value: " + min + "\nMaximum value: " + max + "\nRollover status = "
				+ rollOver;
	}

}
