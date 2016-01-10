package ua.com.fishtrade.beans;

import java.util.ArrayList;
import java.util.Collection;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import ua.com.fishtrade.entity.FishStore;
import ua.com.fishtrade.entity.GeneralFishOrder;
import ua.com.fishtrade.service.FishStoreService;
import ua.com.fishtrade.service.GenFishOrderService;

@Named
@Scope("session")
public class NewShipmentBean {
	
	private GeneralFishOrder genFishOrder;
	private FishStore fishStore;
//	private Collection<FishStore> listFish;
 	@Inject
	private GenFishOrderService genFishService;
 	@Inject
 	private FishStoreService fishStoreServ;
 	double totalWeight = 0.0;
	
	
	public NewShipmentBean() {
		
		this.genFishOrder = new GeneralFishOrder();
		this.fishStore = new FishStore();
		//listFish = new ArrayList<>();
		
	}

	public GeneralFishOrder getGenFishOrder() {
		return genFishOrder;
	}

	public void setGenFishOrder(GeneralFishOrder genFishOrder) {
		this.genFishOrder = genFishOrder;
	}
	
	
	public FishStore getFishStore() {
		return fishStore;
	}

	public void setFishStore(FishStore fishStore) {
		this.fishStore = fishStore;
	}

/*	@SuppressWarnings("finally")
	public String addFish(String name) {
		totalWeight += fishStore.getOrderedWeight();
		try{
			fishStoreServ.findByName(name);
			
		}
		catch(javax.persistence.NoResultException e) {
			listFish.add(fishStore);
			fishStore.setGeneralFishOrders(this.genFishOrder);
		}
		finally { 
		return "AddNewShipment";
		}
	}*/
	
	public String addOrder() {
		
		this.genFishOrder.setOrderStatus('N');			
		genFishService.SaveToDb(this.genFishOrder);
		return "AddNewShipment";
	}

}
