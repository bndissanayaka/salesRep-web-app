package com.casrilanka.test.model;


public class SalesRep {
	private int id;
	private String name;
	private String email;
	private String contactNumber;
	private String joinedDate;
	private String cuurentRoute;
	private String comment;
	
	
	public SalesRep(String name, String email, String contactNumber, String joinedDate, String cuurentRoute,
			String comment) {
		super();
		this.name = name;
		this.email = email;
		this.contactNumber = contactNumber;
		this.joinedDate = joinedDate;
		this.cuurentRoute = cuurentRoute;
		this.comment = comment;
	}

	public SalesRep(int id, String name, String email, String contactNumber, String joinedDate2, String cuurentRoute,
			String comment) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.contactNumber = contactNumber;
		this.joinedDate = joinedDate2;
		this.cuurentRoute = cuurentRoute;
		this.comment = comment;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public String getJoinedDate() {
		return joinedDate;
	}
	public void setJoinedDate(String joinedDate) {
		this.joinedDate = joinedDate;
	}
	public String getCuurentRoute() {
		return cuurentRoute;
	}
	public void setCuurentRoute(String cuurentRoute) {
		this.cuurentRoute = cuurentRoute;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	
	

}
