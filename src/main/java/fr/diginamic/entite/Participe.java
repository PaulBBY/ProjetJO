package fr.diginamic.entite;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * 
 * Cette classe est une entité JPA, représentant les participations (relation
 * entre évenement et athlètes, dont son organisation). Données récupérées
 * depuis evenement.csv
 */

@Entity
@Table(name = "participe", uniqueConstraints = @UniqueConstraint(columnNames = { "ID_ATHLETE", "ID_EVENEMENT" }))
public class Participe {

	/**
	 * Identifiant unique de la participation
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	/**
	 * Age de l'Athlete au moment de la participation
	 */
	@Column(name = "AGE")
	private Integer age;

	/**
	 * Taille de l'Athlete au moment de la participation
	 */
	@Column(name = "TAILLE")
	private Float taille;

	/**
	 * Poid de l'Athlete au moment de la participation
	 */
	@Column(name = "POIDS")
	private Float poid;

	/**
	 * Equipe de l'Athlete au moment de la participation (une Organisation peut
	 * avoir plusieurs équipes)
	 */
	@Column(name = "EQUIPE")
	private String equipe;

	/**
	 * Médaile obtenu par l'Athlete à cette participation
	 */
	@Column(name = "MEDAILLE")
	private String medaille;

	/**
	 * Athlete de la participation (contient Nom et Sexe) Relation ManyToOne : la
	 * participation concerne un athlète, un Athlete peut être concerné par
	 * plusieurs participations
	 */
	@ManyToOne
	@JoinColumn(name = "ID_ATHLETE")
	private Athlete athlete;

	/**
	 * Evenement de la participation (contient édition et épreuve) Relation
	 * ManyToOne : la participation concerne un évenement, un évenement peut être
	 * concerné par plusieurs participations(plusieurs Athlétes)
	 */
	@ManyToOne
	@JoinColumn(name = "ID_EVENEMENT")
	private Evenement evenement;

	/**
	 * Organisation de la participation Relation ManyToOne : la participation
	 * concerne une organisation, une organisation peut être concerné par plusieurs
	 * participations
	 */
	@ManyToOne
	@JoinColumn(name = "ID_ORGANISATION")
	private Organisation organisation;

	/**
	 * Constructeur par défault de JPA
	 */
	public Participe() {
	}

	public Participe(Integer age, Float taille, Float poid, String equipe, String medaille, Athlete athlete,
			Evenement evenement, Organisation organisation) {
		this.age = age;
		this.taille = taille;
		this.poid = poid;
		this.equipe = equipe;
		this.medaille = medaille;
		this.athlete = athlete;
		this.evenement = evenement;
		this.organisation = organisation;
	}

	@Override
	public String toString() {
		return "Participe [id=" + id + ", poid=" + poid + ", taille=" + taille + ", medaille=" + medaille + ", equipe="
				+ equipe + ", age=" + age + ", athlete=" + athlete + ", evenement=" + evenement + ", organisation="
				+ organisation + "]";
	}

	public Float getPoid() {
		return poid;
	}

	public void setPoid(Float poid) {
		this.poid = poid;
	}

	public Float getTaille() {
		return taille;
	}

	public void setTaille(Float taille) {
		this.taille = taille;
	}

	public String getMedaille() {
		return medaille;
	}

	public void setMedaille(String medaille) {
		this.medaille = medaille;
	}

	public String getEquipe() {
		return equipe;
	}

	public void setEquipe(String equipe) {
		this.equipe = equipe;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Athlete getAthlete() {
		return athlete;
	}

	public void setAthlete(Athlete athlete) {
		this.athlete = athlete;
	}

	public Evenement getEvenement() {
		return evenement;
	}

	public void setEvenement(Evenement evenement) {
		this.evenement = evenement;
	}

	public Organisation getOrganisation() {
		return organisation;
	}

	public void setOrganisation(Organisation organisation) {
		this.organisation = organisation;
	}

	public int getId() {
		return id;
	}

}
