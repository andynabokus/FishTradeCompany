package ua.com.fishtrade.dao;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import ua.com.fishtrade.entity.Customer;


@Component
@Repository
public class CustomerDaoImpl implements CustomerDao{

	@PersistenceContext
	private EntityManager em;
	public Customer findById(int id) {
		Customer customer = null;
		customer = em.find(Customer.class, id);
		return customer;
	}
	
	public void SaveToDb(Customer customer) {
		if(customer.getId() == 0) {
			em.persist(customer);
		} else em.merge(customer);
		
	}

	@SuppressWarnings("finally")
	public Customer findCustomerByLogin(String Login) {
		String sqlQuery = "SELECT c FROM Customer c "
				+ "WHERE c.login LIKE " + "'" + Login + "' ";
		TypedQuery<Customer> query = em.createQuery(sqlQuery, Customer.class);
		Customer customer = null;
		 try {
			 customer =  query.getSingleResult();			 
		 } catch (javax.persistence.NoResultException e){
			customer = null;			 
		 } finally {
			 return customer;
		 }	
	}

	@Override
	public List<Customer> customers() {
		String txt = "SELECT cust ";   
	      txt += "FROM Customer cust ";
	      TypedQuery<Customer> query = em.createQuery(txt, Customer.class);
	      return query.getResultList();
	}


	@Override
	public List<Customer> getBorrows() {
		String sqlQuery = "SELECT c FROM Customer c "
				+ "WHERE c.debt > 0 ";
		TypedQuery<Customer> query = em.createQuery(sqlQuery, Customer.class);
		 return query.getResultList();
	}

	
	
//Question?
	/*	public List<Object[]> getOrderedFish(int id) {
		String txt = "SELECT  fishSt.fishName, custOrd.weight,"
				+ "custOrd.copySalePrice FROM ";   
	      txt += "FishStore fishSt, CustOrder custOrd ";
	      txt += "WHERE fishSt.id = custOrd.id ";
	      txt += "AND custOrd.id = " + id;
	      TypedQuery<Object[]> query = em.createQuery(txt, Object[].class);
	      return query.getResultList();
	}*/


}
