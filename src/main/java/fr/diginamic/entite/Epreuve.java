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
@Table(name = "epreuve", uniqueConstraints = @UniqueConstraint(columnNames = { "EPREUVE_ENG" }))
public class Epreuve {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "EPREUVE_ENG")
	private String epreuveEng;

	@Column(name = "EPREUVE_FR")
	private String epreuveFr;

	@Column(name = "SPORT_ENG")
	private String sportEng;

	@Column(name = "SPORT_FR")
	private String sportFr;

	@OneToMany(mappedBy = "epreuve")
	private List<Evenement> evenement;

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
