package ua.com.fishtrade.service;


//import java.util.Collection;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ua.com.fishtrade.dao.CustOrderDao;
import ua.com.fishtrade.entity.CustOrder;
import ua.com.fishtrade.entity.FishStore;
import ua.com.fishtrade.dao.FishStoreDao;
//import ua.com.fishtrade.entity.Staff;
//import ua.com.fishtrade.dao.StaffDao;
//import ua.com.fishtrade.dao.ConfirmedPaymentDao;
@Component
@Named
public class CustOrderServiceImpl implements CustOrderService {
	@Inject
	private CustOrderDao custOrderDao;	
/*	@Inject
	private StaffDao staffDao;*/
/*	@Inject
	private ConfirmedPaymentDao confPayDao;*/
	@Inject
	private FishStoreDao fishStoreDao;
	public CustOrder findById(int id) {
		return custOrderDao.findById(id);
	}
	/*@Transactional
	public List<OrderedFish> getOrderedFishByCompany(String company) {
		return custOrderDao.getOrderedFishByCompany(company);
	}
	@Transactional
	public List<OrderedFish> getOrderedFishByCompanyDate(String company, java.sql.Date dateBefore,
			java.sql.Date dateAfter) {
		return custOrderDao.getOrderedFishByCompanyDate(company, dateBefore, dateAfter);
	}*/

	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public boolean SaveToDB(CustOrder custOrder) { 
		//Check if ordered weight is not more than on warehouse
		FishStore fishStrore = fishStoreDao.findById(custOrder.getFishId());
			if(fishStrore.getSaleWeight() - custOrder.getWeight() < 0.0) {
				System.out.println("You can't order fish " + fishStrore.getFishName() +" more than " 
			+ fishStrore.getSaleWeight() + " kg");
				return false;
			}
		custOrderDao.SaveToDB(custOrder);
		return true;
	}

	@Override
	public Integer findCustomerId(String number) {
		return custOrderDao.findCustomerId(number);
	}

	@Override
	public List<CustOrder> findByOrderNum(String number) {
		return custOrderDao.findByOrderNum(number);
	}
}
