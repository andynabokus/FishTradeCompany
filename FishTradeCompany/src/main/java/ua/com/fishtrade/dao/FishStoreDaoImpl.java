package ua.com.fishtrade.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import ua.com.fishtrade.entity.FishStore;

@Component
@Repository
public class FishStoreDaoImpl implements FishStoreDao{
	@PersistenceContext
	private EntityManager em;
	public FishStore findById(int id) {
		 FishStore fishStore = null;
		 fishStore = em.find(FishStore.class, id);
		 return fishStore;	
	}
	public void SaveToDB(FishStore newFishType) {
		if(newFishType.getId() == 0) {
			em.persist(newFishType);	
		} else em.merge(newFishType);
			
	}
	//Need to find row in the FishStore by ID
	public Integer orderedFishId(String name, Double price) {
		String sqlQuery = "SELECT f.id FROM FishStore f "
				+ "WHERE f.fishName LIKE " + "'" + name + "' "
				+ "AND f.salePrice = " + price;
		TypedQuery<Integer> query = em.createQuery(sqlQuery, Integer.class);
		return query.getSingleResult();
	}
	public List<FishStore> availableFishForSale() {
		String txt = "SELECT fishSt ";   
	      txt += "FROM FishStore fishSt ";
	      txt += "WHERE fishSt.salePrice > 0 ";
	      txt += "AND fishSt.saleWeight > 0 ";
	      txt += "AND fishSt.deliveryDate IS NOT NULL";
	      TypedQuery<FishStore> query = em.createQuery(txt, FishStore.class);
	      return query.getResultList();
	}
	public Double getWarehouseWeightById(int id) {
		String sqlQuery = "SELECT f.warehouseWeight FROM FishStore f WHERE f.id = " + id;
		TypedQuery<Double> query = em.createQuery(sqlQuery, Double.class);
		return query.getSingleResult();
	}
	public int findFishByGenFishOrderId(int id) {
		String sqlQuery = "SELECT f From FishStore f " + 
						  "WHERE f.idGenFishOrder = " + id;
		TypedQuery<Integer> query = em.createQuery(sqlQuery, Integer.class);
		return query.getSingleResult();
	}
	public List<Integer> findIdsByFishNameForSale(String fishName) {
		String sqlQuery = "SELECT f.id From FishStore f " + 
				  "WHERE f.fishName LIKE '" + fishName + "' " +
				  "AND f.warehouseWeight > 0 " +
				  "AND f.deliveryDate IS NOT NULL";
		 TypedQuery<Integer> query = em.createQuery(sqlQuery, Integer.class);
	     return query.getResultList();
	}
	public List<String> distinctFish() {
		String sqlQuery = "SELECT DISTINCT f.fishName " +
						  "FROM FishStore f";
   		TypedQuery<String> query = em.createQuery(sqlQuery, String.class);
   		return query.getResultList();
	}
	@Override
	public List<FishStore> warehouseFish() {
		String txt = "SELECT fishSt ";   
	      txt += "FROM FishStore fishSt ";
	      txt += "WHERE fishSt.deliveryDate IS NOT NULL";
	      TypedQuery<FishStore> query = em.createQuery(txt, FishStore.class);
	      return query.getResultList();
	}
	@Override
	public FishStore findByName(String name) {
		String txt = "SELECT fishSt ";   
	      txt += "FROM FishStore fishSt ";
	      txt += "WHERE fishSt.fishName LIKE '" + name + "'";
	      TypedQuery<FishStore> query = em.createQuery(txt, FishStore.class);
	      return query.getSingleResult();
	}
	@Override
	public List<FishStore> allFish() {
		String txt = "SELECT fishSt ";   
	      txt += "FROM FishStore fishSt ";
	      TypedQuery<FishStore> query = em.createQuery(txt, FishStore.class);
	      return query.getResultList();
	}
	@Override
	public List<FishStore> activeFish() {
		String txt = "Select fishSt "
				+ "FROM FishStore fishSt "
				+ "WHERE fishSt.deliveredWeight - fishSt.warehouseWeight <= fishSt.warehouseWeight";   
	      TypedQuery<FishStore> query = em.createQuery(txt, FishStore.class);
	      return query.getResultList();
	}	
}
