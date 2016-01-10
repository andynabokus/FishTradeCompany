package ua.com.fishtrade.service;




import java.util.List;

import ua.com.fishtrade.entity.Customer;

public interface CustomerService {
	
	public Customer findById(int id);
	public void SaveToDb(Customer customer);
	public Customer findCustomerByLogin(String Login);
	public List<Customer> customers();
	public List<Customer>  getBorrows();

}

