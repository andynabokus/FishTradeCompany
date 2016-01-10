package ua.com.fishtrade.entity;

import java.sql.Date;
import java.lang.Comparable;


public class Income implements Comparable<Income> {
	

	private java.sql.Date date;
	private double totalSum;
	
	public Income() {}
	
	public Income(Date date, double totalSum)  {
	
		this.date = date;
		this.totalSum = totalSum;
	}

	public java.sql.Date getDate() {
		return date;
	}
	public void setDate(java.sql.Date date) {
		this.date = date;
	}
	public double getTotalSum() {
		return totalSum;
	}
	public void setTotalSum(double totalSum) {
		this.totalSum = totalSum;
	}

	@Override
	public int compareTo(Income o) {
		return (int) (o.getTotalSum() - this.getTotalSum());
	}
	
}
