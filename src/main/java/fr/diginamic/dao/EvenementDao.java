package fr.diginamic.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import fr.diginamic.entite.Edition;
import fr.diginamic.entite.Epreuve;
import fr.diginamic.entite.Evenement;

public class EvenementDao implements Dao<Evenement> {

	private EntityManager em;
	private TypedQuery<Evenement> querySelect;

	public EvenementDao(EntityManager em) {
		this.em = em;
		this.querySelect = this.em.createQuery("select ev from Evenement ev " + "join ev.epreuve ep "
				+ "join ev.edition ed " + "where ep.id=:p1 " + "and ed.id=:p2", Evenement.class);
	}
	// select e.p.medaille, e.p.// bla from Evenement e join e.participe p where
	// e.sportEng = :p1 and e.medaille <> 'NA' order by

	public List<Object[]> selectCustomMedailleEpreuve(String cible) {
		TypedQuery<Object[]> query = em.createQuery(
				"select p.medaille, p.athlete.nom, p.equipe, e.epreuve.epreuveEng, e.edition.annee, e.edition.ville from Evenement e "
						+ "join e.participe p where e.epreuve.epreuveEng= :p1 and p.medaille <> 'NA' order by e.edition.annee asc",
				Object[].class).setParameter("p1", cible);
		return query.getResultList();

	}

	public List<Object[]> selectCustomMedailleSport(String cible) {
		TypedQuery<Object[]> query = em.createQuery(
				"select p.medaille, p.athlete.nom, p.equipe, e.epreuve.sportEng, e.edition.annee, e.edition.ville from Evenement e "
						+ "join e.participe p where e.epreuve.sportEng= :p1 and p.medaille <> 'NA' order by e.edition.annee asc",
				Object[].class).setParameter("p1", cible);
		return query.getResultList();


	}

	@Override
	public List<Evenement> selectAll(EntityManager em) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Evenement insertIfNotExistCustom(EntityManager em, Evenement t) {
		// TODO Auto-generated method stub
		// Evenement exEvenement = selec()
		return null;
	}

	public Evenement insertIfNotExistCustom(Edition edition, Epreuve epreuve) {
		// TODO Auto-generated method stub
		Evenement exEvenement = selectCustom(edition, epreuve);
		if (exEvenement == null) {
			try {
				Evenement nvEvenement = new Evenement(edition, epreuve);
				em.persist(nvEvenement);
				return nvEvenement;
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e);
			}

		}
		return exEvenement;
	}

	@Override
	public int updateCustom(EntityManager em, Evenement t1, Evenement t2) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean deleteCustom(EntityManager em, Evenement t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Evenement selectCustom(EntityManager em, Evenement t) {
		// TODO Auto-generated method stub
		return null;
	}

	public Evenement selectCustom(Edition edition, Epreuve epreuve) {
		// TODO Auto-generated method stub
		querySelect.setParameter("p1", epreuve.getId()).setParameter("p2", edition.getId());

		List<Evenement> evenement = querySelect.getResultList();

		if (evenement.size() == 0) {
			return null;
		}
		return evenement.get(0);

	}

	public Evenement selectCustom(EntityManager em, String v1) {
		// TODO Auto-generated method stub
		/*
		 * TypedQuery<Athlete> query = em.createQuery("select ev from Evenement ev " +
		 * "join ev.epreuve ep " + "join ev.edition ed " + "where ep.epreuveEng= :p1 " +
		 * "and ed." + "", Athlete.class) .setParameter("p1", v1);
		 * 
		 * List<Athlete> athlete = query.getResultList();
		 * 
		 * if (athlete.size() == 0) { return null; } return athlete.get(0); return null;
		 */
		return null;
	}

}
