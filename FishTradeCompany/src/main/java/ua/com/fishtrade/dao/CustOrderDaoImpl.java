package ua.com.fishtrade.dao;



import java.sql.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import ua.com.fishtrade.entity.CustOrder;


@Component
@Repository
public class CustOrderDaoImpl implements CustOrderDao {
    @PersistenceContext
    private EntityManager em;
	public CustOrder findById(int id) {
	CustOrder custOrder = null;
	custOrder = em.find(CustOrder.class,id);
	return custOrder;
	}
	/*public List<OrderedFish> getOrderedFishByCompany(String company) {
		String sqlQuery = "SELECT new ua.com.fishtrade.entity.OrderedFish(fishSt.fishName,"
				+ "custOrd.weight, custOrd.copySalePrice, "
				+ "custOrd.orderDate, custOrd.comments) "
				+ "FROM FishStore fishSt, CustOrder custOrd, Customer cust "
				+ "WHERE fishSt.id = custOrd.id "
				+ "AND custOrd.customerId = cust.id "
				+ "AND cust.company LIKE '" + company + "'";   
	      TypedQuery<OrderedFish> query = em.createQuery(sqlQuery, OrderedFish.class);
	      return query.getResultList();
	}
	public List<OrderedFish> getOrderedFishByCompanyDate(String company, java.sql.Date dateBefore, 
			java.sql.Date dateAfter) {
			String sqlQuery = "SELECT new ua.com.fishtrade.entity.OrderedFish(fishSt.fishName,"
				+ "custOrd.weight, custOrd.copySalePrice, "
				+ "custOrd.orderDate, custOrd.comments) "
				+ "FROM FishStore fishSt, CustOrder custOrd, Customer cust "
				+ "WHERE fishSt.id = custOrd.id "
				+ "AND custOrd.customerId = cust.id "
				+ "AND cust.company LIKE '" + company + "' " 
		      	+ "AND custOrd.orderDate BETWEEN '" + dateBefore +"' "
		      	+ "AND '" + dateAfter + "'";
	      TypedQuery<OrderedFish> query = em.createQuery(sqlQuery, OrderedFish.class);
	      return query.getResultList();

	}*/

	public void SaveToDB(CustOrder custOrder) {
		em.persist(custOrder);
		
	}

	public List<CustOrder> getOrderedFishByDate(Date dateBefore, Date dateAfter) {
		String sqlQuery = "SELECT cO FROM CustOrder cO "
				+ "WHERE custOrd.orderDate BETWEEN '"+ dateBefore +"' "
		      	+ "AND '" + dateAfter + "'";
		 TypedQuery<CustOrder> query = em.createQuery(sqlQuery, CustOrder.class);
	     return query.getResultList();
	}

	@Override
	public Integer findCustomerId(String number) {
		String sqlQuery = "SELECT DISTINCT cO.customerId FROM CustOrder cO "
				+ "WHERE cO.orderNum LIKE '"+ number +"'";
		 TypedQuery<Integer> query = em.createQuery(sqlQuery, Integer.class);
	     return query.getSingleResult();
	}

	@Override
	public List<CustOrder> findByOrderNum(String number) {
		String sqlQuery = "SELECT cO FROM CustOrder cO "
				+ "WHERE cO.orderNum LIKE '"+ number +"' ";
		 TypedQuery<CustOrder> query = em.createQuery(sqlQuery, CustOrder.class);
	     return query.getResultList();
	}

}
