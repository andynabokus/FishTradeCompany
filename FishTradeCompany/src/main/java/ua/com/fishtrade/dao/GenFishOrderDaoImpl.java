package ua.com.fishtrade.dao;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import ua.com.fishtrade.entity.FishStore;
import ua.com.fishtrade.entity.GeneralFishOrder;

@Component
@Repository
public class GenFishOrderDaoImpl implements GenFishOrderDao{
    @PersistenceContext
    private EntityManager em;
	
	public GeneralFishOrder findById(int id) {
		 GeneralFishOrder genFishOredr = null;
		 genFishOredr = em.find(GeneralFishOrder.class, id);
		 return genFishOredr;
	
	}
	

	public Collection<FishStore> findByGenFishOrderId(int id) {
		TypedQuery<FishStore> query = em.createQuery("SELECT f FROM FishStore f WHERE f.idGenFishOrder = " + id, FishStore.class);
		return query.getResultList();
	}


	public void SaveToDb(GeneralFishOrder genFishOrder) {
		if(genFishOrder.getId() == 0) {
			em.persist(genFishOrder);
		} else 	em.merge(genFishOrder);
	}


	@Override
	public GeneralFishOrder findOrderByNum(String num) {
		String sqlQuery = "SELECT genOrder FROM  GeneralFishOrder genOrder "
				+ "WHERE genOrder.orderNum LIKE " + "'" + num + "' ";
		TypedQuery<GeneralFishOrder> query = em.createQuery(sqlQuery, GeneralFishOrder.class);
		return query.getSingleResult();
	}



}
