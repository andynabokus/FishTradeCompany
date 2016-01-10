package ua.com.fishtrade.service;
import java.util.Collection;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ua.com.fishtrade.dao.GenFishOrderDao;
import ua.com.fishtrade.entity.FishStore;
import ua.com.fishtrade.entity.GeneralFishOrder;


@Component
@Named
public class GenFishOrderServiceImpl implements GenFishOrderService{
@Inject
private GenFishOrderDao genFishOrderDao;
	public GeneralFishOrder findById(int id) {
		return genFishOrderDao.findById(id);
	}
	
	
	public Collection<FishStore> findByGenFishOrderId(int id) {	
		return genFishOrderDao.findByGenFishOrderId(id);
	}
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void SaveToDb(GeneralFishOrder genFishOrder) {
		genFishOrderDao.SaveToDb(genFishOrder);
	}


	@Override
	public GeneralFishOrder findOrderByNum(String num) {
		return genFishOrderDao.findOrderByNum(num);
	}




}
