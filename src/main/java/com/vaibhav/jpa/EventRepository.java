package com.vaibhav.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vaibhav.model.Event;

public interface EventRepository extends JpaRepository<Event, Integer> {

}
