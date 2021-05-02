package com.javacourseproject2.hibernateAndJpa2.Entities;

import java.sql.Date;

import javax.persistence.*;

@Entity
@Table(name="users")
public class Users {
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private int id;
	
	@Column(name="username_")
	private String userName_;
	
	@Column(name="password_")
	private String password_;
	
	@Column(name="namesurname")
	private String nameSurname;
	
	@Column(name="email")
	private String eMail;
	
	@Column(name="gender")
	private String gender;
	
	@Column(name="birthdate")
	private Date birhdate;
	
	@Column(name="createdate")
	private Date createdate;
	
	@Column(name="telnr1")
	private String telNr1;
	
	@Column(name="telnr2")
	private String telNr2;

	public Users(int id, String userName_, String password_, String nameSurname, String eMail, String gender,
			Date birhdate, Date createdate, String telNr1, String telNr2) {
		this.id = id;
		this.userName_ = userName_;
		this.password_ = password_;
		this.nameSurname = nameSurname;
		this.eMail = eMail;
		this.gender = gender;
		this.birhdate = birhdate;
		this.createdate = createdate;
		this.telNr1 = telNr1;
		this.telNr2 = telNr2;
	}
	
	public Users() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName_() {
		return userName_;
	}

	public void setUserName_(String userName_) {
		this.userName_ = userName_;
	}

	public String getPassword_() {
		return password_;
	}

	public void setPassword_(String password_) {
		this.password_ = password_;
	}

	public String getNameSurname() {
		return nameSurname;
	}

	public void setNameSurname(String nameSurname) {
		this.nameSurname = nameSurname;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getBirhdate() {
		return birhdate;
	}

	public void setBirhdate(Date birhdate) {
		this.birhdate = birhdate;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public String getTelNr1() {
		return telNr1;
	}

	public void setTelNr1(String telNr1) {
		this.telNr1 = telNr1;
	}

	public String getTelNr2() {
		return telNr2;
	}

	public void setTelNr2(String telNr2) {
		this.telNr2 = telNr2;
	}
	
	
	
	
	

	
}
