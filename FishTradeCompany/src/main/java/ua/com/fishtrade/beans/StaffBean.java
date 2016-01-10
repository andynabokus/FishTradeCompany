package ua.com.fishtrade.beans;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import ua.com.fishtrade.entity.Staff;
import ua.com.fishtrade.service.StaffService;

@Named
@Scope("session")
public class StaffBean {
	public Staff employee = null;
	private List<Staff> staffList = null;
	//public Staff employee = null;
	@Inject
	private StaffService staffService;
	
	private String newPassord;


	public StaffBean() {
		employee = new Staff();
	}
	

	
	public Staff getEmployee() {
		return employee;
	}



	public void setEmployee(Staff newEmployee) {
		this.employee = newEmployee;
	}


	public List<Staff> getStaffList() {
		return staffList;
	}

	public void setStaffList(List<Staff> staffList) {
		this.staffList = staffList;
	}
	
	

	public String getNewPassord() {
		return newPassord;
	}



	public void setNewPassord(String newPassord) {
		this.newPassord = newPassord;
	}



	public String saveNewEmployee() {
		employee.setPassword(newPassord);
		newPassord = "";
	    staffService.SaveToDb(employee);  
	      return "StaffControl";
	}
	public String editStaff(String id) {
		int idEmp = Integer.valueOf(id);
		this.employee = staffService.findById(idEmp);
		return "NewStaff";
	}
	public String addEmployee(){
		employee = new Staff();
		return "NewStaff";
	}
	public void refreshStaffList() {
		staffList = staffService.findWorkingStaff();
	}
}
