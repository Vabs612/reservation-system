package com.vaibhav.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vaibhav.jpa.EventRepository;
import com.vaibhav.model.Event;
import com.vaibhav.model.EventDetails;

@Controller
public class EventController {

	@Autowired
	EventRepository repostitory;

	@RequestMapping("/event")
	public String getEvent(@RequestParam int id, ModelMap model) {
		Event event = repostitory.findById(id).get();
		System.err.println("=============" + event);
		System.err.println(event.getEventDetails());
		model.addAttribute("event", event);
		return "event-details";

	}

	@RequestMapping("/book")
	public String getbook(@RequestParam int id, ModelMap model) {
		EventDetails eventDetails = repostitory.findById(id).get().getEventDetails().stream()
				.filter(x -> x.getId() == id).findFirst().get();
//		EventDetails event = repostitory.findById(id).get().getEventDetails().stream().filter(x -> x.getId() == id)
//				.findFirst().get();
//		System.err.println(event);
		model.addAttribute("eventDetails", eventDetails);
		return "book";

	}

	@RequestMapping("/booking")
	public String saveBooking(@ModelAttribute EventDetails eventDetails, ModelMap model) {
		System.out.println("--------------------------------------------");
		System.err.println(eventDetails.getEventDate());
		model.addAttribute("eventDetails", eventDetails);
		return "book";

	}
}
