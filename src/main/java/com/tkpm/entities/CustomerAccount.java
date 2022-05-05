package com.tkpm.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "customer_account")
@PrimaryKeyJoinColumn(foreignKey=@ForeignKey(name = "fk_customer_base"))
public class CustomerAccount extends BaseAccount {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7570950875716299948L;

	@Column(name = "name")
	private String name;
	
	@Column(name = "identity_code")
	private String identityCode;
	
	@Column(name = "phone_number")
	private String phoneNumber;
	
	//Constructors
	public CustomerAccount() {
		super();
	}

	public CustomerAccount(String name, String identityCode, String phoneNumber) {
		super();
		this.name = name;
		this.identityCode = identityCode;
		this.phoneNumber = phoneNumber;
	}

	//Getters
	public String getName() {return name;}
	public String getIdentityCode() {return identityCode;}
	public String getPhoneNumber() {return phoneNumber;}
	
	//Setters
	public void setName(String name) {this.name = name;}
	public void setIdentityCode(String identityCode) {this.identityCode = identityCode;}
	public void setPhoneNumber(String phoneNumber) {this.phoneNumber = phoneNumber;}
	
	
	
}
