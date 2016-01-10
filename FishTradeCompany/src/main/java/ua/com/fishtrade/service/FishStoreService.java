package ua.com.fishtrade.service;


import java.util.List;

import ua.com.fishtrade.entity.FishStore;


public interface FishStoreService {
	
	public FishStore findById(int id);
	public void SaveToDB(FishStore newFishType);
	public int orderedFishId(String name, Double price);
	public List<FishStore> fishForSale();
	public Double getWarehouseWeightById(int id);
	public boolean updateFishWeightAndDateByIdGenFishOrder(int id, double weight,java.sql.Date date);
	public boolean updatePriceByFishId(int id, double salePrice);
	public boolean updateSaleWeightByFisId(int id, double saleWeight);
	public boolean writeOffFishWarWeightById(int id, double weight);
	public List<FishStore> warehouseFish();
	public FishStore findByName(String name);
	public List<FishStore> allFish();
	public List<FishStore> activeFish();
}
