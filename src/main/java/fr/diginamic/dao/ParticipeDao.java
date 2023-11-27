package fr.diginamic.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import fr.diginamic.entite.Athlete;
import fr.diginamic.entite.Epreuve;
import fr.diginamic.entite.Evenement;
import fr.diginamic.entite.Organisation;
import fr.diginamic.entite.Participe;

public class ParticipeDao implements Dao<Participe> {

	
	private EntityManager em;
	private TypedQuery<Participe> querySelect; 
	
	

	public ParticipeDao(EntityManager em) {
		this.em = em;
		this.querySelect = this.em
				.createQuery("select p from Participe p " + "join p.athlete a " + "join p.evenement e "
						+ "where a.id=:p1 " + "and e.id=:p2", Participe.class);
	}

	
	public List<Object[]> selectAllCustom(EntityManager em) {
		// TODO Auto-generated method stub
		TypedQuery<Object[]> query = em.createQuery("select p.medaille, p.athlete.nom, p.equipe, p.evenement.epreuve.epreuveEng, p.evenement.edition.annee, p.evenement.edition.ville from Participe p where p.medaille <> 'NA' order by p.evenement.edition.annee asc", Object[].class);
		return query.getResultList();
	}

	@Override
	public Participe insertIfNotExistCustom(EntityManager em, Participe t) {
		// TODO Auto-generated method stub
		return null;
	}

	public Participe insertIfNotExistCustom(Integer age, Float taille, Float poids, String equipe,
			String medaille, Athlete athlete, Evenement evenement, Organisation organisation) {
		// TODO Auto-generated method stub
		Participe exParticipe = selectCustom(athlete, evenement);
		if (exParticipe == null) {
			try {
				Participe nvParticipe = new Participe(age, taille, poids, equipe, medaille, athlete, evenement,
						organisation);
				em.persist(nvParticipe);
				return nvParticipe;
			} catch (Exception e) {
				// TODO: handle exception
			}

		}
		return exParticipe;
	}

	@Override
	public int updateCustom(EntityManager em, Participe t1, Participe t2) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean deleteCustom(EntityManager em, Participe t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Participe selectCustom(EntityManager em, Participe t) {
		// TODO Auto-generated method stub
		return null;
	}

	public Participe selectCustom(Athlete athlete, Evenement evenemenet) {
		// TODO Auto-generated method stub
		querySelect.setParameter("p1", athlete.getId()).setParameter("p2", evenemenet.getId());

		List<Participe> participe = querySelect.getResultList();

		if (participe.size() == 0) {
			return null;
		}
		return participe.get(0);

	}


	@Override
	public List<Participe> selectAll(EntityManager em) {
		// TODO Auto-generated method stub
		return null;
	}

}
