package ua.com.fishtrade.dao;

import java.util.List;

import ua.com.fishtrade.entity.FishStore;


public interface FishStoreDao {
	public FishStore findById(int id);
	public void SaveToDB(FishStore newFishType);
	public Integer orderedFishId(String name, Double price);
	public List<FishStore> availableFishForSale();
	public List<String> distinctFish();
	public Double getWarehouseWeightById(int id);
	public int findFishByGenFishOrderId(int id);
	public List<Integer> findIdsByFishNameForSale(String fishName);
	public List<FishStore> warehouseFish();
	public FishStore findByName(String name);
	public List<FishStore> allFish();
	public List<FishStore> activeFish();

	
}

