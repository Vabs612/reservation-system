package com.vaibhav.model;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Event {

	@Id
	@GeneratedValue
	@Column(name = "event_id")
	private Integer id;
	private String name;
	private String photo;
	private String description;
	@OneToMany(mappedBy = "event")
	private Set<EventDetails> eventDetails;

	public Event(Integer id, String name, String photo, String description) {
		super();
		this.id = id;
		this.name = name;
		this.photo = photo;
		this.description = description;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	@Override
	public String toString() {
		return "Event [id=" + id + ", name=" + name + ", photo=" + photo + "]";
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Event() {
		super();

	}

	public Set<EventDetails> getEventDetails() {
		return eventDetails;
	}

	public void setEventDetails(Set<EventDetails> eventDetails) {
		this.eventDetails = eventDetails;
	}

}
