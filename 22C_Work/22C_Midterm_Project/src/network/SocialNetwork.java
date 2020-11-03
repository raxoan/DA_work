/**
 * @author Aaron Wong
 */

package network;

import dataStructures.*;

public class SocialNetwork extends Profile {
	/* Member Variables */
	private LinkedStack<Integer> availableIDs = new LinkedStack<Integer>();
	private int ID = 0;
	private LinkedDictionary<Integer, String> network = new LinkedDictionary<Integer, String>();
	
	/* Instance Methods */
	public void addUser(Profile user) {
		if (!availableIDs.isEmpty()) { // use an available ID from the stack
			user.setID(availableIDs.pop());
			network.add(ID, user.getName());
		} else { // use a new ID
			user.setID(ID);
			network.add(ID, user.getName());
			ID++;
		}
		System.out.println(user.getName() + " has been added.");
	}
	
	public boolean contains(Profile user) { // is this user in the network?
		return network.contains(user.getID());
	}
	
	public Profile findUser(int ID) { // find 
		if (network.contains(ID)) {
			return network. // how to return a Profile datatype?
		}
	}
	
	public void findUser(String name) { // find a user in the network based on a name 
		
	}
	
	public void deleteUser(Profile user) {
		availableIDs.push(user.getID()); // when removing a user from the network, save their ID for future use
		network.remove(user.getID());
	}
	
	public boolean isEmpty() { // is this network empty
		return network.isEmpty();
	}
	

}
