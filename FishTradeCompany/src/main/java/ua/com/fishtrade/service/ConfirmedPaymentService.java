package ua.com.fishtrade.service;

import java.util.List;

import ua.com.fishtrade.entity.ConfirmedPayment;

//import ua.com.fishtrade.entity.Income;
import java.sql.Date;

public interface ConfirmedPaymentService {
	public ConfirmedPayment findById(int id);
	//public Double getIncomeByDate(Date dateBefore, Date dateAfter);
	public void SaveToDb(ConfirmedPayment confPaym);
	public List<ConfirmedPayment> getOrdersForConfirmtion();
	//public List<ConfirmedPayment> getOrdersForAddPayment();
	/*public boolean setPayment(int id, double payment);*/
	public List<ConfirmedPayment> readyForDispatching();
	public ConfirmedPayment findByOrderNum(String number);
	
}
