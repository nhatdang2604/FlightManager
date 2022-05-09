package com.tkpm.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ticket")
public class Ticket implements Serializable, Comparable<Ticket> {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4179448035661626991L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@ManyToOne(
			cascade = {
					CascadeType.PERSIST,
					CascadeType.MERGE,
					CascadeType.DETACH,
					CascadeType.REFRESH},
			fetch = FetchType.EAGER)
	@JoinColumn(
			name = "flight_id", 
			referencedColumnName = "id",
			nullable = true)
	private Flight flight;
	
	@ManyToOne(
			cascade = {
					CascadeType.PERSIST,
					CascadeType.MERGE,
					CascadeType.DETACH,
					CascadeType.REFRESH},
			fetch = FetchType.EAGER)
	@JoinColumn(
			name = "ticket_class_id", 
			referencedColumnName = "id",
			nullable = true)
	private TicketClass ticketClass;
	
	@ManyToOne(
			cascade = {
					CascadeType.PERSIST,
					CascadeType.MERGE,
					CascadeType.DETACH,
					CascadeType.REFRESH},
			fetch = FetchType.EAGER)
	@JoinColumn(
			name = "customer_id", 
			referencedColumnName = "id",
			nullable = true)
	private CustomerAccount customer;
	
	@Column(name = "price")
	private Integer price;
	
	//Constructors
	public Ticket() {
		super();
		
	}

	//Getters
	public Integer getId() {return id;}
	public Flight getFlight() {return flight;}
	public TicketClass getTicketClass() {return ticketClass;}
	public CustomerAccount getCustomer() {return customer;}
	public Integer getPrice() {return price;}

	//Setters
	public void setId(Integer id) {this.id = id;}
	public void setFlight(Flight flight) {this.flight = flight;}
	public void setTicketClass(TicketClass ticketClass) {this.ticketClass = ticketClass;}
	public void setCustomer(CustomerAccount customer) {this.customer = customer;}
	public void setPrice(Integer price) {this.price = price;}

	//Compare for using Set, by implementing Comparable
	public int compareTo(Ticket another) {
		if (null == another) return 1;
		
		int result = 
				(this.getId() > another.getId()?1:
					(this.getId() < another.getId()? -1: 0));
		
		return result;
	}

	
}
