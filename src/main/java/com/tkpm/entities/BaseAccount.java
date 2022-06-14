package com.tkpm.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "base_account")
public abstract class BaseAccount implements Serializable, Comparable<BaseAccount> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6131587916518800656L;

	@Id
	@Column(name = "id")
	private Integer id;
	
	//Attributes
	@OneToOne(
			cascade = {
					CascadeType.DETACH,
					CascadeType.MERGE,
					CascadeType.PERSIST,
					CascadeType.REFRESH
			},
			fetch = FetchType.LAZY,
			optional = false)
	@MapsId
	@JoinColumn(name = "id")
	protected User user;

	@OneToMany(
			cascade = CascadeType.ALL,
			mappedBy = "account",
			orphanRemoval = true)
	private Set<Reservation> reservations;
	
	public BaseAccount() {
		//do nothing
	}
	
	public BaseAccount(User user) {
		this.user = user;
	}
	
	//Getters
	public Integer getId() {return id;}
	public User getUser() {return user;}
	public Set<Reservation> getReservations() {return reservations;}
	
	//Setters
	public void setId(Integer id) {this.id = id;}
	public void setUser(User user) {this.user = user;}
	public void setReservations(Set<Reservation> reservations) {this.reservations = reservations;}

	public int compareTo(BaseAccount arg0) {
		
		if (null == arg0) return 1;
		BaseAccount another = (BaseAccount) arg0;
		
		int result = 
				(this.getId() > another.getId()?1:
					(this.getId() < another.getId()? -1: 0));
		
		return result;
	}
}
