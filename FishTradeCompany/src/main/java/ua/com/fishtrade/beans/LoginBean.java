package ua.com.fishtrade.beans;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;
import javax.faces.event.ActionEvent;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Scope;

import ua.com.fishtrade.entity.Customer;
import ua.com.fishtrade.entity.FishStore;
import ua.com.fishtrade.service.CustomerService;
import ua.com.fishtrade.service.FishStoreService;

@Named
@Scope("session")
@DependsOn
public class LoginBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String login;
	private String password;
	private String result;
	private List<FishStore> fishStoreList = null;
	@Inject
	private CustomerService custService;
	private Customer customer = null;
	private int id;
	@Inject
	private FishStoreService fishStoreService;
	private Map<String, Boolean> changePas = new HashMap<String, Boolean>();
	
	public Map<String, Boolean> getChangePas() {
		return changePas;
	}

	public void setChangePas(Map<String, Boolean> changePas) {
		this.changePas = changePas;
	}
	
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public int getId() {
		return id;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Customer getCustomer() {
		return customer;
	}
	public String register() {
		return "CustomerRegistration";
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
	
	public String changeSystemPas() {
		this.customer.setPassword(password);
		this.custService.SaveToDb(customer);
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "Main_Page?faces-redirect=true";
	}
	
	public String checkCustomer() {		
		this.customer = custService.findCustomerByLogin(this.login);
		if(customer != null ){
			  if(password.equals(customer.getPassword())){
				  if(changePas.get(login)) return "ChangeCustPas";
				  
/*				  FacesContext facesContext = FacesContext.getCurrentInstance();
				  HttpSession session = (HttpSession) facesContext.getExternalContext();
				  session.setAttribute("customer", customer);*/
/*				  FacesContext.getCurrentInstance().setAttribute("customer", customer);
				  FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("customer", customer);*/
			      /*FacesContext context = FacesContext.getCurrentInstance();
			      context.getExternalContext().getRequestMap().put("customer", customer);*/
				 // RequestContext context = RequestContext.getCurrentInstance();
/*			        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, customer.getCompany(),  null);
			        FacesContext.getCurrentInstance().addMessage(null, message);*/
			        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			        Map<String, Object> sessionMap = externalContext.getSessionMap();
			        sessionMap.put("customer", customer);
				  //context.addCallbackParam("customer", customer);
				 // this.id = this.customer.getId();
				  //this.result = "Customer is found";
/*					FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Welcome company " + customer.getCompany() ,  null);
			        FacesContext.getCurrentInstance().addMessage(null, message);*/
			   return "FishList";
			  }
			 }
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "No such Customer. Please Register",  null);
        FacesContext.getCurrentInstance().addMessage(null, message);
		this.result = "No such customer";
		return "Main_Page";
	}
	
}