package ua.com.fishtrade.entity;

import java.io.Serializable;
import java.util.Collection;


//import javax.persistence.OneToMany;
import javax.persistence.*;

import ua.com.fishtrade.util.EncryptedStringConverter;
@Entity
public class Customer implements Serializable {
	
	@Id
 	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	//@Column(name = "COMPANY")
	private String company;
	private String city;
	private String street;
	private String buildingNum;
	private String phoneNum;
	private String account;
	private double percentPaym;
	private double debt;
	private String login;
	@Convert(converter=EncryptedStringConverter.class)  
	private String password;
	@OneToMany(mappedBy = "customer")
	Collection<CustOrder> orders;
/*	@ManyToMany
	@JoinTable(name="CustOrder", joinColumns=@JoinColumn(name="fishId"),
	   inverseJoinColumns=@JoinColumn(name="customerId"))
	private Collection<FishStore> fish;*/
	
	public Customer() {}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getBuildingNum() {
		return buildingNum;
	}

	public void setBuildingNum(String buildingNum) {
		this.buildingNum = buildingNum;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public double getPercentPaym() {
		return percentPaym;
	}

	public void setPercentPaym(double percentPaym) {
		this.percentPaym = percentPaym;
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

	
/*	public Collection<CustOrder> getOrders() {
		return orders;
	}

	public void setOrders(Collection<CustOrder> orders) {
		this.orders = orders;
	}*/
	
	
	
/*	public Collection<FishStore> getFish() {
		return fish;
	}

	public void setFish(Collection<FishStore> fish) {
		this.fish = fish;
	}*/

	
	public double getDebt() {
		return debt;
	}

	public void setDebt(double debt) {
		this.debt = debt;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", company=" + company + ", city=" + city
				+ ", street = " + street +",  buildingNum=" + buildingNum + ", phoneNum=" + phoneNum
				+ ", account=" + account + ", percentPaym=" + percentPaym
				+ ", login=" + login + ", password=" + password + /*", orders="
				+ orders + */"]";
	}
	
}
