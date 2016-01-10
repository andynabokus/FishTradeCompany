package ua.com.fishtrade.dao;


import java.util.List;

import ua.com.fishtrade.entity.Staff;

public interface StaffDao {
	
	public Staff findById(int id);
	public void SaveToDb(Staff employee);
	public List<Integer> findIdStaff(String firstName, String lastName, String position);
	public List<Staff> findWorkingStaff();
	public Staff findStaffByLogin(String Login);

}
