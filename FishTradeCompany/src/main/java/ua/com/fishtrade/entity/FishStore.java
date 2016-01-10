package ua.com.fishtrade.entity;

//import java.util.Collection;

import javax.persistence.*;

@Entity
public class FishStore {
	
@ManyToOne
@JoinColumn(name="id",insertable=false, updatable=false)
private CustOrder CustOrders;
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
private int id;
private String fishName;
private double warehouseWeight;
private java.sql.Date deliveryDate;
private double orderedWeight;
private double deliveredWeight;
private double saleWeight;
private double deliveryPrice;
private double salePrice;
private double writeOffWeight;
private String comments;
/*@Column(insertable=false, updatable=false)
private int idGenFishOrder;*/
@ManyToOne
@JoinColumn(name="idGenFishOrder")
private GeneralFishOrder generalFishOrders;
/*@ManyToMany
@JoinTable(name="CustOrder", joinColumns=@JoinColumn(name="customerId"),
   inverseJoinColumns=@JoinColumn(name="fishId"))
private Collection<Customer> customer;*/

public FishStore() { }

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getFishName() {
	return fishName;
}

public void setFishName(String fishName) {
	this.fishName = fishName;
}

public java.sql.Date getDeliveryDate() {
	return deliveryDate;
}

public void setDeliveryDate(java.sql.Date deliveryDate) {
	this.deliveryDate = deliveryDate;
}

public double getOrderedWeight() {
	return orderedWeight;
}

public void setOrderedWeight(double orderedWeight) {
	this.orderedWeight = orderedWeight;
}

public double getDeliveredWeight() {
	return deliveredWeight;
}

public void setDeliveredWeight(double deliveredWeight) {
	this.deliveredWeight = deliveredWeight;
}

public double getSaleWeight() {
	return saleWeight;
}

public void setSaleWeight(double saleWeight) {
	this.saleWeight = saleWeight;
}

public double getDeliveryPrice() {
	return deliveryPrice;
}

public void setDeliveryPrice(double deliveryPrice) {
	this.deliveryPrice = deliveryPrice;
}

public double getSalePrice() {
	return salePrice;
}

public void setSalePrice(double salePrice) {
	this.salePrice = salePrice;
}


public double getWriteOffWeight() {
	return writeOffWeight;
}

public void setWriteOffWeight(double writeOffWeight) {
	this.writeOffWeight = writeOffWeight;
}

public String getComments() {
	return comments;
}

public void setComments(String comments) {
	this.comments = comments;
}

public GeneralFishOrder getGeneralFishOrders() {
	return generalFishOrders;
}

public void setGeneralFishOrders(GeneralFishOrder generalFishOrders) {
	this.generalFishOrders = generalFishOrders;
}


/*public Collection<Customer> getCustomer() {
	return customer;
}

public void setCustomer(Collection<Customer> customer) {
	this.customer = customer;
} */

public double getWarehouseWeight() {
	return warehouseWeight;
}

public void setWarehouseWeight(double warehouseWeight) {
	this.warehouseWeight = warehouseWeight;
}

@Override
public String toString() {
	return "FishStore [CustOrders=" + CustOrders + ", id=" + id + ", fishName="
			+ fishName + ", warehouseWeight=" + warehouseWeight
			+ ", deliveryDate=" + deliveryDate + ", deliveredWeight="
			+ deliveredWeight + ", saleWeight=" + saleWeight
			+ ", deliveryPrice=" + deliveryPrice + ", salePrice=" + salePrice
			+ ", writeOffWeight=" + writeOffWeight + ", comments=" + comments
			+ ", generalFishOrders=" + generalFishOrders + "]";
}

/*public int getIdGenFishOrder() {
	return idGenFishOrder;
}

public void setIdGenFishOrder(int idGenFishOrder) {
	this.idGenFishOrder = idGenFishOrder;
}*/



}
