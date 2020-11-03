package network;

public interface ProfileInterface {
	public String getName();
	
	public String getStatus();
	
//	public AList<T> getFriendsList();
	
	public void setName(String newName);
	
	public void setStatus(String newStatus);
	
//	public void setFriendsList(AList<T> friendsList);
}
