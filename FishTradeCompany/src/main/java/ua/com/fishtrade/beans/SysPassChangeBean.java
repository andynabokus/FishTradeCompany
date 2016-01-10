package ua.com.fishtrade.beans;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import ua.com.fishtrade.entity.Staff;
import ua.com.fishtrade.service.StaffService;

@Named
@Scope("session")
public class SysPassChangeBean {
	
	private Staff employee = null;
	@Inject
	private LoginBeanStaff loginBeanStaff;
	@Inject
	private StaffService staffService;
	@PostConstruct
	public void init() {
		this.employee = this.loginBeanStaff.getStaff();
	}
	
	public Staff getEmployee() {
		return employee;
	}

	public void setEmployee(Staff employee) {
		this.employee = employee;
	}

	public String  changeSystemPas() {
		staffService.SaveToDb(employee);
		return "/staff/LoginStaff";
	}
	
}
