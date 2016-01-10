package ua.com.fishtrade.dao;

import java.sql.Date;
import java.util.List;

import ua.com.fishtrade.entity.ConfirmedPayment;
import ua.com.fishtrade.entity.CustOrder;



public interface ConfirmedPaymentDao {

	public ConfirmedPayment findById(int id);
	//Report for General Manager
	//public List<Income> getIncomeByDate(Date dateBefore,Date dateAfter);
	
	public void SaveToDb(ConfirmedPayment confPaym);
	public List<ConfirmedPayment> getOrdersForConfirmtion();
	//public List<ConfirmedPayment> getOrdersForAddPayment();
	public List<ConfirmedPayment> readyForDispatching();
	public ConfirmedPayment findByOrderNum(String number);
	
}
