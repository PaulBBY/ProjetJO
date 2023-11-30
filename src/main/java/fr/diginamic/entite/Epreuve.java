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
 * Cette classe est une entité JPA, regroupant les noms en Anglais et en Français des épreuves et des Sports, et leurs évenements associés (voir Evenement)
 */
@Entity
@Table(name = "epreuve", uniqueConstraints = @UniqueConstraint(columnNames = { "EPREUVE_ENG" }))
public class Epreuve {
	
	/**
	 * Identifant unique de l'épreuve
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	/**
	 * Nom Anglaise de l'épreuve
	 */
	@Column(name = "EPREUVE_ENG")
	private String epreuveEng;

	/**
	 * Nom Français de l'épreuve
	 */
	@Column(name = "EPREUVE_FR")
	private String epreuveFr;

	/**
	 * Nom Anglais du Sport
	 */
	@Column(name = "SPORT_ENG")
	private String sportEng;

	/**
	 * Nom Français du Sport
	 */
	@Column(name = "SPORT_FR")
	private String sportFr;

	/**
	 * Evenements associés à une épreuve
	 * Remation OneToMant : une épreuve peut être dans plusieurs évenments, un évenement ne concerne qu'une épreuve
	 */
	@OneToMany(mappedBy = "epreuve")
	private List<Evenement> evenement;

	/**
	 * Constructeur par défault JPA
	 */
	public Epreuve() {
	}

	public Epreuve(String epreuveEng, String sportEng) {
		this.epreuveEng = epreuveEng;
		this.sportEng = sportEng;
	}

	@Override
	public String toString() {
		return "Epreuve [id=" + id + ", epreuveEng=" + epreuveEng + ", epreuveFr=" + epreuveFr + ", sportEng="
				+ sportEng + ", sportFr=" + sportFr + ", evenement=" + evenement + "]";
	}

	public String getEpreuveEng() {
		return epreuveEng;
	}

	public void setEpreuveEng(String epreuveEng) {
		this.epreuveEng = epreuveEng;
	}

	public String getEpreuveFr() {
		return epreuveFr;
	}

	public void setEpreuveFr(String epreuveFr) {
		this.epreuveFr = epreuveFr;
	}

	public String getSportEng() {
		return sportEng;
	}

	public void setSportEng(String sportEng) {
		this.sportEng = sportEng;
	}

	public String getSportFr() {
		return sportFr;
	}

	public void setSportFr(String sportFr) {
		this.sportFr = sportFr;
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
