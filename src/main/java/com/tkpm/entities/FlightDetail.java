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
@Table(name = "flight_detail")
public class FlightDetail implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -389979282679597092L;

	@Id
	@OneToOne(
			cascade = CascadeType.ALL,
			fetch = FetchType.LAZY)
	@JoinColumn(name = "flight_id")
	private Flight flight;
	
	@Column(name = "flight_time")
	private Integer flightTime;	//Minutes
	
	@Column(name = "first_class_seat_size")
	private Integer numberOfFirstClassSeat;
	
	@Column(name = "second_class_seat_size")
	private Integer numberOfSecondClassSeat;
	
	//Constructors
	public FlightDetail() {
		super();
		
	}

	//Getters
	public Flight getFlight() {return flight;}
	public Integer getFlightTime() {return flightTime;}
	public Integer getNumberOfFirstClassSeat() {return numberOfFirstClassSeat;}
	public Integer getNumberOfSecondClassSeat() {return numberOfSecondClassSeat;}

	//Setters
	public void setFlight(Flight flight) {this.flight = flight;}
	public void setFlightTime(Integer flightTime) {this.flightTime = flightTime;}
	public void setNumberOfFirstClassSeat(Integer numberOfFirstClassSeat) {this.numberOfFirstClassSeat = numberOfFirstClassSeat;}
	public void setNumberOfSecondClassSeat(Integer numberOfSecondClassSeat) {this.numberOfSecondClassSeat = numberOfSecondClassSeat;}

	
}
