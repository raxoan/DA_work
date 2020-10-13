/**
 * @author Aaron
 * CIS 35B - Lab 2
 * UpdateAuto.java
 */

package adapter;

public interface UpdateAuto {

	/**
	 * This function searches the Model for a given OptionSet and sets the name of OptionSet to newName.
	 * @param modelName
	 * @param optionSetname
	 * @param newName
	 */
	public void updateOptionSetName(String modelName, String optionSetName, String newName);

	/**
	 * This function searches the Model for a given OptionSet and Option name, and
	 * sets the price to newPrice.
	 * 
	 * @param modelName
	 * @param optionSetName 	- optionSet name
	 * @param optionName		- Option name
	 * @param newPrice
	 */
	public void updateOptionPrice(String modelName, String optionSetName, String optionName, float newPrice);
}
