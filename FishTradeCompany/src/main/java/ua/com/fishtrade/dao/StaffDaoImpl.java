package ua.com.fishtrade.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import ua.com.fishtrade.entity.Staff;
@Component
@Repository
public class StaffDaoImpl implements StaffDao {
	 @PersistenceContext
	 private EntityManager em;
	public Staff findById(int id) {
	Staff staff = null;
	staff = em.find(Staff.class, id);
	return staff;
	}
	public void SaveToDb(Staff employee) {
		if(employee.getId() == 0) {
			em.persist(employee);
		} else  em.merge(employee);
	}

	public List<Integer> findIdStaff(String firstName, String lastName,
			String position) {
		String sqlQuery = "SELECT s.id FROM Staff s "
				+ "WHERE s.firstName LIKE " + "'" + firstName + "' "
				+ "AND s.lastName LIKE " + "'" + lastName + "' "
				+ "AND s.position LIKE " + "'" + position + "'";
		TypedQuery<Integer> query = em.createQuery(sqlQuery, Integer.class);
		return query.getResultList();
	}
	@Override
	public List<Staff> findWorkingStaff() {
		String sqlQuery = "SELECT s FROM Staff s "
				+ "WHERE s.status LIKE 'A'"
				+ "AND s.id > 1";
		TypedQuery<Staff> query = em.createQuery(sqlQuery, Staff.class);
		return query.getResultList();
	}
	@Override
	@SuppressWarnings("finally")
	public Staff findStaffByLogin(String Login) {
		String sqlQuery = "SELECT s FROM Staff s "
				+ "WHERE s.login LIKE " + "'" + Login + "' ";
		TypedQuery<Staff> query = em.createQuery(sqlQuery, Staff.class);
		Staff staff = null;
		 try {
			 staff =  query.getSingleResult();			 
		 } catch (javax.persistence.NoResultException e){
			staff = null;			 
		 } finally {
			 return staff;
		 }	
	}
	
}
