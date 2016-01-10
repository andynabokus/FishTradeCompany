package ua.com.fishtrade.entity;

import java.util.Collection;
//import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class GeneralFishOrder {
	@Id
 	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private double orderedWeight;
	private String orderNum;
	private char orderStatus;
	
	@OneToMany(mappedBy="generalFishOrders", cascade=CascadeType.PERSIST)
	private Collection<FishStore> fish;
	
	public GeneralFishOrder() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getOrderedWeight() {
		return orderedWeight;
	}

	public void setOrderedWeight(double orderWeight) {
		this.orderedWeight = orderWeight;
	}


	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public char getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(char orderStatus) {
		this.orderStatus = orderStatus;
	}

	
	
	public Collection<FishStore> getFish() {
		return fish;
	}

	public void setFish(Collection<FishStore> fish) {
		this.fish = fish;
	}

	@Override
	public String toString() {
		return "GeneralFishOrder [id=" + id + ", orderedWeight="
				+ orderedWeight + ", orderNum="	+ orderNum + 
				", orderStatus=" + orderStatus + ", fish=" + fish
				+ "]";
	}


}
