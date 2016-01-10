package ua.com.fishtrade.beans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import ua.com.fishtrade.entity.ConfirmedPayment;
import ua.com.fishtrade.entity.CustOrder;
import ua.com.fishtrade.entity.FishStore;
import ua.com.fishtrade.entity.GeneralFishOrder;
import ua.com.fishtrade.service.ConfirmedPaymentService;
import ua.com.fishtrade.service.CustOrderService;
import ua.com.fishtrade.service.FishStoreService;


@Named
@Scope("session")
public class StoreManagerControlBean {
	

	private Map<Long, String> delivWeight = new HashMap<Long, String>();
	private Map<Long, String> writeOffWeight = new HashMap<Long, String>();
  	private Map<Long, Boolean> selectedStore = new HashMap<Long, Boolean>();
  	private Map<String, Boolean> selOrderForDispatching = new HashMap<String, Boolean>();
	private List<FishStore> fishStoreList = null;
	private List<ConfirmedPayment> confOrdersDispatching= null;
	private List<CustOrder> custOrderDispatching = null;
	private List<CustOrder> fullcustOrderDispatching = null;
	private List<FishStore> dispatchingList = null;
	
	private ArrayList<Map.Entry<CustOrder,FishStore>> arrayCustOrder = null;
	
	@Inject
	private FishStoreService fishStoreService;
	@Inject
	private ConfirmedPaymentService confPayServ;
	@Inject
	private CustOrderService custOrdServ;
	
	
	public ArrayList<Map.Entry<CustOrder,FishStore>> getArrayCustOrder() {
		return arrayCustOrder;
	}

	public void setArrayCustOrder(ArrayList<Map.Entry<CustOrder,FishStore>> arrayCustOrder) {
		this.arrayCustOrder = arrayCustOrder;
	}

	public Map<String, Boolean> getSelOrderForDispatching() {
		return selOrderForDispatching;
	}

	public void setSelOrderForDispatching(
			Map<String, Boolean> selOrderForDispatching) {
		this.selOrderForDispatching = selOrderForDispatching;
	}

	public List<FishStore> getDispatchingList() {
		return dispatchingList;
	}

	public void setDispatchingList(List<FishStore> dispatchingList) {
		this.dispatchingList = dispatchingList;
	}

	public List<CustOrder> getFullcustOrderDispatching() {
		return fullcustOrderDispatching;
	}

	public void setFullcustOrderDispatching(List<CustOrder> fullcustOrderDispatching) {
		this.fullcustOrderDispatching = fullcustOrderDispatching;
	}

	public List<ConfirmedPayment> getConfOrdersDispatching() {
		return confOrdersDispatching;
	}

	public void setConfOrdersDispatching(
			List<ConfirmedPayment> confOrdersDispatching) {
		this.confOrdersDispatching = confOrdersDispatching;
	}

	public List<CustOrder> getCustOrderDispatching() {
		return custOrderDispatching;
	}

	public void setCustOrderDispatching(List<CustOrder> custOrderDispatching) {
		this.custOrderDispatching = custOrderDispatching;
	}


	public Map<Long, Boolean> getSelectedStore() {
		return selectedStore;
	}

	public void setSelectedStore(Map<Long, Boolean> selectedStore) {
		this.selectedStore = selectedStore;
	}

	public Map<Long, String> getWriteOffWeight() {
		return writeOffWeight;
	}

	public void setWriteOffWeight(Map<Long, String> writeOffWeight) {
		this.writeOffWeight = writeOffWeight;
	}

	public Map<Long, String> getDelivWeight() {
		return delivWeight;
	}

	public void setDelivWeight(Map<Long, String> delivWeight) {
		this.delivWeight = delivWeight;
	}

	public List<FishStore> getFishStoreList() {
		return fishStoreList;
	}

	public void setFishStoreList(List<FishStore> fishStoreList) {
		this.fishStoreList = fishStoreList;
	}
	
	public void refreshList(){
		fishStoreList = fishStoreService.fishForSale();
	}
	
	public void dispatchingOrders() {
		 Map<CustOrder,FishStore> combineOrder = new HashMap<>();
		fullcustOrderDispatching = new ArrayList<>();
		//dispatchingList = new ArrayList<>();
		confOrdersDispatching = confPayServ.readyForDispatching();
		for(ConfirmedPayment confPay : confOrdersDispatching) {
			custOrderDispatching = custOrdServ.findByOrderNum(confPay.getOrderNum());
			for(CustOrder custOrder : custOrderDispatching) {
				combineOrder.put(custOrder, fishStoreService.findById(custOrder.getFishId()));
			fullcustOrderDispatching.add(custOrder);
				//dispatchingList.add(fishStoreService.findById(custOrder.getFishId()));
			}
		}
		Set<Map.Entry<CustOrder,FishStore>> arraySet = combineOrder.entrySet();
		arrayCustOrder = new ArrayList<Map.Entry<CustOrder,FishStore>>(arraySet);
	}
	
	public String updateItem() {
		if(selectedStore.isEmpty()) return "StoreManageFish";
		for (FishStore fish : fishStoreList) {
			if(selectedStore.get(fish.getId())) {
				 int changeCounter = 0;
				 if(!delivWeight.get(fish.getId()).isEmpty()) {
					 fish.setWarehouseWeight(fish.getWarehouseWeight() + Double.parseDouble(delivWeight.get(fish.getId())));
					 changeCounter++;
					 }
				 if(!writeOffWeight.get(fish.getId()).isEmpty()) {
					 fish.setWarehouseWeight(fish.getWarehouseWeight() - Double.parseDouble(writeOffWeight.get(fish.getId())));
					 fish.setWriteOffWeight(fish.getWriteOffWeight() + Double.parseDouble(writeOffWeight.get(fish.getId())));
					 changeCounter++;
					 }
				 if(changeCounter != 0) {
				 fishStoreService.SaveToDB(fish);
				 }
			
			}
		
		}
		return "null";
		}
	
	public String dispatchItem() {
		if(selOrderForDispatching.isEmpty()) return "null";
		FishStore fish = null;
		ConfirmedPayment confPay;
		for(CustOrder custOrder: fullcustOrderDispatching) {
			if(selOrderForDispatching.get(custOrder.getOrderNum())) {
				fish = fishStoreService.findById(custOrder.getFishId());
				fish.setWarehouseWeight(fish.getWarehouseWeight() - custOrder.getWeight());
				fish.setSaleWeight(fish.getSaleWeight() - custOrder.getWeight());
				confPay = confPayServ.findByOrderNum(custOrder.getOrderNum());
				confPay.setShipmentAvailable('Y');
				fishStoreService.SaveToDB(fish);
				confPayServ.SaveToDb(confPay);
			}
		}
		this.dispatchingOrders();
		return "null";
	}
	
}
