/**
 * A class that represents a rational number.
 * 
 * @author Aaron Wong CIS 22C - Lab 1 Rational.java
 */

public class Rational {
	// PUT PRIVATE DATA FIELDS HERE
	private int numer, denom;
//	private float rNum;

	/**
	 * The default constructor for objects of class Rational. Creates the rational
	 * number 1.
	 */
	public Rational() {
		// ADD CODE TO THE CONSTRUCTOR
		numer = 1;
		denom = 1;
//    	rNum = numer / denom;
	}

	/**
	 * The alternate constructor for objects of class Rational. Creates a rational
	 * number equivalent to n/d.
	 * 
	 * @param n The numerator of the rational number.
	 * @param d The denominator of the rational number.
	 */
	public Rational(int n, int d) {
		// ADD CODE TO THE ALTERNATE CONSTRUCTOR
		// this variable must be a positive number
		if (d == 0) {
			throw new ZeroDenominatorException("Denominator is zero.");
		} else {
			numer = n;
			denom = d;
			normalize();
		}
	}

	/**
	 * Get the value of the Numerator
	 *
	 * @return the value of the numerator
	 */
	public int getNumerator() {
		// CHANGE THE RETURN TO SOMETHING APPROPRIATE
		return numer;
	}

	/**
	 * Get the value of the Denominator
	 *
	 * @return the value of the denominator
	 */
	public int getDenominator() {
		// CHANGE THE RETURN TO SOMETHING APPROPRIATE
		return denom;
	}

	/**
	 * Negate a rational number r
	 * 
	 * @return a new rational number that is negation of this number -r
	 */
	public Rational negate() {
		// CHANGE THE RETURN TO SOMETHING APPROPRIATE
		assert (numer > 0); // cannot negate zero or an already negative number
		Rational r = new Rational(-numer, denom);
		return r;
	}

	/**
	 * Invert a rational number r
	 * 
	 * @return a new rational number that is 1/r.
	 */
	public Rational invert() {
		// CHANGE THE RETURN TO SOMETHING APPROPRIATE
		assert (numer != 0); // cannot invert zero
		Rational r = new Rational(denom, numer);
		return r;
	}

	/**
	 * Add two rational numbers
	 *
	 * @param other the second argument of the add
	 * @return a new rational number that is the sum of this and the other rational
	 */
	public Rational add(Rational other) {
		// ADD NEW CODE AND CHANGE THE RETURN TO SOMETHING APPROPRIATE
		int newD = this.denom * other.getDenominator();
		int newN = (this.numer * other.getDenominator()) + (this.denom * other.getNumerator());
		Rational r = new Rational(newN, newD);
		return r;
	}

	/**
     * Subtract a rational number t from this one r
     *
     * @param other the second argument of subtract
     * @return a new rational number that is r-t
     */    
    public Rational subtract(Rational other)
    {               
        // CHANGE THE RETURN TO SOMETHING APPROPRIATE
    	int newD = this.denom * other.getDenominator();
		int newN = (this.numer * other.getDenominator()) - (this.denom * other.getNumerator());
		Rational r = new Rational(newN, newD);
		return r;
    }

	/**
	 * Multiply two rational numbers
	 *
	 * @param other the second argument of multiply
	 * @return a new rational number that is the sum of this object and the other
	 *         rational.
	 */
	public Rational multiply(Rational other) {
		// ADD NEW CODE AND CHANGE THE RETURN TO SOMETHING APPROPRIATE
		Rational r = new Rational(this.numer * other.getNumerator(), this.denom * other.getDenominator());
		return r;

	}

	/**
	 * Divide this rational number r by another one t
	 *
	 * @param other the second argument of divide
	 * @return a new rational number that is r/t
	 */
	public Rational divide(Rational other) {
		// CHANGE THE RETURN TO SOMETHING APPROPRIATE
		Rational r = new Rational(this.numer * other.getDenominator(), this.denom * other.getNumerator());
		return r;
	}

	/**
	 * Put the rational number in normal form where the numerator and the
	 * denominator share no common factors. Guarantee that only the numerator is
	 * negative.
	 *
	 */
	private void normalize() {
		// ADD CODE TO NORMALIZE THE RATIONAL NUMBER
		if (denom < 0 && numer > 0) { // if denominator is negative and numerator is positive, make sure to save
										// numerator as the negative number
			int gcd = gcd(-denom, numer); // find greatest common denominator of rational number
			numer = -numer / gcd;
			denom = -denom / gcd;
		} else if (numer < 0 && denom > 0) { // if numerator is negative
			int gcd = gcd(denom, -numer);
			numer = numer / gcd;
			denom = denom / gcd;
		} else if (numer < 0 && denom < 0){ // both are negative
			int gcd = gcd(-denom, -numer);
			numer = -numer / gcd;
			denom = -denom / gcd;
		} else {
			int gcd = gcd(denom, numer); // both are positive
			numer = numer / gcd;
			denom = denom / gcd;
		}
	}

	/**
	 * Recursively compute the greatest common divisor of two positive integers
	 *
	 * @param a the first argument of gcd
	 * @param b the second argument of gcd
	 * @return the gcd of the two arguments
	 */
	private int gcd(int a, int b) {
		int result = 0;
		if (a < b)
			result = gcd(b, a);
		else if (b == 0)
			result = a;
		else {
			int remainder = a % b;
			result = gcd(b, remainder);
		}
		return result;
	}

}
