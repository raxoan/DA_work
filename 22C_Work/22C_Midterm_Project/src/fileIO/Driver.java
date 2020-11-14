package fileIO;

import network.*;

public class Driver {

	public static void main(String[] args) {
		SocialNetwork bookFace = new SocialNetwork();
		
		System.out.println("Network empty? " + bookFace.isEmpty());
		
		Profile me = new Profile("Aaron");
		bookFace.addUser(me);
		
		System.out.println(me.toString());
		System.out.println();
		
		Profile friend1 = new Profile("Potato");
		bookFace.addUser(friend1);
		
		Profile friend2 = new Profile("Clive");
		bookFace.addUser(friend2);
		
		friend2.setStatus("edgy");
		
		me.addFriend(friend2);
		me.addFriend(friend1);
		
		System.out.println(me.getName() + "'s friends list:\n" + me.getFriendsList());
		System.out.println();
		
		System.out.println("Network empty? " + bookFace.isEmpty());
		System.out.println(me.toString() + "\n" + friend1.toString());
		System.out.println();
		bookFace.deleteUser(me);
		bookFace.deleteUser(friend1);
		friend1.addFriend(friend2);
		System.out.println("\n" + friend1.getName() + "'s friends: " + friend1.getFriendsList());
		System.out.println();
		friend2.addFriend(friend2);
		System.out.println("\n" + friend2.getName() + "'s friends: " + friend2.getFriendsList());
		
		me.removeFriend(friend1);
		
		System.out.println("Searching for 'Clive'..." + bookFace.findUser("Clive"));
		
		bookFace.clear();
		System.out.println("Is network clear? " + bookFace.isEmpty());
	
	}
	
	
}
