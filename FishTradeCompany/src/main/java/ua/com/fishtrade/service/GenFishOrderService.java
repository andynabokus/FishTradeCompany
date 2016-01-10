package ua.com.fishtrade.service;

import java.util.Collection;

import ua.com.fishtrade.entity.FishStore;
import ua.com.fishtrade.entity.GeneralFishOrder;

public interface GenFishOrderService {
	
	public GeneralFishOrder findById(int id);
	public Collection<FishStore> findByGenFishOrderId(int id);
	public void SaveToDb(GeneralFishOrder genFishOrder);
	public GeneralFishOrder findOrderByNum(String num);
}
