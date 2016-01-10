package ua.com.fishtrade.beans;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;
import org.springframework.context.annotation.Scope;

import ua.com.fishtrade.entity.Customer;
import ua.com.fishtrade.entity.FishStore;
import ua.com.fishtrade.entity.GeneralFishOrder;
import ua.com.fishtrade.service.CustomerService;
import ua.com.fishtrade.service.FishStoreService;
import ua.com.fishtrade.service.GenFishOrderService;

@Named
@Scope("session")
public class CEOControlBean {
	
	private List<FishStore> ceoFishList = null;
	private List<FishStore> warehouseList = null;
	private List<Customer> customerList = null;
	private Map<Long, String> newPercent = new HashMap<Long, String>();
	private Map<Long, Boolean> customerSelected = new HashMap<Long, Boolean>();
	private Map<Long, String> newSaleWeight = new HashMap<Long, String>();
	private Map<Long, Boolean> warehouseSelected = new HashMap<Long, Boolean>();
	private Map<Long, String> newPrice = new HashMap<Long, String>();
	private Map<Long, String> newWriteOffWeight = new HashMap<Long, String>();
	private Map<Long, String> newComments = new HashMap<Long, String>();
	private FishStore newFish;
	private GeneralFishOrder genFishOrder;
	private Date deliveryDate;
	double totOrderedWeight = 0;
	
	@Inject
	private GenFishOrderService genFishOrd;
	@Inject
	private FishStoreService fishStoreService;
	@Inject
	private CustomerService custService;
	
	public CEOControlBean() {
		ceoFishList = new ArrayList<>();
		newFish = new FishStore();
		genFishOrder = new GeneralFishOrder();
	}
	
	public FishStore getNewFish() {
		return newFish;
	}

	public void setNewFish(FishStore newFish) {
		this.newFish = newFish;
	}

	public List<FishStore> getCeoFishList() {
		return ceoFishList;
	}

	public void setCeoFishList(List<FishStore> ceoFishList) {
		this.ceoFishList = ceoFishList;
	}

	public GeneralFishOrder getGenFishOrder() {
		return genFishOrder;
	}

	public void setGenFishOrder(GeneralFishOrder genFishOrder) {
		this.genFishOrder = genFishOrder;
	}
	
	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	
	public List<FishStore> getWarehouseList() {
		return warehouseList;
	}

	public void setWarehouseList(List<FishStore> warehouseList) {
		this.warehouseList = warehouseList;
	}
		
	public Map<Long, String> getNewSaleWeight() {
		return newSaleWeight;
	}

	public void setNewSaleWeight(Map<Long, String> newSaleWeight) {
		this.newSaleWeight = newSaleWeight;
	}

	
	
	public Map<Long, Boolean> getWarehouseSelected() {
		return warehouseSelected;
	}

	public void setWarehouseSelected(Map<Long, Boolean> warehouseSelected) {
		this.warehouseSelected = warehouseSelected;
	}
	
	public Map<Long, String> getNewPrice() {
		return newPrice;
	}

	public void setNewPrice(Map<Long, String> newPrice) {
		this.newPrice = newPrice;
	}

	public Map<Long, String> getNewWriteOffWeight() {
		return newWriteOffWeight;
	}

	public void setNewWriteOffWeight(Map<Long, String> newWriteOffWeight) {
		this.newWriteOffWeight = newWriteOffWeight;
	}
	
	public Map<Long, String> getNewComments() {
		return newComments;
	}

	public void setNewComments(Map<Long, String> newComments) {
		this.newComments = newComments;
	}
	
	public List<Customer> getCustomerList() {
		return customerList;
	}

	public void setCustomerList(List<Customer> customerList) {
		this.customerList = customerList;
	}
	
	public void refreshWarehouseList(){
		warehouseList = fishStoreService.warehouseFish();
	}
	
	public void refreshCustomerList(){
		customerList = custService.customers();
	}

	public Map<Long, String> getNewPercent() {
		return newPercent;
	}

	public void setNewPercent(Map<Long, String> newPercent) {
		this.newPercent = newPercent;
	}

	public Map<Long, Boolean> getCustomerSelected() {
		return customerSelected;
	}

	public void setCustomerSelected(Map<Long, Boolean> customerSelected) {
		this.customerSelected = customerSelected;
	}

	public String addFish() {
		
		this.newFish.setDeliveredWeight(0);
		this.newFish.setDeliveryDate(new java.sql.Date(deliveryDate.getTime()));
		ceoFishList.add(newFish);
		totOrderedWeight += newFish.getOrderedWeight();
		return "AddFishItem";
		
	}
	
	public String submitFishItems() {
		genFishOrder.setOrderedWeight(totOrderedWeight);
		totOrderedWeight = 0;
		for(FishStore fs : ceoFishList) {
			fs.setGeneralFishOrders(genFishOrder);
			
		}
		genFishOrder.setFish(ceoFishList);
		genFishOrd.SaveToDb(genFishOrder);
		return "CEOControl";
	}
	
	public String submitOrder() {
		
		return "AddFishItem";
	}
	
	
	public String ceoChange(){
		
		if(warehouseSelected.isEmpty()) return "ManageSalingProcess";
		for (FishStore fish : warehouseList) {
			 if (warehouseSelected.get(fish.getId())) {
				 int changeCounter = 0;
				 if(!newSaleWeight.get(fish.getId()).isEmpty()) {
				 fish.setSaleWeight(Double.parseDouble(newSaleWeight.get(fish.getId())));
				 changeCounter++;
				 }
				 if(!newPrice.get(fish.getId()).isEmpty()) {
				 fish.setSalePrice(Double.parseDouble(newPrice.get(fish.getId())));
				 changeCounter++;
				 }
				 if(!newWriteOffWeight.get(fish.getId()).isEmpty()) {
				 fish.setWriteOffWeight(fish.getWriteOffWeight() + Double.parseDouble(newWriteOffWeight.get(fish.getId())));
				 fish.setWarehouseWeight(fish.getWarehouseWeight() - Double.parseDouble(newWriteOffWeight.get(fish.getId())));
				 changeCounter++;
				 }
				 if(!newComments.get(fish.getId()).isEmpty()) {
				 fish.setComments(newComments.get(fish.getId()));
				 changeCounter++;
				 }
				 if(changeCounter != 0) {
				 fishStoreService.SaveToDB(fish);
				 }
			 }
			
		}
		
		return "ManageSalingProcess";
		
	}
	
	public String ceoPaymPercChange() {
		for(Customer cust : customerList) {
			if(customerSelected.get(cust.getId())) {
				cust.setPercentPaym(Double.parseDouble(newPercent.get(cust.getId())));
				custService.SaveToDb(cust);
			}
		}
		return "CustomerControl";
	}
	
	 public void onDateSelect(SelectEvent event) {
	        FacesContext facesContext = FacesContext.getCurrentInstance();
	        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
	        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", format.format(event.getObject())));
	    }

}
