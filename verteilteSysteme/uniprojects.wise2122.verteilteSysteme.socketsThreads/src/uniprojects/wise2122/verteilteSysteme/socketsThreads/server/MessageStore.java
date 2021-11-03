package uniprojects.wise2122.verteilteSysteme.socketsThreads.server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessageStore {
	
	private Map<String, List<String>> messages;
	private Object monitor;
	
	public MessageStore() {
		this.messages = new HashMap<>();
		this.monitor = new Object();
	}
	
	public void register(String name) {
		this.messages.put(name, new ArrayList<String>());
	}
	
	public List<String> retrieve(String user){
		if(!this.messages.containsKey(user)) {
			return new ArrayList<String>();
		}
		return this.messages.get(user);
	}
	
	public boolean writeMessage(String user, String message) {
		if(!this.messages.containsKey(user)) {
			return false;
		}
		this.messages.get(user).add(message);	
		return true;
	}
	

}
