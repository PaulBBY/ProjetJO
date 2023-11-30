package fr.diginamic.entite;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
/**
 * Cette classe est une entité JPA, regroupant une édition (voir Edition) et une épreuve (voir Epreuve) à ses participations (voir Participation).
 * Données récupérées depuis evenement.csv
 */

@Entity
@Table(name = "evenement", uniqueConstraints = @UniqueConstraint(columnNames = { "EPREUVE_ID", "EDITION_ID" }))
public class Evenement {

	/**
	 * Identifiant unique de l'évenement
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	/**
	 * Edition de l'évenement.
	 * Relation ManyToOne : l'évenement a une édition, une édition a plusieurs évenemnts?
	 */
	@ManyToOne
	@JoinColumn(name = "EDITION_ID")
	private Edition edition;

	/**
	 * Epreuve de l'évenement.
	 * Idem que ci dessus
	 */
	@ManyToOne
	@JoinColumn(name = "EPREUVE_ID")
	private Epreuve epreuve;

	/**
	 * Participations de l'épreuve.
	 * Relation OneToMany : l'évenement à plusieurs participations, une participation concerne un seul évenement.
	 */
	@OneToMany(mappedBy = "evenement")
	private List<Participe> participe;

	/**
	 * Constructeur par défault de JPA
	 */
	public Evenement() {
	}

	public Evenement(Edition edition, Epreuve epreuve) {
		this.edition = edition;
		this.epreuve = epreuve;
	}

	@Override
	public String toString() {
		return "Evenement [id=" + id + ", edition=" + edition.toString() + ", epreuve=" + epreuve.toString() + ", participe=" + participe.toString()
				+ "]";
	}

	public Edition getEdition() {
		return edition;
	}

	public void setEdition(Edition edition) {
		this.edition = edition;
	}

	public Epreuve getEpreuve() {
		return epreuve;
	}

	public void setEpreuve(Epreuve epreuve) {
		this.epreuve = epreuve;
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
