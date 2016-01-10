package ua.com.fishtrade.dao;

import java.util.Collection;


import ua.com.fishtrade.entity.FishStore;
import ua.com.fishtrade.entity.GeneralFishOrder;



public interface GenFishOrderDao {
	public GeneralFishOrder findById(int id);
	public void SaveToDb(GeneralFishOrder genFishOrder);
	public Collection<FishStore> findByGenFishOrderId(int id);
	public GeneralFishOrder findOrderByNum(String num);
	

}
