package ua.com.fishtrade.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ua.com.fishtrade.dao.ConfirmedPaymentDao;
import ua.com.fishtrade.dao.CustOrderDao;
import ua.com.fishtrade.dao.FishStoreDao;
import ua.com.fishtrade.entity.ConfirmedPayment;
import ua.com.fishtrade.entity.CustOrder;
import ua.com.fishtrade.entity.FishStore;
//import ua.com.fishtrade.entity.Income;
@Component
@Named
public class ConfirmedPaymentServiceImpl implements ConfirmedPaymentService {
	@Inject
	private ConfirmedPaymentDao confPayDao;
	@Inject
	private CustOrderDao custOrderDao;
	@Inject
	private FishStoreDao fishStoreDao;
	public ConfirmedPayment findById(int id) {
		return confPayDao.findById(id);
	}
/*	public Double getIncomeByDate(Date dateBefore, Date dateAfter) {
		//Find orders by date
		List<CustOrder> custOrders = confPayDao.getCustOrderByDate(dateBefore, dateAfter);
		//Find expenses for the each fish from the Orders
		double expences = 0.0;
		List<Integer> custOrderNum = new ArrayList<Integer>();
		//Calculate expenses according money to buy fish and lost fish due to write Off
		for(CustOrder cO : custOrders) {
		expences += expences + fishStoreDao.findById(cO.getFishId()).getDeliveryPrice() * cO.getWeight() 
				 + fishStoreDao.findById(cO.getFishId()).getWriteOffWeight() * fishStoreDao.findById(cO.getFishId()).getDeliveryPrice();
		//Find list of the order's number to calculate how much money was received from the sold fish
		custOrderNum.add(Integer.parseInt(cO.getOrderNum()));
		}
		double saledFishIncomes = 0.0;
		ConfirmedPayment confPay = null;
		//Calculate how much received money from the sold fish to the customers.
		for (Integer orderNum : custOrderNum) {
			confPay = confPayDao.findConfPaymentByOrderNum(orderNum);
			if(confPay != null && confPay.getShipmentAvailable() == 'Y') {
			saledFishIncomes += saledFishIncomes + confPayDao.findConfPaymentByOrderNum(orderNum).getPayedMoney();
			}
		}
		//Return clear income
		return saledFishIncomes - expences;
		
		//think about income from database
	}*/
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void SaveToDb(ConfirmedPayment confPaym) {
		confPayDao.SaveToDb(confPaym);
		
	}
	public List<ConfirmedPayment> getOrdersForConfirmtion() {
		return confPayDao.getOrdersForConfirmtion();
	}
/*	public List<ConfirmedPayment> getOrdersForAddPayment() {
		return confPayDao.getOrdersForAddPayment();
	}*/
/*	public boolean setPayment(int apprOrderNum, double payment) {
		//Find id by customer's orderNum
		ConfirmedPayment confPayment = confPayDao.findConfPaymentByOrderNum(apprOrderNum);
		//Set payment money for the customer's order
		confPayment.setPayedMoney(payment);
		//Calculate total sum of the customer's fish order under orderNum
		List<Integer> idSOrder = custOrderDao.findIdsCustOrderByOrderNum(apprOrderNum); 
		double sumCopySalePrice = 0.0;
		CustOrder custOrder = null;
		for(Integer idS : idSOrder) {
		custOrder = custOrderDao.findById(idS);
		sumCopySalePrice += custOrder.getCopySalePrice();
		}
		//Check if shipment allowed according prepayment
		if((100 * payment) / sumCopySalePrice >= confPayment.getCopyPercentPaym()) {
		//Set new weight sale and warehouse weight on the warehouse
		//Get List of custOrders by customer's orderNum
			List<CustOrder> custOrders = custOrderDao.findCustOrdersByOrderNum(apprOrderNum);
			for (CustOrder custOrderOne : custOrders) {
				Collection<FishStore> orderedFish = custOrderOne.getFish();
				for(FishStore fS : orderedFish) {
					fS.setSaleWeight(fS.getSaleWeight() - custOrder.getWeight());
					fS.setWarehouseWeight(fS.getWarehouseWeight() - custOrder.getWeight());
					fishStoreDao.SaveToDB(fS);
				}
			}
			confPayment.setShipmentAvailable('Y');	
		}
		confPayDao.SaveToDb(confPayment);
		return true;
	}*/
	@Override
	public List<ConfirmedPayment> readyForDispatching() {
		return confPayDao.readyForDispatching();
	}
	@Override
	public ConfirmedPayment findByOrderNum(String number) {
		return confPayDao.findByOrderNum(number);
	}


}
