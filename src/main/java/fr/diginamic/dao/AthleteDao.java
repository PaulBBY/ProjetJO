package fr.diginamic.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import fr.diginamic.entite.Athlete;

public class AthleteDao implements Dao<Athlete> {
	
	private EntityManager em;
	private TypedQuery<Athlete> querySelect; 

	public AthleteDao(EntityManager em) {
		this.em = em;
		this.querySelect =this.em.createQuery("select a from Athlete a " 
						+ "where a.nom= :p1", Athlete.class);
	}

	@Override
	public List<Athlete> selectAll(EntityManager em) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Athlete insertIfNotExistCustom(EntityManager em, Athlete t) {
		// TODO Auto-generated method stub
		return null;
	}

	public Athlete insertIfNotExistCustom(String v1, char c1) {
		// TODO Auto-generated method stub
		Athlete exAthlete = selectCustom(v1);

		if (exAthlete == null) {
			try {
				Athlete nvAthlete = new Athlete(v1, c1);
				em.persist(nvAthlete);
				return nvAthlete;
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e);
			}
			
		} 
		
		return exAthlete;
		

	}

	@Override
	public int updateCustom(EntityManager em, Athlete t1, Athlete t2) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean deleteCustom(EntityManager em, Athlete t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Athlete selectCustom(EntityManager em, Athlete t) {
		// TODO Auto-generated method stub
		return null;
	}

	public Athlete selectCustom(String v1) {
		// TODO Auto-generated method stub
		querySelect.setParameter("p1", v1);

		List<Athlete> athlete = querySelect.getResultList();

		if (athlete.size() == 0) {
			return null;
		}
		return athlete.get(0);
	}

}
