package fr.diginamic.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import fr.diginamic.entite.Edition;
import fr.diginamic.entite.Epreuve;
import fr.diginamic.entite.Evenement;

/**
 * Dao pour la classe Evenement
 */
public class EvenementDao implements Dao<Evenement> {

	/**
	 * Un EntityManager initialisé par le constructeur, utilisé dans l'ensemble des
	 * méthodes de cette classe
	 */
	private EntityManager em;
	/**
	 * Un TypedQuery initialisé dans le constructeur, utilisé dans certaines
	 * méthodes appelées à répétition (pour ne pas avoir à créer la query à chaque
	 * appel de la méthode)
	 */
	private TypedQuery<Evenement> querySelect;

	/**
	 * Constructeur de la classe EvenementDao
	 * 
	 * @param em, entityManager créer dans la classe Main ou CreaScript querySelect
	 *            créée à partir de em
	 */
	public EvenementDao(EntityManager em) {
		this.em = em;
		this.querySelect = this.em.createQuery("select ev from Evenement ev " + "join ev.epreuve ep "
				+ "join ev.edition ed " + "where ep.id=:p1 " + "and ed.id=:p2", Evenement.class);
	}

	/**
	 * Surchage le selectCustom hérité de Dao, utilisé dans le classe CreaScript.
	 * Récupére certains paramètres Epreuve, ayant pour nom d'épreuve cible
	 * 
	 * @param cible, nom d'épreuve
	 * @return une liste d'objets (certains paramêtres de Epreuve, pas tous, pour
	 *         optimiser)
	 */
	public List<Object[]> selectCustomMedailleEpreuve(String cible) {
		TypedQuery<Object[]> query = em.createQuery(
				"select p.medaille, p.athlete.nom, p.equipe, e.epreuve.epreuveEng, e.edition.annee, e.edition.ville from Evenement e "
						+ "join e.participe p where e.epreuve.epreuveEng= :p1 and p.medaille <> 'NA' order by e.edition.annee asc",
				Object[].class).setParameter("p1", cible);
		return query.getResultList();

	}

	/**
	 * Surchage le selectCustom hérité de Dao, utilisé dans le classe CreaScript.
	 * Récupére certains paramètres Epreuve, ayant pour nom de sport cible
	 * 
	 * @param cible, nom sport
	 * @return une liste d'objets (certains paramêtres de Epreuve, pas tous, pour
	 *         optimiser)
	 */
	public List<Object[]> selectCustomMedailleSport(String cible) {
		TypedQuery<Object[]> query = em.createQuery(
				"select p.medaille, p.athlete.nom, p.equipe, e.epreuve.sportEng, e.edition.annee, e.edition.ville from Evenement e "
						+ "join e.participe p where e.epreuve.sportEng= :p1 and p.medaille <> 'NA' order by e.edition.annee asc",
				Object[].class).setParameter("p1", cible);
		return query.getResultList();

	}

	/**
	 * Non utilisé
	 */

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

	/**
	 * 
	 * @param edition, edition de l'évenement
	 * @param epreuve, epreuve de l'évenement
	 * @return retourne un évenement déjà présent en BDD (exEvenement) en utilisant
	 *         la fonction selectCustom. Si aucun évenement n'en ressort
	 *         (selectCustom return null), la méthode appelle le constructeur, crée
	 *         un événement, le persist (em), et retourne ce dernier (nvEvenement)
	 *         en objet
	 */
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

	/**
	 * Non utilisé
	 */
	@Override
	public int updateCustom(EntityManager em, Evenement t1, Evenement t2) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * Non utilisé
	 */
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

	/**
	 * Surcharge de selectCustom hérité de Dao, utilisant un objet Edition et
	 * Epreuve, pour retrouver l'évenement
	 * 
	 * @param edition, edition de l'évenement
	 * @param epreuve, épreuve de l'évenement
	 * @return un objet Evenement correspondant à edition et épreuve, grâce à JPQL
	 *         Si cet objet n'existe pas, return null
	 */
	public Evenement selectCustom(Edition edition, Epreuve epreuve) {
		// TODO Auto-generated method stub
		querySelect.setParameter("p1", epreuve.getId()).setParameter("p2", edition.getId());

		List<Evenement> evenement = querySelect.getResultList();

		if (evenement.size() == 0) {
			return null;
		}
		return evenement.get(0);

	}

	/**
	 * Non utilisé
	 */
	public Evenement selectCustom(EntityManager em, String v1) {
		// TODO Auto-generated method stub

		return null;
	}

}
