package com.vaibhav.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vaibhav.constant.RoleConstants;
import com.vaibhav.jpa.RoleRepository;
import com.vaibhav.jpa.UserRepository;
import com.vaibhav.model.Role;
import com.vaibhav.model.User;

import jakarta.validation.Valid;

@Controller
public class LoginController {

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	public UserRepository userRepository;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@GetMapping("/login")
	public String showLoginPage() {
		System.err.println("Controller called");
		return "login";
	}

//	@GetMapping("/welcome")
//	public String welcome() {
//		System.err.print("welcome");
//		return "user";
//	}

	@PostMapping("/login")
	public String welcomePage(@RequestParam String name, @RequestParam String password, ModelMap model) {
		model.addAttribute("name", name);
		return "user";
	}

	@GetMapping("/registration")
	public String registrationForm(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		return "registration";
	}

	@PostMapping("/registration")
	public String registration(@Valid @ModelAttribute User user, BindingResult result, Model model) {
		System.err.println("User details " + user);
		User existingUser = userRepository.findByEmail(user.getEmail());

		if (existingUser != null)
			result.rejectValue("email", null, "User already registered !!!");

		if (result.hasErrors()) {
			model.addAttribute("user", user);
			return "/registration";
		}

		Role role = roleRepository.findByName(RoleConstants.Roles.USER);

		if (role == null)
			role = roleRepository.save(new Role(RoleConstants.Roles.USER));
		System.err.println("Role from db " + role);
		User user1 = new User(user.getName(), user.getEmail(), bCryptPasswordEncoder.encode(user.getPassword()),
				Arrays.asList(role));
		userRepository.save(user1);
		return "redirect:/registration?success";
	}
}
