package com.vaibhav.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vaibhav.model.Event;
import com.vaibhav.model.EventDetails;

public interface EventRepository extends JpaRepository<Event, Integer> {

	public EventDetails findByEventDetails_Id(int id);

}
