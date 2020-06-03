
/**
 * Candy.java
 * @author Aaron Wong
 * CIS 36B
 */
import java.util.ArrayList;

public abstract class Candy {
	private int numCalories;
	private ArrayList<String> ingredients;
	private static int numPieces = 0;

	public Candy() {
		this(0, new ArrayList<String>());
	}

	public Candy(int numCalories, ArrayList<String> ingredients) {
		this.numCalories = numCalories;
		this.ingredients = ingredients;
	}

	public Candy(Candy c) {
		numCalories = c.numCalories;
		ingredients = new ArrayList<String>(c.ingredients);
	}

	public static int getNumPieces() {
		return numPieces;
	}

	public int getNumCalories() {
		return numCalories;
	}

	public static void updateNumPieces() {
		numPieces++;
	}

	public void setNumCalories(int numCals) {
		numCalories = numCals;
	}

	public void addIngredient(String ingredient) {
		ingredients.add(ingredient);
	}

	public abstract void printCandyGreeting();

	@Override
	public String toString() {
		String result = "Total Calories " + numCalories;
		result += "\nIngredients:\n";
		for (int i = 0; i < ingredients.size(); i++) {
			result += ingredients.get(i) + "\n";
		}
		return result;

	}

	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		} else if (!(o instanceof Candy)) {
			return false;
		} else {
			Candy c = (Candy) o;
			return this.numCalories == c.numCalories && this.ingredients.equals(c.ingredients);
		}

	}

}
