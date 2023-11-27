package fr.diginamic.dao;

import java.util.List;

import javax.persistence.EntityManager;
/** Interface DAO, implémenté par toutes les classes de service. Détails, c.f ProduitDao */
public interface Dao<T> {
	
	List<T> selectAll(EntityManager em);
	T insertIfNotExistCustom(EntityManager em, T t);
	int updateCustom(EntityManager em, T t1, T t2);
	boolean deleteCustom(EntityManager em, T t);
	T selectCustom(EntityManager em, T t);

}
