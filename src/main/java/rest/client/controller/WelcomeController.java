package rest.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import rest.client.service.UserServiceREST;

@Controller
@RequestMapping("/")
public class WelcomeController {
	private UserServiceREST serviceREST;
	@Autowired
	public WelcomeController(UserServiceREST userServiceREST) {
		serviceREST = userServiceREST;
	}
	@GetMapping
	public String welcomePage(Model model) {
		model.addAttribute("allUsers", serviceREST.getAllUsers());
		return "welcome";
	}
}
