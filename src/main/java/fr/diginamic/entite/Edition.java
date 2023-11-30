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
 * Cette classe est une entité JPA, représentant les éditions (relation entre l'année, la saison, la ville et ses épreuves)/
 * Données récupérées de puis evenement.csv
 */
@Entity
@Table(name = "edition", uniqueConstraints = @UniqueConstraint(columnNames = { "ANNEE", "SAISON", "VILLE" }))
public class Edition {

	
	/**
	 * Identifiant unique de l'édition
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	/**
	 * Année de l'édition
	 */
	@Column(name = "ANNEE")
	private Integer annee;

	/**
	 * Saison de l'édition
	 */
	@Column(name = "SAISON")
	private String saison;

	/**
	 * Ville de l'édition
	 */
	@Column(name = "VILLE")
	private String ville;
	
	/**
	 * Evenements de l'édition
	 * Relation OnetoMany : l'édition a plusieurs évenements, un évenement n'est concerné que par une seul édition
	 */
	@OneToMany(mappedBy="edition")
	private List<Evenement> evenement;

	/**
	 * Constructeur par défault de JPA
	 */
	public Edition() {
	}

	public Edition(int annee, String saison, String ville) {
		this.annee = annee;
		this.saison = saison;
		this.ville = ville;
	}

	@Override
	public String toString() {
		return "Edition [id=" + id + ", annee=" + annee + ", saison=" + saison + ", ville=" + ville + ", evenements="
				+ evenement + "]";
	}

	public Integer getAnnee() {
		return annee;
	}

	public void setAnnee(Integer annee) {
		this.annee = annee;
	}

	public String getSaison() {
		return saison;
	}

	public void setSaison(String saison) {
		this.saison = saison;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public List<Evenement> getEvenement() {
		return evenement;
	}

	public void setEvenement(List<Evenement> evenement) {
		this.evenement = evenement;
	}

	public int getId() {
		return id;
	}

}
