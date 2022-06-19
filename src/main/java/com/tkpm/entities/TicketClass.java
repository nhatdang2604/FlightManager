package com.tkpm.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ticket_class")
public class TicketClass {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "name")
	private String name;

	//Constructors
	public TicketClass() {
		//do nothing
	}
	
	public TicketClass(Integer id, String name) {
		this.id = id;
		this.name = name;
	}
	
	//Getters
	public Integer getId() {return id;}
	public String getName() {return name;}
	
	//Setters
	public void setId(Integer id) {this.id = id;}
	public void setName(String name) {this.name = name;}
}
