package com.vaibhav.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vaibhav.jpa.EventRepository;
import com.vaibhav.model.Event;

@Controller
public class EventController {

	@Autowired
	EventRepository repostitory;

	@RequestMapping("/event")
	public String getEvent(@RequestParam int id, ModelMap model) {
		Event event = repostitory.findById(id).get();
		System.err.println("============="+event);
		System.err.println(event.getEventDetails());
		model.addAttribute("event", event);
		return "event-details";

	}
}
