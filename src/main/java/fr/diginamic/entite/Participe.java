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

@Entity
@Table(name = "participe", uniqueConstraints = @UniqueConstraint(columnNames = { "ID_ATHLETE", "ID_EVENEMENT" }))
public class Participe {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "AGE")
	private Integer age;

	@Column(name = "TAILLE")
	private Float taille;

	@Column(name = "POIDS")
	private Float poid;

	@Column(name = "EQUIPE")
	private String equipe;

	@Column(name = "MEDAILLE")
	private String medaille;

	@ManyToOne
	@JoinColumn(name = "ID_ATHLETE")
	private Athlete athlete;

	@ManyToOne
	@JoinColumn(name = "ID_EVENEMENT")
	private Evenement evenement;

	@ManyToOne
	@JoinColumn(name = "ID_ORGANISATION")
	private Organisation organisation;

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
