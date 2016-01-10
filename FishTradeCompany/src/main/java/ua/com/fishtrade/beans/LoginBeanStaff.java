package ua.com.fishtrade.beans;

import java.io.Serializable;

import java.util.HashMap;
import java.util.Map;

//import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import ua.com.fishtrade.entity.Staff;
import ua.com.fishtrade.service.StaffService;

@Named
@Scope("session")
public class LoginBeanStaff implements Serializable{

	private static final long serialVersionUID = 1L;
	private String login;
	private String password;
	private String result;
	private Map<String, Boolean> staffLoging = new HashMap<String, Boolean>();
	
	@Inject
	private StaffService staffService;
	private Staff staff;

	public Map<String, Boolean> getStaffLoging() {
		return staffLoging;
	}

	public void setStaffLoging(Map<String, Boolean> staffLoging) {
		this.staffLoging = staffLoging;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	public String checkEmployee() {
	
		this.staff = staffService.findStaffByLogin(this.login);
		if(staff != null ){
			  if(password.equals(staff.getPassword())){
				  
				  switch(staff.getPosition()) {
				  case "secofficer" : {
					  if(staffLoging.get(login)) return "/staff/ChangeEmpPas";
					  return "/staff/SecurityAction";
				  }
				  case "accountant" :
					  {
					  if(staffLoging.get(login)) return "/staff/ChangeEmpPas";
					  return "/staff/AccountantControl";
					  }
				  case "ceo" : {
					  if(staffLoging.get(login)) return "/staff/ChangeEmpPas";
					  return "/staff/CEOControl";
				  }
				  case "storemanager" : {
					  if(staffLoging.get(login)) return "/staff/ChangeEmpPas";
					  return "/staff/StoreManagerControl";
				  }
				  case "system" : return "/staff/SecurityAction";
				  default : {
					  this.result = "No such Employee";
					  return "/staff/LoginStaff";
				  	}
				  }		  
			
			/*	  FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("staff", staff);
			   return "/staff/SecurityAction";*/
			  }
			 }
		 this.result = "No such Employee";
		return "/staff/LoginStaff";
	}
	
}
