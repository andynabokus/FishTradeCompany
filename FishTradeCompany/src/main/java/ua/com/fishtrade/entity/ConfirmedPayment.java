package ua.com.fishtrade.entity;


//import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
//import javax.persistence.OneToOne;
//import javax.persistence.OneToOne;

@Entity
public class ConfirmedPayment {
	@Id
 	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String orderNum;
	private double copyPercentPaym;
	private double payedMoney;
	private double totalSumOrder;
	private char shipmentAvailable;
	private String comments;
	
/*	@Column(insertable=false, updatable=false)
	private int stuffId;*/
	
/*	@OneToOne
	@JoinColumn(name="apprOrderNum")
	private CustOrder custOrder;*/
	
	@ManyToOne
	@JoinColumn(name="stuffId")
	private Staff staff;
	
	public ConfirmedPayment() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getCopyPercentPaym() {
		return copyPercentPaym;
	}

	public void setCopyPercentPaym(double copyPercentPaym) {
		this.copyPercentPaym = copyPercentPaym;
	}

	public char getShipmentAvailable() {
		return shipmentAvailable;
	}

	public void setShipmentAvailable(char shipmentAvailable) {
		this.shipmentAvailable = shipmentAvailable;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

/*	public CustOrder getCustOrder() {
		return custOrder;
	}

	public void setCustOrder(CustOrder custOrder) {
		this.custOrder = custOrder;
	}
*/
	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}
	
	

/*	public int getStuffId() {
		return stuffId;
	}

	public void setStuffId(int stuffId) {
		this.stuffId = stuffId;
	}*/



	
	public double getPayedMoney() {
		return payedMoney;
	}

	public void setPayedMoney(double payedMoney) {
		this.payedMoney = payedMoney;
	}
	
	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String string) {
		this.orderNum = string;
	}
	
	

	public double getTotalSumOrder() {
		return totalSumOrder;
	}

	public void setTotalSumOrder(double totalSumOrder) {
		this.totalSumOrder = totalSumOrder;
	}

	@Override
	public String toString() {
		return "ConfirmedPayment [id=" + id + ", percentPaym=" + copyPercentPaym
				+ ", shipmentAvailable=" + shipmentAvailable + ", comments="
				+ comments + /*", apprOrderId=" + apprOrderId + ", stuffId="
				+ stuffId + */ ", staff=" + staff + "]";
	}


}
