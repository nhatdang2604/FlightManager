package com.tkpm.entities;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "reservation")
public class Reservation implements Serializable, Comparable<Reservation>{

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6565753193438714199L;
	
	@Id
	@OneToOne(
			cascade = CascadeType.ALL,
			fetch = FetchType.EAGER)
	@JoinColumn(name = "id")
	private Ticket ticket;
	
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
	
	@Column(name = "booking_date")
	private LocalDate bookDate;
	
	//Constructors
	public Reservation() {
		super();
		
	}

	//Getters
	public Ticket getTicket() {return ticket;}
	public Flight getFlight() {return flight;}
	public TicketClass getTicketClass() {return ticketClass;}
	public CustomerAccount getCustomer() {return customer;}
	public Integer getPrice() {return price;}
	public LocalDate getBookDate() {return bookDate;}
	
	//Setters
	public void setTicket(Ticket ticket) {this.ticket = ticket;}
	public void setFlight(Flight flight) {this.flight = flight;}
	public void setTicketClass(TicketClass ticketClass) {this.ticketClass = ticketClass;}
	public void setCustomer(CustomerAccount customer) {this.customer = customer;}
	public void setPrice(Integer price) {this.price = price;}
	public void setBookDate(LocalDate bookDate) {this.bookDate = bookDate;}
	
	//Compare for using Set, by implementing Comparable
	public int compareTo(Reservation another) {
		return this.getTicket().compareTo(another.getTicket());
	}
	
}
