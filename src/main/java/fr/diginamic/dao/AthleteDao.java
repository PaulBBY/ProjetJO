package fr.diginamic.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import fr.diginamic.entite.Athlete;

/**
 * DAO pour la classe Athlete
 */
public class AthleteDao implements Dao<Athlete> {
	
	
	/**
	 * Un EntityManager initialisé par le constructeur, 
	 * utilisé dans l'ensemble des méthodes de cette classe
	 */
	private EntityManager em;
	
	/**
	 * Un TypedQuery initialisé dans le constructeur,
	 * utilisé dans certaines méthodes appelées à répétition (pour ne pas avoir  à créer la query à chaque appel de la méthode)
	 */
	private TypedQuery<Athlete> querySelect; 

	/**
	 * Constructeur de la classe AthleteDao
	 * @param em, entityManager créer dans la classe Main ou CreaScript
	 * querySelect créée à partir de em
	 */
	public AthleteDao(EntityManager em) {
		this.em = em;
		this.querySelect =this.em.createQuery("select a from Athlete a " 
						+ "where a.nom= :p1", Athlete.class);
	}

	/**
	 * Non utilisé
	 */
	@Override
	public List<Athlete> selectAll(EntityManager em) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Non utilisé sous cette forme
	 */
	@Override
	public Athlete insertIfNotExistCustom(EntityManager em, Athlete t) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 
	 * @param v1, nom de l'Athléte
	 * @param c1, sexe de l'Athléte
	 * @return retourne un athlète déjà présent en BDD (exAthlete) en utilisant la fonction selectCustom,
	 * avec v1 (son nom) en paramétre.
	 * Si aucun athlète portant v1 en nom existe déjà en BDD, 
	 * la méthode appelle le constructeur, crée un Athlete, le persist (em), et retourne ce dernier (nvAthlete) en objet
	 */
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

	/**
	 * Non utilisé
	 */
	@Override
	public int updateCustom(EntityManager em, Athlete t1, Athlete t2) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * Non utilisé
	 */
	@Override
	public boolean deleteCustom(EntityManager em, Athlete t) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Non utilisé sous cette forme
	 */
	@Override
	public Athlete selectCustom(EntityManager em, Athlete t) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Surcharge de selectCustom hérité de Dao, utilisant un String en param plutôt qu'un objet Athlete
	 * @param v1, nom de l'athléte
	 * @return un objet Athlete portant le nom v1 grâce à JPQL
	 * Si cet objet n'existe pas, return null
	 */
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
