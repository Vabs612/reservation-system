package com.vaibhav.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Event {

	@Id
	@GeneratedValue
	private Integer id;
	private String name;
	private String photo;
	private String description;

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

}
