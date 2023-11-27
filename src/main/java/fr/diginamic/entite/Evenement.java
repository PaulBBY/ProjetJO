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

@Entity
@Table(name = "evenement", uniqueConstraints = @UniqueConstraint(columnNames = { "EPREUVE_ID", "EDITION_ID" }))
public class Evenement {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	@JoinColumn(name = "EDITION_ID")
	private Edition edition;

	@ManyToOne
	@JoinColumn(name = "EPREUVE_ID")
	private Epreuve epreuve;

	@OneToMany(mappedBy = "evenement")
	private List<Participe> participe;

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
