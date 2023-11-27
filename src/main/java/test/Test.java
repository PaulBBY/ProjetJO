package test;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.transaction.Transaction;
import javax.transaction.TransactionManager;

import org.hibernate.internal.build.AllowSysOut;

import fr.diginamic.creation.CreaScript;
import fr.diginamic.dao.AthleteDao;
import fr.diginamic.dao.EditionDao;
import fr.diginamic.dao.EpreuveDao;
import fr.diginamic.dao.EvenementDao;
import fr.diginamic.dao.OrganisationDao;
import fr.diginamic.dao.ParticipeDao;
import fr.diginamic.entite.Athlete;
import fr.diginamic.entite.Evenement;
import fr.diginamic.entite.Organisation;
import fr.diginamic.entite.Participe;

public class Test {

	public int demandeInt() {
		
		Scanner scan = new Scanner(System.in);
		
		Boolean condition = true;
		int demande = 0;
		
		while(condition) {
			try {
				demande = Integer.parseInt(scan.next());				
				condition = false;
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("Ordre non valide");
			}
		}
		return demande;

		

	}

	public String demandeString() {

		Scanner scan = new Scanner(System.in);

		String demande = scan.nextLine();
		return demande;

	}

	public void init(EntityManager em, AthleteDao athleteDao, OrganisationDao organisationDao, EditionDao editionDao,
			EpreuveDao epreuveDao, EvenementDao evenementDao, ParticipeDao participeDao) throws IOException {

		EntityManagerFactory entityManagerFactoryCreate = Persistence.createEntityManagerFactory("PaulJOCreate");
		EntityManager emInit = entityManagerFactoryCreate.createEntityManager();
		emInit.close();

		CreaScript go = new CreaScript(em, athleteDao, organisationDao, editionDao, epreuveDao, evenementDao,
				participeDao);

		go.script();

	}

	public void askInit(EntityManager em, AthleteDao athleteDao, OrganisationDao organisationDao, EditionDao editionDao,
			EpreuveDao epreuveDao, EvenementDao evenementDao, ParticipeDao participeDao) throws IOException {

		System.out.println("Voulez vous :\n " + "1. Initier la base de données\n " + "2. Utiliser la base de données\n "
				+ "3. Quitter");

		int ordre = 0;

		while (true) {
			
		
			ordre = demandeInt();
			System.out.println(ordre);
			switch (ordre) {
			case 1:
				init(em, athleteDao, organisationDao, editionDao, epreuveDao, evenementDao, participeDao);
				break;
			case 2:
				askAction(em, athleteDao, organisationDao, editionDao, epreuveDao, evenementDao, participeDao);
				break;
			case 3:
				break;
			default:
				System.out.println("Demande non reconnue");
			}

		}

	}

	public void askAction(EntityManager em, AthleteDao athleteDao, OrganisationDao organisationDao,
			EditionDao editionDao, EpreuveDao epreuveDao, EvenementDao evenementDao, ParticipeDao participeDao)
			throws IOException {

		int ordre = 0;
		while (true) {

			System.out.println("Voulez vous :\n " + "1. Tableau des médailles depuis la création des JO\n "
					+ "2. Tabeau des médailles par sport\n " + "3. Tableau des médailles par sport et épreuve\n "
					+ "4. Revenir au précédent menu\n " + "5. Quitter");

			ordre = demandeInt();
			switch (ordre) {
			case 1:
				System.out.println("voici l'ensemble des médailles obtenues depuis la création des JO :");
				List<Object[]> toutesMedailles = participeDao.selectAllCustom(em);

				for (Object[] o : toutesMedailles) {
					System.out.println("Médaille : " + o[0] + ", Athlete : " + o[1] + ", Equipe : " + o[2]
							+ ", Epreuve : " + o[3] + ", Année : " + o[4] + ", Ville : " + o[5]);
				}
				break;

			case 2:
				System.out.println("Quel sport recherchez vous ?");
				String demandeSport = demandeString();
				List<Object[]> medaillesSport = evenementDao.selectCustomMedailleSport(demandeSport);
				System.out.println("Voici les médailles pour le sport : " + demandeSport);

				for (Object[] o : medaillesSport) {
					System.out.println("Médaille : " + o[0] + ", Athlete : " + o[1] + ", Equipe : " + o[2]
							+ ", Sport : " + o[3] + ", Année : " + o[4] + ", Ville : " + o[5]);
				}

				break;

			case 3:
				System.out.println("Quelle épreuve recherchez vous ?");
				String demandeEpreuve = demandeString();
				List<Object[]> medaillesEpreuve = evenementDao.selectCustomMedailleEpreuve(demandeEpreuve);
				System.out.println("Voici les médailles pour le sport : " + demandeEpreuve);
				System.out.println(medaillesEpreuve.size());
				for (Object[] o : medaillesEpreuve) {
					System.out.println("Médaille : " + o[0] + ", Athlete : " + o[1] + ", Equipe : " + o[2]
							+ ", Epreuve : " + o[3] + ", Année : " + o[4] + ", Ville : " + o[5]);
				}

				break;

			case 4:
				askInit(em, athleteDao, organisationDao, editionDao, epreuveDao, evenementDao, participeDao);
				break;
			case 5:
				break;

			default:
				System.out.println("Demande non reconnue");
			}
		}
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		EntityManagerFactory entityManagerFactoryUse = Persistence.createEntityManagerFactory("PaulJOUse");
		EntityManager em = entityManagerFactoryUse.createEntityManager();

		AthleteDao athleteDao = new AthleteDao(em);
		OrganisationDao organisationDao = new OrganisationDao(em);
		EditionDao editionDao = new EditionDao(em);
		EpreuveDao epreuveDao = new EpreuveDao(em);

		EvenementDao evenementDao = new EvenementDao(em);
		ParticipeDao participeDao = new ParticipeDao(em);

		Test test = new Test();
		test.askInit(em, athleteDao, organisationDao, editionDao, epreuveDao, evenementDao, participeDao);
		;

		System.out.println("Merci d'avoir utiliser cette appication");
		em.close();

	}

}
