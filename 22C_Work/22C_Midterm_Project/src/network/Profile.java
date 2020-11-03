/**
 * @author Aaron Wong
 * 22C - Midterm Project - Social Network
 * Profile.java
 */
package network;

import dataStructures.*;

/**
 * This class holds the information for each individual 'profile' in this social
 * network.
 * 
 * @author Aaron
 *
 */
public class Profile implements ProfileInterface {
	//////////////////////
	/* Member Variables */
	//////////////////////
	private String name; // Name of person using profile
	private int ID; // Unique identifier for users
	private String status; // Status of profile in network: 'online', 'offline', 'busy', 'away'.
	private AList<Profile> friends; // User's friends list, contains an arraylist of other profiles.

	//////////////////
	/* Constructors */
	//////////////////
	public Profile() {
		name = "Name unknown";
		status = "offline";
		friends = new AList<Profile>();
	}

	public Profile(String name) {
		this.name = name;
		status = "offline";
		friends = new AList<Profile>();
	}
	
	public Profile(String name, String status) {
		this.name = name;
		this.status = status;
		friends = new AList<Profile>();
	}

	////////////////////
	/* Getter Methods */
	////////////////////
	public String getName() {
		return name;
	}

	public String getStatus() {
		return status;
	}

	public int getID() {
		return ID;
	}

	/**
	 * Retrieves the list of friends that a user has
	 * 
	 * @return a string(?) list of friends that this user has.
	 */
	public String getFriendsList() {
		return displayList(friends);
	}

	////////////////////
	/* Setter Methods */
	////////////////////
	public void setName(String newName) {
		name = newName;
	}

	public void setStatus(String newStatus) {
		status = newStatus;

	}

	public void setFriendsList(AList<Profile> friendsList) {
		friends = friendsList;
	}

	public void setID(int i) {
		ID = i;
	}

	/* Instance methods */
	public void addFriend(Profile friend) {
		if (this.equals(friend)) {
			System.out.print("Cannot add self to friends list");
		} else {
			friends.add(friend);
		}
	}
	
	public void removeFriend(Profile friend) {
		if (friends.contains(friend)) {
			friends.remove(friends.getPos(friend));
			System.out.println(friend.getName() + " removed from" + name + "'s friends list.");
		} else {
			System.out.print("Friend not found");
		}
	}

	public static String displayList(AList<Profile> friends2) {
		StringBuffer str = new StringBuffer();
		Object[] listArray = friends2.toArray();
		for (int index = 0; index < listArray.length; index++) {
			str.append(listArray[index] + " ");
		} // end for
		return str.toString();
	} // end displayList

	/* Overridden methods */
	@Override
	public String toString() {
		return "Name: " + name + "; Status: " + status + "; ID: " + ID + ". ";
	}
}
