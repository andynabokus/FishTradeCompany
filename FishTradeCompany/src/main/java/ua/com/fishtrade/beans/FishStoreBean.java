package ua.com.fishtrade.beans;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
/*import javax.faces.bean.ManagedProperty;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;*/
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.springframework.context.annotation.DependsOn;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import ua.com.fishtrade.entity.ConfirmedPayment;
import ua.com.fishtrade.entity.CustOrder;
import ua.com.fishtrade.entity.Customer;
import ua.com.fishtrade.entity.FishStore;
import ua.com.fishtrade.service.ConfirmedPaymentService;
import ua.com.fishtrade.service.CustOrderService;
import ua.com.fishtrade.service.FishStoreService;
import ua.com.fishtrade.service.StaffService;


@Named
@Scope("session")
@DependsOn
public class FishStoreBean {
	
	private List<FishStore> fishStoreList = null;
	private List<FishStore> warehouseList = null;
	private List<FishStore> storeManagerList = null;
	private List<FishStore> ceoFishList = null;
  	private Map<Long, Boolean> selected = new HashMap<Long, Boolean>();
	private Map<Long, Boolean> warehouseSelected = new HashMap<Long, Boolean>();
	private Map<Long, Boolean> storeManSelected = new HashMap<Long, Boolean>();
	private Map<Long, String> orderedWeight = new HashMap<Long, String>();
	private Map<Long, String> newSaleWeight = new HashMap<Long, String>();
	private Map<Long, String> newPrice = new HashMap<Long, String>();
	private Map<Long, String> newWriteOffWeight = new HashMap<Long, String>();
	private Map<Long, String> storeManWriteOffWeight = new HashMap<Long, String>();
	private Map<Long, String> newComments = new HashMap<Long, String>();
	private Map<Long, String> storeManComments = new HashMap<Long, String>();
	private Map<Long, String> storeManDelFishWeight = new HashMap<Long, String>();
	private Map<Long, String> storeManDelivDay = new HashMap<Long, String>();
	private String genFishOrder;
	private Date date;
	private FishStore newFish;
	private double totalSumOrder;
	private ArrayList<Map.Entry<String, Double>> arrayOrder = null;
	private String orderNumber;
	private CustOrder order = null;
	private Customer customer = null;
	@Inject
	private CustOrderService custOrderServ;
	@Inject
	private StaffService staffServ;
	@Inject
	private FishStoreService fishStoreService;
	@Inject
	private ConfirmedPaymentService confPayServ;
/*	@Inject
	private LoginBean loginBean;
	
	
	

	public LoginBean getLoginBean() {
		return loginBean;
	}

	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}*/

	private static String makeHashString(String plain) throws NoSuchAlgorithmException {
        final int MD_PASSWORD_LENGTH = 16;
        final String HASH_ALGORITHM = "SHA1";
        String hash = null;
         try {
                MessageDigest md = MessageDigest.getInstance(HASH_ALGORITHM);
                md.update(plain.getBytes());
                BigInteger hashint = new BigInteger(1, md.digest());
                hash = hashint.toString(MD_PASSWORD_LENGTH);
            } catch (NoSuchAlgorithmException nsae) {
                throw(nsae);
            }
        return hash;
    }
    
	@PostConstruct
	public void init() {
		// FacesContext context = FacesContext.getCurrentInstance();
		/* customer = (Customer) 
				 context .getCurrentInstance().getExternalContext().getApplicationMap().get("customer");*/
/*		  FacesContext facesContext = FacesContext.getCurrentInstance();
		  HttpSession session = (HttpSession) facesContext.getExternalContext();
		  session.setAttribute("customer", customer);
		 customer =   (Customer) session.getAttribute("customer");*/
		//customer   =  loginBean.getCustomer();
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> sessionMap = externalContext.getSessionMap();
		customer = (Customer) sessionMap.get("customer");
/*	   ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
	   Map<String, Object> requestParameterMap = externalContext.getRequestParameterMap();
	   id = Integer.parseInt(requestParameterMap.get("id"));*/
	}
	
	
	public Customer getCustomer() {
		return customer;
	}


	public FishStoreBean () {
		order = new CustOrder();
		newFish = new FishStore();
		ceoFishList = new ArrayList<>();
		//customer = (Customer) FacesContext .getCurrentInstance().getExternalContext().getApplicationMap().get("customer");
		 //this.id = customer.getId();
	}
	
	
	
	public Map<Long, String> getOrderedWeight() {
		return orderedWeight;
	}

	public void setOrderedWeight(Map<Long, String> orderedWeight) {
		this.orderedWeight = orderedWeight;
	}

	public Map<Long, Boolean> getSelected() {
		return selected;
	}



	public void setSelected(Map<Long, Boolean> selected) {
		this.selected = selected;
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
	
	public void storeManrefreshList(){
		storeManagerList = fishStoreService.activeFish();
	}
	
	
	public List<FishStore> getStoreManagerList() {
		return storeManagerList;
	}

	public void setStoreManagerList(List<FishStore> storeManagerList) {
		this.storeManagerList = storeManagerList;
	}

	public void refreshWarehouseList(){
		warehouseList = fishStoreService.warehouseFish();
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

	public Map<Long, Boolean> getWarehouseSelected() {
		return warehouseSelected;
	}

	public void setWarehouseSelected(Map<Long, Boolean> warehouseSelected) {
		this.warehouseSelected = warehouseSelected;
	}
	
	public Map<Long, Boolean> getStoreManSelected() {
		return storeManSelected;
	}

	public void setStoreManSelected(Map<Long, Boolean> storeManSelected) {
		this.storeManSelected = storeManSelected;
	}
	
	public Map<Long, String> getStoreManDelivDay() {
		return storeManDelivDay;
	}

	public void setStoreManDelivDay(Map<Long, String> storeManDelivDay) {
		this.storeManDelivDay = storeManDelivDay;
	}
		
	public Map<Long, String> getStoreManDelFishWeight() {
		return storeManDelFishWeight;
	}

	public void setStoreManDelFishWeight(Map<Long, String> storeManDelFishWeight) {
		this.storeManDelFishWeight = storeManDelFishWeight;
	}
	
	
	public Map<Long, String> getStoreManWriteOffWeight() {
		return storeManWriteOffWeight;
	}

	public void setStoreManWriteOffWeight(Map<Long, String> storeManWriteOffWeight) {
		this.storeManWriteOffWeight = storeManWriteOffWeight;
	}
	
	public Map<Long, String> getStoreManComments() {
		return storeManComments;
	}

	public void setStoreManComments(Map<Long, String> storeManComments) {
		this.storeManComments = storeManComments;
	}

	public String getGenFishOrder() {
		return genFishOrder;
	}

	public void setGenFishOrder(String genFishOrder) {
		this.genFishOrder = genFishOrder;
	}
	
	public FishStore getNewFish() {
		return newFish;
	}

	public void setNewFish(FishStore newFish) {
		this.newFish = newFish;
	}
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	public double getTotalSumOrder() {
		return totalSumOrder;
	}

	public void setTotalSumOrder(double totalSumOrder) {
		this.totalSumOrder = totalSumOrder;
	}

	public ArrayList<Map.Entry<String, Double>> getArrayOrder() {
		return arrayOrder;
	}

	public void setArrayOrder(ArrayList<Map.Entry<String, Double>> arrayOrder) {
		this.arrayOrder = arrayOrder;
	}
	
	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String cartCheck() {
		  
		Map<String,Double> order = new HashMap<String,Double>();	 
		totalSumOrder = 0.0;  
	        for (FishStore fish : fishStoreList) {
	            if (selected.get(fish.getId())) {
	            	order.put(fish.getFishName(), Double.parseDouble(orderedWeight.get(fish.getId())));
	            	totalSumOrder += fish.getSalePrice() * Double.parseDouble(orderedWeight.get(fish.getId()));
	            }
	        }
			Set<Map.Entry<String, Double>> orderSet = order.entrySet();
			arrayOrder = new ArrayList<Map.Entry<String, Double>>(orderSet);
/*	        Map<String,Object> options = new HashMap<String, Object>();
	        options.put("resizable", false);
	        options.put("draggable", false);
	        options.put("modal", true);
	        RequestContext.getCurrentInstance().openDialog("CheckCart", options, null);*/
	        return "CheckCart";
	}
	
    
	public String submit() {
		 Collection<FishStore> fishList = new ArrayList<>();
	        //Date of order
			Date today = Calendar.getInstance().getTime();
	        DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
	        String datestring = df.format(today).toString();
	      //creates SHA1 hash of 16 digits for the order number
	        try {
				String hash = makeHashString(datestring);
				int dateStrLen = datestring.length();
				orderNumber = datestring+hash.substring(dateStrLen,dateStrLen+5);
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		//Insert selected fish in the Table CustOrder inside database 
	        double totalSumOrder = 0.0;
	        for (FishStore fish : fishStoreList) {
	            if (selected.get(fish.getId())) {
	            	fishList.add(fish);
	            	order.setFishId(fish.getId());
	            	order.setFish(fishList);
	            	order.setCustomer(customer);
	    	        order.setWeight(Double.parseDouble(orderedWeight.get(fish.getId())));
	    	        order.setCopySalePrice(fish.getSalePrice());
	    	        order.setOrderNum(orderNumber);
	    	        order.setOrderDate(new java.sql.Date(today.getTime()));
	    	        totalSumOrder += fish.getSalePrice() * Double.parseDouble(orderedWeight.get(fish.getId()));
	    	        custOrderServ.SaveToDB(order);	    	        
	            }
	        }
			ConfirmedPayment confPay = new ConfirmedPayment();
			confPay.setCopyPercentPaym(customer.getPercentPaym());
			confPay.setPayedMoney(0.0);
			confPay.setShipmentAvailable('N');
			//id = 1 means that this is system not real employee
			confPay.setStaff(staffServ.findById(1));
			confPay.setOrderNum(orderNumber);
			//add Total Sum
			confPay.setTotalSumOrder(totalSumOrder);
			confPayServ.SaveToDb(confPay);

	        return "PrintOrder";
	    }
		public String back() {
			selected.clear();
			orderedWeight.clear();
			return "FishList";
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
					 fish.setWriteOffWeight(Double.parseDouble(newWriteOffWeight.get(fish.getId())));
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
		public String storeManChange() {
			 for (FishStore fish : storeManagerList) {
				 if (storeManSelected.get(fish.getId())) {
					 int changeCounter = 0;
					 if(!storeManDelFishWeight.get(fish.getId()).isEmpty()) {
						 fish.setWarehouseWeight(fish.getWarehouseWeight() + (Double.parseDouble(storeManDelFishWeight.get(fish.getId()))));
						 changeCounter++;
						 }
					 if(!storeManDelivDay.get(fish.getId()).isEmpty()) {
						 DateFormat format = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH);
						 Date date=null;
						try {
							date = format.parse(storeManDelivDay.get(fish.getId()));
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						 fish.setDeliveryDate(new java.sql.Date(date.getTime()));
						 changeCounter++;
						 }
					 if(!storeManWriteOffWeight.get(fish.getId()).isEmpty()) {
						 fish.setWarehouseWeight(fish.getWarehouseWeight() - fish.getWriteOffWeight());
						 fish.setWriteOffWeight(fish .getWriteOffWeight() + Double.parseDouble(storeManWriteOffWeight.get(fish.getId())));
						 changeCounter++;
						 }
					 if(!storeManComments.get(fish.getId()).isEmpty()) {
						 fish.setComments(storeManComments.get(fish.getId()));
						 changeCounter++;
						 }
					 if(changeCounter != 0) {
					 fishStoreService.SaveToDB(fish);
					 }
				 
				 }
			 
			 }
			 return "StoreManagerControl";
		}
		
		public String addOrder() {
			
			return "CEOControl";
		}
		
		public String addFish() {
			
/*			 DateFormat format = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH);
			 Date date=null;
			try {
				date = format.parse(this.date);
			} catch (ParseException e) {
				e.printStackTrace();
			}*/
			 newFish.setDeliveryDate(new java.sql.Date(date.getTime()));
			 //newFish.setGeneralFishOrders(genFishServ.findOrderByNum(this.genFishOrder));
			 newFish.setWarehouseWeight(0.0);
			 fishStoreService.SaveToDB(newFish);
			 ceoFishList.add(newFish);
			 RequestContext.getCurrentInstance().reset("form:panel");
			return "AddFishItem";
		}
		
		public String logOut() {
			 FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
			return "Main_Page?faces-redirect=true";
		}
	 
	    public void onDateSelect(SelectEvent event) {
	        FacesContext facesContext = FacesContext.getCurrentInstance();
	        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
	        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", format.format(event.getObject())));
	    }
}
		
