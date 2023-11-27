package fr.diginamic.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import fr.diginamic.entite.Edition;

public class EditionDao implements Dao<Edition> {

	private EntityManager em;
	private TypedQuery<Edition> querySelect;
	
	
	
	public EditionDao(EntityManager em) {
		this.em = em;
		this.querySelect = this.em.createQuery(
				"select e from Edition e " 
						+ "where e.annee= :p1 " 
						+ "and e.saison= :p2 " 
						+ "and e.ville= :p3",
					Edition.class);
	}

	@Override
	public List<Edition> selectAll(EntityManager em) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Edition insertIfNotExistCustom(EntityManager em, Edition t) {
		// TODO Auto-generated method stub
		return null;
	}

	public Edition insertIfNotExistCustom(Integer n1, String v1, String v2) {
		// TODO Auto-generated method stub
		Edition exEdition = selectCustom(n1, v1, v2);

		if (exEdition == null) {
			try {
				Edition nvEdition = new Edition(n1, v1, v2);
				em.persist(nvEdition);
				return nvEdition;
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e);
			}
		}

		return exEdition;
	}

	@Override
	public int updateCustom(EntityManager em, Edition t1, Edition t2) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean deleteCustom(EntityManager em, Edition t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Edition selectCustom(EntityManager em, Edition t) {
		// TODO Auto-generated method stub
		return null;
	}

	public Edition selectCustom(Integer n1, String v1, String v2) {
		// TODO Auto-generated method stub
		querySelect
		.setParameter("p1", n1)
		.setParameter("p2", v1)
		.setParameter("p3", v2);

		List<Edition> edition = querySelect.getResultList();

		if (edition.size() == 0) {
			return null;
		}
		return edition.get(0);

	}

}
