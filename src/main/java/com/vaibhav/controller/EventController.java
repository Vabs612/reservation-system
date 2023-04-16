package com.vaibhav.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EventController {

	@RequestMapping("/event")
	public String getEvent(@RequestParam String id) {
		return "event-details";

	}
}
