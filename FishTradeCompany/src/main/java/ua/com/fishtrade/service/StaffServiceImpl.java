package ua.com.fishtrade.service;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ua.com.fishtrade.dao.StaffDao;
import ua.com.fishtrade.entity.Staff;
@Component
@Named
public class StaffServiceImpl implements StaffService {
	@Inject
	private StaffDao staffDao;
	public Staff findById(int id) {
		return staffDao.findById(id);
	}
	@Transactional
	public void SaveToDb(Staff employee) {
		staffDao.SaveToDb(employee);
	}
	
	@Transactional
	public boolean updateStatusStaffById(int id, char status) {
		Staff employee = staffDao.findById(id);
		employee.setStatus(status);
		staffDao.SaveToDb(employee);
		return true;
	}
	@Transactional
	public List<Integer> findIdStaff(String firstName, String lastName,
			String position) {
		return staffDao.findIdStaff(firstName, lastName, position);
	}
	@Override
	public List<Staff> findWorkingStaff() {
		return staffDao.findWorkingStaff();
	}
	@Override
	public Staff findStaffByLogin(String Login) {
		return staffDao.findStaffByLogin(Login);
	}


}
