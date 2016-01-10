package ua.com.fishtrade.service;




import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ua.com.fishtrade.dao.CustomerDao;
import ua.com.fishtrade.entity.Customer;

@Component
@Named
public class CustomerServiceImpl implements CustomerService {
	@Inject
	private CustomerDao custDao;
	
	public Customer findById(int id) {
		return custDao.findById(id);
	}

	@Transactional
	public void SaveToDb(Customer customer) {
		custDao.SaveToDb(customer);
		
	}
	@Transactional
	public Customer findCustomerByLogin(String Login) {
		return custDao.findCustomerByLogin(Login);
	}

	@Override
	public List<Customer> customers() {
		return custDao.customers();
	}


	@Override
	public List<Customer> getBorrows() {
		return custDao.getBorrows();
	}

}
