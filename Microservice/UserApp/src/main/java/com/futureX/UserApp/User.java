package com.futureX.UserApp;

import java.awt.Composite;
import java.math.BigInteger;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
@IdClass(UserId.class)
public class User {

	@Id
	private BigInteger userid;
	@Id
	private BigInteger courseid;
	private String username;
	
	public User() {
		super();
	}
	
	public User(BigInteger userid, BigInteger courseid, String username) {
		super();
		this.userid = userid;
		this.courseid = courseid;
		this.username = username;
	}
	
	public BigInteger getUserid() {
		return userid;
	}
	public void setUserid(BigInteger userid) {
		this.userid = userid;
	}
	public BigInteger getCourseid() {
		return courseid;
	}
	public void setCourseid(BigInteger courseid) {
		this.courseid = courseid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
		
}
