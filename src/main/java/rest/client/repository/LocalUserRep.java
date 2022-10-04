package rest.client.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import rest.client.model.User;
@Component
public class LocalUserRep {
	private  Map<Integer, User> allUsers = new HashMap<>();
	
	public Map<Integer, User> getAllUsers() {
		return allUsers;
	}
	public void setAllUsers(List<User>users) {
		users.stream().forEach(user -> allUsers.put(user.getId(), user));
	}
	public User getOneUser(Integer id) {
		return allUsers.get(id);
	}
	public void addUser(User user) {
		allUsers.put(user.getId(), user);
	}
	public void deleteUser(int id) {
		allUsers.remove(id);
	}
	
}
