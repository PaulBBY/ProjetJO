package fr.diginamic.creation;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;

import fr.diginamic.dao.AthleteDao;
import fr.diginamic.dao.EditionDao;
import fr.diginamic.dao.EpreuveDao;
import fr.diginamic.dao.EvenementDao;
import fr.diginamic.dao.OrganisationDao;
import fr.diginamic.dao.ParticipeDao;
import fr.diginamic.entite.Athlete;
import fr.diginamic.entite.Edition;
import fr.diginamic.entite.Epreuve;
import fr.diginamic.entite.Evenement;
import fr.diginamic.entite.Organisation;

/**
 * Cette classe regroupe les méthodes permettant l'implémentation de la base de données depuis les fichiers csv, ainsi que les traductions
 */
public class CreaScript {
	
	private EntityManager em;
	
	private AthleteDao athleteDao;
	private OrganisationDao organisationDao;
	private EditionDao editionDao;
	private EpreuveDao epreuveDao;

	private EvenementDao evenementDao;
	private ParticipeDao participeDao;
	
	public CreaScript(EntityManager em, AthleteDao athleteDao, OrganisationDao organisationDao, EditionDao editionDao,
			EpreuveDao epreuveDao, EvenementDao evenementDao, ParticipeDao participeDao) {
		this.em = em;
		this.athleteDao = athleteDao;
		this.organisationDao = organisationDao;
		this.editionDao = editionDao;
		this.epreuveDao = epreuveDao;
		this.evenementDao = evenementDao;
		this.participeDao = participeDao;
	}

	private String paramOrga = "C:\\Pro\\JO\\liste_des_organisations.csv";
	Path pathListOrga = Paths.get(paramOrga);

	private String paramSport = "C:\\Pro\\JO\\liste_des_sports.csv";
	Path pathListSport = Paths.get(paramSport);

	private String paramEpreuve = "C:\\Pro\\JO\\liste_des_epreuves.csv";
	Path pathListEpreuve = Paths.get(paramEpreuve);

	private String paramEvenement = "C:\\Pro\\JO\\evenements.csv";
	Path pathlistEvenement = Paths.get(paramEvenement);

	//List<Epreuve> epreuves = new ArrayList<>();
	//List<Organisation> organisations = new ArrayList<>();

	public void majOrganisation() throws IOException {

		List<String> lignes = CreaUtil.lire(pathListOrga);
		HashMap<String, String[]> hashLignes = new HashMap<>();

		for (String ligne : lignes) {
			String[] values = CreaUtil.formate(ligne);
			String index = values[0];

			hashLignes.put(index, values);
		}

		List<Organisation> organisation = organisationDao.selectAll(em);
		Iterator<Organisation> itOrga = organisation.iterator();

		Boolean obsolete = null;

		em.getTransaction().begin();

		int test = 0;
		String elimine = null;

		while (itOrga.hasNext()) {
			Organisation orga = itOrga.next();
			System.out.println("je suis là");

			for (String k : hashLignes.keySet()) {
				if (k.equals(orga.getCio())) {
					String[] local = hashLignes.get(k);
					if (local[4].equals("N")) {
						obsolete = false;
					} else {
						obsolete = true;
					}
					organisationDao.updateCustom(orga, local[1], local[2], local[3], obsolete);
					elimine = k;
					break;
				}

			}

			hashLignes.remove(elimine);

		}
		em.getTransaction().commit();
	}

	public void majEpreuveSport() throws IOException {
		
		List<String> lignesEpreuve = CreaUtil.lire(pathListEpreuve);
		List<String> lignesSport = CreaUtil.lire(pathListSport);
		HashMap<String, String> hashLignesEpreuve = new HashMap<>();
		HashMap<String, String> hashLignesSport = new HashMap<>();

		for (String ligne : lignesEpreuve) {
			String[] values = CreaUtil.formate(ligne);
			if(values.length!=2) {
				continue;
			}
			hashLignesEpreuve.put(values[0], values[1]);
		}
		
		for (String ligne : lignesSport) {
			String[] values = CreaUtil.formate(ligne);
			if(values.length!=2) {
				continue;
			}
			hashLignesSport.put(values[0], values[1]);
		}
		
		
		List<Epreuve> organisation = epreuveDao.selectAll(em);
		Iterator<Epreuve> itEpreuve = organisation.iterator();

		
		String elimineSport = null;
		String elimineEpreuve = null;
		String tradSport = null;
		String tradEpreuve = null;
		
		em.getTransaction().begin();

		
		while (itEpreuve.hasNext()) {
			
			Epreuve epreuve = itEpreuve.next();
			
			for (String k : hashLignesSport.keySet()) {
				if (k.equals(epreuve.getSportEng())) {					
					elimineSport = k;
					tradSport = hashLignesSport.get(k);
					break;
				}
			}
			
			for (String k : hashLignesEpreuve.keySet()) {
				if (k.equals(epreuve.getEpreuveEng())) {					
					elimineEpreuve = k;
					tradEpreuve = hashLignesEpreuve.get(k);
					break;
				}
			}
			
			System.out.println("+++++++" +tradEpreuve+"+++++"+tradSport);
			
			epreuveDao.updateCustom(epreuve, tradSport, tradEpreuve);
			tradSport=null;
			tradEpreuve=null;
			hashLignesSport.remove(elimineSport);
			hashLignesEpreuve.remove(elimineEpreuve);	
			
			
		}
		
		em.getTransaction().commit();
	}

	public void majEpreuve() throws IOException {

	}

	public void creaEvenement() throws IOException {

		List<String> lignes = CreaUtil.lire(pathlistEvenement);

		int i = 0;
		Boolean cond = true;

		for (String ligne : lignes) {

			if (cond == true) {

				em.getTransaction().begin();
				cond = false;
			}

			i++;

			String[] valeurs = CreaUtil.formate(ligne);

			// Vérif homo données
			if (valeurs.length != 15) {
				continue;
			}

			// Valeurs pour Athlete
			String nomAthlete = valeurs[1];
			char sexeAthlete = valeurs[2].charAt(0);

			// Valeurs pour Participe
			Integer ageParticipe = null;
			try {
				ageParticipe = Integer.parseInt(valeurs[3]);
			} catch (Exception e) {
				System.out.println(e);
			}

			Float tailleParticipe = null;
			try {
				tailleParticipe = Float.parseFloat(valeurs[4]);
			} catch (Exception e) {
				System.out.println(e);
			}

			Float poidsParticipe = null;
			try {
				poidsParticipe = Float.parseFloat(valeurs[5]);
			} catch (Exception e) {
				System.out.println(e);
			}

			String equipeParticipe = valeurs[6];
			String medailleParticipe = valeurs[14];

			// Valeurs pour organisation
			String codeOrganisation = valeurs[7];

			// Valeurs pour edition
			Integer anneeEdition = null;
			try {
				anneeEdition = Integer.parseInt(valeurs[9]);
			} catch (Exception e) {
				// TODO: handle exception
			}

			String saisonEdition = valeurs[10];
			String villeEdition = valeurs[11];

			// Valeurs pour Epreuve
			String nomSportEpreuve = valeurs[12];
			String nomEpreuve = valeurs[13];

			// athlete
			
			Athlete athlete = athleteDao.insertIfNotExistCustom(nomAthlete, sexeAthlete);
			Organisation organisation = organisationDao.insertIfNotExistCustom(codeOrganisation);
			Edition edition = editionDao.insertIfNotExistCustom(anneeEdition, saisonEdition, villeEdition);
			Epreuve epreuve = epreuveDao.insertIfNotExistCustom(nomEpreuve, nomSportEpreuve);

			Evenement evenement = evenementDao.insertIfNotExistCustom(edition, epreuve);

			participeDao.insertIfNotExistCustom(ageParticipe, tailleParticipe, poidsParticipe, equipeParticipe,
					medailleParticipe, athlete, evenement, organisation);


			if (i % 200 == 0) {
				em.getTransaction().commit();
				cond = true;
			}
			
			
		}
		if(cond == false) {
			em.getTransaction().commit();
		}
	}

	public void script() throws IOException {
	System.out.println("là");
		creaEvenement();
		majOrganisation();
		majEpreuveSport();

	}

}
