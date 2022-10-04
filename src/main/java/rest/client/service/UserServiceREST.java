package rest.client.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import rest.client.model.User;
import rest.client.repository.LocalUserRep;
@Service
public class UserServiceREST {
	private RestTemplate restTemplate;
	private ObjectMapper objectMapper;
	private LocalUserRep repository;
	
	@Autowired
	public UserServiceREST(RestTemplate restTemplate, ObjectMapper objectMapper, LocalUserRep localUserRep) {
		this.restTemplate = restTemplate;
		this.objectMapper = objectMapper;
		this.repository = localUserRep;
	}
	
	public User getOneUser(int id) {
		if(repository.getAllUsers().containsKey(id)) {
			return repository.getOneUser(id);
		}
		String url = "http://localhost:8080/users/"+id;
		User user = restTemplate.getForObject(url, User.class);
		repository.addUser(user);
		return user;
	}
	
	public List<User> getAllUsers() {
		if(repository.getAllUsers().isEmpty() ) {
			String url = "http://localhost:8080/users";
			String allUsers = restTemplate.getForObject(url, String.class);
			
			updateLocalRepository(allUsers);
		}
		return repository.getAllUsers().entrySet().stream().map(entry -> entry.getValue()).toList();
	}
	
	public void saveUser(HttpServletRequest request) {
		String name = request.getParameter("name");
		String surname = request.getParameter("surname");
		int age = Integer.parseInt(request.getParameter("age"));
		
		String url = "http://localhost:8080/users";
		String allUsers = restTemplate.postForObject(url, new User(name, surname, age), String.class);
	
		updateLocalRepository(allUsers);
	}
	
	public void updateUser(int id, HttpServletRequest request) {
		
		String name = request.getParameter("name");
		String surname = request.getParameter("surname");
		int age = Integer.parseInt(request.getParameter("age"));
		
		String url = "http://localhost:8080/users/"+id;
		HttpEntity<User> req = new HttpEntity<User>(new User(name,surname,age));
		ResponseEntity<String> allUser = restTemplate.exchange(url, HttpMethod.PUT, req, String.class);
		
		updateLocalRepository(allUser.getBody());
	}
	
	public void updateLocalRepository(String allUsers) {
		try {
			List<User> users = objectMapper.readValue(allUsers, new TypeReference<List<User>>(){});
			repository.setAllUsers(users);
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void deleteUser(int id) {
		String url = "http://localhost:8080/users/"+id;
		restTemplate.delete(url);
		repository.deleteUser(id);
	}
}

	












