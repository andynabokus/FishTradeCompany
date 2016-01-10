package ua.com.fishtrade.dao;

import java.util.List;

import ua.com.fishtrade.entity.CustOrder;


public interface CustOrderDao {
	
	public CustOrder findById(int id);
	//Find ordered Fish by Company
	//public List<OrderedFish> getOrderedFishByCompany(String company);
	//Find ordered Fish by company and time period
	//public List<OrderedFish> getOrderedFishByCompanyDate(String company, java.sql.Date dateBefore, 
			//java.sql.Date dateAfter);
	public List<CustOrder> getOrderedFishByDate(java.sql.Date dateBefore, 
			java.sql.Date dateAfter);
	public void SaveToDB(CustOrder custOrder);
	public Integer findCustomerId(String number);
	public List<CustOrder> findByOrderNum(String number);
}
