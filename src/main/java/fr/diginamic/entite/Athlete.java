package fr.diginamic.entite;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Cette classe est une entité JPA, représentant les caractéristiques "figées" d'un athlète.
 * Données récupérées depuis evenement.csv
 */

@Entity
@Table(name = "athlete", uniqueConstraints = @UniqueConstraint(columnNames = { "NOM" }))
public class Athlete {

	/**
	 * Identifiant unique de l'athlète
	 */
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	
	/**
	 * Nom de l'athlète
	 */
	@Column(name = "NOM")
	private String nom;

	/**
	 * Sexe de l'athlète
	 */
	@Column(name = "SEXE")
	private char sexe;

	/**
	 * List des participations de l'athlète.
	 * Relation one to many : atlhlète a plusieurs participations, une particpation concerne un athlète.
	 */
	@OneToMany(mappedBy = "athlete")
	private List<Participe> participe;

	/**
	 * Constructeur par défault pour JPA
	 */
	public Athlete() {
	}

	/**
	 * Param utilisé dans l'application
	 * @param nom, nom athlète
	 * @param sexe, sexe athlète
	 */
	public Athlete(String nom, char sexe) {
		this.nom = nom;
		this.sexe = sexe;
	}

	@Override
	public String toString() {
		return "Athlete [id=" + id + ", nom=" + nom + ", sexe=" + sexe + ", participe=" + participe + "]";
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public char getSexe() {
		return sexe;
	}

	public void setSexe(char sexe) {
		this.sexe = sexe;
	}

	public List<Participe> getParticipe() {
		return participe;
	}

	public void setParticipe(List<Participe> participe) {
		this.participe = participe;
	}

	public int getId() {
		return id;
	}

}
