package rest.client.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import rest.client.service.UserServiceREST;

@Controller
@RequestMapping("/users")
public class UserController {
	private UserServiceREST serviceREST;
	
	@Autowired
	public UserController(UserServiceREST userServiceREST) {
		serviceREST = userServiceREST;
	}
	@GetMapping("/{id}")
	public String getUserPage(@PathVariable("id")int id, Model model) {
		model.addAttribute("user", serviceREST.getOneUser(id));
		return "user-page";
	}
	@PostMapping
	public String saveUser(HttpServletRequest request) {
		serviceREST.saveUser(request);
		return "redirect:/";
	}
	@GetMapping("/{id}/change")
	public String getFormForChange(Model model, @PathVariable("id")int id) {
		model.addAttribute("user", serviceREST.getOneUser(id));
		return "user-form-for-change";
	}
	@PostMapping("/{id}/change")
	public String updateUser(HttpServletRequest request, @PathVariable("id")int id) {
		serviceREST.updateUser(id, request);
		return "redirect:/";
	}
	@GetMapping("/{id}/delete")
	public String deleteUser(@PathVariable("id")int id) {
		serviceREST.deleteUser(id);
		return "redirect:/";
	}
}






















