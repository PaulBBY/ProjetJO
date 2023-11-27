package fr.diginamic.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import fr.diginamic.entite.Epreuve;

public class EpreuveDao implements Dao<Epreuve> {
	
	private EntityManager em;
	private TypedQuery<Epreuve> querySelect; 

	public EpreuveDao(EntityManager em) {
		this.em = em;
		this.querySelect =this.em.createQuery("select e from Epreuve e " + "where e.epreuveEng = :p1", Epreuve.class);
	}

	@Override
	public List<Epreuve> selectAll(EntityManager em) {
		// TODO Auto-generated method stub
		TypedQuery<Epreuve> query = em.createQuery("select o from Epreuve o", Epreuve.class);

		List<Epreuve> epreuve = query.getResultList();
		return epreuve;
	}

	@Override
	public Epreuve insertIfNotExistCustom(EntityManager em, Epreuve t) {
		// TODO Auto-generated method stub
		return null;
	}

	public Epreuve insertIfNotExistCustom(String nomEpreuve, String nomSport) {
		// TODO Auto-generated method stub
		Epreuve exEpreuve = selectCustom(nomEpreuve);
		if (exEpreuve == null) {
			try {
				Epreuve nvEpreuve = new Epreuve(nomEpreuve, nomSport);
				em.persist(nvEpreuve);
				return nvEpreuve;
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e);
			}
		}
		return exEpreuve;
	}

	@Override
	public int updateCustom(EntityManager em, Epreuve t1, Epreuve t2) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public int updateCustom(Epreuve epreuve, String tradSport, String tradEpreuve) {
		// TODO Auto-generated method stub
		epreuve.setSportFr(tradSport);
		epreuve.setEpreuveFr(tradEpreuve);
		
		return 1;
	}


	@Override
	public boolean deleteCustom(EntityManager em, Epreuve t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Epreuve selectCustom(EntityManager em, Epreuve t) {
		// TODO Auto-generated method stub
		return null;
	}

	public Epreuve selectCustom(String nomEpreuve) {
		// TODO Auto-generated method stub
		
		querySelect.setParameter("p1", nomEpreuve);

		List<Epreuve> epreuve = querySelect.getResultList();
		if (epreuve.size() == 0) {
			return null;
		}
		return epreuve.get(0);

	}

}
