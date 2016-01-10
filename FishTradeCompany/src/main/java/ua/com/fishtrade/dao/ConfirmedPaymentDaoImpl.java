package ua.com.fishtrade.dao;


import java.sql.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import ua.com.fishtrade.entity.ConfirmedPayment;
import ua.com.fishtrade.entity.CustOrder;
//import ua.com.fishtrade.entity.Income;

@Component
@Repository
public class ConfirmedPaymentDaoImpl implements ConfirmedPaymentDao {
	@PersistenceContext
	private EntityManager em;
	public ConfirmedPayment findById(int id) {
		ConfirmedPayment conPayment = null;
		conPayment = em.find(ConfirmedPayment.class, id);
		return conPayment;
	}	
	public List<ConfirmedPayment> getOrdersForConfirmtion() {
		String strQuery = "SELECT cp FROM ConfirmedPayment cp " +
				"WHERE  cp.totalSumOrder - cp.payedMoney > 0";
		TypedQuery<ConfirmedPayment> query =  em.createQuery(strQuery, ConfirmedPayment.class);
		return query.getResultList();
		
	}

/*	public List<Income> getIncomeByDate(Date dateBefore, Date dateAfter) {
		String txt = "SELECT new ua.com.fishtrade.entity.Income"
				+ "(fishSt.fishName, custOrd.orderDate, custOrd.weight, "
				+ "custOrd.copySalePrice, confPay.copyPercentPaym, "
				+ "confPay.payedMoney, fishSt.writeOffWeight, fishSt.deliveryPrice) ";   
	      txt += "FROM FishStore fishSt, CustOrder custOrd, ConfirmedPayment confPay ";
	      txt += "WHERE custOrd.id = confPay.apprOrderId ";
	      txt += "AND fishSt.id = custOrd.fishId ";
	      txt += "AND confPay.shipmentAvailable LIKE 'Y' ";
	      txt += "AND custOrd.orderDate BETWEEN '" + dateBefore +"'";
	      txt += "AND '" + dateAfter + "'";
	      TypedQuery<Income> query = em.createQuery(txt, Income.class);
	      query.setParameter(1, dateBefore);
	      query.setParameter(2, dateAfter);
	      return query.getResultList();
	}*/
	
	public void SaveToDb(ConfirmedPayment confPaym) {
		if(confPaym.getId() == 0) {
			em.persist(confPaym);	
		} else em.merge(confPaym);
	}
	@Override
	public ConfirmedPayment findByOrderNum(String number) {
		String sqlQuery = "SELECT confPay FROM ConfirmedPayment confPay " + 
				  "WHERE confPay.orderNum LIKE '" + number + "'";
		 TypedQuery<ConfirmedPayment> query = em.createQuery(sqlQuery, ConfirmedPayment.class);
	     return query.getSingleResult();
	}
	@Override
	public List<ConfirmedPayment> readyForDispatching() {
		String sqlQuery = "SELECT confPay FROM ConfirmedPayment confPay " 
					+ "WHERE confPay.payedMoney - (confPay.copyPercentPaym * confPay.totalSumOrder) / 100 >= 0 "
					+ "AND confPay.shipmentAvailable LIKE 'N'";
		 TypedQuery<ConfirmedPayment> query = em.createQuery(sqlQuery, ConfirmedPayment.class);
	 return query.getResultList();
	}



/*	public List<ConfirmedPayment> getOrdersForAddPayment() {
		String strQuery = "SELECT cp FROM ConfirmedPayment cp " +
				"WHERE  cp.payedMoney = 0.0";
		TypedQuery<ConfirmedPayment> query =  em.createQuery(strQuery, ConfirmedPayment.class);
		return query.getResultList();
	}*/
	

}
