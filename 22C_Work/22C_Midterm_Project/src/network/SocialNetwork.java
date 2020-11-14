/**
 * @author Aaron Wong
 */

package network;

import dataStructures.*;

public class SocialNetwork extends Profile {
	/* Member Variables */
	private LinkedStack<Integer> availableIDs;
	private AList<Integer> usedIDs;
	private int ID;
	private LinkedDictionary<Integer, String> network;
	
	
	public SocialNetwork() {
		availableIDs = new LinkedStack<Integer>();
		usedIDs = new AList<Integer>();
		ID = 0;
		network = new LinkedDictionary<Integer, String>();
		
	}
	/* Instance Methods */
	public void addUser(Profile user) {
		if (!availableIDs.isEmpty()) { // use an available ID from the stack
			user.setID(availableIDs.pop());
			network.add(ID, user.getName());
			usedIDs.add(ID); // add ID to usedIDs stack
		} else { // use a new ID
			user.setID(ID);
			network.add(ID, user.getName());
			usedIDs.add(ID);
			ID++;
		}
		System.out.println(user.getName() + " has been added to the network");
	}
	
	public boolean contains(Profile user) { // is this user in the network?
		return network.contains(user.getID());
	}
	
	public String findUser(int ID) { // find the name of the user with a given ID
		if (network.contains(ID)) {
			return network.getValue(ID);
		} else {
			return "User not found";
		}
	}
	
	public int findUser(String name) { // find a user in the network based on a name
		boolean found = false;
		int itr = 0; // to iterate through the usedIDs list
		int pos;
		while (!found && itr <= usedIDs.getLength()) {
			// iterate through the usedIDs list
			if (network.getValue(usedIDs.getEntry(itr)).equalsIgnoreCase(name)) {
				found = true;
			}
		}
		pos = usedIDs.getEntry(itr);
		if (!found) {
			pos = -1;
		}
		return pos;	
		
	}
	
	public void deleteUser(Profile user) {
		availableIDs.push(user.getID()); // when removing a user from the network, save their ID for future use
		usedIDs.remove(usedIDs.getPos(user.getID())); // remove the ID from the usedID list
		network.remove(user.getID());
		
	}
	/**
	 * Resets current network to default settings
	 */
	public void clear() {
		availableIDs = new LinkedStack<Integer>();
		usedIDs = new AList<Integer>();
		ID = 0;
		network.clear();
	}
	
	public boolean isEmpty() { // is this network empty
		return network.isEmpty();
	}
	

}
