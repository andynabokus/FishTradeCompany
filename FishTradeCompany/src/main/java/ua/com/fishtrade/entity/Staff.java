package ua.com.fishtrade.entity;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import ua.com.fishtrade.util.EncryptedStringConverter;

@Entity
public class Staff {
	
	@Id
 	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String firstName;
	private String lastName;
	private String city;
	private String street;
	private String buildingNum;
	private String position;
	private String phoneNum;
	private String login;
	@Convert(converter=EncryptedStringConverter.class)
	private String password;
	private char status;
	private String comments;
	

	@OneToMany(mappedBy="staff", cascade=CascadeType.PERSIST)
	private Collection<ConfirmedPayment> confPayments;
	
	public Staff() {}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getBuildingNum() {
		return buildingNum;
	}
	public void setBuildingNum(String buildingNum) {
		this.buildingNum = buildingNum;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public char getStatus() {
		return status;
	}
	public void setStatus(char status) {
		this.status = status;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}


	public Collection<ConfirmedPayment> getConfPayments() {
		return confPayments;
	}

	public void setConfPayments(Collection<ConfirmedPayment> confPayments) {
		this.confPayments = confPayments;
	}

	@Override
	public String toString() {
		return "Staff [id=" + id + ", firstName=" + firstName + ", lastName="
				+ lastName + ", city=" + city + ", street=" + street
				+ ", buildingNum=" + buildingNum + ", position=" + position
				+ ", phoneNum=" + phoneNum + ", login=" + login + ", password="
				+ password + ", status=" + status + ", comments=" + comments
				+ "]";
	}

	
	
	
}
