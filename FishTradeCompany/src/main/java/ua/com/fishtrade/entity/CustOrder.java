package ua.com.fishtrade.entity;


import java.util.Collection;

//import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
//import javax.persistence.OneToOne;



@Entity
public class CustOrder {

	@Id
 	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String orderNum;
	private int fishId;
	private double weight;
	private double copySalePrice;
	private java.sql.Date orderDate;
	private String comments;
	@Column(insertable=false, updatable=false)
	private int customerId;

	@OneToMany(mappedBy="CustOrders")
	private Collection<FishStore> fish;
	
	@ManyToOne()
	@JoinColumn(name = "customerId")
	private Customer customer;
	
/*	@OneToOne(mappedBy="custOrder",cascade=CascadeType.PERSIST)
	private  ConfirmedPayment  confPaym;*/
	
	public CustOrder() {}

	
	public int getFishId() {
		return fishId;
	}



	public void setFishId(int fishId) {
		this.fishId = fishId;
	}



/*	public ConfirmedPayment getConfPaym() {
		return confPaym;
	}



	public void setConfPaym(ConfirmedPayment confPaym) {
		this.confPaym = confPaym;
	}*/



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public double getCopySalePrice() {
		return copySalePrice;
	}

	public void setCopySalePrice(double copySalePrice) {
		this.copySalePrice = copySalePrice;
	}

	public java.sql.Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(java.sql.Date orderDate) {
		this.orderDate = orderDate;
	}


	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}



	public Collection<FishStore> getFish() {
		return fish;
	}



	public void setFish(Collection<FishStore> fish) {
		this.fish = fish;
	}



	public String getOrderNum() {
		return orderNum;
	}


	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}


	@Override
	public String toString() {
		return "CustOrder [id=" + id + ", orderNum=" + orderNum + ", fishId="
				+ fishId + ", weight=" + weight + ", copySalePrice="
				+ copySalePrice + ", orderDate=" + orderDate + ", comments="
				+ comments + ", customerId=" + customerId + ", fish=" + fish
				+ ", customer=" + customer + /*", confPaym=" + confPaym +*/ "]";
	}


/*	public Collection<FishStore> getFish() {
		return fish;
	}



	public void setFish(Collection<FishStore> fish) {
		this.fish = fish;
	}*/



/*	public int getCustomerId() {
		return customerId;
	}



	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}*/

	
}
