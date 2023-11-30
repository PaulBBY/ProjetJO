package fr.diginamic.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import fr.diginamic.entite.Edition;

/**
 * Dao pour la classe Edition
 */
public class EditionDao implements Dao<Edition> {

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
	private TypedQuery<Edition> querySelect;

	/**
	 * Constructeur de la classe EditionDao
	 * 
	 * @param em, entityManager créer dans la classe Main ou CreaScript querySelect
	 *            créée à partir de em
	 */
	public EditionDao(EntityManager em) {
		this.em = em;
		this.querySelect = this.em.createQuery(
				"select e from Edition e " + "where e.annee= :p1 " + "and e.saison= :p2 " + "and e.ville= :p3",
				Edition.class);
	}

	/**
	 * Non utilisé
	 */
	@Override
	public List<Edition> selectAll(EntityManager em) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Non utilisé sous cette forme
	 */
	@Override
	public Edition insertIfNotExistCustom(EntityManager em, Edition t) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 
	 * @param n1, année
	 * @param v1, saison
	 * @param v2, ville
	 * @return retourne une édition déjà présente en BDD (exEdition) en utilisant la fonction selectCustom,
	 * avec n1, v1 et v2 en paramétre.
	 * Si aucune édition n'en ressort (selectCustom return null), 
	 * la méthode appelle le constructeur, crée une édition, le persist (em), et retourne ce dernier (nvEdition) en objet
	 */
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

	/**
	 * Non utilisé
	 */
	@Override
	public int updateCustom(EntityManager em, Edition t1, Edition t2) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * Non utilisé
	 */
	@Override
	public boolean deleteCustom(EntityManager em, Edition t) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Non utilisé sous cette forme
	 */
	@Override
	public Edition selectCustom(EntityManager em, Edition t) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Surcharge le selectCustom hérité de Dao, utilisant un Integer et des String en param plutôt qu'un objet Edition
	 * @param n1, année
	 * @param v1, saison
	 * @param v2, ville
	 * @return un objet Edition portant les paramétres grâce à JPQL
	 * Si cet objet n'existe pas, return null
	 */
	public Edition selectCustom(Integer n1, String v1, String v2) {
		// TODO Auto-generated method stub
		querySelect.setParameter("p1", n1).setParameter("p2", v1).setParameter("p3", v2);

		List<Edition> edition = querySelect.getResultList();

		if (edition.size() == 0) {
			return null;
		}
		return edition.get(0);

	}

}
