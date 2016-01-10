package ua.com.fishtrade.service;

//import java.sql.Date;
//import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import ua.com.fishtrade.dao.FishStoreDao;
import ua.com.fishtrade.entity.FishStore;
@Component
@Named
public class FishStoreServiceImpl implements FishStoreService {
	@Inject
	private FishStoreDao fishStoreDao;
	public FishStore findById(int id) {
		return fishStoreDao.findById(id);
	}
	@Transactional
	public void SaveToDB(FishStore newFishType) {
		fishStoreDao.SaveToDB(newFishType);
	}
	@Transactional()
	public int orderedFishId(String name, Double price) {
		return fishStoreDao.orderedFishId(name, price);
	}
	@Transactional()
	public List<FishStore> fishForSale() {
		
/*		HashMap<String,Double> salingFish = new HashMap<String, Double>();
		FishStore fishStore = null;
		double totalWeight = 0.0;
		List<String> distincFish = fishStoreDao.distinctFish();
		for (String fs : distincFish) {
		//Find Id's available fish for the sale	from the distinct Fish List on warehouse
			List<Integer> availableFish = fishStoreDao.findIdsByFishNameForSale(fs);
		//Calculate Total weight from all raws of distict's fish name 	
			for(Integer id : availableFish) {
				fishStore = fishStoreDao.findById(id);
				totalWeight += fishStore.getWarehouseWeight(); 				
			}
			salingFish.put(fs, totalWeight);
			totalWeight = 0.0;
		}*/
 		return fishStoreDao.availableFishForSale();
	}

	@Transactional()
	public Double getWarehouseWeightById(int id) {
		return fishStoreDao.getWarehouseWeightById(id);
	}
	@Transactional()
	public boolean updateFishWeightAndDateByIdGenFishOrder(int id,
			double weight, java.sql.Date date) {
		FishStore fishStore = fishStoreDao.findById(fishStoreDao.findFishByGenFishOrderId(id));
		fishStore.setWarehouseWeight(weight);
		fishStore.setDeliveryDate(date);
		fishStoreDao.SaveToDB(fishStore);
		return true;
	}
	public boolean updatePriceByFishId(int id, double salePrice) {
			FishStore fishStore = fishStoreDao.findById(id);
			fishStore.setSalePrice(salePrice);
			fishStoreDao.SaveToDB(fishStore);
		return true;
	}
	public boolean updateSaleWeightByFisId(int id, double saleWeight) {
		FishStore fishStore = fishStoreDao.findById(id);
		fishStore.setSaleWeight(saleWeight);
		fishStoreDao.SaveToDB(fishStore);
		return true;
	}

	public boolean writeOffFishWarWeightById(int id, double weight) {
		FishStore fishStore = fishStoreDao.findById(id);
		if(fishStore.getWarehouseWeight() - weight < 0) {
			System.out.println("You can't write Off more than warehouse weight = " + fishStore.getWarehouseWeight());
			return false;
		}
		fishStore.setWarehouseWeight(fishStore.getWarehouseWeight() - weight);
		fishStore.setWriteOffWeight(weight);
		fishStoreDao.SaveToDB(fishStore);
		return true;
	}
	@Override
	public List<FishStore> warehouseFish() {
		return fishStoreDao.warehouseFish();
	}
	@Override
	public FishStore findByName(String name) {
		return fishStoreDao.findByName(name);
	}
	@Override
	public List<FishStore> allFish() {
		return fishStoreDao.allFish();
	}
	@Override
	public List<FishStore> activeFish() {
		return fishStoreDao.activeFish();
	}

}
