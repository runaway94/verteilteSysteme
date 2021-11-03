package ChatServer;

import java.util.ArrayList;
import java.util.List;

public class ChatAdministration {
	
	private List<OnlineUser> users;
	
	public ChatAdministration() {
		this.users = new ArrayList<>();
	}
	
	public void userEntered(OnlineUser user) {
		user.sendAdminMessage("Hello" + user.getName() + ", welcome to the chat!");
		this.users.stream().forEach(u -> u.sendAdminMessage(user.getName() + " entered the chat."));
		this.users.add(user);
	}
	
	public void userLeft(OnlineUser user) {
		if(!this.users.contains(user)) {
			return;
		}
		this.users.remove(user);
		this.users.stream().forEach(u -> u.sendAdminMessage(user.getName() + " left the chat."));
	}
	
	public void sendMsg(OnlineUser user, String message) {
		this.users.stream().filter(u -> !u.equals(user)).forEach(u -> u.sendMessage(user.getName(), message));
	}
	

}
