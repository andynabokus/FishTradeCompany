package ua.com.fishtrade.service;

import java.util.List;

import ua.com.fishtrade.entity.CustOrder;


public interface CustOrderService {
	public CustOrder findById(int id);
	//public List<OrderedFish> getOrderedFishByCompany(String company);
	//public List<OrderedFish> getOrderedFishByCompanyDate(String company, java.sql.Date dateBefore,
	//		java.sql.Date dateAfter);
	public boolean SaveToDB(CustOrder custOrder);
	public Integer findCustomerId(String number);
	public List<CustOrder> findByOrderNum(String number);
}
