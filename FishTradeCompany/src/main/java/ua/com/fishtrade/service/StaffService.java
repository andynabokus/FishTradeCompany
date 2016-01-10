package ua.com.fishtrade.service;

import java.util.List;

import ua.com.fishtrade.entity.Staff;

public interface StaffService {
	
	public Staff findById(int id);
	public void SaveToDb(Staff employee);
	public List<Integer> findIdStaff(String firstName, String lastName, String position);
	public boolean updateStatusStaffById(int id, char status);
	public List<Staff> findWorkingStaff();
	public Staff findStaffByLogin(String Login);


}
