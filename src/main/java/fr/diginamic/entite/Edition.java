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

@Entity
@Table(name = "edition", uniqueConstraints = @UniqueConstraint(columnNames = { "ANNEE", "SAISON", "VILLE" }))
public class Edition {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	// a verif
	@Column(name = "ANNEE")
	private Integer annee;

	@Column(name = "SAISON")
	private String saison;

	@Column(name = "VILLE")
	private String ville;

	@OneToMany(mappedBy="edition")
	private List<Evenement> evenement;

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
