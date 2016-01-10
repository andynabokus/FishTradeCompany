package ua.com.fishtrade.beans;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import ua.com.fishtrade.entity.Customer;
import ua.com.fishtrade.service.CustomerService;


@Named
@Scope("session")
public class CustomerBean {
	private Customer customer = null;
	private List<Customer> customers = null;
	@Inject
	private CustomerService custService;
	
	public CustomerBean() {
		customer = new Customer();
	}
	
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public List<Customer> getCustomers() {
		return customers;
	}
	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}
	
	public String saveNewCustomer() {
		custService.SaveToDb(customer);
		return "ListCustomers";
	}
}
