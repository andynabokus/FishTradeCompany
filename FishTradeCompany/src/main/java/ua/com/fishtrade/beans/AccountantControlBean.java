package ua.com.fishtrade.beans;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import ua.com.fishtrade.entity.ConfirmedPayment;
import ua.com.fishtrade.entity.Customer;
import ua.com.fishtrade.service.ConfirmedPaymentService;
import ua.com.fishtrade.service.CustOrderService;
import ua.com.fishtrade.service.CustomerService;



@Named
@Scope("session")
public class AccountantControlBean {
	
	private List<ConfirmedPayment> needConfPay = null;
	private List<Customer> customerBorrows = null;
	private Map<Long, Boolean> confirmed = new HashMap<Long, Boolean>();
	private Map<Long, String> recievedPayment = new HashMap<Long, String>();
	@Inject
	private ConfirmedPaymentService confPayService;
	@Inject
	private CustomerService custService;
	@Inject
	private CustOrderService custOrderServ;
	
	public Map<Long, Boolean> getConfirmed() {
		return confirmed;
	}

	public void setConfirmed(Map<Long, Boolean> confirmed) {
		this.confirmed = confirmed;
	}

	public Map<Long, String> getRecievedPayment() {
		return recievedPayment;
	}

	public void setRecievedPayment(Map<Long, String> recievedPayment) {
		this.recievedPayment = recievedPayment;
	}

	public List<ConfirmedPayment> getNeedConfPay() {
		return needConfPay;
	}

	
	
	public void setNeedConfPay(List<ConfirmedPayment> needConfPay) {
		this.needConfPay = needConfPay;
	}


	
	public List<Customer> getCustomerBorrows() {
		return customerBorrows;
	}

	public void setCustomerBorrows(List<Customer> customerBorrows) {
		this.customerBorrows = customerBorrows;
	}

	public void refreshList(){
		needConfPay = confPayService.getOrdersForConfirmtion();
	}
	public String submit() {
			 
		 for (ConfirmedPayment confPayment : needConfPay) {
			 if(confirmed.get(confPayment.getId())) {
				 confPayment.setPayedMoney(confPayment.getPayedMoney() + Double.parseDouble(recievedPayment.get(confPayment.getId())));
				 confPayService.SaveToDb(confPayment);
				 Customer customer = custService.findById(custOrderServ.findCustomerId(confPayment.getOrderNum()));
				 customer.setDebt(confPayment.getTotalSumOrder() - confPayment.getPayedMoney());
				 custService.SaveToDb(customer);
			 }
			
		}
		 this.refreshList();
		 
		return "null"; 
	 }
	
	public void listBorrows() {
		customerBorrows = custService.getBorrows();
		
	}
}
