package ua.com.fishtrade.beans;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import ua.com.fishtrade.entity.Staff;
import ua.com.fishtrade.service.StaffService;

@Named
@Scope("session")
public class MainManageBean {
	
	private Staff system;
	private Staff secOfficer;
	@Inject
	private StaffService staffService;
	@Inject
	private LoginBeanStaff loginStaff;
	
	

	public Staff getSecOfficer() {
		return secOfficer;
	}

	public void setSecOfficer(Staff secOfficer) {
		this.secOfficer = secOfficer;
	}

	public Staff getSystem() {
		return system;
	}

	public void setSystem(Staff system) {
		this.system = system;
	}

	@PostConstruct
	public void init() {
		system = loginStaff.getStaff();
		secOfficer = new Staff();
		secOfficer.setPosition("secofficer");
		secOfficer.setStatus('A');
		secOfficer.setPassword("P@ssw0rd");
	}
	
	public String  addSecurity() {
		
		staffService.SaveToDb(secOfficer);
		return "/staff/SecurityAction";
	}
	public String  changeSystemPas() {
		staffService.SaveToDb(system);
		return "/staff/SecurityAction";
	}

}
