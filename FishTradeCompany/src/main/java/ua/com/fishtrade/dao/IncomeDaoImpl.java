package ua.com.fishtrade.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import ua.com.fishtrade.entity.Income;

@Component
@Repository
public class IncomeDaoImpl implements IncomeDao {
	
    @PersistenceContext
    private EntityManager em;
    
	@Override
	public List<Income> getIncome(String startDate, String endDate) {
		String sqlQuery = "SELECT new ua.com.fishtrade.entity.Income(custOrd.orderDate, SUM((custOrd.copySalePrice * custOrd.weight) - (fishSt.deliveryPrice * custOrd.weight))) "
				+ "FROM CustOrder custOrd, FishStore fishSt "
				+ "WHERE custOrd.orderNum = ANY"
				+ "(SELECT confPay.orderNum  FROM ConfirmedPayment confPay "
				+ "WHERE confPay.shipmentAvailable LIKE 'Y') "
				+ "AND custOrd.fishId = fishSt.id "
				+ "AND custOrd.orderDate BETWEEN '" + startDate + "' AND '" + endDate + "' "
				+ "AND custOrd.orderNum  = ANY"
				+ "(SELECT confPay.orderNum  FROM ConfirmedPayment confPay "
				+ "WHERE confPay.shipmentAvailable LIKE 'Y') "
				+ "GROUP BY custOrd.orderDate";
		TypedQuery<Income> query = em.createQuery(sqlQuery, Income.class); 
		return query.getResultList();
	}
	
}
