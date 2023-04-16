package com.vaibhav.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vaibhav.jpa.EventRepository;
import com.vaibhav.model.Event;

@Controller
public class HomeController {

	@Autowired
	EventRepository eventRepository;

	@RequestMapping("/")
	public String showHomePage(ModelMap model) {
		List<Event> eventsList = eventRepository.findAll();
		System.err.println(eventsList);
		model.addAttribute("events", eventsList);
		return "index";
	}
}
